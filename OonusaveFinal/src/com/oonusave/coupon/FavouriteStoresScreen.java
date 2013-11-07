package com.oonusave.coupon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Store;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.ws.WSSender;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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

public class FavouriteStoresScreen extends BaseListActivity implements OnClickListener{

	ImageView backButton;
	Button submitButton;
	List<Store> storeList = new ArrayList<Store>();
	StoreAdaptor storesAdapter = null;
	String errorMessage = "";
	TextView titleBarTextView;
	EditText searchText;
	ListView lv ;
	private boolean isSearch= false;
	String searchStr = "";
	List<Store> tempList = null;
	private static final int PRO_DIALOG = 100;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.favourite_store_list_screen);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);
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
		searchText = (EditText) findViewById(R.id.searchText);
		searchText.setHint(TitleTextUtils.getSearchMsgStr());
		
		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getMyFavStoreTitleText());

		
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
				Store store = (Store) storeList.get(position);
				//Toast.makeText(getApplicationContext(), "SubCategory Id -- >" + subCategory.getSubCategoryId() + ":: SubName -- >" + subCategory.getSubCategoryName() ,
				//Toast.LENGTH_SHORT).show();
				getCoupons(store);
			}

		});
		
		//Log.i(Constants.TAG, " tempList --- > " + tempList);
		tempList = (List) getLastNonConfigurationInstance();
		if(tempList == null) {
			fetchStores(searchStr);
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
		DataUtil.CURRENT_SCREEN = PageManager.MY_FAV_STORE_SCREEN;
		super.onResume();
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
		}
		return dialog;
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



	void getCoupons(Store store) {
		//Log.i(Constants.TAG, " ==== getCoupons ======= > StoreId ---- > " + store.getStoreId());
		Intent intent = new Intent(this,StoreCouponListScreen.class);
		intent.putExtra("isFavStoreCoupons", true);
		intent.putExtra("store", store);
		startActivity(intent);
		finish();
	}



	//ProgressDialog progressDialog = null;

	private Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
			//Log.i(Constants.TAG, "# StoreListScreen # ----- handleMessage() ------ ");
			switch(msg.what) {
			case 0:
				if(storeList != null && storeList.size() > 0) {
					storesAdapter.clear();
					storesAdapter.notifyDataSetChanged();
					for(int  i=0;i<storeList.size();i++){
						storesAdapter.add(storeList.get(i));
					}
					storesAdapter.notifyDataSetChanged();
					removeDialog(PRO_DIALOG);
				}else {
					removeDialog(PRO_DIALOG);
					Intent intent = new Intent(getApplicationContext(),NotFoundScreen.class);
					intent.putExtra("NotFoundType", "FavStoresNotFound");
					startActivity(intent);
					//finish();
					//Toast.makeText(getApplicationContext(), "No favourite stores!", Toast.LENGTH_SHORT).show();
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
	protected void fetchStores(final String keyword) {
		//progressDialog = ProgressDialog.show(this, "", AlertMsgUtil.getLoadingMessageText());
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					if(keyword == null || "".equalsIgnoreCase(keyword.trim())) {
						//Log.i(Constants.TAG, " ==== SelectStores ======== ");
						storeList = WSSender.sendSelectFavouriteStores((int)DataUtil.userDetails.getUserId());
					}else {
						
						//Log.i(Constants.TAG, " ==== SearchStores ========   Key " + keyword);
						storeList = WSSender.sendSearchFavouriteStores(keyword,(int)DataUtil.userDetails.getUserId());
					}
					messageHandler.sendEmptyMessage(0);
				}catch (IOException e) {
					if(isOnline()) {
						errorMessage = AlertMsgUtil.getCommonErrorMsg();
					}else {
						errorMessage = AlertMsgUtil.getConnectFailureMessage();
					}
					messageHandler.sendEmptyMessage(2);
				}
				catch (Exception e) {
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
			if(isSearch){
				isSearch = false;
				fetchStores("");
			}else {
				finish();
			}
		}else if(v == submitButton) {
			InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			mgr.hideSoftInputFromWindow(submitButton.getWindowToken(),0);
			searchStr = searchText.getText() != null ? searchText.getText().toString() : "";
			if(!searchStr.trim().equalsIgnoreCase("") || searchStr.trim().length() > 0) {
				//Log.i(Constants.TAG, " Searching favorite stores ------- >" + searchStr);
				isSearch = true;
				storesAdapter.clear();
				fetchStores(searchStr);
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
