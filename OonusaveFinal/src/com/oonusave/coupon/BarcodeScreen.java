package com.oonusave.coupon;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Coupon;
import com.oonusave.coupon.model.GeneralResponse;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.ws.WSSender;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 
 * @author Ramesh
 *
 */
public class BarcodeScreen extends BaseActivity implements OnClickListener{

	ImageView barImage;
	//String imageUrl = "";
	ImageButton couponButton = null;
	Button trackButton;
	Coupon coupon = null;
	TextView descText,couponCode,expDateText;
	EditText EditText01;

	private static final int PRO_DIALOG = 0;
	private static final int DEFAULT_DIALOG = 1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.barcode_view);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		//((Button)findViewById(R.id.btnLeft)).setText("Registration");
		//((TextView)findViewById(R.id.titleText)).setText("Electronic Shop");
		couponButton = (ImageButton)findViewById(R.id.btnLeft);
		couponButton.setVisibility(ImageButton.VISIBLE);
		couponButton.setBackgroundResource(ImageUtils.getBackIamge());
		couponButton.setOnClickListener(this);
		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);	
		Intent intent = getIntent();
		coupon = (Coupon)intent.getSerializableExtra("coupon");
		barImage = (ImageView) findViewById(R.id.bar_image);
		descText = (TextView) findViewById(R.id.descText);
		//couponCode = (TextView) findViewById(R.id.couponCode);
		expDateText = (TextView) findViewById(R.id.expDateText);

		trackButton = (Button) findViewById(R.id.track_button);
		trackButton.setBackgroundResource(ImageUtils.getTrackAndShareBtnImage());
		trackButton.setOnClickListener(this);
		descText.setText(coupon.getShortDesc());

		//couponCode.setText(coupon.getCouponId() + "");
		
		String expDate = "00-00-0000";
		if(coupon.getExpiryDate().indexOf("T") != -1) {
			String str[] = coupon.getExpiryDate().split("T");
			String s[] = str[0].split("-");
			expDate = s[2] + "-" + s[1] + "-" + s[0]; 
		}
		//String expDate = coupon.getExpiryDate().indexOf("T") != -1 ? (coupon.getExpiryDate().split("T"))[0]  : coupon.getExpiryDate(); 
		expDateText.setText(TitleTextUtils.getBarcodeExpireDateMsg() + ":     " + expDate);
		
		//Log.(Constants.TAG, " Barcode url ---- > " + coupon.getBarcode());
		Drawable image = ImageOperations(getApplicationContext(),coupon.getBarcode(),"image.jpg");
		barImage.setImageDrawable(image);		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.BARCODE_SCREEN;
		super.onResume();
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
			alertBuilder.setTitle("Total Saving")
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					showUsedCouponsScreen();

				}
			})
			.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					try {
						int totalSaving = Integer.parseInt(EditText01.getText().toString());
						sendTotalSaving(totalSaving);
					}catch(NumberFormatException e) {
						
					}
					
				}
			});
			LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.total_saving_custom_dialog,null);
			EditText01 = (EditText) layout.findViewById(R.id.EditText01);
			alertBuilder.setView(layout);
			dialog = alertBuilder.create();
			break;
		}
		}
		return dialog;
	}


	private Handler messageHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			removeDialog(PRO_DIALOG);
			showUsedCouponsScreen();
		}
	};
	

	private void sendTotalSaving(final int totalSaving) {
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					GeneralResponse generalResponse = WSSender.sendTotalSaving(totalSaving);
					messageHandler.sendMessage(Message.obtain(messageHandler,0));
				} catch (IOException e) {
					messageHandler.sendMessage(Message.obtain(messageHandler,2));
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
		}
	}

	public Object fetch(String address) throws MalformedURLException,IOException {
		URL url = new URL(address);
		Object content = url.getContent();
		return content;
	}

	void showUsedCouponsScreen() {
		startActivity(new Intent(getApplicationContext(),UsedCouponScreen.class));
		finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == couponButton) {
			if(coupon.getOfferPrice().equalsIgnoreCase("0")) {
				showDialog(DEFAULT_DIALOG);
			}
			finish();
		}else if(v == trackButton) {
			if(coupon.getOfferPrice().equalsIgnoreCase("0")) {
				showDialog(DEFAULT_DIALOG);
			}else {
				showUsedCouponsScreen();
				finish();
			}
		}
	}
}
