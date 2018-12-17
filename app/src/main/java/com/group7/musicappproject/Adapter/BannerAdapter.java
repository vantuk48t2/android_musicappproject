package com.group7.musicappproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.group7.musicappproject.Activity.ListSongActivity;
import com.group7.musicappproject.Model.QuangCao;
import com.group7.musicappproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_banner, null);

        ImageView imgBackgroundBanner = view.findViewById(R.id.imgBackgroundBanner);
        ImageView imgSongBanner       = view.findViewById(R.id.imgBanner);
        TextView  txtTitleSongBanner  = view.findViewById(R.id.txtTitleBannerSong);
        TextView  txtContent          = view.findViewById(R.id.txtContent);

        Picasso.with(context)
                .load(arrayListBanner.get(position).getHinhanh())
                .into(imgBackgroundBanner);

        Picasso.with(context)
                .load(arrayListBanner.get(position).getHinhbaihat())
                .into(imgSongBanner);

        txtTitleSongBanner.setText(arrayListBanner.get(position).getTenbaihat());
        txtContent.setText(arrayListBanner.get(position).getNoidung());
        
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("banner", arrayListBanner.get(position));
                context.startActivity(intent);
            }
        });

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
