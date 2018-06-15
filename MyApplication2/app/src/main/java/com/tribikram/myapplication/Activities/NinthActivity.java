package com.tribikram.myapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tribikram.myapplication.R;
import com.tribikram.myapplication.models.MusicVideo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NinthActivity extends AppCompatActivity {

    private MusicAdapter adapter;
    private ActionMode actionMode;
    private boolean isActionMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninth);

        ArrayList<MusicVideo> MusicList = createMusicList();
        RecyclerView recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MusicAdapter(this, MusicList);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<MusicVideo> createMusicList() {
        ArrayList<MusicVideo> MusicList = new ArrayList<>();

        MusicVideo mv1 = new MusicVideo();
        mv1.setTitle("Despacito");
        mv1.setArtist("Louis");
        mv1.setAlbum("Jpt");
        mv1.setLength(5.0);

        MusicVideo mv2 = new MusicVideo("Kutu ma kutu", "Kali", "2 rupees", 5.30);
        MusicVideo mv3 = new MusicVideo("Kutu ma kutu", "Kali", "2 rupees", 5.30);

        MusicList.add(mv1);
        MusicList.add(mv2);
        MusicList.add(mv3);

        return MusicList;
    }

    ActionMode.Callback actionCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.action_menu, menu);
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
                   /* Log.d("legend","delete clicked" + adapter.selectedPosition);*/
                    int position = adapter.selectedPosition;

                    adapter.MusicList.remove(position);
                    adapter.notifyItemRemoved(position);

                    actionMode.finish();
                    return true;

                case R.id.action_edit:
                   /* Log.d("legend","edit clicked" + adapter.selectedPosition);*/

                    return true;
                default:
                    return false;

            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            adapter.selectedPosition = -1;
            adapter.notifyDataSetChanged();
            isActionMode = false;

        }
    };

    public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.CustomViewHolder> {

        private Context context;
        private ArrayList<MusicVideo> MusicList;
        private LayoutInflater inflater;
        private int selectedPosition = -1;

        public MusicAdapter(Context context, ArrayList<MusicVideo> Musics) {
            this.context = context;
            this.MusicList = Musics;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.music_item_list, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            MusicVideo music = MusicList.get(position);

            if (position == selectedPosition) {
                /*holder.tv_title.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));*/
                holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            } else {
                holder.itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.list_background));
                /*holder.tv_title.setTextColor(ContextCompat.getColor(context,R.color.black));*/
            }
            holder.tv_title.setText(music.getTitle());
            holder.tv_album.setText(music.getAlbum());


        }

        @Override
        public int getItemCount() {
            return MusicList.size();
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {

            private TextView tv_title, tv_album;
            private ImageButton imageButton;


            public CustomViewHolder(View itemView) {
                super(itemView);
                tv_title = itemView.findViewById(R.id.tv_title);
                tv_album = itemView.findViewById(R.id.tv_album);
                imageButton = itemView.findViewById(R.id.imageButton);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        MusicVideo music = MusicList.get(position);

                        Intent intent = new Intent(context, TenthActivity.class);
                        intent.putExtra("Music", music);
                       /* intent.putExtra("Tit1le", music.getTitle());
                        intent.putExtra("Album", music.getAlbum());
                        intent.putExtra("Length", music.getLength());*/
                        startActivity(intent);
                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        if (!isActionMode) {
                            isActionMode = true;
                            actionMode = startSupportActionMode(actionCallback);
                            selectedPosition = getAdapterPosition();
                            notifyItemChanged(getAdapterPosition());
                        }

                        return true;
                    }
                });

                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "Hello ", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

}