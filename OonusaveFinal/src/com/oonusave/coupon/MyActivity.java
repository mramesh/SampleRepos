package com.oonusave.coupon;

import java.io.IOException;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.GeneralResponse;
import com.oonusave.coupon.model.Login;
import com.oonusave.coupon.model.UserDetails;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.util.Utils;
import com.oonusave.coupon.ws.WSSender;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Ramesh
 *
 */
public class MyActivity extends Activity {

	Button loginBtn,signUpBtn, demoUserBtn;
	EditText userNameText,passwordText,emailText01;
	Context context;
	private static final int PRO_DIALOG = 0;
	private static final int DEFAULT_DIALOG = 1;
	
	SharedPreferences settings = null;
	public static final String PREFS_NAME = "MyPrefsFile";
	TextView forgotPassTextView,newUserRegisterTextView,loginTitleTextView,demoUserTxt, rememberPassTextView;
	
	String infoMessage,errorMessage;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.main);
		context = getApplicationContext();
		
		settings = getSharedPreferences(PREFS_NAME, 0);

		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		//((TextView)findViewById(R.id.titleText)).setText("Login");
		//((ImageButton)findViewById(R.id.btnLeft)).setBackgroundResource(R.drawable.home);
		//((ImageButton)findViewById(R.id.btnLeft)).setBackgroundColor(Color.TRANSPARENT);
		//((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);

		
		loginTitleTextView = (TextView) findViewById(R.id.loginTitleTextView);
		userNameText = (EditText) findViewById(R.id.txt_username);
		passwordText = (EditText) findViewById(R.id.txt_password);
		
		//userNameText.setHint("Username");
		//passwordText.setHint("Password");
		
		userNameText.setHint(TitleTextUtils.getUserNameText());
		passwordText.setHint(TitleTextUtils.getPasswordText());
		
		userNameText.setText(settings.getString("userName",""));
		passwordText.setText(settings.getString("password", ""));
		
		//Log.i(Constants.TAG, " MyActivity ::: UserName ------ > " + settings.getString("userName",""));
		//Log.i(Constants.TAG, " MyActivity ::: Password ------ > " + settings.getString("password", ""));
		
		
				
		loginBtn = (Button) findViewById(R.id.login_button);
		signUpBtn = (Button) findViewById(R.id.signup_button);
		
		loginBtn.setBackgroundResource(ImageUtils.getLoginImage());
		signUpBtn.setBackgroundResource(ImageUtils.getSignUpImage());
		
		
		
		
		
		//newUserRegisterTextView = (TextView) findViewById(R.id.newUserRegisterTextView);
		forgotPassTextView = (TextView) findViewById(R.id.forgotPassTextView);
		rememberPassTextView = (TextView) findViewById(R.id.rememberPassTextView);
		
		//demoUserTxt = (TextView) findViewById(R.id.demoUserTxt);
		
		demoUserBtn = (Button) findViewById(R.id.demouser_button);
		
		demoUserBtn.setBackgroundResource(ImageUtils.getDemoUserImage());
		
		
		
		//loginTitleTextView.setText(TitleTextUtils.getLoginTitleText());
		forgotPassTextView.setText(TitleTextUtils.getLoginForgotPwdMsgStr());
		rememberPassTextView.setText(TitleTextUtils.getLoginRememberMyPwdMsgStr());
		
		//newUserRegisterTextView.setText(TitleTextUtils.getLoginNewUserRegisterHereMsgStr());
		
		
		forgotPassTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				showDialog(DEFAULT_DIALOG);
			}
		});
		
		demoUserBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				handleLogin("demouser@mocansa.com", "pass123");
			}
				
		});
		
		
//		demoUserTxt.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				handleLogin("demouser@mocansa.com", "pass123");
//			}
//		});

		// 	set listener
		loginBtn.setOnClickListener(new OnClickListener());
		signUpBtn.setOnClickListener(new OnClickListener());
		
		//settings = getSharedPreferences(PREFS_NAME, 0);       
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.LOGIN_SCREEN;
		super.onResume();
	}
	
	
	 @Override
	  public boolean onKeyDown(int keyCode, KeyEvent event) {
	      if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	  startActivity(createHomeIntent());
	    	  System.exit(0);
	      }
	      return super.onKeyDown(keyCode, event);
	  }
	 	

	 public static Intent createHomeIntent() {
	        Intent intent = new Intent(Intent.ACTION_MAIN, null);
	        intent.addCategory(Intent.CATEGORY_HOME);
	        return intent;
		}





	class OnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v instanceof Button) {
				if(v == loginBtn) {
					
					String userName = userNameText.getText().toString();
					String password = passwordText.getText().toString();
					if(userName == null || userName.length() <= 0) {
						// show alert
						showDialog1(AlertMsgUtil.getEmptyLoginMessage());
						return;
					}

					if(password == null || password.length() <= 0) {
						// show alert
						showDialog1(AlertMsgUtil.getEmptyLoginMessage());
						return;
					}

					showDialog(PRO_DIALOG);
					handleLogin(userNameText.getText().toString(),passwordText.getText().toString());
					
					
					//showCouponListScreen();
					


				}else if(v == signUpBtn) {
					showRegistrationScreen();

				}else {}
			}
		}
	}


	ProgressDialog progressDialog = null;






	protected void handleLogin(final String userName,final String password) {

		new Thread() {
			public void run() {
				try {
					int resCode = doLogin(userName, password);
					if(resCode == 0) {
						messageHandler.sendMessage(Message.obtain(messageHandler,0));
					}else if(resCode == 2){
						messageHandler.sendMessage(Message.obtain(messageHandler,2));
					}else{
						messageHandler.sendMessage(Message.obtain(messageHandler,1));
					}
					Thread.sleep(2);
				} catch (InterruptedException e) {

				}
			}
		}.start();
	}


	private Handler messageHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			//Log.i(Constants.TAG, "Inside handle message ------ > " + msg.what);
			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			//handle update

			case 0: 
//				SharedPreferences.Editor editor = settings.edit();
//				editor.putBoolean("loginStatus", false);
//				// Don't forget to commit your edits!!!
//				editor.commit();
//				
				showCouponListScreen();
				break;
			case 1:
				Toast.makeText(getApplicationContext(),
						AlertMsgUtil.getInvalidLoginMessage(),
						Toast.LENGTH_LONG).show();
				break;
			case 2: 
				Toast.makeText(getApplicationContext(),
						AlertMsgUtil.getConnectFailureMessage(),
						Toast.LENGTH_LONG).show();
				break;
			}
		}
	};


	private Handler forgotPasswordHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0:
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getForgotPasswordSuccessMessage(), Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};


	AlertDialog alertDialog; 

	void showDialog1(String alertMsg) {
		alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Error");
		alertDialog.setMessage(alertMsg);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				return;
			} 
		}); 
		alertDialog.show();
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
			
		case DEFAULT_DIALOG :{
			
			AlertDialog.Builder alertBuilder = new
			AlertDialog.Builder(this);
			alertBuilder.setTitle(TitleTextUtils.getLoginForgotPwdMsgStr())
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
					if(!Utils.getEmailValidationStatus(emailText01.getText().toString())) {
						Toast.makeText(getApplicationContext(), AlertMsgUtil.getInvalidEmailAddressMessage(), Toast.LENGTH_SHORT).show();
						return;
					}

					if(emailText01.getText() != null && emailText01.getText().toString().length() > 0) {
						dialog.dismiss();
						sendForgotPassword();
					}
					
				}
			});
			LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.forgot_password_dialog,null);
			TextView Text01 = (TextView)layout.findViewById(R.id.Text01);
			emailText01 = (EditText)layout.findViewById(R.id.EditText01);
			emailText01.setText(userNameText.getText().toString());
			Text01.setText(TitleTextUtils.getForgotPasswordAlertMsgStr());
			alertBuilder.setView(layout);
			dialog = alertBuilder.create();
			break;
		}
		}
		return dialog;	
	}


	void sendForgotPassword() {
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					GeneralResponse generalResponse = WSSender.sendForgotPasswordRequest(emailText01.getText().toString());
					forgotPasswordHandler.sendMessage(Message.obtain(forgotPasswordHandler, 0, generalResponse.getMessage()));
				}catch(Exception e) {
					forgotPasswordHandler.sendMessage(Message.obtain(forgotPasswordHandler, 1, e.getMessage()));
				}
			}
		}.start();
	}
	


	int doLogin(String userName,String password) {
//		//Log.i(Constants.TAG,"# LoginScreen # ------ doLogin ----- ");
//		//Log.i(Constants.TAG,"# LoginScreen # ------ Username  -----> " + userName);
//		//Log.i(Constants.TAG,"# LoginScreen # ------ Password  -----> " + password);
		SharedPreferences.Editor editor = settings.edit();
		try {
			Login login = new Login();
			login.setUsername(userName);
			login.setPassword(password);
			//login.setDeviceIndentifier("A286E8D1-9681-505A-90FF-DAE45A842DC5");
			login.setDeviceIndentifier("213123");
			InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			mgr.hideSoftInputFromWindow(loginBtn.getWindowToken(),0);
			
			UserDetails userDetails = WSSender.sendValidateCredentialRequest(login);
			if(userDetails == null) {
				editor.putBoolean("loginStatus", false);
				// Don't forget to commit your edits!!!
				editor.commit();
				return 1;
			}else {



//				//Log.i(Constants.TAG,"# LoginScreen # ------ LoginSuccessful  -----> " );
//				//Log.i(Constants.TAG,"# LoginScreen # ------ UserId  -----> " + userDetails.getUserName());
//				//Log.i(Constants.TAG,"# LoginScreen # ------ LoginSuccessful  -----> " );
//				//Log.i(Constants.TAG,"# LoginScreen # ------ LoginSuccessful  -----> " );
//				//Log.i(Constants.TAG,"# LoginScreen # ------ LoginSuccessful  -----> " );

				DataUtil.userName = userName;
				DataUtil.password = password;
				DataUtil.userId = userDetails.getUserId();

				if(!userName.equals("demouser@mocansa.com")) {
				
				editor.putString("userName", userName);
				editor.putString("password", password);
				editor.putBoolean("loginStatus", true);
				}
				// Don't forget to commit your edits!!!
				
				return 0;
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
			//Toast.makeText(getApplicationContext(), ioe.getMessage(), Toast.LENGTH_SHORT);
			return 2;
		}catch(Exception e) {
			//Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
			return 2;
		}finally {
			if(editor != null)
				editor.commit();
		}
		
	}


	void showRegistrationScreen() {
		//Intent intent = new Intent(this,ShortRegistrationScreen.class);
		Intent intent = new Intent(this,SignUpScreen.class);
		startActivity(intent);
	}


	void showCouponListScreen() {
		//Intent intent = new Intent(this,CouponListScreen.class);
		//Intent intent = new Intent(this,UsedCouponScreen.class);
		//Intent intent = new Intent(this,CouponsScreen.class);
		Intent intent = new Intent(this,MyMapStore.class);
		//Intent intent = new Intent(this,ExpiryCouponsScreen.class);
		startActivity(intent);
		finish();
	}
}

/*
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);		

		((TextView)findViewById(R.id.text)).setText("Ramesh");

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,	KeyEvent.KEYCODE_BACK));
				dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
			}
		});

		// ...
	}
}*/