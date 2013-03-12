package org.xuning;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BuylistAdapter extends BaseAdapter{

	Context context;
	List<Map<String,Object>> listItems;
	
	LayoutInflater lif;
	public BuylistAdapter(Context context,List<Map<String,Object>> listItems)
	{
		this.context=context;
		this.listItems=listItems;
	}
	
	@Override
	public int getCount() {
		
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		
		return listItems.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		MyView mv;
		if(convertView == null)
		{
			mv = new MyView();
			lif = LayoutInflater.from(context);
			convertView = lif.inflate(R.layout.buylistlistlayout, null);
			mv.name = (TextView)convertView.findViewById(R.id.buylistlistname);
			mv.num = (TextView)convertView.findViewById(R.id.buylistlistnum);
			mv.price = (TextView)convertView.findViewById(R.id.buylistlistprice);
			convertView.setTag(mv);
		}
		else
		{
			mv = (MyView)convertView.getTag();
		}
		
		mv.name.setText((String)listItems.get(position).get("name"));
		mv.num.setText((Integer)listItems.get(position).get("num")+"ทÝ");
		mv.price.setText((Float)listItems.get(position).get("price")+"ิช");
		
		System.out.println((String)listItems.get(position).get("name"));
		return convertView;
	}
	
	private class MyView
	{
		TextView name,price,num;
	}

}
