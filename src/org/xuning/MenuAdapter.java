package org.xuning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MenuAdapter extends BaseAdapter{
	Context context;
	List<Map<String,Object>> listItems;
	
	LayoutInflater lif;
	
	private static final int MSG_SUCCESS = 0;//获取成功的标识  
	private static final int MSG_FAILURE = 1;//获取失败的标识 
	private Thread mThread;
	int pos;
	
	public MenuAdapter(Context context,List<Map<String,Object>> listItems)
	{
		this.context=context;
		this.listItems=listItems;
	}
	
	@Override
	public int getCount()
	{
		return listItems.size();
	}
	
	@Override
	public Object getItem(int position)
	{
		//return null;
		//return position;
		return listItems.get(position);
	}
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	@Override
	public View getView(int position,View convertView,ViewGroup parent)
	{
		MyView mv;
		
		if(convertView==null)
		{
			mv = new MyView();
			lif=LayoutInflater.from(context);
			convertView = lif.inflate(R.layout.menugridlayout, null);
			mv.menu_grid_name = (TextView)convertView.findViewById(R.id.menu_grid_name);
			mv.menu_grid_price = (TextView)convertView.findViewById(R.id.menu_grid_price);
			mv.menu_grid_pic = (ImageView)convertView.findViewById(R.id.menu_grid_pic);
			mv.menu_grid_star = (RatingBar)convertView.findViewById(R.id.menu_grid_star);
			mv.menu_grid_check = (CheckBox)convertView.findViewById(R.id.menu_grid_check);
			convertView.setTag(mv);
		}
		else
		{
			mv=(MyView)convertView.getTag();
		}
		

		System.out.println("!!图片路径"+(String)listItems.get(position).get("pic"));
		System.out.println("菜名"+(String)listItems.get(position).get("name"));
		mv.menu_grid_pic.setImageBitmap(BitmapFactory.decodeFile((String)listItems.get(position).get("pic")));
		//mv.menu_grid_pic.setImageResource(R.drawable.cai_1);
		mv.menu_grid_name.setText((String)listItems.get(position).get("name"));
		mv.menu_grid_price.setText((Float)listItems.get(position).get("price")+"元");
		mv.menu_grid_star.setRating((Float)listItems.get(position).get("star"));
		
		if((Integer)listItems.get(position).get("check")==0)
			mv.menu_grid_check.setChecked(false);
		else
			mv.menu_grid_check.setChecked(true);
		return convertView;
		
		
	}
	

	
	private class MyView{
		ImageView menu_grid_pic;
		TextView menu_grid_name,menu_grid_price;
		CheckBox menu_grid_check;
		RatingBar menu_grid_star;
		
	}
	
	
	
}
