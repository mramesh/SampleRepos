package com.oonusave.coupon;

import com.oonusave.coupon.R;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.TitleTextUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * 
 * @author Ramesh
 *
 */
public class MoreScreen1 extends BaseActivity implements OnItemClickListener,OnFocusChangeListener{

	ImageButton backButton;

	TextView titleBarTextView;
	public static final String PREFS_NAME = "MyPrefsFile";
	SharedPreferences settings = null;

	static final int PRO_DIALOG = 0, TEST_DIALOG = 2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		//setContentView(R.layout.more);
		setContentView(R.layout.more);

		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		//((TextView)findViewById(R.id.titleText)).setText("More...");
		backButton = (ImageButton)findViewById(R.id.btnLeft);
		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getMoreScreenTitleText());
		backButton.setVisibility(ImageButton.INVISIBLE);
		//backButton.setBackgroundResource(R.drawable.back_button);

		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);

		GridView mGrid = (GridView) findViewById(R.id.grid_view);
		mGrid.setOnItemClickListener(this);
		mGrid.setOnFocusChangeListener(this);
		mGrid.setAdapter(new ImageAdapter(getApplicationContext()));
		mGrid.setVerticalSpacing(20);
		mGrid.setGravity(Gravity.CENTER_VERTICAL);
		mGrid.setHorizontalSpacing(20);
		mGrid.setNumColumns(3);
		mGrid.setColumnWidth(60);

		settings = getSharedPreferences(PREFS_NAME, 0);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.MORE_SCREEN;
		super.onResume();
	}



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			//startActivity(createHomeIntent());
			//finish();
			//System.exit(0);
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}


	public Dialog onCreateDialog(int dialogId) {
		Dialog dialog = null;
		switch (dialogId) {
		case PRO_DIALOG:
			ProgressDialog dialog1 = new ProgressDialog(this);
			dialog1.setMessage(AlertMsgUtil.getLoadingMessageText());
			dialog1.setIndeterminate(true);
			dialog1.setCancelable(true);
			dialog = dialog1;

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


	public static Intent createHomeIntent() {
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addCategory(Intent.CATEGORY_HOME);
		return intent;
	}





	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub

	}



	@Override
	public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "# MoreScreen1 # ------  --------------");
		//Log.i(Constants.TAG, "# MoreScreen1 # ------ v--- > " + v + " ---- pos --- > " + pos + " --- id ---- > " + id);
		if(pos == 8) {
			// Profile
			if(DataUtil.userName.equals("demouser@mocansa.com")) {
				showDialog(TEST_DIALOG);
			}else {
				
				SharedPreferences.Editor editor = settings.edit();
				editor.putBoolean("loginStatus", false);
				//editor.putString("userName", "");
				editor.putString("password", "");
				// Don't forget to commit your edits!!!
				editor.commit();
				Intent i = new Intent(this, MyActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);
				finish();

			}

		}else if(pos == 0) {
			// Used Coupon
			Intent intent = new Intent(this,SettingsMain.class);
			startActivity(intent);
		}else if(pos == 1) {
			// Expiry Coupon
			if(DataUtil.userName.equals("demouser@mocansa.com")) {
				showDialog(TEST_DIALOG);
			}else {
				Intent intent = new Intent(this,UsedCouponScreen.class);
				startActivity(intent);
			}


		}else if(pos == 2) {
			// Store Finder
			if(DataUtil.userName.equals("demouser@mocansa.com")) {
				showDialog(TEST_DIALOG);
			}else {
				Intent intent = new Intent(this,ExpiryCouponsScreen.class);
				startActivity(intent);
			}


		}else if(pos == 3) {
			// Feeback
			Intent intent = new Intent(this,StoreListScreen.class);
			startActivity(intent);

		}else if(pos == 4 ) {
			//Inbox
			Intent intent = new Intent(this,FAQsScreen.class);
			startActivity(intent);

		}else if(pos == 5) {

			if(DataUtil.userName.equals("demouser@mocansa.com")) {
				showDialog(TEST_DIALOG);
			}else {
				Intent intent = new Intent(this,FeedbackMainScreen.class);
				startActivity(intent);
			}

		}else if(pos == 6) {

			if(DataUtil.userName.equals("demouser@mocansa.com")) {
				showDialog(TEST_DIALOG);


			}else{ 

				Intent intent = new Intent(this,FavouriteStoresScreen.class);
				startActivity(intent);
			}
		}else if(pos == 7) {
			Intent intent = new Intent(this,CompetionMainScreen.class);
			startActivity(intent);
		}



		//		}else if(pos == 5) {
		//			if(DataUtil.userName.equals("demouser@mocansa.com")) {
		//				showDialog(TEST_DIALOG);
		//			}else {
		//			Intent intent = new Intent(this,FavouriteStoresScreen.class);
		//			startActivity(intent);
		//			}
		//
		//		}else if(pos == 6) {
		//			// FAQ
		//			//Intent intent = new Intent(this,MapScreenTest.class);
		//			
		//			if(DataUtil.userName.equals("demouser@mocansa.com")) {
		//				showDialog(TEST_DIALOG);
		//			}else {
		//			Intent intent = new Intent(this,MyMapStore.class);
		//			startActivity(intent);
		//			}
		//		}else if(pos == 7 ) {
		//			//My Wallet
		//			if(DataUtil.userName.equals("demouser@mocansa.com")) {
		//				showDialog(TEST_DIALOG);
		//			}else {
		//				Intent intent = new Intent(this,FeedbackMainScreen.class);
		//				startActivity(intent);
		//			}
		//		}else if(pos == 8) {
		//			if(DataUtil.userName.equals("demouser@mocansa.com")) {
		//				showDialog(TEST_DIALOG);
		//			}else {
		//				Intent intent = new Intent(this,FAQsScreen.class);
		//				startActivity(intent);
		//			}
		//		}
		//		}else if(pos == 9) {
		//			Intent intent = new Intent(this,SettingsScreen.class);
		//			startActivity(intent);
		//		}
	}
}
