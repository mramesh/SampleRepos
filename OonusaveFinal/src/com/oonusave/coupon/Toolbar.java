package com.oonusave.coupon;

import com.oonusave.coupon.R;
import com.oonusave.coupon.util.DataUtil;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @author Ramesh
 *
 */

public class Toolbar extends LinearLayout {

	TextView option1 = null;
	TextView option2 = null;
	TextView option3 = null;
	TextView option4 = null;
	TextView option5 = null;
	
	
	public Toolbar(final Context context) {
		super(context);
	}

	public Toolbar(final Context con, AttributeSet attrs) {
		super(con,attrs);		
		setOrientation(HORIZONTAL);
		setBackgroundColor(getResources().
				getColor(android.R.color.transparent));

		LayoutInflater inflater = (LayoutInflater) 
		con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.navigation, this);

		TypedArray a = con.obtainStyledAttributes(attrs, 
				R.styleable.Toolbar);
		String option = a.getString(R.styleable.Toolbar_textViewId);

		String resourceId = "com.oonusave.coupon:id/"+option;
		int optionId = getResources().getIdentifier(resourceId,null,null);        		
		final TextView currentOption = (TextView) findViewById(optionId);
		currentOption.setBackgroundColor(getResources().
				getColor(android.R.color.white));
		currentOption.setTextColor(getResources().
				getColor(android.R.color.black));
		currentOption.requestFocus(optionId);
		currentOption.setFocusable(false);
		currentOption.setClickable(false);

		option1 = (TextView) findViewById(R.id.option1);
		//option1.setText(DataUtil.priLang.equalsIgnoreCase("English") ? "Home" : "Hjem");
		option1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				if(DataUtil.mapScreen) {
					option1.setText("Map");
					option1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.icon3,0,0);
					showHomeScree(con);
				}else {
					option1.setText("List");
					option1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic1,0,0);
					showMapScreen(con);
				}
				
//				if(option1 != currentOption) {
//					showHomeScree(con);
//				}else{
				
//					if(DataUtil.CURRENT_SCREEN == PageManager.COUPON_LIST_SCREEN){
//						option1.setText(DataUtil.priLang.equalsIgnoreCase("English") ? "List" : "liste");
//						option1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic1,0,0);
//						showMapScreen(con);
//					}else {
//						option1.setText(DataUtil.priLang.equalsIgnoreCase("English") ? "Map" : "Kort");
//						option1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.icon3,0,0);
//						showHomeScree(con);
//					}
					
					
//					if(DataUtil.CURRENT_SCREEN != PageManager.COUPON_LIST_SCREEN){
//						showHomeScree(con);
//					}
				//}
			}
		});

		option2 = (TextView) findViewById(R.id.option2);
		option2.setText("Category");
		option2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(option2 != currentOption) { 
					showBrowseScreen(con);
				}else {
					if(DataUtil.CURRENT_SCREEN != PageManager.CATEGORIES_SCRREN) {
						showBrowseScreen(con);
					}
				}
			}
		});
		
		option3 = (TextView) findViewById(R.id.option3);
		option3.setText("My wallet");
		option3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(option3 != currentOption) { 
					showMyWalletScreen(con);
				}else {
					if(DataUtil.CURRENT_SCREEN != PageManager.MY_WALLET_SCREEN) {
						showMyWalletScreen(con);
					}
				}
			}
		});
		
		
		option4 = (TextView) findViewById(R.id.option4);
		option4.setText("Favorite");
		option4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(option4 != currentOption) { 
					showFavStoreScreen(con);
				}
			}
		});
	

		option5 = (TextView) findViewById(R.id.option5);
		option5.setText("More");
		option5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(option4 != currentOption) { 
					showMoreScreen(con);
				}else {
					if(DataUtil.CURRENT_SCREEN != PageManager.MORE_SCREEN && DataUtil.CURRENT_SCREEN != PageManager.SETTINGS_SCREEN) {
						showMoreScreen(con);
					}
				}
			}
		});
	}
	
	
	void showFavStoreScreen(Context con) {
		Intent i = new Intent(con,FavouriteStoresScreen.class);
		//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		con.startActivity( i );
	}

	void showHomeScree(Context con) {
		//Intent i = new Intent(con,CouponListScreen.class);
		Intent i = new Intent(con,CouponsScreen.class);
		//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		con.startActivity( i );
	}


	void showBrowseScreen(Context con) {
		Intent i = new Intent(con,CategoryListScreen.class);
		//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		con.startActivity( i );
	}

	void showMyWalletScreen(Context con) {
		//con.startActivity( new Intent(con,MyWalletScreen.class));
		
				
		
		Intent i = new Intent(con,MyWalletScreen.class);
		//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		con.startActivity( i );
	}
	
	
	
	
	void showMapScreen(Context con) {
		Intent i = new Intent(con,MyMapStore.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		con.startActivity( i );
	}
	

	void showMoreScreen(Context con) {
		
		Intent i = null;
//		if(DataUtil.CURRENT_SCREEN > 8){
//			if( DataUtil.CURRENT_SCREEN == PageManager.EDIT_PROFILE_SCREEN) {
//				
//			}else if(DataUtil.CURRENT_SCREEN == PageManager.SETTINGS_SCREEN) {
//				
//			}else if(DataUtil.CURRENT_SCREEN)
//		}else{
//			
//		}
		i = new Intent(con,MoreScreen1.class);
		i.putExtra("fromCouponScreen", false);
		//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		con.startActivity( i );

	}
	
	
	

}
