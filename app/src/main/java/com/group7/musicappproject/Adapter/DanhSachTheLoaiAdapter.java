package com.group7.musicappproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group7.musicappproject.Activity.ListSongActivity;
import com.group7.musicappproject.Model.TheLoai;
import com.group7.musicappproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachTheLoaiAdapter extends RecyclerView.Adapter<DanhSachTheLoaiAdapter.ViewHolder> {

    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public DanhSachTheLoaiAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_danhsachtheloai, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TheLoai theLoai = theLoaiArrayList.get(i);
        Picasso.with(context)
                .load(theLoai.getHinhTheLoai())
                .into(viewHolder.imgTheLoai);
        viewHolder.txtTenTheLoai.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgTheLoai;
        TextView txtTenTheLoai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTheLoai = itemView.findViewById(R.id.imgTheLoaiTheoChuDe);
            txtTenTheLoai = itemView.findViewById(R.id.txtTenTheLoaiTheoChuDe);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("theLoai", theLoaiArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
