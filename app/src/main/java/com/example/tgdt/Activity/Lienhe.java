package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Lienhe extends AppCompatActivity {

    private Button bt_lienhe;
    private BottomNavigationView bottom_menu;
    private String sdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lienhe);

        init_view();
        set_click_lienhe();
        click_bottom_menu();
        get_data_intent();
    }
    private void get_data_intent() {
        if (getIntent() != null) {
            sdt = getIntent().getStringExtra(Key_Account.Phone);
        }
    }

    private void click_bottom_menu() {
        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_bottom_home:
                        item.setChecked(true);
                        Intent intent = new Intent(Lienhe.this, Home.class);
                        intent.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent);
                        break;
                    case R.id.menu_bottom_gift:
                        item.setChecked(true);
                        Intent intent1 = new Intent(Lienhe.this, SanphamNews.class);
                        intent1.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent1);
                        break;
                    case R.id.menu_bottom_local:
                        item.setChecked(true);
                        Intent intent2 = new Intent(Lienhe.this, Vitri.class);
                        intent2.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent2);
                        break;
                    case R.id.menu_bottom_taikhoan:
                        item.setChecked(true);
                        Intent intent3 = new Intent(Lienhe.this, Trangcanhan.class);
                        intent3.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
    }

    private void set_click_lienhe() {
        bt_lienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Capquyen();
            }
        });
    }
    private void Capquyen() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) { // 23

            // Kiểm tra đã cấp quyền chưa
            int sendSmsPermisson = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE);

            if (sendSmsPermisson != PackageManager.PERMISSION_GRANTED) {
                // Kiểm tra nếu chưa cấp quyền thì yêu cầu cấp
                this.requestPermissions(
                        new String[]{Manifest.permission.CALL_PHONE},
                        555
                );
                return;
            }
        }
        this.callNow();
    }

    @SuppressLint("MissingPermission")
    private void callNow() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + "0336028630"));
        try {
            this.startActivity(callIntent);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),"Có lỗi ghi liên " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    private void init_view() {
        bt_lienhe = findViewById(R.id.bt_lienhe);
        bottom_menu = findViewById(R.id.bottom_menu);
    }
}