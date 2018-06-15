package com.tribikram.myapplication.Activities;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tribikram.myapplication.NetworkActivity;
import com.tribikram.myapplication.R;
import com.tribikram.myapplication.models.Flower;

import java.util.ArrayList;

import restClient.ApiClient;
import restClient.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);
        progressBar = findViewById(R.id.progressbar);

        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout = findViewById(R.id.swipe_id);

        getFlowers();




    }

   /* private void createFlower() {

        Flower flower = new Flower();
        flower.setName("Rose");
        flower.setCategory("Shurbs");
        flower.setPrice(10.0);
        flower.setInstructions("Hello");
        Call<Flower> call = apiInterface.createFlower(flower);
        call.enqueue(new Callback<Flower>() {
            @Override
            public void onResponse(Call<Flower> call, Response<Flower> response) {
                if(response.isSuccessful()){
                    Flower flower = response.body();
                }
            }

            @Override
            public void onFailure(Call<Flower> call, Throwable t) {

            }
        });*/


    private void getFlowers() {
        progressBar.setVisibility(View.VISIBLE);
        Call<ArrayList<Flower>> call = apiInterface.getFlowers();
        call.enqueue(new Callback<ArrayList<Flower>>() {
            @Override
            public void onResponse(Call<ArrayList<Flower>> call, Response<ArrayList<Flower>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    ArrayList<Flower> flowers = response.body();

                    NetworkAdapter adapter = new NetworkAdapter(RetrofitActivity.this, flowers);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Flower>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.d("legend", t.getLocalizedMessage());
            }
        });
    }

    public class NetworkAdapter extends RecyclerView.Adapter<NetworkAdapter.NetworkViewHolder> {

        private Context context;
        private ArrayList<Flower> flowerList;


        public NetworkAdapter(Context context, ArrayList<Flower> flowerList) {
            this.context = context;
            this.flowerList = flowerList;

        }

        @Override
        public NetworkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.fruit_list_item, parent, false);
            return new NetworkViewHolder(view);
        }

        @Override
        public void onBindViewHolder(NetworkAdapter.NetworkViewHolder holder, int position) {
            Flower flower = flowerList.get(position);
            holder.tv1.setText(flower.getCategory());
            holder.tv2.setText(flower.getInstructions());
            holder.tv3.setText(flower.getName());
            holder.tv4.setText("$ " + flower.getPrice());

            Picasso.get().load("http://services.hanselandpetal.com/photos/" + flower.getPhoto())
                    .resize(30, 50)
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return flowerList.size();
        }

        public class NetworkViewHolder extends RecyclerView.ViewHolder {

            private TextView tv1, tv2, tv3, tv4;
            private ImageView imageView;

            public NetworkViewHolder(View itemView) {
                super(itemView);
                tv1 = itemView.findViewById(R.id.tv1);
                tv2 = itemView.findViewById(R.id.tv2);
                tv3 = itemView.findViewById(R.id.tv3);
                tv4 = itemView.findViewById(R.id.tv4);
                imageView = itemView.findViewById(R.id.imageView);

            }
        }
    }
}
