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


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
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
public class CategoryListScreen extends BaseListActivity implements OnClickListener{


	List<Category> categoryList = new ArrayList<Category>();
	CategoryAdaptor categoryAdapter = null;

	ImageButton homeButton = null;
	ImageButton cancelButton = null;
	private static final int PRO_DIALOG = 0;
	String errorMessage = "";
	TextView titleBarTextView;
	List<Category> tempList = null;
	ListView lv = null;
	//private static ProcessThread processThread = null; 
	int position = 0;
	int categoryId = 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Object retained = this.getLastNonConfigurationInstance();
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	

		Intent intent = getIntent();
		//SoapObject resultObject = wsSender.callWS(WSRequest.getCategoryRequest(), WSConstants.SOAP_ACTION_MOBILE_DISPLAY_ALL_CATEGORY);
		setContentView(R.layout.sub_category_screen);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		//((TextView)findViewById(R.id.titleText)).setText("Category List");





			homeButton = (ImageButton)findViewById(R.id.btnLeft);
			homeButton.setVisibility(ImageButton.INVISIBLE);
			homeButton.setBackgroundResource(R.drawable.home_btn);
			homeButton.setOnClickListener(this);

			cancelButton = (ImageButton)findViewById(R.id.btnRight);
			cancelButton.setVisibility(ImageButton.INVISIBLE);
			cancelButton.setBackgroundResource(ImageUtils.getBackIamge());
			cancelButton.setOnClickListener(this);


			titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
			titleBarTextView.setText(TitleTextUtils.getCategoryTitleText());

			//lv = (ListView) findViewById(R.id.list);
			lv = getListView();

			categoryAdapter = new CategoryAdaptor( 
					this,
					categoryList ); 
			//setListAdapter( categoryAdapter );
			lv.setAdapter(categoryAdapter);
			
			lv.setCacheColorHint(Color.TRANSPARENT);
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int pos, long id) {
			//		Log.i(Constants.TAG, " Position --- > " + pos);
									
					Category category = (Category) categoryList.get(position);
					
					if(category.getCount() > 0){
						
					Intent intent = new Intent(getApplicationContext(),SubCategoryListScreen.class);
					intent.putExtra("category", category);
					startActivity(intent);
					}else {
				
					Intent intent1 = new Intent(getApplicationContext(),SubCategoryCouponListScreen.class);
					intent1.putExtra("action", "subCate");
					SubCategory subCategory = new SubCategory();
					subCategory.setSubCategoryId(categoryId);
					intent1.putExtra("subCate", subCategory);
					intent1.putExtra("fromCategory",true);
					startActivity(intent1);
					}
					
					
					
//					position = pos;
//					checkSubCategoryCount(pos);
				}

			});
			//Log.i(Constants.TAG, " tempList --- > " + tempList);
//			if(processThread != null && processThread.isAlive()){
//				processThread.setHandler(new MessageHandler());
//			}else{
			
			tempList = (List) getLastNonConfigurationInstance();
			if(tempList == null) {
				fetchCategory();
			}else {
				//Log.i(Constants.TAG, " tempList Size --- > " + tempList.size());
				for(int i = 0 ; i < tempList.size() ; i++){
					categoryList.add(tempList.get(i));
				}
			}
			//}
	}
	
	private void checkSubCategoryCount(int pos){
		//Log.i(Constants.TAG, " *** checkSubCategoryCount *** " + pos);
		categoryId = categoryList.get(pos).getCategoryId();
		//Log.i(Constants.TAG, " Category Id --- > " + categoryId) ;
		checkCouponsCount();
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.CATEGORIES_SCRREN;
		super.onResume();
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			//Log.d(this.getClass().getName(), "back button pressed");
			//finish();
			//System.exit(0);
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public Object onRetainNonConfigurationInstance() {
		// TODO Auto-generated method stub

		//Log.i(Constants.TAG, "CategoryListScreen ----- onRetainNonConfigurationInstance() --- ");
		final List<Category> data = collectMyLoadedData();
		return data;
	}

	private List<Category> collectMyLoadedData() {
		tempList = new ArrayList<Category>();
		for(int i = 0 ; i < categoryList.size() ; i++) {
			tempList.add(categoryList.get(i));
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

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		//Log.i(Constants.TAG, " **** onConfigurationChanged *** ");
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		
		// Check the orientation of the screen
		if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			//Log.i(Constants.TAG, " ---- Landscape ---- ");
		}else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			//Log.i(Constants.TAG, " ---- Portrait ----- ");
		}
		
		
	}




	private Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
			//Log.i(Constants.TAG, "# CategoryListScreen # ----- handleMessage() ------ ");
			removeDialog(PRO_DIALOG);
			switch(msg.what){
			case 0:
				if(categoryList != null && categoryList.size() > 0) {		
					categoryAdapter.notifyDataSetChanged();
					//couponAdapter.notifyDataSetInvalidated();
					for(int  i=0;i<categoryList.size();i++){
						categoryAdapter.add(categoryList.get(i));
					}
					//Log.i(Constants.TAG, "# CouponListScreen # ----- List size  ------> " + categoryList.size());
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
	
	
	
	
	private Handler messageHandler1 = new Handler() {
		public void handleMessage(Message msg) {
			removeDialog(PRO_DIALOG);
			switch(msg.what){
			case 0:
				Category category = (Category) categoryList.get(position);
				Intent intent = new Intent(getApplicationContext(),SubCategoryListScreen.class);
				intent.putExtra("category", category);
				startActivity(intent);
				break;
			case 1:
				Intent intent1 = new Intent(getApplicationContext(),SubCategoryCouponListScreen.class);
				intent1.putExtra("action", "subCate");
				SubCategory subCategory = new SubCategory();
				subCategory.setSubCategoryId(categoryId);
				intent1.putExtra("subCate", subCategory);
				intent1.putExtra("fromCategory",true);
				startActivity(intent1);
				break;
			}
		}
	};
		
	
	protected void checkCouponsCount() {
		showDialog(PRO_DIALOG);
			new Thread() {
					public void run() {
						try {
							int subCount = WSSender.sendGetSubCategoryCount(categoryId);
							if(subCount == 0){
								messageHandler1.sendMessage(Message.obtain(messageHandler1,1));
							}else{
								messageHandler1.sendMessage(Message.obtain(messageHandler1,0));
							}
						} catch (IOException e) {
							e.printStackTrace();
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
	}

	
	
	
	protected void fetchCategory() {

		showDialog(PRO_DIALOG);
		/*processThread = new ProcessThread();
		processThread.start();	*/
				
				new Thread() {
					public void run() {
						try {
							categoryList = WSSender.sendSearchAllCategoryRequest(DataUtil.userDetails.getUserId());
							messageHandler.sendMessage(Message.obtain(messageHandler,0));
						} catch (IOException e) {
							if(isOnline()) {
								errorMessage = AlertMsgUtil.getCommonErrorMsg();
							}else {
								errorMessage = AlertMsgUtil.getConnectFailureMessage();
							}
							messageHandler.sendMessage(Message.obtain(messageHandler,1));
						} catch(Exception e) {
							e.printStackTrace();
							errorMessage = AlertMsgUtil.getCommonErrorMsg();
							messageHandler.sendMessage(Message.obtain(messageHandler,1));
						}
					}
				}.start();
	}


//	class ProcessThread extends Thread{
//		Handler messageHandler = new MessageHandler();
//		public void setHandler(Handler h) { messageHandler = h; } // for handler swapping after config change
//		//public MyDataObject getDataObject() { return dataObject; } // return data object (completed) to caller
//		public Handler getHandler(){
//			return messageHandler;
//		}
//
//		public void run() {
//			try {
//				categoryList = WSSender.sendSearchAllCategoryRequest();
//				messageHandler.sendMessage(Message.obtain(messageHandler,0));
//			} catch (IOException e) {
//				errorMessage ="Error while getting category list. Try again!";
//				messageHandler.sendMessage(Message.obtain(messageHandler,1));
//			} catch(Exception e) {
//				e.printStackTrace();
//				errorMessage ="Error!Please Try again!";
//				messageHandler.sendMessage(Message.obtain(messageHandler,1));
//			}
//		}
//
//
//	}




	void showDialog(String alertMsg) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Error");
		alertDialog.setMessage(alertMsg);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				return;
			} 
		}); 
		alertDialog.show();
	}

	@Override
	public void onClick(View v) {
		if(v == homeButton) {

		}else if(v == cancelButton) {

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
