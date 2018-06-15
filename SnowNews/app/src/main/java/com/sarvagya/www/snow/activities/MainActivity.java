package com.sarvagya.www.snow.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sarvagya.www.snow.R;
import com.sarvagya.www.snow.Utils.NetworkUtil;
import com.sarvagya.www.snow.adapters.TopHeadlineAdapter;
import com.sarvagya.www.snow.injection.components.DaggerMyAppComponent;
import com.sarvagya.www.snow.injection.components.MyAppComponent;
import com.sarvagya.www.snow.injection.modules.ContextModule;
import com.sarvagya.www.snow.models.News;
import com.sarvagya.www.snow.network.Api;
import com.sarvagya.www.snow.network.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView topRecyclerView;

    SpotsDialog dialog;

    @Inject
    ApiInterface apiInterface;

    @Inject
    Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        topRecyclerView = findViewById(R.id.topRecyclerView);
        topRecyclerView.setLayoutManager(linearLayoutManager);

        MyAppComponent component = DaggerMyAppComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build();

        //injecting apiInterface and picasso
        component.injectMainActivity(this);

        //checking the network connection
        if (NetworkUtil.isNetworkAvailable(this)){
            fetchTopHeadlineNews();
        }else {
            Toast.makeText(this, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
        }

    }

    private void fetchTopHeadlineNews() {
        showLoadingDialog();

        Call<ArrayList<News>> call = apiInterface.getNews(Api.topHeadlineUrl);
        call.enqueue(new Callback<ArrayList<News>>() {
            @Override
            public void onResponse(Call<ArrayList<News>> call, Response<ArrayList<News>> response) {

                if (response.isSuccessful()){
                    dialog.dismiss();
                    ArrayList<News> newsList = response.body();

                    TopHeadlineAdapter adapter = new TopHeadlineAdapter(MainActivity.this, newsList, picasso);
                }else {
                    Toast.makeText(MainActivity.this, "Error from the server!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<News>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void showLoadingDialog() {
        dialog = new SpotsDialog(MainActivity.this);
        dialog.show();
        dialog.setMessage("please wait...");
    }
}
