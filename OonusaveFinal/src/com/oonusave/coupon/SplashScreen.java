package com.oonusave.coupon;


import java.io.IOException;
import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Category;
import com.oonusave.coupon.model.Login;
import com.oonusave.coupon.model.UserDetails;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.ws.WSSender;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.ImageButton;

/**
 * 
 * @author Ramesh
 *
 */

public class SplashScreen extends Activity {

	protected boolean _active = true;
	protected int _splashTime = 5000; // time to display the splash screen in ms

	SharedPreferences settings = null;
	public static final String PREFS_NAME = "MyPrefsFile";

	ImageButton bgImageButton;
	boolean isSplashCompleted = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		settings = getSharedPreferences(PREFS_NAME, 0);
		setContentView(R.layout.splash);
		
		bgImageButton = (ImageButton) findViewById(R.id.bgImage);
		//DataUtil.priLang = settings.getString("Language", "English");
		bgImageButton.setBackgroundResource(ImageUtils.getSplashImage());
		
		if(!isOnline()) {
			showAlert();
			return;
		}
		
		if(!isGPSEnabled()) {
			showAlert1();
		}
			
		
		// thread for displaying the SplashScreen
		Thread splashTread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while(_active && (waited < _splashTime)) {
						sleep(100);
						if(_active) {
							waited += 100;
						}
					}
					
					
				} catch(InterruptedException e) {
					// do nothing
				} finally {
					try {
						List<Category> categoryList = WSSender.sendSearchAllCategoryRequest(7);
						DataUtil.categoryList = categoryList; 
					}catch(Exception e) {
						e.printStackTrace();
						Log.e(Constants.TAG, " Error while getting category List --- > " + e.getLocalizedMessage());
					}
					

					//stop();
					isSplashCompleted = true;

			}
			}
		};
		splashTread.start();
	

		//if(settings.getBoolean("loginStatus", false)) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					if(settings.getBoolean("loginStatus", false)) {
						doLogin();
					}else {
						while(!isSplashCompleted) { 
							Thread.sleep(200);
						}
						startActivity(new Intent(getApplicationContext(),MyActivity.class));
						finish();
					}
				}catch(Exception e) {
					e.printStackTrace();	
				}
			}
		};
		thread.start();
		//}
	}
	
	
	@Override
	protected void onResume() {
		DataUtil.CURRENT_SCREEN = PageManager.SPLASH_SCREEN;
		super.onResume();
	}
	

	private void doLogin() {
		Login login = new Login();
		SharedPreferences.Editor editor = settings.edit();
		login.setUsername(settings.getString("userName",""));
		login.setPassword(settings.getString("password", ""));
		login.setDeviceIndentifier("213123");
		try {		    	
			UserDetails userDetails = WSSender.sendValidateCredentialRequest(login);
			if(userDetails == null) {
				editor.putBoolean("loginStatus", false);
				// Don't forget to commit your edits!!!
				editor.commit();
				finish();
				startActivity(new Intent(getApplicationContext(),MyActivity.class));

			}else {
				DataUtil.userName = login.getUsername();
				DataUtil.password = login.getPassword();
				DataUtil.userId = userDetails.getUserId();


				editor.putString("userName", login.getUsername());
				editor.putString("password", login.getPassword());
				
				editor.putBoolean("loginStatus", true);
				// Don't forget to commit your edits!!!
				editor.commit();
				startActivity(new Intent(getApplicationContext(),MyMapStore.class));
				finish();
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(Exception e) {
		}
	}


	void showAlert(){
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which){
				case DialogInterface.BUTTON_POSITIVE:
					//Yes button clicked
					try {
						dialog.dismiss();
						finish();
					}catch(Exception ioe) {
						
					}
					break;

				case DialogInterface.BUTTON_NEGATIVE:
					//No button clicked
					break;
				}
			}
		};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Oonusave Server Unreachable");
		//builder.setMessage("Mocansa requires an active network connection.  Please activate a data or wifi connection and try again.").setPositiveButton("Exit", dialogClickListener).show();
		builder.setMessage(Constants.NO_NETWOTK_ENG)
		.setPositiveButton("Exit", dialogClickListener).show();
	}
	
	
	
	void showAlert1(){
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			
			public void onClick(DialogInterface dialog, int which) {
				switch (which){
				case DialogInterface.BUTTON_POSITIVE:
					//Yes button clicked
					dialog.dismiss();
					try {
						startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
					}catch(Exception ioe) {
						ioe.printStackTrace();
					}
					break;
				case DialogInterface.BUTTON_NEGATIVE:
					//No button clicked
					dialog.dismiss();
					break;
				}
			}
		};
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Location Unavailable");
		builder.setMessage(Constants.LOCATION_UNAVAILABLE_ENG)
		.setPositiveButton("GPS Settings", dialogClickListener).setNegativeButton("Cancel", dialogClickListener).show();
	}




	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			_active = false;
		}
		return true;
	}


	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if(cm == null || cm.getActiveNetworkInfo() == null) {
			return false;
		}
		return cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}
	
	
	public boolean isGPSEnabled() {
		LocationManager locationMgr = (LocationManager) getApplicationContext()
		.getSystemService(Context.LOCATION_SERVICE);
		if(locationMgr != null) {
			return locationMgr.isProviderEnabled (LocationManager.GPS_PROVIDER);
		}
		return false;
	}


}
