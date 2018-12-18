package com.group7.musicappproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.group7.musicappproject.Activity.ListChudeActivity;
import com.group7.musicappproject.Activity.ListSongActivity;
import com.group7.musicappproject.Model.ChuDe;
import com.group7.musicappproject.Model.TheLoai;
import com.group7.musicappproject.Model.TheLoaiToday;
import com.group7.musicappproject.R;
import com.group7.musicappproject.Service.APIService;
import com.group7.musicappproject.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai_Today extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtXemThemChuDeTheLoai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalSV);
        txtXemThemChuDeTheLoai = view.findViewById(R.id.txtXemThem);
        txtXemThemChuDeTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListChudeActivity.class);
                startActivity(intent);
            }
        });
        GetData();

        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<TheLoaiToday> callback = dataService.getCategoryMusic();
        callback.enqueue(new Callback<TheLoaiToday>() {
            @Override
            public void onResponse(Call<TheLoaiToday> call, Response<TheLoaiToday> response) {
                TheLoaiToday theLoaiToday = response.body();
                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(theLoaiToday.getChuDe());

                final  ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(theLoaiToday.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580,250);
                layoutParams.setMargins(10, 20, 10, 30);
                for (int i = 0; i < chuDeArrayList.size(); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType((ImageView.ScaleType.FIT_XY));
                    if (chuDeArrayList.get(i).getHinhChuDe() != null) {
                        Picasso.with(getActivity())
                                .load(chuDeArrayList.get(i).getHinhChuDe())
                                .into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }

                for (int j = 0; j < chuDeArrayList.size(); j++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType((ImageView.ScaleType.FIT_XY));
                    if (theLoaiArrayList.get(j).getHinhTheLoai() != null) {
                        Picasso.with(getActivity())
                                .load(theLoaiArrayList.get(j).getHinhTheLoai())
                                .into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListSongActivity.class);
                            intent.putExtra("theLoai", theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }

                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<TheLoaiToday> call, Throwable t) {

            }
        });
    }
}
