package ar.com.utreporter;

import java.util.Timer;
import java.util.TimerTask;

import com.google.android.maps.GeoPoint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import ar.com.utreporter.config.AppUserData;
import ar.com.utreporter.utils.DataHelper;

public class HomeActivity extends Activity {

	int[] id_servicios = null;
	Button configButton;
	Button misTicketsButton;

	Timer timer;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.home);
		configButton = (Button) findViewById(R.id.configbutton);
		configButton.setOnClickListener(clicListener);
		misTicketsButton = (Button) findViewById(R.id.misTicketsButton);
		misTicketsButton.setOnClickListener(clicListener);
		
		
	}
	
	
	protected void onResume() {
		super.onResume();
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Toast.makeText(this, "GPS está activado.",
					Toast.LENGTH_SHORT).show();
			if(timer != null){
				timer.cancel();
			}
			if(AppUserData.getInstance().trackMyPos){
				timer = new Timer();
				timer.schedule(new PostPositionTask(), 0, 1000*AppUserData.getInstance().frecuencia);
			}
		} else {
			showGPSDisabledAlertToUser();
		}
		
	}
	
	protected void onDestroy() {
		if(timer != null){
			timer.cancel();
		}
		
		super.onDestroy();
	}

	
	class PostPositionTask extends TimerTask {
		public void run() {
			Location l = ((AppDataLocalization) getApplication()).getLastLocation();
			if(l != null){
				System.out.println("Post Position Task !");
				DataHelper.getInstance().postMyPos(HomeActivity.this, new GeoPoint((int)(l.getLatitude()*1E6), (int)(l.getLongitude()*1E6)));
			}
		}
	}
	
	
	private void showGPSDisabledAlertToUser() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder
				.setMessage(
						"GPS está desactivado. Deseas activarlo?")
				.setCancelable(false)
				.setPositiveButton("Ir a la página de configuración.",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent callGPSSettingIntent = new Intent(
										android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								startActivity(callGPSSettingIntent);
							}
						});
		alertDialogBuilder.setNegativeButton("Cancelar",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
	}

	
	private final OnClickListener clicListener = new OnClickListener() {
		public void onClick(View v) {
			if (v == configButton) {
				Intent i = new Intent(HomeActivity.this,
						ConfigurationActivity.class);
				startActivity(i);
			}else if(v == misTicketsButton){
				Intent i = new Intent(HomeActivity.this,
						MisTicketsActivity.class);
				startActivity(i);
			}
		}
	};

}
