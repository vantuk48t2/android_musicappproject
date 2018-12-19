package com.group7.musicappproject.Service;

import com.group7.musicappproject.Model.Album;
import com.group7.musicappproject.Model.BaiHat;
import com.group7.musicappproject.Model.ChuDe;
import com.group7.musicappproject.Model.Playlist;
import com.group7.musicappproject.Model.QuangCao;
import com.group7.musicappproject.Model.TheLoai;
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

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoPlaylist(@Field("idPlaylist") String idPlaylist);

    @GET("danhsachplaylist.php")
    Call<List<Playlist>> getDanhSachPlaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoTheLoai(@Field("idTheLoai") String idTheLoai);

    @GET("danhsachchude.php")
    Call<List<ChuDe>> getTatCaChuDe();

    @FormUrlEncoded
    @POST("danhsachtheloaitrongchude.php")
    Call<List<TheLoai>> getTheLoaiTheoChuDe(@Field("idChuDe") String idChuDe);

    @GET("danhsachalbum.php")
    Call<List<Album>> getTatCaAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> getDanhSachBaiHatTheoAlbum(@Field("idAlbum") String idAlbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> updateLuotThich(@Field("luotThich") String luotThich, @Field("idBaiHat") String idBaiHat);
}
