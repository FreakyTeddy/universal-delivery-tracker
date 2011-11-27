package ar.com.udt.config;

public class AppUserData {
	
	private static AppUserData INSTANCE  = null;
	
	public String  lastusername = "";
	public boolean trackMyPos = true;
	public int 	   frecuencia = 10;//min
	public boolean esInvitado = false;
	public int 	   user_id = -1;
	
	private AppUserData(){
		
	}
	
	public static AppUserData getInstance(){
		if(INSTANCE == null){
			INSTANCE =  new AppUserData();
		}
		return INSTANCE;
	}

}
