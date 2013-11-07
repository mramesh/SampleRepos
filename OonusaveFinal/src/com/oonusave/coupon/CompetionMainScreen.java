package com.oonusave.coupon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Competion;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.ws.WSSender;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CompetionMainScreen extends BaseActivity implements OnClickListener{

	ImageButton backBtn;
	RelativeLayout topSaverLayout, firstUserLayout, topRecommentedLayout;

	private static final int PRO_DIALOG = 0;
	List<Competion> competionList = new ArrayList<Competion>();
	TextView titleBarTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.competion_view_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		((ImageButton)findViewById(R.id.btnRight)).setVisibility(View.INVISIBLE);


		backBtn = (ImageButton)findViewById(R.id.btnLeft);
		backBtn.setVisibility(ImageButton.VISIBLE);
		backBtn.setBackgroundResource(ImageUtils.getBackIamge());
		
		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getCompetionTitleStr());

		topSaverLayout = (RelativeLayout) findViewById(R.id.topSaverLayout);
		topRecommentedLayout = (RelativeLayout) findViewById(R.id.topRecommentedLayout);
		firstUserLayout = (RelativeLayout) findViewById(R.id.firstUserLayout);

		backBtn.setOnClickListener(this);
		topRecommentedLayout.setOnClickListener(this);
		topSaverLayout.setOnClickListener(this);
		firstUserLayout.setOnClickListener(this);

		fetchCompetion();

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
			switch(msg.what){
			case 0:
				if(competionList != null && competionList.size() > 0) {		
					//Toast.makeText(getApplicationContext(), " Size --- > " + competionList.size(), Toast.LENGTH_SHORT).show();
					
					//for(int i = 0 ; i< competionList.size() ; i++){
						Competion competion = (Competion) competionList.get(0);
						((TextView)findViewById(R.id.title1)).setText(competion.getComprtionName());
						((TextView)findViewById(R.id.price1)).setText(competion.getPrice());
						((TextView)findViewById(R.id.desc1)).setText(competion.getDescription());
						
						competion = (Competion) competionList.get(1);
						((TextView)findViewById(R.id.title2)).setText(competion.getComprtionName());
						((TextView)findViewById(R.id.price2)).setText(competion.getPrice());
						((TextView)findViewById(R.id.desc2)).setText(competion.getDescription());
						
						competion = (Competion) competionList.get(2);
						((TextView)findViewById(R.id.title3)).setText(competion.getComprtionName());
						((TextView)findViewById(R.id.price3)).setText(competion.getPrice());
						((TextView)findViewById(R.id.desc3)).setText(competion.getDescription());
						
					//}
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
					e.printStackTrace();
				} catch(Exception e) {
					e.printStackTrace();
					messageHandler.sendMessage(Message.obtain(messageHandler,1));
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	public void onClick(View v) {
		
		
		
		
		Intent intent = new Intent(getApplicationContext(),UserListScreen.class);
		if(v == topRecommentedLayout) {
			//Toast.makeText(getApplicationContext(), " topRecommentedLayout ", Toast.LENGTH_SHORT).show();
			intent.putExtra("action", "toprecomentor");
			startActivity(intent);
		}else if(v == topSaverLayout) {
			//	Toast.makeText(getApplicationContext(), " topSaverLayout ", Toast.LENGTH_SHORT).show();
			intent.putExtra("action", "topsaver");
			startActivity(intent);
		}else if(v == firstUserLayout) {
			//Toast.makeText(getApplicationContext(), " firstUserLayout ", Toast.LENGTH_SHORT).show();
			intent.putExtra("action", "topuser");
			startActivity(intent);
		}else if(v == backBtn) {
			finish();
		}

	}



}
