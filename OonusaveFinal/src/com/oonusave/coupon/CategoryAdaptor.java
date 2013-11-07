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
import com.oonusave.coupon.util.Constants;
import com.oonusave.coupon.util.Utils;
/**
 * 
 * @author Ramesh
 *
 */
public class CategoryAdaptor extends BaseAdapter{
	
	private Context context;
	private List<Category> categoryList;
	private DrawableManager drawableManager = new DrawableManager();

	CategoryAdaptor(Context context,List<Category> categoryList) {
		//Log.i(Constants.TAG,"# CategoryAdaptor # ------ constructor() ----- ");
		this.context = context;
		this.categoryList = categoryList;
	}
	
	public void add(Category category) {
		categoryList.add(category);
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return categoryList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return categoryList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		// TODO Auto-generated method stub
		//if(v == null) {
		Category category = categoryList.get(position);
		v = LayoutInflater.from(context).inflate(R.layout.category_item, null);
		TextView nameText = (TextView) v.findViewById(R.id.categoryNameTextView);
		nameText.setText(category.getCategoryName().length() > 35 ? category.getCategoryName().substring(0,35): category.getCategoryName() );
		
		ImageView catImage = (ImageView) v.findViewById(R.id.catImage);
		drawableManager.fetchDrawableOnThread(category.getImagePath(), catImage);
				
		//}
		return v;
		//return new CategoryAdaptorView(this.context, category );

	}
}


class CategoryAdaptorView extends LinearLayout {

	Context context;
	TextView categoryNameText;

	public CategoryAdaptorView(Context context,Category category) {
		super(context);
		this.context = context;

		//Log.i(Constants.TAG,"# CouponAdaptorView # ------ constructor() ----- ");
		// TODO Auto-generated constructor stub
		this.setOrientation(HORIZONTAL);
		this.setBackgroundResource(R.drawable.category_bar);

		LinearLayout.LayoutParams textParams = 
			new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		

		
		TextView categoryName = new TextView( context );
		categoryName.setText( category.getCategoryName() );
		categoryName.setTextSize((int)Utils.convertDipToPixel(16, context));
		categoryName.setPadding(5, 10, 10, 5);
		//categoryName.setW
		
		categoryName.setTextColor(Color.WHITE);
		addView(categoryName,textParams );
	}
}
