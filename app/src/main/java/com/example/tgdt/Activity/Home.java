package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.tgdt.Adapter.Adapter_menu_navi_shop;
import com.example.tgdt.Adapter.Adapter_uudai;
import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Console.Link_anh_menu_shop;
import com.example.tgdt.Console.Link_viewfliper;
import com.example.tgdt.Model.model_account_sigin;
import com.example.tgdt.Model.model_menu_navi_shop;
import com.example.tgdt.Model.model_sanpham;
import com.example.tgdt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

public class Home extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private RecyclerView list_uudai;
    private ImageView img_lienhe;
    private ImageView img_tichdiem;
    private ImageView imgbt_hotro;
    private ImageView imgbt_baohanh;
    private ImageView img_avata_home;
    private ListView listView_menu_navi;
    private DrawerLayout drawerLayout;
    private ImageView imgbt_news1, imgbt_news2;
    private TextView txt_tatca;
    private BottomNavigationView bottom_menu;

    private TextView txt_user_home;

    private ArrayList<model_menu_navi_shop> arrayList_menu_navi;
    private Adapter_menu_navi_shop adapter_menu_navi_shop;

    private ImageButton imgbt_shop;

    private ArrayList<String> arrayList;

    private Adapter_uudai adapter_uudai;
    private ArrayList<model_sanpham> arrayList_news;

    private String sdt;

    private ArrayList<model_account_sigin> array_account_sigins;

    private static final int MY_PERMISSION_REQUEST_CODE_CALL_PHONE = 555;

    private static final int MY_PERMISSION_REQUEST_CODE_SEND_SMS = 1;

    private static final String LOG_TAG = "AndroidExample";

    private String noidung_hotro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init_view();
        get_sdt();
        setviewfliper();
        click_shop();
        click_lienhe();
        click_hotro();
        click_baohanh();
        click_avata();
        set_click_menu();
        set_click_tichdiem();
        get_data_new();
        click_imgbt_news1();
        click_imgbt_news2();
        click_tatca();
        click_bottom_menu();
    }
    private void set_infor() {
        if (array_account_sigins.get(0).getAvata().equals("")){
            img_avata_home.setImageResource(R.drawable.ic_baseline_person_24);
        }else {
            Picasso.with(getApplicationContext()).load(array_account_sigins.get(0).getAvata()).into(img_avata_home);
        }

        txt_user_home.setText(array_account_sigins.get(0).getUsername());
    }

    private void click_bottom_menu() {
        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_bottom_home:
                        item.setChecked(true);
                        break;
                    case R.id.menu_bottom_gift:
                        item.setChecked(true);
                        Intent intent1 = new Intent(Home.this, SanphamNews.class);
                        intent1.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent1);
                        break;
                    case R.id.menu_bottom_local:
                        item.setChecked(true);
                        Intent intent2 = new Intent(Home.this, Vitri.class);
                        intent2.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent2);
                        break;
                    case R.id.menu_bottom_taikhoan:
                        item.setChecked(true);
                        Intent intent3 = new Intent(Home.this, Trangcanhan.class);
                        intent3.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
    }

    private void click_tatca() {
        txt_tatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SanphamNews.class);
                intent.putExtra(Key_Account.Phone, sdt);
                startActivity(intent);
            }
        });
    }

    private void click_imgbt_news2() {
        imgbt_news2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SanphamNews.class);
                intent.putExtra(Key_Account.Phone, sdt);
                startActivity(intent);
            }
        });

    }

    private void click_imgbt_news1() {
        imgbt_news1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SanphamNews.class);
                intent.putExtra(Key_Account.Phone, sdt);
                startActivity(intent);
            }
        });
    }

    private void get_data_new() {
        FirebaseDatabase.getInstance().getReference().child("Hanghoa").child("all")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()) {
                            arrayList_news.add(snapshot.getValue(model_sanpham.class));
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
                Intent intent = new Intent(Home.this, Tichdiem.class);
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
                        Intent intent = new Intent(Home.this, Trangcanhan.class);
                        intent.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent);
                        break;
                    case 1:
                        break;
                    case 2:
                        Intent intent2 = new Intent(Home.this, Sanpham.class);
                        intent2.putExtra(Key_Account.Phone, sdt);
                        intent2.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent2.putExtra(Key_menu_sp.Key_sanpham, "Điện thoại");
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(Home.this, Sanpham.class);
                        intent3.putExtra(Key_Account.Phone, sdt);
                        intent3.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent3.putExtra(Key_menu_sp.Key_sanpham, "Laptop");
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(Home.this, Sanpham.class);
                        intent4.putExtra(Key_Account.Phone, sdt);
                        intent4.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent4.putExtra(Key_menu_sp.Key_sanpham, "Đồng hồ");
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(Home.this, Sanpham.class);
                        intent5.putExtra(Key_Account.Phone, sdt);
                        intent5.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent5.putExtra(Key_menu_sp.Key_sanpham, "Phụ kiện");
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(Home.this, Quanly.class);
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

    private void click_baohanh() {
        imgbt_baohanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Baohanh.class);
                startActivity(intent);
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

    private void click_hotro() {
        imgbt_hotro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Hotro.class);
                intent.putExtra(Key_Account.Phone, sdt);
                startActivity(intent);
            }
        });
    }

    private void click_lienhe() {
        img_lienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Lienhe.class);
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
                            set_infor();
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

    private void click_shop() {
        imgbt_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Sanpham.class);
                intent.putExtra(Key_Account.Phone, sdt);
                intent.putExtra(Key_Account.Username, array_account_sigins.get(0).getUsername());
                intent.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                intent.putExtra(Key_menu_sp.Key_sanpham, "Điện thoại");
                startActivity(intent);
            }
        });

    }

    private void init_view() {
        viewFlipper = findViewById(R.id.viewfliper);
        list_uudai = findViewById(R.id.list_uudai);
        imgbt_shop = findViewById(R.id.imgbt_shop);
        img_lienhe = findViewById(R.id.imgbt_call);
        imgbt_hotro = findViewById(R.id.imgbt_hotro);
        imgbt_baohanh = findViewById(R.id.imgbt_baohanh);
        img_avata_home = findViewById(R.id.img_avata_home);
        listView_menu_navi = findViewById(R.id.list_menu);
        drawerLayout = findViewById(R.id.layout_full);
        img_tichdiem = findViewById(R.id.imgbt_tichdiem);
        imgbt_news1 = findViewById(R.id.imgbt_news1);
        imgbt_news2 = findViewById(R.id.imgbt_news2);
        txt_tatca = findViewById(R.id.txt_tatca);
        bottom_menu = findViewById(R.id.bottom_menu);

        txt_user_home = findViewById(R.id.txt_user_home);

        arrayList_menu_navi = new ArrayList<>();
        adapter_menu_navi_shop = new Adapter_menu_navi_shop(getApplicationContext(), arrayList_menu_navi);
        listView_menu_navi.setAdapter(adapter_menu_navi_shop);

        arrayList = new ArrayList<>();
        arrayList_news = new ArrayList<>();
        adapter_uudai = new Adapter_uudai(getApplicationContext(),arrayList_news, sdt);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        list_uudai.setLayoutManager(linearLayoutManager);
        list_uudai.setHasFixedSize(true);
        list_uudai.setAdapter(adapter_uudai);

        array_account_sigins = new ArrayList<>();
    }

    private void setviewfliper() {

        arrayList = new ArrayList<>();
        arrayList.add(Link_viewfliper.link1);
        arrayList.add(Link_viewfliper.link2);
        arrayList.add(Link_viewfliper.link3);
        arrayList.add(Link_viewfliper.link4);

        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        for (int i = 0; i < arrayList.size(); i ++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(getApplicationContext()).load(arrayList.get(i)).into(imageView);
            viewFlipper.addView(imageView);
        }

        Animation in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_in);
        Animation out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
    }
}