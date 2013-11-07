package com.oonusave.coupon;

import java.util.List;

import com.oonusave.coupon.R;
import com.oonusave.coupon.model.Competion;
import com.oonusave.coupon.model.FAQ;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 
 * @author Ramesh
 *
 */

public class CompetionAdapter extends BaseAdapter{

	private Context context;
	private List<Competion> competionList;
	
	
	CompetionAdapter(Context context,List<Competion> competionList) {
		//Log.i(Constants.TAG,"# FAQAdapter # ------ constructor() ----- ");
		this.context = context;
		this.competionList = competionList;
	}
	
	public void add(Competion competion) {
		this.competionList.add(competion);
	}
	
	
	@Override
	public int getCount() {
		return competionList.size();
	}

	@Override
	public Object getItem(int pos) {
		return competionList.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int position, View v, ViewGroup vg) {
		Competion competion = competionList.get(position);
		v = LayoutInflater.from(context).inflate(R.layout.competion_item, null);
		TextView competionText = (TextView) v.findViewById(R.id.competionName);
		TextView priceText = (TextView) v.findViewById(R.id.price);
		TextView descText = (TextView) v.findViewById(R.id.desc);
		competionText.setText(competion.getComprtionName());
		priceText.setText(competion.getPrice());
		descText.setText(competion.getDescription());
		return v;
		
	}
	

}


class CompetionAdaptorView extends LinearLayout {

	Context context;
	
	public CompetionAdaptorView(Context context,FAQ faq) {
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

