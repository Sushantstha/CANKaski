package com.example.sushantpc.cankaski;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void clickEvents(View view){
        Intent intent= new Intent(this, EventsActivity.class);
        startActivity(intent);
    }

    public void clickStalls(View view){
        Intent intent= new Intent(this, StallsActivity.class);
        startActivity(intent);
    }

    public void clickOffers(View view){
        Intent intent= new Intent(this, OffersActivity.class);
        startActivity(intent);
    }

    public void clickLocation(View view){
        Snackbar.make(view, "Under Construction", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
