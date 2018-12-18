package com.group7.musicappproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.group7.musicappproject.Adapter.DanhSachChuDeAdapter;
import com.group7.musicappproject.Model.ChuDe;
import com.group7.musicappproject.R;
import com.group7.musicappproject.Service.APIService;
import com.group7.musicappproject.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListChudeActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewChuDe;
    DanhSachChuDeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chude);
        map();
        getData();
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<ChuDe>> callback = dataService.getTatCaChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> chuDeArrayList = (ArrayList<ChuDe>) response.body();
                adapter = new DanhSachChuDeAdapter(ListChudeActivity.this, chuDeArrayList);
                recyclerViewChuDe.setLayoutManager(new GridLayoutManager(ListChudeActivity.this, 1));
                recyclerViewChuDe.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void map() {
        toolbar = findViewById(R.id.toolBarTatCaChuDe);
        recyclerViewChuDe = findViewById(R.id.recyclerViewDanhSachChuDe);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
