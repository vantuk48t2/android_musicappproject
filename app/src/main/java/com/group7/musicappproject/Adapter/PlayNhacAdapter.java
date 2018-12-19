package com.group7.musicappproject.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group7.musicappproject.Model.BaiHat;
import com.group7.musicappproject.R;

import java.util.ArrayList;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder> {

    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public PlayNhacAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
         this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_playnhac, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat = baiHatArrayList.get(i);
        viewHolder.txtIndex.setText(i + 1 + "");
        viewHolder.txtTenBaiHat.setText(baiHat.getTenBaiHat());
        viewHolder.txtTenCaSi.setText(baiHat.getCaSi());
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIndex, txtTenBaiHat, txtTenCaSi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.txtPlayIndex);
            txtTenBaiHat = itemView.findViewById(R.id.txtPlayTenBaiHat);
            txtTenCaSi = itemView.findViewById(R.id.txtPlayTenCaSi);
        }
    }
}
