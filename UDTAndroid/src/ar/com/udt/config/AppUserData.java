package ar.com.udt.config;

public class AppUserData {
	
	private static AppUserData INSTANCE  = null;
	
	public boolean trackMyPos = true;
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
