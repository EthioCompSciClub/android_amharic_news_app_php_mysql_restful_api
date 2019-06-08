package com.example.ethionewapp.retrofit;


import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {

    String BASE_URL = "http://ethiocompsciclub.000webhostapp.com";


    @GET("api.php")
    Call<NewsArrayData> getApiNewsData();
}
