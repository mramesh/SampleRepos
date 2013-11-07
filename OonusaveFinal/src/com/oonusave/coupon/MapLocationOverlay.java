package com.oonusave.coupon;

import java.util.Iterator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.oonusave.coupon.R;
import com.oonusave.coupon.util.Utils;


/**
 * 
 * @author Ramesh
 *
 */
public class MapLocationOverlay  extends Overlay {

	//  Store these as global instances so we don't keep reloading every time
	private Bitmap bubbleIcon, shadowIcon,mappinIcon;

	private MapScreen mapLocationViewer;
	private Context context;

	private Paint	innerPaint, borderPaint, textPaint,textPaint1;

	//  The currently selected Map Location...if any is selected.  This tracks whether an information  
	//  window should be displayed & where...i.e. whether a user 'clicked' on a known map location
	private MapLocation selectedMapLocation;  

	public MapLocationOverlay(MapScreen mapLocationViewer,Context context) {

		this.mapLocationViewer = mapLocationViewer;
		this.context = context;
		bubbleIcon = BitmapFactory.decodeResource(mapLocationViewer.getResources(),R.drawable.map);
		
		shadowIcon = BitmapFactory.decodeResource(mapLocationViewer.getResources(),R.drawable.shadow);
		mappinIcon = BitmapFactory.decodeResource(mapLocationViewer.getResources(),R.drawable.mappin_red);
	}

	@Override
	public boolean onTap(GeoPoint p, MapView	mapView)  {

		//  Store whether prior popup was displayed so we can call invalidate() & remove it if necessary.
		boolean isRemovePriorPopup = selectedMapLocation != null;  

		//  Next test whether a new popup should be displayed
		selectedMapLocation = getHitMapLocation(mapView,p);
		if ( isRemovePriorPopup || selectedMapLocation != null) {
			mapView.invalidate();
		}		

		//  Lastly return true if we handled this onTap()
		return selectedMapLocation != null;
	}

	@Override
	public void draw(Canvas canvas, MapView	mapView, boolean shadow) {

		drawMapLocations(canvas, mapView, shadow);
		drawInfoWindow(canvas, mapView, shadow);
	}

	/**
	 * Test whether an information balloon should be displayed or a prior balloon hidden.
	 */
	private MapLocation getHitMapLocation(MapView	mapView, GeoPoint	tapPoint) {

		//  Track which MapLocation was hit...if any
		MapLocation hitMapLocation = null;

		RectF hitTestRecr = new RectF();
		Point screenCoords = new Point();
		Iterator<MapLocation> iterator = mapLocationViewer.getMapLocations().iterator();
		while(iterator.hasNext()) {
			MapLocation testLocation = iterator.next();

			//  Translate the MapLocation's lat/long coordinates to screen coordinates
			mapView.getProjection().toPixels(testLocation.getPoint(), screenCoords);

			// Create a 'hit' testing Rectangle w/size and coordinates of our icon
			// Set the 'hit' testing Rectangle with the size and coordinates of our on screen icon
			hitTestRecr.set(-bubbleIcon.getWidth()/2,-bubbleIcon.getHeight(),bubbleIcon.getWidth()/2,0);
			hitTestRecr.offset(screenCoords.x,screenCoords.y);

			//  Finally test for a match between our 'hit' Rectangle and the location clicked by the user
			mapView.getProjection().toPixels(tapPoint, screenCoords);
			if (hitTestRecr.contains(screenCoords.x,screenCoords.y)) {
				hitMapLocation = testLocation;
				break;
			}
		}

		//  Lastly clear the newMouseSelection as it has now been processed
		tapPoint = null;

		return hitMapLocation; 
	}

	private void drawMapLocations(Canvas canvas, MapView	mapView, boolean shadow) {

		Iterator<MapLocation> iterator = mapLocationViewer.getMapLocations().iterator();
		Point screenCoords = new Point();
		while(iterator.hasNext()) {	   
			MapLocation location = iterator.next();
			mapView.getProjection().toPixels(location.getPoint(), screenCoords);

			if (shadow) {
				//  Only offset the shadow in the y-axis as the shadow is angled so the base is at x=0; 
				canvas.drawBitmap(shadowIcon, screenCoords.x, screenCoords.y - shadowIcon.getHeight(),null);
			} else {
				if(location.isCurrentLocation()) {
					canvas.drawBitmap(mappinIcon, screenCoords.x - mappinIcon.getWidth()/2, screenCoords.y - mappinIcon.getHeight(),null);
				}else{
					canvas.drawBitmap(bubbleIcon, screenCoords.x - bubbleIcon.getWidth()/2, screenCoords.y - bubbleIcon.getHeight(),null);
				}
			}
		}
	}

	private void drawInfoWindow(Canvas canvas, MapView	mapView, boolean shadow) {

		if ( selectedMapLocation != null) {
			if ( shadow) {
				//  Skip painting a shadow in this tutorial
			} else {
				//  First determine the screen coordinates of the selected MapLocation
				Point selDestinationOffset = new Point();
				mapView.getProjection().toPixels(selectedMapLocation.getPoint(), selDestinationOffset);

				//  Setup the info window with the right size & location
				int INFO_WINDOW_WIDTH = (int)Utils.convertDipToPixel(300, context);
				int INFO_WINDOW_HEIGHT = (int)Utils.convertDipToPixel(50, context);
				
				if(selectedMapLocation.isCurrentLocation()) {
					INFO_WINDOW_WIDTH = (int)Utils.convertDipToPixel(150, context);
					INFO_WINDOW_HEIGHT = (int)Utils.convertDipToPixel(40, context);
				}
				
				
				RectF infoWindowRect = new RectF(0,0,INFO_WINDOW_WIDTH,INFO_WINDOW_HEIGHT);				
				int infoWindowOffsetX = selDestinationOffset.x-INFO_WINDOW_WIDTH/2;
				int infoWindowOffsetY = selDestinationOffset.y-INFO_WINDOW_HEIGHT-bubbleIcon.getHeight();
				infoWindowRect.offset(infoWindowOffsetX,infoWindowOffsetY);

				
				int intoWin = (int) Utils.convertDipToPixel(5, context);
				//  Draw inner info window
				canvas.drawRoundRect(infoWindowRect, intoWin, intoWin, getInnerPaint());

				//  Draw border for info window
				canvas.drawRoundRect(infoWindowRect, intoWin, intoWin, getBorderPaint());

				//  Draw the MapLocation's name
				int TEXT_OFFSET_X = (int)Utils.convertDipToPixel(10, context);
				int TEXT_OFFSET_Y = (int)Utils.convertDipToPixel(20, context);
				canvas.drawText(selectedMapLocation.getName(),infoWindowOffsetX+TEXT_OFFSET_X,infoWindowOffsetY+TEXT_OFFSET_Y,getTextPaint());
				if(!selectedMapLocation.isCurrentLocation()) {
					canvas.drawText(selectedMapLocation.getAddress(),infoWindowOffsetX+TEXT_OFFSET_X,infoWindowOffsetY+TEXT_OFFSET_Y + (int)Utils.convertDipToPixel(20, context) ,getTextPaint1());
				}
			}
		}
	}

	public Paint getInnerPaint() {
		if ( innerPaint == null) {
			innerPaint = new Paint();
			innerPaint.setARGB(225, 75, 75, 75); //gray
			innerPaint.setAntiAlias(true);
		}
		return innerPaint;
	}

	public Paint getBorderPaint() {
		
		if ( borderPaint == null) {
			borderPaint = new Paint();
			borderPaint.setARGB(255, 255, 255, 255);
			borderPaint.setAntiAlias(true);
			borderPaint.setStyle(Style.STROKE);
			borderPaint.setStrokeWidth(2);
		}
		return borderPaint;
	}

	public Paint getTextPaint() {
		if ( textPaint == null) {
			textPaint = new Paint();
			textPaint.setFakeBoldText(true);
			textPaint.setARGB(255, 255, 255, 255);
			textPaint.setTextSize(14);
			textPaint.setAntiAlias(true);
		}
		return textPaint;
	}

	public Paint getTextPaint1() {
		if ( textPaint1 == null) {
			textPaint1 = new Paint();
			textPaint1.setARGB(225, 225, 225, 225);
			textPaint1.setTextSize(12);
			textPaint1.setAntiAlias(false);
		}
		return textPaint1;
	}
}