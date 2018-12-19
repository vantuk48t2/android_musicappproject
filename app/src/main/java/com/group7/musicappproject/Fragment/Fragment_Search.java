package com.group7.musicappproject.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group7.musicappproject.Adapter.SearchBaiHatAdapter;
import com.group7.musicappproject.Model.BaiHat;
import com.group7.musicappproject.R;
import com.group7.musicappproject.Service.APIService;
import com.group7.musicappproject.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Search extends Fragment {
    View view;
    Toolbar toolbar;
    RecyclerView recyclerViewSearchBaiHat;
    TextView txtKhongCoData;
    SearchBaiHatAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        toolbar = view.findViewById(R.id.toolBarSearchBaiHat);
        recyclerViewSearchBaiHat = view.findViewById(R.id.recyclerViewSearchBaiHat);
        txtKhongCoData = view.findViewById(R.id.txtKhongCoData);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchBaiHatTheoTuKhoa(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void searchBaiHatTheoTuKhoa(String key) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.timKiemBaiHat(key);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList = (ArrayList<BaiHat>) response.body();
                if (baiHatArrayList.size() > 0) {
                    adapter = new SearchBaiHatAdapter(getActivity(), baiHatArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewSearchBaiHat.setLayoutManager(linearLayoutManager);
                    recyclerViewSearchBaiHat.setAdapter(adapter);
                    txtKhongCoData.setVisibility(View.GONE);
                    recyclerViewSearchBaiHat.setVisibility(View.VISIBLE);
                } else {
                    txtKhongCoData.setVisibility(View.VISIBLE);
                    recyclerViewSearchBaiHat.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
