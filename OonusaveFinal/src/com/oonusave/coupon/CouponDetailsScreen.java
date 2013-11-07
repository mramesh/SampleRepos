package com.oonusave.coupon;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.AddToWalletResponse;
import com.oonusave.coupon.model.Coupon;
import com.oonusave.coupon.model.GeneralResponse;
import com.oonusave.coupon.model.UseCouponResponse;
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
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
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
public class CouponDetailsScreen extends BaseActivity implements OnClickListener{

	TextView couponNameText,shopNameText,addressText,shortDescText,descText,priceText,locText,categoryText,subCategoryText,expiresText;
	EditText emailText01;
	Button locationBtn,useCouponBtn,addToWalletBtn,tellAFriendBtn;
	ImageView storeImage;
	Coupon coupon = null;
	ImageButton couponButton = null;
	private static final int PRO_DIALOG = 0;
	private static final int DEFAULT_DIALOG = 1,TEST_DIALOG = 2;
	
	private String errorMessage = "",infoMessage = "";
	private boolean isMyWallet = false;
	private DrawableManager drawableManager = new DrawableManager();
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		//Log.i(Constants.TAG, "# CouponDetailsScreen # ------------ onCreate() ------------ ");
		setContentView(R.layout.coupon_details_view);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);

		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);	
		Intent i = getIntent();
		isMyWallet = i.getBooleanExtra("MyWallet", false);
		coupon = (Coupon) i.getSerializableExtra("couponObj");

		couponButton = (ImageButton)findViewById(R.id.btnLeft);
		couponButton.setVisibility(ImageButton.VISIBLE);
		if(isMyWallet) {
			//couponButton.setBackgroundResource(R.drawable.mywallet_button);
			couponButton.setBackgroundResource(ImageUtils.getMyWalletBtnImage());
		}else {
			//couponButton.setBackgroundResource(R.drawable.coupons_button);
			couponButton.setBackgroundResource(ImageUtils.getBackIamge());
		}
		couponButton.setOnClickListener(this);
		((TextView)findViewById(R.id.couponStoreName)).setText(coupon.getStoreName());
		StringBuffer sb = new StringBuffer();
		
		if(coupon.getAddress1() != null && !coupon.getAddress1().equalsIgnoreCase("")) {
			sb.append(coupon.getAddress1()+"\n");
		}

		if(coupon.getAddress2() != null && !coupon.getAddress2().equalsIgnoreCase("")) {
			sb.append(coupon.getAddress2()+"\n");
		}
		
		

		if(coupon.getLocation() != null && !coupon.getLocation().equalsIgnoreCase("")) {
			sb.append(coupon.getLocation()+"\n");
		}
			

		if(coupon.getCountry() != null && !coupon.getCountry().equalsIgnoreCase("")) {
			sb.append(coupon.getCountry()+"\n");
		}
		
		if(coupon.getPhoneNo() != null && !coupon.getPhoneNo().equalsIgnoreCase("")) {
			sb.append(coupon.getPhoneNo()+"\n");
		}
		
		if(coupon.getWebAddress() != null && !coupon.getWebAddress().equalsIgnoreCase("")) {
			sb.append(coupon.getWebAddress()+"\n");
		}

		//coupon.getAddress1()+"\n"+ + "\n" + coupon.getLocation() + " \n" + coupon.getCountry()
		((TextView)findViewById(R.id.title)).setText(sb.toString());
		//Log.i(Constants.TAG, "# CouponDetailsScreen # --- Desc ---- > " + coupon.getDesc());
		//Log.i(Constants.TAG, "# CouponDetailsScreen # --- ImagePath ---- > " + coupon.getImagePath());
		//Log.i(Constants.TAG, "# CouponDetailsScreen # --- CouponName ---- > " + coupon.getCouponName());
		/*
		couponIdText 	= (TextView) findViewById(R.id.couponIdText);
		shopNameText 	= (TextView) findViewById(R.id.shopNameText);
		addressText 	= (TextView) findViewById(R.id.addressText);
		shortDescText 	= (TextView) findViewById(R.id.descText);
		descText 		= (TextView) findViewById(R.id.descText); 
		locText 		= (TextView) findViewById(R.id.locText);
		categoryText 	= (TextView) findViewById(R.id.categoryText);
		subCategoryText = (TextView) findViewById(R.id.subCategoryText);
		descText 		= (TextView) findViewById(R.id.descText);
		expiresText 	= (TextView) findViewById(R.id.expiresText);

		couponIdText.setText("123");
		shopNameText.setText(coupon.getShopName());
		addressText.setText(coupon.getAddress());
		shortDescText.setText(coupon.getShortDesc());
		descText.setText(coupon.getShortDesc());
		locText.setText(coupon.getLati()+"" +coupon.getLongi());
		categoryText.setText(coupon.getCategory());
		subCategoryText.setText(coupon.getSubCategory());
		expiresText.setText(coupon.getExpiryDate());
		 */
		shortDescText = (TextView) findViewById(R.id.descText);
		couponNameText = (TextView) findViewById(R.id.title);

		storeImage = (ImageView) findViewById(R.id.titleImg);

		addToWalletBtn = (Button) findViewById(R.id.add_to_wallet_btn);
		
		TextView excTextView = (TextView) findViewById(R.id.exclusiveCouponText);
		useCouponBtn = (Button) findViewById(R.id.use_coupon_btn);
		if(coupon.getFeatured() == 1) {
			excTextView.setVisibility(TextView.VISIBLE);
			useCouponBtn.setVisibility(Button.GONE);
		}else {
			excTextView.setVisibility(TextView.GONE);
			useCouponBtn.setVisibility(Button.VISIBLE);
		}
		
		locationBtn = (Button) findViewById(R.id.location_btn);
		tellAFriendBtn = (Button) findViewById(R.id.tell_a_friend_btn);

		addToWalletBtn.setBackgroundResource(ImageUtils.getAddToWalletBtnImage());
		useCouponBtn.setBackgroundResource(ImageUtils.getUseCouponBtnImage());
		locationBtn.setBackgroundResource(ImageUtils.getLocationBtnImage());
		tellAFriendBtn.setBackgroundResource(ImageUtils.getTellAFriendBtnImage());


		OnClickListener onClickListener = new OnClickListener();
		addToWalletBtn.setOnClickListener(onClickListener);
		useCouponBtn.setOnClickListener(onClickListener);
		locationBtn.setOnClickListener(onClickListener);
		tellAFriendBtn.setOnClickListener(onClickListener);

		if(isMyWallet){
			//addToWalletBtn.setVisibility(Button.INVISIBLE);
			//addToWalletBtn.setBackgroundResource(R.drawable.remove_from_wallet);
			addToWalletBtn.setBackgroundResource(ImageUtils.getRemoveFromWalletImage());
		}

		shortDescText.setText( "\n" + coupon.getShortDesc() + "\n\n"+ coupon.getDesc());
		couponNameText.setTag(coupon.getCouponName());
		//storeImage.setBackgroundColor(Color.TRANSPARENT);
		storeImage.setBackgroundResource(R.drawable.store_img_bg);
		//Drawable image = ImageOperations(getApplicationContext(),coupon.getImagePath(),"image.jpg");
		//storeImage.setImageDrawable(image);
		drawableManager.fetchDrawableOnThread(coupon.getImagePath(), storeImage);
		//storeImage.setPadding(6, 6, 6, 4);
		
		insertClickReport();
		
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.COUPON_DETAILS_SCREEN;

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


	class OnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v instanceof Button) {
				if(v == addToWalletBtn) {
					//Log.i(Constants.TAG, "# CouponDetailsScreen # ----- addToWallet button clicked ------ ");
					if(isMyWallet) 
						removeFromWallet();
					else
						addToWallet();
				}else if(v == useCouponBtn){
					//Log.i(Constants.TAG, "# CouponDetailsScreen # ----- useCoupon button clicked ------ ");

					useCoupon();	
				}else if(v == locationBtn) {
					//Log.i(Constants.TAG, "# CouponDetailsScreen # ----- location button clicked ------ ");
					Toast.makeText(getApplicationContext(), " Please wait!" ,
							Toast.LENGTH_SHORT).show();
					showLocation();
				}else if(v == tellAFriendBtn) {
					showDialog(1);
				}else{}
			}
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

		case DEFAULT_DIALOG :{

			AlertDialog.Builder alertBuilder = new
			AlertDialog.Builder(this);
			alertBuilder.setTitle(TitleTextUtils.getTellAFriendText())
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
						//Log.i(Constants.TAG, " Email address has comma ");
						
						String emailIds[] = emailText01.getText().toString().split(",");
						for(int i = 0 ; i < emailIds.length ; i++) {
							if(!Utils.getEmailValidationStatus(emailIds[i].toString())) {
								Toast.makeText(getApplicationContext(), AlertMsgUtil.getInvalidEmailAddressMessage(), Toast.LENGTH_SHORT).show();
								return;
							}
						}
					}else {
						//Log.i(Constants.TAG, " Email address don't have comma ");
						if(!Utils.getEmailValidationStatus(emailText01.getText().toString())) {
							Toast.makeText(getApplicationContext(), AlertMsgUtil.getInvalidEmailAddressMessage(), Toast.LENGTH_SHORT).show();
							return;
						}
					}





					if(emailText01.getText() != null && emailText01.getText().toString().length() > 0) {
						dialog.dismiss();
						sendTellAFriend();
					}					

				}
			});

			LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.update_status_custom_dialog,null);
			TextView Text01 = (TextView)layout.findViewById(R.id.Text01);
			TextView Text02 = (TextView)layout.findViewById(R.id.Text02);
			emailText01 = (EditText)layout.findViewById(R.id.EditText01);
			Text01.setText(TitleTextUtils.getEmailAddresText());
			Text02.setText(TitleTextUtils.getEmailAddresDescText());

			alertBuilder.setView(layout);
			dialog = alertBuilder.create();
			break;
		}
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
		}
		return dialog;
	}
	
	
	void showRegistrationScreen() {
		Intent intent = new Intent(this,ShortRegistrationScreen.class);
		startActivity(intent);
		finish();
	}



	void sendTellAFriend() {
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					GeneralResponse generalResponse = WSSender.sendTellAFriend(DataUtil.userDetails.getUserName(), (int)coupon.getCouponId(), emailText01.getText().toString());
					tellAFriendHandler.sendMessage(Message.obtain(tellAFriendHandler, 0, generalResponse.getMessage()));
				}catch(Exception e) {
					tellAFriendHandler.sendMessage(Message.obtain(tellAFriendHandler, 1, e.getMessage()));
				}
			}
		}.start();
	}
	
	
	void insertClickReport() {
		//showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					GeneralResponse generalResponse = WSSender.insertClickReport((int) coupon.getCouponId());
//					if(generalResponse.isSuccess()) {
//						clickReportHandler.sendMessage(Message.obtain(clickReportHandler, 0));
//					}else {
//						clickReportHandler.sendMessage(Message.obtain(clickReportHandler, 1, "error"));
//					}
				}catch(Exception e) {
					e.printStackTrace();
					
				}
			}
		}.start();
	}

	private Handler tellAFriendHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0:
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getEmailAlertMessage(), Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
	
	
	private Handler clickReportHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0:
				Toast.makeText(getApplicationContext(), "Click report success!!!!", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), "Click report fail!!!!", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};





	private Handler addToWalletMessageHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			//Log.i(Constants.TAG, "Inside handle message ------ > " + msg.what);
			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0: 

				Toast.makeText(getApplicationContext(), infoMessage,Toast.LENGTH_SHORT).show();
				if(isMyWallet){
					startActivity(new Intent(getApplicationContext(),MyWalletScreen.class));
					finish();
				}
				break;
			case 1:
				Toast.makeText(getApplicationContext(), errorMessage,
						Toast.LENGTH_SHORT).show();
				break;
			case 2: 
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getConnectFailureMessage(),
						Toast.LENGTH_SHORT).show();
				break;
			}


		}
	};



	private Handler useCouponMessageHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			//Log.i(Constants.TAG, "Inside handle message ------ > " + msg.what);
			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0: 
				showAlert(); 
				break;
			case 1:
				Toast.makeText(getApplicationContext(), errorMessage,
						Toast.LENGTH_SHORT).show();
				break;
			case 2: 
				Toast.makeText(getApplicationContext(), errorMessage,
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};


	void showLocation() {
		//Log.i(Constants.TAG, "# CouponDetailsScreen # ----- showLocation ------ ");
		Intent i = new Intent(this,MyMap.class);
		//Intent i = new Intent(this,MapScreen.class);
		i.putExtra("coupon", coupon);
		startActivity(i);

	}


	void useCoupon() {
		
//		if(coupon.getFeatured() == 1) {
//			Toast.makeText(getApplicationContext(), "Non exclusive coupon", Toast.LENGTH_LONG).show();
//		}else {
			
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				//Log.i(Constants.TAG, "# CouponDetailsScreen # ----- useCoupon ------ ");
				
				try {
			
					
					if(WSSender.sendValidateCouponRequest(DataUtil.userDetails.getUserId(), coupon.getCouponId())) {
						useCouponMessageHandler.sendMessage(Message.obtain(useCouponMessageHandler,0));
					}else {
						//Log.i(Constants.TAG,"# CouponAdaptorView # ------ INVALID COUPON  ----- ");
						//errorMessage = "Invalid Coupon!";
						errorMessage = AlertMsgUtil.getCouponUsedMessage();
						useCouponMessageHandler.sendMessage(Message.obtain(useCouponMessageHandler,1));
					}
				}catch(IOException ioe) {
					if(isOnline()) {
						errorMessage = AlertMsgUtil.getConnectFailureMessage();
					}else {
						errorMessage = AlertMsgUtil.getCommonErrorMsg();
					}
					useCouponMessageHandler.sendMessage(Message.obtain(useCouponMessageHandler,2));
				}
			}}.start();
		//}
	}



	AlertDialog alertDialog; 

	void showDialog1(String alertMsg) {
		alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Error");
		alertDialog.setMessage(alertMsg);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				alertDialog.cancel();
			} 
		}); 

	alertDialog.show();
	}
	
	
	

	void showAlert(){




		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which){
				case DialogInterface.BUTTON_POSITIVE:
					//Yes button clicked
					try {
						UseCouponResponse useCouponResponse = WSSender.sendUseCouponRequest(DataUtil.userId, coupon.getCouponId(), coupon.getStoreId());
						if(useCouponResponse != null) {
							if(useCouponResponse.isSuccess()) {
								Intent i = new Intent(getApplicationContext(),BarcodeScreen.class);
								//i.putExtra("barcodeImageUrl", coupon.getBarcode());
								i.putExtra("coupon", coupon);
								startActivity(i);
								finish();
							}else {
								// Show alert
								//showDialog1(useCouponResponse.getMessage());
								showDialog1(AlertMsgUtil.getCouponUsedMessage());
							}
						}
					}catch(IOException ioe) {
						showDialog1(AlertMsgUtil.getConnectFailureMessage());
					}
					break;

				case DialogInterface.BUTTON_NEGATIVE:
					//No button clicked
					break;
				}
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(Constants.getUseCouponMessage()).setPositiveButton("Yes", dialogClickListener)
		.setNegativeButton("No", dialogClickListener).show();
	}



	void addToWallet() {
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					AddToWalletResponse addToWalletResponse = WSSender.sendAddToWalletRequest((int)DataUtil.userDetails.getUserId(),(int)coupon.getCouponId());
					if(addToWalletResponse.isSuccess()) {
						//showDialog(addToWalletResponse.getMessage());
						//infoMessage = addToWalletResponse.getMessage();
						infoMessage = AlertMsgUtil.getCouponAddedToWalletMessage();
						addToWalletMessageHandler.sendMessage(Message.obtain(addToWalletMessageHandler,0));
					}else {
						//errorMessage = addToWalletResponse.getMessage();
						errorMessage = AlertMsgUtil.getCouponAlreadyAddedMessage();
						addToWalletMessageHandler.sendMessage(Message.obtain(addToWalletMessageHandler,1));
					}
				}catch(IOException e) {
					if(isOnline()) {
						errorMessage = AlertMsgUtil.getConnectFailureMessage();
					}else {
						errorMessage = AlertMsgUtil.getCommonErrorMsg();
					}
					addToWalletMessageHandler.sendMessage(Message.obtain(addToWalletMessageHandler,2));
				}
			}
		}.start();

	}


	void removeFromWallet() {
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					AddToWalletResponse addToWalletResponse = WSSender.sendRemoveFromWalletRequest(DataUtil.userId,coupon.getCouponId());
					if(addToWalletResponse.isSuccess()) {
						//infoMessage = addToWalletResponse.getMessage();
						infoMessage = AlertMsgUtil.getCouponRemovedFromWalletMessage();
						addToWalletMessageHandler.sendMessage(Message.obtain(addToWalletMessageHandler,0));
					}else {
						errorMessage = addToWalletResponse.getMessage();
						addToWalletMessageHandler.sendMessage(Message.obtain(addToWalletMessageHandler,1));
					}
				}catch(IOException ioe) {
					ioe.printStackTrace();
					if(isOnline()) {
						errorMessage = AlertMsgUtil.getConnectFailureMessage();
					}else {
						errorMessage = AlertMsgUtil.getCommonErrorMsg();
					}
					addToWalletMessageHandler.sendMessage(Message.obtain(addToWalletMessageHandler,2));
				}catch(Exception e) {
					errorMessage = AlertMsgUtil.getCommonErrorMsg();
					addToWalletMessageHandler.sendMessage(Message.obtain(addToWalletMessageHandler,2));
				}
			}
		}.start();
	}


	private Drawable ImageOperations(Context ctx, String url, String saveFilename) {
		try {
			InputStream is = (InputStream) this.fetch(url);
			Drawable d = Drawable.createFromStream(is, "src");
			return d;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch(Exception e) {
			return null;
		}
	}

	public Object fetch(String address) throws MalformedURLException,IOException {
		URL url = new URL(address);
		Object content = url.getContent();
		return content;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == couponButton) {
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
