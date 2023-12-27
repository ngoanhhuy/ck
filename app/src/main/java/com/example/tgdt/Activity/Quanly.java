package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tgdt.Adapter.Adapter_dathang_quanly;
import com.example.tgdt.Adapter.Adapter_menu_navi_shop;
import com.example.tgdt.Adapter.Adapter_sanpham;
import com.example.tgdt.Adapter.Adapter_sanpham_quanly;
import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Console.Link_anh_menu_shop;
import com.example.tgdt.Console.Link_viewfliper;
import com.example.tgdt.Model.model_menu_navi_shop;
import com.example.tgdt.Model.model_sanpham;
import com.example.tgdt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Quanly extends AppCompatActivity {

    private ImageButton imgbt_menu;
    private DrawerLayout drawerLayout;
    private ListView list_menu;
    private BottomNavigationView menu_sp_quanly;
    private BottomNavigationView bottom_menu;

    private RecyclerView list_sanpham;
    private Adapter_sanpham_quanly adapter_sanpham;
    private ArrayList<model_sanpham> arrayList_sp;

    private ArrayList<model_menu_navi_shop> arrayList_shop;
    private Adapter_menu_navi_shop adapter_menu_navi_shop;

    private String sdt;
    private String loaitk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly);

        init_view();
        get_data_user();
        click_menu();
        get_data_user();
        set_menu();
        set_click_menu();
        click_menu_sp();
        set_data_sp();
        click_bottom_menu();
    }

    private void click_bottom_menu() {
        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_bottom_sanpham:
                        item.setChecked(true);
                        break;
                    case R.id.menu_bottom_themhang:
                        item.setChecked(true);
                        Intent intent_themhang = new Intent(Quanly.this, Quanly_themhang.class);
                        intent_themhang.putExtra(Key_Account.Phone, sdt);
                        intent_themhang.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent_themhang);
                        break;
                    case R.id.menu_bottom_donhang:
                        item.setChecked(true);
                        Intent intent_donhang = new Intent(Quanly.this, Quanly_donhang.class);
                        intent_donhang.putExtra(Key_Account.Phone, sdt);
                        intent_donhang.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent_donhang);
                        break;
                    case R.id.menu_bottom_quanly:
                        item.setChecked(true);
                        Intent intent_quanly = new Intent(Quanly.this, Quanly_shop.class);
                        intent_quanly.putExtra(Key_Account.Phone, sdt);
                        intent_quanly.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent_quanly);
                        break;
                }

                return false;
            }
        });
    }

    private void set_data_sp() {
        model_sanpham model_sanpham = new model_sanpham();

        FirebaseDatabase.getInstance().getReference().child("Hanghoa").child("Điện thoại")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        if (snapshot.exists()) {
                            arrayList_sp.add(snapshot.getValue(model_sanpham.class));
                            adapter_sanpham.notifyDataSetChanged();
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


        adapter_menu_navi_shop.notifyDataSetChanged();
    }

    private void click_menu_sp() {
        menu_sp_quanly.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                model_sanpham model_sanpham = new model_sanpham();
                switch (item.getItemId()) {
                    case R.id.menu_sp_dienthoai:
                        item.setChecked(true);
                        arrayList_sp.clear();

                        FirebaseDatabase.getInstance().getReference().child("Hanghoa").child("Điện thoại")
                                .addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                        if (snapshot.exists()) {
                                            arrayList_sp.add(snapshot.getValue(model_sanpham.class));
                                            adapter_sanpham.notifyDataSetChanged();
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

                        adapter_sanpham.notifyDataSetChanged();
                        break;
                    case R.id.menu_sp_laptop:
                        item.setChecked(true);
                        arrayList_sp.clear();

                        FirebaseDatabase.getInstance().getReference().child("Hanghoa").child("Laptop")
                                .addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                        if (snapshot.exists()) {
                                            arrayList_sp.add(snapshot.getValue(model_sanpham.class));
                                            adapter_sanpham.notifyDataSetChanged();
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

                        adapter_sanpham.notifyDataSetChanged();
                        break;
                    case R.id.menu_sp_dongho:
                        item.setChecked(true);
                        arrayList_sp.clear();

                        FirebaseDatabase.getInstance().getReference().child("Hanghoa").child("Đồng hồ")
                                .addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                        if (snapshot.exists()) {
                                            arrayList_sp.add(snapshot.getValue(model_sanpham.class));
                                            adapter_sanpham.notifyDataSetChanged();
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

                        adapter_sanpham.notifyDataSetChanged();
                        break;
                    case R.id.menu_sp_phukien:
                        item.setChecked(true);
                        arrayList_sp.clear();

                        FirebaseDatabase.getInstance().getReference().child("Hanghoa").child("Phụ kiện")
                                .addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                        if (snapshot.exists()) {
                                            arrayList_sp.add(snapshot.getValue(model_sanpham.class));
                                            adapter_sanpham.notifyDataSetChanged();
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

                        adapter_sanpham.notifyDataSetChanged();
                        break;
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
                        Intent intent1 = new Intent(Quanly.this, Home.class);
                        intent1.putExtra(Key_Account.Phone, sdt);
                        intent1.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(Quanly.this, Sanpham.class);
                        intent2.putExtra(Key_Account.Phone, sdt);
                        intent2.putExtra(Key_Account.Loaitk, loaitk);
                        intent2.putExtra(Key_menu_sp.Key_sanpham, "dienthoai");
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(Quanly.this, Sanpham.class);
                        intent3.putExtra(Key_Account.Phone, sdt);
                        intent3.putExtra(Key_Account.Loaitk, loaitk);
                        intent3.putExtra(Key_menu_sp.Key_sanpham, "laptop");
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(Quanly.this, Sanpham.class);
                        intent4.putExtra(Key_Account.Phone, sdt);
                        intent4.putExtra(Key_Account.Loaitk, loaitk);
                        intent4.putExtra(Key_menu_sp.Key_sanpham, "dongho");
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(Quanly.this, Sanpham.class);
                        intent5.putExtra(Key_Account.Phone, sdt);
                        intent5.putExtra(Key_Account.Loaitk, loaitk);
                        intent5.putExtra(Key_menu_sp.Key_sanpham, "phukien");
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(Quanly.this, Quanly.class);
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

    private void get_data_user() {

        if (getIntent() != null) {
            sdt = getIntent().getStringExtra(Key_Account.Phone);
            loaitk = getIntent().getStringExtra(Key_Account.Loaitk);
        }

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

    private void init_view() {

        imgbt_menu = findViewById(R.id.imgbt_menu);
        drawerLayout = findViewById(R.id.layout_full);
        list_menu = findViewById(R.id.list_menu);
        list_sanpham = findViewById(R.id.list_sanpham);
        menu_sp_quanly = findViewById(R.id.menu_sp_quanly);
        bottom_menu = findViewById(R.id.bottom_menu);

        arrayList_shop = new ArrayList<>();
        adapter_menu_navi_shop = new Adapter_menu_navi_shop(getApplicationContext(), arrayList_shop);
        list_menu.setAdapter(adapter_menu_navi_shop);

        arrayList_sp = new ArrayList<>();
        list_sanpham.setLayoutManager(new GridLayoutManager(this, 2));
        adapter_sanpham = new Adapter_sanpham_quanly(getApplicationContext(), arrayList_sp, sdt);
        list_sanpham.setAdapter(adapter_sanpham);

    }
}