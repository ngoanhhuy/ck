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

import com.example.tgdt.Activity.Infor_sanpham;
import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Model.model_dathang;
import com.example.tgdt.Model.model_sanpham;
import com.example.tgdt.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class Adapter_lichsu extends RecyclerView.Adapter<Adapter_lichsu.Viewholder> {

    private Context context;
    private List<model_dathang> list;
    private String sdt;

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Adapter_lichsu(Context context, List<model_dathang> list, String sdt) {
        this.context = context;
        this.list = list;
        this.sdt = sdt;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<model_dathang> getList() {
        return list;
    }

    public void setList(List<model_dathang> list) {
        this.list = list;
    }

    public Adapter_lichsu(Context context, List<model_dathang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.row_news, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.txt_mota.setText(list.get(position).getMota());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        int gia = Integer.parseInt(list.get(position).getGiahang());
        holder.txt_gia.setText(decimalFormat.format(gia));
        holder.txt_ten.setText(list.get(position).getTenhang());
        Picasso.with(context).load(list.get(position).getUrl()).into(holder.img_url);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private ImageView img_url;
        private TextView txt_ten, txt_gia, txt_mota;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            img_url = itemView.findViewById(R.id.img_url);
            txt_gia = itemView.findViewById(R.id.txt_gia);
            txt_ten = itemView.findViewById(R.id.txt_ten);
            txt_mota = itemView.findViewById(R.id.txt_mota);
        }
    }
}
