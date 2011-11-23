package ar.com.udt;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import ar.com.udt.components.MyItemizedOverlay;
import ar.com.udt.model.Persona;
import ar.com.udt.utils.DataHelper;
import ar.com.udt.utils.DialogFactory;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class TrackTicketActivity  extends MapActivity{

	MapView mapView;
	MapController mapController;
	GeoPoint myLocation = null;
	GeoPoint gPoint = null;
	MyItemizedOverlay itemizedOverlay;
	Drawable drawable;
	Button trackButton;
	EditText tracktext;
	
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
	    setContentView(R.layout.trackticket);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		mapController = mapView.getController();
		
		trackButton = (Button) findViewById(R.id.trackbutton);
		trackButton.setOnClickListener(clickListener);
		tracktext= (EditText) findViewById(R.id.ticketid);
		
		
		
		drawable = getResources().getDrawable(R.drawable.marker);
		itemizedOverlay = new MyItemizedOverlay(drawable, mapView);
		List<Overlay> overlays = mapView.getOverlays();
		overlays.clear();
		overlays.add(itemizedOverlay);		
		
		GeoPoint point =  new GeoPoint(-34603683,-58381573);
		mapController.setCenter(point);
		mapController.setZoom(18);
		
	}
	
	protected boolean isRouteDisplayed() {
		return true;
	}
	
	private final OnClickListener clickListener =  new OnClickListener() {
		public void onClick(View v) {
			String text = tracktext.getText().toString();
			try{
			int i = Integer.parseInt(text);
			ArrayList<Persona> tickets = DataHelper.getInstance().getFamiliaresByClentId("Sarasa");
			Persona ticket = tickets.get(i%tickets.size());
			Coordenadas c = ticket.getPosicion();
			GeoPoint point = new GeoPoint((int) (c.getLatitud() * 1E6), (int) (c.getLongitud() * 1E6));
			OverlayItem overlayItem = new OverlayItem(point, ticket.getUsername(),ticket.getUrlImage());
			itemizedOverlay.deleteAll();
			itemizedOverlay.addOverlay(overlayItem);
			mapController.animateTo(point);
			}catch (Exception e) {
				DialogFactory.getFactory().getDialogAcept(TrackTicketActivity.this, "ERROR", "Ticket Invalido");
			}
		
		}
	};

}
