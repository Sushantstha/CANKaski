package com.tribikram.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tribikram.myapplication.models.Flower;

public class FlowersDetailsActivity extends AppCompatActivity {

    private TextView tv_1;
    private TextView tv_2;
    private TextView tv_3;
    private TextView tv_4;
    private ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowers_details);
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3= findViewById(R.id.tv_3);
        tv_4= findViewById(R.id.tv_4);
        img1 = findViewById(R.id.img1);

        Intent intent = getIntent();
        Flower flower = (Flower) intent.getSerializableExtra("Flower");

        tv_1.setText(flower.getCategory());
        tv_2.setText(String.valueOf(flower.getPrice()));
        tv_3.setText(flower.getName());
        tv_4.setText(flower.getInstructions());
        Picasso.get().load("http://services.hanselandpetal.com/photos/" + flower.getPhoto())
                .into(img1);
    }
}
