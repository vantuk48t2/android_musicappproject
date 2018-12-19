package com.group7.musicappproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.group7.musicappproject.Activity.ListTheLoaiActivity;
import com.group7.musicappproject.Model.ChuDe;
import com.group7.musicappproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachChuDeAdapter extends RecyclerView.Adapter<DanhSachChuDeAdapter.ViewHolder> {

    Context context;
    ArrayList<ChuDe> chuDeArrayList;

    public DanhSachChuDeAdapter(Context context, ArrayList<ChuDe> chuDeArrayList) {
        this.context = context;
        this.chuDeArrayList = chuDeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_danhsachchude, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ChuDe chuDe = chuDeArrayList.get(i);
        Picasso.with(context)
                .load(chuDe.getHinhChuDe())
                .into(viewHolder.imgHinhNen);
    }

    @Override
    public int getItemCount() {
        return chuDeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHinhNen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhNen = itemView.findViewById(R.id.imgDanhSachChuDe);
            imgHinhNen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListTheLoaiActivity.class);
                    intent.putExtra("chuDe", chuDeArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
