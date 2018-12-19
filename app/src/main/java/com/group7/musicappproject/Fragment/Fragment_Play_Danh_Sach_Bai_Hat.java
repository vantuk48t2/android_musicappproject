package com.group7.musicappproject.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group7.musicappproject.Activity.PlayActivity;
import com.group7.musicappproject.Adapter.PlayNhacAdapter;
import com.group7.musicappproject.R;

public class Fragment_Play_Danh_Sach_Bai_Hat extends Fragment {

    View view;
    RecyclerView recyclerViewPlay;
    PlayNhacAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danh_sach_bai_hat, container, false);
        recyclerViewPlay = view.findViewById(R.id.recylerViewPlayBaiHat);
        if (PlayActivity.baiHatArrayList.size() > 0) {
            adapter = new PlayNhacAdapter(getActivity(), PlayActivity.baiHatArrayList);
            recyclerViewPlay.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlay.setAdapter(adapter);
        }
        return view;
    }
}
