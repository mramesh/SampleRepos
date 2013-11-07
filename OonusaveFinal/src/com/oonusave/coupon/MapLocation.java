package com.oonusave.coupon;

import com.google.android.maps.GeoPoint;

/** Class to hold our location information */
public class MapLocation {

	private GeoPoint	point;
	private String		name = "";
	private String 		address = "";
	private boolean isCurrentLocation  = false;

	public MapLocation(String name,String address,boolean isCurrentLocation,double latitude, double longitude) {
		this.name = name;
		this.address = address;
		this.isCurrentLocation = isCurrentLocation;
		point = new GeoPoint((int)(latitude*1e6),(int)(longitude*1e6));
	}

	public GeoPoint getPoint() {
		return point;
	}

	public String getName() {
		return name;
	}
	
	
	public String getAddress() {
		return address;
	}
	
	public boolean isCurrentLocation() {
		return isCurrentLocation;
	}
	
}
