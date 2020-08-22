package com.example.githubrepositories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Button request;
    RecyclerView recyclerView;
    EditText username;
    JsonPlaceHolderAPI api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        request = (Button) findViewById(R.id.request);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        username = (EditText) findViewById(R.id.username);
        progressBar.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(JsonPlaceHolderAPI.class);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    private void makeRequest() {
        if (!TextUtils.isEmpty(username.getText())) {
            Call<List<Repo>> call = api.getRepos();

            call.enqueue(new Callback<List<Repo>>() {
                @Override
                public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        List<Repo> repoList = response.body();
                        RepoAdapter adapter=new RepoAdapter(MainActivity.this,repoList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.setAdapter(adapter);
                        //         result.append(content);
                    }
                }

                @Override
                public void onFailure(Call<List<Repo>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
