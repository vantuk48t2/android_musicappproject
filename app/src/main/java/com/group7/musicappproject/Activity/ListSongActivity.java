package com.group7.musicappproject.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.group7.musicappproject.Adapter.DanhSachBaiHatAdapter;
import com.group7.musicappproject.Model.Album;
import com.group7.musicappproject.Model.BaiHat;
import com.group7.musicappproject.Model.Playlist;
import com.group7.musicappproject.Model.QuangCao;
import com.group7.musicappproject.Model.TheLoai;
import com.group7.musicappproject.R;
import com.group7.musicappproject.Service.APIService;
import com.group7.musicappproject.Service.DataService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongActivity extends AppCompatActivity {
    QuangCao quangCao;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachBaiHat;
    FloatingActionButton floatingActionButton;
    ImageView imDanhSachCaKhuc;
    ArrayList<BaiHat> baiHatArrayList;
    Playlist playlist;
    TheLoai theLoai;
    Album album;
    DanhSachBaiHatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        getDataFromIntent();
        map();
        init();
        if (quangCao != null && !quangCao.getTenbaihat().equals("")) {
            setValueInView(quangCao.getTenbaihat(), quangCao.getHinhbaihat());
            GetDataBanner(quangCao.getIdQuangCao());
        }
        if (playlist != null && !playlist.getTen().equals("")) {
            setValueInView(playlist.getTen(), playlist.getHinhNen());
            GetDataPlaylist(playlist.getIdPlaylist());
        }
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setValueInView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
        }
        if (album != null && !album.getTenAlbum().equals("")) {
            setValueInView(album.getTenAlbum(), album.getHinhAlbum());
            GetDatAlbum(album.getIdAlbum());
        }


    }

    private void GetDatAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.getDanhSachBaiHatTheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList = (ArrayList<BaiHat>) response.body();
                setAdapter();
                event_click();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataTheLoai(String idTheLoai) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.getDanhSachBaiHatTheoTheLoai(idTheLoai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList = (ArrayList<BaiHat>) response.body();
                setAdapter();
                event_click();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist(String idPlaylist) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.getDanhSachBaiHatTheoPlaylist(idPlaylist);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList = (ArrayList<BaiHat>) response.body();
                setAdapter();
                event_click();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this)
                .load(hinh)
                .into(imDanhSachCaKhuc);
    }

    private void GetDataBanner(String idQuangCao) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.getDanhSachBaiHatTheoQuangCao(idQuangCao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList = (ArrayList<BaiHat>) response.body();
                setAdapter();
                event_click();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setAdapter() {
        adapter = new DanhSachBaiHatAdapter(ListSongActivity.this, baiHatArrayList);
        recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
        recyclerViewDanhSachBaiHat.setAdapter(adapter);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void map() {
        coordinatorLayout = findViewById(R.id.coordinator);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolBarDanhSach);
        recyclerViewDanhSachBaiHat = findViewById(R.id.recyclerViewDanhSachBaiHat);
        floatingActionButton = findViewById(R.id.floatingActionBtn);
        imDanhSachCaKhuc = findViewById(R.id.imgDanhSachcaKhuc);
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangCao = (QuangCao) intent.getSerializableExtra("banner");
            }
            if (intent.hasExtra("itemPlaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemPlaylist");
            }
            if (intent.hasExtra("theLoai")) {
                theLoai = (TheLoai) intent.getSerializableExtra("theLoai");
            }
            if (intent.hasExtra("album")) {
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }

    private void event_click() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListSongActivity.this, PlayActivity.class);
                intent.putExtra("arrayBaiHat", baiHatArrayList);
                startActivity(intent);
            }
        });
    }
}
