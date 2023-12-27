package com.example.tgdt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tgdt.Activity.Trunggian_xoahang;
import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Model.model_sanpham;
import com.example.tgdt.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_sanpham_quanly extends RecyclerView.Adapter<Adapter_sanpham_quanly.Viewholder> {

    private Context context;
    private ArrayList<model_sanpham> arrayList;
    private String sdt;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<model_sanpham> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<model_sanpham> arrayList) {
        this.arrayList = arrayList;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Adapter_sanpham_quanly(Context context, ArrayList<model_sanpham> arrayList, String sdt) {
        this.context = context;
        this.arrayList = arrayList;
        this.sdt = sdt;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.row_sp, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int i) {

        holder.txt_tensp.setText(arrayList.get(i).getTen_sp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        int gia = Integer.parseInt(arrayList.get(i).getGia_sp());
        holder.txt_giasp.setText(decimalFormat.format(gia));
        Picasso.with(context).load(arrayList.get(i).getLink_anh_sp()).into(holder.img_sp);
        holder.img_sp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(context, Trunggian_xoahang.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Key_menu_sp.Key_masp, arrayList.get(i).getMahang() +"");
                intent.putExtra(Key_menu_sp.Key_loaisp,arrayList.get(i).getLoaisp() + "");
                context.startActivity(intent);

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        ImageView img_sp;
        TextView txt_tensp;
        TextView txt_giasp;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img_sp = itemView.findViewById(R.id.img_sp);
            txt_giasp = itemView.findViewById(R.id.txt_giasp);
            txt_tensp = itemView.findViewById(R.id.txt_tensp);
        }
    }
}
