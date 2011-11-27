package ar.com.udt;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ar.com.udt.components.MyItemizedOverlay;
import ar.com.udt.utils.DataHelper;
import ar.com.udt.utils.DialogFactory;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class TrackTicketActivity extends MapActivity {

	MapView mapView;
	MapController mapController;
	GeoPoint myLocation = null;
	GeoPoint gPoint = null;
	MyItemizedOverlay itemizedOverlay;
	Drawable drawable;
	Button trackButton;
	EditText tracktext;
	TextView descripcion;
	JSONObject ticketinfo;

	Timer posScheduler;

	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.trackticket);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		mapController = mapView.getController();

		trackButton = (Button) findViewById(R.id.trackbutton);
		trackButton.setOnClickListener(clickListener);
		tracktext = (EditText) findViewById(R.id.ticketid);
		descripcion = (TextView) findViewById(R.id.descripcion);

		drawable = getResources().getDrawable(R.drawable.marker);
		itemizedOverlay = new MyItemizedOverlay(drawable, mapView);
		List<Overlay> overlays = mapView.getOverlays();
		overlays.clear();
		overlays.add(itemizedOverlay);

		GeoPoint point = new GeoPoint(-34603683, -58381573);
		mapController.setCenter(point);
		mapController.setZoom(18);
		trackButton.requestFocus();
	}

	protected boolean isRouteDisplayed() {
		return true;
	}

	private final OnClickListener clickListener = new OnClickListener() {
		public void onClick(View v) {
			String text = tracktext.getText().toString();
			try {
				int i = Integer.parseInt(text);
				Thread t = new Thread(new GetTicket(i));
				t.start();
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(tracktext.getWindowToken(),0);
			} catch (Exception e) {
				DialogFactory.getFactory().getDialogAcept(
						TrackTicketActivity.this, "ERROR", "Ticket Invalido");
			}

		}
	};

	class GetTicket implements Runnable {
		int ticket;

		public GetTicket(int ticket) {
			this.ticket = ticket;
		}

		public void run() {
			ticketinfo = DataHelper.getInstance().getTicketInfo("" + ticket);
			h.sendEmptyMessage(0);
		}
	}

	class PositionTask extends TimerTask {
		public void run() {
				if (ticketinfo != null) {
					try {
						itemizedOverlay.deleteAll();
						JSONArray coords = DataHelper.getInstance()
								.getTicketCoords(
										ticketinfo.getString("id_ticket"));
						GeoPoint point = null;
						if(coords == null){
							return;
						}
						for (int i = 0; i < coords.length(); i++) {
							JSONObject coord = coords.getJSONObject(i);
							point = new GeoPoint(coord.getInt("lat"),
									coord.getInt("lon"));
							OverlayItem overlayItem = new OverlayItem(point,
									"Device " + i, "");
							itemizedOverlay.addOverlay(overlayItem);
						}
						if (point != null) {
							mapController.animateTo(point);
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
	}

	Handler h = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				if (ticketinfo != null) {
					try {
						descripcion
								.setText(ticketinfo.getString("descripcion"));
						if (posScheduler != null) {
							posScheduler.cancel();
						}
						posScheduler = new Timer();
						posScheduler.schedule(new PositionTask(), 0, 30000);

					} catch (JSONException e) {
						e.printStackTrace();
						descripcion.setText("");
					}

				} else {
					descripcion.setText("");
					tracktext.setText("");
					DialogFactory.getFactory().getDialogAcept(
							TrackTicketActivity.this, "ERROR",
							"Ticket Invalido");
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
