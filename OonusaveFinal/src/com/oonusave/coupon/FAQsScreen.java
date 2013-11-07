package com.oonusave.coupon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ErrorManager;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Category;
import com.oonusave.coupon.model.FAQ;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.ws.WSSender;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
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

public class FAQsScreen extends BaseListActivity implements OnClickListener{

	ImageButton backButton = null;
	FAQAdapter faqAdapter = null;
	List<FAQ> faqList = new ArrayList<FAQ>();
	TextView titleBarTextView;
	List<FAQ> tempList = null;
	private final int PRO_DIALOG = 100,TEST_DIALOG = 1;
	String errorMesssage = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.faq_view);		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, COUNTRIES));

		//((TextView)findViewById(R.id.titleText)).setText("Coupons");
		backButton = (ImageButton)findViewById(R.id.btnLeft);
		backButton.setVisibility(ImageButton.VISIBLE);
		//backButton.setBackgroundResource(R.drawable.back_button);
		backButton.setBackgroundResource(ImageUtils.getBackIamge());
		backButton.setOnClickListener(this);
		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);

		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getFAQTitleText());



		faqAdapter = new FAQAdapter( 
				this,
				faqList ); 
		setListAdapter( faqAdapter );

		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				FAQ faq = (FAQ) faqList.get(position);
				
				
				//Toast.makeText(getApplicationContext(), "Category Id -- >" + category.getCategoryId() + ":: Name -- >" + category.getCategoryName() ,
				//Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getApplicationContext(),FAQDetailsScreen.class);
				intent.putExtra("faq", faq);
				startActivity(intent);
				
				
				((ImageView)view.findViewById(R.id.catImage)).setBackgroundResource(R.drawable.msg_read_icon);
				((TextView) view.findViewById(R.id.categoryNameTextView)).setTextColor(Color.BLUE);
			}
		});


		//Log.i(Constants.TAG, " tempList --- > " + tempList);
		tempList = (List) getLastNonConfigurationInstance();
		if(tempList == null) {
			fetchFAQs();
		}else {
			//Log.i(Constants.TAG, " tempList Size --- > " + tempList.size());
			for(int i = 0 ; i < tempList.size() ; i++){
				faqList.add(tempList.get(i));
			}
		}
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.FAQ_SCREEN;
		super.onResume();
	}
	
	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		super.onPostResume();
		
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		// TODO Auto-generated method stub

		//Log.i(Constants.TAG, "CategoryListScreen ----- onRetainNonConfigurationInstance() --- ");
		final List<FAQ> data = collectMyLoadedData();
		return data;
	}

	private List<FAQ> collectMyLoadedData() {
		tempList = new ArrayList<FAQ>();
		for(int i = 0 ; i < faqList.size() ; i++) {
			tempList.add(faqList.get(i));
		}
		return tempList;
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			//progressDialog.dismiss();

		}
		return super.onKeyDown(keyCode, event);
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


	//ProgressDialog progressDialog = null;

	private Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
			//Log.i(Constants.TAG, "# FAQListScreen # ----- handleMessage() ------ ");
			switch(msg.what){
			///super.handleMessage(msg);
			case 0:{ 
				faqAdapter.notifyDataSetChanged();
				//couponAdapter.notifyDataSetInvalidated();
				for(int  i=0;i<faqList.size();i++){
					faqAdapter.add(faqList.get(i));
				}
				//Log.i(Constants.TAG, "# FAQListScreen # ----- List size  ------> " + faqList.size());
				removeDialog(PRO_DIALOG);
				faqAdapter.notifyDataSetChanged();
			}
			break;
			case 1: {
				Toast.makeText(getApplicationContext(), errorMesssage, Toast.LENGTH_SHORT);
				break;
			}
			}

		}
	};


	protected void fetchFAQs() {
		//progressDialog = ProgressDialog.show(this, "", AlertMsgUtil.getLoadingMessageText());
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					//faqList = WSSender.sendFAQRequest();
					faqList = WSSender.sendMInboxmessageIP();
					messageHandler.sendEmptyMessage(0);
					//Thread.sleep(2);
				} catch(IOException ioe){
					if(isOnline()) {
						errorMesssage = AlertMsgUtil.getCommonErrorMsg();
					}else {
						errorMesssage = AlertMsgUtil.getConnectFailureMessage();  
					}
					messageHandler.sendEmptyMessage(1);
				}catch(Exception e) {
					errorMesssage = AlertMsgUtil.getCommonErrorMsg();
					messageHandler.sendEmptyMessage(1);
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

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if(cm == null || cm.getActiveNetworkInfo() == null) {
			return false;
		}
		return cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}


}
