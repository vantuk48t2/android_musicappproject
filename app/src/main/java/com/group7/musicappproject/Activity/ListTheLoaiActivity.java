package com.group7.musicappproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.group7.musicappproject.Adapter.DanhSachTheLoaiAdapter;
import com.group7.musicappproject.Model.ChuDe;
import com.group7.musicappproject.Model.TheLoai;
import com.group7.musicappproject.R;
import com.group7.musicappproject.Service.APIService;
import com.group7.musicappproject.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTheLoaiActivity extends AppCompatActivity {

    ChuDe chuDe;
    Toolbar toolbar;
    RecyclerView recyclerViewTheLoai;
    DanhSachTheLoaiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_the_loai);
        getIntentFromChuDe();
        map();
        getData();
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> callback = dataService.getTheLoaiTheoChuDe(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> theLoaiArrayList = (ArrayList<TheLoai>) response.body();
                adapter = new DanhSachTheLoaiAdapter(ListTheLoaiActivity.this, theLoaiArrayList);
                recyclerViewTheLoai.setLayoutManager(new GridLayoutManager(ListTheLoaiActivity.this, 2));
                recyclerViewTheLoai.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void map() {
        toolbar = findViewById(R.id.toolBarTheLoai);
        recyclerViewTheLoai = findViewById(R.id.recyclerViewDanhSachTheLoai);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getIntentFromChuDe() {
        Intent intent = getIntent();
        if (intent.hasExtra("chuDe")) {
            chuDe = (ChuDe) intent.getSerializableExtra("chuDe");
        }
    }
}
