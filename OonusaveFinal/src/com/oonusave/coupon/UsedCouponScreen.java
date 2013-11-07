package com.oonusave.coupon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Coupon;
import com.oonusave.coupon.model.Login;
import com.oonusave.coupon.model.UserDetails;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.ws.WSSender;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
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
public class UsedCouponScreen extends BaseListActivity implements OnClickListener{

	ImageButton backButton = null;
	CouponAdapter couponAdapter = null;
	List<Coupon> couList = new ArrayList<Coupon>();
	private static final int PRO_DIALOG = 0;
	TextView totalSavingText,titleBarTextView;
	ListView lv;
	List<Coupon> tempList = null;
	UserDetails userDetails = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.used_coupon_view);
		//setContentView(R.layout.category_screen);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, COUNTRIES));

		//((TextView)findViewById(R.id.titleText)).setText("Coupons");
		backButton = (ImageButton)findViewById(R.id.btnLeft);
		backButton.setVisibility(ImageButton.VISIBLE);
		//backButton.setBackgroundResource(R.drawable.back_button);
		backButton.setBackgroundResource(ImageUtils.getBackIamge());
		backButton.setOnClickListener(this);
		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getUsedCouponsTitleText());
		
		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);
		totalSavingText = (TextView) findViewById(R.id.totalSavingText);
		totalSavingText.setText(TitleTextUtils.getTotalSavingText() + ": DKK "+ DataUtil.userDetails.getTotalSaving());
		lv = getListView();
		//fetchCoupons();
		couponAdapter = new CouponAdapter( 
				getApplicationContext(),
				couList,true ); 
		setListAdapter( couponAdapter );
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				Coupon coupon = couList.get(position);
				Intent intent = new Intent(getApplicationContext(),UsedCouponDetailsScreen.class);
				intent.putExtra("couponObj", coupon);
				startActivity(intent);
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
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.USED_COUPON_LIST_SCREEN;
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



	private Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0:
				if(userDetails != null)
					totalSavingText.setText( TitleTextUtils.getTotalSavingText() +": DKK "+ userDetails.getTotalSaving());
				if(couList != null && couList.size() > 0){
					couponAdapter.notifyDataSetChanged();
					for(int  i=0;i<couList.size();i++){
						couponAdapter.add(couList.get(i));
					}
					couponAdapter.notifyDataSetChanged();
				}else {
					//Toast.makeText(getApplicationContext(), AlertMsgUtil.getNoCouponsFoundAlertMsg(), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(getApplicationContext(),NotFoundScreen.class);
					intent.putExtra("NotFoundType", "UsedCouponNotFound");
					startActivity(intent);
				}
				break;
			case 1:
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getConnectFailureMessage(), Toast.LENGTH_SHORT).show();
				break;
			case 2: 
				//String message = msg.obj != null ? msg.obj.toString() : AlertMsgUtil.getCommonErrorMsg();
				if(msg.obj != null || msg.obj.toString().length() > 0) {
					Toast.makeText(getApplicationContext(), msg.obj.toString().length(), Toast.LENGTH_SHORT).show();
				}
				break;
			}
		}
	};
	protected void fetchCoupons() {
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					Login login = new Login();
					login.setUsername(DataUtil.userName);
					login.setPassword(DataUtil.password);
					//login.setDeviceIndentifier("A286E8D1-9681-505A-90FF-DAE45A842DC5");
					login.setDeviceIndentifier("213123");
					userDetails = WSSender.sendValidateCredentialRequest(login);
					couList = WSSender.sendGetAllUsedCoupons(DataUtil.userDetails.getUserId());
					messageHandler.sendMessage(Message.obtain(messageHandler,0));
				}catch (IOException ioe) {
					messageHandler.sendMessage(Message.obtain(messageHandler,1));
				}catch (Exception e) {
					messageHandler.sendMessage(Message.obtain(messageHandler,2,AlertMsgUtil.getCommonErrorMsg()));
				}
			}
		}.start();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == backButton) {
			finish();
		}
	}
}
