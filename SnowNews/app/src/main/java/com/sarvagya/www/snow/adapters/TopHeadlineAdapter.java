package com.sarvagya.www.snow.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sarvagya.www.snow.R;
import com.sarvagya.www.snow.models.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TopHeadlineAdapter extends RecyclerView.Adapter<TopHeadlineAdapter.CustomViewHolder> {

    private ArrayList<News> newsArrayList;
    private Context context;
    private Picasso picasso;
    private LayoutInflater inflater;

    public TopHeadlineAdapter(Context context, ArrayList<News> newsArrayList, Picasso picasso){
        this.context = context;
        this.newsArrayList = newsArrayList;
        this.picasso = picasso;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.top_headline_list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        News news = newsArrayList.get(position);
        Log.d("checkNews", news.getStatus());

        holder.tvHeadLine.setText(news.getArticles().getTitle());

        picasso.load(news.getArticles().getUrl())
                .fit()
                .centerCrop()
                .into(holder.imgNews);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgNews;
        private TextView tvHeadLine;

        public CustomViewHolder(View itemView) {
            super(itemView);

            imgNews = itemView.findViewById(R.id.imgNews);
            tvHeadLine = itemView.findViewById(R.id.tvHeadLine);
        }
    }
}
