package com.example.ethionewapp;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.ethionewapp.retrofit.NewsApi;
import com.example.ethionewapp.retrofit.NewsArrayData;
import com.example.ethionewapp.retrofit.NewsData;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsAdapater newsAdapater;
    private OkHttpClient okHttpClient;
    private NewsApi newsApi;
    private TextView second_text;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        second_text = findViewById(R.id.second_layout_text);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        newsApi = retrofit.create(NewsApi.class);
        colleteData(newsApi);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                colleteData(newsApi);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void colleteData(NewsApi newsApi){
        Call<NewsArrayData> collectNewsData = newsApi.getApiNewsData();

        collectNewsData.enqueue(new Callback<NewsArrayData>() {
            @Override
            public void onResponse(Call<NewsArrayData> call, Response<NewsArrayData> response) {
                NewsArrayData data = response.body();
                List<NewsData> d = data.getData();

                second_text.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                newsAdapater = new NewsAdapater(MainActivity.this, d);
                recyclerView.setAdapter(newsAdapater);
            }

            @Override
            public void onFailure(Call<NewsArrayData> call, Throwable t) {
                second_text.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        });
    }
}
