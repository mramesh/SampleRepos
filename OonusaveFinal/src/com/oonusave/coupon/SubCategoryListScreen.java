package com.oonusave.coupon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Category;
import com.oonusave.coupon.model.SubCategory;
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
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * @author Ramesh
 *
 */


public class SubCategoryListScreen extends BaseListActivity implements OnClickListener{
	List<SubCategory> subCategoryList = null;
	SubCategoryAdaptor categoryAdapter = null;
	Category category = null;
	ImageButton cancelButton = null;
	private static final int PRO_DIALOG = 0;
	String errorMessage = "";
	TextView titleBarTextView;
	List<SubCategory> tempList = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		Intent intent = getIntent();
		subCategoryList = new ArrayList<SubCategory>();

		category = (Category)intent.getSerializableExtra("category");
		setContentView(R.layout.sub_category_screen);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		//((TextView)findViewById(R.id.titleText)).setText("SubCategory List");

		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);
		cancelButton = (ImageButton)findViewById(R.id.btnLeft);
		cancelButton.setVisibility(ImageButton.VISIBLE);
		//cancelButton.setBackgroundResource(R.drawable.back_button);
		cancelButton.setBackgroundResource(ImageUtils.getBackIamge());
		cancelButton.setOnClickListener(this);
		
		
		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getSubCategoryTitleText());
		
		categoryAdapter = new SubCategoryAdaptor( 
				this, 
				subCategoryList ); 
		setListAdapter( categoryAdapter );

		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				SubCategory subCategory = (SubCategory) subCategoryList.get(position);
				//Toast.makeText(getApplicationContext(), "SubCategory Id -- >" + subCategory.getSubCategoryId() + ":: SubName -- >" + subCategory.getSubCategoryName() ,
				//Toast.LENGTH_SHORT).show();
				getCoupons(subCategory);
			}

		});
		
		
		//Log.i(Constants.TAG, " tempList --- > " + tempList);
		tempList = (List) getLastNonConfigurationInstance();
		
		if(tempList == null) {
			fetchSubCategory();
		}else {
			//Log.i(Constants.TAG, " tempList Size --- > " + tempList.size());
			for(int i = 0 ; i < tempList.size() ; i++){
				subCategoryList.add(tempList.get(i));
			}
		}
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.SUBCATEGORIES_SCRREN;
		super.onResume();
	}
	
	
	@Override
	public Object onRetainNonConfigurationInstance() {
		// TODO Auto-generated method stub
		final List<SubCategory> data = collectMyLoadedData();
		return data;
	}
	
	private List<SubCategory> collectMyLoadedData() {
		tempList = new ArrayList<SubCategory>();
		for(int i = 0 ; i < subCategoryList.size() ; i++) {
			tempList.add(subCategoryList.get(i));
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
		}
		return dialog;
	}


	void getCoupons(SubCategory subCategory) {
		//Log.i(Constants.TAG, " ==== getCoupons ======= > SubCategoryID ---- > " + subCategory.getSubCategoryId());

		Intent intent = new Intent(this,SubCategoryCouponListScreen.class);
		intent.putExtra("action", "subCate");
		intent.putExtra("subCate", subCategory);
		intent.putExtra("fromCategory",false);
		startActivity(intent);
	}






	private Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
			//Log.i(Constants.TAG, "# CategoryListScreen # ----- handleMessage() ------ ");
			removeDialog(PRO_DIALOG);
			switch(msg.what){
			case 0:
				categoryAdapter.notifyDataSetChanged();
				//couponAdapter.notifyDataSetInvalidated();
				if(subCategoryList != null && subCategoryList.size() > 0) {
					for(int  i=0;i<subCategoryList.size();i++){
						categoryAdapter.add(subCategoryList.get(i));
					}
					//Log.i(Constants.TAG, "# CouponListScreen # ----- List size  ------> " + subCategoryList.size());
					categoryAdapter.notifyDataSetChanged();
				}
				break;
			case 1:
				Toast.makeText(getApplicationContext(), errorMessage,
						Toast.LENGTH_SHORT).show();
				break;
			}

		}
	};
	protected void fetchSubCategory() {
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					subCategoryList = WSSender.sendGetAllSubCategoryForCategory(category);
					messageHandler.sendMessage(Message.obtain(messageHandler,0));
				}catch(IOException ioe) {
					if(isOnline()) {
						errorMessage = 	AlertMsgUtil.getCommonErrorMsg();
					}else{
						errorMessage = AlertMsgUtil.getConnectFailureMessage();
					}
					messageHandler.sendMessage(Message.obtain(messageHandler,1));
				}catch (Exception e) {
					errorMessage = AlertMsgUtil.getCommonErrorMsg();
					messageHandler.sendMessage(Message.obtain(messageHandler,1));
				}
			}
		}.start();
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == cancelButton) {
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
