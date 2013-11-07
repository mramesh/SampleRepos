package com.oonusave.coupon;

import com.oonusave.coupon.R;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 
 * @author Ramesh
 *
 */

public class FeedbackMainScreen extends BaseActivity implements OnClickListener{

	ImageButton backBtn;
	Button rateAppBtn,reportBugBtn;
	
	static final int TEST_DIALOG = 0;
	
	TextView titleBarTextView,rateThisAppTextView,rateThisAppDescTextView,reportABugTextView,reportABugDescTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.feedback_view_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);
		
		backBtn = (ImageButton)findViewById(R.id.btnLeft);
		backBtn.setVisibility(ImageButton.VISIBLE);
		//backBtn.setBackgroundResource(R.drawable.back_button);
		backBtn.setBackgroundResource(ImageUtils.getBackIamge());
		backBtn.setOnClickListener(this);
		
		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		rateThisAppTextView = (TextView) findViewById(R.id.rateThisAppTextView);
		rateThisAppDescTextView = (TextView) findViewById(R.id.rateThisAppDescTextView);
		reportABugTextView = (TextView) findViewById(R.id.reportABugTextView);
		reportABugDescTextView = (TextView) findViewById(R.id.reportABugDescTextView);
		
		titleBarTextView.setText(TitleTextUtils.getFeedbackTitleText());
		rateThisAppTextView.setText(TitleTextUtils.getFeedbackRateThisAppTitleText());
		rateThisAppDescTextView.setText(TitleTextUtils.getFeedbackRateThisAppDescText());
		
		
		reportABugTextView.setText(TitleTextUtils.getFeedbackReportABugTitleText());
		reportABugDescTextView.setText(TitleTextUtils.getFeedbackReportABugDescText());
		
		
		rateAppBtn = (Button) findViewById(R.id.rate_app_button);
		reportBugBtn = (Button) findViewById(R.id.report_bug_button);
		rateAppBtn.setBackgroundResource(ImageUtils.getRateThisAppBtnImage());
		reportBugBtn.setBackgroundResource(ImageUtils.getReportABugBtnImage());
		rateAppBtn.setOnClickListener(this);
		reportBugBtn.setOnClickListener(this);
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.FEEDBACK_MAIN_SCREEN;
		super.onResume();
	}
	
	
	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		super.onPostResume();
		if(DataUtil.userName.equals("demouser@mocansa.com")) {
			showDialog(TEST_DIALOG);
		}
	}
	
	
	public Dialog onCreateDialog(int dialogId) {
		Dialog dialog = null;
		switch (dialogId) {
		
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


	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == backBtn ) {
			finish();
		}else if(v == rateAppBtn) {
			//
			startActivity(new Intent(this,FeedbackScreen1.class));
			
		}else if(v == reportBugBtn) {
			//
			startActivity(new Intent(this,FeedbackScreen2.class));
		}else{}
		
	}
}
