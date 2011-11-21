package ar.com.udt.config;

public class AppConfiguration {
	
	private static AppConfiguration INSTANCE  = null;
	
	public boolean trackMyPos = true;
	
	private AppConfiguration(){
		
	}
	
	public static AppConfiguration getInstance(){
		if(INSTANCE == null){
			INSTANCE =  new AppConfiguration();
		}
		return INSTANCE;
	}

}
