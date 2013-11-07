package com.oonusave.coupon.model;

/**
 * 
 * @author Ramesh
 *
 */


public class LocationInfo {
	
	private static final String EMPTY = "";
	
	//private String latitude = "13.033191561698914";
	//private String longitude = "80.21185219287872";
	
//	private String latitude = "55.0706778";
//	private String longitude = "14.9207614";
	
	private String latitude = "0";
	private String longitude = "0";
//	
	//private String latitude = "37.799800872802734";
	//private String longitude = "-122.40699768066406";
	
	
    public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
