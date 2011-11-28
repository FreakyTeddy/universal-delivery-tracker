package ar.com.udt;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;
import ar.com.udt.config.AppUserData;

public class AppDataLocalization extends Application implements
		LocationListener {
	public static final String PREFS_NAME = "UTPREFERENCES";

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
		locationManager.requestLocationUpdates(
		LocationManager.GPS_PROVIDER,
		Config.MINIMUM_TIME_BETWEEN_UPDATES,
		Config.MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
		this
		);

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

	
	public Location getLastLocation(){
		return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	}
	
	public void onLocationChanged(Location location) {
         String message = String.format(
                 "New Location \n Longitude: %1$s \n Latitude: %2$s",
                 location.getLongitude(), location.getLatitude()
         );
         Toast.makeText(AppDataLocalization.this, message, Toast.LENGTH_LONG).show();
     }

     public void onStatusChanged(String s, int i, Bundle b) {
         Toast.makeText(AppDataLocalization.this, "Provider status changed",
                 Toast.LENGTH_LONG).show();
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
