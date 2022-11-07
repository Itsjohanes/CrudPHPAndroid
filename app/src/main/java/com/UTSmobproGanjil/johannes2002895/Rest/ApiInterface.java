package com.UTSmobproGanjil.johannes2002895.Rest;

import com.UTSmobproGanjil.johannes2002895.Model.GetNotes;
import com.UTSmobproGanjil.johannes2002895.Model.PostPutDelNotes;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ApiInterface {

    //select
    @GET("restapinote.php")
    Call<GetNotes> getNotes(@Query("function") String function);

    //insert data
    @Multipart
    @POST("restapinote.php")
    Call<PostPutDelNotes> postNotes(@Query("function") String function,
                                    @Part("nama") RequestBody nama,
                                    @Part("kategori") RequestBody kategori,
                                    @Part("deskripsi") RequestBody deskripsi,
                                    @Part("ukuran") RequestBody ukuran);

    //Update
    @Multipart
    @POST("restapinote.php")
    Call<PostPutDelNotes> postUpdateNotes(@Query("function") String function,
                                          @Query("id") String id,
                                          @Part("nama") RequestBody nama,
                                          @Part("kategori") RequestBody kategori,
                                          @Part("deskripsi") RequestBody deskripsi,
                                          @Part("ukuran") RequestBody ukuran);

    //Delete data ke tabel melalui API
    @POST("restapinote.php")
    Call<PostPutDelNotes> deleteNotes(@Query("function") String function, @Query("id") String id);
}
