package com.oonusave.coupon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Coupon;
import com.oonusave.coupon.model.SubCategory;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.ws.WSSender;
/**
 * 
 * @author Ramesh
 */
public class SubCategoryCouponListScreen extends BaseListActivity implements OnClickListener{

	List<Coupon> couList = new ArrayList<Coupon>();
	String action = "";
	CouponAdapter couponAdapter = null;
	ImageButton homeButton = null;
	ListView lv;
	TextView titleBarTextView;
	private final int PRO_DIALOG = 100;
	List<Coupon> tempList = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.sub_category_coupon_list_screen);
		//setContentView(R.layout.category_screen);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, COUNTRIES));

		//((TextView)findViewById(R.id.titleText)).setText("Coupons");
		couList = new ArrayList<Coupon>();
		homeButton = (ImageButton)findViewById(R.id.btnLeft);
		if(getIntent().getBooleanExtra("fromCategory", false)){
			homeButton.setVisibility(ImageButton.INVISIBLE);	
		}else{
		homeButton.setVisibility(ImageButton.VISIBLE);
		//homeButton.setBackgroundResource(R.drawable.subcategories_button);
		homeButton.setBackgroundResource(ImageUtils.getSubCategoriesBtnImage());
		homeButton.setOnClickListener(this);
		}
		

		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);
		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getCouponListTitleText());
		
		couponAdapter = new CouponAdapter( 
				getApplicationContext(),
				couList,false ); 
		setListAdapter( couponAdapter );
		lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				//Toast.makeText(getApplicationContext(), "List Clicked ",
				//  Toast.LENGTH_SHORT).show();
				showCouponDetailsScreen(position);
			}
		});
		
		//Log.i(Constants.TAG, " tempList --- > " + tempList);
		tempList = (List) getLastNonConfigurationInstance();
		if(tempList == null) {
			fetchCoupons();
		}else {
			//Log.i(Constants.TAG, " tempList Size --- > " + tempList.size());
			for(int i = 0 ; i < tempList.size() ; i++){
				couList.add(tempList.get(i));
			}
		}
		
		/*
	  Button button = (Button) findViewById(R.id.button);
	  //lv.addHeaderView(button);

	  lv.setTextFilterEnabled(true);

	  lv.setOnItemClickListener(new OnItemClickListener() {
		@Override
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {
	      // When clicked, show a toast with the TextView text
	      Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
	          Toast.LENGTH_SHORT).show();
	    }

	  });
		 */
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.SUBCATEGORY_COUPON_LIST_SCREEN;
		super.onResume();
	}
	
	
	@Override
	public Object onRetainNonConfigurationInstance() {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG, "CategoryListScreen ----- onRetainNonConfigurationInstance() --- ");
		final List<Coupon> data = collectMyLoadedData();
		return data;
	}

	private List<Coupon> collectMyLoadedData() {
		tempList = new ArrayList<Coupon>();
		for(int i = 0 ; i < couList.size() ; i++) {
			tempList.add(couList.get(i));
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
	


	//ProgressDialog progressDialog = null;

	private Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
			//Log.i(Constants.TAG, "# CouponListScreen # ----- handleMessage() ------ ");
			//progressDialog.dismiss();
			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0:
				if(couList != null) {
					if(couList.size() > 0 ) {
					couponAdapter.notifyDataSetChanged();
					//couponAdapter.notifyDataSetInvalidated();
					for(int  i=0;i<couList.size();i++){
						couponAdapter.add(couList.get(i));
					}
					//Log.i(Constants.TAG, "# CouponListScreen # ----- List size  ------> " + couList.size());
					couponAdapter.notifyDataSetChanged();
					}else {
						removeDialog(PRO_DIALOG);
						Intent intent = new Intent(getApplicationContext(),NotFoundScreen.class);
						intent.putExtra("type", "two");
						intent.putExtra("NotFoundType", "SubCouponNotFound");
						startActivity(intent);
						finish();
						//Toast.makeText(getApplicationContext(), AlertMsgUtil.getNoCouponsFoundAlertMsg(), Toast.LENGTH_SHORT).show();
					}
				}
				break;
			case 2: 
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getConnectFailureMessage(), Toast.LENGTH_SHORT);
				break;
			}
		}
	};
	protected void fetchCoupons() {
		//progressDialog = ProgressDialog.show(this, "", AlertMsgUtil.getLoadingMessageText());
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					
					SubCategory subCategory = (SubCategory)getIntent().getSerializableExtra("subCate");
					if(getIntent().getBooleanExtra("fromCategory", false)) {
						couList = WSSender.sendSearchCouponBasedOnCategory(subCategory);
					}else {
						couList = WSSender.sendSearchCouponBasedOnSubCategory(subCategory);
					}
					
					messageHandler.sendEmptyMessage(0);
				} catch (IOException e) {
					messageHandler.sendEmptyMessage(2);
				}
			}
		}.start();
	}



	void showCouponDetailsScreen(int pos) {
		Coupon coupon = couList.get(pos);
		Intent intent = new Intent(this,CouponDetailsScreen.class);
		intent.putExtra("couponObj", coupon);
		startActivity(intent);
	}


	void showDialog(String alertMsg) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Info");
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
		// TODO Auto-generated method stub
		if(v == homeButton) {
			finish();
		}
	}
}
