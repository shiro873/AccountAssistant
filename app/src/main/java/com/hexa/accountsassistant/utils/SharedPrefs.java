package com.hexa.accountsassistant.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private static SharedPrefs prefs;
    private SharedPreferences sharedPreferences;

    public static SharedPrefs getInstance(Context context) {
        if (prefs == null) {
            prefs = new SharedPrefs(context);
        }
        return prefs;
    }

    private SharedPrefs(Context context) {
        sharedPreferences = context.getSharedPreferences("AssistantPrefs",Context.MODE_PRIVATE);
    }

    public void saveData(String key,String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor .putString(key, value);
        prefsEditor.commit();
    }

    public String getData(String key) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }
}
