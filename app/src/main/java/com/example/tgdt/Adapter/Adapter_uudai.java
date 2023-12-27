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
import com.example.tgdt.Model.model_sanpham;
import com.example.tgdt.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class Adapter_uudai extends RecyclerView.Adapter<Adapter_uudai.Viewholder> {

    private Context context;
    private List<model_sanpham> list;
    private String sdt;

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Adapter_uudai(Context context, List<model_sanpham> list, String sdt) {
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

    public List<model_sanpham> getList() {
        return list;
    }

    public void setList(List<model_sanpham> list) {
        this.list = list;
    }

    public Adapter_uudai(Context context, List<model_sanpham> list) {
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
        int gia = Integer.parseInt(list.get(position).getGia_sp());
        holder.txt_gia.setText(decimalFormat.format(gia));
        holder.txt_ten.setText(list.get(position).getTen_sp());
        Picasso.with(context).load(list.get(position).getLink_anh_sp()).into(holder.img_url);
        holder.img_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Infor_sanpham.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Key_menu_sp.Key_masp, position+"");
                intent.putExtra(Key_menu_sp.Key_loaisp,list.get(position).getLoaisp() + "");
                intent.putExtra(Key_menu_sp.Key_sdt, sdt+"");
                context.startActivity(intent);
            }
        });
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
