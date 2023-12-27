package com.example.tgdt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tgdt.Activity.Infor_sanpham;
import com.example.tgdt.Activity.Thanhtoan;
import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Model.model_giohang;
import com.example.tgdt.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class Adapter_giohang extends RecyclerView.Adapter<Adapter_giohang.Viewholder> {

    private Context context;
    private List<model_giohang> giohangList;
    private String sdt;

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Adapter_giohang(Context context, List<model_giohang> giohangList, String sdt) {
        this.context = context;
        this.giohangList = giohangList;
        this.sdt = sdt;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<model_giohang> getGiohangList() {
        return giohangList;
    }

    public void setGiohangList(List<model_giohang> giohangList) {
        this.giohangList = giohangList;
    }

    public Adapter_giohang(Context context, List<model_giohang> giohangList) {
        this.context = context;
        this.giohangList = giohangList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.row_giohang, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.txt_tenhang.setText(giohangList.get(position).getTenhang());
        holder.txt_motahang.setText(giohangList.get(position).getMota());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        int gia = Integer.parseInt(giohangList.get(position).getGiahang());
        holder.txt_giahang.setText(decimalFormat.format(gia) + " vnd");
        Picasso.with(context).load(giohangList.get(position).getUrl()).into(holder.img_giohang);
        holder.thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Thanhtoan.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Key_menu_sp.Key_masp, position+"");
                intent.putExtra(Key_menu_sp.Key_sdt, sdt+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return giohangList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private ImageView img_giohang;
        private TextView txt_tenhang, txt_giahang, txt_motahang;
        private Button thanhtoan;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            img_giohang = itemView.findViewById(R.id.img_hang);
            txt_giahang = itemView.findViewById(R.id.txt_giahang);
            txt_motahang = itemView.findViewById(R.id.txt_motahang);
            txt_tenhang = itemView.findViewById(R.id.txt_tenhang);
            thanhtoan = itemView.findViewById(R.id.bt_thanhtoan);
        }
    }
}
