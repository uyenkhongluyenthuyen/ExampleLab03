package com.example.examplelab03;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ThiSinhAdapter extends BaseAdapter {

    private List<ThiSinh> listTS;

    //ngữ cảnh của ứng dụng
    private Activity context;

    // đối tượng phân tích layout
    private LayoutInflater inflater;

    public ThiSinhAdapter(List<ThiSinh> listTS, Activity context) {
        this.listTS = listTS;
        this.context = context;
        this.inflater =(LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return listTS.size();
    }

    @Override
    public Object getItem(int position) {
        return listTS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = view;
        String packageName = context.getPackageName();
        if(v == null){
            v = inflater.inflate(R.layout.thisinh_item, null);
        }

         TextView tvSBD= v.findViewById(R.id.tvSDB);
         TextView tvHoten= v.findViewById(R.id.tvHoten);
         TextView tvTongDiem= v.findViewById(R.id.tvTongDiem);

         tvSBD.setText(listTS.get(position).getSbd());
         tvHoten.setText(listTS.get(position).getHoTen());
         tvTongDiem.setText(String.valueOf(listTS.get(position).tongDiem()));

        return v;
    }
}
