package ar.com.utreporter.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;


import android.net.ParseException;

public final class HttpHelper {

	
	public static void asyncPostData(final String url, final ArrayList<NameValuePair> params, final AsynchHttpResponseListener listener){
		 new Thread() {
	            public void run() {
	                try {
	                    String response = post(url,params);
	                    listener.onComplete(response);
	                } catch (Exception e) {
	                	listener.onError(e.getMessage());
	                }
	            }
	        }.start();
	}
	
	
	public static String postData(String url, ArrayList<NameValuePair> params) {
		// Create a new HttpClient and Post Header
		try {
			return post(url, params);
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
		return "";
	}
	
	private static String post(String url, ArrayList<NameValuePair> params) throws ClientProtocolException, IOException{
		if(params == null){
			params = new ArrayList<NameValuePair>();
		}
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		System.out.println("URI: " + httppost.getURI());
		httppost.setEntity(new UrlEncodedFormEntity(params));
		// Execute HTTP Post Request
		HttpResponse response = httpclient.execute(httppost);
		return getResponseBody(response);

	}
	
	
	public static String getResponseBody(HttpResponse response) {
		String response_text = null;
		HttpEntity entity = null;
		try {
			entity = response.getEntity();
			response_text = _getResponseBody(entity);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			if (entity != null) {
				try {
					entity.consumeContent();
				} catch (IOException e1) {
				}
			}
		}

		return response_text;

	}

	public static String _getResponseBody(final HttpEntity entity)
			throws IOException, ParseException {
		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		InputStream instream = entity.getContent();
		if (instream == null) {
			return "";
		}
		if (entity.getContentLength() > Integer.MAX_VALUE) {
			throw new IllegalArgumentException(
					"HTTP entity too large to be buffered in memory");
		}
		String charset = getContentCharSet(entity);
		if (charset == null) {
			charset = HTTP.DEFAULT_CONTENT_CHARSET;
		}
		Reader reader = new InputStreamReader(instream, charset);
		StringBuilder buffer = new StringBuilder();
		try {
			char[] tmp = new char[1024];
			int l;
			while ((l = reader.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}
		} finally {
			reader.close();
		}
		return buffer.toString();
	}

	public static String getContentCharSet(final HttpEntity entity)
			throws ParseException {
		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		String charset = null;
		if (entity.getContentType() != null) {
			HeaderElement values[] = entity.getContentType().getElements();
			if (values.length > 0) {
				NameValuePair param = values[0].getParameterByName("charset");
				if (param != null) {
					charset = param.getValue();
				}
			}
		}
		return charset;
	}

}