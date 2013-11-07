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
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 
 * @author Ramesh
 *
 */

public class FeedbackScreen2 extends BaseActivity implements OnClickListener{
		
	ImageButton sendBtn,backBtn;
	EditText subjectText,contentText;
	Button submitButton;
	private static final int PRO_DIALOG = 0;
	TextView titleBarTextView,subjectTextView,messageTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.feedback_view_2);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		
		
		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		//subjectTextView  = (TextView) findViewById(R.id.subjectTextView);
		//messageTextView  = (TextView) findViewById(R.id.messageTextView);
		
		titleBarTextView.setText(TitleTextUtils.getFeebackScr2TitleText());
		//subjectTextView.setText(TitleTextUtils.getFeedbackScr2SubjectText());
		//messageTextView.setText(TitleTextUtils.getFeedbackScr2MessageText());
		
		sendBtn = (ImageButton)findViewById(R.id.btnRight);
		sendBtn.setVisibility(ImageButton.INVISIBLE);
		//sendBtn.setBackgroundResource(R.drawable.home_btn);

		backBtn = (ImageButton)findViewById(R.id.btnLeft);
		backBtn.setVisibility(ImageButton.VISIBLE);
		//backBtn.setBackgroundResource(R.drawable.back_button);
		backBtn.setBackgroundResource(ImageUtils.getBackIamge());

		subjectText = (EditText) findViewById(R.id.subject_text);
		contentText = (EditText) findViewById(R.id.content_text);
		
		subjectText.setHint(TitleTextUtils.getFeedbackScr2SubjectText());
		contentText.setHint(TitleTextUtils.getFeedbackScr2MessageText());
		
		submitButton = (Button) findViewById(R.id.submit_button);	
		submitButton.setBackgroundResource(ImageUtils.getSubmitImage());
		//sendBtn.setOnClickListener(this);
		backBtn.setOnClickListener(this);
		submitButton.setOnClickListener(this);
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.FEEDBACK_SCREEN2;
		super.onResume();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == submitButton){
			if(subjectText.getText() == null || subjectText.getText().toString().trim().length() <= 0) {
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getfeedbackEmptySubject(),Toast.LENGTH_SHORT).show();
				return;
			}
			if(contentText.getText() == null || contentText.getText().toString().trim().length() <= 0) {
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getFeedbackEmptyComments(),Toast.LENGTH_SHORT).show();
				return;
			}
			showDialog(PRO_DIALOG);
			sendFeedback();

		}else if(v == backBtn) {
			finish();
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
		new Thread() {
			public void run() {
				try {
					if(WSSender.sendFeebackRequest(subjectText.getText().toString(), contentText.getText().toString())){
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
