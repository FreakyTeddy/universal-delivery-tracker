package ar.com.utreporter;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;
import ar.com.utreporter.config.AppUserData;

public class AppDataLocalization extends Application implements
		LocationListener {
	public static final String PREFS_NAME = "UTREPORTERPREFERENCES";

	protected LocationManager locationManager;

	public void onCreate() {
		super.onCreate();
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		AppUserData.getInstance().lastusername = settings.getString(
				"lastusername", "");
		AppUserData.getInstance().trackMyPos = settings.getBoolean(
				"trackmypos", false);
		AppUserData.getInstance().frecuencia = settings
				.getInt("frecuencia", 10);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		
		System.out.println("CREA APP");
	}

	public void saveConfig() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("lastusername", AppUserData.getInstance().lastusername);
		editor.putBoolean("trackmypos", AppUserData.getInstance().trackMyPos);
		editor.putInt("frecuencia", AppUserData.getInstance().frecuencia);
		// Commit the edits!
		editor.commit();
	}

	public void startListen(){
		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER,
				Config.MINIMUM_TIME_BETWEEN_UPDATES,
				Config.MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
				this
				);
		System.out.println("START LSTEN");
	}
	
	public void stopListen(){
		System.out.println("STOP LISTEN");
		locationManager.removeUpdates(this);
	}
	
	
	public Location getLastLocation(){
		return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	}
	
	public void onLocationChanged(Location location) {
        
     }

     public void onStatusChanged(String s, int i, Bundle b) {
     }

     public void onProviderDisabled(String s) {
         Toast.makeText(AppDataLocalization.this,
                 "Provider disabled by the user. GPS turned off",
                 Toast.LENGTH_LONG).show();
     }

     public void onProviderEnabled(String s) {
         Toast.makeText(AppDataLocalization.this,
                 "Provider enabled by the user. GPS turned on",
                 Toast.LENGTH_LONG).show();
     }
     
     
}
