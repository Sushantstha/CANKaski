package com.tribikram.myapplication.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.tribikram.myapplication.R;

import java.util.ArrayList;

public class EightActivity extends AppCompatActivity {
    private ActionMode actionMode;
    private boolean isActionMode = false;
    private CustomAdapter adapter;
    private ArrayList<String> selectedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight);

        ArrayList<String> fruitList = createArrayList();
        selectedList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CustomAdapter(this, fruitList);
        recyclerView.setAdapter(adapter);
    }

    ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.action_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.action_delete:
                    adapter.fruitList.removeAll(selectedList);
                    /*
                    for(String fruit1: selectedList) {
                        for (String fruit2 : adapter.fruitList) {
                            if (fruit1.equals(fruit2)) {
                                adapter.fruitList.remove(fruit2);
                            }
                        }
                    }*/

                    adapter.notifyDataSetChanged();
                    actionMode.finish();
                    return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            isActionMode = false;
            selectedList.clear();
            adapter.notifyDataSetChanged();

        }
    };

    private ArrayList<String> createArrayList() {
        ArrayList<String> fruitList = new ArrayList<>();
        fruitList.add("Apple");
        fruitList.add("Banana");
        fruitList.add("Mango");
        fruitList.add("Pine Apple");
        fruitList.add("Water Melon");

        return fruitList;
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

        private Context context;
        private ArrayList<String> fruitList;
        private LayoutInflater inflater;

        public CustomAdapter(Context context, ArrayList<String> fruits) {
            this.context = context;
            this.fruitList = fruits;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.fruit_list_item, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            String fruit = fruitList.get(position);
            holder.tv_title.setText(fruit);

            if (isActionMode) {
                holder.checkbox.setVisibility(View.VISIBLE);
                if (selectedList.contains(fruit)) {
                    holder.checkbox.setChecked(true);
                } else {
                    holder.checkbox.setChecked(false);
                }

            } else {
                holder.checkbox.setVisibility(View.GONE);
            }

        }

        @Override
        public int getItemCount() {
            return fruitList.size();
        }

        //ViewHolder
        public class CustomViewHolder extends RecyclerView.ViewHolder {

            private TextView tv_title;
            private CheckBox checkbox;

            public CustomViewHolder(View itemView) {
                super(itemView);
                tv_title = itemView.findViewById(R.id.tv_title);
                checkbox = itemView.findViewById(R.id.checkbox);

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        if (!isActionMode) {
                            actionMode = startSupportActionMode(callback);
                            isActionMode = true;
                        }

                        String fruit = fruitList.get(getAdapterPosition());
                        selectedList.add(fruit);

                        actionMode.setTitle(String.valueOf(selectedList.size()));

                        notifyDataSetChanged();//data refresh i.e back to what it is
                        return true;
                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isActionMode) {
                            String fruit = fruitList.get(getAdapterPosition());
                            if (selectedList.contains(fruit)) {
                                selectedList.remove(fruit);
                            } else {
                                selectedList.add(fruit);
                            }
                            if (selectedList.size() == 0)
                                actionMode.finish();

                            actionMode.setTitle(String.valueOf(selectedList.size()));
                            notifyItemChanged(getAdapterPosition());
                        } else {
                            Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                checkbox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String fruit = fruitList.get(getAdapterPosition());
                        if(checkbox.isChecked()){
                            selectedList.add(fruit);
                        }else{
                            selectedList.remove(fruit);
                        }
                        actionMode.setTitle(String.valueOf(selectedList.size()));
                        notifyItemChanged(getAdapterPosition());
                    }
                });
            }
        }
    }
}
