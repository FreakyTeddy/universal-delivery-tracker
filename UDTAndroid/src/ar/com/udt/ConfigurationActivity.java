package ar.com.udt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import ar.com.udt.config.AppUserData;


public class ConfigurationActivity extends Activity {
	
	SeekBar barra;
	TextView textBarra;
	CheckBox check;
	Button save;
	Button cancel;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
		setContentView(R.layout.config);
		barra = (SeekBar) findViewById(R.id.barra);
		textBarra = (TextView) findViewById(R.id.textbarra);
		save =  (Button) findViewById(R.id.save);
		cancel = (Button) findViewById(R.id.cancel);
		check = (CheckBox) findViewById(R.id.trackme);
		check.setChecked(AppUserData.getInstance().trackMyPos);
		barra.setProgress(AppUserData.getInstance().frecuencia);
		setBarraText(AppUserData.getInstance().frecuencia);
		barra.setOnSeekBarChangeListener(barlistener);
		
		save.setOnClickListener(clicListener);
		cancel.setOnClickListener(clicListener);
	}

	private final OnClickListener clicListener = new OnClickListener() {
		public void onClick(View v) {
			if(v == cancel ){
				finish();
			}else if(v == save){
				saveChanges();
				finish();
			}			
		}
	};

	private final OnSeekBarChangeListener barlistener = new OnSeekBarChangeListener() {
		public void onStopTrackingTouch(SeekBar seekBar) {
			if(seekBar.getProgress() == 0){
				check.setChecked(false);
			}
		}
		public void onStartTrackingTouch(SeekBar seekBar) {
						
		}
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			setBarraText(progress);
		}
	};
	
	private void setBarraText(int min){
		textBarra.setText(""+min+" min");
	}
	
	private void saveChanges(){
		AppUserData.getInstance().frecuencia = barra.getProgress();
		AppUserData.getInstance().trackMyPos = check.isChecked();
		AppDataLocalization app = (AppDataLocalization) getApplication();
		app.saveConfig();
	}
	
	

}
