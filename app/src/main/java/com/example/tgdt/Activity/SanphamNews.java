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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tgdt.Adapter.Adapter_menu_navi_shop;
import com.example.tgdt.Adapter.Adapter_uudai;
import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Console.Link_anh_menu_shop;
import com.example.tgdt.Model.model_account_sigin;
import com.example.tgdt.Model.model_menu_navi_shop;
import com.example.tgdt.Model.model_sanpham;
import com.example.tgdt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class SanphamNews extends AppCompatActivity {

    private RecyclerView list_uudai;
    private ImageView img_lienhe;
    private ImageView img_tichdiem;
    private ImageView img_avata_home;
    private ListView listView_menu_navi;
    private DrawerLayout drawerLayout;
    private BottomNavigationView bottom_menu;
    private ImageView imgbt_news1;

    private ArrayList<model_menu_navi_shop> arrayList_menu_navi;
    private Adapter_menu_navi_shop adapter_menu_navi_shop;

    private ArrayList<String> arrayList;

    private Adapter_uudai adapter_uudai;
    private ArrayList<model_sanpham> arrayList_uudai;

    private String sdt;

    private ArrayList<model_account_sigin> array_account_sigins;

    private static final int MY_PERMISSION_REQUEST_CODE_CALL_PHONE = 555;

    private static final int MY_PERMISSION_REQUEST_CODE_SEND_SMS = 1;

    private static final String LOG_TAG = "AndroidExample";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham_news);

        init_view();
        get_sdt();
        click_lienhe();
        click_avata();
        set_click_menu();
        set_click_tichdiem();
        get_data_news();
        click_bottom_menu();
        click_imgbt_news1();
    }

    private void click_imgbt_news1() {
        imgbt_news1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SanphamNews.this, SanphamNews.class);
                intent.putExtra(Key_Account.Phone, sdt);
                startActivity(intent);
            }
        });
    }

    private void click_bottom_menu() {
        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_bottom_home:
                        Intent intent1 = new Intent(SanphamNews.this, Home.class);
                        intent1.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent1);
                        break;
                    case R.id.menu_bottom_gift:
                        item.setChecked(true);
                        break;
                    case R.id.menu_bottom_local:
                        Intent intent2 = new Intent(SanphamNews.this, Vitri.class);
                        intent2.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent2);
                        break;
                    case R.id.menu_bottom_taikhoan:
                        item.setChecked(true);
                        Intent intent3 = new Intent(SanphamNews.this, Trangcanhan.class);
                        intent3.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
    }

    private void get_data_news() {
        FirebaseDatabase.getInstance().getReference().child("Hanghoa").child("all")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()) {
                            arrayList_uudai.add(snapshot.getValue(model_sanpham.class));
                            adapter_uudai.notifyDataSetChanged();
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

    private void set_click_tichdiem() {
        img_tichdiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SanphamNews.this, Tichdiem.class);
                intent.putExtra(Key_Account.Phone, sdt);
                intent.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                startActivity(intent);
            }
        });
    }

    private void set_click_menu() {
        listView_menu_navi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Intent intent = new Intent(SanphamNews.this, Sanpham.class);
                        intent.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(SanphamNews.this, Home.class);
                        intent1.putExtra(Key_Account.Phone, sdt);
                        intent1.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(SanphamNews.this, Sanpham.class);
                        intent2.putExtra(Key_Account.Phone, sdt);
                        intent2.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent2.putExtra(Key_menu_sp.Key_sanpham, "Điện thoại");
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(SanphamNews.this, Sanpham.class);
                        intent3.putExtra(Key_Account.Phone, sdt);
                        intent3.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent3.putExtra(Key_menu_sp.Key_sanpham, "Laptop");
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(SanphamNews.this, Sanpham.class);
                        intent4.putExtra(Key_Account.Phone, sdt);
                        intent4.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent4.putExtra(Key_menu_sp.Key_sanpham, "Đồng hồ");
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(SanphamNews.this, Sanpham.class);
                        intent5.putExtra(Key_Account.Phone, sdt);
                        intent5.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent5.putExtra(Key_menu_sp.Key_sanpham, "Phụ kiện");
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(SanphamNews.this, Quanly.class);
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

    private void click_lienhe() {
        img_lienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SanphamNews.this, Lienhe.class);
                intent.putExtra(Key_Account.Phone, sdt);
                startActivity(intent);
            }
        });
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

    private void get_sdt() {
        if (getIntent() != null) {
            sdt = getIntent().getStringExtra(Key_Account.Phone);
            get_data_user();
        }
    }

    private void init_view() {
        list_uudai = findViewById(R.id.list_uudai);
        img_lienhe = findViewById(R.id.imgbt_call);
        img_avata_home = findViewById(R.id.img_avata_home);
        listView_menu_navi = findViewById(R.id.list_menu);
        drawerLayout = findViewById(R.id.layout_full);
        img_tichdiem = findViewById(R.id.imgbt_tichdiem);
        bottom_menu = findViewById(R.id.bottom_menu);
        imgbt_news1 = findViewById(R.id.imgbt_news1);

        arrayList_menu_navi = new ArrayList<>();
        adapter_menu_navi_shop = new Adapter_menu_navi_shop(getApplicationContext(), arrayList_menu_navi);
        listView_menu_navi.setAdapter(adapter_menu_navi_shop);

        arrayList = new ArrayList<>();
        arrayList_uudai = new ArrayList<>();
        adapter_uudai = new Adapter_uudai(getApplicationContext(), arrayList_uudai, sdt);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        list_uudai.setLayoutManager(linearLayoutManager);
        list_uudai.setHasFixedSize(true);
        list_uudai.setAdapter(adapter_uudai);
        array_account_sigins = new ArrayList<>();
    }
}