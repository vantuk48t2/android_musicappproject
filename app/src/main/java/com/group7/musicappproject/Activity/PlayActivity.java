package com.group7.musicappproject.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.group7.musicappproject.Adapter.ViewPagerPlayNhac;
import com.group7.musicappproject.Fragment.Fragment_Dia_Nhac;
import com.group7.musicappproject.Fragment.Fragment_Play_Danh_Sach_Bai_Hat;
import com.group7.musicappproject.Model.BaiHat;
import com.group7.musicappproject.R;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtTime, txtTotalTime;
    SeekBar seekBarTime;
    ImageButton btnPlay, btnNext, btnPre, btnRepeat, btnRandom;
    ViewPager viewPagerPlay;
    public static ArrayList<BaiHat> baiHatArrayList = new ArrayList<>();
    public static ViewPagerPlayNhac adapterNhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Bai_Hat fragment_play_danh_sach_bai_hat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        map();
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        baiHatArrayList.clear();
        if (intent == null) return;
        if (intent.hasExtra("baiHat")) {
            BaiHat baiHat = intent.getParcelableExtra("baiHat");
            baiHatArrayList.add(baiHat);
        }
        if (intent.hasExtra("arrayBaiHat")) {
            ArrayList<BaiHat> _baiHatArrayList = intent.getParcelableArrayListExtra("arrayBaiHat");
            baiHatArrayList = _baiHatArrayList;
        }
    }

    private void map() {
        toolbar = findViewById(R.id.toolBarPlayNhac);
        txtTime = findViewById(R.id.txtTime);
        txtTotalTime = findViewById(R.id.txtTotalTime);
        seekBarTime = findViewById(R.id.seekBarSong);
        btnPlay = findViewById(R.id.btnPlay);
        btnNext = findViewById(R.id.btnNext);
        btnPre = findViewById(R.id.btnPrevious);
        btnRandom = findViewById(R.id.btnSuffle);
        btnRepeat = findViewById(R.id.btnRepeat);
        viewPagerPlay = findViewById(R.id.viewpagerPlay);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);
        adapterNhac = new ViewPagerPlayNhac(getSupportFragmentManager());
        fragment_play_danh_sach_bai_hat = new Fragment_Play_Danh_Sach_Bai_Hat();
        adapterNhac.addFragment(fragment_play_danh_sach_bai_hat);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        adapterNhac.addFragment(fragment_dia_nhac);
        viewPagerPlay.setAdapter(adapterNhac);
    }
}
