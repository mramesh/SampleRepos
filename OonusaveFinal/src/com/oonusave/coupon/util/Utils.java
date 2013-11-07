package com.oonusave.coupon.util;

import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class Utils {


	public static boolean getEmailValidationStatus(String emailId){

		int length = emailId.length();
		int lat = emailId.indexOf("@");
		int ldot = emailId.indexOf(".");
		if (emailId.indexOf("@") == -1 || emailId.indexOf("@") == 0 || emailId.indexOf("@") == length-1) {
			//Log.d(TAG,"----1--------");
			return false;
		}
		if (emailId.indexOf(".") == -1 || emailId.indexOf(".") == 0 || emailId.indexOf(".") == length-1) {
			//Log.d(TAG,"----2--------");
			return false;
		}
		if (emailId.indexOf("@", (lat + 1)) != -1) {
			//Log.d(TAG,"----3--------");
			return false;
		}
		if (emailId.indexOf(".", (lat + 2)) == -1) {
			//Log.d(TAG,"----4--------");
			return false;
		}
		if (emailId.substring(lat-1,lat) == "." || emailId.substring(lat+1,lat+2) == "."){
			//Log.d(TAG,"----5--------");
			return false;
		}
		if (emailId.indexOf(" ") != -1) {
			//Log.d(TAG,"----6--------");
			return false;
		}
		if(emailId.indexOf(".",(lat+1)) == -1){
			//Log.d(TAG,"----7--------");
			return false;
		}
		if(emailId.lastIndexOf(".") == length-1){
			//Log.d(TAG,"------8--------");
			return false;
		}

		return true;
	}



	public static float convertDipToPixel(int dip,Context c) {

		// Converts dip into its equivalent px
		Resources r = c.getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
		return px;

	}



	public static String[] getBirthDateDetails(String birthDate) {

		if(birthDate.indexOf("-") != -1) {
			return birthDate.split("-");
		}
		return null;

	}
	
	
	
	public static String[] getBirthDateDetails1(String birthDate) {

		if(birthDate.indexOf("T") != -1) {
			return birthDate.split("T")[0].split("-");
		}
		return null;

	}
	
	 public static void CopyStream(InputStream is, OutputStream os)
	    {
	        final int buffer_size=1024;
	        try
	        {
	            byte[] bytes=new byte[buffer_size];
	            for(;;)
	            {
	              int count=is.read(bytes, 0, buffer_size);
	              if(count==-1)
	                  break;
	              os.write(bytes, 0, count);
	            }
	        }
	        catch(Exception ex){}
	    }
	

}
