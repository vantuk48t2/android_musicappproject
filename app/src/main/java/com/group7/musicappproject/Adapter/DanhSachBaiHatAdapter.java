package com.group7.musicappproject.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group7.musicappproject.Model.BaiHat;
import com.group7.musicappproject.R;

import java.util.ArrayList;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder> {

    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public DanhSachBaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_danhsachbaihat, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat = baiHatArrayList.get(i);
        String index = i + 1 + "";
        viewHolder.txtIndex.setText(index);
        viewHolder.txtTenBaiHat.setText(baiHat.getTenBaiHat());
        viewHolder.txtTenCaSi.setText(baiHat.getCaSi());
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIndex, txtTenBaiHat, txtTenCaSi;
        ImageView imgLuotThich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.txtDanhSachIndex);
            txtTenCaSi = itemView.findViewById(R.id.txtTenCaSiTrongDanhSach);
            txtTenBaiHat = itemView.findViewById(R.id.txtTenBaiHatTrongDanhSach);
            imgLuotThich = itemView.findViewById(R.id.imgLuotThichDanhSach);
        }
    }
}
