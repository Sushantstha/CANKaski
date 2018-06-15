package com.sarvagya.www.snow.network;

import com.sarvagya.www.snow.models.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET
    Call<ArrayList<News>> getNews(@Url String url);
}
