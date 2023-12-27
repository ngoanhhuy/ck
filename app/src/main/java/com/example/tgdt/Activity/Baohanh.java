package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Baohanh extends AppCompatActivity {

    private BottomNavigationView bottom_menu;

    private String loaitk;
    private String sdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baohanh);

        initview();
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
                    case R.id.menu_bottom_home:
                        Intent intent = new Intent(Baohanh.this, Home.class);
                        intent.putExtra(Key_Account.Phone, sdt+"");
                        startActivity(intent);
                        break;
                    case R.id.menu_bottom_themhang:
                        item.setChecked(true);

                        break;
                    case R.id.menu_bottom_donhang:
                        item.setChecked(true);

                        break;
                    case R.id.menu_bottom_quanly:
                        item.setChecked(true);
                        break;
                }

                return false;
            }
        });
    }

    private void initview() {
        bottom_menu = findViewById(R.id.bottom_menu);
    }

}