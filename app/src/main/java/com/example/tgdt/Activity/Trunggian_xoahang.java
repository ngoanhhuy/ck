package com.example.tgdt.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Trunggian_xoahang extends AppCompatActivity {

    private String loaisp;
    private String masp;
    private Button bt_xoa;
    private Button bt_trove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trunggian_xoahang);

        init_view();
        get_data_intent();
        click_trove();
    }

    private void click_trove() {
        bt_trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void xoahang() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Hanghoa");
        Query applesQuery = ref.child("all").orderByChild("mahang").equalTo(masp);

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
        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference().child("Hanghoa");
        Query applesQuery2 = ref2.child(loaisp).orderByChild("mahang").equalTo(masp);

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

        finish();
    }

    private void setClick_btxoa() {
        bt_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoahang();
            }
        });
    }

    private void init_view() {
        bt_xoa = findViewById(R.id.bt_xoa);
        bt_trove = findViewById(R.id.bt_trove);
    }

    private void get_data_intent() {

        if (getIntent() != null) {
            loaisp = getIntent().getStringExtra(Key_menu_sp.Key_loaisp);
            masp = getIntent().getStringExtra(Key_menu_sp.Key_masp);
            setClick_btxoa();
        }

    }
}