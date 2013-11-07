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

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
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
public class CouponsScreen extends BaseListActivity implements OnClickListener{


	ImageButton signoutBtn,settingBtn,backButton = null;
	CouponAdapter couponAdapter = null;
	List<Coupon> couList = new ArrayList<Coupon>();
	private static final int PRO_DIALOG = 0,TEST_DIALOG = 1;
	boolean search = false;
	Button submitBtn;
	EditText dateRangeText;
	String errorMessage = "";
	ListView lv ;
	TextView titleBarTextView;//,noOfDaysTextView;
	private boolean isFirst = true;
	List<Coupon> tempList = null;
	private boolean isSearch = false;

	public static final String PREFS_NAME = "MyPrefsFile";
	SharedPreferences settings = null;


	//LocationFinder finder = null;
	//private LocationManager locationMgr = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.coupon_view);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		settings = getSharedPreferences(PREFS_NAME, 0);
		couList = new ArrayList<Coupon>();
		//		backButton = (ImageButton)findViewById(R.id.btnLeft);
		//		backButton.setVisibility(ImageButton.VISIBLE);
		//		//backButton.setBackgroundResource(R.drawable.back_button);
		//		backButton.setBackgroundResource(ImageUtils.getBackIamge());
		//		backButton.setOnClickListener(this);
		//
		//		submitBtn = (Button)findViewById(R.id.submit_button);
		//		submitBtn.setBackgroundResource(ImageUtils.getSubmitImage());
		//		submitBtn.setOnClickListener(this);



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

		submitBtn = (Button)findViewById(R.id.submit_button);
		submitBtn.setBackgroundResource(ImageUtils.getSubmitImage());
		submitBtn.setOnClickListener(this);


		dateRangeText = (EditText)findViewById(R.id.searchText);
		dateRangeText.setHint(TitleTextUtils.getSearchMsgStr());

		//noOfDaysTextView = (TextView) findViewById(R.id.noOfDaysTextView);
		//noOfDaysTextView.setText(TitleTextUtils.getExpiryCouponNoOfDaysText());

		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getCouponListTitleText());
		DataUtil.mapScreen = false;

//		Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
//		toolbar.option1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.icon3,0,0);
//		toolbar.option1.setText(DataUtil.priLang.equalsIgnoreCase("English") ? "Map" : "Kort");

		//((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);
		//DataUtil.locationInfo = new LocationInfo();

		couponAdapter = new CouponAdapter( 
				getApplicationContext(),
				couList,false ); 
		setListAdapter( couponAdapter );

		lv = getListView();
		lv.setCacheColorHint(Color.TRANSPARENT);
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
	protected void onResume() {
		//fetchCoupons();
		//searchButton.requestFocus();
		DataUtil.CURRENT_SCREEN = PageManager.COUPON_LIST_SCREEN;

		//DataUtil.locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, Integer.parseInt(Constants.MIN_TIME), Integer.parseInt(Constants.MIN_DISTANCE), DataUtil.finder);
		//locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, Integer.parseInt(Constants.MIN_TIME), Integer.parseInt(Constants.MIN_DISTANCE), finder);
		//hideKeyboard();
		super.onResume();
	}


	@Override  
	protected void onPause() {  
		super.onPause();  
	}  



	@Override
	public Object onRetainNonConfigurationInstance() {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "CategoryListScreen ----- onRetainNonConfigurationInstance() --- ");
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


	void showCouponDetailsScreen(int pos) {

		if(DataUtil.userName.equals("demouser@mocansa.com")) {
			showDialog(TEST_DIALOG);
		}else {
			Coupon coupon = couList.get(pos);
			Intent intent = new Intent(this,CouponDetailsScreen.class);
			intent.putExtra("couponObj", coupon);
			startActivity(intent);
		}
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
			break;

		case TEST_DIALOG:	

			AlertDialog.Builder alertBuilder = new
			AlertDialog.Builder(this);
			alertBuilder.setTitle("Demo User")

			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			})
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					showRegistrationScreen();
				}
			});

			LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.custom_status_dialog,null);
			TextView Text01 = (TextView)layout.findViewById(R.id.Text01);
			Text01.setText("You are in the demo account. kindly register your self to use this feature. Do you want to register now?");
			alertBuilder.setView(layout);
			dialog = alertBuilder.create();
			break;
		}
		return dialog;
	}


	void showRegistrationScreen() {
		Intent intent = new Intent(this,ShortRegistrationScreen.class);
		startActivity(intent);
		finish();
	}



	private Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
			removeDialog(PRO_DIALOG);
			if(isSearch) {
				settingBtn.setBackgroundResource(ImageUtils.getBackIamge());
			}else {
				settingBtn.setBackgroundResource(ImageUtils.getSettingsImage());
			}
			switch(msg.what) {
			case 0:
				//Log.i(Constants.TAG, "Coupon List Size ------ > " + couList.size());
				if(couList != null && couList.size() > 0) {
					couponAdapter.clear();
					for(int  i=0;i<couList.size();i++){
						couponAdapter.add(couList.get(i));
					}
					couponAdapter.notifyDataSetChanged();
				}else {
					//Toast.makeText(getApplicationContext(), AlertMsgUtil.getNoCouponsFoundAlertMsg(), Toast.LENGTH_SHORT).show();
					//					Intent intent = new Intent(getApplicationContext(),NotFoundScreen.class);
					//					intent.putExtra("NotFoundType", "ExpiryCouponNotFound");
					//					startActivity(intent);
					removeDialog(PRO_DIALOG);
					Intent intent = new Intent(getApplicationContext(),NotFoundScreen.class);
					intent.putExtra("type", "one");
					intent.putExtra("NotFoundType", "CouponNotFound");
					startActivity(intent);
				}
				break;
			case 1:
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getConnectFailureMessage(), Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};



	protected void fetchCoupons() {
		isSearch = false;
		//lv.setAdapter(null);

		//progressDialog = ProgressDialog.show(this, "", AlertMsgUtil.getLoadingMessageText());
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					couList = WSSender.sendSearchCouponBasedOnLocationRequest(new LocationInfo());
					//Log.i(Constants.TAG, "CouponScreen ::: Coupon List Size ------ > " + couList.size());
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
		couponAdapter.clear();
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



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == signoutBtn) {
			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean("loginStatus", false);
			//editor.putString("userName", "");
			editor.putString("password", "");
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
		}else if(v == submitBtn) {

			InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			mgr.hideSoftInputFromWindow(submitBtn.getWindowToken(),0);
			if(dateRangeText.getText().toString().trim().length() <= 0) {
				return;
			}
			couList.clear();
			couponAdapter.clear();
			//lv.setAdapter(null);
			couponAdapter.notifyDataSetChanged();
			fetchCoupons(dateRangeText.getText().toString());
		}
	}
}






class LocationFinder implements LocationListener {

	Context context;
	public LocationFinder(Context con) {
		this.context = con;
	}

	@Override
	public void onLocationChanged(Location location) {
		try {
			if (location == null) {
				return;
			}
			double curr_lat = formatFraction(location.getLatitude());
			double curr_longi = formatFraction(location.getLongitude());
			//			//Log.i(Constants.TAG, " ************ LOCATION INFO ************************ ");
			//			//Log.i(Constants.TAG, " Lati --- >  " + curr_lat + "::: Longi ---- > " + curr_longi);
			//			//Log.i(Constants.TAG, " ************ LOCATION INFO ************************ ");
			//Toast.makeText(context, "Location Changed --  Lati -> " + curr_lat + ": Longi - > " + curr_longi, Toast.LENGTH_SHORT).show();
			DataUtil.locationInfo.setLatitude(curr_lat+"");
			DataUtil.locationInfo.setLongitude(curr_longi+"");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}


	public double formatFraction(double d) {
		try {
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setGroupingUsed(false); // don't group by threes
			nf.setMinimumFractionDigits(5);
			nf.setMaximumFractionDigits(5);
			String sd = nf.format(d);
			return Double.valueOf(sd.trim()).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}


}
