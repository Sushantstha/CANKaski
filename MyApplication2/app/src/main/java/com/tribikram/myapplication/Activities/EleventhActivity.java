package com.tribikram.myapplication.Activities;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tribikram.myapplication.R;

import java.util.ArrayList;

public class EleventhActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleventh);


        SearchView searchView = findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(false);

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_layout);
        final RecyclerView recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> animalList = createAnimalList();
        final AnimalAdapter adapter = new AnimalAdapter(this, animalList);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("legend", query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                new CountDownTimer(3000, 1000) {

                    @Override
                    public void onTick(long l) {
                        Log.d("legend", "Hellooo");
                    }

                    @Override
                    public void onFinish() {
                        ArrayList<String> animalList = refreshAnimalList();
                        AnimalAdapter adapter = new AnimalAdapter(EleventhActivity.this, animalList);
                        recyclerView.setAdapter(adapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }.start();
            }
        });

    }

    private ArrayList<String> createAnimalList() {
        ArrayList<String> animalList = new ArrayList<>();

        animalList.add("Cat");
        animalList.add("Dog");
        animalList.add("Cow");
        animalList.add("Donkey");

        return animalList;
    }

    private ArrayList<String> refreshAnimalList() {
        ArrayList<String> animalList = new ArrayList<>();

        animalList.add("Cat");
        animalList.add("Dog");
        animalList.add("Cow");
        animalList.add("Donkey");
        animalList.add("Monkey");
        animalList.add("Elephant");

        return animalList;
    }

    public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

        private Context context;
        private ArrayList<String> animalList;
        private ArrayList<String> filteredList;

        public AnimalAdapter(Context context, ArrayList<String> animals) {
            this.context = context;
            this.animalList = animals;
            filteredList = new ArrayList<>();
            filteredList.addAll(animals);
        }

        public void filter(String searchQuery) {
            filteredList.clear();

            if (searchQuery.isEmpty()) {
                filteredList.addAll(animalList);
            } else {

                for (String animal : animalList) {
                    if (animal.toLowerCase().contains(searchQuery.toLowerCase())) {
                        filteredList.add(animal);
                    }

                }
            }
            notifyDataSetChanged();
        }

        @Override
        public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new AnimalViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AnimalViewHolder holder, int position) {
            String animal = filteredList.get(position);
            holder.tv_title.setText(animal);

        }

        @Override
        public int getItemCount() {
            return filteredList.size();
        }

        public class AnimalViewHolder extends RecyclerView.ViewHolder {

            private TextView tv_title;

            public AnimalViewHolder(View itemView) {
                super(itemView);
                tv_title = itemView.findViewById(R.id.tv_title);
            }
        }
    }
}
