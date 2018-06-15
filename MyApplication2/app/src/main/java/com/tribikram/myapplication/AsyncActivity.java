package com.tribikram.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AsyncActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        progressBar = findViewById(R.id.progressbar);

        NetworkTask task = new NetworkTask();
        task.execute(100, 101, 102, 103, 104);

    }

    private class NetworkTask extends AsyncTask<Integer, Void, Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            int sum = 0;
            for (int i = 0; i < params.length;i ++){
                Log.d("legend"," " + params[i]);
                sum += params[i];
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sum;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            Toast.makeText(AsyncActivity.this, "sum: " + result , Toast.LENGTH_LONG).show();
        }
    }
}
