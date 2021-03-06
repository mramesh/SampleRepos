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

package com.oonusave.coupon.map;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.oonusave.coupon.MyMapStore;
import com.oonusave.coupon.StoreCouponListScreen;
import com.oonusave.coupon.model.Store;


public class MyItemizedOverlay extends BalloonItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> m_overlays = new ArrayList<OverlayItem>();
	private Context c;
	private boolean isStore = false;


	public MyItemizedOverlay(Drawable defaultMarker, MapView mapView,boolean isStore) {
		super(defaultMarker, mapView);
		c = mapView.getContext();
		populate(); // Add this
		this.isStore = isStore;
	}

	public void addOverlay(OverlayItem overlay) {
		m_overlays.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return m_overlays.get(i);
	}

	@Override
	public int size() {
		return m_overlays.size();
	}

	@Override
	protected boolean onBalloonTap(int index) {
		//Toast.makeText(c, "index --> " + index + " :: isStore --> " + isStore + ":: storeList size ---- > " + MyMapStore.storeList.size(),
			//Toast.LENGTH_LONG).show();
		
		
		if(isStore) {
			if(MyMapStore.storeList != null && MyMapStore.storeList.size() > 0) {
				//if(MyMapStore.storeList.size() <=  index) {
					Store store = (Store) MyMapStore.storeList.get(index);	
					Intent intent = new Intent(c,StoreCouponListScreen.class);
					intent.putExtra("store", store);
					intent.putExtra("isFavStoreCoupons", false);
					c.startActivity(intent);
				//}
			}
		}
		return true;
	}

}
