package com.example.tgdt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tgdt.Activity.Thanhtoan;
import com.example.tgdt.Activity.Trungian_xulydonhang;
import com.example.tgdt.Console.Key_dathang;
import com.example.tgdt.Console.Key_menu_sp;
import com.example.tgdt.Model.model_dathang;
import com.example.tgdt.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class Adapter_dathang_quanly extends RecyclerView.Adapter<Adapter_dathang_quanly.Viewholder> {

    private Context context;
    private List<model_dathang> list;

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

    public Adapter_dathang_quanly(Context context, List<model_dathang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.row_infor_quanlydon, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        int sotien = Integer.parseInt(list.get(position).getTongtien());
        holder.txt_sotien.setText("Số tiền "+decimalFormat.format(sotien) + " vnd");
        holder.txt_sdtnhan.setText("Số điện thoại nhận: "+list.get(position).getSdtnhan());
        holder.txt_diachinhan.setText("Địa chỉ nhận: "+list.get(position).getDiachinhan());
        holder.txt_tenhang.setText(list.get(position).getDiachinhan());
        holder.txt_tennhan.setText("Tên người nhận: "+list.get(position).getTennhan());
        Picasso.with(context).load(list.get(position).getUrl()).into(holder.img_giohang);
        int giahang = Integer.parseInt(list.get(position).getGiahang());
        holder.txt_giahang.setText(giahang +"");
        holder.txt_motahang.setText(list.get(position).getMota());
        holder.bt_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Trungian_xulydonhang.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra(Key_dathang.Key_url, list.get(position).getUrl());
                intent.putExtra(Key_dathang.Key_tenhang, list.get(position).getTenhang());
                intent.putExtra(Key_dathang.Key_giahang, list.get(position).getGiahang());
                intent.putExtra(Key_dathang.Key_mota, list.get(position).getMota());
                intent.putExtra(Key_dathang.Key_diachinhan, list.get(position).getDiachinhan());
                intent.putExtra(Key_dathang.Key_sdtnhan, list.get(position).getSdtnhan());
                intent.putExtra(Key_dathang.Key_tennhan, list.get(position).getTennhan());
                intent.putExtra(Key_dathang.Key_tongtien, list.get(position).getTongtien());
                intent.putExtra(Key_dathang.Key_sdttk, list.get(position).getSdttk());
                intent.putExtra(Key_dathang.Key_idhang, list.get(position).getIdhang());
                intent.putExtra(Key_dathang.Position, position);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private TextView txt_sotien, txt_sdtnhan, txt_diachinhan, txt_tennhan;
        private ImageView img_giohang;
        private TextView txt_tenhang, txt_giahang, txt_motahang;
        private Button bt_xacnhan;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img_giohang = itemView.findViewById(R.id.img_hang);
            txt_giahang = itemView.findViewById(R.id.txt_giahang);
            txt_motahang = itemView.findViewById(R.id.txt_motahang);
            txt_tenhang = itemView.findViewById(R.id.txt_tenhang);
            bt_xacnhan = itemView.findViewById(R.id.bt_xacnhan);
            txt_sotien = itemView.findViewById(R.id.txt_sotien);
            txt_sdtnhan = itemView.findViewById(R.id.txt_sdtnhan);
            txt_diachinhan = itemView.findViewById(R.id.txt_diachinhan);
            txt_tennhan = itemView.findViewById(R.id.txt_tennhan);

        }
    }
}
