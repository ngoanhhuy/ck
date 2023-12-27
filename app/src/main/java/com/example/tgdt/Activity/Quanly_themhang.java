package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tgdt.Adapter.Adapter_menu_navi_shop;
import com.example.tgdt.Adapter.EmployeeDataUtils;
import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Console.Link_anh_menu_shop;
import com.example.tgdt.Model.Employee;
import com.example.tgdt.Model.model_menu_navi_shop;
import com.example.tgdt.Model.model_sanpham;
import com.example.tgdt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class Quanly_themhang extends AppCompatActivity {
    
    private EditText edt_tensp, edt_giasp, edt_motasp;
    private Spinner spiner_loaisp;
    private ImageView img_anhsp;
    private Button bt_themhang;
    private TextView txt_loaisp;
    private BottomNavigationView bottom_menu;
    private DrawerLayout drawerLayout;
    private ListView list_menu;
    private ImageButton imgbt_menu;

    private String linkanh;
    private String loaisp;

    private ProgressDialog progressDialog;

    private ArrayList<model_menu_navi_shop> arrayList_shop;
    private Adapter_menu_navi_shop adapter_menu_navi_shop;

    private int key_them_anh = 123;

    private String sdt;
    private String loaitk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_themhang);
        
        init_view();
        get_data_intent();
        click_themanh();
        click_themhang();
        setProgressDialog();
        set_spinner();
        click_bottom_menu();
        set_click_menu();
        click_menu();
        set_menu();
    }

    private void set_click_menu() {
        list_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Intent intent = new Intent(Quanly_themhang.this, Trangcanhan.class);
                        intent.putExtra(Key_Account.Phone, sdt);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(Quanly_themhang.this, Home.class);
                        intent1.putExtra(Key_Account.Phone, sdt);
                        intent1.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(Quanly_themhang.this, Sanpham.class);
                        intent2.putExtra(Key_Account.Phone, sdt);
                        intent2.putExtra(Key_Account.Loaitk, loaitk);
                        intent2.putExtra(Key_menu_sp.Key_sanpham, "dienthoai");
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(Quanly_themhang.this, Sanpham.class);
                        intent3.putExtra(Key_Account.Phone, sdt);
                        intent3.putExtra(Key_Account.Loaitk, loaitk);
                        intent3.putExtra(Key_menu_sp.Key_sanpham, "laptop");
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(Quanly_themhang.this, Sanpham.class);
                        intent4.putExtra(Key_Account.Phone, sdt);
                        intent4.putExtra(Key_Account.Loaitk, loaitk);
                        intent4.putExtra(Key_menu_sp.Key_sanpham, "dongho");
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(Quanly_themhang.this, Sanpham.class);
                        intent5.putExtra(Key_Account.Phone, sdt);
                        intent5.putExtra(Key_Account.Loaitk, loaitk);
                        intent5.putExtra(Key_menu_sp.Key_sanpham, "phukien");
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(Quanly_themhang.this, Quanly.class);
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
                        Intent intent_themhang = new Intent(Quanly_themhang.this, Quanly.class);
                        intent_themhang.putExtra(Key_Account.Phone, sdt);
                        intent_themhang.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent_themhang);
                        break;
                    case R.id.menu_bottom_themhang:
                        item.setChecked(true);
                        break;
                    case R.id.menu_bottom_donhang:
                        item.setChecked(true);
                        Intent intent_donhang = new Intent(Quanly_themhang.this, Quanly_donhang.class);
                        intent_donhang.putExtra(Key_Account.Phone, sdt);
                        intent_donhang.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent_donhang);
                        break;
                    case R.id.menu_bottom_quanly:
                        item.setChecked(true);
                        Intent intent_quanly = new Intent(Quanly_themhang.this, Quanly_shop.class);
                        intent_quanly.putExtra(Key_Account.Phone, sdt);
                        intent_quanly.putExtra(Key_Account.Loaitk, loaitk);
                        startActivity(intent_quanly);
                        break;
                }

                return false;
            }
        });
    }

    private void set_spinner() {
        Employee[] employees = EmployeeDataUtils.getEmployees();
        ArrayAdapter<Employee> adapter = new ArrayAdapter<Employee>(this,
                android.R.layout.simple_spinner_item,
                employees);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner_loaisp.setAdapter(adapter);

        spiner_loaisp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Employee employee = (Employee) adapter.getItem(position);
        loaisp = employee.getLoaisp();
    }

    private void setProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("loading...");
    }

    private void click_themhang() {

        bt_themhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_tensp.getText().toString().length() == 0 || edt_giasp.getText().toString().length() == 0
                        || edt_motasp.getText().toString().length() == 0 || loaisp.length() == 0
                        || linkanh.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Nhập đủ thông tin sản phẩm trước khi thêm.", Toast.LENGTH_SHORT).show();
                }else {
                    model_sanpham model_sp = new model_sanpham();
                    model_sp.setGia_sp(edt_giasp.getText().toString());
                    model_sp.setLink_anh_sp(linkanh);
                    model_sp.setMota(edt_motasp.getText().toString());
                    model_sp.setTen_sp(edt_tensp.getText().toString());
                    model_sp.setLoaisp(loaisp);
                    Random r = new Random();
                    int i1 = r.nextInt(10000);
                    model_sp.setMahang(i1 + "");


                    FirebaseDatabase.getInstance().getReference().child("Hanghoa").child(loaisp).
                            child(System.currentTimeMillis() + "").setValue(model_sp)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    FirebaseDatabase.getInstance().getReference().child("Hanghoa").child("all").
                                            child(System.currentTimeMillis() + "").setValue(model_sp)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(getApplicationContext(), "Thêm hàng thành công", Toast.LENGTH_SHORT).show();
                                                    edt_tensp.setText("");
                                                    edt_giasp.setText("");
                                                    edt_motasp.setText("");
                                                    img_anhsp.setImageResource(R.drawable.ic_baseline_image_24);
                                                    progressDialog.cancel();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressDialog.cancel();
                                            Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.cancel();
                            Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void click_themanh() {
        img_anhsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, key_them_anh);
                progressDialog.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == key_them_anh && resultCode == RESULT_OK && data != null) {

            FirebaseStorage.getInstance().getReference().child("hanghoa").child(System.currentTimeMillis() + "")
                    .putFile(data.getData()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                    task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            progressDialog.cancel();
                            linkanh = uri + "";
                            Picasso.with(getApplicationContext()).load(linkanh).into(img_anhsp);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.cancel();
                    Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                }
            });

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init_view() {
        imgbt_menu = findViewById(R.id.imgbt_menu);
        edt_tensp = findViewById(R.id.edt_tensp);
        edt_giasp = findViewById(R.id.edt_giasp);
        edt_motasp = findViewById(R.id.edt_mota);
        spiner_loaisp = findViewById(R.id.spiner_loaisp);
        img_anhsp = findViewById(R.id.img_anhsp);
        bt_themhang = findViewById(R.id.bt_themsp);
        txt_loaisp = findViewById(R.id.txt_loaisp);
        bottom_menu = findViewById(R.id.bottom_menu);
        drawerLayout = findViewById(R.id.layout_full);
        list_menu = findViewById(R.id.list_menu);
        arrayList_shop = new ArrayList<>();
        adapter_menu_navi_shop = new Adapter_menu_navi_shop(getApplicationContext(), arrayList_shop);
        list_menu.setAdapter(adapter_menu_navi_shop);
    }
}