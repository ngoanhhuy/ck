package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Hotro extends AppCompatActivity {
    
    private Button bt_hotro;
    private BottomNavigationView bottom_menu;
    private EditText edt_noidung;

    private String sdt;

    private String noidung_hotro;

    private static final int MY_PERMISSION_REQUEST_CODE_SEND_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotro);
        
        init_view();
        get_data_intent();
        click_bottom_menu();
        click_lienhe();
        click_bottom_menu();
    }


    private void click_lienhe() {
        bt_hotro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_noidung.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Nhập nội dung", Toast.LENGTH_SHORT).show();
                }else {
                    noidung_hotro = edt_noidung.getText().toString();
                    hotro();
                }
            }
        });

    }

    private void hotro() {
        if (android.os.Build.VERSION.SDK_INT >=  android.os.Build.VERSION_CODES.M) {

            int sendSmsPermisson = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.SEND_SMS);

            if (sendSmsPermisson != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSION_REQUEST_CODE_SEND_SMS
                );
                return;
            }
        }
        this.gui_hotro();
    }

    private void gui_hotro() {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("0336028630",
                    null,
                    noidung_hotro,
                    null,
                    null);

            Toast.makeText(getApplicationContext(),"Gửi thành công!",
                    Toast.LENGTH_LONG).show();
            edt_noidung.setText("");
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),"Không gửi được... " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    private void click_bottom_menu() {
        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_bottom_home:
                        item.setChecked(true);
                        Intent intent = new Intent(Hotro.this, Home.class);
                        intent.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent);
                        break;
                    case R.id.menu_bottom_gift:
                        item.setChecked(true);
                        Intent intent1 = new Intent(Hotro.this, SanphamNews.class);
                        intent1.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent1);
                        break;
                    case R.id.menu_bottom_local:
                        item.setChecked(true);
                        Intent intent2 = new Intent(Hotro.this, Vitri.class);
                        intent2.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent2);
                        break;
                    case R.id.menu_bottom_taikhoan:
                        item.setChecked(true);
                        Intent intent3 = new Intent(Hotro.this, Trangcanhan.class);
                        intent3.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
    }

    private void get_data_intent() {
        if (getIntent() != null) {
            sdt = getIntent().getStringExtra(Key_Account.Phone);
        }
    }

    private void init_view() {
        bt_hotro = findViewById(R.id.bt_hotro);
        bottom_menu = findViewById(R.id.bottom_menu);
        edt_noidung = findViewById(R.id.edt_noidung);
    }
}