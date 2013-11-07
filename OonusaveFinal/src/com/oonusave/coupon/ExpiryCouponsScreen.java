package com.oonusave.coupon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Coupon;
import com.oonusave.coupon.model.Store;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.ws.WSSender;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
public class ExpiryCouponsScreen extends BaseListActivity implements OnClickListener{
	
	ImageButton backButton = null;
	CouponAdapter couponAdapter = null;
	List<Coupon> couList = new ArrayList<Coupon>();
	private static final int PRO_DIALOG = 0;
	boolean search = false;
	Button submitBtn;
	EditText dateRangeText;
	String errorMessage = "";
	ListView lv ;
	TextView titleBarTextView;//,noOfDaysTextView;
	private boolean isFirst = true;
	List<Coupon> tempList = null;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.expiry_coupon_view);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		backButton = (ImageButton)findViewById(R.id.btnLeft);
		backButton.setVisibility(ImageButton.VISIBLE);
		//backButton.setBackgroundResource(R.drawable.back_button);
		backButton.setBackgroundResource(ImageUtils.getBackIamge());
		backButton.setOnClickListener(this);

		submitBtn = (Button)findViewById(R.id.submit_button);
		submitBtn.setBackgroundResource(ImageUtils.getSubmitImage());
		submitBtn.setOnClickListener(this);
		dateRangeText = (EditText)findViewById(R.id.searchText);
		dateRangeText.setHint(TitleTextUtils.getExpiryCouponNoOfDaysText());
		
		//noOfDaysTextView = (TextView) findViewById(R.id.noOfDaysTextView);
		//noOfDaysTextView.setText(TitleTextUtils.getExpiryCouponNoOfDaysText());
		
		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getExpiryCouponTitleText());

		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);

		couponAdapter = new CouponAdapter( 
				getApplicationContext(),
				couList,false ); 
		setListAdapter( couponAdapter );

		lv = getListView();

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
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.EXPIRY_COUPON_SCREEN;
		super.onResume();
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
		Coupon coupon = couList.get(pos);
		Intent intent = new Intent(this,CouponDetailsScreen.class);
		intent.putExtra("couponObj", coupon);
		startActivity(intent);
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



	private Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0:
				
				//Log.i(Constants.TAG, "Coupon List Size ------ > " + couList.size());
				
				if(couList != null && couList.size() > 0) {	
					couponAdapter.notifyDataSetChanged();
					for(int  i=0;i<couList.size();i++){
						couponAdapter.add(couList.get(i));
					}
					couponAdapter.notifyDataSetChanged();
				}else {
					//Toast.makeText(getApplicationContext(), AlertMsgUtil.getNoCouponsFoundAlertMsg(), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(getApplicationContext(),NotFoundScreen.class);
					intent.putExtra("NotFoundType", "ExpiryCouponNotFound");
					startActivity(intent);
				}
				break;

			case 1:
				Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
				break;

			case 2:
				Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
				break;
			}

		}
	};



	protected void fetchCoupons() {
		showDialog(PRO_DIALOG);
//		int dateRange = 0;
//		if(dateRangeText.getText() != null ) {
//			try {
//				dateRange = Integer.parseInt(dateRangeText.getText().toString());
//			}catch(Exception e) {
//				dateRange = 2;
//			}
//		}
//		final int dRange = dateRange;	
		new Thread() {
			public void run() {
				try {
					couList = WSSender.send10GetExpiryCoupons(DataUtil.userDetails.getUserId());
//					if(isFirst){
//						couList = WSSender.send10GetExpiryCoupons(DataUtil.userDetails.getUserId());
//						isFirst = false;
//					}
//					else {
//						couList = WSSender.sendGetExpiryCoupons(DataUtil.userDetails.getUserId(), dRange);
//					}
					messageHandler.sendMessage(Message.obtain(messageHandler,0));
				}catch(IOException ioe) {
					if(isOnline()) {
						errorMessage = AlertMsgUtil.getCommonErrorMsg();
					}else {
						errorMessage = AlertMsgUtil.getConnectFailureMessage();
					}
					messageHandler.sendMessage(Message.obtain(messageHandler,1));
				}catch (Exception e) {
					errorMessage = AlertMsgUtil.getCommonErrorMsg();
					messageHandler.sendMessage(Message.obtain(messageHandler,2));
				}
			}
		}.start();
	}
	
	public void fetchCoupons(final String dateRange) {
		showDialog(PRO_DIALOG);
		
		new Thread() {
			public void run() {
				try{
					int dateRan = 0;
					if(dateRange != null ) {
						try {
							dateRan = Integer.parseInt(dateRange);
						}catch(Exception e) {
							dateRan = 0;
						}
					}
				couList = WSSender.sendGetExpiryCoupons(DataUtil.userDetails.getUserId(), dateRan);
				messageHandler.sendMessage(Message.obtain(messageHandler,0));
				}catch(IOException ioe) {
					if(isOnline()) {
						errorMessage = AlertMsgUtil.getCommonErrorMsg();
					}else {
						errorMessage = AlertMsgUtil.getConnectFailureMessage();
					}
					messageHandler.sendMessage(Message.obtain(messageHandler,1));
				}catch (Exception e) {
					errorMessage = AlertMsgUtil.getCommonErrorMsg();
					messageHandler.sendMessage(Message.obtain(messageHandler,2));
				}
			}
		}.start();
	}
	
	
	



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == backButton) {
			if(search) {
				search = false;
				couponAdapter.clear();
				fetchCoupons();
			}else {
				finish();
			}
		}else if(v == submitBtn) {

			InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			mgr.hideSoftInputFromWindow(submitBtn.getWindowToken(),0);
			if(dateRangeText.getText() == null || dateRangeText.getText().toString().trim().length() <= 0) {
				//Toast.makeText(this, "Please enter no. of days!", Toast.LENGTH_SHORT).show();
				return;
			}
			//couList.clear();
			couponAdapter.clear();
			//lv.setAdapter(null);
			couponAdapter.notifyDataSetChanged();
			search = true;
			fetchCoupons(dateRangeText.getText().toString().trim());
		}
	}
	
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if(cm == null || cm.getActiveNetworkInfo() == null) {
			return false;
		}
		return cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}
	
}
