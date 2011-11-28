package ar.com.udt;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import ar.com.udt.components.MyItemizedOverlay;
import ar.com.udt.utils.DataHelper;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class DondeEstoyActivity  extends MapActivity{

	MapView mapView;
	MapController mapController;
	GeoPoint gPoint = null;
	MyItemizedOverlay itemizedOverlay;
	Drawable drawable;
	TextView address; 
	Button share;
	Timer timer ;
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
	    setContentView(R.layout.dondeestoy);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		mapController = mapView.getController();
		
		address = (TextView) findViewById(R.id.address);
		drawable = getResources().getDrawable(R.drawable.marker);
		itemizedOverlay = new MyItemizedOverlay(drawable, mapView);
		List<Overlay> overlays = mapView.getOverlays();
		overlays.clear();
		overlays.add(itemizedOverlay);		
		
		GeoPoint point =  new GeoPoint(-34603683,-58381573);
		mapController.setCenter(point);
		mapController.setZoom(18);
		share = (Button) findViewById(R.id.shareButton);
		
		share.setOnClickListener(shareListener);
		
		
		
	}
	
	private final OnClickListener shareListener = new OnClickListener() {
		public void onClick(View v) {
			Context ctx = DondeEstoyActivity.this;
			Intent i = new Intent(Intent.ACTION_SEND) ;
			i.setType("text/html");
			i.putExtra(android.content.Intent.EXTRA_TEXT,ctx.getString(R.string.my_pos_text)+ address.getText() );
			ctx.startActivity(Intent.createChooser(i,ctx.getString(R.string.sharetext)));
		}
	};
	
	protected void onResume() {
		super.onResume();
		if(timer != null){
			timer.cancel();
		}
		timer =  new Timer();
		timer.schedule(new UpdateMapTask(), 0,10000);
	}
	
	
	protected boolean isRouteDisplayed() {
		return true;
	}
	
	class UpdateMapTask extends TimerTask {
		public void run() {
			Location l  = ((AppDataLocalization)getApplication()).getLastLocation();
			if(l != null ){
				gPoint = new GeoPoint((int)(l.getLatitude()*1E6), (int) (l.getLongitude()*1E6));
			 		h.sendEmptyMessage(0);
			 		h.sendEmptyMessage(1);
			}
		}
	}

	Handler h = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == 0){
				OverlayItem overlayItem = new OverlayItem(gPoint, "Tú","");
				itemizedOverlay.deleteAll();
				itemizedOverlay.addOverlay(overlayItem);
				mapController.animateTo(gPoint);
				Context ctx = DondeEstoyActivity.this;
				DataHelper.getInstance().postMyPos(ctx, gPoint);
			}
			if(msg.what == 1){
				String add = DataHelper.getInstance().getAddress(gPoint);
				if(add != null){
					address.setText(add);
				}		
			}
		};
	};

	

}
