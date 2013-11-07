package com.oonusave.coupon;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.oonusave.coupon.R;

import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;

public class BaseListActivity extends ListActivity{
	
	//Toolbar toolbar = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//		setContentView(R.layout.coupon_list_view);
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
//		toolbar = (Toolbar) findViewById(R.id.toolBar);
//		Log.i(Constants.TAG, " toolbar --- > " + toolbar);
//		if(toolbar != null) {
//		if(DataUtil.mapScreen) {
//			toolbar.option1.setText("List");
//			toolbar.option1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic3,0,0);
//		}else {
//			toolbar.option1.setText("Map");
//			toolbar.option1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.icon3,0,0);
//		}
//		}
		super.onCreate(savedInstanceState);
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
	    MenuItem item1 = menu.getItem(0);
	    item1.setTitle("Category");
	    
	    MenuItem item2 = menu.getItem(1);
	    item2.setTitle("Favorite" );
	    	    
	    MenuItem item3 = menu.getItem(2);
	    item3.setTitle(!DataUtil.mapScreen ? ( "Map" ) :  "List" );
	    item3.setIcon(DataUtil.mapScreen == false ? R.drawable.icon3 : R.drawable.ic1);
	    
	    MenuItem item4 = menu.getItem(3);
	    item4.setTitle( "My wallet");
	    	    
	    MenuItem item5 = menu.getItem(4);
	    item5.setTitle("More");
	    return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.category:
	    	showCategoryScreen();
	        return true;
	    case R.id.fav:
	    	showFavStoreScreen();
	        return true;
	    case R.id.map:
	    	showMapScreen();
	        return true;
	    case R.id.mywallet:
	    	showMyWalletScreen();
	        return true;
	    case R.id.more:
	    	showMoreScreen();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	private void showCategoryScreen() {
		//Toast.makeText(getApplicationContext(), "showCategoryScreen", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(getApplicationContext(),CategoryListScreen.class);
		//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity( i );
	}
	
	private void showFavStoreScreen() {
		//Toast.makeText(getApplicationContext(), "showFavStoreScreen", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(getApplicationContext(),FavouriteStoresScreen.class);
		//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity( i );
	}
	
	
	private void showMapScreen() {
		//Toast.makeText(getApplicationContext(), "showMapScreen", Toast.LENGTH_SHORT).show();
		
		if(DataUtil.mapScreen) {
			Intent i = new Intent(getApplicationContext(),CouponsScreen.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity( i );
		}else {
			
			Intent i = new Intent(getApplicationContext(),MyMapStore.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity( i );
			
		}
		
		
	}
	
	private void showMyWalletScreen() {
		//Toast.makeText(getApplicationContext(), "showMyWalletScreen", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(getApplicationContext(),MyWalletScreen.class);
		//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity( i );
	}
	
	
	private void showMoreScreen() {
		//Toast.makeText(getApplicationContext(), "showMoreScreen", Toast.LENGTH_SHORT).show();
		
		Intent i = null;
		i = new Intent(getApplicationContext(),MoreScreen1.class);
		i.putExtra("fromCouponScreen", false);
		//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity( i );
	}
	
	
	
	
	@Override
	protected void onResume() {
		
		super.onResume();
		//toolbar = (Toolbar) findViewById(R.id.toolBar);
//		Log.i(Constants.TAG, " On Resume toolbar --- > " + toolbar);
//		if(toolbar != null) {
		if(DataUtil.mapScreen) {
			//toolbar.option1.setText("List");
			//toolbar.option1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic3,0,0);
		}else {
			//toolbar.option1.setText("Map");
			//toolbar.option1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.icon3,0,0);
		}
		//}
	}
}
