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

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.oonusave.coupon.R;
import com.oonusave.coupon.map.MyItemizedOverlay;
import com.oonusave.coupon.model.Coupon;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;

public class MyMap extends BaseMapActivity implements OnClickListener{

	//MapView mapView;
	List<Overlay> mapOverlays;
	Drawable drawable;
	Drawable drawable2;
	MyItemizedOverlay itemizedOverlay;
	MyItemizedOverlay itemizedOverlay2;

	ImageButton backButton = null;
	ImageButton directionButton = null;
	Coupon coupon = null;

	TextView titleBarTextView;
	Button showDirectionBtn;
	
	/**
     * Note that this may be null if the Google Play services APK is not available.
     */
    private GoogleMap mMap;


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.map_view);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.my_title);

		backButton = (ImageButton)findViewById(R.id.btnLeft);
		backButton.setVisibility(ImageButton.VISIBLE);
		//backButton.setBackgroundResource(R.drawable.back_button);
		backButton.setBackgroundResource(ImageUtils.getBackIamge());
		backButton.setOnClickListener(this);
		
		directionButton = (ImageButton) findViewById(R.id.btnRight);
		directionButton.setVisibility(ImageButton.VISIBLE);
		directionButton.setBackgroundResource(ImageUtils.getShowDirectionBtn());
		directionButton.setOnClickListener(this);

		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getMapViewTitleText());

		coupon = (Coupon) getIntent().getSerializableExtra("coupon");
	//	mapView = (MapView) findViewById(R.id.mapview);



	///	mapView.setBuiltInZoomControls(true);

	//	mapOverlays = mapView.getOverlays();

		// first overlay
//		drawable = getResources().getDrawable(R.drawable.map);
//		itemizedOverlay = new MyItemizedOverlay(drawable, mapView,false);
//
//		GeoPoint point1 = new GeoPoint((int)(coupon.getLati() * 1E6),(int)(coupon.getLongi() * 1E6));
//		OverlayItem overlayItem = new OverlayItem(point1, coupon.getStoreName(), 
//		coupon.getAddress1()+ " "+ coupon.getAddress2() + " " + coupon.getAddress3() + " " + coupon.getCountry());
//		itemizedOverlay.addOverlay(overlayItem);
//
//		mapOverlays.add(itemizedOverlay);
//
//		drawable2 = getResources().getDrawable(R.drawable.marker2);
//		itemizedOverlay2 = new MyItemizedOverlay(drawable2, mapView,false);
//
//		GeoPoint point2 = new GeoPoint((int)(Double.parseDouble(DataUtil.locationInfo.getLatitude()) * 1E6 ), (int)(Double.parseDouble(DataUtil.locationInfo.getLongitude()) * 1E6));
//		OverlayItem overlayItem4 = new OverlayItem(point2, "Current Location", 
//		"");		
//		itemizedOverlay2.addOverlay(overlayItem4);

		
//		mapOverlays.add(itemizedOverlay2);
//		 
//
//		final MapController mc = mapView.getController();
//		mc.animateTo(point2);
//		mc.setZoom(9);
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
	    	mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(DataUtil.locationInfo.getLatitude()), Double.parseDouble(DataUtil.locationInfo.getLongitude()))).title("Current Location"));
	    	mMap.addMarker(new MarkerOptions().position(new LatLng(coupon.getLati(),coupon.getLongi())).title(coupon.getStoreName()).draggable(true).snippet(coupon.getAddress1()+ " "+ coupon.getAddress2() + " " + coupon.getAddress3() + " " + coupon.getCountry()));
	    	//mMap.setMyLocationEnabled(true);
	    	
	    	mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(DataUtil.locationInfo.getLatitude()), Double.parseDouble(DataUtil.locationInfo.getLongitude())), 12.0f));
	    	
	    }
	
	
	private void showDefaultMap() {
		Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0?q=" + coupon.getAddress1() +", " + coupon.getAddress2() + ", " + coupon.getLocation() + ", " + coupon.getCountry()
        ));
		startActivity(intent);
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		DataUtil.CURRENT_SCREEN = PageManager.MAP_SCREEN;
		setUpMapIfNeeded();
		super.onResume();
	}

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == backButton) {
			finish();
		}else if(v == directionButton) {
			showDefaultMap();
		}
	}

}
