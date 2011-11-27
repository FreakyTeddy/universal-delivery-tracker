package ar.com.udt;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import ar.com.udt.config.AppUserData;
import ar.com.udt.utils.DataHelper;

public class HomeActivity extends Activity {

	Button dondeEstoyButton;
	Button trackFamilyButton;
	Button trackTicketButton;
	Button configButton;
	int[] id_servicios = null;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
	    setContentView(R.layout.home);
	    dondeEstoyButton = (Button) findViewById(R.id.dondeestoy);
	    dondeEstoyButton.setOnClickListener(clicListener);
	    trackFamilyButton = (Button) findViewById(R.id.trackFamilyButton);
	    trackFamilyButton.setOnClickListener(clicListener);
	    trackTicketButton = (Button) findViewById(R.id.trackTicketButton);
	    trackTicketButton.setOnClickListener(clicListener);
	    configButton = (Button) findViewById(R.id.configbutton);
	    configButton.setOnClickListener(clicListener);
	    setEnabledButtons();
	}
	
	private void setEnabledButtons() {
		if(AppUserData.getInstance().esInvitado){
			dondeEstoyButton.setEnabled(false);
			trackFamilyButton.setEnabled(false);
			configButton.setEnabled(false);
		}else{
			configButton.setEnabled(true);
			dondeEstoyButton.setEnabled(false);
			trackFamilyButton.setEnabled(false);
			Timer t = new Timer();
			t.schedule(new ServiciosTask(), 0);
		}
		trackTicketButton.setEnabled(true);

	}

	
	Handler h = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(id_servicios!=null){
				for (int i = 0; i < id_servicios.length; i++) {
					switch (id_servicios[i]) {
					case Config.DONDE_ESTOY_SERVICE_ID:
						dondeEstoyButton.setEnabled(true);
						break;
					case Config.TRACK_FAMILIAR_SERVICE_ID:
						trackFamilyButton.setEnabled(true);
						break;
					}
				}
			}
		};
	};
	class ServiciosTask extends TimerTask{
		public void run() {
			id_servicios = DataHelper.getInstance().getServiciosHabilitados();
			h.sendEmptyMessage(0);
		}
	} 
	
	private final OnClickListener clicListener = new OnClickListener() {
		public void onClick(View v) {
			if(v== dondeEstoyButton){
				Intent i = new Intent(HomeActivity.this, DondeEstoyActivity.class);
				startActivity(i);
			}else if( v==trackFamilyButton){
				Intent i = new Intent(HomeActivity.this, TrackFamilyActivity.class);
				startActivity(i);
			}else if( v==trackTicketButton){
				Intent i = new Intent(HomeActivity.this, TrackTicketActivity.class);
				startActivity(i);
			}else if( v==configButton){
				Intent i = new Intent(HomeActivity.this, ConfigurationActivity.class);
				startActivity(i);
			}
		}
	};

}
