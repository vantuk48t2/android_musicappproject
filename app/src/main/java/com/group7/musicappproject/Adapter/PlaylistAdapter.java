package com.group7.musicappproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group7.musicappproject.Model.Playlist;
import com.group7.musicappproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(Context context, int resource, List<Playlist> objects) {
        super(context, resource, objects);
    }

    class ViewHolder {
        TextView txtLvTitlePlaylist;
        ImageView imgBackground, imgPlaylist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.line_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.txtLvTitlePlaylist = convertView.findViewById(R.id.txtLvTitlePlaylist);
            viewHolder.imgBackground = convertView.findViewById(R.id.imgBackgroundPlaylist);
            viewHolder.imgPlaylist = convertView.findViewById(R.id.imgPlaylist);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.with(getContext())
                .load(playlist.getHinhNen())
                .into(viewHolder.imgBackground);
        Picasso.with(getContext())
                .load(playlist.getIcon())
                .into(viewHolder.imgPlaylist);

        viewHolder.txtLvTitlePlaylist.setText(playlist.getTen());

        return convertView;
    }
}
