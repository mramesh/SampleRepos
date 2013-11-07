/***
 * Copyright (c) 2010 readyState Software Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.oonusave.coupon;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.oonusave.coupon.map.MyItemizedOverlay;
import com.oonusave.coupon.model.Store;
import com.oonusave.coupon.util.AlertMsgUtil;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;
import com.oonusave.coupon.ws.WSSender;

public class MyMapStore extends BaseMapActivity implements OnClickListener{


	/**
	 * Note that this may be null if the Google Play services APK is not available.
	 */
	private GoogleMap mMap;
	SharedPreferences settings = null;
	public static final String PREFS_NAME = "MyPrefsFile";

	//MapView mapView;
	//List<Overlay> mapOverlays;
	Drawable drawable;
	Drawable drawable2;
	MyItemizedOverlay itemizedOverlay;
	MyItemizedOverlay itemizedOverlay2;
	ImageButton backButton = null;
	private static final int PRO_DIALOG = 0;
	public static List<Store> storeList = new ArrayList<Store>();

	LocationManager locationManager = null;
	LocationFinder1 finder = new LocationFinder1();
	private String provider;
	TextView titleBarTextView;
	Activity mActivity = null;
	//Toolbar toolbar ;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.store_map_view);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);
		mActivity = this;
		settings = getSharedPreferences(PREFS_NAME, 0);

		backButton = (ImageButton)findViewById(R.id.btnLeft);
		backButton.setVisibility(ImageButton.INVISIBLE);
		//backButton.setBackgroundResource(R.drawable.back_button);
		backButton.setBackgroundResource(ImageUtils.getBackIamge());
		backButton.setOnClickListener(this);

		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getMapViewTitleText());
		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);
		//		mapView = (MapView) findViewById(R.id.mapview);
		//		mapView.setBuiltInZoomControls(true);
		//		mapOverlays = mapView.getOverlays();

		// first overlay
		//		drawable = getResources().getDrawable(R.drawable.map);
		//		itemizedOverlay = new MyItemizedOverlay(drawable, mapView,true);
		//
		//
		//		//mapOverlays.add(itemizedOverlay);
		//		drawable2 = getResources().getDrawable(R.drawable.marker2);
		//		itemizedOverlay2 = new MyItemizedOverlay(drawable2, mapView,false);
		//		GeoPoint point2 = new GeoPoint((int)(Double.parseDouble(DataUtil.locationInfo.getLatitude()) * 1E6 ), (int)(Double.parseDouble(DataUtil.locationInfo.getLongitude()) * 1E6));
		//		OverlayItem overlayItem4 = new OverlayItem(point2, "Current Location", 
		//				"");		
		//		itemizedOverlay2.addOverlay(overlayItem4);


		//		mapOverlays.add(itemizedOverlay2);
		//
		//
		//		final MapController mc = mapView.getController();
		//		mc.animateTo(point2);
		//		mc.setZoom(9);
		//
		//		DataUtil.mapScreen = true;
		//		loadStores();

		// Get the location manager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the locatioin provider -> use
		// default
		Criteria criteria = new Criteria();
		Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

		// Initialize the location fields
		if (location != null) {
			System.out.println("Provider " + provider + " has been selected.");
			processOnLocationChanged(location);
		} else {
			//	      latituteField.setText("Location not available");
			//	      longitudeField.setText("Location not available");
		}
	}



	private void processOnLocationChanged(Location location) {
		
		try {
			if (location == null) {
				return;
			}
			double curr_lat = formatFraction(location.getLatitude());
			double curr_longi = formatFraction(location.getLongitude());
			DataUtil.locationInfo.setLatitude(curr_lat+"");
			DataUtil.locationInfo.setLongitude(curr_longi+"");
			Toast.makeText(getApplicationContext(), "Location Changed == > " + curr_lat + " : " + curr_longi, Toast.LENGTH_LONG).show();
			new LoadStoresAsyncTask().execute("");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public double formatFraction(double d) {
		try {
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setGroupingUsed(false); // don't group by threes
			nf.setMinimumFractionDigits(5);
			nf.setMaximumFractionDigits(5);
			String sd = nf.format(d);
			return Double.valueOf(sd.trim()).doubleValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}


	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
					.getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
			}
						
			
		}
	}

	/**
	 * This is where we can add markers or lines, add listeners or move the camera. In this case, we
	 * just add a marker near Africa.
	 * <p>
	 * This should only be called once and when we are sure that {@link #mMap} is not null.
	 */
	private void setUpMap() {
		mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(DataUtil.locationInfo.getLatitude()) * 1E6 , Double.parseDouble(DataUtil.locationInfo.getLongitude()) * 1E6)).title("Current Location").icon(BitmapDescriptorFactory
				.fromResource(R.drawable.marker2)));
		//mMap.setMyLocationEnabled(true);
    	mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(DataUtil.locationInfo.getLatitude()), Double.parseDouble(DataUtil.locationInfo.getLongitude())), 12.0f));
		
	}



	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(finder);
	}




	@Override
	protected void onResume() {
		super.onResume();
		DataUtil.CURRENT_SCREEN = PageManager.STORES_MAP_SCREEN;
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 10, finder);
		setUpMapIfNeeded();
	}



	//	    private void unregister() {
	//	        if( registered ) {
	//	            Log.d( LOG_TAG, "unregister()" );
	//	            C2DMessaging.unregister( this );
	//	            Log.d( LOG_TAG, "unregister() done" );
	//	        }
	//	    }

	//	@Override
	//	protected boolean isRouteDisplayed() {
	//		return false;
	//	}


	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch (id) {
		case PRO_DIALOG:
			ProgressDialog dialog1 = new ProgressDialog(this);
			dialog1.setMessage(AlertMsgUtil.getLoadingMessageText());
			dialog1.setIndeterminate(true);
			dialog1.setCancelable(true);
			dialog = dialog1;
		}
		return dialog;
	}


	private class LoadStoresAsyncTask extends AsyncTask<String, Void, Boolean> {

		final List<MarkerOptions> markers = new ArrayList<MarkerOptions>();

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(PRO_DIALOG);
		}

		@Override
		protected Boolean doInBackground(String... params) {
			try{
				storeList = WSSender.sendSelectStoresRequest();
				System.out.println(" Store list size ==== > " + storeList.size());
				for(final Store s : storeList) {
					final Drawable drawable = new DrawableManager().fetchDrawable(s.getAccountType());
					final String address = s.getAddress1()+ " "+ s.getAddress2() + " " + s.getAddress3() + " " + s.getCountry();
					MarkerOptions marker = new MarkerOptions().position(new LatLng(Double.parseDouble(s.getLatitude()) * 1E6 , Double.parseDouble(s.getLongitude()) * 1E6)).title(s.getStoreName()).snippet(address).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
					markers.add(marker);
				}
				return true;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			removeDialog(PRO_DIALOG);
			if(result) {
				for(final MarkerOptions markerOptions: markers) {
					mMap.addMarker(markerOptions);
				}
			}
		}

	}



	//	private Handler messageHandler = new Handler() {
	//
	//		@Override
	//		public void handleMessage(Message msg) {
	//			//Log.i(Constants.TAG, "Inside handle message ------ > " + msg.what);
	//			removeDialog(PRO_DIALOG);
	//			switch(msg.what) {
	//			case 0: 
	//				for(final Store s : storeList) {
	//
	//					Thread thread = new Thread() {
	//						@Override
	//						public void run() {
	//							final Drawable drawable = new DrawableManager().fetchDrawable(s.getAccountType());
	//							runOnUiThread(new Runnable() {
	//
	//								@Override
	//								public void run() {
	//									if(mActivity != null) {
	//										itemizedOverlay = new MyItemizedOverlay(drawable, mapView,true);
	//										//mapLocations.add(new MapLocation(s.getStoreName(),s.getAddress1() + s.getAddress2() + s.getAddress3() + s.getCountry() , false ,Double.parseDouble(s.getLatitude()),Double.parseDouble(s.getLongitude())));
	//										GeoPoint point1 = new GeoPoint((int)(Double.parseDouble(s.getLatitude()) * 1E6),(int)(Double.parseDouble(s.getLongitude()) * 1E6));
	//										OverlayItem overlayItem = new OverlayItem(point1, s.getStoreName(), 
	//												s.getAddress1()+ " "+ s.getAddress2() + " " + s.getAddress3() + " " + s.getCountry());
	//										itemizedOverlay.addOverlay(overlayItem);
	//										mapOverlays.add(itemizedOverlay);		
	//									}
	//								}
	//							});
	//						}
	//					};
	//					thread.start();
	//				}
	//				break;
	//			case 1:
	//				Toast.makeText(getApplicationContext(), "Error getting store list!",
	//						Toast.LENGTH_SHORT).show();
	//				break;
	//			case 2: 
	//				Toast.makeText(getApplicationContext(), AlertMsgUtil.getConnectFailureMessage(),
	//						Toast.LENGTH_SHORT).show();
	//				break;
	//			}
	//		}
	//	};
	//
	//
	//	public void loadStores() {
	//		showDialog(PRO_DIALOG);
	//		new Thread() {
	//			public void run() {
	//				try{
	//					storeList = WSSender.sendSelectStoresRequest();
	//					messageHandler.sendMessage(Message.obtain(messageHandler,0));
	//				}catch(Exception e) {
	//					e.printStackTrace();
	//					messageHandler.sendMessage(Message.obtain(messageHandler,1));
	//				}
	//			}
	//		}.start();
	//	}
	//
	//
	//
	//
	@Override
	public void onClick(View v) {
		if(v == backButton) {
			finish();
		}
	}
	


	class LocationFinder1 implements LocationListener {
		@Override
		public void onLocationChanged(Location location) {
			processOnLocationChanged(location);
		}

		@Override
		public void onProviderDisabled(String arg0) {
		}

		@Override
		public void onProviderEnabled(String provider) {

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}
	}
}

