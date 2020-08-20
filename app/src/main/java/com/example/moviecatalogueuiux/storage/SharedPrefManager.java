package com.example.moviecatalogueuiux.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private Context context;

    private SharedPrefManager(Context context){
        this.context = context;
    }

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setSwitchDaily(Context context, boolean setting){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean("settingDaily", setting);
        editor.apply();
    }

    public static void setSwitchUpcoming(Context context, boolean setting){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean("settingUpcoming", setting);
        editor.apply();
    }

    public static Boolean getSwitchDaily(Context context){
        return getPreferences(context).getBoolean("settingDaily",false);
    }

    public static Boolean getSwitchUpcoming(Context context){
        return getPreferences(context).getBoolean("settingUpcoming", false);
    }
}
