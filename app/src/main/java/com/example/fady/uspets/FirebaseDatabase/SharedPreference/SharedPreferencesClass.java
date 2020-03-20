package com.example.fady.uspets.FirebaseDatabase.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPreferencesClass {
    public final String SHARED_PREF_NAME = "PET_SHARED_PREF";
    public static final String USERNAME_PREF = "userName";
    public static final String PASSWORD_PREF = "userPassword";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Inject
    public SharedPreferencesClass(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public void putStringInPref(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringFromPref(String key) {
        return sharedPreferences.getString(key, "");
    }
}
