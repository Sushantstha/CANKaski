package com.tribikram.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tribikram on 5/7/2018.
 */

public class Prefs {

    public static final String PREF_NAME="user";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";

    private SharedPreferences preferences;

    public Prefs(Context context){

        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveName(String name){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_NAME, name);
        editor.apply();
    }

    public String getName(){
        return preferences.getString(KEY_NAME, "HELLO");
    }
}
