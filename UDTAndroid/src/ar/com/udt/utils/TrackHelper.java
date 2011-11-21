package ar.com.udt.utils;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


import android.content.Context;
import android.telephony.TelephonyManager;

import com.google.android.maps.GeoPoint;

public class TrackHelper {

	
	private static final String BASEURL = "http://www.abarbieri.com.ar/index.php/";
	private static final String TRACKER_CONTROLLER = "tracker/";

	public static void postMyPos(Context ctx, GeoPoint myPos){
			TelephonyManager telephonyManager = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
			ArrayList<NameValuePair> par = new ArrayList<NameValuePair>();
			System.out.println("IMEI:" + telephonyManager.getDeviceId());
			par.add(new BasicNameValuePair("imei", telephonyManager.getDeviceId()));
			par.add(new BasicNameValuePair("lat",""+ myPos.getLatitudeE6()));
			par.add(new BasicNameValuePair("lon", ""+myPos.getLongitudeE6()));
			String resp = HttpHelper.postData(BASEURL + TRACKER_CONTROLLER + "track_user_pos", par);
			System.out.println("resp del post: " + resp);
			
	}
	
}
