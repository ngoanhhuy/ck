package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Model.model_dathang;
import com.example.tgdt.Model.model_giohang;
import com.example.tgdt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Thanhtoan extends AppCompatActivity {

    private ImageView img_sp;
    private EditText edt_diachi, edt_sdt, edt_tennhan;
    private TextView txt_tensp, txt_giasp, txt_motasp;
    private TextView txt_tienhang, txt_tienship, txt_tong;
    private Button bt_dathang;

    private String sdt, position;
    private ArrayList<model_giohang> arrayList_giohang;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);

        inti_view();
        getdata_intent();
        click_dathang();
        set_progessdialog();
    }

    private void set_progessdialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("Loading...");
        progressDialog.show();
    }

    private void click_dathang() {
        bt_dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_diachi.getText().toString().length() != 0 && edt_sdt.getText().toString().length() != 0
                && edt_tennhan.getText().toString().length() != 0) {
                    String diachinhan = edt_diachi.getText().toString();
                    String sdtnhan = edt_sdt.getText().toString();
                    String tennhan = edt_tennhan.getText().toString();

                    model_dathang model_dathang = new model_dathang();
                    model_dathang.setUrl(arrayList_giohang.get(Integer.parseInt(position)).getUrl());
                    model_dathang.setTenhang(arrayList_giohang.get(Integer.parseInt(position)).getTenhang());
                    model_dathang.setGiahang(arrayList_giohang.get(Integer.parseInt(position)).getGiahang());
                    model_dathang.setMota(arrayList_giohang.get(Integer.parseInt(position)).getMota());
                    model_dathang.setDiachinhan(diachinhan);
                    model_dathang.setSdtnhan(sdtnhan);
                    model_dathang.setTennhan(tennhan);
                    model_dathang.setSdttk(sdt);
                    Random r = new Random();
                    int i1 = r.nextInt(10000);
                    model_dathang.setIdhang(i1 + "");
                    int a = Integer.parseInt(arrayList_giohang.get(Integer.parseInt(position)).getGiahang());
                    int tongtien = a + 30000;
                    model_dathang.setTongtien(tongtien +"");

                    FirebaseDatabase.getInstance().getReference().child("dathang").child(sdt+"")
                            .child(System.currentTimeMillis() +"").setValue(model_dathang)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    FirebaseDatabase.getInstance().getReference().child("dathang").child("all")
                                            .child(System.currentTimeMillis() +"").setValue(model_dathang)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(getApplicationContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();

                                                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("giohang");
                                                    Query applesQuery = ref.child(sdt+"").orderByChild("tenhang").equalTo(arrayList_giohang.get(Integer.parseInt(position)).getTenhang());

                                                    applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                            for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                                                appleSnapshot.getRef().removeValue();
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(DatabaseError databaseError) {
                                                        }
                                                    });

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Có lỗi khi đặt hàng", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Có lỗi khi đặt hàng", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    Toast.makeText(getApplicationContext(), "Nhập đủ thông tin trước khi đặt hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getdata_intent() {
        if (getIntent() != null) {
            position = getIntent().getStringExtra(Key_menu_sp.Key_masp);
            sdt = getIntent().getStringExtra(Key_menu_sp.Key_sdt);
            get_data_sp();
        }
    }
    private void set_view() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        Picasso.with(getApplicationContext()).load(arrayList_giohang.get(Integer.parseInt(position)).getUrl()).into(img_sp);
        txt_tensp.setText(arrayList_giohang.get(Integer.parseInt(position)).getTenhang());

        int giasp = Integer.parseInt(arrayList_giohang.get(Integer.parseInt(position)).getGiahang());
        txt_giasp.setText(decimalFormat.format(giasp) + " vnd");
        txt_motasp.setText(arrayList_giohang.get(Integer.parseInt(position)).getMota());

        int tienhang = Integer.parseInt(arrayList_giohang.get(Integer.parseInt(position)).getGiahang());
        txt_tienhang.setText("Tiền hàng: " + decimalFormat.format(tienhang)  +" vnd");
        int a = Integer.parseInt(arrayList_giohang.get(Integer.parseInt(position)).getGiahang());
        int tongtien = a + 30000;
        txt_tong.setText("Tổng tiền: " + decimalFormat.format(tongtien)  + " vnd");
    }

    private void get_data_sp() {
        FirebaseDatabase.getInstance().getReference().child("giohang").child(sdt+"")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()) {
                            arrayList_giohang.add(snapshot.getValue(model_giohang.class));
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    set_view();
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

    private void inti_view() {
        img_sp = findViewById(R.id.img_sp);
        edt_diachi = findViewById(R.id.edt_diachi);
        edt_sdt = findViewById(R.id.edt_sdt);
        edt_tennhan = findViewById(R.id.edt_tennhan);
        txt_tensp = findViewById(R.id.txt_tensp);
        txt_giasp = findViewById(R.id.txt_giasp);
        txt_motasp = findViewById(R.id.txt_motasp);
        txt_tienhang = findViewById(R.id.txt_tienhang);
        txt_tienship = findViewById(R.id.txt_tienship);
        txt_tong = findViewById(R.id.txt_tong);
        bt_dathang = findViewById(R.id.bt_dathang);
        arrayList_giohang = new ArrayList<>();
    }
}