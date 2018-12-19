package com.group7.musicappproject.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

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

    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkRandom = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getData();
        map();
        event_click();
    }

    private void event_click() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapterNhac.getItem(1) != null) {
                    if (baiHatArrayList.size() > 0) {
                        fragment_dia_nhac.setImg(baiHatArrayList.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 500);
                    }
                }
            }
        }, 500);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.iconplay);
                } else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!repeat) {
                    if (checkRandom){
                        checkRandom = !checkRandom;
                        btnRandom.setImageResource(R.drawable.iconsuffle);
                    }
                    btnRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = !repeat;
                } else {
                   btnRepeat.setImageResource(R.drawable.iconrepeat);
                   repeat = !repeat;
                }
            }
        });
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkRandom) {
                    if (repeat){
                        repeat = !repeat;
                        btnRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    btnRandom.setImageResource(R.drawable.iconshuffled);
                    checkRandom = !checkRandom;
                } else {
                    btnRandom.setImageResource(R.drawable.iconsuffle);
                    checkRandom = !checkRandom;
                }
            }
        });
        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiHatArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < baiHatArrayList.size()){
                        btnPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat) {
                            if (position == 0){
                                position = baiHatArrayList.size();
                            }
                            position -= 1;
                        }
                        if (checkRandom) {
                            Random random = new Random();
                            int index = random.nextInt(baiHatArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > baiHatArrayList.size() - 1){
                            position = 0;
                        }
                        new PlayMp3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                        fragment_dia_nhac.setImg(baiHatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                        updateTime();
                    }
                }
                btnPre.setClickable(false);
                btnNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnPre.setClickable(true);
                        btnNext.setClickable(true);
                        handler.removeCallbacks(this);
                    }
                }, 3000);
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiHatArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < baiHatArrayList.size()){
                        btnPlay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position < 0) {
                            position = baiHatArrayList.size() - 1;
                        }
                        if (repeat) {
                            position += 1;
                        }
                        if (checkRandom) {
                            Random random = new Random();
                            int index = random.nextInt(baiHatArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                        fragment_dia_nhac.setImg(baiHatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                        updateTime();
                    }
                }
                btnPre.setClickable(false);
                btnNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnPre.setClickable(true);
                        btnNext.setClickable(true);
                        handler.removeCallbacks(this);
                    }
                }, 3000);
            }
        });
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
                mediaPlayer.stop();
                baiHatArrayList.clear();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);
        adapterNhac = new ViewPagerPlayNhac(getSupportFragmentManager());
        fragment_play_danh_sach_bai_hat = new Fragment_Play_Danh_Sach_Bai_Hat();
        adapterNhac.addFragment(fragment_play_danh_sach_bai_hat);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        adapterNhac.addFragment(fragment_dia_nhac);
        viewPagerPlay.setAdapter(adapterNhac);
        fragment_dia_nhac = (Fragment_Dia_Nhac) adapterNhac.getItem(1);
        if (baiHatArrayList.size() > 0) {
            getSupportActionBar().setTitle(baiHatArrayList.get(0).getTenBaiHat());
            new PlayMp3().execute(baiHatArrayList.get(0).getLinkBaiHat());
            btnPlay.setImageResource(R.drawable.iconpause);
        }
    }

    class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baiHat) {
            super.onPostExecute(baiHat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baiHat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            updateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarTime.setMax(mediaPlayer.getDuration());
    }

    private  void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    seekBarTime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next){
                    if (baiHatArrayList.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null){
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        if (position < baiHatArrayList.size()){
                            btnPlay.setImageResource(R.drawable.iconpause);
                            position++;
                            if (repeat) {
                                if (position == 0){
                                    position = baiHatArrayList.size();
                                }
                                position -= 1;
                            }
                            if (checkRandom) {
                                Random random = new Random();
                                int index = random.nextInt(baiHatArrayList.size());
                                if (index == position) {
                                    position = index - 1;
                                }
                                position = index;
                            }
                            if (position > baiHatArrayList.size() - 1){
                                position = 0;
                            }
                            new PlayMp3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                            fragment_dia_nhac.setImg(baiHatArrayList.get(position).getHinhBaiHat());
                            getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                        }
                    }
                    btnPre.setClickable(false);
                    btnNext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnPre.setClickable(true);
                            btnNext.setClickable(true);
                            handler.removeCallbacks(this);
                        }
                    }, 3000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
