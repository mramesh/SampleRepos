package com.oonusave.coupon;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Coupon;
import com.oonusave.coupon.model.LocationInfo;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.ws.WSSender;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * @author Ramesh
 *
 */

public class CouponListScreen extends BaseListActivity implements OnClickListener{

	List<Coupon> couList = new ArrayList<Coupon>();
	//String action = "";
	CouponAdapter couponAdapter = null;
	ImageButton signoutBtn,settingBtn;
	EditText searchText;
	Button searchButton;
	ListView lv;
	TextView titleBarTextView;
	private LocationManager locationMgr = null;
	//private LocationFinder finder = null;
	private boolean isSearch = false;
	public static final String PREFS_NAME = "MyPrefsFile";
	SharedPreferences settings = null;
	List<Coupon> tempList = null;
	private static final int PRO_DIALOG = 100;
	LocationFinder finder = null;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		settings = getSharedPreferences(PREFS_NAME, 0);
		//setContentView(R.layout.row);
		//setContentView(R.layout.coupon_list_view);
		setContentView(R.layout.coupon_view);
		//setContentView(R.layout.category_screen);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, COUNTRIES));

		//((TextView)findViewById(R.id.titleText)).setText("Coupons");
		signoutBtn = (ImageButton)findViewById(R.id.btnLeft);
		signoutBtn.setVisibility(ImageButton.VISIBLE);
		//signoutBtn.setBackgroundResource(R.drawable.signout_button);
		signoutBtn.setBackgroundResource(ImageUtils.getSignoutImage());
		//signoutBtn.setBackgroundResource(ImageUtils.getBackIamge());
		signoutBtn.setOnClickListener(this);

		settingBtn = (ImageButton)findViewById(R.id.btnRight);
		settingBtn.setVisibility(ImageButton.VISIBLE);
		//settingBtn.setBackgroundResource(R.drawable.settings_btn);
		settingBtn.setBackgroundResource(ImageUtils.getSettingsImage());

		settingBtn.setOnClickListener(this);
		
		searchText = (EditText) findViewById(R.id.searchText);
		searchButton = (Button) findViewById(R.id.submit_button);
		searchButton.setBackgroundResource(ImageUtils.getSubmitImage());
		searchButton.setOnClickListener(this);

		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getCouponListTitleText());
		//DataUtil.locationInfo = new LocationInfo();
		finder = new LocationFinder(getApplicationContext());
		locationMgr = (LocationManager) getApplicationContext()
		.getSystemService(Context.LOCATION_SERVICE);
		locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, finder);
		locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 1, finder);
		boolean isGPS = locationMgr.isProviderEnabled (LocationManager.GPS_PROVIDER);
		boolean isNetwork = locationMgr.isProviderEnabled (LocationManager.NETWORK_PROVIDER); 
				
		Location loc = locationMgr.getLastKnownLocation("gps");
				//Toast.makeText(getApplicationContext(), "isGPS --- > " + isGPS, Toast.LENGTH_LONG).show();


//		locationMgr = (LocationManager)
//		getSystemService(Context.LOCATION_SERVICE);
//		Criteria criteria = new Criteria();
//		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
//		criteria.setAltitudeRequired(true);
//		criteria.setBearingRequired(false);
//		criteria.setCostAllowed(true);
//		criteria.setPowerRequirement(Criteria.ACCURACY_COARSE);
//		locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1.0f, this);
//		String provider = locationMgr.getBestProvider(criteria, true);
//		
//		boolean isGPS = locationMgr.isProviderEnabled (provider);
//		Location loc = locationMgr.get
//		//locationMgr.get
//
//		Toast.makeText(getApplicationContext(), "provider -- > " + provider, Toast.LENGTH_LONG).show();

		if(loc == null) {
			loc = locationMgr.getLastKnownLocation("network");
			if(loc !=  null) {
				////Log.i(Constants.TAG, "LATITUTE --- >" + loc.getLatitude() + " :: LONGITUDE  ----> " + loc.getLongitude());
				DataUtil.locationInfo.setLatitude(loc.getLatitude() + "");
				DataUtil.locationInfo.setLongitude(loc.getLongitude() + "");
				//Toast.makeText(getApplicationContext(), "Lati ->" + loc.getLatitude() + " ::: Longi -> " + loc.getLongitude() , Toast.LENGTH_SHORT).show();
			}else {
				//Toast.makeText(getApplicationContext(), "N/w loc is null :: isGPS --- > " + isGPS, Toast.LENGTH_LONG).show();
			}
		}else {
			// location is null
			//Toast.makeText(getApplicationContext(), "Location is null :: isGPS --- > " + isGPS, Toast.LENGTH_LONG).show();
			////Log.i(Constants.TAG, "Location is null");
			DataUtil.locationInfo.setLatitude(loc.getLatitude() + "");
			DataUtil.locationInfo.setLongitude(loc.getLongitude() + "");
	//		Toast.makeText(getApplicationContext(), "Location is not null", Toast.LENGTH_LONG).show();
		}

		Intent intent = getIntent();
		//lv = (ListView)findViewById(R.id.list);
		lv = getListView();
		couponAdapter = new CouponAdapter( 
				getApplicationContext(),
				couList,false ); 
		lv.setAdapter( couponAdapter );
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				//Toast.makeText(getApplicationContext(), "List Clicked ",
				//  Toast.LENGTH_SHORT).show();

				showCouponDetailsScreen(position);

			}

		});



		//Log.i(Constants.TAG, " tempList --- > " + tempList);
		tempList = (List) getLastNonConfigurationInstance();
		////Log.i(Constants.TAG, " CouponList Size --- > " + couList.size());
		if(tempList == null) {
			fetchCoupons();
		}else {
			//Log.i(Constants.TAG, " tempList Size --- > " + tempList.size());
			for(int i = 0 ; i < tempList.size() ; i++){
				couList.add(tempList.get(i));
			}
		}

	}



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			//startActivity(createHomeIntent());
			//finish();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	//	@Override
	//	public void onConfigurationChanged(Configuration newConfig) {
	//		//Log.i(Constants.TAG, " **** onConfigurationChanged *** ");
	//		
	//		// TODO Auto-generated method stub
	//		super.onConfigurationChanged(newConfig);
	//		// Check the orientation of the screen
	//		if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	//			//Log.i(Constants.TAG, " ---- Landscape ---- ");
	//		}else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
	//			//Log.i(Constants.TAG, " ---- Portrait ----- ");
	//		}
	//				
	//	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		// TODO Auto-generated method stub
		final List<Coupon> data = collectMyLoadedData();
		return data;
	}

	private List<Coupon> collectMyLoadedData() {
		tempList = new ArrayList<Coupon>();
		for(int i = 0 ; i < couList.size() ; i++) {
			tempList.add(couList.get(i));
		}
		return tempList;

	}


	@Override
	public Dialog onCreateDialog(int dialogId) {
		Dialog dialog = null;
		switch (dialogId) {
		case PRO_DIALOG:
			ProgressDialog dialog1 = new ProgressDialog(this);
			dialog1.setMessage(AlertMsgUtil.getLoadingMessageText());
			dialog1.setIndeterminate(true);
			dialog1.setCancelable(true);
			dialog = dialog1;
		}
		return dialog;
	}


	public static Intent createHomeIntent() {
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(Intent.CATEGORY_HOME);
		return intent;
	}




	@Override
	protected void onResume() {
		//fetchCoupons();
		//searchButton.requestFocus();
		DataUtil.CURRENT_SCREEN = PageManager.COUPON_LIST_SCREEN;
		locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, Integer.parseInt(Constants.MIN_TIME), Integer.parseInt(Constants.MIN_DISTANCE), finder);
		locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, Integer.parseInt(Constants.MIN_TIME), Integer.parseInt(Constants.MIN_DISTANCE), finder);
		hideKeyboard();
		super.onResume();
	}


	@Override  
	protected void onPause() {  
		//remove the listener  
		locationMgr.removeUpdates(finder);  
		super.onPause();  
	}  


	//ProgressDialog progressDialog = null;

	private Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {

			if(isSearch) {
				settingBtn.setBackgroundResource(ImageUtils.getBackIamge());
			}else {
				settingBtn.setBackgroundResource(ImageUtils.getSettingsImage());
			}

			switch(msg.what) {
			case 0:
				if(couList != null) {
					if(couList.size() > 0) {
						couponAdapter.clear();
						couponAdapter.notifyDataSetChanged();
						lv.setAdapter(couponAdapter);
						//couponAdapter.notifyDataSetInvalidated();
						for(int  i=0;i<couList.size();i++){
							couponAdapter.add(couList.get(i));
						}
						couponAdapter.notifyDataSetChanged();
						removeDialog(PRO_DIALOG);
					}else {
						removeDialog(PRO_DIALOG);
						Intent intent = new Intent(getApplicationContext(),NotFoundScreen.class);
						intent.putExtra("NotFoundType", "CouponNotFound");
						startActivity(intent);
						//finish();
					}
				}
				break;
			case 1:
				Toast.makeText(getApplicationContext(), "Error while getting coupon list!", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	protected void fetchCoupons() {
		isSearch = false;
		lv.setAdapter(null);
		//progressDialog = ProgressDialog.show(this, "", AlertMsgUtil.getLoadingMessageText());
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					couList = WSSender.sendSearchCouponBasedOnLocationRequest(new LocationInfo());
					messageHandler.sendMessage(Message.obtain(messageHandler,0));
				}catch(IOException ioe){
					ioe.printStackTrace();
					//Toast.makeText(getApplicationContext(), "Connection Failure! Check your internet connection.", Toast.LENGTH_LONG);
					messageHandler.sendMessage(Message.obtain(messageHandler,1));
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}


	protected void fetchCoupons(final String keyword) {
		isSearch = true;
		//progressDialog = ProgressDialog.show(this, "", AlertMsgUtil.getLoadingMessageText());
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					couList = WSSender.searchCouponBasedOnKeyword(keyword);
					//Log.i(Constants.TAG, " Search Coupon List Size ----- > " + couList.size());
					messageHandler.sendMessage(Message.obtain(messageHandler,0));
				}catch (Exception e) {
					// TODO: handle exception
					messageHandler.sendMessage(Message.obtain(messageHandler,2));
				}

			}
		}.start();
	}


	void showCouponDetailsScreen(int pos) {
		Coupon coupon = couList.get(pos);
		Intent intent = new Intent(this,CouponDetailsScreen.class);

		intent.putExtra("couponObj", coupon);
		intent.putExtra("MyWallet", false);
		startActivity(intent);


	}


	void showDialog(String alertMsg) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Info");
		alertDialog.setMessage(alertMsg);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				return;
			} 
		}); 
		alertDialog.show();
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == signoutBtn) {
			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean("loginStatus", false);
			// Don't forget to commit your edits!!!
			editor.commit();
			Intent i = new Intent(this, MyActivity.class);
			startActivity(i);

			finish();
		}else if(v  == settingBtn) {
			if(isSearch){
				isSearch = false;
				fetchCoupons();
			}else{
				Intent i = new Intent(this, SettingsScreen.class);
				i.putExtra("fromCouponScreen", true);
				startActivity(i);
			}
		}else if(v == searchButton) {
			InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			mgr.hideSoftInputFromWindow(searchButton.getWindowToken(),0);
			if(searchText.getText() != null && searchText.getText().toString().length() > 0) {
				couponAdapter.clear();
				couponAdapter.notifyDataSetChanged();
				fetchCoupons(searchText.getText().toString());
			}
		}
	}
	
	public void hideKeyboard() {
//		InputMethodManager mgr = (InputMethodManager) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		mgr.hideSoftInputFromWindow(searchButton.getWindowToken(),0);
	}


//	@Override
//	public void onLocationChanged(Location location) {
//		// TODO Auto-generated method stub
//
//		try {
//			if (location == null) {
//				return;
//			}
//			double curr_lat = formatFraction(location.getLatitude());
//			double curr_longi = formatFraction(location.getLongitude());
//
//			//			//Log.i(Constants.TAG, " ************ LOCATION INFO ************************ ");
//			//			//Log.i(Constants.TAG, " Lati --- >  " + curr_lat + "::: Longi ---- > " + curr_longi);
//			//			//Log.i(Constants.TAG, " ************ LOCATION INFO ************************ ");
//
//			//Toast.makeText(context, "Location Changed --  Lati -> " + curr_lat + ": Longi - > " + curr_longi, Toast.LENGTH_SHORT).show();
//
//			DataUtil.locationInfo.setLatitude(curr_lat+"");
//			DataUtil.locationInfo.setLongitude(curr_longi+"");
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	public double formatFraction(double d) {
//		try {
//			NumberFormat nf = NumberFormat.getNumberInstance();
//			nf.setGroupingUsed(false); // don't group by threes
//			nf.setMinimumFractionDigits(5);
//			nf.setMaximumFractionDigits(5);
//			String sd = nf.format(d);
//			return Double.valueOf(sd.trim()).doubleValue();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return d;
//	}
//
//
//
//	@Override
//	public void onProviderDisabled(String provider) {
//		// TODO Auto-generated method stub
//
//	}
//
//
//
//	@Override
//	public void onProviderEnabled(String provider) {
//		// TODO Auto-generated method stub
//
//	}
//
//
//
//	@Override
//	public void onStatusChanged(String provider, int status, Bundle extras) {
//		// TODO Auto-generated method stub
//
//	}
}




//TODO: To be moved as a separate class
//class LocationFinder implements LocationListener {
//
//	Context context;
//	public LocationFinder(Context con) {
//		this.context = con;
//	}
//
//	@Override
//	public void onLocationChanged(Location location) {
//		try {
//			if (location == null) {
//				return;
//			}
//			double curr_lat = formatFraction(location.getLatitude());
//			double curr_longi = formatFraction(location.getLongitude());
//
//			//			//Log.i(Constants.TAG, " ************ LOCATION INFO ************************ ");
//			//			//Log.i(Constants.TAG, " Lati --- >  " + curr_lat + "::: Longi ---- > " + curr_longi);
//			//			//Log.i(Constants.TAG, " ************ LOCATION INFO ************************ ");
//
//			//Toast.makeText(context, "Location Changed --  Lati -> " + curr_lat + ": Longi - > " + curr_longi, Toast.LENGTH_SHORT).show();
//			DataUtil.locationInfo.setLatitude(curr_lat+"");
//			DataUtil.locationInfo.setLongitude(curr_longi+"");
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	
//
//
//
//	@Override
//	public void onProviderDisabled(String provider) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onProviderEnabled(String provider) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onStatusChanged(String provider, int status, Bundle extras) {
//		// TODO Auto-generated method stub
//
//	}
//	
//	
//	public double formatFraction(double d) {
//		try {
//			NumberFormat nf = NumberFormat.getNumberInstance();
//			nf.setGroupingUsed(false); // don't group by threes
//			nf.setMinimumFractionDigits(5);
//			nf.setMaximumFractionDigits(5);
//			String sd = nf.format(d);
//			return Double.valueOf(sd.trim()).doubleValue();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return d;
//	}
//}
