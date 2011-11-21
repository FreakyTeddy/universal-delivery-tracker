package ar.com.udt;

import java.util.ArrayList;
import java.util.List;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import ar.com.udt.components.FamiliaresSpinnerAdapter;
import ar.com.udt.components.MyItemizedOverlay;
import ar.com.udt.model.Persona;
import ar.com.udt.utils.DataHelper;
import ar.com.udt.utils.Storage;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class TrackFamilyActivity  extends MapActivity {

	MapView mapView;
	MapController mapController;
	ArrayList<Persona> familiares=null;
	
	Spinner spinnerFamilia;

	Drawable drawable;
	MyItemizedOverlay itemizedOverlay;
	
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
	    setContentView(R.layout.trackfamily);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		mapController = mapView.getController();
		
		GeoPoint point =  new GeoPoint(-33632078,-58372829);
		mapController.setCenter(point);
		mapController.setZoom(18);
		
		drawable = getResources().getDrawable(R.drawable.marker);
		itemizedOverlay = new MyItemizedOverlay(drawable, mapView);
		List<Overlay> overlays = mapView.getOverlays();
		overlays.clear();
		overlays.add(itemizedOverlay);
		
		
		cargarDatosFamilia();
		
	}

	private void cargarDatosFamilia(){
		familiares = DataHelper.getInstance().getFamiliaresByClentId("Sarasa");
		for (Persona familiar : familiares) {
			Coordenadas c = familiar.getPosicion();
			GeoPoint point = new GeoPoint((int) (c.getLatitud() * 1E6), (int) (c.getLongitud() * 1E6));
			OverlayItem overlayItem = new OverlayItem(point, familiar.getUsername(),familiar.getUrlImage());
			itemizedOverlay.addOverlay(overlayItem);
		}
		spinnerFamilia = (Spinner) findViewById(R.id.spinnerFamilia);
		FamiliaresSpinnerAdapter adapter = new FamiliaresSpinnerAdapter(this,familiares);
		spinnerFamilia.setAdapter(adapter);
		spinnerFamilia.setOnItemSelectedListener(new OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	Persona p = (Persona)spinnerFamilia.getSelectedItem();
				if(p != null){
					Coordenadas c = p.getPosicion();
					GeoPoint point = new GeoPoint((int) (c.getLatitud() * 1E6), (int) (c.getLongitud() * 1E6));
					mapController.animateTo(point);
				}
		    }

		    public void onNothingSelected(AdapterView<?> parentView) {

		    }

		});
	}
	
	protected boolean isRouteDisplayed() {
		return true;
	}
		
	public void onProviderDisabled(String provider) {
	}

	public void onProviderEnabled(String provider) {

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {

	}
	
	

}
