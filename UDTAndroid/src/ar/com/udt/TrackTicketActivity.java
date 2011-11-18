package ar.com.udt;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import ar.com.udt.components.MyItemizedOverlay;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class TrackTicketActivity  extends MapActivity implements LocationListener{

	MapView mapView;
	MapController mapController;
	private LocationManager locationManager;
	GeoPoint myLocation = null;
	GeoPoint gPoint = null;
	MyItemizedOverlay itemizedOverlay;
	Drawable drawable;
	
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
	    setContentView(R.layout.trackticket);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		mapController = mapView.getController();
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		drawable = getResources().getDrawable(R.drawable.marker);
		itemizedOverlay = new MyItemizedOverlay(drawable, mapView);
		List<Overlay> overlays = mapView.getOverlays();
		overlays.clear();
		overlays.add(itemizedOverlay);		
		
		GeoPoint point =  new GeoPoint(-33632078,-58372829);
		mapController.setCenter(point);
		mapController.setZoom(18);
	}
	
	protected void onResume() {
		super.onResume();
			if (locationManager != null) {
				locationManager.requestLocationUpdates(
						LocationManager.NETWORK_PROVIDER, 1000, 50, this);
			}
	}
	
	protected boolean isRouteDisplayed() {
		return true;
	}
	
	public void onLocationChanged(Location location) {
		AppDataLocalization appState = ((AppDataLocalization) getApplicationContext());
		GeoPoint myLocation = new GeoPoint((int) (appState.getCoordenadas()
				.getLatitud() * 1E6), (int) (appState.getCoordenadas()
				.getLongitud() * 1E6));
		gPoint = myLocation;
		OverlayItem overlayItem = new OverlayItem(myLocation, "Tú","");
		itemizedOverlay.deleteAll();
		itemizedOverlay.addOverlay(overlayItem);
		mapController.animateTo(myLocation);
		
	}
	
	
		


	

	

	public void onProviderDisabled(String provider) {
	}

	public void onProviderEnabled(String provider) {

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {

	}
	
	

}
