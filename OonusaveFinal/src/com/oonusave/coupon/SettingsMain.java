package com.oonusave.coupon;

import com.oonusave.coupon.R;
import com.oonusave.coupon.util.ImageUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class SettingsMain extends BaseActivity implements OnClickListener{

	ImageButton backBtn;
	RelativeLayout generalLayout, profileLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.settings_view_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		((ImageButton)findViewById(R.id.btnRight)).setVisibility(View.INVISIBLE);
		
		
		backBtn = (ImageButton)findViewById(R.id.btnLeft);
		backBtn.setVisibility(ImageButton.VISIBLE);
		backBtn.setBackgroundResource(ImageUtils.getBackIamge());
		
		generalLayout = (RelativeLayout) findViewById(R.id.profileLayout);
		profileLayout = (RelativeLayout) findViewById(R.id.generalLayout);
		
		
		backBtn.setOnClickListener(this);
		generalLayout.setOnClickListener(this);
		profileLayout.setOnClickListener(this);
		
		
	}
	
	@Override
	public void onClick(View v) {
		if(v == generalLayout) {
			//Toast.makeText(getApplicationContext(), " General Layout", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(getApplicationContext(), RegistrationScreen.class);
			startActivity(i);
			
		}else if(v == profileLayout){
			//Toast.makeText(getApplicationContext(), " Profile Layout", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(getApplicationContext(), SettingsScreen.class);
			startActivity(i);
			
		}else if(v == backBtn) {
			finish();
		}
	}

}
