package com.oonusave.coupon;

import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Category;
import com.oonusave.coupon.model.FAQ;
import com.oonusave.coupon.util.Constants;

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
/**
 * 
 * @author Ramesh
 *
 */

public class FAQAdapter extends BaseAdapter{

	private Context context;
	private List<FAQ> faqList;
	
	
	FAQAdapter(Context context,List<FAQ> faqList) {
		//Log.i(Constants.TAG,"# FAQAdapter # ------ constructor() ----- ");
		this.context = context;
		this.faqList = faqList;
	}
	
	public void add(FAQ faq) {
		this.faqList.add(faq);
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return faqList.size();
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return faqList.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public View getView(int position, View v, ViewGroup vg) {
		// TODO Auto-generated method stub
		FAQ faq = faqList.get(position);
		v = LayoutInflater.from(context).inflate(R.layout.faq_item, null);
		ImageView imView = (ImageView) v.findViewById(R.id.catImage);
		
		//imView.setVisibility(View.GONE);
		imView.setBackgroundResource(R.drawable.msg_icon);
		TextView nameText = (TextView) v.findViewById(R.id.categoryNameTextView);
		nameText.setText(faq.getQuestion());
		return v;
		//return new FAQAdaptorView(context,faq);
	}
	

}


class FAQAdaptorView extends LinearLayout {

	Context context;
	
	public FAQAdaptorView(Context context,FAQ faq) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;

		//Log.i(Constants.TAG,"# FAQAdaptorView # ------ constructor() ----- ");
		// TODO Auto-generated constructor stub
		this.setOrientation(HORIZONTAL);
		this.setBackgroundResource(R.drawable.category_bar);

		LinearLayout.LayoutParams textParams = 
			new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		TextView question = new TextView( context );
		question.setText( faq.getQuestion() );
		question.setTextSize(15f);
		question.setPadding(5, 10, 10, 5);
		//categoryName.setW
		
		question.setTextColor(Color.WHITE);
		addView(question,textParams );
		
	}
	
	
	
	
}

