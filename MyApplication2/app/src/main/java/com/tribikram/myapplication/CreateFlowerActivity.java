package com.tribikram.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import restClient.ApiClient;
import restClient.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateFlowerActivity extends AppCompatActivity {

    private EditText et_name, et_price, et_category, et_instruction;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flower);

        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);

        et_name = findViewById(R.id.et_name);
        et_price = findViewById(R.id.et_price);
        et_category = findViewById(R.id.et_category);
        et_instruction = findViewById(R.id.et_instruction);
        progressBar = findViewById(R.id.progressbar);
    }

    public void createFlower(View view) {
        String name = et_name.getText().toString();
        String category = et_category.getText().toString();
        String instructions = et_instruction.getText().toString();
        float price = Float.parseFloat(et_price.getText().toString());

        progressBar.setVisibility(View.VISIBLE);
        Call<String> call = apiInterface.createFlower(name, category, instructions, price);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()){
                    String msg = response.body();
                    Log.d("legend", msg);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(CreateFlowerActivity.this, t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}

