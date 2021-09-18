package com.example.mymoviedb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitInterface {

    @GET("discover/movie?api_key=8de6c835e2e410d41ca6241fe5eb9c29")
    Call<JsonRet> getAllMovies();

    @GET("search/movie?api_key=8de6c835e2e410d41ca6241fe5eb9c29")
    Call<JsonRet> getSearchedMovies(@Query("search") String search);

}


//    @GET("/discover/movie?sort_by=popularity.desc")
//    movie?sort_by=popularity.desc