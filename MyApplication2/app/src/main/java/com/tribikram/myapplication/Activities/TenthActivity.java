package com.tribikram.myapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tribikram.myapplication.R;
import com.tribikram.myapplication.models.MusicVideo;

public class TenthActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_tenth);

        TextView tv_title = findViewById(R.id.tv_title);
        TextView tv_album = findViewById(R.id.tv_album);
        TextView tv_length = findViewById(R.id.tv_length);

        Intent intent = getIntent();
        MusicVideo music = (MusicVideo) intent.getSerializableExtra("Music");
        /*String title = intent.getStringExtra("Title");
        String album = intent.getStringExtra("Album");
        double length = intent.getDoubleExtra("Length", 0.0);
*/
        tv_title.setText(music.getTitle());
        tv_album.setText(music.getAlbum()+ "   " + music.getArtist());
        tv_length.setText("Length: "+ music.getLength());


    }
}
