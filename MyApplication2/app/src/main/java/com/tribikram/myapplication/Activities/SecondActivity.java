package com.tribikram.myapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.tribikram.myapplication.R;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private ArrayList<String> fruitList;
    private DatePicker datePicker;
    private String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // taking reference from layout
        final EditText et_name = findViewById(R.id.et_name);
        final EditText et_password = findViewById(R.id.et_password);
        Button btn_save = findViewById(R.id.btn_save);
        final TextView tv_result = findViewById(R.id.tv_result);
        final ImageButton btn_image = findViewById(R.id.btn_image);
        Switch switch1 = findViewById(R.id.switch1);
        final Spinner spinner = findViewById(R.id.spinner);
        Button btn_display = findViewById(R.id.btn_display);
        final DatePicker datepicker = findViewById(R.id.date_picker);
        Button btn_display_date = findViewById(R.id.btn_display_date);

        switch1.setChecked(true);

        //set clicklistener in button
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String password = et_password.getText().toString();

                tv_result.setText(name + "" + password);
            }
        });


        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SecondActivity.this, "Image button is clicked", Toast.LENGTH_SHORT).show();

            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Toast.makeText(SecondActivity.this,""+ isChecked, Toast.LENGTH_SHORT).show();
                if (isChecked){
                    btn_image.setVisibility(View.VISIBLE);
                }else{
                    btn_image.setVisibility((View.INVISIBLE));
                }

            }
        });

        //Setting Spinner
        createFruitList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_spinner_dropdown_item,fruitList);
        spinner.setAdapter(adapter);
        spinner.setSelection(2,true);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String value = fruitList.get(position);
                Toast.makeText(SecondActivity.this, value, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //display value of spinner
        btn_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = (int) spinner.getSelectedItemId();
                String value = fruitList.get(index);
                //String value = (String) spinner.getSelectedItem();
                Toast.makeText(SecondActivity.this, value, Toast.LENGTH_LONG).show();
            }
        });

        //display selected date from datepicker
        btn_display_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = datepicker.getYear();
                int month = datepicker.getMonth();
                int day = datepicker.getDayOfMonth();


                Toast.makeText(SecondActivity.this, year + " " + monthArray[month] + " " + day,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void createFruitList() {
        fruitList = new ArrayList<>();
        fruitList.add("apple");
        fruitList.add("banana");
        fruitList.add("grapes");
        fruitList.add("water melon");
        fruitList.add("mango");

    }
}