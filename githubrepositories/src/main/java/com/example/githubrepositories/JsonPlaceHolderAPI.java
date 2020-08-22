package com.example.githubrepositories;



import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface JsonPlaceHolderAPI {
    @GET("timur21/repos")
    Call <List<Repo>> getRepos(); //передача username
}
