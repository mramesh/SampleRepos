package com.oonusave.coupon;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Coupon;
import com.oonusave.coupon.model.GeneralResponse;
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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
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

public class UsedCouponDetailsScreen extends BaseActivity implements View.OnClickListener{

	TextView couponNameText,shopNameText,addressText,shortDescText,descText,priceText,locText,categoryText,subCategoryText,expiresText;
	Button locationBtn,useCouponBtn,addToWalletBtn,tellAFriendBtn;
	ImageView storeImage;
	Coupon coupon = null;
	ImageButton couponButton = null;
	private static final int PRO_DIALOG = 0;
	private static final int DEFAULT_DIALOG = 1;
	private String errorMessage = "",infoMessage = "";
	private boolean isMyWallet = false;
	private DrawableManager drawableManager = new DrawableManager();
	
	EditText emailText01;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		//Log.i(Constants.TAG, "# UsedCouponDetailsScreen # ------------ onCreate() ------------ ");
		setContentView(R.layout.used_coupon_details_view);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);

		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);	
		Intent i = getIntent();
		coupon = (Coupon) i.getSerializableExtra("couponObj");

		couponButton = (ImageButton)findViewById(R.id.btnLeft);
		couponButton.setVisibility(ImageButton.VISIBLE);
		//couponButton.setBackgroundResource(R.drawable.coupons_button);
		couponButton.setBackgroundResource(ImageUtils.getCouponsBtnImage());
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
		
		//coupon.getAddress1()+"\n"+coupon.getAddress2() + "\n" + coupon.getAddress3() + " \n" + coupon.getCountry()
		((TextView)findViewById(R.id.title)).setText(sb.toString());
		//Log.i(Constants.TAG, "# CouponDetailsScreen # --- Desc ---- > " + coupon.getDesc());
		//Log.i(Constants.TAG, "# CouponDetailsScreen # --- ImagePath ---- > " + coupon.getImagePath());
		//Log.i(Constants.TAG, "# CouponDetailsScreen # --- CouponName ---- > " + coupon.getCouponName());
				shortDescText = (TextView) findViewById(R.id.descText);
		couponNameText = (TextView) findViewById(R.id.title);
		storeImage = (ImageView) findViewById(R.id.titleImg);
		locationBtn = (Button) findViewById(R.id.location_btn);
		OnClickListener onClickListener = new OnClickListener();
		tellAFriendBtn = (Button) findViewById(R.id.tell_a_friend_btn);
		locationBtn.setBackgroundResource(ImageUtils.getLocationBtnImage());
		tellAFriendBtn.setBackgroundResource(ImageUtils.getTellAFriendBtnImage());
		
		
		
		locationBtn.setOnClickListener(onClickListener);
		tellAFriendBtn.setOnClickListener(onClickListener);

//		if(isMyWallet){
//			//addToWalletBtn.setVisibility(Button.INVISIBLE);
//			addToWalletBtn.setBackgroundResource(R.drawable.remove_from_wallet);
//		}

		shortDescText.setText( "\n" + coupon.getShortDesc() + "\n\n"+ coupon.getDesc());
		couponNameText.setTag(coupon.getCouponName());
		storeImage.setBackgroundColor(Color.TRANSPARENT);
		drawableManager.fetchDrawableOnThread(coupon.getImagePath(), storeImage);
		//Drawable image = ImageOperations(getApplicationContext(),coupon.getImagePath(),"image.jpg");
		//storeImage.setImageDrawable(image);
		
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.USED_COUPON_DETAILS_SCREEN;
		super.onResume();
	}

	class OnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v instanceof Button) {
				if(v == locationBtn) {
					//Log.i(Constants.TAG, "# CouponDetailsScreen # ----- location button clicked ------ ");
					Toast.makeText(getApplicationContext(), AlertMsgUtil.getLoadingMessageText(),
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
			dialog1.setMessage("Please wait...");
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

		}
		return dialog;
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
	
	
	private Handler tellAFriendHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0:
				Toast.makeText(getApplicationContext(),  AlertMsgUtil.getEmailAlertMessage(), Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	

	void showLocation() {
		//Log.i(Constants.TAG, "# CouponDetailsScreen # ----- showLocation ------ ");
		Intent i = new Intent(this,MyMap.class);
		i.putExtra("coupon", coupon);
		startActivity(i);

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




}
