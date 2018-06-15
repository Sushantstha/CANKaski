package com.tribikram.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.tribikram.myapplication.Activities.EleventhActivity;
import com.tribikram.myapplication.models.Flower;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NetworkActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        progressBar = findViewById(R.id.progressbar);

        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        URL url = null;
        try {
            url = new URL("http://services.hanselandpetal.com/feeds/flowers.json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new GetTask().execute(url);
    }

    private class GetTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];

            String result = " ";

            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                inputStream.close();
                inputStreamReader.close();
                reader.close();

                result = sb.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("legend", result);

            Gson gson = new Gson();
            ArrayList<Flower> flowers = gson.fromJson(result, new TypeToken<ArrayList<Flower>>(){}.getType());


            NetworkAdapter adapter = new NetworkAdapter(NetworkActivity.this, flowers);
            recyclerView.setAdapter(adapter);

            progressBar.setVisibility(View.GONE);
        }
    }

    public class NetworkAdapter extends RecyclerView.Adapter<NetworkAdapter.NetworkViewHolder>{

        private Context context;
        private ArrayList<Flower> flowerList;


        public NetworkAdapter(Context context, ArrayList<Flower> flowerList) {
            this.context = context;
            this.flowerList = flowerList;

        }

        @Override
        public NetworkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.fruit_list_item,parent,false);
            return new NetworkViewHolder(view);
        }

        @Override
        public void onBindViewHolder(NetworkViewHolder holder, int position) {
            Flower flower = flowerList.get(position);
            holder.tv1.setText(flower.getCategory());
            holder.tv2.setText(flower.getInstructions());
            holder.tv3.setText(flower.getName());
            holder.tv4.setText("$ " + flower.getPrice());

            Picasso.get().load("http://services.hanselandpetal.com/photos/" + flower.getPhoto())
                    .resize(30,50)
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return flowerList.size();
        }

        public class NetworkViewHolder extends RecyclerView.ViewHolder{

            private TextView tv1,tv2,tv3,tv4;
            private ImageView imageView;

            public NetworkViewHolder(View itemView) {
                super(itemView);
                tv1 = itemView.findViewById(R.id.tv1);
                tv2 = itemView.findViewById(R.id.tv2);
                tv3 = itemView.findViewById(R.id.tv3);
                tv4 = itemView.findViewById(R.id.tv4);
                imageView = itemView.findViewById(R.id.imageView);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = getAdapterPosition();
                        Flower flower = flowerList.get(pos);

                        Intent intent= new Intent(NetworkActivity.this, FlowersDetailsActivity.class);
                        intent.putExtra("Flower", flower);
                        startActivity(intent);


                    }
                });
            }
        }
    }
}
