package com.tribikram.myapplication.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tribikram.myapplication.Fragment.FirstFragment;
import com.tribikram.myapplication.Fragment.SecondFragment;
import com.tribikram.myapplication.R;

public class FifthActivity extends AppCompatActivity {

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        Button button = findViewById(R.id.button);
        //get an object of fragment manager
        fm = getSupportFragmentManager();
        //create an object of first fragment
        FirstFragment firstFragment = new FirstFragment();
        //add fragment into activity
        fm.beginTransaction().add(R.id.fragment_container, firstFragment).commit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment displayfrag = fm.findFragmentByTag("first");

                if (displayfrag == null) {
                    FirstFragment firstFragment = new FirstFragment();
                    fm.beginTransaction().replace(R.id.fragment_container, firstFragment, "first").commit();
                } else {
                    SecondFragment secondFragment = new SecondFragment();
                    fm.beginTransaction().replace(R.id.fragment_container, secondFragment, "second").commit();
                }
                /*Fragment displayFragment = fm.findFragmentById(R.id.fragment_container);
                if (displayFragment instanceof FirstFragment) {
                    SecondFragment secondFragment = new SecondFragment();
                    fm.beginTransaction().replace(R.id.fragment_container, secondFragment).commit();
                } else {
                    FirstFragment firstFragment = new FirstFragment();
                    fm.beginTransaction().replace(R.id.fragment_container, firstFragment).commit();
                }
            }*/


            }

        });
    }


}
