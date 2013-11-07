package com.oonusave.coupon;

import com.oonusave.coupon.R;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


/**
 * 
 * @author Ramesh
 *
 */


public class NotFoundScreen extends BaseActivity implements OnClickListener{
	
	ImageButton backBtn;
	ImageButton notFoundImageBtn;
	String notFoundType = "";
	private boolean showBack = true;
	String type = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		notFoundType = getIntent().getStringExtra("NotFoundType");
		showBack = getIntent().getBooleanExtra("ShowBack",true);
		type = getIntent().getStringExtra("type");
		if(type == null || type.equalsIgnoreCase(""))
			type = "four";
		
		if("one".equalsIgnoreCase(type)){
			setContentView(R.layout.no_coupon_view1);
			//Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
			//toolbar.option1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.icon3,0,0);
			//toolbar.option1.setText(DataUtil.priLang.equalsIgnoreCase("English") ? "Map" : "Kort");
			
			
		}else if("two".equalsIgnoreCase(type)) {
			setContentView(R.layout.no_coupon_view2);
		}else if("three".equalsIgnoreCase(type)) {
			setContentView(R.layout.no_coupon_view3);
		}else {
			setContentView(R.layout.no_coupon_view4);
		}
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);
		
		backBtn = (ImageButton)findViewById(R.id.btnLeft);
		if(showBack) 
			backBtn.setVisibility(ImageButton.VISIBLE);
		else
			backBtn.setVisibility(ImageButton.INVISIBLE);
		//backBtn.setVisibility(ImageButton.VISIBLE);
		//backBtn.setBackgroundResource(R.drawable.back_button);
		backBtn.setBackgroundResource(ImageUtils.getBackIamge());
		
		notFoundImageBtn = (ImageButton) findViewById(R.id.notFoundImage);
		if(notFoundType.equalsIgnoreCase("CouponNotFound")) {
			notFoundImageBtn.setBackgroundResource(ImageUtils.getCouponNotFoundBgImage());
		}else if(notFoundType.equalsIgnoreCase("MyWalletNotFound")) {
			notFoundImageBtn.setBackgroundResource(ImageUtils.getMyWalletNotFoundBgImage());
		}else if(notFoundType.equalsIgnoreCase("StoresNotFound")) {
			notFoundImageBtn.setBackgroundResource(ImageUtils.getStoresNotFoundBgImage());
		}else if(notFoundType.equalsIgnoreCase("FavStoresNotFound")) {
			notFoundImageBtn.setBackgroundResource(ImageUtils.getFavStoresNotFoundBgImage());
		}else if(notFoundType.equalsIgnoreCase("UsedCouponNotFound")) {
			notFoundImageBtn.setBackgroundResource(ImageUtils.getUsedCouponNotFoundBgImage());
		}else if(notFoundType.equalsIgnoreCase("ExpiryCouponNotFound")) {
			notFoundImageBtn.setBackgroundResource(ImageUtils.getCouponNotFoundBgImage());
		}else if(notFoundType.equalsIgnoreCase("SubCouponNotFound")) {
			notFoundImageBtn.setBackgroundResource(ImageUtils.getCouponNotFoundBgImage());
		}else {
			notFoundImageBtn.setBackgroundResource(ImageUtils.getCouponNotFoundBgImage());
		}
		backBtn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		if(v == backBtn) {
			if(notFoundType.equalsIgnoreCase("CouponNotFound")) {
				//intent = new Intent(getApplicationContext(),CouponListScreen.class);
				finish();
				return;
			}else if(notFoundType.equalsIgnoreCase("MyWalletNotFound")) {
				//intent = new Intent(getApplicationContext(),MyWalletScreen.class);
				finish();
				return;
			}else if(notFoundType.equalsIgnoreCase("StoresNotFound")) {
				//intent = new Intent(getApplicationContext(),StoreListScreen.class);
				finish();
				return;
			}else if(notFoundType.equalsIgnoreCase("FavStoresNotFound")) {
				//intent = new Intent(getApplicationContext(),FavouriteStoresScreen.class);
				finish();
				return;
			}else if(notFoundType.equalsIgnoreCase("UsedCouponNotFound")) {
				finish();
				return;
			}else if(notFoundType.equalsIgnoreCase("ExpiryCouponNotFound")) {
				finish();
				return;
			}else if(notFoundType.equalsIgnoreCase("SubCouponNotFound")) {
				//intent = new Intent(getApplicationContext(),CategoryListScreen.class);
				//startActivity(intent);
				finish();
				return;
			}else {
				intent = new Intent(getApplicationContext(),CouponListScreen.class);
			}
			startActivity(intent);
			finish();
		}
	}
}
