package com.oonusave.coupon;

import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Coupon;
import com.oonusave.coupon.util.Utils;
import com.oonusave.coupon.ws.WSSender;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author Ramesh
 *
 */

public class CouponAdapter extends BaseAdapter {

	private Context context;
	private List<Coupon> couponList;
	private boolean isMyWallet;
	private DrawableManager drawableManager = new DrawableManager();
	private LayoutInflater inflater;

	CouponAdapter(Context context,List<Coupon> couponList,boolean isMyWallet) {
		super();
		//Log.i(Constants.TAG,"# CouponAdapter # ------ constructor() ----- ");
		this.context = context;
		this.couponList = couponList;
		this.isMyWallet = isMyWallet;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void add(Coupon coupon) {
		couponList.add(coupon);
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return couponList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return couponList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void clear() {
		couponList.clear();
	}
	
	
	

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG,"# CouponAdapter # ------ getView() ----- ");
		Coupon coupon = couponList.get(position);
		
//		if(v == null) {
//			//return new CouponAdaptorView(this.context, coupon,drawableManager,isMyWallet );
//			v = LayoutInflater.from(context).inflate(R.layout.coupon_item, null);
//		}
			v = inflater.inflate(R.layout.coupon_item1, null);
		
			ImageView couponImage = (ImageView) v.findViewById(R.id.couponImage);
			drawableManager.fetchDrawableOnThread(coupon.getImagePath(), couponImage);
			
			if(coupon.getFeatured() == 0) {
				((ImageView) v.findViewById(R.id.couponBgImage)).setBackgroundResource(R.drawable.image_bg2);
			}
			
			
			//couponImage.setBackgroundDrawable(drawableManager.fetchDrawable(coupon.getImagePath()));
			
			TextView couponName = (TextView) v.findViewById(R.id.couponName);
			TextView storeName = (TextView)v.findViewById(R.id.storeName);
			TextView kmsText = (TextView) v.findViewById(R.id.kmsText);
			TextView daysText = (TextView) v.findViewById(R.id.daysText);
			
			//RelativeLayout kmsLayout = (RelativeLayout) v.findViewById(R.id.kmsLayout);
			LinearLayout kmsLayout = (LinearLayout) v.findViewById(R.id.kmsLayout);
			
			if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				//Log.i(Constants.TAG, " Configuration.ORIENTATION_LANDSCAPE ");
				couponName.setText(coupon.getCouponName().length() > 30 ? coupon.getCouponName().substring(0,29) + "..." :coupon.getCouponName());
				storeName.setText(coupon.getStoreName().length() > 30 ? coupon.getStoreName().substring(0,29) + "..." : coupon.getStoreName());				
			}else {
				couponName.setText(coupon.getCouponName().length() > 16 ? coupon.getCouponName().substring(0,15) + "..." :coupon.getCouponName());
				storeName.setText(coupon.getStoreName().length() > 16 ? coupon.getStoreName().substring(0,15) + "..." : coupon.getStoreName());
			}
			if(isMyWallet) {
				if(kmsLayout != null)
					kmsLayout.setVisibility(LinearLayout.INVISIBLE);
			}else{
				kmsText.setText("  " + coupon.getDistance());
				daysText.setText("  " + coupon.getNoDaysToExpiry() + " Days");
				
			}
			
			
			
			return v;
		
		
	}
}

class CouponAdaptorView extends LinearLayout {

	Context appContext;
	TextView addToWalletTxt;
	TextView showLocationTxt;
	TextView useCouponTxt; 
	Coupon coupon;
	boolean isMyWallet;
	DrawableManager drawableManager = null;
	public CouponAdaptorView(Context context,Coupon coupon,DrawableManager drawableManager,boolean isMyWallet) {
		super(context);
		this.appContext = context;
		this.coupon = coupon;
		this.isMyWallet = isMyWallet;
		this.drawableManager = drawableManager;
		//Log.i(Constants.TAG,"# CouponAdaptorView # ------ constructor() ----- ");
		// TODO Auto-generated constructor stub
		this.setOrientation(HORIZONTAL);
		this.setBackgroundResource(R.drawable.coupon_listing_bg1);

		//android.view.ViewGroup.LayoutParams layoutParms = new android.view.ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		//setLayoutParams(layoutParms);

		LinearLayout.LayoutParams imageParams = 
			new LinearLayout.LayoutParams(50, 50);

		ImageView iconImage = new ImageView(context);
		iconImage.setLayoutParams(imageParams);
		iconImage.setBackgroundColor(Color.TRANSPARENT);
		//iconImage.setImageResource(R.drawable.couponicon);
		//Drawable image = ImageOperations(context,coupon.getImagePath(),"image.jpg");
		//iconImage.setImageDrawable(image);
		drawableManager.fetchDrawableOnThread(coupon.getImagePath(), iconImage);
		iconImage.setPadding(5, 5, 5, 5);
		addView(iconImage,imageParams);

		//LinearLayout detailLayout = new LinearLayout(context);
		//detailLayout.setOrientation(VERTICAL);


		LinearLayout.LayoutParams desParams = 
			new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		//setBackgroundColor(Color.GREEN);
		int marginValue = (int)Utils.convertDipToPixel(1, context);
		desParams.setMargins(marginValue, marginValue, marginValue, marginValue);


		TextView name = new TextView( context );
		name.setText( coupon.getCouponName());
		name.setTextSize((int)Utils.convertDipToPixel(16, context));
		name.setPadding((int)Utils.convertDipToPixel(20, context), (int)Utils.convertDipToPixel(5, context), (int)Utils.convertDipToPixel(20, context), 0);
		name.setTextColor(Color.WHITE);
		addView(name,desParams);

		//detailLayout.addView(shortDesc,desParams);



		/*
		LinearLayout optionLayout = new LinearLayout(context);
		optionLayout.setOrientation(HORIZONTAL);

		if(!isMyWallet) {
			addToWalletTxt = new TextView(context);
			addToWalletTxt.setText(" Add To Wallet ");
			addToWalletTxt.setTextColor(Color.MAGENTA);
			optionLayout.addView(addToWalletTxt);
		}

		showLocationTxt = new TextView(context);
		showLocationTxt.setText(" Loc ");
		showLocationTxt.setTextColor(Color.YELLOW);
		optionLayout.addView(showLocationTxt);

		useCouponTxt = new TextView(context);
		useCouponTxt.setTextColor(Color.GREEN);

		useCouponTxt.setText(" Use Coupon ");
		optionLayout.addView(useCouponTxt);

		detailLayout.addView(optionLayout);

		addToWalletTxt.setOnClickListener(new ClickListener());
		showLocationTxt.setOnClickListener(new ClickListener());
		useCouponTxt.setOnClickListener(new ClickListener());

		/*final ImageButton addToWalleteBtn = new ImageButton(context);
		ImageButton showLocationBtn = new ImageButton(context);
		ImageButton useCouponBtn = new ImageButton(context);

		addToWalleteBtn.setOnClickListener(new ClickListener());
		showLocationBtn.setOnClickListener(new ClickListener());
		useCouponBtn.setOnClickListener(new ClickListener());
		addToWalleteBtn.setImageResource(R.drawable.android);
		addToWalleteBtn.setBackgroundColor(Color.TRANSPARENT);
		addToWalleteBtn.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				//Log.i(Constants.TAG, "# CouponAdaptor # -------- addToWalleteBtn - onFocusChange()  ");
				//Log.i(Constants.TAG, "# CouponAdaptor # -------- hasFocus ----------- > " + hasFocus);
				// 	TODO Auto-generated method stub
				if (hasFocus==true)
				{
					addToWalleteBtn.setImageResource(R.drawable.androidonfocus);
					//Log.i(Constants.TAG, "# CouponAdaptor # -------- NON-FOCUS ------ ");
				}else{
					addToWalleteBtn.setImageResource(R.drawable.android);
					//Log.i(Constants.TAG, "# CouponAdaptor # -------- FOUCS ----------" );
				}
			}
		});


		addToWalleteBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//Log.i(Constants.TAG, "# CouponAdaptor # -------- addToWalleteBtn - onClick()  ");
				// TODO Auto-generated method stub
				addToWalleteBtn.setImageResource(R.drawable.androidonclick);
			}
		});     


		optionLayout.addView(addToWalleteBtn);
		 */





	}




}
