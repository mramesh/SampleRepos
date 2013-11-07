package com.oonusave.coupon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Category;
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

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SignUpScreen extends BaseActivity implements android.view.View.OnClickListener{

	ImageButton backButton;
	ImageView accountDetailsBtn, perDetailsBtn, notiDetailsBtn;
	
	RelativeLayout accountDetailsLayout, personalDetailsLayout, notificationLayout;
	
	UserDetails userDetails = new UserDetails();
	
	ListView categoryListView = null;
	
	
	
	private static final int PRO_DIALOG = 0;
	String errorMessage = "";
	String infoMessage = "";
	
	Button continueBtn, backBtn1,backBtn2,continueBtn1, finishButton;
	CheckBox tandcBox;
	SharedPreferences settings = null;
	public static final String PREFS_NAME = "MyPrefsFile";
	
	RadioGroup radioGroup = null;
	SeekBar ageSeekBar = null;
	
	
	EditText emailText, passwordText, confirmPassText, nickNameTet, postCodeText;
	
	
	TextView accEmailTextView, accPassTextView, accConPassTextView;
	TextView perAgeTextView, perNickNameTextView, perGenderTextView, perPostCodeTextView;
	TextView tandcTextView,seekbarTextView;
	
	
	
	List<Integer> tempList = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.signup_screen);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);

		//((TextView)findViewById(R.id.titleText)).setText("SignUp");

		((ImageButton)findViewById(R.id.btnRight)).setVisibility(View.INVISIBLE);
		
		backButton = (ImageButton)findViewById(R.id.btnLeft);
		backButton.setVisibility(ImageButton.VISIBLE);
		backButton.setClickable(true);
		//homeButton.setBackgroundResource(R.drawable.back_button);
		backButton.setBackgroundResource(ImageUtils.getBackIamge());
		backButton.setOnClickListener(this);

		
		
		accountDetailsBtn = (ImageView)findViewById(R.id.accDetailImage);
		perDetailsBtn = (ImageView)findViewById(R.id.personalDetailImage);
		notiDetailsBtn = (ImageView)findViewById(R.id.notificationDetailImage);
		
		accountDetailsBtn.setOnClickListener(this);
		perDetailsBtn.setOnClickListener(this);
		notiDetailsBtn.setOnClickListener(this);
		
		continueBtn = (Button)findViewById(R.id.continue_button);
		backBtn1 = (Button)findViewById(R.id.back_button1);
		backBtn2 = (Button)findViewById(R.id.back_button2);
		continueBtn1 = (Button)findViewById(R.id.continue_button1);
		finishButton = (Button)findViewById(R.id.finish_button);
		
		
		
		
		
		ageSeekBar = (SeekBar) findViewById(R.id.seekbar);
		
		seekbarTextView = (TextView) findViewById(R.id.seekbarTextView);
		
		
		continueBtn.setOnClickListener(this);
		backBtn1.setOnClickListener(this);
		backBtn2.setOnClickListener(this);
		continueBtn1.setOnClickListener(this);
		finishButton.setOnClickListener(this);
		
		
		accountDetailsLayout = (RelativeLayout) findViewById(R.id.accLayout);
		personalDetailsLayout = (RelativeLayout) findViewById(R.id.perLayout);
		notificationLayout = (RelativeLayout) findViewById(R.id.notiLayout);
		
		
		
		accEmailTextView = (TextView) findViewById(R.id.emailTextView);
		accPassTextView = (TextView) findViewById(R.id.pwdTextView);
		accConPassTextView = (TextView) findViewById(R.id.confirmPwdTextView);
		perAgeTextView = (TextView) findViewById(R.id.ageTextView);
		perNickNameTextView = (TextView) findViewById(R.id.nickNameTextView);
		perGenderTextView = (TextView) findViewById(R.id.genderTextView);
		perPostCodeTextView = (TextView) findViewById(R.id.postCodeTextView);
		
		accEmailTextView.setText("EmailID");
		accPassTextView.setText("Password");
		accConPassTextView.setText("Confirm Password");
		perAgeTextView.setText("Age");
		perNickNameTextView.setText("Nick Name");
		perGenderTextView.setText("Gender");
		perPostCodeTextView.setText("Post Code");
		
		
		
		
		emailText = (EditText) findViewById(R.id.email_text);
		passwordText = (EditText) findViewById(R.id.password_text);
		confirmPassText = (EditText) findViewById(R.id.con_password_text);
		nickNameTet = (EditText) findViewById(R.id.nick_name_text);
		postCodeText = (EditText) findViewById(R.id.post_code_text); 
		
		
		tandcBox = (CheckBox) findViewById(R.id.checkbox);
		
		
		
		categoryListView = (ListView) findViewById(R.id.listView);
		//categoryListView.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.child_row,DataUtil.categoryList.toArray()));
		
		categoryListView.setAdapter(new CategoryAdaptor(getApplicationContext(),R.layout.child_row, DataUtil.categoryList));
				
		categoryListView.setCacheColorHint(Color.TRANSPARENT);
		
		
		categoryListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int pos,
					long id) {
				if(v != null) {
					CheckedTextView c = (CheckedTextView)v;
					if(c.isChecked()) {
						tempList.remove(new Integer(pos));
					}else {
						
						tempList.remove(new Integer(pos));
						tempList.add(new Integer(pos));
					}
					c.toggle();
				}
			}
		});
		
		
		radioGroup = (RadioGroup) findViewById(R.id.widget1);
		
		
		
		tandcTextView = (TextView) findViewById(R.id.tandcTextView);
		SpannableString content = new SpannableString(TitleTextUtils.getShortRegTandCStr());
		content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
		tandcTextView.setText(content);
				
		
		tandcTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),TermsAndCondScreen.class));
			}
			
		});
		
		
		ageSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				seekbarTextView.setText(progress+"");
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		settings = getSharedPreferences(PREFS_NAME, 0);
		showAcc();
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
	
	@Override
	public void onClick(View v) {
		if(v == backButton) {
			finish();
		}else if(v == accountDetailsBtn){
			showAcc();
			
		}else if(v == perDetailsBtn){

			showPer();
		}else if(v == notiDetailsBtn){
			showNoti();
		}
		
		if(v == continueBtn ) {
						
			if(validation1()) {
				showPer();
			}
		} else if(v == continueBtn1) {
			if(validation()){
				showNoti();
			}
			
		}else if(v == backBtn1) {
			showAcc();
			
		}else if(v == backBtn2) {
			showPer();
			
		}else if(v == finishButton) {
			if(validation() && validation3()) {
				// Insert user
				userRegistration();
			}
		}
	}
	
	
	
	void userRegistration() {
		showDialog(PRO_DIALOG);
		
		new Thread() {
			public void run() {
				try {


					
					userDetails.setUserName(emailText.getText().toString());
					userDetails.setStatus("2222");
					userDetails.setPassword(passwordText.getText().toString());
					
					
					//final UserDetails userDetails = new UserDetails();
					//userDetails.setFirstName(fNameText.getText().toString());
					//userDetails.setLastName(lNameText.getText().toString());
					userDetails.setAddress1("");
					userDetails.setAddress2("");
					userDetails.setCity("");
					userDetails.setState("");
					userDetails.setCountry("");
					//userDetails.setDeviceIndentifier("213123");
					userDetails.setHowToKnow("");
					userDetails.setMobileNumber("");
					userDetails.setInterest("");
					//userDetails.setUserName(emailText.getText().toString());
					userDetails.setStatus("2222");
					//userDetails.setUserName("");
					//userDetails.setPassword(passwordText.getText().toString());
					userDetails.setZipcode(postCodeText.getText().toString());
					userDetails.setReferredBy("someone");
					userDetails.setAge(ageSeekBar.getProgress());
					StringBuffer sb = new StringBuffer();
					
					for(Integer integer : tempList) {
						Category category = DataUtil.categoryList.get(integer); 
						sb.append(category.getCategoryName() + "*" + category.getCategoryId()+"/");
					}
					
					if(sb.length() > 3) {
						userDetails.setCategories(sb.substring(0, sb.length() - 1));
					}
					
					
					//userDetails.setCategories(""); // Todo
					userDetails.setGender(radioGroup.getCheckedRadioButtonId() == 1 ? "Male" : "Female");

					RegistrationResponse registrationRespone = WSSender.sendRegistreationRequest(userDetails);
					if(registrationRespone.isSuccess()) {
						//infoMessage = registrationRespone.getMessage();
						infoMessage = AlertMsgUtil.getRegistrationSucessMessage();
						messageHandler.sendMessage(Message.obtain(messageHandler,0));

					}else {
						//errorMessage = registrationRespone.getMessage();
						errorMessage = AlertMsgUtil.getRegistrationFailureMessage();
						messageHandler.sendMessage(Message.obtain(messageHandler,1));
					}
				} catch (IOException e) {
					errorMessage = AlertMsgUtil.getConnectFailureMessage();
					messageHandler.sendMessage(Message.obtain(messageHandler,1));
				}

			}
		}.start();
	}
	
	
	
	
	private boolean validation1() {
		
		String email = emailText.getText().toString();
		String password = passwordText.getText().toString();
		String conPassword = confirmPassText.getText().toString();
		
		if(email == null || email.trim().length() <= 0) {
			// show alert
			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
			showToast(AlertMsgUtil.getAlertMsgEmptyEmailAddress());
			showAcc();
			return false;
		}

		if(!Utils.getEmailValidationStatus(email)){
			showToast(AlertMsgUtil.getInvalidEmailAddress());
			showAcc();
			return false;
		}
		
		if(password == null || password.trim().length() <= 0) {
			// show alert
			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
			showToast(AlertMsgUtil.getAlertMsgEmptyPassword());
			showAcc();
			return false;
		}

		if(password.trim().length() <= 5){
			showToast(AlertMsgUtil.getAlertMsgPasswordLength());
			//showToast("Password must be atleast 6 chars!");
			showAcc();
			return false;
		}


		if(!password.equals(conPassword)) {
			showToast(AlertMsgUtil.getAlertMsgPwdandConPwdDontMatch());
			return false;
		}
		
		return true;
	}
	
	
	
	private boolean validation () {
		
		String email = emailText.getText().toString();
		String password = passwordText.getText().toString();
		String conPassword = confirmPassText.getText().toString();
		String postCode = postCodeText.getText().toString();
		String nickName = nickNameTet.getText().toString();
		
		
		if(email == null || email.trim().length() <= 0) {
			// show alert
			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
			showToast(AlertMsgUtil.getAlertMsgEmptyEmailAddress());
			showAcc();
			return false;
		}

		if(!Utils.getEmailValidationStatus(email)){
			showToast(AlertMsgUtil.getInvalidEmailAddress());
			showAcc();
			return false;
		}
		
		if(password == null || password.trim().length() <= 0) {
			// show alert
			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
			showToast(AlertMsgUtil.getAlertMsgEmptyPassword());
			showAcc();
			return false;
		}

		if(password.trim().length() <= 5){
			showToast(AlertMsgUtil.getAlertMsgPasswordLength());
			//showToast("Password must be atleast 6 chars!");
			showAcc();
			return false;
		}


		if(!password.equals(conPassword)) {
			showToast(AlertMsgUtil.getAlertMsgPwdandConPwdDontMatch());
			return false;
		}
		
		
		if(postCode == null || postCode.trim().length() <= 0) {
			//showToast(AlertMsgUtil.getAlertMsgEmptyFirstName());
			showToast("Please enter post code");
			showPer();
			return false;
		}

		if(nickName == null || nickName.trim().length() <= 0) {
			// show alert
			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
			//showToast(AlertMsgUtil.getAlertMsgEmptyLastName());
			showToast("Please enter nick name");
			showPer();
			return false;
		}
		
		
		
		return true;
	}
	
	
	
	
	private boolean validation3() {
		if(tempList.size() == 0) {
			showToast(AlertMsgUtil.getAlertCategorySelect());
			showNoti();
			return false;
		}
		
		if(!tandcBox.isChecked()) {
			showToast(AlertMsgUtil.getAlertMsgTnCAccept());
			showNoti();
			return false;
		}
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

					if(!doLogin(userDetails.getUserName(),userDetails.getPassword())) {
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
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		
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

	
	
	private void showAcc() {
		//accountDetailsBtn.setBackgroundResource(R.drawable.account_details_selected);
		//perDetailsBtn.setBackgroundResource(R.drawable.personal_details);
		//notiDetailsBtn.setBackgroundResource(R.drawable.notification_details);
		
		accountDetailsBtn.setBackgroundResource(ImageUtils.getAccountDetailsTabSelectedImage());
		perDetailsBtn.setBackgroundResource(ImageUtils.getPersonalDetailsTabNormalImage());
		notiDetailsBtn.setBackgroundResource(ImageUtils.getNotificationDetailsTabNormalImage());
		
		accountDetailsLayout.setVisibility(View.VISIBLE);
		personalDetailsLayout.setVisibility(View.GONE);
		notificationLayout.setVisibility(View.GONE);
	}
	
	
	private void showPer() {
		//accountDetailsBtn.setBackgroundResource(R.drawable.account_details);
		//perDetailsBtn.setBackgroundResource(R.drawable.personal_details_selected);
		//notiDetailsBtn.setBackgroundResource(R.drawable.notification_details);
		
		
		accountDetailsBtn.setBackgroundResource(ImageUtils.getAccountDetailsTabNormalImage());
		perDetailsBtn.setBackgroundResource(ImageUtils.getPersonalDetailsTabSelectedImage());
		notiDetailsBtn.setBackgroundResource(ImageUtils.getNotificationDetailsTabNormalImage());
		
		accountDetailsLayout.setVisibility(View.GONE);
		personalDetailsLayout.setVisibility(View.VISIBLE);
		notificationLayout.setVisibility(View.GONE);
	}
	
	private void showNoti() {
//		accountDetailsBtn.setBackgroundResource(R.drawable.account_details);
//		perDetailsBtn.setBackgroundResource(R.drawable.personal_details);
//		notiDetailsBtn.setBackgroundResource(R.drawable.notification_details_selected);
		
		accountDetailsBtn.setBackgroundResource(ImageUtils.getAccountDetailsTabNormalImage());
		perDetailsBtn.setBackgroundResource(ImageUtils.getPersonalDetailsTabNormalImage());
		notiDetailsBtn.setBackgroundResource(ImageUtils.getNotificationDetailsTabSelectedImage());
		
		accountDetailsLayout.setVisibility(View.GONE);
		personalDetailsLayout.setVisibility(View.GONE);
		notificationLayout.setVisibility(View.VISIBLE);
	}
	
	
	
	private class CategoryAdaptor extends ArrayAdapter<Category>  {

		private List<Category> items;

        public CategoryAdaptor(Context context, int textViewResourceId, List<Category> items) {
                super(context, textViewResourceId, items);
                this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.child_row, null);
                }
                
                
                Category o = items.get(position);
                if (o != null) {
                        CheckedTextView tt = (CheckedTextView) v.findViewById(R.id.autoupdatecheckboxview);
                        if (tt != null) {
                              tt.setText(o.getCategoryName());
                              tt.setChecked(tempList.contains(new Integer(position)));
                        }
                        
                }
                return v;
        }
		
		
		
		
	}
	
	
	
	
	

}
