package com.example.ethionewapp.retrofit;

import java.time.chrono.JapaneseDate;
import java.util.List;

public class NewsArrayData {

    private List<NewsData> data;

    public NewsArrayData(List<NewsData> data){
        this.data = data;

    }

    public List<NewsData> getData(){
        return data;
    }

}
