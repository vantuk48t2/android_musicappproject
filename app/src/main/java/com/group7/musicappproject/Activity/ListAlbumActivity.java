package com.group7.musicappproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.group7.musicappproject.Adapter.DanhSachTatCaAlbumAdapter;
import com.group7.musicappproject.Model.Album;
import com.group7.musicappproject.R;
import com.group7.musicappproject.Service.APIService;
import com.group7.musicappproject.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAlbumActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewAlbum;
    DanhSachTatCaAlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_album);
        map();
        getData();
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.getTatCaAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                albumAdapter = new DanhSachTatCaAlbumAdapter(ListAlbumActivity.this, albumArrayList);
                recyclerViewAlbum.setLayoutManager(new GridLayoutManager(ListAlbumActivity.this, 2));
                recyclerViewAlbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void map() {
        toolbar = findViewById(R.id.toolBarAlbum);
        recyclerViewAlbum = findViewById(R.id.recylerViewTatCaAlbum);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Các Album");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
