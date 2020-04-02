package com.example.wednesdaytask;

import com.example.wednesdaytask.Models.ArtistSongs;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search")
    Call<ArtistSongs> getArtist(
            @Query("term") String term,
            @Query("limit") String limit
    );

}
