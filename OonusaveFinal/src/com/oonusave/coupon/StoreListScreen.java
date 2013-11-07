package com.oonusave.coupon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Category;
import com.oonusave.coupon.model.Store;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.ws.WSSender;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


/**
 * 
 * @author Ramesh
 *
 */

public class StoreListScreen extends BaseListActivity implements OnClickListener{
	
	ImageView backButton;
	Button submitButton;
	List<Store> storeList = new ArrayList<Store>();
	StoreAdaptor storesAdapter = null;
	String errorMessage = "";
	ListView lv ;
	EditText searchText;
	String searchString = "";
	TextView titleBarTextView;
	private boolean isSearch = false;
	List<Store> tempList = null;
	private static final int PRO_DIALOG = 0,TEST_DIALOG = 1;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.store_list_screen);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);
		storeList = new ArrayList<Store>();
		
		backButton = (ImageButton)findViewById(R.id.btnLeft);
		backButton.setVisibility(ImageButton.VISIBLE);
		//backButton.setBackgroundResource(R.drawable.back_button);
		backButton.setBackgroundResource(ImageUtils.getBackIamge());
		backButton.setClickable(true);
		backButton.setOnClickListener(this);

		submitButton = (Button) findViewById(R.id.submit_button);
		submitButton.setBackgroundResource(ImageUtils.getSubmitImage());
		submitButton.setClickable(true);
		submitButton.setOnClickListener(this);
		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getStoreFinderTitleText());
		searchText = (EditText) findViewById(R.id.searchText);
		searchText.setHint(TitleTextUtils.getSearchMsgStr());		
		
		storesAdapter = new StoreAdaptor( 
				this,
				storeList,false); 
		setListAdapter( storesAdapter );
		lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				
				//Log.i(Constants.TAG, " Store Item Clicked ");
				//Log.i(Constants.TAG, "Store Position --- > " + position);
				

				
				Store store = (Store) storeList.get(position);
				//Log.i(Constants.TAG, "Store Id --- > " + store.getStoreId() + " :: Store Name --- > " + store.getStoreName());
				//Toast.makeText(getApplicationContext(), "SubCategory Id -- >" + subCategory.getSubCategoryId() + ":: SubName -- >" + subCategory.getSubCategoryName() ,
				//Toast.LENGTH_SHORT).show();
				getCoupons(store);
			}

		});
		
		
		
		//Log.i(Constants.TAG, " tempList --- > " + tempList);
		tempList = (List) getLastNonConfigurationInstance();
		if(tempList == null) {
			if(!isSearch){
				fetchStores(searchString);
			}
		}else {
			//Log.i(Constants.TAG, " tempList Size --- > " + tempList.size());
			for(int i = 0 ; i < tempList.size() ; i++){
				storeList.add(tempList.get(i));
			}
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.STORE_LIST_SCREEN;
		super.onResume();
	}
	
	
	
	@Override
	public Object onRetainNonConfigurationInstance() {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "CategoryListScreen ----- onRetainNonConfigurationInstance() --- ");
		final List<Store> data = collectMyLoadedData();
		return data;
	}
	
	private List<Store> collectMyLoadedData() {
		tempList = new ArrayList<Store>();
		for(int i = 0 ; i < storeList.size() ; i++) {
			tempList.add(storeList.get(i));
		}
		return tempList;
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
	

	void getCoupons(Store store) {
		
		if(DataUtil.userName.equals("demouser@mocansa.com")) {
			showDialog(TEST_DIALOG);
		}else {
		
		Intent intent = new Intent(this,StoreCouponListScreen.class);
		intent.putExtra("store", store);
		intent.putExtra("isFavStoreCoupons", false);
		startActivity(intent);
		}
	}
	
	
	//ProgressDialog progressDialog = null;
	private Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
			
			switch(msg.what) {
			case 0:
				if(storeList != null && storeList.size() > 0 ) {
					
					storesAdapter.notifyDataSetChanged();
					
//					storesAdapter = new StoreAdaptor( 
//							getApplicationContext(),
//							storeList,false);
					storesAdapter.clear();
					//lv.setAdapter(storesAdapter);
					
					//Log.i(Constants.TAG, " LIST SIZE ----------- > " + storeList.size());
					for(int  i=0;i<storeList.size();i++){
						storesAdapter.add((Store) storeList.get(i));
					}
					removeDialog(PRO_DIALOG);
					storesAdapter.notifyDataSetChanged();
				}else {
					removeDialog(PRO_DIALOG);
					Intent intent = new Intent(getApplicationContext(),NotFoundScreen.class);
					intent.putExtra("NotFoundType", "StoresNotFound");
					startActivity(intent);
					//finish();
					
				}
				break;
			case 1:
				removeDialog(PRO_DIALOG);
				Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
				break;
			case 2: 
				removeDialog(PRO_DIALOG);
				Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
	
	protected void fetchStores(final String searchString) {
		//progressDialog = ProgressDialog.show(this, "", AlertMsgUtil.getLoadingMessageText());
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					if(searchString == null || "".equalsIgnoreCase(searchString.trim())) {
						storeList = WSSender.sendSelectStoresRequest();
						//Log.i(Constants.TAG, "Select StoreList Size ----- > " + storeList.size());
					}else {
						isSearch = true;
						storeList = WSSender.sendSerachStoresRequest(searchString);
						//Log.i(Constants.TAG, "Search Select StoreList Size ----- > " + storeList.size());
					}
					messageHandler.sendEmptyMessage(0);
				}catch (IOException e) {
					e.printStackTrace();
					if(isOnline()) {
						errorMessage = AlertMsgUtil.getCommonErrorMsg();
					}else{
						errorMessage = AlertMsgUtil.getConnectFailureMessage();
					}
					messageHandler.sendEmptyMessage(2);
					
				}
				catch (Exception e) {
					e.printStackTrace();
					errorMessage = AlertMsgUtil.getCommonErrorMsg();
					messageHandler.sendEmptyMessage(1);
				}
			}
		}.start();
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == backButton) {
			//Log.i(Constants.TAG, "StoreListScreen --- isSearch ---  > " + isSearch);
			if(isSearch){
				isSearch = false;
				fetchStores("");
				
			}else {
				finish();
			}
		}else if(v == submitButton) {
			InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			mgr.hideSoftInputFromWindow(submitButton.getWindowToken(),0);
			
			//lv.setAdapter(null);
			
			storesAdapter.notifyDataSetInvalidated();
			storesAdapter.notifyDataSetChanged();
			searchString = searchText.getText() != null ? searchText.getText().toString().trim() : "";
			if(searchString.length() > 0){
				storesAdapter.clear();
				fetchStores(searchString);
			}
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
