package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tgdt.Adapter.Adapter_giohang;
import com.example.tgdt.Adapter.Adapter_menu_navi_shop;
import com.example.tgdt.Adapter.Adapter_sanpham;
import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Console.Link_anh_menu_shop;
import com.example.tgdt.Model.model_account_sigin;
import com.example.tgdt.Model.model_giohang;
import com.example.tgdt.Model.model_menu_navi_shop;
import com.example.tgdt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Giohang extends AppCompatActivity {

    private RecyclerView recy_giohang;
    private Adapter_giohang adapter_giohang;
    private ArrayList<model_giohang> arrayList_giohang;
    private BottomNavigationView bottom_menu;
    private DrawerLayout drawerLayout;

    private ImageView img_avata_home;
    private ListView listView_menu_navi;

    private ArrayList<model_menu_navi_shop> arrayList_menu_navi;
    private Adapter_menu_navi_shop adapter_menu_navi_shop;

    private ArrayList<model_account_sigin> array_account_sigins;

    private String sdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);

        init_view();
        getData_intent();
        click_bottom_menu();
        set_click_menu();
        click_avata();
    }

    private void set_menu() {
        arrayList_menu_navi.add(new model_menu_navi_shop(Link_anh_menu_shop.Link_anh_taikhoan,
                "Tài khoản"));
        arrayList_menu_navi.add(new model_menu_navi_shop(Link_anh_menu_shop.Link_anh_trangchu,
                "Trang chủ"));
        arrayList_menu_navi.add(new model_menu_navi_shop(Link_anh_menu_shop.Link_anh_dienthoai,
                "Điện thoại"));
        arrayList_menu_navi.add(new model_menu_navi_shop(Link_anh_menu_shop.Link_anh_laptop,
                "Laptop"));
        arrayList_menu_navi.add(new model_menu_navi_shop(Link_anh_menu_shop.Link_anh_dongho,
                "Đồng hồ"));
        arrayList_menu_navi.add(new model_menu_navi_shop(Link_anh_menu_shop.Link_anh_phukien,
                "Phụ kiện"));

        if (array_account_sigins.get(0).getLoaitk().equals("admin")) {
            arrayList_menu_navi.add(new model_menu_navi_shop(Link_anh_menu_shop.Link_anh_quanly,
                    "Quản lý "));
        }
        adapter_menu_navi_shop.notifyDataSetChanged();
    }

    private void set_click_menu() {
        listView_menu_navi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Intent intent = new Intent(Giohang.this, Trangcanhan.class);
                        intent.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(Giohang.this, Home.class);
                        intent1.putExtra(Key_Account.Phone, sdt);
                        intent1.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(Giohang.this, Sanpham.class);
                        intent2.putExtra(Key_Account.Phone, sdt);
                        intent2.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent2.putExtra(Key_menu_sp.Key_sanpham, "Điện thoại");
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(Giohang.this, Sanpham.class);
                        intent3.putExtra(Key_Account.Phone, sdt);
                        intent3.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent3.putExtra(Key_menu_sp.Key_sanpham, "Laptop");
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(Giohang.this, Sanpham.class);
                        intent4.putExtra(Key_Account.Phone, sdt);
                        intent4.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent4.putExtra(Key_menu_sp.Key_sanpham, "Đồng hồ");
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(Giohang.this, Sanpham.class);
                        intent5.putExtra(Key_Account.Phone, sdt);
                        intent5.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent5.putExtra(Key_menu_sp.Key_sanpham, "Phụ kiện");
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(Giohang.this, Quanly.class);
                        intent6.putExtra(Key_Account.Phone, sdt);
                        intent6.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        startActivity(intent6);
                        break;
                }

            }
        });
    }

    private void click_avata() {
        img_avata_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void click_bottom_menu() {
        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_bottom_home:
                        item.setChecked(true);
                        item.setChecked(true);
                        Intent intent = new Intent(Giohang.this, Home.class);
                        intent.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent);
                        break;
                    case R.id.menu_bottom_gift:
                        item.setChecked(true);
                        Intent intent1 = new Intent(Giohang.this, SanphamNews.class);
                        intent1.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent1);
                        break;
                    case R.id.menu_bottom_local:
                        item.setChecked(true);
                        Intent intent2 = new Intent(Giohang.this, Vitri.class);
                        intent2.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent2);
                        break;
                    case R.id.menu_bottom_taikhoan:
                        item.setChecked(true);
                        Intent intent3 = new Intent(Giohang.this, Trangcanhan.class);
                        intent3.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
    }

    private void getData_intent() {
        if (getIntent() != null) {
            sdt = getIntent().getStringExtra(Key_Account.Phone);
            get_giohang();
            arrayList_giohang = new ArrayList<>();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            recy_giohang.setLayoutManager(linearLayoutManager);
            adapter_giohang = new Adapter_giohang(getApplicationContext(), arrayList_giohang,sdt);
            recy_giohang.setAdapter(adapter_giohang);
            get_data_user();
        }
    }

    private void get_data_user() {

        FirebaseDatabase.getInstance().getReference().child("user").child(sdt)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        if (snapshot.exists()) {

                            array_account_sigins.add(snapshot.getValue(model_account_sigin.class));
                            set_menu();
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
                        Toast.makeText(getApplicationContext(), "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void get_giohang() {
        FirebaseDatabase.getInstance().getReference().child("giohang").child(sdt+"")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        if (snapshot.exists()) {
                            arrayList_giohang.add(snapshot.getValue(model_giohang.class));
                            adapter_giohang.notifyDataSetChanged();
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
        recy_giohang = findViewById(R.id.recy_giohang);
        bottom_menu = findViewById(R.id.bottom_menu);
        drawerLayout = findViewById(R.id.layout_full);
        listView_menu_navi = findViewById(R.id.list_menu);
        array_account_sigins = new ArrayList<>();
        img_avata_home = findViewById(R.id.img_avata_home);

        arrayList_menu_navi = new ArrayList<>();
        adapter_menu_navi_shop = new Adapter_menu_navi_shop(getApplicationContext(), arrayList_menu_navi);
        listView_menu_navi.setAdapter(adapter_menu_navi_shop);
    }
}