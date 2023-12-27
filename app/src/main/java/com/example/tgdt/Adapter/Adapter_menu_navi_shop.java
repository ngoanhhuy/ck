package com.example.tgdt.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tgdt.Model.model_menu_navi_shop;
import com.example.tgdt.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_menu_navi_shop extends BaseAdapter {

    private Context context;
    private ArrayList<model_menu_navi_shop> arrayList;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<model_menu_navi_shop> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<model_menu_navi_shop> arrayList) {
        this.arrayList = arrayList;
    }

    public Adapter_menu_navi_shop(Context context, ArrayList<model_menu_navi_shop> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class Viewholder{

        private CircleImageView circlerview;
        private TextView txt_menu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Viewholder viewholder = null;

        if (convertView == null) {
            viewholder = new Viewholder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_list_menu_cuahang, null);
            viewholder.circlerview = convertView.findViewById(R.id.circlerview);
            viewholder.txt_menu = convertView.findViewById(R.id.txt_menu);
            convertView.setTag(viewholder);
        }else {
            viewholder = (Viewholder) convertView.getTag();
        }
        model_menu_navi_shop model_list_navi = arrayList.get(position);
        Picasso.with(context).load(model_list_navi.getCirclerview()).into(viewholder.circlerview);
        viewholder.txt_menu.setText(model_list_navi.getTxt_menu());

        return convertView;
    }
}
