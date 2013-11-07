package com.oonusave.coupon;

import java.io.IOException;

import com.oonusave.coupon.R;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.ws.WSSender;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author Ramesh
 *
 */
public class FeedbackScreen1 extends BaseActivity implements android.view.View.OnClickListener{


	ImageButton sendBtn,backBtn;
	EditText subjectText,contentText;
	Button submitButton;
	private static final int PRO_DIALOG = 0;
	Button starImg1,starImg2,starImg3,starImg4,starImg5;
	TextView titleBarTextView,ratingTextView,commentsTextView;
	
	int rating = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.feedback_view_1);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		sendBtn = (ImageButton)findViewById(R.id.btnRight);
		sendBtn.setVisibility(ImageButton.INVISIBLE);
		//sendBtn.setBackgroundResource(R.drawable.home_btn);

		backBtn = (ImageButton)findViewById(R.id.btnLeft);
		backBtn.setVisibility(ImageButton.VISIBLE);
		//backBtn.setBackgroundResource(R.drawable.back_button);
		backBtn.setBackgroundResource(ImageUtils.getBackIamge());
		
		starImg1 = (Button) findViewById(R.id.starImg1);
		starImg2 = (Button) findViewById(R.id.starImg2);
		starImg3 = (Button) findViewById(R.id.starImg3);
		starImg4 = (Button) findViewById(R.id.starImg4);
		starImg5 = (Button) findViewById(R.id.starImg5);
		
		starImg1.setOnClickListener(this);
		starImg2.setOnClickListener(this);
		starImg3.setOnClickListener(this);
		starImg4.setOnClickListener(this);
		starImg5.setOnClickListener(this);
		

		//subjectText = (EditText) findViewById(R.id.subject_text);
		contentText = (EditText) findViewById(R.id.content_text);
		contentText.setHint(TitleTextUtils.getFeedbackScr1CommentsText());
		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		ratingTextView = (TextView) findViewById(R.id.ratingTextView);
		//commentsTextView = (TextView) findViewById(R.id.commentsTextView);
		
		submitButton = (Button) findViewById(R.id.submit_button);	
		submitButton.setBackgroundResource(ImageUtils.getSubmitImage());
		titleBarTextView.setText(TitleTextUtils.getFeebackScr1TitleText());
		ratingTextView.setText(TitleTextUtils.getFeedbackScr1RatingText());
		//commentsTextView.setText(TitleTextUtils.getFeedbackScr1CommentsText());
		//sendBtn.setOnClickListener(this);
		backBtn.setOnClickListener(this);
		submitButton.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.FEEDBACK_SCREEN1;
		super.onResume();
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == submitButton){


//			if(subjectText.getText() == null || subjectText.getText().toString().length() <= 0) {
//				Toast.makeText(getApplicationContext(), "Subject cannot be empty!",Toast.LENGTH_SHORT).show();
//				return;
//			}

			if(contentText.getText() == null || contentText.getText().toString().trim().length() <= 0) {
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getFeedbackEmptyMessage(),Toast.LENGTH_SHORT).show();
				return;
			}
			
			
			
//			showDialog(PRO_DIALOG);
			sendFeedback();

		}else if(v == backBtn) {
			finish();
		}else if(v == starImg1) {
			
			//Log.i(Constants.TAG, "STAR IMAGE CLIKED ");
			starImg1.setBackgroundResource(R.drawable.star_userselected);
			starImg2.setBackgroundResource(R.drawable.star_selected);
			starImg3.setBackgroundResource(R.drawable.star_selected);
			starImg4.setBackgroundResource(R.drawable.star_selected);
			starImg5.setBackgroundResource(R.drawable.star_selected);
			rating = 1;
			
		}else if(v == starImg2) {
			starImg1.setBackgroundResource(R.drawable.star_userselected);
			starImg2.setBackgroundResource(R.drawable.star_userselected);
			starImg3.setBackgroundResource(R.drawable.star_selected);
			starImg4.setBackgroundResource(R.drawable.star_selected);
			starImg5.setBackgroundResource(R.drawable.star_selected);
			rating = 2;
			
		}else if(v == starImg3) {
			starImg1.setBackgroundResource(R.drawable.star_userselected);
			starImg2.setBackgroundResource(R.drawable.star_userselected);
			starImg3.setBackgroundResource(R.drawable.star_userselected);
			starImg4.setBackgroundResource(R.drawable.star_selected);
			starImg5.setBackgroundResource(R.drawable.star_selected);
			rating = 3;
			
		}else if(v == starImg4) {
			starImg1.setBackgroundResource(R.drawable.star_userselected);
			starImg2.setBackgroundResource(R.drawable.star_userselected);
			starImg3.setBackgroundResource(R.drawable.star_userselected);
			starImg4.setBackgroundResource(R.drawable.star_userselected);
			starImg5.setBackgroundResource(R.drawable.star_selected);
			rating = 4;
		}else if(v == starImg5) {
			starImg1.setBackgroundResource(R.drawable.star_userselected);
			starImg2.setBackgroundResource(R.drawable.star_userselected);
			starImg3.setBackgroundResource(R.drawable.star_userselected);
			starImg4.setBackgroundResource(R.drawable.star_userselected);
			starImg5.setBackgroundResource(R.drawable.star_userselected);
			rating = 5;
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
		}
		return dialog;
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			removeDialog(PRO_DIALOG);
		}
		return super.onKeyDown(keyCode, event);
	}


	private Handler messageHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			//Log.i(Constants.TAG, "Inside handle message ------ > " + msg.what);
			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0: 
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getFeedbackSubmittedSuccessMessage(),Toast.LENGTH_SHORT).show();
				finish();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getFeedbackSubmittedFailureMessage(),
						Toast.LENGTH_SHORT).show();
				break;
			case 2: 
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getConnectFailureMessage(),
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};



	private void sendFeedback() {
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {

					if(WSSender.sendFeebackRequest(rating+"", contentText.getText().toString())){
						messageHandler.sendMessage(Message.obtain(messageHandler,0));
					}else {
						messageHandler.sendMessage(Message.obtain(messageHandler,1));
					}
				} catch (IOException e) {
					messageHandler.sendMessage(Message.obtain(messageHandler,2));
				}

			}
		}.start();

	}



}
