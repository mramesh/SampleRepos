package com.oonusave.coupon;

import com.oonusave.coupon.R;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.DataUtil;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * 
 * @author Ramesh
 *
 */

public class ImageAdapter extends BaseAdapter {

	private Context mContext;

	public ImageAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return mThumbIds.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		return new IconAdapterView(mContext, mThumbIds[position],getIconName(position));
	}

	private Integer[] mThumbIds = {
			//R.drawable.profile_icon1,
			R.drawable.settings_icon1,
			R.drawable.used_coupons_icon1,
			R.drawable.expiry_coupons_icon1,
			R.drawable.finder_icon1,
			R.drawable.inbox_icon1,
			R.drawable.feedback_icon1,
			R.drawable.favorite_icon1,
			R.drawable.competition_icon1,
			R.drawable.logout_icon
	};
	
	private String[] titleName = {
			//"Profile","Settings","Used Coupon","Expiry Coupon","Store Finder","My Favorite Stores","Map","Feedback","FAQs",
			//Constants.MORE_PROFILE_ENG,
			Constants.MORE_SETTINGS_ENG,
			Constants.MORE_USED_COUPON_ENG,
			Constants.MORE_EXPIRY_COUPON_ENG,
			Constants.MORE_STORE_FINDER_ENG,
			Constants.MORE_INBOX_ENG,
			Constants.MORE_FEEDBACK_ENG,
			
			//Constants.MORE_MAP_ENG,
			Constants.MORE_FAV_STORE_ENG,
			Constants.MORE_COMPETION_ENG,
			Constants.LOG_OUT
			
	};
	
	private String[] titleNameDanish = {
			Constants.MORE_SETTINGS_ENG,
			Constants.MORE_USED_COUPON_ENG,
			Constants.MORE_EXPIRY_COUPON_ENG,
			Constants.MORE_STORE_FINDER_ENG,
			Constants.MORE_INBOX_ENG,
			Constants.MORE_FEEDBACK_ENG,
			
			//Constants.MORE_MAP_ENG,
			Constants.MORE_FAV_STORE_ENG,
			Constants.MORE_COMPETION_ENG,
			Constants.LOG_OUT
			
	};
	
	
	String getIconName(int position) {
		
			return titleName[position];
		
	}
}



class IconAdapterView extends LinearLayout{

	Integer drawableId;
	String title;

	public IconAdapterView(Context context,Integer drawableId,String title) {
		super(context);
		// TODO Auto-generated constructor stub
		this.title = title;
		this.drawableId = drawableId;
		this.setGravity(Gravity.CENTER_HORIZONTAL);
		
		setOrientation(LinearLayout.VERTICAL);
		setBackgroundColor(Color.TRANSPARENT);
		ImageView imageView;
		TextView titleView;

		imageView = new ImageView(context);
		imageView.setImageResource(drawableId);
		imageView.setPadding(2, 2, 2, 2);
		imageView.setLayoutParams(new GridView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		//imageView.setScaleType(ImageView.ScaleType.CENTER);
		
		

		
		addView(imageView);

		titleView = new TextView(context);
		titleView.setText(title);
		titleView.setTextSize(12f);
		titleView.setPadding(1, 1, 1, 1);
		titleView.setGravity(Gravity.CENTER_HORIZONTAL);
		titleView.setTextColor(Color.WHITE);
		addView(titleView);

	}

}




