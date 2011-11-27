package ar.com.udt;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Spinner;
import ar.com.udt.components.MyItemizedOverlay;
import ar.com.udt.utils.DataHelper;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class TrackFamilyActivity extends MapActivity {

	MapView mapView;
	MapController mapController;
	JSONArray familiares = null;

	Spinner spinnerFamilia;

	Drawable drawable;
	MyItemizedOverlay itemizedOverlay;
	JSONObject ticketinfo;
	Timer posScheduler;

	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.trackfamily);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		mapController = mapView.getController();

		GeoPoint point = new GeoPoint(-34603683, -58381573);
		mapController.setCenter(point);
		mapController.setZoom(10);

		drawable = getResources().getDrawable(R.drawable.marker);
		itemizedOverlay = new MyItemizedOverlay(drawable, mapView);
		List<Overlay> overlays = mapView.getOverlays();
		overlays.clear();
		overlays.add(itemizedOverlay);

		// cargarDatosFamilia();

	}

	protected boolean isRouteDisplayed() {
		return true;
	}

	protected void onStart() {
		super.onStart();
		Thread t = new Thread() {
			public void run() {
				ticketinfo = DataHelper.getInstance().getFamilyInfo();
				System.out.println("TICKET INFOO: ");
				h.sendEmptyMessage(0);
			}
		};
		t.start();
	}

//	private void cargarDatosFamilia() {
//		familiares = DataHelper.getInstance().getFamiliaresByClentId("Sarasa");
//		for (Persona familiar : familiares) {
//			Coordenadas c = familiar.getPosicion();
//			GeoPoint point = new GeoPoint((int) (c.getLatitud() * 1E6),
//					(int) (c.getLongitud() * 1E6));
//			OverlayItem overlayItem = new OverlayItem(point,
//					familiar.getUsername(), familiar.getUrlImage());
//			itemizedOverlay.addOverlay(overlayItem);
//		}
//		spinnerFamilia = (Spinner) findViewById(R.id.spinnerFamilia);
//		FamiliaresSpinnerAdapter adapter = new FamiliaresSpinnerAdapter(this,
//				familiares);
//		spinnerFamilia.setAdapter(adapter);
//		spinnerFamilia.setOnItemSelectedListener(new OnItemSelectedListener() {
//			public void onItemSelected(AdapterView<?> parentView,
//					View selectedItemView, int position, long id) {
//				Persona p = (Persona) spinnerFamilia.getSelectedItem();
//				if (p != null) {
//					Coordenadas c = p.getPosicion();
//					GeoPoint point = new GeoPoint((int) (c.getLatitud() * 1E6),
//							(int) (c.getLongitud() * 1E6));
//					mapController.animateTo(point);
//				}
//			}
//
//			public void onNothingSelected(AdapterView<?> parentView) {
//
//			}
//
//		});
//	}

	class PositionTask extends TimerTask {
		public void run() {
			synchronized (ticketinfo) {
				if (ticketinfo != null) {
					try {
						System.out.println("REFRESHH");
						familiares = DataHelper.getInstance()
								.getFamilyCoords(
										ticketinfo.getString("id_ticket"));
						if(familiares == null){
							return;
						}
						h.sendEmptyMessage(1);
						
						

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	Handler h = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				if (ticketinfo != null) {
					if (posScheduler != null) {
						posScheduler.cancel();
					}
					posScheduler = new Timer();
					posScheduler.schedule(new PositionTask(), 0, 30000);
				}
			}
			if(msg.what == 1){
				itemizedOverlay.deleteAll();
				for (int i = 0; i < familiares.length(); i++) {
					JSONObject familiar;
					try {
						familiar = familiares.getJSONObject(i);
					GeoPoint point = new GeoPoint(familiar.getInt("lat"),
							familiar.getInt("lon"));
					JSONObject info = familiar
							.getJSONObject("descripcion");
					System.out.println("FAMILIAR: " + info.toString());
					OverlayItem overlayItem = new OverlayItem(point,
							info.getString("nombre"),
							info.getString("img"));
					itemizedOverlay.addOverlay(overlayItem);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
	};

	protected void onDestroy() {
		super.onDestroy();
		if (posScheduler != null) {
			posScheduler.cancel();
		}
	};

}
