package com.tribikram.myapplication.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tribikram.myapplication.Fragment.FirstFragment;
import com.tribikram.myapplication.Fragment.SecondFragment;
import com.tribikram.myapplication.R;

public class SixthActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteraction {
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);
        
        fm = getSupportFragmentManager();
        FirstFragment fragg = new FirstFragment();
        fm.beginTransaction().add(R.id.first_container, fragg, "first").commit();

        fm = getSupportFragmentManager();
        SecondFragment frag = new SecondFragment();
        fm.beginTransaction().add(R.id.second_container, frag,"second").commit();
    }
    @Override
    public void onClick(String data) {
      /*  Toast.makeText(this,data,Toast.LENGTH_LONG).show();*/

      SecondFragment frag = (SecondFragment) fm.findFragmentByTag("second");
      frag.setValue(data);
    }
}
