package com.oonusave.coupon;

import java.io.IOException;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Login;
import com.oonusave.coupon.model.RegistrationResponse;
import com.oonusave.coupon.model.UserDetails;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.util.Utils;
import com.oonusave.coupon.ws.WSSender;
/**
 * 
 * @author Ramesh
 *
 */
public class ShortRegistrationScreen extends BaseActivity implements android.view.View.OnClickListener{

	EditText fNameText,lNameText,emailText,passwordText,conPasswordText;
	Button submitBtn,readMoreBtn;
	ImageButton homeButton,sendMailBtn;
	private static final int PRO_DIALOG = 0;
	String errorMessage = "";
	String infoMessage = "";

	TextView titleBarTextView,regTitleTextView,firstNameTextView,lastNameTextView,emailTextView,pwdTextView,confirmPwdTextView,tandcTextView;
	CheckBox tandcBox;
	SharedPreferences settings = null;
	public static final String PREFS_NAME = "MyPrefsFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.short_registration_view);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);

		//((TextView)findViewById(R.id.titleText)).setText("SignUp");

		sendMailBtn = (ImageButton)findViewById(R.id.btnRight);
		sendMailBtn.setVisibility(ImageButton.INVISIBLE);
		//sendMailBtn.setBackgroundResource(ImageUtils.getBackIamge());
		//sendMailBtn.setOnClickListener(new OnClickListener());

		homeButton = (ImageButton)findViewById(R.id.btnLeft);
		homeButton.setVisibility(ImageButton.VISIBLE);
		homeButton.setClickable(true);
		//homeButton.setBackgroundResource(R.drawable.back_button);
		homeButton.setBackgroundResource(ImageUtils.getBackIamge());


		//		readMoreBtn = (Button) findViewById(R.id.readme_button);
		//		readMoreBtn.setVisibility(Button.VISIBLE);
		//		readMoreBtn.setBackgroundResource(ImageUtils.getReadmeIamge());
		//		

		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		regTitleTextView = (TextView) findViewById(R.id.regTitleTextView);
		//		firstNameTextView  = (TextView) findViewById(R.id.firstNameTextView);
		//		lastNameTextView  = (TextView) findViewById(R.id.lastNameTextView);
		//		emailTextView  = (TextView) findViewById(R.id.emailTextView);
		//		pwdTextView  = (TextView) findViewById(R.id.pwdTextView);
		//		confirmPwdTextView  = (TextView) findViewById(R.id.confirmPwdTextView);



		tandcTextView = (TextView) findViewById(R.id.tandcTextView);

		titleBarTextView.setText(TitleTextUtils.getShortRegTitleStr());
		regTitleTextView.setText(TitleTextUtils.getShortRegSubTitleStr());


		//		firstNameTextView.setText(TitleTextUtils.getShortFirstNameStr());
		//		lastNameTextView.setText(TitleTextUtils.getShortRegLastNameStr());
		//		emailTextView.setText(TitleTextUtils.getShortRegEmailAddressStr());
		//		pwdTextView.setText(TitleTextUtils.getShortRegPasswordStr());
		//		confirmPwdTextView.setText(TitleTextUtils.getShortRegConfirmPasswordStr());



//		SpannableString content = new SpannableString(TitleTextUtils.getShortRegTandCStr());
//		content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
//		tandcTextView.setText(content);

		//tandcTextView  = (TextView) findViewById(R.id.regTitleTextView);

		fNameText = (EditText) findViewById(R.id.fname_text);
//		lNameText = (EditText) findViewById(R.id.lname_text);
		emailText = (EditText) findViewById(R.id.email_text);
		passwordText = (EditText) findViewById(R.id.password_text);
		conPasswordText = (EditText) findViewById(R.id.con_password_text);

		tandcBox = (CheckBox) findViewById(R.id.checkbox);

		// Place Holder

		fNameText.setHint(TitleTextUtils.getShortFirstNameStr());
		//lNameText.setHint(TitleTextUtils.getShortRegLastNameStr());
		emailText.setHint(TitleTextUtils.getShortRegEmailAddressStr());
		passwordText.setHint(TitleTextUtils.getShortRegPasswordStr());
		conPasswordText.setHint(TitleTextUtils.getShortRegConfirmPasswordStr());

		//		tandcTextView.setOnClickListener(new View.OnClickListener() {
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				startActivity(new Intent(getApplicationContext(),TermsAndCondScreen.class));
		//			}
		//			
		//		});
		submitBtn = (Button) findViewById(R.id.next_button);
		submitBtn.setBackgroundResource(ImageUtils.getNextBtn());
		submitBtn.setOnClickListener(this);
		homeButton.setOnClickListener(this);
		//readMoreBtn.setOnClickListener(this);
		settings = getSharedPreferences(PREFS_NAME, 0);

	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.SHORT_REGISTRATIN_SCREEN;
		super.onResume();
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


	
	void showNextScreen() {
		final UserDetails userDetails = new UserDetails();
		userDetails.setFirstName(fNameText.getText().toString());
		//userDetails.setLastName(lNameText.getText().toString());
		userDetails.setAddress1("");
		userDetails.setAddress2("");
		userDetails.setCity("");
		userDetails.setState("");
		userDetails.setCountry("");
		userDetails.setDeviceIndentifier("213123");
		userDetails.setHowToKnow("");
		userDetails.setMobileNumber("");
		userDetails.setInterest("");
		userDetails.setUserName(emailText.getText().toString());
		userDetails.setStatus("2222");
		//userDetails.setUserName("");
		userDetails.setPassword(passwordText.getText().toString());
		userDetails.setZipcode("12345");
		
		Intent intent = new Intent(this,ShortRegistrationScreenTwo.class);
		intent.putExtra("userDetails", userDetails);
		startActivity(intent);
		
	}




	void userRegistration() {
		new Thread() {
			public void run() {
//				try {
//
//
//					
//
//					RegistrationResponse registrationRespone = WSSender.sendRegistreationRequest(userDetails);
//					if(registrationRespone.isSuccess()) {
//						//infoMessage = registrationRespone.getMessage();
//						infoMessage = AlertMsgUtil.getRegistrationSucessMessage();
//						messageHandler.sendMessage(Message.obtain(messageHandler,0));
//
//					}else {
//						//errorMessage = registrationRespone.getMessage();
//						errorMessage = AlertMsgUtil.getRegistrationFailureMessage();
//						messageHandler.sendMessage(Message.obtain(messageHandler,1));
//					}
//				} catch (IOException e) {
//					errorMessage = AlertMsgUtil.getConnectFailureMessage();
//					messageHandler.sendMessage(Message.obtain(messageHandler,1));
//				}

			}
		}.start();
	}

	void goHome() {
		//Log.i(Constants.TAG, "# RegistrationScreen # ----- goHome() ------ ");
		finish();
	}


	boolean validation() {

		InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		mgr.hideSoftInputFromWindow(fNameText.getWindowToken(),0);
		String firstName = fNameText.getText().toString();
		//String lastName = lNameText.getText().toString();
		String email = emailText.getText().toString();
		String password = passwordText.getText().toString();
		String conPassword = conPasswordText.getText().toString();


		if(firstName == null || firstName.trim().length() <= 0) {
			// show alert
			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
			showToast(AlertMsgUtil.getAlertMsgEmptyFirstName());
			return false;
		}

//		if(lastName == null || lastName.trim().length() <= 0) {
//			// show alert
//			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
//			showToast(AlertMsgUtil.getAlertMsgEmptyLastName());
//			return false;
//		}

		if(email == null || email.trim().length() <= 0) {
			// show alert
			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
			showToast(AlertMsgUtil.getAlertMsgEmptyEmailAddress());
			return false;
		}

		if(!Utils.getEmailValidationStatus(email)){
			showToast(AlertMsgUtil.getInvalidEmailAddress());
			return false;
		}


		if(password == null || password.trim().length() <= 0) {
			// show alert
			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
			showToast(AlertMsgUtil.getAlertMsgEmptyPassword());
			return false;
		}

		if(password.trim().length() <= 5){
			showToast(AlertMsgUtil.getAlertMsgPasswordLength());
			//showToast("Password must be atleast 6 chars!");
			return false;
		}


		if(!password.equals(conPassword)) {
			showToast(AlertMsgUtil.getAlertMsgPwdandConPwdDontMatch());
			return false;
		}

//		if(!tandcBox.isChecked()) {
//			showToast(AlertMsgUtil.getAlertMsgTnCAccept());
//			return false;
//		}
		return true;
	}

	private Handler messageHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			//Log.i(Constants.TAG, "Inside handle message ------ > " + msg.what);
			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0: 
				Toast.makeText(getApplicationContext(), infoMessage,Toast.LENGTH_SHORT).show();
				handleLogin();

				break;
			case 1:
				Toast.makeText(getApplicationContext(), errorMessage,
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};



	protected void handleLogin() {
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {

					if(!doLogin(emailText.getText().toString().trim(),passwordText.getText().toString().trim())) {
						loginMessageHandler.sendMessage(Message.obtain(loginMessageHandler,1));
					}else {
						loginMessageHandler.sendMessage(Message.obtain(loginMessageHandler,0));
					}
					Thread.sleep(2);
				} catch (InterruptedException e) {
				}
			}
		}.start();
	}


	private Handler loginMessageHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			//Log.i(Constants.TAG, "Inside handle message ------ > " + msg.what);
			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			//handle update
			case 0: 
				showCouponListScreen();
				finish();
				break;
			case 1:
				Toast.makeText(getApplicationContext(),
						"Username(email) and/or Password invalid!",
						Toast.LENGTH_LONG).show();
				break;
			}
		}
	};


	void showCouponListScreen() {
		Intent intent = new Intent(this,CouponListScreen.class);
		startActivity(intent);
		finish();
	}




	boolean doLogin(String userName,String password) {
		try {

			Login login = new Login();
			login.setUsername(userName);
			login.setPassword(password);
			//login.setDeviceIndentifier("A286E8D1-9681-505A-90FF-DAE45A842DC5");
			login.setDeviceIndentifier("213123");
			SharedPreferences.Editor editor = settings.edit();
			UserDetails userDetails = WSSender.sendValidateCredentialRequest(login);
			if(userDetails == null) {
				editor.putBoolean("loginStatus", false);
				// Don't forget to commit your edits!!!
				editor.commit();
				return false;
			}else {
				DataUtil.userName = userName;
				DataUtil.password = password;
				DataUtil.userId = userDetails.getUserId();
				editor.putString("userName", userName);
				editor.putString("password", password);
				editor.putBoolean("loginStatus", true);
				// Don't forget to commit your edits!!!
				editor.commit();
				return true;
			}
		}catch(IOException ioe) {

		}
		return false;
	}





	void showToast(String message) {
		Toast.makeText(getApplicationContext(), message,Toast.LENGTH_SHORT).show();
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == submitBtn) {
			if(validation()) {
				//showDialog(PRO_DIALOG);
				//userRegistration();
				showNextScreen();
			}
		}else if (v == homeButton) {
			goHome();
			//	}else if(v == readMoreBtn){
			//		
			//		//Log.i(Constants.TAG, " Readmore button clicked ");
			//		
			//		startActivity(new Intent(getApplicationContext(),TermsAndCondScreen.class));
		}else{}
	}
}
