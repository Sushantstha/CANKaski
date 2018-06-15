package com.example.dell.practiceproject2;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dell.practiceproject2.Fragments.FirstFragment;
import com.example.dell.practiceproject2.Fragments.SecondFragment;

public class FragmentActivity extends AppCompatActivity implements FirstFragment.OnClickListener{

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        fm = getSupportFragmentManager();

        FirstFragment frag1 = new FirstFragment();
        fm.beginTransaction().add(R.id.container, frag1).commit();
    }

    @Override
    public void onClick(String data) {
        SecondFragment frag2 = SecondFragment.getInstance(data);
        fm.beginTransaction().replace(R.id.container, frag2, "Second").commit();
    }
}
