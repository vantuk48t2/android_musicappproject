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
import com.group7.musicappproject.Model.Album;
import com.group7.musicappproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachTatCaAlbumAdapter extends RecyclerView.Adapter<DanhSachTatCaAlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> albumArrayList;

    public DanhSachTatCaAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_tatcaalbum, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Album album = albumArrayList.get(i);
        Picasso.with(context).load(album.getHinhAlbum()).into(viewHolder.imgHinh);
        viewHolder.txtTenAlbum.setText(album.getTenAlbum());
        viewHolder.txtTenCaSi.setText(album.getTenCaSiAlbum());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinh;
        TextView txtTenAlbum, txtTenCaSi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinh = itemView.findViewById(R.id.imgTatCaAlbum);
            txtTenAlbum = itemView.findViewById(R.id.txtTenAlbum);
            txtTenCaSi = itemView.findViewById(R.id.txtTenCaSiAllAlbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("album", albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
