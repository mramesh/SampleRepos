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
import com.oonusave.coupon.model.SubCategory;
import com.oonusave.coupon.util.Constants;
/**
 * 
 * @author Ramesh
 *
 */
public class SubCategoryAdaptor extends BaseAdapter{
	
	private Context context;
	private List<SubCategory> subCategoryList;

	SubCategoryAdaptor(Context context,List<SubCategory> subCategoryList) {
		//Log.i(Constants.TAG,"# CategoryAdaptor # ------ constructor() ----- ");
		this.context = context;
		this.subCategoryList = subCategoryList;
	}
	
	public void add(SubCategory subCategory) {
		subCategoryList.add(subCategory);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return subCategoryList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return subCategoryList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		// TODO Auto-generated method stub
		//Log.i(Constants.TAG,"# CategoryAdaptor # ------ getView() ----- ");
		SubCategory subCategory = subCategoryList.get(position);
		
		v = LayoutInflater.from(context).inflate(R.layout.category_item, null);
		
		ImageView imView = (ImageView) v.findViewById(R.id.catImage);
		imView.setVisibility(View.GONE);
		
		TextView nameText = (TextView) v.findViewById(R.id.categoryNameTextView);
		nameText.setText(subCategory.getSubCategoryName());
		
		return v;
		//return new SubCategoryAdaptorView(this.context, subCategory );

	}
}


class SubCategoryAdaptorView extends LinearLayout {

	Context context;
	TextView categoryNameText;

	public SubCategoryAdaptorView(Context context,SubCategory category) {
		super(context);
		this.context = context;

		//Log.i(Constants.TAG,"# CouponAdaptorView # ------ constructor() ----- ");
		// TODO Auto-generated constructor stub
		this.setOrientation(HORIZONTAL);
		setBackgroundResource(R.drawable.category_bg);

		LinearLayout.LayoutParams textParams = 
			new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		textParams.setMargins(1, 1, 1,1);
		TextView subCategoryName = new TextView( context );
		subCategoryName.setText( category.getSubCategoryName() );
		subCategoryName.setTextSize(15f);
		subCategoryName.setTextColor(Color.BLACK);
		subCategoryName.setPadding(5, 10, 10, 5);
		
		addView(subCategoryName,textParams );
	}
}
