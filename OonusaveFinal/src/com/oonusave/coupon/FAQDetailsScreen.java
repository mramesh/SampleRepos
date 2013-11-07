package com.oonusave.coupon;


import com.oonusave.coupon.R;
import com.oonusave.coupon.model.FAQ;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
/**
 * 
 * @author Ramesh
 *
 */
public class FAQDetailsScreen extends BaseActivity implements OnClickListener{
	
	
	ImageButton backButton = null;
	TextView questionText,answerText;
	FAQ faq = null;
	TextView titleBarTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.faq_detail_view);		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, COUNTRIES));
		 
		
		
		//((TextView)findViewById(R.id.titleText)).setText("Coupons");
		backButton = (ImageButton)findViewById(R.id.btnLeft);
		backButton.setVisibility(ImageButton.VISIBLE);
		//backButton.setBackgroundResource(R.drawable.back_button);
		backButton.setBackgroundResource(ImageUtils.getBackIamge());
		backButton.setOnClickListener(this);
		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);
		
		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getFAQTitleText());
		
		questionText = (TextView) findViewById(R.id.questionText);
		answerText = (TextView) findViewById(R.id.answerText);
		Intent intent = getIntent();
		
		
		
		faq = (FAQ)intent.getSerializableExtra("faq");
		//fetchAnswer();	
		questionText.setText(faq.getQuestion());
		answerText.setText(faq.getAnswer());
				
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.FAQ_DETAILS_SCREEN;
		super.onResume();
	}
	
	
	
	//ProgressDialog progressDialog = null;
	
	private Handler messageHandler = new Handler() {
    	public void handleMessage(Message msg) {
    		//Log.i(Constants.TAG, "# FAQsDetailsScreen # ----- handleMessage() ------ ");
    		///super.handleMessage(msg);
    		questionText.setText(faq.getQuestion());
    		answerText.setText(faq.getAnswer());
    		//progressDialog.dismiss();
    	
    		
        }
    };
	
		
		
	    
	    
    void fetchAnswer() {
//	    	progressDialog = ProgressDialog.show(this, "", "processing...");
//	        new Thread() {
//	        	public void run() {
//	        		try {
//	        			faq = WSSender.sendFAQDetailsRequest(faq.getFaqId());
//	        			Thread.sleep(2);
//	        		} catch (InterruptedException e) {
//	            	
//	            }
//	        		messageHandler.sendEmptyMessage(0);
//	        		
//	            }
//	       }.start();
	    }
	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == backButton) {
			finish();
		}
	}
}
