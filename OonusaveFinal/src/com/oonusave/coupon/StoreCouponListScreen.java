package com.oonusave.coupon;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Coupon;
import com.oonusave.coupon.model.GeneralResponse;
import com.oonusave.coupon.model.Store;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.ws.WSSender;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
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
public class StoreCouponListScreen extends BaseListActivity implements OnClickListener{

	List<Coupon> couList = new ArrayList<Coupon>();
	Store store ;
	CouponAdapter couponAdapter = null;
	ImageButton addFavoriteStore,homeButton = null;
	ImageView storeImage;
	private static final int PRO_DIALOG = 0;
	Button mapButton,websiteBtn,favButton;
	String infoMessage = "",errorMessage = "";
	boolean isFavStoreCoupons = false;
	List<Coupon> tempList = null;
	TextView kmsTextView;
	private DrawableManager drawableManager = new DrawableManager();
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		isFavStoreCoupons = intent.getBooleanExtra("isFavStoreCoupons", false);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.coupon_list_store);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);


		store = (Store)intent.getSerializableExtra("store");
		homeButton = (ImageButton)findViewById(R.id.btnLeft);
		homeButton.setVisibility(ImageButton.VISIBLE);
		kmsTextView = (TextView) findViewById(R.id.kmsText);
		kmsTextView.setText(store.getDistance());
	
		
		
		if(isFavStoreCoupons) {
			//homeButton.setBackgroundResource(R.drawable.mystores_button);
			homeButton.setBackgroundResource(ImageUtils.getBackIamge());
		}else {
			//homeButton.setBackgroundResource(R.drawable.button_storelist);
			homeButton.setBackgroundResource(ImageUtils.getBackIamge());
		}
		homeButton.setOnClickListener(this);
				
		addFavoriteStore = (ImageButton)findViewById(R.id.btnRight);
		addFavoriteStore.setVisibility(ImageButton.INVISIBLE);
		
		((TextView)findViewById(R.id.couponStoreName)).setText(store.getStoreName());
		StringBuffer sb = new StringBuffer();
		
		if(store.getAddress1() != null && !store.getAddress1().equalsIgnoreCase("")) {
			sb.append(store.getAddress1()+"\n");
		}

		if(store.getAddress2() != null && !store.getAddress2().equalsIgnoreCase("")) {
			sb.append(store.getAddress2()+"\n");
		}

		if(store.getAddress3() != null && !store.getAddress3().equalsIgnoreCase("")) {
			sb.append(store.getAddress3()+"\n");
		}
		if(store.getCountry() != null && !store.getCountry().equalsIgnoreCase("")) {
			sb.append(store.getCountry()+"\n");
		}

		
		
		//store.getAddress1()+"\n"+store.getAddress2() + "\n" + store.L() + " \n" + store.getCountry()
		((TextView)findViewById(R.id.title)).setText(sb.toString());

		storeImage = (ImageView) findViewById(R.id.titleImg);
		//storeImage.setBackgroundColor(Color.TRANSPARENT);
		//storeImage.setBackgroundResource(R.drawable.store_img_bg);

		drawableManager.fetchDrawableOnThread(store.getImageUrl(), storeImage);
		//Drawable image = ImageOperations(getApplicationContext(),store.getImageUrl(),"image.jpg");
		//storeImage.setImageDrawable(image);

		mapButton = (Button) findViewById(R.id.map_button);
		websiteBtn = (Button) findViewById(R.id.website_btn);
		favButton = (Button)findViewById(R.id.add_favorite_btn);
		
		mapButton.setBackgroundResource(ImageUtils.getMapBtnImage());
		websiteBtn.setBackgroundResource(ImageUtils.getWebsiteBtnImage());
		if(isFavStoreCoupons) {
			//favButton.setBackgroundResource(R.drawable.remove_favorite_store_btn);
			favButton.setBackgroundResource(ImageUtils.getRemoveFavStoreBtnImage());
		}else {
			favButton.setBackgroundResource(ImageUtils.getAddFavStoreBtnImage());
		}
		
		mapButton.setOnClickListener(this);
		websiteBtn.setOnClickListener(this);
		favButton.setOnClickListener(this);
		
		
		couponAdapter = new CouponAdapter( 
				getApplicationContext(),
				couList,true ); 
		setListAdapter( couponAdapter );
		ListView lv = getListView();

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
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.STORE_DETAILS_SCREEN;
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
			break;
		}
		return dialog;
	}


	//ProgressDialog progressDialog = null;

	private Handler messageHandler = new Handler() {
		public void handleMessage(Message msg) {
			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0:
				couponAdapter.notifyDataSetChanged();
				//Log.i(Constants.TAG, " Coupon List Size ---- > " + couList.size());
				couponAdapter.clear();
				for(int  i=0;i<couList.size();i++){
					couponAdapter.add(couList.get(i));
				}
				//progressDialog.dismiss();
				couponAdapter.notifyDataSetChanged();
				break;

			case 2:
				Toast.makeText(getApplicationContext(), AlertMsgUtil.getConnectFailureMessage(), Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};


	private Handler addFavoriteStoreHander = new Handler() {

		public void handleMessage(Message msg) {
			removeDialog(PRO_DIALOG);
			switch(msg.what) {
			case 0:
				Toast.makeText(getApplicationContext(), infoMessage, Toast.LENGTH_SHORT).show();
				if(isFavStoreCoupons)
					startActivity(new Intent(getApplicationContext(),FavouriteStoresScreen.class));
				
				finish();
				break;
			case 1: 
				Toast.makeText(getApplicationContext(), infoMessage, Toast.LENGTH_SHORT).show();
				break;
			case 2: 
				Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	protected void fetchCoupons() {
		//progressDialog = ProgressDialog.show(this, "", AlertMsgUtil.);
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					couList = WSSender.sendSelectCouponsInStore(store.getStoreId());
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
			if(isFavStoreCoupons) {
				startActivity(new Intent(getApplicationContext(),FavouriteStoresScreen.class));
			}else{
			finish();
			}
		}else if(v == favButton) {
			addFavoriteStore();
		}else if(v == mapButton) {
			showMapLocation();
		}else if(v == websiteBtn) {
			showWebsite();
		}else{}
	}


	private void addFavoriteStore() {
		
		//progressDialog = ProgressDialog.show(this, "", "Please wait...");
		showDialog(PRO_DIALOG);
		new Thread() {
			public void run() {
				try {
					GeneralResponse generalResponse = null;
					if(isFavStoreCoupons){
						generalResponse = WSSender.sendRemoveFavoriteStore((int)DataUtil.userDetails.getUserId(), (int)store.getStoreId());
					}else{
						generalResponse = WSSender.sendAddFavoriteStore((int)DataUtil.userDetails.getUserId(), (int)store.getStoreId());
						//infoMessage = generalResponse.getMessage();
					}
										
					if(generalResponse.isSuccess()) {
						if(isFavStoreCoupons) {
							infoMessage = AlertMsgUtil.getRemoveFavStoreSuccess();
						}else {
							infoMessage = AlertMsgUtil.getAddFavStoreSuccess();
						}
						addFavoriteStoreHander.sendMessage(Message.obtain(addFavoriteStoreHander,0));
					}else {
						if(isFavStoreCoupons) {
							infoMessage = AlertMsgUtil.getRemoveFavStoreFailure();
						}else {
							infoMessage = AlertMsgUtil.getAddFavStoreFailure();
						}
						addFavoriteStoreHander.sendMessage(Message.obtain(addFavoriteStoreHander,1));
					}
				}catch(Exception e) {
					errorMessage = e.getMessage();
					addFavoriteStoreHander.sendMessage(Message.obtain(addFavoriteStoreHander,2,e.getMessage()));
				}
			}
		}.start();
	}


	private void showMapLocation() {
		Intent i = new Intent(this,MyMap.class);
		Coupon c = new Coupon();
		c.setStoreName(store.getStoreName());
		c.setLati(Float.parseFloat(store.getLatitude()));
		c.setLongi(Float.parseFloat(store.getLongitude()));
		c.setAddress1(store.getAddress1());
		i.putExtra("coupon", c);
		startActivity(i);

	}

	public void showWebsite() {
		//startActivity( new Intent( Intent.ACTION_VIEW, Uri.parse("http://www.mocansa.com/StoreDetails.aspx?couponid=" + store.getStoreId())));
		//Log.i(Constants.TAG, "Web Address --- > " + store.getWebAddress());
		if(store.getWebAddress().startsWith("http")) {
			startActivity( new Intent( Intent.ACTION_VIEW, Uri.parse(store.getWebAddress())));
			//finish();
		}else {
			Toast.makeText(getApplicationContext(), "Not a valid website url!", Toast.LENGTH_SHORT).show();
		}
	}

	private Drawable ImageOperations(Context ctx, String url, String saveFilename) {
		try {
			InputStream is = (InputStream) this.fetch(url);
			Drawable d = Drawable.createFromStream(is, "src");
			return d;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch(Exception e) {
			return null;
		}
	}

	public Object fetch(String address) throws MalformedURLException,IOException {
		URL url = new URL(address);
		Object content = url.getContent();
		return content;
	}

}
