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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.tgdt.Adapter.Adapter_menu_navi_shop;
import com.example.tgdt.Adapter.Adapter_sanpham;
import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Console.Link_anh_menu_shop;
import com.example.tgdt.Console.Link_viewfliper;
import com.example.tgdt.Model.model_giohang;
import com.example.tgdt.Model.model_menu_navi_shop;
import com.example.tgdt.Model.model_sanpham;
import com.example.tgdt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;

public class Sanpham extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private DrawerLayout drawerLayout;
    private ListView list_menu;
    private ImageButton imgbt_menu;
    private String sdt;
    private String loaitk;
    private String loaisp;
    private TextView txt_number_sp;
    private ImageView txt_giohang;
    private BottomNavigationView bottom_menu;


    private RecyclerView list_sanpham;
    private Adapter_sanpham adapter_sanpham;
    private ArrayList<model_sanpham> arrayList_sp;

    private ArrayList<model_menu_navi_shop> arrayList_shop;
    private Adapter_menu_navi_shop adapter_menu_navi_shop;

    private ArrayList<model_giohang> arrayList_giohang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham);

        init_view();
        click_menu();
        set_click_menu();
        get_data_loai_sp();
        click_giohang();
        click_bottom_menu();
    }

    private void click_bottom_menu() {
        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_sp_dienthoai:
                        item.setChecked(true);
                        loaisp = "Điện thoại";
                        arrayList_sp.clear();
                        set_data_sp();
                        break;
                    case R.id.menu_sp_laptop:
                        item.setChecked(true);
                        loaisp = "Laptop";
                        arrayList_sp.clear();
                        set_data_sp();
                        break;
                    case R.id.menu_sp_dongho:
                        item.setChecked(true);
                        loaisp = "Đồng hồ";
                        arrayList_sp.clear();
                        set_data_sp();;
                        break;
                    case R.id.menu_sp_phukien:
                        item.setChecked(true);
                        loaisp = "Phụ kiện";
                        arrayList_sp.clear();
                        set_data_sp();
                        break;
                }

                return false;
            }
        });
    }

    private void click_giohang() {
        txt_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sanpham.this, Giohang.class);
                intent.putExtra(Key_Account.Phone, sdt);
                startActivity(intent);
            }
        });

    }

    private void get_giohang() {
        arrayList_giohang.clear();
        FirebaseDatabase.getInstance().getReference().child("giohang").child(sdt+"")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()) {
                            arrayList_giohang.add(snapshot.getValue(model_giohang.class));
                            txt_number_sp.setText(arrayList_giohang.size()+"");
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
    private void click_xemhang() {
        list_sanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void get_data_loai_sp() {
        if (getIntent() != null) {
            sdt = getIntent().getStringExtra(Key_Account.Phone);
            loaitk = getIntent().getStringExtra(Key_Account.Loaitk);
            loaisp = getIntent().getStringExtra(Key_menu_sp.Key_sanpham);
            set_data_sp();
            set_menu();
            set_adapter();
            get_giohang();
        }
    }

    private void set_adapter() {
        arrayList_sp = new ArrayList<>();
        list_sanpham.setLayoutManager(new GridLayoutManager(this, 2));
        adapter_sanpham = new Adapter_sanpham(getApplicationContext(), arrayList_sp, sdt);
        list_sanpham.setAdapter(adapter_sanpham);
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

    private void set_click_menu() {
        list_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Intent intent = new Intent(Sanpham.this, Trangcanhan.class);
                        intent.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(Sanpham.this, Home.class);
                        intent1.putExtra(Key_Account.Phone, sdt);
                        intent1.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(Sanpham.this, Sanpham.class);
                        intent2.putExtra(Key_Account.Phone, sdt);
                        intent2.putExtra(Key_Account.Loaitk, loaitk);
                        intent2.putExtra(Key_menu_sp.Key_sanpham, "Điện thoại");
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(Sanpham.this, Sanpham.class);
                        intent3.putExtra(Key_Account.Phone, sdt);
                        intent3.putExtra(Key_Account.Loaitk, loaitk);
                        intent3.putExtra(Key_menu_sp.Key_sanpham, "Laptop");
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(Sanpham.this, Sanpham.class);
                        intent4.putExtra(Key_Account.Phone, sdt);
                        intent4.putExtra(Key_Account.Loaitk, loaitk);
                        intent4.putExtra(Key_menu_sp.Key_sanpham, "Đồng hồ");
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(Sanpham.this, Sanpham.class);
                        intent5.putExtra(Key_Account.Phone, sdt);
                        intent5.putExtra(Key_Account.Loaitk, loaitk);
                        intent5.putExtra(Key_menu_sp.Key_sanpham, "Phụ kiện");
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(Sanpham.this, Quanly.class);
                        intent6.putExtra(Key_Account.Phone, sdt);
                        intent6.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent6);
                        break;
                }

            }
        });
    }

    private void set_data_sp() {
        FirebaseDatabase.getInstance().getReference().child("Hanghoa").
                child(loaisp + "").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists()) {
                    arrayList_sp.add(snapshot.getValue(model_sanpham.class));
                    adapter_sanpham.notifyDataSetChanged();
                    click_xemhang();
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
        viewFlipper = findViewById(R.id.viewfliper);
        drawerLayout = findViewById(R.id.drawerlayout);
        list_menu = findViewById(R.id.list_menu);
        imgbt_menu = findViewById(R.id.imgbt_menu);
        list_sanpham = findViewById(R.id.list_sanpham);
        txt_number_sp = findViewById(R.id.txt_number_sp);
        txt_giohang = findViewById(R.id.txt_giohang);
        bottom_menu = findViewById(R.id.bottom_menu);

        arrayList_shop = new ArrayList<>();
        adapter_menu_navi_shop = new Adapter_menu_navi_shop(getApplicationContext(), arrayList_shop);
        list_menu.setAdapter(adapter_menu_navi_shop);
        arrayList_giohang = new ArrayList<>();

    }
}