package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tgdt.Adapter.Adapter_lichsu;
import com.example.tgdt.Adapter.Adapter_menu_navi_shop;
import com.example.tgdt.Adapter.Adapter_uudai;
import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Console.Link_anh_menu_shop;
import com.example.tgdt.Model.model_account_sigin;
import com.example.tgdt.Model.model_dathang;
import com.example.tgdt.Model.model_menu_navi_shop;
import com.example.tgdt.Model.model_sanpham;
import com.example.tgdt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

public class Trangcanhan extends AppCompatActivity {

    private ImageView imgbt_dangxuly;
    private BottomNavigationView bottom_menu;
    private LinearLayoutCompat doiavata;
    private ImageView img_avata_home;
    private TextView txt_user_home;
    private ImageView imgbt_giohang;
    private ListView listView_menu_navi;
    private DrawerLayout drawerLayout;
    private LinearLayoutCompat doiten;
    private LinearLayoutCompat doimatkhau;
    private ArrayList<model_account_sigin> array_account_sigins;
    private RecyclerView recy;
    private ImageButton imgbt_daxuly;

    private ArrayList<model_menu_navi_shop> arrayList_menu_navi;
    private Adapter_menu_navi_shop adapter_menu_navi_shop;
    private Adapter_lichsu Adapter_lichsu;
    private ArrayList<model_dathang> arrayList_lichsu;

    private String sdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangcanhan);

        init_view();
        get_data_intent();
        click_giohang();
        click_bottom_menu();
        click_doiavata();
        click_avata();
        set_click_menu();
        click_doimatkhau();
        click_doiten();
        click_dangxuly();
        click_daxuly();
    }

    private void click_daxuly() {
        imgbt_daxuly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList_lichsu.clear();
                Adapter_lichsu.notifyDataSetChanged();
                get_data_daxuly();
            }
        });
    }
    private void get_data_daxuly() {
        FirebaseDatabase.getInstance().getReference().child("dathang_daxuly").child(sdt)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()) {
                            arrayList_lichsu.add(snapshot.getValue(model_dathang.class));
                            Collections.reverse(arrayList_lichsu);
                            Adapter_lichsu.notifyDataSetChanged();
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

    private void click_dangxuly() {
        imgbt_dangxuly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList_lichsu.clear();
                Adapter_lichsu.notifyDataSetChanged();
                get_data_dangxuly();
            }
        });
    }

    private void get_data_dangxuly() {
        FirebaseDatabase.getInstance().getReference().child("dathang").child(sdt)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()) {
                            arrayList_lichsu.add(snapshot.getValue(model_dathang.class));
                            Collections.reverse(arrayList_lichsu);
                            Adapter_lichsu.notifyDataSetChanged();
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

    private void click_doiten() {

        doiten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_dialogdoiten();
            }
        });

    }

    private void set_dialogdoiten() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_doiten);

        EditText edt_tendoi = dialog.findViewById(R.id.edt_tendoi);
        Button bt_luuten = dialog.findViewById(R.id.bt_luuten);

        bt_luuten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_tendoi.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Nhập tên để đổi", Toast.LENGTH_SHORT).show();
                }else {
                    model_account_sigin model_account_sigin = new model_account_sigin();
                    model_account_sigin.setAvata(array_account_sigins.get(0).getAvata());
                    model_account_sigin.setLoaitk(array_account_sigins.get(0).getLoaitk());
                    model_account_sigin.setPasswork(array_account_sigins.get(0).getPasswork());
                    model_account_sigin.setSdt(array_account_sigins.get(0).getSdt());
                    model_account_sigin.setUsername(edt_tendoi.getText().toString());

                    FirebaseDatabase.getInstance().getReference().child("user").child(sdt).child("info")
                            .setValue(model_account_sigin).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(), "Đổi tên thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });

        dialog.show();
    }

    private void click_doimatkhau() {
        doimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_dialogdoimatkhau();
            }
        });
    }

    private void set_dialogdoimatkhau() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_doimk);

        EditText edt_mkdoi = dialog.findViewById(R.id.edt_matkhaudoi);
        Button bt_luumk = dialog.findViewById(R.id.bt_luumatkhau);

        bt_luumk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt_mkdoi.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Nhập mật khẩu để đổi", Toast.LENGTH_SHORT).show();
                }else {
                    model_account_sigin model_account_sigin = new model_account_sigin();
                    model_account_sigin.setAvata(array_account_sigins.get(0).getAvata());
                    model_account_sigin.setLoaitk(array_account_sigins.get(0).getLoaitk());
                    model_account_sigin.setPasswork(edt_mkdoi.getText().toString());
                    model_account_sigin.setSdt(array_account_sigins.get(0).getSdt());
                    model_account_sigin.setUsername(array_account_sigins.get(0).getUsername());

                    FirebaseDatabase.getInstance().getReference().child("user").child(sdt).child("info")
                            .setValue(model_account_sigin).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });

        dialog.show();
    }

    private void set_click_menu() {
        listView_menu_navi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Intent intent = new Intent(Trangcanhan.this, Trangcanhan.class);
                        intent.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(Trangcanhan.this, Home.class);
                        intent1.putExtra(Key_Account.Phone, sdt);
                        intent1.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(Trangcanhan.this, Sanpham.class);
                        intent2.putExtra(Key_Account.Phone, sdt);
                        intent2.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent2.putExtra(Key_menu_sp.Key_sanpham, "Điện thoại");
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(Trangcanhan.this, Sanpham.class);
                        intent3.putExtra(Key_Account.Phone, sdt);
                        intent3.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent3.putExtra(Key_menu_sp.Key_sanpham, "Laptop");
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(Trangcanhan.this, Sanpham.class);
                        intent4.putExtra(Key_Account.Phone, sdt);
                        intent4.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent4.putExtra(Key_menu_sp.Key_sanpham, "Đồng hồ");
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(Trangcanhan.this, Sanpham.class);
                        intent5.putExtra(Key_Account.Phone, sdt);
                        intent5.putExtra(Key_Account.Loaitk, array_account_sigins.get(0).getLoaitk());
                        intent5.putExtra(Key_menu_sp.Key_sanpham, "Phụ kiện");
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(Trangcanhan.this, Quanly.class);
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

    private void set_infor() {
        if (array_account_sigins.get(0).getAvata().equals("")){
            img_avata_home.setImageResource(R.drawable.ic_baseline_person_24);
        }else {
            Picasso.with(getApplicationContext()).load(array_account_sigins.get(0).getAvata()).into(img_avata_home);
        }

        txt_user_home.setText(array_account_sigins.get(0).getUsername());
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

    private void click_doiavata() {
        doiavata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 123);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {

            FirebaseStorage.getInstance().getReference().child("user").child("avata").child(sdt).child(System.currentTimeMillis() + "")
                    .putFile(data.getData()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                    task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            model_account_sigin model_account_sigin = new model_account_sigin();
                            Picasso.with(getApplicationContext()).load(uri).into(img_avata_home);
                            model_account_sigin.setAvata(uri +"");
                            model_account_sigin.setLoaitk(array_account_sigins.get(0).getLoaitk());
                            model_account_sigin.setPasswork(array_account_sigins.get(0).getPasswork());
                            model_account_sigin.setSdt(array_account_sigins.get(0).getSdt());
                            model_account_sigin.setUsername(array_account_sigins.get(0).getUsername());

                            FirebaseDatabase.getInstance().getReference().child("user").child(sdt).child("info")
                                    .setValue(model_account_sigin).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    set_infor();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                }
            });

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void click_bottom_menu() {
        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_bottom_home:
                        item.setChecked(true);
                        Intent intent = new Intent(Trangcanhan.this, Home.class);
                        intent.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent);
                        break;
                    case R.id.menu_bottom_gift:
                        item.setChecked(true);
                        Intent intent1 = new Intent(Trangcanhan.this, SanphamNews.class);
                        intent1.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent1);
                        break;
                    case R.id.menu_bottom_local:
                        item.setChecked(true);
                        Intent intent2 = new Intent(Trangcanhan.this, Vitri.class);
                        intent2.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent2);
                        break;
                    case R.id.menu_bottom_taikhoan:
                        item.setChecked(true);
                        break;
                }

                return false;
            }
        });
    }

    private void click_giohang() {
        imgbt_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Trangcanhan.this, Giohang.class);
                intent.putExtra(Key_Account.Phone, sdt);
                startActivity(intent);
            }
        });
    }

    private void get_data_intent() {
        if (getIntent() != null) {
            sdt = getIntent().getStringExtra(Key_Account.Phone);
            get_data_user();
        }
    }

    private void init_view() {
        imgbt_dangxuly = findViewById(R.id.imgbt_dangxuly);
        bottom_menu = findViewById(R.id.bottom_menu);
        doiavata = findViewById(R.id.doiavata);
        img_avata_home = findViewById(R.id.img_avata_home);
        imgbt_giohang = findViewById(R.id.imgbt_giohang);

        listView_menu_navi = findViewById(R.id.list_menu);
        drawerLayout = findViewById(R.id.layout_full);

        txt_user_home = findViewById(R.id.txt_user_home);
        recy = findViewById(R.id.recy);

        doiten = findViewById(R.id.doiten);
        doimatkhau = findViewById(R.id.doimatkhau);
        arrayList_lichsu = new ArrayList<>();
        Adapter_lichsu = new Adapter_lichsu(getApplicationContext(), arrayList_lichsu, sdt);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recy.setLayoutManager(linearLayoutManager);
        recy.setAdapter(Adapter_lichsu);
        imgbt_daxuly = findViewById(R.id.imgbt_daxuly);
        arrayList_menu_navi = new ArrayList<>();
        adapter_menu_navi_shop = new Adapter_menu_navi_shop(getApplicationContext(), arrayList_menu_navi);
        listView_menu_navi.setAdapter(adapter_menu_navi_shop);

        array_account_sigins = new ArrayList<>();
    }
}