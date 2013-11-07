package com.oonusave.coupon;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.GeneralResponse;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.util.Utils;
import com.oonusave.coupon.ws.WSSender;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.LayoutInflater;
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

public class TermsAndCondScreen extends BaseActivity implements OnClickListener{
	
	ImageButton backButton,sendMailBtn;
	TextView titleBarTextView,contentTextView;
	int position = 0;
	int size = DataUtil.termsAndCondEng.length;
	Button nextBtn,preBtn;

	EditText emailText01;
	private static final int PRO_DIALOG = 0;
	private static final int DEFAULT_DIALOG = 1;
	
	TextView pageNumber;
	int pageNo = 1;
	String errorMessage = ""; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.terms_and_condition);
		//setContentView(R.layout.category_screen);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, COUNTRIES));

		//((TextView)findViewById(R.id.titleText)).setText("Coupons");
		backButton = (ImageButton)findViewById(R.id.btnLeft);
		backButton.setVisibility(ImageButton.VISIBLE);
		backButton.setBackgroundResource(ImageUtils.getBackIamge());
		backButton.setOnClickListener(this);
		
		sendMailBtn = (ImageButton)findViewById(R.id.btnRight);
		sendMailBtn.setVisibility(ImageButton.VISIBLE);
		sendMailBtn.setBackgroundResource(R.drawable.sendmail_button);
		sendMailBtn.setOnClickListener(this);
				
		nextBtn = (Button) findViewById(R.id.nextBtn);
		nextBtn.setBackgroundResource(ImageUtils.getNextBtn());
		preBtn = (Button) findViewById(R.id.preBtn);
		preBtn.setBackgroundResource(ImageUtils.getPrevBtn());
		if(pageNo == 1) {
			preBtn.setVisibility(Button.INVISIBLE);
		}
		
		pageNumber = (TextView) findViewById(R.id.pageNumber);
		pageNumber.setText(pageNo+" of "+ size);
				
		nextBtn.setOnClickListener(this);
		preBtn.setOnClickListener(this);
		
		//((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);
		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getTandCTitleStr());
		//titleBarTextView.setText(DataUtil.termsAndCondDan[position]);
		
		contentTextView = (TextView) findViewById(R.id.contentTextView);
		//contentTextView.setText(Html.fromHtml(DataUtil.priLang.equalsIgnoreCase("English") ? DataUtil.TandCTextEng : DataUtil.TandCTextDAN));
		contentTextView.setText(Html.fromHtml(DataUtil.termsAndCondEng[position] ));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == backButton) {
			finish();
		}else if(v == sendMailBtn) {
			//invokeEmailApplication();
			//sendTandC();
			showDialog(DEFAULT_DIALOG);
		}else if(v == nextBtn) {
			preBtn.setVisibility(Button.VISIBLE);
			if(position <= size - 2){
				position++;
				++pageNo;
				//pageNumber.setText(++pageNo+"");
				if(pageNo == size) {
					nextBtn.setVisibility(Button.INVISIBLE);
				}else{
					nextBtn.setVisibility(Button.VISIBLE);
				}
				pageNumber.setText(pageNo+" of "+ size);
				contentTextView.setText(Html.fromHtml(DataUtil.termsAndCondEng[position] ));
			}
		}else if(v == preBtn) {
			nextBtn.setVisibility(Button.VISIBLE);
			if(position > 0) {
				--pageNo;
				if(pageNo == 1) {
					preBtn.setVisibility(Button.INVISIBLE);
				}else{
					preBtn.setVisibility(Button.VISIBLE);
				}
				position--;
				//pageNumber.setText(--pageNo+"");
				pageNumber.setText(pageNo+" of "+ size);
				contentTextView.setText(Html.fromHtml(DataUtil.termsAndCondEng[position]));
			}
		}
	}
		

	private void invokeEmailApplication() {
//		Intent intent4 = new Intent(Intent.ACTION_SENDTO,
//				  Uri.fromParts("mailto", "testemail@gmail.com", TitleTextUtils.getShortRegTandCStr()));
//				startActivity(intent4);
	
		Intent it = new Intent(Intent.ACTION_SEND);   
		//it.putExtra(Intent.EXTRA_EMAIL, "me@abc.com");
		it.putExtra(Intent.EXTRA_SUBJECT, "Mocansa Terms and Conditions");   
		it.putExtra(Intent.EXTRA_TEXT, DataUtil.TandCTextEng);   
		it.setType("text/plain");   
		startActivity(Intent.createChooser(it, "Choose Email Client"));
	
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
			break;
			
		case DEFAULT_DIALOG :{
			
			AlertDialog.Builder alertBuilder = new
			AlertDialog.Builder(this);
			alertBuilder.setTitle(TitleTextUtils.getSendMailTitleMsgStr())
			.setNegativeButton(TitleTextUtils.getCancelBtnText(), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			})
			.setPositiveButton(TitleTextUtils.getSendBtnText(), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					
					if(emailText01.getText() == null || emailText01.getText().toString().length() <= 0) {
						Toast.makeText(getApplicationContext(), AlertMsgUtil.getEmptyEmailAddressMessage(), Toast.LENGTH_SHORT).show();
						return;
					}
					if(emailText01.getText().toString().indexOf(",") != -1) {
						String emailIds[] = emailText01.getText().toString().split(",");
						for(int i = 0 ; i < emailIds.length ; i++) {
							if(!Utils.getEmailValidationStatus(emailIds[i].toString())) {
								Toast.makeText(getApplicationContext(), AlertMsgUtil.getInvalidEmailAddressMessage(), Toast.LENGTH_SHORT).show();
								return;
							}
						}
					}else {
						if(!Utils.getEmailValidationStatus(emailText01.getText().toString())) {
							Toast.makeText(getApplicationContext(), AlertMsgUtil.getInvalidEmailAddressMessage(), Toast.LENGTH_SHORT).show();
							return;
						}
					}


					if(emailText01.getText() != null && emailText01.getText().toString().length() > 0) {
						dialog.dismiss();
						sendTandC();
					}
				}
			});
			LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.forgot_password_dialog,null);
			TextView Text01 = (TextView)layout.findViewById(R.id.Text01);
			emailText01 = (EditText)layout.findViewById(R.id.EditText01);
			Text01.setText(TitleTextUtils.getTandCMsgStr());
			//Text01.setText("");
			alertBuilder.setView(layout);
			dialog = alertBuilder.create();
			break;
		}
		}
		return dialog;	
	}


	void sendTandC() {
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					GeneralResponse generalResponse = WSSender.sendTandC(emailText01.getText().toString());
					if(generalResponse.isSuccess())
						sendMailHandler.sendMessage(Message.obtain(sendMailHandler, 0, generalResponse.getMessage()));
					else 
						sendMailHandler.sendMessage(Message.obtain(sendMailHandler, 1, generalResponse.getMessage()));
				}catch(Exception e) {
					e.printStackTrace();
					errorMessage = e.getMessage();
					sendMailHandler.sendMessage(Message.obtain(sendMailHandler, 2, errorMessage));
				}
			}
		}.start();
	}
	
	private Handler sendMailHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0:
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getTandCSendSuccessMessage(), Toast.LENGTH_SHORT).show();
				finish();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getTandCSendFailureMessage(), Toast.LENGTH_SHORT).show();
				break;
			case 2:
				
				Toast.makeText(getApplicationContext(), errorMessage == null ? "Error" : errorMessage , Toast.LENGTH_SHORT).show();
				break;	
			}
		}
	};

	
	
	
	
}
