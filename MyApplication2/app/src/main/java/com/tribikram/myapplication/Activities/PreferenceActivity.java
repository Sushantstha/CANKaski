package com.tribikram.myapplication.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tribikram.myapplication.R;
import com.tribikram.myapplication.utils.Prefs;

public class PreferenceActivity extends AppCompatActivity {

    private Prefs prefs;

    private TextView tv_name;
    private EditText editText;
    private  Button button;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        prefs = new Prefs(this);

        tv_name = findViewById(R.id.tv_name);
        editText = findViewById(R.id.edittext);
        button = findViewById(R.id.button);

       String name = prefs.getName();
        tv_name.setText(name);
    }

    public void saveToPreference(View view){
        String name = editText.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
            prefs.saveName(name);
            tv_name.setText(prefs.getName());
        }
    }
}
