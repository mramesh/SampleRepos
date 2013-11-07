package com.oonusave.coupon;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.oonusave.coupon.model.Coupon;
import com.oonusave.coupon.util.DataUtil;
import com.oonusave.coupon.util.ImageUtils;
import com.oonusave.coupon.util.TitleTextUtils;


/**
 * 
 * @author Ramesh
 *
 */

public class MapScreen  extends BaseMapActivity implements OnClickListener{

	//	MapView mapView; 
	//	MapController mc;
	//	GeoPoint p;
	ImageButton backButton = null;
	private List<MapLocation> mapLocations;
	MapLocationOverlay overlay = null;
	Coupon coupon = null;

	TextView titleBarTextView;
	/**
	 * Note that this may be null if the Google Play services APK is not available.
	 */
	private GoogleMap mMap;


	/** Called when the activity is first created. */
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

		titleBarTextView = (TextView) findViewById(R.id.titleBarTextView);
		titleBarTextView.setText(TitleTextUtils.getMapViewTitleText());

		((ImageButton)findViewById(R.id.btnRight)).setVisibility(ImageButton.INVISIBLE);
		coupon = (Coupon) getIntent().getSerializableExtra("coupon");
		//mapView = (MapView) findViewById(R.id.mapview);




		//		overlay = new MapLocationOverlay(this,getApplicationContext());
		//		mapView.getOverlays().add(overlay);
		//
		//		mapView.getController().setZoom(14);
		//		mapView.getController().setCenter(getMapLocations().get(0).getPoint());
		//mapView.setStreetView(true);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
		
	}


	public List<MapLocation> getMapLocations() {
		if (mapLocations == null) {
			mapLocations = new ArrayList<MapLocation>();
			mapLocations.add(new MapLocation(coupon.getStoreName() , coupon.getAddress1()+ " "+ coupon.getAddress2() + " " + coupon.getAddress3() + " " + coupon.getCountry() ,false ,coupon.getLati(),coupon.getLongi()));
			mapLocations.add(new MapLocation("Current Location", "", true , Double.parseDouble(DataUtil.locationInfo.getLatitude()) , Double.parseDouble(DataUtil.locationInfo.getLongitude())));
		}
		return mapLocations;
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
			mMap.setMyLocationEnabled(true);
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
		//mapLocations.add(new MapLocation(coupon.getStoreName() , coupon.getAddress1()+ " "+ coupon.getAddress2() + " " + coupon.getAddress3() + " " + coupon.getCountry() ,false ,coupon.getLati(),coupon.getLongi()));
		//mapLocations.add(new MapLocation("Current Location", "", true , Double.parseDouble(DataUtil.locationInfo.getLatitude()) , Double.parseDouble(DataUtil.locationInfo.getLongitude())));
		
	}


	//	public MapView getMapView() {
	//		return mapView;
	//	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		//menu.add(0, MORE_OPTIONS_ID, Menu.NONE, "More Options" );   
		//menu.add(0, SEARCH_ID, Menu.NONE, "Search" );

		MenuInflater inflater = getMenuInflater();      
		inflater.inflate(R.menu.group_list_menu, menu);     
		return result;
	}

	//	@Override
	//	public boolean onOptionsItemSelected(MenuItem item) {
	//		switch ( item.getItemId() ) {
	//		case R.id.more_options:
	//			// Street View
	//			mapView.setSatellite(false);
	//			mapView.setStreetView(true);
	//
	//			break;
	//		case R.id.search:
	//			// Satilitte View
	//			mapView.setStreetView(false);
	//			mapView.setSatellite(true);
	//			break;
	//		}
	//		return super.onOptionsItemSelected(item);
	//	}



	//	public boolean onKeyDown(int keyCode, KeyEvent event) 
	//	{
	//		MapController mc = mapView.getController(); 
	//		switch (keyCode) 
	//		{
	//		case KeyEvent.KEYCODE_3:
	//			mc.zoomIn();
	//			break;
	//		case KeyEvent.KEYCODE_1:
	//			mc.zoomOut();
	//			break;
	//		}
	//		return super.onKeyDown(keyCode, event);
	//	}    

	//	@Override
	//	protected boolean isRouteDisplayed() {
	//		// TODO Auto-generated method stub
	//		return false;
	//	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == backButton) {
			finish();
		}
	}
}
