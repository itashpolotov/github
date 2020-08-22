package com.example.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderAPI {
      //  posts?userId=1

    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer []  id,

            @Query("_sort") String sortId,//=id
            @Query("_order") String order

            ); //defining a query with possibility to accept a random integer     //posts?userId=1


    @GET("posts")
    Call <List<Post>> getPosts();
}
