package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.tgdt.Adapter.Adapter_dathang_quanly;
import com.example.tgdt.Adapter.Adapter_giohang;
import com.example.tgdt.Adapter.Adapter_menu_navi_shop;
import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Console.Link_anh_menu_shop;
import com.example.tgdt.Model.model_dathang;
import com.example.tgdt.Model.model_menu_navi_shop;
import com.example.tgdt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Quanly_donhang extends AppCompatActivity {

    private RecyclerView list_sanpham;
    private Adapter_dathang_quanly adapter_dathang_quanly;
    private ArrayList<model_dathang> arrayList_dathang;

    private ImageButton imgbt_menu;
    private DrawerLayout drawerLayout;
    private ListView list_menu;

    private ArrayList<model_menu_navi_shop> arrayList_shop;
    private Adapter_menu_navi_shop adapter_menu_navi_shop;

    private BottomNavigationView top_menu;

    private BottomNavigationView bottom_menu;

    private String sdt;
    private String loaitk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_donhang);

        init_view();
        get_donchuaxuly();
        click_bottom_menu();
        get_data_intent();
        set_click_menu();
        click_menu();
        set_menu();
        click_top_menu();
    }

    private void click_top_menu() {
        top_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.chuaxuly:
                        item.setChecked(true);
                        get_donchuaxuly();
                        break;
                    case R.id.daxuly:
                        item.setChecked(true);
                        get_dondaxuly();
                }
                return false;
            }
        });
    }

    private void set_click_menu() {
        list_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        break;
                    case 1:
                        Intent intent1 = new Intent(Quanly_donhang.this, Home.class);
                        intent1.putExtra(Key_Account.Phone, sdt);
                        intent1.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(Quanly_donhang.this, Sanpham.class);
                        intent2.putExtra(Key_Account.Phone, sdt);
                        intent2.putExtra(Key_Account.Loaitk, loaitk);
                        intent2.putExtra(Key_menu_sp.Key_sanpham, "dienthoai");
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(Quanly_donhang.this, Sanpham.class);
                        intent3.putExtra(Key_Account.Phone, sdt);
                        intent3.putExtra(Key_Account.Loaitk, loaitk);
                        intent3.putExtra(Key_menu_sp.Key_sanpham, "laptop");
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(Quanly_donhang.this, Sanpham.class);
                        intent4.putExtra(Key_Account.Phone, sdt);
                        intent4.putExtra(Key_Account.Loaitk, loaitk);
                        intent4.putExtra(Key_menu_sp.Key_sanpham, "dongho");
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(Quanly_donhang.this, Sanpham.class);
                        intent5.putExtra(Key_Account.Phone, sdt);
                        intent5.putExtra(Key_Account.Loaitk, loaitk);
                        intent5.putExtra(Key_menu_sp.Key_sanpham, "phukien");
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(Quanly_donhang.this, Quanly.class);
                        intent6.putExtra(Key_Account.Phone, sdt);
                        intent6.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent6);
                        break;
                }

            }
        });
    }

    private void click_menu() {
        imgbt_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void set_menu() {
        arrayList_shop.add(new model_menu_navi_shop(Link_anh_menu_shop.Link_anh_taikhoan,
                "Tài khoản"));
        arrayList_shop.add(new model_menu_navi_shop(Link_anh_menu_shop.Link_anh_trangchu,
                "Trang chủ"));
        arrayList_shop.add(new model_menu_navi_shop(Link_anh_menu_shop.Link_anh_dienthoai,
                "Điện thoại"));
        arrayList_shop.add(new model_menu_navi_shop(Link_anh_menu_shop.Link_anh_laptop,
                "Laptop"));
        arrayList_shop.add(new model_menu_navi_shop(Link_anh_menu_shop.Link_anh_dongho,
                "Đồng hồ"));
        arrayList_shop.add(new model_menu_navi_shop(Link_anh_menu_shop.Link_anh_phukien,
                "Phụ kiện"));

        if (loaitk.equals("admin")) {
            arrayList_shop.add(new model_menu_navi_shop(Link_anh_menu_shop.Link_anh_quanly,
                    "Quản lý "));
        }

        adapter_menu_navi_shop.notifyDataSetChanged();

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
                        Intent intent_themhang = new Intent(Quanly_donhang.this, Quanly.class);
                        intent_themhang.putExtra(Key_Account.Phone, sdt);
                        intent_themhang.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent_themhang);
                        break;
                    case R.id.menu_bottom_themhang:
                        item.setChecked(true);
                        Intent intent_donhang = new Intent(Quanly_donhang.this, Quanly_themhang.class);
                        intent_donhang.putExtra(Key_Account.Phone, sdt);
                        intent_donhang.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent_donhang);
                        break;
                    case R.id.menu_bottom_donhang:
                        item.setChecked(true);
                        break;
                    case R.id.menu_bottom_quanly:
                        item.setChecked(true);
                        Intent intent_quanly = new Intent(Quanly_donhang.this, Quanly_shop.class);
                        intent_quanly.putExtra(Key_Account.Phone, sdt);
                        intent_quanly.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent_quanly);
                        break;
                }

                return false;
            }
        });
    }

    private void get_dondaxuly() {
        arrayList_dathang.clear();
        FirebaseDatabase.getInstance().getReference().child("dathang_daxuly").child("all")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()) {
                            arrayList_dathang.add(snapshot.getValue(model_dathang.class));
                            adapter_dathang_quanly.notifyDataSetChanged();
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

    private void get_donchuaxuly() {
        arrayList_dathang.clear();
        FirebaseDatabase.getInstance().getReference().child("dathang").child("all")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()) {
                            arrayList_dathang.add(snapshot.getValue(model_dathang.class));
                            adapter_dathang_quanly.notifyDataSetChanged();
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

    private void init_view() {
        list_sanpham = findViewById(R.id.list_sanpham);
        bottom_menu = findViewById(R.id.bottom_menu);
        imgbt_menu = findViewById(R.id.imgbt_menu);
        drawerLayout = findViewById(R.id.layout_full);
        list_menu = findViewById(R.id.list_menu);
        top_menu = findViewById(R.id.top_menu);

        arrayList_dathang = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        list_sanpham.setLayoutManager(linearLayoutManager);
        adapter_dathang_quanly = new Adapter_dathang_quanly(getApplicationContext(), arrayList_dathang);
        list_sanpham.setAdapter(adapter_dathang_quanly);
        arrayList_shop = new ArrayList<>();
        adapter_menu_navi_shop = new Adapter_menu_navi_shop(getApplicationContext(), arrayList_shop);
        list_menu.setAdapter(adapter_menu_navi_shop);
    }
}