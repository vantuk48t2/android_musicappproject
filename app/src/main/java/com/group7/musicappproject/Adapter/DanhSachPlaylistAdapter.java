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
import com.group7.musicappproject.Model.Playlist;
import com.group7.musicappproject.R;
import com.squareup.picasso.Picasso;

import java.security.PublicKey;
import java.util.ArrayList;

public class DanhSachPlaylistAdapter extends RecyclerView.Adapter<DanhSachPlaylistAdapter.ViewHolder> {

    Context context;
    ArrayList<Playlist> playlistArrayList;

    public DanhSachPlaylistAdapter(Context context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_danhsachplaylist, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Playlist playlist = playlistArrayList.get(i);
        viewHolder.txtTenPlaylist.setText(playlist.getTen());

        Picasso.with(context)
                .load(playlist.getHinhNen())
                .into(viewHolder.imgHinhNen);
    }

    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHinhNen;
        TextView txtTenPlaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhNen = itemView.findViewById(R.id.imgDanhSachPlaylist);
            txtTenPlaylist = itemView.findViewById(R.id.txtTenPlaylistTrongDanhSach);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("itemPlaylist", playlistArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
