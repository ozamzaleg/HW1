package com.example.oz_tal_application_project.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MySP {

    public interface KEYS {
        public static final String KEY_TOP_TEN = "KEY_TOP_TEN";
        public static final String SWITCH = "KEY_SWITCH";
        public static final String NAME1 = "KEY_MAME1";
        public static final String NAME2 = "KEY_NAME2";
        public static final String MY_SP="MY_SP";
    }

    private static MySP instance;
    private SharedPreferences prefs;

    public static MySP getInstance() {
        return instance;
    }

    private MySP(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(KEYS.MY_SP, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new MySP(context);
        }
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String def) {
        return prefs.getString(key, def);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean def) {
        return prefs.getBoolean(key ,def);
    }

    public void removeKey(String key) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key).apply();
    }
}

