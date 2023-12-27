package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.Model.model_account_sigin;
import com.example.tgdt.Model.model_dathang;
import com.example.tgdt.Model.model_sanpham;
import com.example.tgdt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Quanly_shop extends AppCompatActivity {

    private TextView txt_sodondangxuly, txt_sodondaxuly, txt_sohang, txt_sotk, txt_tongsotien;

    private ArrayList<model_dathang> arrayList_dangxuly;
    private ArrayList<model_dathang> arrayList_daxuly;
    private ArrayList<model_sanpham> arrayList_sp;
    private BottomNavigationView bottom_menu;
    private ArrayList<model_account_sigin> arrayList_tk;

    private String sdt;
    private String loaitk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_shop);

        initview();
        get_sodondangxuly();
        get_sodondaxuly();
        get_sohang();
        get_sotk();
        get_data_intent();
        click_bottom_menu();
    }
    private void get_data_intent() {
        if (getIntent() != null) {
            sdt = getIntent().getStringExtra(Key_Account.Phone);
            loaitk = getIntent().getStringExtra(Key_Account.Loaitk);
        }
    }


    private void click_bottom_menu() {
        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_bottom_sanpham:
                        item.setChecked(true);
                        item.setChecked(true);
                        Intent intent_sanpham = new Intent(Quanly_shop.this, Quanly.class);
                        intent_sanpham.putExtra(Key_Account.Phone, sdt);
                        intent_sanpham.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent_sanpham);
                        break;
                    case R.id.menu_bottom_themhang:
                        item.setChecked(true);
                        Intent intent_themhang = new Intent(Quanly_shop.this, Quanly_themhang.class);
                        intent_themhang.putExtra(Key_Account.Phone, sdt);
                        intent_themhang.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent_themhang);
                        break;
                    case R.id.menu_bottom_donhang:
                        item.setChecked(true);
                        Intent intent_donhang = new Intent(Quanly_shop.this, Quanly_donhang.class);
                        intent_donhang.putExtra(Key_Account.Phone, sdt);
                        intent_donhang.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent_donhang);
                        break;
                    case R.id.menu_bottom_quanly:
                        item.setChecked(true);
                        break;
                }

                return false;
            }
        });
    }


    private void get_tongtien() {
        int tongtien = 0;
        for (int i = 0; i < arrayList_daxuly.size(); i++ ) {
            int tien = Integer.parseInt(arrayList_daxuly.get(i).getTongtien());
            tongtien = tongtien + tien;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txt_tongsotien.setText("Tổng số tiền hàng đã xử lý: " + decimalFormat.format(tongtien) + " vnd");
    }

    private void get_sotk() {
        FirebaseDatabase.getInstance().getReference().child("user").child("all")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()) {
                            arrayList_tk.add(snapshot.getValue(model_account_sigin.class));
                            txt_sotk.setText("Số lượng tài khoản dùng app: " + arrayList_dangxuly.size());
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

                    }
                });
    }

    private void get_sohang() {
        FirebaseDatabase.getInstance().getReference().child("Hanghoa").child("all")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()) {
                            arrayList_sp.add(snapshot.getValue(model_sanpham.class));
                            txt_sohang.setText("Tổng số mặt hàng trong shop: " + arrayList_sp.size());
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

                    }
                });
    }

    private void get_sodondaxuly() {
        FirebaseDatabase.getInstance().getReference().child("dathang_daxuly").child("all")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()) {
                            arrayList_daxuly.add(snapshot.getValue(model_dathang.class));
                            txt_sodondaxuly.setText("Số đơn đã xử lý: " + arrayList_daxuly.size());
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    get_tongtien();
                                }
                            },3000);

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

                    }
                });
    }

    private void get_sodondangxuly() {
        FirebaseDatabase.getInstance().getReference().child("dathang").child("all")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()) {
                            arrayList_dangxuly.add(snapshot.getValue(model_dathang.class));
                            txt_sodondangxuly.setText("Số đơn đang xử lý: " + arrayList_dangxuly.size());
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

                    }
                });
    }

    private void initview() {
        txt_sodondangxuly = findViewById(R.id.txt_sodondangxuly);
        txt_sodondaxuly = findViewById(R.id.txt_sodondaxuly);
        txt_sohang = findViewById(R.id.txt_sohang);
        txt_sotk = findViewById(R.id.txt_sotk);
        bottom_menu = findViewById(R.id.bottom_menu);
        txt_tongsotien = findViewById(R.id.txt_tongsotien);

        arrayList_dangxuly = new ArrayList<>();
        arrayList_daxuly = new ArrayList<>();
        arrayList_sp = new ArrayList<>();
        arrayList_tk = new ArrayList<>();
    }
}