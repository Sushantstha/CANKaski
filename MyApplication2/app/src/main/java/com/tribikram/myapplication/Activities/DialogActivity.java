package com.tribikram.myapplication.Activities;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tribikram.myapplication.Fragment.CustomDialogFragment;
import com.tribikram.myapplication.Fragment.DatePickerFragment;
import com.tribikram.myapplication.R;

public class DialogActivity extends AppCompatActivity implements DatePickerFragment.DateSetlistener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   Log.d("legend","hello");*/


                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure; You want to delete this item?");
                builder.setCancelable(false);

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("legend","Delete clicked");
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("legend", "Cancel Clicked");
                    }
                });

                builder.setNeutralButton("More", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("legend", "More Clicked");
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final String[] colors = {"Red", "Green", "Blue"};

                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
                builder.setTitle("Pick a color").setMultiChoiceItems(colors, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        String color = colors[i];
                        Log.d("legend", color);
                    }
                });
              /*  builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String color = colors[i];
                        Log.d("legend", color);
                    }
                });*/
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
            }
        });

    }
    public  void showDialogFragment(View view){
        CustomDialogFragment fragment = new CustomDialogFragment();
        FragmentManager fm = getSupportFragmentManager();
        fragment.show(fm , "CustomDialog");
    }

    public void showDatePicker(View view){
        DatePickerFragment fragment = new DatePickerFragment();
        FragmentManager fm = getSupportFragmentManager();
        fragment.show(fm, "DatePicker");
    }


    @Override
    public void onDatePicked(int year, int month, int day) {
        Log.d("legend", year + "-" + month + "-" + day);
    }
}
