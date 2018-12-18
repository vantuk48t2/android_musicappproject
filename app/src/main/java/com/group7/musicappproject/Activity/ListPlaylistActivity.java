package com.group7.musicappproject.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.group7.musicappproject.Adapter.DanhSachPlaylistAdapter;
import com.group7.musicappproject.Model.Playlist;
import com.group7.musicappproject.R;
import com.group7.musicappproject.Service.APIService;
import com.group7.musicappproject.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPlaylistActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachPlaylist;
    DanhSachPlaylistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_playlist);
        map();
        init();
        getData();
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.getDanhSachPlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> playlistArrayList = (ArrayList<Playlist>) response.body();
                adapter = new DanhSachPlaylistAdapter(ListPlaylistActivity.this, playlistArrayList);
                recyclerViewDanhSachPlaylist.setLayoutManager(new GridLayoutManager(ListPlaylistActivity.this, 2));
                recyclerViewDanhSachPlaylist.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlist");
        toolbar.setTitleTextColor(getResources().getColor(R.color.purple));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void map() {
        toolbar = findViewById(R.id.toolBarDanhSachPlaylist);
        recyclerViewDanhSachPlaylist = findViewById(R.id.recyclerViewDanhSachPlaylist);
    }
}
