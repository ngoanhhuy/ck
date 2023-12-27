package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Model.model_giohang;
import com.example.tgdt.Model.model_sanpham;
import com.example.tgdt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.Delayed;

public class Infor_sanpham extends AppCompatActivity {

    private ImageView img_info_anh;
    private TextView txt_info_ten, txt_info_gia, txt_info_mota;
    private Button bt_themhang;
    private TextView txt_number_sp;

    private String position;
    private String loaisp;
    private String sdt;
    private ProgressDialog progressDialog;

    private ArrayList<model_sanpham> arrayList_sp;
    private ArrayList<model_giohang> arrayList_giohang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_sanpham);

        initview();
        get_data_intent();
        set_progessdialog();
        click_bt_themhang();
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

    private void click_bt_themhang() {
        bt_themhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model_giohang model_giohang = new model_giohang();
                model_giohang.setSdt(sdt);
                model_giohang.setGiahang(arrayList_sp.get(Integer.parseInt(position)).getGia_sp());
                model_giohang.setTenhang(arrayList_sp.get(Integer.parseInt(position)).getTen_sp());
                model_giohang.setLoaihang(loaisp);
                model_giohang.setUrl(arrayList_sp.get(Integer.parseInt(position)).getLink_anh_sp());
                model_giohang.setDiachi("");
                model_giohang.setSdt_nhanhang("");
                model_giohang.setMota(arrayList_sp.get(Integer.parseInt(position)).getMota());

                FirebaseDatabase.getInstance().getReference().child("giohang").child(sdt +"").
                        child(System.currentTimeMillis()+"").setValue(model_giohang).addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                FirebaseDatabase.getInstance().getReference().child("giohang").child("all").
                                        child(System.currentTimeMillis()+"").setValue(model_giohang).addOnCompleteListener(
                                        new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(getApplicationContext(), "Thêm hàng vào giỏ thành công", Toast.LENGTH_SHORT).show();
                                                finish();
                                                get_giohang();
                                            }
                                        }
                                ).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void set_progessdialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("Loading...");
        progressDialog.show();
    }

    private void set_info_sp() {
        txt_info_ten.setText(arrayList_sp.get(Integer.parseInt(position)).getTen_sp());
        Picasso.with(getApplicationContext()).load(arrayList_sp.get(Integer.parseInt(position)).getLink_anh_sp()).into(img_info_anh);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        int gia = Integer.parseInt(arrayList_sp.get(Integer.parseInt(position)).getGia_sp());
        txt_info_gia.setText(decimalFormat.format(gia) + " vnd");
        txt_info_mota.setText(arrayList_sp.get(Integer.parseInt(position)).getMota());
    }

    private void get_data_sp() {

        FirebaseDatabase.getInstance().getReference().child("Hanghoa").
                child(loaisp + "").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists()) {
                    arrayList_sp.add(snapshot.getValue(model_sanpham.class));
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            set_info_sp();
                            progressDialog.cancel();
                        }
                    },3000);
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

    private void initview() {
        img_info_anh = findViewById(R.id.img_anh_info);
        txt_info_gia = findViewById(R.id.txt_info_gia);
        txt_info_ten = findViewById(R.id.txt_info_ten);
        txt_info_mota = findViewById(R.id.txt_info_mota);
        bt_themhang = findViewById(R.id.bt_themgiohang);
        txt_number_sp = findViewById(R.id.txt_number_sp);
        arrayList_sp = new ArrayList<>();
        arrayList_giohang = new ArrayList<>();
    }

    private void get_data_intent() {
        if (getIntent() != null) {
            position = getIntent().getStringExtra(Key_menu_sp.Key_masp);
            loaisp = getIntent().getStringExtra(Key_menu_sp.Key_loaisp);
            sdt = getIntent().getStringExtra(Key_menu_sp.Key_sdt);
            get_data_sp();
            get_giohang();
        }
    }
}