package com.oonusave.coupon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Coupon;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.ws.WSSender;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
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
public class MyWalletScreen extends BaseListActivity implements OnClickListener{

	List<Coupon> couList = new ArrayList<Coupon>();
	CouponAdapter couponAdapter = null;
	ImageButton backButton =null;
	private static final int PRO_DIALOG = 0, TEST_DIALOG = 2;
	String errorMessage = "";
	ListView lv; 
	TextView titleBarTextView;
	List<Coupon> tempList = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);



		if(DataUtil.userName.equals("demouser@mocansa.com")) {
			showDialog(TEST_DIALOG);
		}else {


			setContentView(R.layout.my_wallet_screen);



			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
			backButton = (ImageButton)findViewById(R.id.btnLeft);
			backButton.setVisibility(ImageButton.INVISIBLE);
			//backButton.setBackgroundResource(R.drawable.back_button);
			backButton.setOnClickListener(this);
			((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);

			titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
			titleBarTextView.setText(TitleTextUtils.getMyWalletTitleText());

			//fetchCoupons();
			couponAdapter = new CouponAdapter( 
					getApplicationContext(),
					couList,true); 
			setListAdapter( couponAdapter );
			lv = getListView();
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// When clicked, show a toast with the TextView text
					showCouponDetailsScreen(couList.get(position));
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


	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.MY_WALLET_SCREEN;
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
					finish();
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




	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			//	    	  startActivity(createHomeIntent());
			//	    	  finish();
			//	    	  System.exit(0);
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}


	public static Intent createHomeIntent() {
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(Intent.CATEGORY_HOME);
		return intent;
	}



	private Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0:
				if(couList != null && couList.size() > 0) {
					couponAdapter.notifyDataSetChanged();
					//couponAdapter.notifyDataSetInvalidated();
					for(int  i=0;i<couList.size();i++){
						couponAdapter.add(couList.get(i));
					}
					couponAdapter.notifyDataSetChanged();
				}else {
					Intent intent = new Intent(getApplicationContext(),NotFoundScreen.class);
					intent.putExtra("NotFoundType", "MyWalletNotFound");
					intent.putExtra("type", "three");
					intent.putExtra("ShowBack", false);
					startActivity(intent);
					finish();
					//Toast.makeText(getApplicationContext(), "No coupons in your wallet!",Toast.LENGTH_SHORT).show();
				}
				break;
			case 1:
				Toast.makeText(getApplicationContext(), errorMessage,Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	protected void fetchCoupons() {
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					couList = WSSender.sendGetAllCouponInWallet(DataUtil.userId, DataUtil.userName, DataUtil.password);	
					//Log.i(Constants.TAG, "MyWallet Coupon Size ---- > " + couList.size());
					messageHandler.sendMessage(Message.obtain(messageHandler,0));
				} catch(IOException ioe){
					if(isOnline()) {
						errorMessage = AlertMsgUtil.getCommonErrorMsg();
					}else {
						errorMessage = AlertMsgUtil.getConnectFailureMessage();
					}
					messageHandler.sendMessage(Message.obtain(messageHandler,0));
				}catch (Exception e) {
					errorMessage = AlertMsgUtil.getCommonErrorMsg();
					messageHandler.sendMessage(Message.obtain(messageHandler,1));
				}
			}
		}.start();
	}



	void showCouponDetailsScreen(Coupon coupon) {
		Intent intent = new Intent(this,CouponDetailsScreen.class);
		intent.putExtra("MyWallet", true);
		intent.putExtra("couponObj", coupon);
		startActivity(intent);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == backButton) {
			finish();
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
