package com.oonusave.coupon;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Category;
import com.oonusave.coupon.model.UserDetails;
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.Utils;
/**
 * 
 * @author Ramesh
 *
 */
public class UserAdaptor extends BaseAdapter{
	
	private Context context;
	private List<UserDetails> usersList;
	private DrawableManager drawableManager = new DrawableManager();

	String str = "";
	UserAdaptor(Context context,List<UserDetails> usersList, String str) {
		//Log.i(Constants.TAG,"# CategoryAdaptor # ------ constructor() ----- ");
		this.context = context;
		this.usersList = usersList;
		this.str = str;
	}
	
	public void add(UserDetails userDetails) {
		usersList.add(userDetails);
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return usersList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return usersList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {

		UserDetails userDetails = usersList.get(position);
		v = LayoutInflater.from(context).inflate(R.layout.user_item, null);
		
		TextView nameText = (TextView) v.findViewById(R.id.userNameTextView);
		nameText.setText(userDetails.getFirstName() );
		
		
		TextView descText = (TextView) v.findViewById(R.id.descTextView);
		descText.setText(str);
		
		
		TextView countText = (TextView) v.findViewById(R.id.couponCountTextView);
		countText.setText( " " + userDetails.getAge()+" Coupons" );
			
		
		
		
		ImageView catImage = (ImageView) v.findViewById(R.id.userImage);
		catImage.setBackgroundResource(R.drawable.compitator_profilebg);
		
		drawableManager.fetchDrawableOnThread(userDetails.getHowToKnow(), catImage);
				

		return v;


	}
}


