package ar.com.udt.utils;

public interface AsynchHttpResponseListener {

	public void onComplete(String response);
	
	public void onError(String error);
	
}
