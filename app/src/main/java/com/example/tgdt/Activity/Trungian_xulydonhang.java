package com.example.tgdt.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tgdt.Adapter.Adapter_giohang;
import com.example.tgdt.Console.Key_Account;
import com.example.tgdt.Console.Key_dathang;
import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Model.model_dathang;
import com.example.tgdt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Random;

public class Trungian_xulydonhang extends AppCompatActivity {

    private String url;
    private String tenhang;
    private String giahang;
    private String mota;
    private String diachinhan;
    private String sdtnhan;
    private String tennhan;
    private String tongtien;
    private String sdttk;
    private String idhang;
    private String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trungian_xulydonhang);

        get_data_intent();
    }

    private void get_data_intent() {
        if (getIntent() != null) {
            url = getIntent().getStringExtra(Key_dathang.Key_url);
            tenhang = getIntent().getStringExtra(Key_dathang.Key_tenhang);
            giahang = getIntent().getStringExtra(Key_dathang.Key_giahang);
            mota = getIntent().getStringExtra(Key_dathang.Key_mota);
            diachinhan = getIntent().getStringExtra(Key_dathang.Key_diachinhan);
            sdtnhan = getIntent().getStringExtra(Key_dathang.Key_sdtnhan);
            tennhan = getIntent().getStringExtra(Key_dathang.Key_tennhan);
            tongtien = getIntent().getStringExtra(Key_dathang.Key_tongtien);
            sdttk = getIntent().getStringExtra(Key_dathang.Key_sdttk);
            idhang = getIntent().getStringExtra(Key_dathang.Key_idhang);
            position = getIntent().getStringExtra(Key_dathang.Position);

            them_dondaxyly();
        }
    }
    private void them_dondaxyly() {
        model_dathang model_dathang = new model_dathang();
        model_dathang.setUrl(url);
        model_dathang.setTenhang(tenhang);
        model_dathang.setGiahang(giahang);
        model_dathang.setMota(mota);
        model_dathang.setDiachinhan(diachinhan);
        model_dathang.setSdtnhan(sdtnhan);
        model_dathang.setTennhan(tennhan);
        model_dathang.setTongtien(tongtien);
        model_dathang.setSdttk(sdttk);
        model_dathang.setIdhang(idhang);

        FirebaseDatabase.getInstance().getReference().child("dathang_daxuly")
                .child(sdttk).child(System.currentTimeMillis()+"").setValue(model_dathang).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                FirebaseDatabase.getInstance().getReference().child("dathang_daxuly")
                        .child("all").child(System.currentTimeMillis()+"").setValue(model_dathang).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        xoa_donchuaxuly();
                        startActivity(new Intent(Trungian_xulydonhang.this, Quanly_donhang.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
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

    private void xoa_donchuaxuly() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("dathang");
        Query applesQuery = ref.child("all").orderByChild("idhang").equalTo(idhang);

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
        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference().child("dathang");
        Query applesQuery2 = ref2.child(sdttk).orderByChild("idhang").equalTo(idhang);

        applesQuery2.addListenerForSingleValueEvent(new ValueEventListener() {
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
}