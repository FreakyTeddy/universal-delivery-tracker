package ar.com.udt.utils;

import java.io.IOException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageManager 
{
	private static final String SPACE          = " ";
	private static final String SPACE_REPLACED = "%20";
	public static Bitmap getBitMap(String sUrl, String name)
	{		
		Bitmap bitmap = null;		
		
		bitmap = (Bitmap)Storage.getInstance().getObject(name);
		
		if(null != bitmap)
			return bitmap;
		
		try
		{
			URL url = new URL(sUrl.replaceAll(SPACE, SPACE_REPLACED));
			bitmap = BitmapFactory.decodeStream(url.openStream());
			Storage.getInstance().setObject(name, bitmap);
		}
		catch(IOException ex)
		{		
			Log.e(ImageManager.class.getName(), "Cannot download image" + sUrl, ex);
			return null;
		}
				
		return bitmap;		
	}	
}
