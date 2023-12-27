package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.Model.model_account_sigin;
import com.example.tgdt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;

public class Dangki extends AppCompatActivity {

    private EditText edt_tentk, edt_sdt, edt_passwork, edt_passwork2;
    private Button bt_trove, bt_dangki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);

        init_view();
        click_trove();
        click_dangki();
    }

    private void click_dangki() {

        bt_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_tentk.getText().toString().length() != 0 && edt_sdt.getText().toString().length() != 0
                        && edt_passwork.getText().toString().length() != 0 && edt_passwork2.getText().toString().length() != 0) {

                    if (edt_passwork.getText().toString().equals(edt_passwork2.getText().toString())) {

                        model_account_sigin model_account_sigin = new model_account_sigin();
                        model_account_sigin.setUsername(edt_tentk.getText().toString());
                        model_account_sigin.setPasswork(edt_passwork.getText().toString());
                        model_account_sigin.setSdt(edt_sdt.getText().toString());
                        model_account_sigin.setAvata("");
                        model_account_sigin.setLoaitk("nguoidung");

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.child("user").hasChild(edt_sdt.getText().toString())) {
                                    Toast.makeText(getApplicationContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                                }else {

                                    FirebaseDatabase.getInstance().getReference().child("user").child(edt_sdt.getText().toString())
                                            .child("info").setValue(model_account_sigin).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            FirebaseDatabase.getInstance().getReference().child("user").child("all")
                                                    .child("info").setValue(model_account_sigin).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    Toast.makeText(getApplicationContext(), "Đăng kí thành công", Toast.LENGTH_SHORT).show();

                                                    Intent intent = new Intent(Dangki.this, MainActivity.class);
                                                    intent.putExtra(Key_Account.Phone, edt_sdt.getText().toString() +"");
                                                    intent.putExtra(Key_Account.Passworrk, edt_passwork.getText().toString());
                                                    startActivity(intent);

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }else {
                        Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void click_trove() {
        bt_trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init_view() {
        edt_tentk = findViewById(R.id.txt_tentk);
        edt_sdt = findViewById(R.id.txt_sdt);
        edt_passwork = findViewById(R.id.edt_passwork);
        edt_passwork2 = findViewById(R.id.edt_passwork2);

        bt_dangki = findViewById(R.id.bt_dangki);
        bt_trove = findViewById(R.id.bt_trove);
    }
}