package org.xuning;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class InfoDialogActivity extends Activity{

	ImageView infoImage;
	TextView info_name;
	TextView info_price;
	TextView info_info;
	TextView info_yingyang1;
	TextView info_yingyang2;
	TextView info_yingyang3;
	TextView info_yingyang4;
	RatingBar info_star;
	Button info_buy;
	
	int id;
	String pic,name,info,yingyang1,yingyang2,yingyang3,yingyang4;
	Float price,star;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infodialoglayout);
		
		infoImage = (ImageView)findViewById(R.id.info_Image);
		info_name = (TextView)findViewById(R.id.info_cai_name);
		info_price = (TextView)findViewById(R.id.info_cai_price);
		info_star = (RatingBar)findViewById(R.id.info_cai_star);
		info_info = (TextView)findViewById(R.id.info_cai_info);
		info_yingyang1 = (TextView)findViewById(R.id.info_yingyang1);
		info_yingyang2 = (TextView)findViewById(R.id.info_yingyang2);
		info_yingyang3 = (TextView)findViewById(R.id.info_yingyang3);
		info_yingyang4 = (TextView)findViewById(R.id.info_yingyang4);
		info_buy = (Button)findViewById(R.id.info_buy);
		Intent intent = getIntent();
		Bundle data = intent.getExtras();
		
		id = (Integer)data.getInt("id");
		pic = (String)data.getString("pic");
		name = (String)data.getString("name");
		info = (String)data.getString("info");
		price = (Float)data.getFloat("price");
		star = (Float)data.getFloat("star");
		yingyang1 = (String)data.getString("yingyang1");
		yingyang2 = (String)data.getString("yingyang2");
		yingyang3 = (String)data.getString("yingyang3");
		yingyang4 = (String)data.getString("yingyang4");
		
		info_name.setText(name);
		info_price.setText(price+"元");
		info_info.setText(info);
		info_star.setRating(star);
		info_yingyang1.setText(yingyang1);
		info_yingyang2.setText(yingyang2);
		info_yingyang3.setText(yingyang3);
		info_yingyang4.setText(yingyang4);
		infoImage.setImageBitmap(BitmapFactory.decodeFile(pic));
		
		info_buy.setOnClickListener(onClickListener);
		
	}
	
	private OnClickListener onClickListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			switch(v.getId())
			{
			case R.id.info_buy:
				String sql="insert into buy values("+MenuActivity.buy_id+","+id+",1,'null')";
				System.out.println("插入语句"+sql);
				System.out.println(sql);
				LoadUtil.insert(sql);
				Toast.makeText(getApplication(),"加入订单成功", Toast.LENGTH_SHORT).show();  
				break;
			}
		}
	};
	@Override
	protected void onResume() {
	 /**
	  * 设置为横屏
	  */
	 if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
	  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	 }
	 super.onResume();
	}
	
}
