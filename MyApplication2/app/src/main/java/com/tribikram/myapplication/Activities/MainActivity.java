package com.tribikram.myapplication.Activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tribikram.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("legend", "inside onCreate");
        Button btn = findViewById(R.id.btn_call);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("legend","button is clicked");
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


 /*       Log.d("legend", "inside onCreate");*/
       /* add(2,3);
        mul(2,3);
    }
    public void add(int a, int b) {
        int c = a + b;

        Log.d("legend", "Add:" + c);

    }
        public void mul(int a, int b){
        int c = a * b;
        Log.d("legend", "Multiply: " + c);


    }*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("legend","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("legend","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("legend","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("legend","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("legend","onDestroy");
    }
}

