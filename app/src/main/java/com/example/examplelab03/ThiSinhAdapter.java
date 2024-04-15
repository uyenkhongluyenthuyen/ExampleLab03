package com.example.examplelab03;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ThiSinhAdapter extends BaseAdapter implements Filterable {

    private List<ThiSinh> listTS;

    //ngữ cảnh của ứng dụng
    private Activity context;

    // đối tượng phân tích layout
    private LayoutInflater inflater;

    private  ArrayList<ThiSinh> databackup;

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


    @Override
    public Filter getFilter() {
        Filter f = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults fr = new FilterResults();
                //backup dữ liệu: lưu tạm dât vào databaclup
                if(databackup == null){
                    databackup = new ArrayList<>(listTS);
                }
                //nếu chuỗi để filter là rỗng thì khôi phụ dữ liệu
                if(charSequence == null || charSequence.length()==0){
                    fr.count = databackup.size();
                    fr.values = databackup;
                }// nếu không rỗng thì thực hiện filter
                else{
                    ArrayList<ThiSinh> newdata = new ArrayList<>();
                    for(ThiSinh s: databackup){
                        if(s.getHoTen().toLowerCase().contains(
                                charSequence.toString().toLowerCase()))
                            newdata.add(s);
                    }
                    fr.count = newdata.size();
                    fr.values = newdata;
                }
                return fr;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listTS = new ArrayList<ThiSinh>();
                ArrayList<ThiSinh> tmp = (ArrayList<ThiSinh>) filterResults.values;
                for(ThiSinh s: tmp){
                    listTS.add(s);
                }
                notifyDataSetChanged();
            }
        };
        return f;
    }

    public ArrayList<ThiSinh> sortByTotalPoint(){
        Collections.sort(listTS, new Comparator<ThiSinh>() {
            @Override
            public int compare(ThiSinh ts1, ThiSinh ts2) {
                float diff = ts1.tongDiem() - ts2.tongDiem();
                return Float.compare(diff, 0.0f);
            }
        });
        return (ArrayList<ThiSinh>) listTS;
    }
}
