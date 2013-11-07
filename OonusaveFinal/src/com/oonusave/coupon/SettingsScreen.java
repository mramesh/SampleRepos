package com.oonusave.coupon;

import java.util.ArrayList;
import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Category;
import com.oonusave.coupon.model.UpdateSettingsResponse;
import com.oonusave.coupon.model.UserDetails;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.ws.WSSender;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.Contacts.Data;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Ramesh
 *
 */
public class SettingsScreen extends BaseActivity implements OnClickListener{

	ImageButton sendBtn,backBtn;
	private static final int PRO_DIALOG = 0,TEST_DIALOG = 1;
	SeekBar seekBar = null;
	TextView seekBarValue,titleBarTextView,preLangTextView,priLangTextView,secLangTextView,radiusSettingsTextView ;
	Button submitBtn = null;
	String errorMessage= "";

	String[] items = new String[] {"English", "Danish"};
	Spinner priLangSpinner ,secLangSpinner, categorySpinner;
	boolean fromCouponScreen = false;
	SharedPreferences settings = null;
	public static final String PREFS_NAME = "MyPrefsFile";
	CheckedTextView checkBox = null;
	
	protected Button selectColoursButton;

	//protected CharSequence[] categories = { "Category 1", "Category 2", "Category 3", "Category 4", "Category 5", "Category 6" };
	protected ArrayList<CharSequence> selectedCategories = new ArrayList<CharSequence>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.settings_view);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		settings = getSharedPreferences(PREFS_NAME, 0);
		fromCouponScreen = getIntent().getBooleanExtra("fromCouponScreen", false);
		
		sendBtn = (ImageButton)findViewById(R.id.btnRight);
		sendBtn.setVisibility(ImageButton.INVISIBLE);
		backBtn = (ImageButton)findViewById(R.id.btnLeft);
		backBtn.setVisibility(ImageButton.VISIBLE);
		//backBtn.setBackgroundResource(R.drawable.back_button);
		backBtn.setBackgroundResource(ImageUtils.getBackIamge());
		
		checkBox = (CheckedTextView) findViewById(R.id.checkbox);
		
		checkBox.setChecked(DataUtil.pushNoti);
		
		backBtn.setOnClickListener(this);
		seekBar = (SeekBar)findViewById(R.id.seekbar);
		final TextView seekBarValue = (TextView)findViewById(R.id.seekbarMinText);
		titleBarTextView = (TextView)findViewById(R.id.titleBarTextView);
		preLangTextView = (TextView)findViewById(R.id.preLangTextView);
		priLangTextView = (TextView)findViewById(R.id.priLangTextView);
		secLangTextView = (TextView)findViewById(R.id.secLangTextView);
		radiusSettingsTextView = (TextView)findViewById(R.id.radiusSettingsTextView);
		
		titleBarTextView.setText(TitleTextUtils.getSettingsTitleStr());
		preLangTextView.setText(TitleTextUtils.getSettingsPreLangStr());
		priLangTextView.setText(TitleTextUtils.getSettingsPriLangStr());
		secLangTextView.setText(TitleTextUtils.getSettingsSecLangStr());
		radiusSettingsTextView.setText(TitleTextUtils.getSettingsRadiusStr());
		
				
		seekBar.setProgress(DataUtil.userDetails.getRadius());
		//seekBarValue.setText(DataUtil.userDetails.getRadius() + " kms");

		priLangSpinner = (Spinner) findViewById(R.id.priLangSpinner);
		secLangSpinner = (Spinner) findViewById(R.id.secLangSpinner);
		//categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,DataUtil.prefLang);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		
		
//		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,DataUtil.prefLang);
//		adapter1.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
		
		priLangSpinner.setAdapter(adapter);
		secLangSpinner.setAdapter(adapter1);
		//categorySpinner.setAdapter(adapter2);
		
		
		
		
		
		
		
		
		submitBtn = (Button) findViewById(R.id.submit_button);
		submitBtn.setBackgroundResource(ImageUtils.getSubmitImage());
		submitBtn.setOnClickListener(this);

		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				seekBarValue.setText(String.valueOf(progress)+ " km");
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
		
		seekBar.setProgress(DataUtil.radius);
		seekBarValue.setText(String.valueOf(DataUtil.radius) + " km");
		int secLangSelection = 0;
		
//		if(DataUtil.secLang.equalsIgnoreCase("English")) {
//			secLangSelection = 1;
//		}else if(DataUtil.secLang.equalsIgnoreCase("Danish")) {
//			secLangSelection = 2;
//		}else {
//			secLangSelection = 0;
//		}
		secLangSpinner.setSelection(secLangSelection);
		secLangSpinner.setVisibility(View.GONE);
		priLangSpinner.setVisibility(View.GONE);
		//priLangSpinner.setSelection(DataUtil.priLang.equalsIgnoreCase("English") ? 0 : 1);
		
		
		selectColoursButton = (Button) findViewById(R.id.select_categories);
		selectColoursButton.setOnClickListener(this);
		for(Category category : DataUtil.selectedCategory) {
			selectedCategories.add(category.getCategoryName());
		}
		
		
		
		checkBox.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				checkBox.toggle();
				
			}
			
		});
		
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.SETTINGS_SCREEN;
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
		


	private Handler messageHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0: 
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getSettingsSaveSuccessMsg(),Toast.LENGTH_SHORT).show();
				if(fromCouponScreen) {
					
					
					
					//startActivity(new Intent(getApplicationContext(),CouponListScreen.class));
					startActivity(new Intent(getApplicationContext(),CouponsScreen.class));
				}else{
					startActivity(new Intent(getApplicationContext(),MoreScreen1.class));
				}
				finish();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), errorMessage ,
						Toast.LENGTH_SHORT).show();
				break;
			case 2: 
				Toast.makeText(getApplicationContext(), errorMessage ,
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == submitBtn) {
			String lang1 = priLangSpinner.getSelectedItem().toString();
			String lang2 = secLangSpinner.getSelectedItem().toString();
			if(lang1.equalsIgnoreCase(lang2)) {
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getBothPriSecNotSameMsg(),Toast.LENGTH_SHORT).show();
				return;
			}
			
			
			SharedPreferences.Editor editor = settings.edit();
			
			
			editor.putString("Language", "English");
			// Don't forget to commit your edits!!!
			editor.commit();
			
//			DataUtil.priLang = "English";
//			if(lang2.equalsIgnoreCase("None")) {
//				DataUtil.secLang = "";
//			}else {
//				DataUtil.secLang = lang2;
//			}
			DataUtil.radius = seekBar.getProgress();
			//Log.i(Constants.TAG, "Radius ------- > " + DataUtil.radius);
			
			String pushNoti = "0";
			if(checkBox.isChecked()) {
				DataUtil.pushNoti = true;
				pushNoti = "1";
			}
			updateSettings(pushNoti);
			
		}else if(v == backBtn) {
			finish();
		}else if(v == selectColoursButton) {
			showSelectColoursDialog();
		}
	}
	
	protected void onChangeSelectedColours() {
		StringBuilder stringBuilder = new StringBuilder();
		
//		for(CharSequence colour : selectedCategories) {
//			
//			
//			DataUtil.selectedCategory.add(;
//			stringBuilder.append(colour + ",");
//		}
		
		ArrayList<Category> list = new ArrayList<Category>();
		for(Category category: DataUtil.categoryList) {
			if(selectedCategories.contains(category.getCategoryName())) {
				Log.i(Constants.TAG, " Selected Category Name ---- > " + category.getCategoryName());
				list.add(category);
			}
		}
		
		DataUtil.selectedCategory = list;
		
		
		
		//selectColoursButton.setText(stringBuilder.toString());
	}

	protected void showSelectColoursDialog() {
		
		int size = DataUtil.categoryList.size();
		
		Log.i(Constants.TAG, "=== showSelectColoursDialog() === " + size);
		boolean[] checkedColours = new boolean[size];
		//int count = DataUtil.categoryList.size();
		final CharSequence[] s = new CharSequence[size];
		for(int i = 0; i < size; i++){
			//checkedColours[i] = selectedCategories.contains(DataUtil.categories[i]);
			String name = DataUtil.categoryList.get(i).getCategoryName();
			Log.i(Constants.TAG, "Name ---- > " + name);
			s[i] = name;
			checkedColours[i] = selectedCategories.contains(name);
		}

		DialogInterface.OnMultiChoiceClickListener coloursDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				if(isChecked) {
					selectedCategories.add(s[which]);
				}else {
					selectedCategories.remove(s[which]);
				}
				onChangeSelectedColours();
			}
		};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select Category");
		//CharSequence[] s = new CharSequence[DataUtil.categoryList.size()];
		//DataUtil.categoryList.toArray(s);
		builder.setMultiChoiceItems(s, checkedColours, coloursDialogListener);
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	

	private void updateSettings(final String pushNoti) {
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					
					
					UpdateSettingsResponse updateSettingResponse = WSSender.sendUpdateSettingsRequest((int)DataUtil.userId, "English", "English", DataUtil.radius, pushNoti);
					if(updateSettingResponse.isSuccess()){
						messageHandler.sendMessage(Message.obtain(messageHandler,0));
					}else {
						errorMessage = updateSettingResponse.getMessage();
						messageHandler.sendMessage(Message.obtain(messageHandler,1));
					}
				} catch (Exception e) {
					errorMessage = "Error while updating settings. please try again!";
					messageHandler.sendMessage(Message.obtain(messageHandler,1));
				}
			}
		}.start();
	}
}
