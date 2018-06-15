package com.tribikram.myapplication.Activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tribikram.myapplication.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileStorageActivity extends AppCompatActivity {

    private static final int STORAGE_REQUEST_CODE = 100;
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_storage);
        tv_content = findViewById(R.id.tv_content);
    }

    public void writeFile(View view) {
        String fileName = "myFile";
        String content = "Hello, this is my first file.";

        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCacheFile(View view) {
        String fileName = "myCacheFile";
        String content = "Hello, this is my first cache file.";
        try {
            File cacheFile = File.createTempFile(fileName, null, getCacheDir());

            FileOutputStream outputStream = new FileOutputStream(cacheFile);
            outputStream.write(content.getBytes());
            outputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readFile(View view) {
        String fileName = "myFile";

        try {
            FileInputStream inputStream = openFileInput(fileName);

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            inputStream.close();
            inputStreamReader.close();
            reader.close();

            String content = sb.toString();
            tv_content.setText(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeExternalStorage(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                writeFile();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_REQUEST_CODE);
            }
        } else {
            writeFile();
        }

    }


    private void writeFile() {
        String fileName = "myFile.txt";
        String content = "Hello, this is my first external file.";

        //create a folder inside documents
       /* File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) +
                "/AndroidCourse");
        if (!directory.mkdirs()) {
            Log.d("legend", "Directory not created");
        }*/

        File directory = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/AndroidCourse");
        if (!directory.mkdirs()) {
            Log.d("legend", "Directory not created");
            //create file inside the created folder
            File file = new File(directory, fileName);

            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(content.getBytes());
                outputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                writeFile();
            } else {
                Toast.makeText(this, "User Denied Permission", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void readExternalFile(View view){
        String fileName = "myFile.txt";
       // String contents = "Hello, this is my first external file.";

        File directory = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/AndroidCourse");
        File file = new File(directory,fileName);

        try {
            FileInputStream inputStream = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            inputStream.close();
            inputStreamReader.close();
            reader.close();

            String content = sb.toString();
            tv_content.setText(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

