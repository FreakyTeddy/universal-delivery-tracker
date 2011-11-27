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
import ar.com.udt.Coordenadas;
import ar.com.udt.config.AppUserData;
import ar.com.udt.model.Persona;

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
	
	public  ArrayList<Persona> getFamiliaresByClentId(String id){
		ArrayList<Persona> pipol =  new ArrayList<Persona>();
		Persona tmp = new Persona(1,"chuchu132", new Coordenadas(-34.584747,-58.400059), "http://graph.facebook.com/chuchu132/picture?type=square", "Soy Ale");
		pipol.add(tmp);
		tmp = new Persona(7,"clauchis", new Coordenadas(-34.586337,-58.401947), "http://graph.facebook.com/nimda/picture?type=square", "Soy Clauchis");
		pipol.add(tmp);
		tmp = new Persona(3,"clau", new Coordenadas(-34.585418,-58.416109), "http://graph.facebook.com/claudia.korzeniewski/picture?type=square", "Soy Clau");
		pipol.add(tmp);
		tmp = new Persona(6,"leo", new Coordenadas(-34.593897,-58.414521), "http://graph.facebook.com/leo.decaria/picture?type=square", "Soy Leito de los wachiturros");
		pipol.add(tmp);
		return pipol;
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
	
}
