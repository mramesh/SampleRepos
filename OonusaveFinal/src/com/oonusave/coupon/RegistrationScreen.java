package com.oonusave.coupon;

import java.io.IOException;
import java.util.Calendar;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.GeneralResponse;
import com.oonusave.coupon.model.UserDetails;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.util.Utils;
import com.oonusave.coupon.ws.WSSender;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @auther Ramesh
 */

public class RegistrationScreen extends BaseActivity implements OnClickListener{

	Button regBtn,pickGenderBtn,pickCountryBtn;
	ImageButton backBtn;
	Button submitBtn;
	//Spinner genderSpinner,countrySpinner;
	Spinner genderSpinner;
	EditText fNameText,lNameText,mNumberText,addr1Text,addr2Text,addr3Text,cityText,stateText,zipText,emailText,userNameText,passwordText,confirmPasswordText, contactNoText, ageText;
	EditText textBirthDate,textBirthTime;

	Button btnSelectDate;

	private int mDay;
	private int mMonth;
	private int mYear;

	//EditText userId,password,confirmPassword;
	String[] countryList = null;
	String[] genderList = null;
	private static final int PRO_DIALOG = 0;
	static final int DATE_DIALOG_ID = 1;
	static final int TIME_DIALOG_ID = 2;
	static final int TEST_DIALOG = 3;

	String infoMessage = "",errorMessage = "";

	//CheckBox alertEmail;

	TextView titleBarTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		//Log.i(Constants.TAG, "# RegistrationScreen # ----- onCreate() ------ ");
		//Log.i(Constants.TAG, "RegistrationScreen :: onCreate()");
		setContentView(R.layout.registration_view);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);		
		//setFeatureDrawableResource(Window.FEATURE_RIGHT_ICON, R.drawable.icon);
		backBtn = (ImageButton)findViewById(R.id.btnLeft);
		backBtn.setVisibility(ImageView.VISIBLE);
		//backBtn.setBackgroundResource(R.drawable.back_button);
		backBtn.setBackgroundResource(ImageUtils.getBackIamge());
		backBtn.setOnClickListener(this);		

		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);
		submitBtn = (Button) findViewById(R.id.submit_button);
		submitBtn.setBackgroundResource(ImageUtils.getSubmitImage());
		submitBtn.setOnClickListener(this);


		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getEditProfileTitleText());

//		countryList = DataUtil.COUNTRIES;
		genderList = DataUtil.GENDER;


//		ArrayAdapter<String> countryAdaptor = 
//			new ArrayAdapter<String>( 
//					this,
//					android.R.layout.simple_spinner_item,
//					countryList);

//		countryAdaptor.setDropDownViewResource(
//				android.R.layout.simple_spinner_dropdown_item);


		ArrayAdapter<String> genderAdaptor = 
			new ArrayAdapter<String>( 
					this,
					android.R.layout.simple_spinner_item,
					genderList);

		genderAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		fNameText = (EditText) findViewById(R.id.fname_text);
//		lNameText = (EditText) findViewById(R.id.lname_text);


		//stateText = (EditText) findViewById(R.id.state_text);

		//alertEmail = (CheckBox) findViewById(R.id.checkbox);
		//emailText = (EditText) findViewById(R.id.email_text);


		passwordText =(EditText) findViewById(R.id.password_text);
		//confirmPasswordText = (EditText) findViewById(R.id.con_password_text);
		ageText = (EditText) findViewById(R.id.age_text);
		

//		contactNoText = (EditText) findViewById(R.id.mobileNoText);

//		addr1Text = (EditText) findViewById(R.id.address1Text);
//		addr2Text = (EditText) findViewById(R.id.address2Text);
		//addr3Text = (EditText) findViewById(R.id.address3_text);
//		cityText = (EditText) findViewById(R.id.cityText);
//		contactNoText = (EditText) findViewById(R.id.mobileNoText);
		zipText = (EditText) findViewById(R.id.postelCodeText);
		//stateText = (EditText) findViewById(R.id.state_text);
//		countrySpinner = (Spinner) findViewById(R.id.countrySpinner);
		genderSpinner = (Spinner) findViewById(R.id.gender_spinner);




//		textBirthDate = (EditText) findViewById(R.id.birthDateText);

		//btnSelectDate = (Button) findViewById(R.id.btnSelectDate);

		//textBirthDate.setEnabled(false);
		//textBirthDate.setFocusable(false);

		//

		

//		btnSelectDate.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				showDialog(DATE_DIALOG_ID);
//			}
//		});
//
//
//		if(DataUtil.userDetails.getDateOfBirth() != null && DataUtil.userDetails.getDateOfBirth().length() > 0 && !DataUtil.userDetails.getDateOfBirth().equalsIgnoreCase("0001-01-01T00:00:00")) {
//			//Log.i(Constants.TAG, " DataUtil.userDetails.getDateOfBirth() ---- > " + DataUtil.userDetails.getDateOfBirth());
//			String str[] = null;
//			if(DataUtil.userDetails.getDateOfBirth().indexOf("T") != -1) {
//				str = Utils.getBirthDateDetails1(DataUtil.userDetails.getDateOfBirth());
//			}else {
//				str = Utils.getBirthDateDetails(DataUtil.userDetails.getDateOfBirth());
//			}
//			
//			
//			mYear = Integer.parseInt(str[0]);
//			mMonth = Integer.parseInt(str[1]);
//			mDay = Integer.parseInt(str[2]);
//			
//			
//			
//			updateDateDisplay();
//		}else {
//			// get the current time
//			final Calendar c = Calendar.getInstance();	
//			mDay = c.get(Calendar.DATE);
//			mMonth = c.get(Calendar.MONTH) + 1;
//			mYear = c.get(Calendar.YEAR);
//		}
//		
//		countrySpinner.setAdapter(countryAdaptor);
		genderSpinner.setAdapter(genderAdaptor);
//
//		//pickGenderBtn = (Button) findViewById(R.id.reg_btn);
//		//pickCountryBtn = (Button) findViewById(R.id.reg_btn);
		populateValues();
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.EDIT_PROFILE_SCREEN;
//		fNameText.setHint(HintUtils.FIRST_NAME);
//		lNameText.setHint(HintUtils.LAST_NAME);
//		emailText.setHint(HintUtils.ALT_EMAIL_ID);
//		addr1Text.setHint(HintUtils.ADDRESS1);
//		addr2Text.setHint(HintUtils.ADDRESS2);
//		textBirthDate.setHint(HintUtils.DOB);
//		cityText.setHint(HintUtils.CITY);
//		contactNoText.setHint(HintUtils.MOBILE_NUMBER);
//		zipText.setHint(HintUtils.POSTEL_CODE);
//		
		fNameText.setHint("Firstname");
		//lNameText.setHint(DataUtil.priLang.equalsIgnoreCase("English")    	? "Lastname" 						: "Efternavn");
		//emailText.setHint(DataUtil.priLang.equalsIgnoreCase("English")		? "Your alternate email address" 	: "Din alternative e-mail-adresse");
		//addr1Text.setHint(DataUtil.priLang.equalsIgnoreCase("English") 		? "Address 1" 						: "Adresse 1");
		//addr2Text.setHint(DataUtil.priLang.equalsIgnoreCase("English") 		? "Address 2" 						: "Adresse 2");
		//cityText.setHint(DataUtil.priLang.equalsIgnoreCase("English") 		? "City" 							: "By");
		ageText.setHint("Age" );
		zipText.setHint("Post code");
		passwordText.setHint("Password");
		
		//stateText.setHint(DataUtil.priLang.equalsIgnoreCase("English") 		? "State" 						: "State");
		//confirmPasswordText.setHint(DataUtil.priLang.equalsIgnoreCase("English") 		? "Confirm Password" 						: "Confirm Password");
		//contactNoText.setHint(DataUtil.priLang.equalsIgnoreCase("English") 	? "Mobile number" 					: "Mobilnummer");
		//textBirthDate.setHint(DataUtil.priLang.equalsIgnoreCase("English") 	? "Date of birth" 					: "Fødselsdato");
		
		
		//
		
		super.onResume();
		
		
		//Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_LONG).show();
		//CustomAlertDialog.showCustomDialog(getApplicationContext());
		
	}
	
	
	
	public void showCustomDialog() {

		AlertDialog.Builder alertBuilder = new
		AlertDialog.Builder(getApplicationContext());
		alertBuilder.setTitle("You are in the demo account. kindly register your self to use this feature.")
		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		})
		.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alertBuilder.show();

	}

	void populateValues() {
		fNameText.setText(DataUtil.userDetails.getFirstName());
		//lNameText.setText(DataUtil.userDetails.getLastName());
		//emailText.setText(DataUtil.userDetails.getEmail());
		//alertEmail.setChecked(DataUtil.userDetails.getAlertEmail().equalsIgnoreCase("yes"));
		//addr1Text.setText(DataUtil.userDetails.getAddress1());
		//addr2Text.setText(DataUtil.userDetails.getAddress2());
		//contactNoText.setText(DataUtil.userDetails.getMobileNumber());
		//cityText.setText(DataUtil.userDetails.getCity());
		//contactNoText.setText(DataUtil.userDetails.getMobileNumber());
		zipText.setText(DataUtil.userDetails.getZipcode()+"");
		ageText.setText(DataUtil.userDetails.getAge()+"");
		passwordText.setText(DataUtil.userDetails.getPassword());
		//stateText.setText(DataUtil.userDetails.getState());
		//confirmPasswordText.setText(DataUtil.userDetails.getPassword());
		//countrySpinner.setSelection(DataUtil.getCountryPostion(DataUtil.userDetails.getCountry()), true);
		genderSpinner.setSelection(DataUtil.getGenderPosition(DataUtil.userDetails.getGender()), true);
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "RegistrationScreen :: onDestroy()");
		super.onDestroy();
	}
	/*
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "RegistrationScreen :: onBackPressed()");
		super.onBackPressed();
	}
	 */
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "RegistrationScreen :: onLowMemory()");
		super.onLowMemory();
	}


	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "RegistrationScreen :: onKeyDown()");
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "RegistrationScreen :: onKeyUp()");
		return super.onKeyUp(keyCode, event);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "RegistrationScreen :: onRestart()");
		super.onRestart();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "RegistrationScreen :: onStart()");
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "RegistrationScreen :: onStop()");
		super.onStop();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "RegistrationScreen :: onPause()");
		super.onPause();
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "RegistrationScreen :: onPostCreate()");
		super.onPostCreate(savedInstanceState);
	}

	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "RegistrationScreen :: onPostResume()");
		super.onPostResume();
		//CustomAlertDialog.showCustomDialog(getApplicationContext());
		
//		if(DataUtil.userName.equals("demouser@mocansa.com")) {
//			showDialog(TEST_DIALOG);
//		}
				
	}




	@Override
	public Dialog onCreateDialog(int dialogId) {
		Dialog dialog = null;
		switch (dialogId) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
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



	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener =
		new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear + 1;
			mDay = dayOfMonth ;
			updateDateDisplay();
		}
	};


	private void updateDateDisplay() {
		textBirthDate.setText(
				new StringBuilder()
				.append(pad(mDay)).append("-")
				.append(pad(mMonth)).append("-")
				.append(pad(mYear)));
	}

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}


	AlertDialog alertDialog; 

	void showDialog(String alertMsg) {
		Toast.makeText(getApplicationContext(), alertMsg, Toast.LENGTH_SHORT).show();
	}


	void userRegistration() {
		//Log.i(Constants.TAG, "# RegistrationScreen # ----- userRegistration ------ ");
		final UserDetails userDetails = new UserDetails();
		userDetails.setFirstName(fNameText.getText().toString());
		//userDetails.setLastName(lNameText.getText().toString());

		//userDetails.setAddress1(addr1Text.getText().toString());
		//userDetails.setAddress2(addr2Text.getText().toString());
		userDetails.setGender(genderSpinner.getSelectedItem().toString());
		//userDetails.setCity(cityText.getText().toString());
//		if(textBirthDate.getText() != null && textBirthDate.getText().toString().length() > 0) {
//			
//			String[] str = Utils.getBirthDateDetails(textBirthDate.getText().toString());
//			
//			String date = pad(Integer.parseInt(str[2])) + "-" + pad(Integer.parseInt(str[1])) + "-" + pad(Integer.parseInt(str[0])); 
//			userDetails.setDateOfBirth(date);
//			
////			userDetails.setDateOfBirth(new StringBuilder()
////			.append(pad(mDay)).append("-")
////			.append(pad(mMonth)).append("-")
////			.append(pad(mYear)).toString());
//		}else{
//			userDetails.setDateOfBirth("0001-01-01");
//		}
		//userDetails.setCountry(countrySpinner.getSelectedItem().toString());
		userDetails.setDeviceIndentifier("213123");
		//userDetails.setHowToKnow("don't know");
		//userDetails.setMobileNumber(contactNoText.getText().toString());
		//userDetails.setInterest("");
		//userDetails.setEmail(emailText.getText().toString());
		userDetails.setStatus("2222");
		//userDetails.setAlertEmail(alertEmail.isChecked() == true ? "Yes" : "No");
		userDetails.setSource("0");
		//userDetails.setUserName(userNameText.getText().toString());
		userDetails.setPassword(passwordText.getText().toString());
		//userDetails.setPassword(passwordText.getText().toString());
		userDetails.setZipcode(zipText.getText().toString());
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					GeneralResponse generalRespone = WSSender.sendEditProfileRequest(userDetails);
					if(generalRespone.isSuccess()) {
						infoMessage = AlertMsgUtil.getEditProfileSuccessMsg();
					}else {
						infoMessage = AlertMsgUtil.getEditProfileFailureMsg();
					}
					messageHandler.sendMessage(Message.obtain(messageHandler,0));
				}catch(IOException ioe) {
					ioe.printStackTrace();
					if(isOnline()) {
						errorMessage = AlertMsgUtil.getCommonErrorMsg();
					}else {
						errorMessage = AlertMsgUtil.getConnectFailureMessage();
					}
					messageHandler.sendMessage(Message.obtain(messageHandler,2));
				}catch(Exception e) {
					errorMessage = AlertMsgUtil.getCommonErrorMsg();
					messageHandler.sendMessage(Message.obtain(messageHandler,2));
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
			case 0: 
//				DataUtil.userDetails.setDateOfBirth(new StringBuilder()
//				.append(pad(mYear)).append("-")
//				.append(pad(mMonth)).append("-")
//				.append(pad(mDay)).toString());
				
				//DataUtil.userDetails.setDateOfBirth(textBirthDate.getText().toString());
				DataUtil.userDetails.setFirstName(fNameText.getText().toString());
				//DataUtil.userDetails.setLastName(lNameText.getText().toString());
				//DataUtil.userDetails.setEmail(emailText.getText().toString());
				DataUtil.userDetails.setGender(genderSpinner.getSelectedItem().toString());
				//DataUtil.userDetails.setCountry(countrySpinner.getSelectedItem().toString());
				//DataUtil.userDetails.setAddress1(addr1Text.getText().toString());
				//DataUtil.userDetails.setAddress2(addr2Text.getText().toString());
				//DataUtil.userDetails.setCity(cityText.getText().toString());
				DataUtil.userDetails.setZipcode(zipText.getText().toString());
				//DataUtil.userDetails.setMobileNumber(contactNoText.getText().toString());
			

				Toast.makeText(getApplicationContext(), infoMessage, Toast.LENGTH_SHORT).show();
				finish();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), infoMessage,
						Toast.LENGTH_SHORT).show();
				break;
			case 2: 
				Toast.makeText(getApplicationContext(), errorMessage,
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};


	boolean validation() {

		String firstName = fNameText.getText().toString();
		//String lastName = lNameText.getText().toString();
		//String email = emailText.getText().toString();
		//String contactNumber = contactNoText.getText().toString();
		//String address1  = addr1Text.getText().toString();
		//String address2 = addr2Text.getText().toString();
		//String address3 = addr3Text.getText().toString();
		//String city = cityText.getText().toString();
		//String state = stateText.getText().toString();
		//		String country = "india";
		String zip = zipText.getText().toString();
		String password = passwordText.getText().toString();
		//String conPassword = confirmPasswordText.getText().toString();
		String age = ageText.getText().toString();
		//String stateNAme = stateText.getText().toString();


		if(firstName == null || firstName.trim().length() <= 0) {
			showDialog("Firstname can not be empty !");
			return false;
		}
//
//		if(lastName == null || lastName.trim().length() <= 0) {
//			showDialog("Lastname can not be empty !");
//			return false;
//		}
//
//		if(email == null || email.trim().length() <= 0) {
//			showDialog("Email can not be empty !");
//			return false;
//		}

//		if(email != null && email.trim().length() > 0) {
//			if(!Utils.getEmailValidationStatus(email)) {
//				showDialog(AlertMsgUtil.getInvalidEmailAddress());
//				return false;
//			}
//		}
		

//		if(address1 == null || address1.trim().length() <= 0) {
//			// show alert
//			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
//			showDialog("Address1 can not be empty !");
//			return false;
//		}
//
//		if(address2 == null || address2.trim().length() <= 0) {
//			// show alert
//			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
//			showDialog("Address2 can not be empty !");
//			return false;
//		}
//
//
//		if(city == null || city.trim().length() <= 0) {
//			// show alert
//			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
//			showDialog("City can not be empty !");
//			return false; 
//		}
//
		
		if(age == null || age.trim().length() <= 0) {
			showDialog("Age cannot be empty!");
			return false;
		}
		
		if(zip == null || zip.trim().length() <= 0) {
			// show alert
			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
			showDialog("Postal code cannot be empty!");
			return false;
		}
		
		
		
//		if(stateNAme == null || stateNAme.trim().length() <= 0) {
//			// show alert
//			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
//			showDialog("state cannot be empty!");
//			return false;
//		}
//		
//		if(dataOfBirth == null || dataOfBirth.trim().length() <= 0) {
//			// show alert
//			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
//			showDialog("Date of birth cannot be empty!");
//			return false;
//		}

		if(password == null || password.trim().length() <= 0) {
			// show alert
			//Log.i(Constants.TAG,"# LoginScreen # ------ show dialog ----- ");
			showDialog(AlertMsgUtil.getAlertMsgEmptyPassword());
			return false;
		}

		if(password.trim().length() <= 5){
			showDialog(AlertMsgUtil.getAlertMsgPasswordLength());
			//showToast("Password must be atleast 6 chars!");
			return false;
		}


//		if(!password.equals(conPassword)) {
//			showDialog(AlertMsgUtil.getAlertMsgPwdandConPwdDontMatch());
//			return false;
//		}


		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == submitBtn) {
			// Update Profile
			if(validation()) {
				// 
				showDialog(PRO_DIALOG);
				userRegistration();
			}

		}else if(v == backBtn) {
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
