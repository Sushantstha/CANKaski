package com.tribikram.myapplication.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tribikram.myapplication.Fragment.FirstFragment;
import com.tribikram.myapplication.Fragment.SecondFragment;
import com.tribikram.myapplication.Fragment.ThirdFragment;
import com.tribikram.myapplication.R;

public class SeventhActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteraction{

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh);

        fragmentManager = getSupportFragmentManager();

        FirstFragment firstFragment = new FirstFragment();
        fragmentManager.beginTransaction().add(android.R.id.content, firstFragment).commit();
    }

    @Override
    public void onClick(String data) {
        String s = "hello";
        ThirdFragment thirdfragment = ThirdFragment.newInstance(data, s);
        fragmentManager.beginTransaction().replace(android.R.id.content, thirdfragment).addToBackStack(null).commit();
    }
}
