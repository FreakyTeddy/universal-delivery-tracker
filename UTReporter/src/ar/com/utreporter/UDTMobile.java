package ar.com.utreporter;


import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class UDTMobile extends Activity {
	
	protected boolean _active = true;
	protected int _splashTime = 2000; // time to display the splash screen in ms
	protected int _splashCount;
	protected ArrayList<Integer> _screens;
	protected ImageView _splashImg;
	protected boolean _exit = false;
	protected boolean _first = true;
	protected LinearLayout layout;
	
	@Override
	public void onPause(){
	    super.onPause();

		_exit = true;
		_first = false;
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		if(!_first){
			finish();
        	startActivity(new Intent(this,LoginActivity.class));
		}
	}
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    // Fullscreen sin titulo
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

	    _screens = new ArrayList<Integer>();
	    _screens.add(R.drawable.utreportersplash);
	    	   
	    _splashCount = _screens.size();
	    
	    layout = new LinearLayout(this);
	    layout.setBackgroundColor(Color.BLACK);
	    layout.setGravity(Gravity.CENTER);
	    _splashImg = new ImageView(this);
	    _splashImg.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	    _splashImg.setScaleType(ScaleType.FIT_XY);
	    layout.addView(_splashImg);
	    setContentView(layout);

	    // Init first
	    _splashImg.setImageResource(_screens.get(0));

	    // thread for displaying the SplashScreen
	    Thread splashTread = new Thread() {
	    	@Override
	        public void run() {
	            try {
	                int waited = 0;
	                int count = 0;
	                while(_active && (count < _splashCount) && !_exit) {
	        			sleep(100);
	        			if(_active && (waited < _splashTime)) {
	                        waited += 100;
	                    } else {
	                    	count++;
	                    	if(count<_splashCount){
	                            Message msg = _handler.obtainMessage();
	                            Bundle b = new Bundle();
	                            b.putInt("splash", count);
	                            msg.setData(b);
	                            _handler.sendMessage(msg);

	                    		waited = 0;
	                    	} else {
	                    		_active = false;
	                    	}
	                    }
	                }
	            } catch(InterruptedException e) {
	                // do nothing
	            	System.out.print(e.toString());
	            } finally {
	                
	                if(!_exit){
	                	finish();
	                	startActivity(new Intent(UDTMobile.this, LoginActivity.class));
	            	    Log.v(" ", "width: " + layout.getWidth());
	            	    Log.v(" ", "height: " + layout.getHeight());
	                } else {
	                }
	            }
	        }
	    };
	    
	    splashTread.start();
	}
	
    // Define the Handler that receives messages from the thread and update the progress
    final Handler _handler = new Handler() {
        public void handleMessage(Message msg) {
            int screen = msg.getData().getInt("splash");
            _splashImg.setImageResource(_screens.get(screen));
        }
    };
}
