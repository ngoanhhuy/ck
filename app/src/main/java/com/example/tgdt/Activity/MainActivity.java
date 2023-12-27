package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.Model.model_account_sigin;
import com.example.tgdt.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.security.Key;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText txt_sdt, txt_passwork;
    private CheckBox checkbox;
    private Button bt_dangnhap, bt_dangki;
    private ArrayList<model_account_sigin> account_sigins;

    private ProgressDialog progressDialog;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_view();
        click_dangki();
        click_dangnhap();
        setProgress_dialog();
        get_data_intent_dangki();
        set_nho_mat_khau();
    }

    private void set_nho_mat_khau() {
        if (sharedPreferences.getBoolean("checkbox", false) == true
        && sharedPreferences.getString(Key_Account.Phone, "").length() > 0
        && sharedPreferences.getString(Key_Account.Passworrk, "").length() > 0 ) {

            txt_sdt.setText(sharedPreferences.getString(Key_Account.Phone, ""));
            txt_passwork.setText(sharedPreferences.getString(Key_Account.Passworrk, ""));
            checkbox.setChecked(true);
            Intent intent = new Intent(MainActivity.this, Home.class);
            intent.putExtra(Key_Account.Phone, sharedPreferences.getString(Key_Account.Phone, ""));
            startActivity(intent);
        }
    }

    private void get_data_intent_dangki() {
        if (getIntent() != null) {
            txt_sdt.setText(getIntent().getStringExtra(Key_Account.Phone));
            txt_passwork.setText(getIntent().getStringExtra(Key_Account.Passworrk));
        }
    }

    private void setProgress_dialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đăng nhập...");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void click_dangnhap() {

        bt_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txt_passwork.getText().length() != 0 && txt_sdt.getText().length() != 0) {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child("user").hasChild(txt_sdt.getText().toString())){

                        FirebaseDatabase.getInstance().getReference().child("user").child(txt_sdt.getText().toString()).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                if (snapshot.exists()){

                                    account_sigins.add(snapshot.getValue(model_account_sigin.class));


                                    if (account_sigins.get(0).getSdt().equals(txt_sdt.getText().toString())
                                    && account_sigins.get(0).getPasswork().equals(txt_passwork.getText().toString())) {

                                        progressDialog.cancel();
                                        if (checkbox.isChecked()) {
                                            editor = sharedPreferences.edit();
                                            editor.putString(Key_Account.Passworrk, txt_passwork.getText().toString());
                                            editor.putString(Key_Account.Phone, txt_sdt.getText().toString());
                                            editor.putBoolean("checkbox", true);
                                            editor.commit();
                                        }else {
                                            editor = sharedPreferences.edit();
                                            editor.remove(Key_Account.Passworrk);
                                            editor.remove(Key_Account.Phone);
                                            editor.remove("checkbox");
                                            editor.commit();
                                        }

                                        Intent intent = new Intent(MainActivity.this, Home.class);
                                        intent.putExtra(Key_Account.Phone, txt_sdt.getText().toString());
                                        startActivity(intent);

                                    }

                                }

                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                progressDialog.cancel();
                                Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else {

                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(), "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

                }else {
                    Toast.makeText(getApplicationContext(), "Nhập vào tài khoản mật khẩu trước", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void click_dangki() {
        bt_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Dangki.class));
            }
        });
    }

    private void init_view() {
        txt_sdt = findViewById(R.id.txt_sdt);
        txt_passwork = findViewById(R.id.txt_passwork);
        checkbox = findViewById(R.id.checkbox);
        bt_dangki = findViewById(R.id.bt_dangki);
        bt_dangnhap = findViewById(R.id.bt_dangnhap);

        account_sigins = new ArrayList<>();

        sharedPreferences = getSharedPreferences("SAVE", MODE_PRIVATE);

    }
}