package ar.com.utreporter;

public class Config {
	/* GPS*/
	public static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
	public static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
	/* GEOLOCATION API*/
	public static final String MAPS_API_URL = "http://maps.google.com/maps/api/geocode/json?sensor=false&latlng=";
	/* UT SERVER*/
	public static final String BASEURL = "http://www.universaltracker.com.ar/index.php/";
	public static final String REGISTER_CONTROLLER = "auth/register";
	public static final String TRACKER_CONTROLLER = "tracker/";
	public static final String AUTH_CONTROLLER = "auth/";
	public static final String SERVICIOS_CONTROLLER = "servicios/";
	public static final int	   DONDE_ESTOY_SERVICE_ID = 1;
	public static final int	   TRACK_FAMILIAR_SERVICE_ID = 2;
	
	
}
