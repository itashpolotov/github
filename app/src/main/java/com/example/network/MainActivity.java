package com.example.network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result=findViewById(R.id.result);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI api=retrofit.create(JsonPlaceHolderAPI.class);
        Call<List<Post>> request=api.getPosts(new Integer[]{1,3,5,7,10},null,null); 

        request.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    List<Post> posts=response.body();
                    for(Post post:posts){
                        String content="";
                        content+="ID: "+post.getId()+"\n";
                        content+="User ID: "+post.getUserId()+"\n";
                        content+="Title: "+post.getPostTitle()+"\n";
                        content+="Body: "+post.getPostText()+"\n";

                        result.append(content);


                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }

    ///Retrofit2

    //Object-Post
    //Mockup
    //Request ->List<Post>

    //Interface



    //HTTP
}