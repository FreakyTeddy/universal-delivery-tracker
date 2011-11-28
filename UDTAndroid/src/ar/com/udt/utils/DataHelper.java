package ar.com.udt.utils;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.maps.GeoPoint;
import android.content.Context;
import android.telephony.TelephonyManager;
import ar.com.udt.Config;
import ar.com.udt.config.AppUserData;

public class DataHelper {

	private static DataHelper INSTANCE = null;
	
	private DataHelper(){
		
	};
	
	static public DataHelper getInstance(){
		if(INSTANCE == null){
			INSTANCE =  new DataHelper();
		}
		return
		INSTANCE;
	}
	
	
	public static boolean login(String user, String password){
		ArrayList<NameValuePair> par = new ArrayList<NameValuePair>();
		par.add(new BasicNameValuePair("login",user));
		par.add(new BasicNameValuePair("password",password));
		String resp = HttpHelper.postData(Config.BASEURL + Config.AUTH_CONTROLLER + "login_mobile", par);
		try{
			int responseNo = Integer.parseInt(resp);
			AppUserData.getInstance().user_id = responseNo;
			return (responseNo > 0);
		}catch (NumberFormatException e) {
			return false;
		}
	}
	
	public int[] getServiciosHabilitados(){
		if(AppUserData.getInstance().esInvitado){
			return null;
		}
		ArrayList<NameValuePair> par = new ArrayList<NameValuePair>();
		par.add(new BasicNameValuePair("user_id",""+AppUserData.getInstance().user_id));
		String resp = HttpHelper.postData(Config.BASEURL + Config.SERVICIOS_CONTROLLER + "get_servicios_contratados", par);
		try {
			JSONArray array = new JSONArray(resp);
			int[] id_servicios = new int[array.length()];
			for(int i = 0; i<array.length();i++){
				id_servicios[i]=array.getJSONObject(i).getInt("id_servicio");
			}
			return id_servicios;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public JSONObject getTicketInfo(String ticketid){
		ArrayList<NameValuePair> par = new ArrayList<NameValuePair>();
		par.add(new BasicNameValuePair("user_id",""+AppUserData.getInstance().user_id));
		par.add(new BasicNameValuePair("ticket_id",ticketid));
		String resp = HttpHelper.postData(Config.BASEURL + Config.TRACKER_CONTROLLER + "get_ticket_info", par);
		try {
			return new JSONObject(resp);
		} catch (JSONException e) {
			return null;
		}
	} 
	
	public JSONArray getTicketCoords(String ticketid){
		ArrayList<NameValuePair> par = new ArrayList<NameValuePair>();
		par.add(new BasicNameValuePair("user_id",""+AppUserData.getInstance().user_id));
		par.add(new BasicNameValuePair("ticket_id",ticketid));
		String resp = HttpHelper.postData(Config.BASEURL + Config.TRACKER_CONTROLLER + "get_ticket_coords", par);
		try {
			return new JSONArray(resp);
		} catch (JSONException e) {
			return null;
		}
	} 
	
	
	public JSONObject getFamilyInfo(){
		ArrayList<NameValuePair> par = new ArrayList<NameValuePair>();
		par.add(new BasicNameValuePair("user_id",""+AppUserData.getInstance().user_id));
		String resp = HttpHelper.postData(Config.BASEURL + Config.TRACKER_CONTROLLER + "get_family_info", par);
		try {
			return new JSONObject(resp);
		} catch (JSONException e) {
			return null;
		}
	} 
	
	public JSONArray getFamilyCoords(String ticketid){
		ArrayList<NameValuePair> par = new ArrayList<NameValuePair>();
		par.add(new BasicNameValuePair("ticket_id",ticketid));
		String resp = HttpHelper.postData(Config.BASEURL + Config.TRACKER_CONTROLLER + "get_family_coords", par);
		try {
			return new JSONArray(resp);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	} 
	
	
	public void postMyPos(Context ctx, GeoPoint myPos){
		TelephonyManager telephonyManager = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
		ArrayList<NameValuePair> par = new ArrayList<NameValuePair>();
		System.out.println("IMEI:" + telephonyManager.getDeviceId());
		par.add(new BasicNameValuePair("imei", telephonyManager.getDeviceId()));
		par.add(new BasicNameValuePair("lat",""+ myPos.getLatitudeE6()));
		par.add(new BasicNameValuePair("lon", ""+myPos.getLongitudeE6()));
		String resp = HttpHelper.postData(Config.BASEURL + Config.TRACKER_CONTROLLER + "track_user_pos", par);
		System.out.println("resp del post: " + resp);
	}
	
	public String getAddress(GeoPoint gp ){
		ArrayList<NameValuePair> par = new ArrayList<NameValuePair>();
		String resp = HttpHelper.postData(Config.MAPS_API_URL + gp.getLatitudeE6()/1E6 + ","+gp.getLongitudeE6()/1E6, par);
		System.out.println("resp del post: " + resp);
		try {
			JSONObject response = new JSONObject(resp);
			String status = response.getString("status");
			if(status.equals("OK")){
				JSONArray results = response.getJSONArray("results");
				return results.getJSONObject(0).getString("formatted_address");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null ;
	}
	
}
