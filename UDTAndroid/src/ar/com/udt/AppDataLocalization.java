package ar.com.udt;

import android.app.Application;
import android.content.SharedPreferences;
import ar.com.udt.config.AppUserData;


public class AppDataLocalization extends Application  {
    public static final String PREFS_NAME = "UTPREFERENCES";

    public void onCreate() {
       super.onCreate();
       SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
       AppUserData.getInstance().lastusername = settings.getString("lastusername","");
       AppUserData.getInstance().trackMyPos = settings.getBoolean("trackmypos",false);
       AppUserData.getInstance().frecuencia = settings.getInt("frecuencia", 10);
    }
    
    
    public void saveConfig(){
    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("lastusername", AppUserData.getInstance().lastusername);
        editor.putBoolean("trackmypos", AppUserData.getInstance().trackMyPos);
        editor.putInt("frecuencia", AppUserData.getInstance().frecuencia);
        // Commit the edits!
        editor.commit();
    }
            
}
