package com.group7.musicappproject.Service;

import com.group7.musicappproject.Model.Album;
import com.group7.musicappproject.Model.BaiHat;
import com.group7.musicappproject.Model.Playlist;
import com.group7.musicappproject.Model.QuangCao;
import com.group7.musicappproject.Model.TheLoaiToday;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("songbanner.php")
    Call<List<QuangCao>> getDataBanner();

    @GET("playlistforcurrentpage.php")
    Call<List<Playlist>> getPlaylistCurrentDay();

    @GET("theloai_chude.php")
    Call<TheLoaiToday> getCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> getAlbum();

    @GET("baihatyeuthich.php")
    Call<List<BaiHat>> getBaiHatYeuThich();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoQuangCao(@Field("idQuangCao") String idQuangCao);
}
