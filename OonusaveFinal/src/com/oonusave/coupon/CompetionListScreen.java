package com.oonusave.coupon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Competion;
import com.oonusave.coupon.model.FAQ;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.ws.WSSender;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
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

/**
 * 
 * @author Ramesh
 *
 */

public class CompetionListScreen extends BaseListActivity implements OnClickListener{

	private static final int PRO_DIALOG = 0;
	List<Competion> competionList = new ArrayList<Competion>();
	List<Competion> tempList = null;
	ListView lv = null;
	TextView titleBarTextView;

	ImageButton homeButton = null;
	ImageButton cancelButton = null;
	CompetionAdapter competionAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

		setContentView(R.layout.faq_view);
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
		titleBarTextView.setText("Competion");

		//lv = (ListView) findViewById(R.id.list);

		competionAdapter = new CompetionAdapter( 
				this,
				competionList ); 
		setListAdapter( competionAdapter );

		lv = getListView();
		
		
		lv.setCacheColorHint(Color.TRANSPARENT);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int pos, long id) {
				Intent intent = new Intent(getApplicationContext(),UserListScreen.class);
				startActivity(intent);
			}
		});


		tempList = (List) getLastNonConfigurationInstance();
		if(tempList == null) {
			fetchCompetion();
		}else {
			for(int i = 0 ; i < tempList.size() ; i++){
				competionList.add(tempList.get(i));
			}
		}

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
		final List<Competion> data = collectMyLoadedData();
		return data;
	}

	private List<Competion> collectMyLoadedData() {
		tempList = new ArrayList<Competion>();
		for(int i = 0 ; i < competionList.size() ; i++) {
			tempList.add(competionList.get(i));
		}
		return tempList;
	}


	private Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
			removeDialog(PRO_DIALOG);
			switch(msg.what){
			case 0:
				if(competionList != null && competionList.size() > 0) {		
					for(int  i=0;i<competionList.size();i++){
						competionAdapter.add(competionList.get(i));
					}
					competionAdapter.notifyDataSetChanged();
				}
				break;
			case 1:
				Toast.makeText(getApplicationContext(), "Error",
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};



	private void fetchCompetion() {
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					competionList = WSSender.sendCompetionRequest();
					messageHandler.sendMessage(Message.obtain(messageHandler,0));
				} catch (IOException e) {
					messageHandler.sendMessage(Message.obtain(messageHandler,1));
				} catch(Exception e) {
					e.printStackTrace();
					messageHandler.sendMessage(Message.obtain(messageHandler,1));
				}
			}
		}.start();
	}
	

	@Override
	public void onClick(View v) {

	}
}
