package org.xuning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.and.netease.utils.MoveBg;

import com.genius.scroll.MyScrollLayout;
import com.genius.scroll.OnViewChangeListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class MenuActivity extends Activity{
	RelativeLayout layout;
	TextView front;
	
	TextView menu_cold;
	TextView menu_home;
	TextView menu_special;
	TextView menu_soup;
	TextView menu_noodle;
	TextView menu_drink;
	
	Button btn1;
	
	int startX;
	int avg_width;
	
	//标记
	int changefrom;
	int changeto;
	
	//菜谱
	GridView grid0,grid1,grid2,grid3,grid4,grid5;
	List<Map<String,Object>> listItems0,listItems1,listItems2,listItems3,listItems4,listItems5;
	private MenuAdapter gridAdapter0, gridAdapter1,gridAdapter2,gridAdapter3,gridAdapter4,gridAdapter5;
	
	private static final int MSG_SUCCESS = 0;//获取成功的标识  
	private static final int MSG_FAILURE = 1;//获取失败的标识 
	private ProgressDialog progressDialog = null;
	private Thread mThread;
	
	//此处buy_id到时候要获取
	public static int buy_id=1001;
	BuylistAdapter buylistadapter;
	ListView buylistlist;
	List<Map<String,Object>> listItemsBuy;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menulayout);
		
		
		grid0=(GridView)findViewById(R.id.menugrid0);
		grid1=(GridView)findViewById(R.id.menugrid1);
		grid2=(GridView)findViewById(R.id.menugrid2);
		grid3=(GridView)findViewById(R.id.menugrid3);
		grid4=(GridView)findViewById(R.id.menugrid4);
		grid5=(GridView)findViewById(R.id.menugrid5);
		
		grid0.setVisibility(View.VISIBLE);
		grid1.setVisibility(View.INVISIBLE);
		grid2.setVisibility(View.INVISIBLE);
		grid3.setVisibility(View.INVISIBLE);
		grid4.setVisibility(View.INVISIBLE);
		grid5.setVisibility(View.INVISIBLE);
		
		grid0.setOnItemClickListener(new grid_item_listener());
		grid1.setOnItemClickListener(new grid_item_listener());
		grid2.setOnItemClickListener(new grid_item_listener());
		grid3.setOnItemClickListener(new grid_item_listener());
		grid4.setOnItemClickListener(new grid_item_listener());
		grid5.setOnItemClickListener(new grid_item_listener());
		
		grid0.setOnItemLongClickListener(new grid_item_long_listener());
		grid1.setOnItemLongClickListener(new grid_item_long_listener());
		grid2.setOnItemLongClickListener(new grid_item_long_listener());
		grid3.setOnItemLongClickListener(new grid_item_long_listener());
		grid4.setOnItemLongClickListener(new grid_item_long_listener());
		grid5.setOnItemLongClickListener(new grid_item_long_listener());
		
		
		btn1=(Button)findViewById(R.id.menu_btn_buy);
		btn1.setOnClickListener(new btn1clicklistener());
		
		mThread = new Thread(runnable);
		progressDialog = ProgressDialog.show(MenuActivity.this, "请稍等...", "读取数据中...", true);
		mThread.start();
		
		initViews();
		
	}
	//查看购买列表的btn1的单击事件
	class btn1clicklistener implements View.OnClickListener
	{

		@Override
		public void onClick(View arg0) {
			
			Intent intent = new Intent(MenuActivity.this,BuyListDialogActivity.class);
			startActivity(intent);
			
			
		}

		
		
	}
	//Grid的单击事件
	class grid_item_listener implements AdapterView.OnItemClickListener
	{

		@SuppressWarnings("unchecked")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			int cai_id;
			
			String sql;
			Map<String,Object> map = null;
			
			System.out.println("!!changefrom = "+changefrom);
			switch(changefrom)
			{
			case 0:map=( Map<String,Object>)MenuActivity.this.gridAdapter0.getItem(position);break;
			case 1:map=( Map<String,Object>)MenuActivity.this.gridAdapter1.getItem(position);break;
			case 2:map=( Map<String,Object>)MenuActivity.this.gridAdapter2.getItem(position);break;
			case 3:map=( Map<String,Object>)MenuActivity.this.gridAdapter3.getItem(position);break;
			case 4:map=( Map<String,Object>)MenuActivity.this.gridAdapter4.getItem(position);break;
			case 5:map=( Map<String,Object>)MenuActivity.this.gridAdapter5.getItem(position);break;
			default:break;
			}
			if((Integer)map.get("check")==0)
			{
				map.put("check", 1);
				
				cai_id = (Integer)map.get("id");
				sql="insert into buy values("+buy_id+","+cai_id+",1,'null')";
				System.out.println(sql);
				LoadUtil.insert(sql);
			}
			else
			{
				map.put("check", 0);
				
				cai_id = (Integer)map.get("id");
				sql="delete from buy where buy_id = "+buy_id+" and cai_id = "+cai_id+"";
				System.out.println(sql);
				LoadUtil.delete(sql);
				
			}
			switch(changefrom)
			{
			case 0:grid0.setAdapter(gridAdapter0);break;
			case 1:grid1.setAdapter(gridAdapter1);break;
			case 2:grid2.setAdapter(gridAdapter2);break;
			case 3:grid3.setAdapter(gridAdapter3);break;
			case 4:grid4.setAdapter(gridAdapter4);break;
			case 5:grid5.setAdapter(gridAdapter5);break;
			default:break;
			}	
			//map.clear();
		}	
	}
	
	
	//Grid的长按事件
	class grid_item_long_listener implements AdapterView.OnItemLongClickListener
	{
		@SuppressWarnings("unchecked")
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			
			Map<String,Object> map = null;
			switch(changefrom)
			{
			case 0:map=( Map<String,Object>)MenuActivity.this.gridAdapter0.getItem(position);break;
			case 1:map=( Map<String,Object>)MenuActivity.this.gridAdapter1.getItem(position);break;
			case 2:map=( Map<String,Object>)MenuActivity.this.gridAdapter2.getItem(position);break;
			case 3:map=( Map<String,Object>)MenuActivity.this.gridAdapter3.getItem(position);break;
			case 4:map=( Map<String,Object>)MenuActivity.this.gridAdapter4.getItem(position);break;
			case 5:map=( Map<String,Object>)MenuActivity.this.gridAdapter5.getItem(position);break;
			default:break;
			}
			Bundle data = new Bundle();
			data.putInt("id", (Integer)map.get("id"));
			data.putString("name", (String)map.get("name"));
			data.putFloat("price", (Float)map.get("price"));
			data.putFloat("star", (Float)map.get("star"));
			data.putString("info", (String)map.get("info"));
			data.putString("pic", (String)map.get("pic"));
			data.putString("yingyang1",(String)map.get("yingyang1"));
			data.putString("yingyang2",(String)map.get("yingyang2"));
			data.putString("yingyang3",(String)map.get("yingyang3"));
			data.putString("yingyang4",(String)map.get("yingyang4"));
			
			Intent intent = new Intent(MenuActivity.this,InfoDialogActivity.class);
			intent.putExtras(data);
			startActivity(intent);
			return false;
		}
		
	}
	//Grid的显示
	private Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
			case MSG_FAILURE:
				Toast.makeText(getApplication(), "读取信息失败，请检查数据完整性", Toast.LENGTH_SHORT).show();
				break;
			case MSG_SUCCESS:
				grid0=(GridView)findViewById(R.id.menugrid0);
				grid1=(GridView)findViewById(R.id.menugrid1);
				grid2=(GridView)findViewById(R.id.menugrid2);
				grid3=(GridView)findViewById(R.id.menugrid3);
				grid4=(GridView)findViewById(R.id.menugrid4);
				grid5=(GridView)findViewById(R.id.menugrid5);
				
				if(listItems0.isEmpty()!=true)
				{
					gridAdapter0 = new MenuAdapter(MenuActivity.this,listItems0);
					grid0.setAdapter(gridAdapter0);
				}
				if(listItems1.isEmpty()!=true)
				{
					System.out.println("外部图片路径"+(String)listItems1.get(0).get("pic"));
					System.out.println("外部菜名"+(String)listItems1.get(0).get("name"));
					gridAdapter1 = new MenuAdapter(MenuActivity.this,listItems1);
					grid1.setAdapter(gridAdapter1);
					System.out.println("执行！！");
				}
				if(listItems2.isEmpty()!=true)
				{
					gridAdapter2 = new MenuAdapter(MenuActivity.this,listItems2);
					grid2.setAdapter(gridAdapter2);
				}
				if(listItems3.isEmpty()!=true)
				{
					gridAdapter3 = new MenuAdapter(MenuActivity.this,listItems3);
					grid3.setAdapter(gridAdapter3);
				}
				if(listItems4.isEmpty()!=true)
				{
					gridAdapter4 = new MenuAdapter(MenuActivity.this,listItems4);
					grid4.setAdapter(gridAdapter4);
				}
				if(listItems5.isEmpty()!=true)
				{
					gridAdapter5 = new MenuAdapter(MenuActivity.this,listItems5);
					grid5.setAdapter(gridAdapter5);
				}
				System.out.println("0:"+grid0.getCount());
				System.out.println("1:"+grid1.getCount());
				System.out.println("2:"+grid2.getCount());
				System.out.println("3:"+grid3.getCount());
				System.out.println("4:"+grid4.getCount());
				System.out.println("5:"+grid5.getCount());
				
				grid0.setVisibility(View.INVISIBLE);
				grid0.setVisibility(View.VISIBLE);

				progressDialog.dismiss();
				break;
			}
		}
	};
	
	//从数据库中读取菜单
	Runnable runnable = new Runnable()
	{
		@Override
		public void run()
		{
			listItems0=new ArrayList<Map<String,Object>>();
			listItems1=new ArrayList<Map<String,Object>>();
			listItems2=new ArrayList<Map<String,Object>>();
			listItems3=new ArrayList<Map<String,Object>>();
			listItems4=new ArrayList<Map<String,Object>>();
			listItems5=new ArrayList<Map<String,Object>>();
			
			listItems0=LoadUtil.getkind0();
			listItems1=LoadUtil.getkind1();
			listItems2=LoadUtil.getkind2();
			listItems3=LoadUtil.getkind3();
			listItems4=LoadUtil.getkind4();
			listItems5=LoadUtil.getkind5();
			
			if(listItems0.isEmpty()==true)
				System.out.println("listItems0空");
			if(listItems1.isEmpty()==true)
				System.out.println("listItems1空");
			if(listItems2.isEmpty()==true)
				System.out.println("listItems2空");
			if(listItems3.isEmpty()==true)
				System.out.println("listItems3空");
			if(listItems4.isEmpty()==true)
				System.out.println("listItems4空");
			if(listItems5.isEmpty()==true)
				System.out.println("listItems5空");
			
			
			mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
		}
	};

	

	//界面的初始化
	private void initViews()
	{
		layout = (RelativeLayout) findViewById(R.id.layout_title_bar);
		
		menu_cold=(TextView)findViewById(R.id.menu_cold);
		menu_home=(TextView)findViewById(R.id.menu_home);
		menu_special=(TextView)findViewById(R.id.menu_special);
		menu_soup=(TextView)findViewById(R.id.menu_soup);
		menu_noodle=(TextView)findViewById(R.id.menu_noodle);
		menu_drink=(TextView)findViewById(R.id.menu_drink);
		
		grid0=(GridView)findViewById(R.id.menugrid0);
		grid1=(GridView)findViewById(R.id.menugrid1);
		grid2=(GridView)findViewById(R.id.menugrid2);
		grid3=(GridView)findViewById(R.id.menugrid3);
		grid4=(GridView)findViewById(R.id.menugrid4);
		grid5=(GridView)findViewById(R.id.menugrid5);
		
		menu_cold.setOnClickListener(onClickListener);
		menu_home.setOnClickListener(onClickListener);
		menu_special.setOnClickListener(onClickListener);
		menu_soup.setOnClickListener(onClickListener);
		menu_noodle.setOnClickListener(onClickListener);
		menu_drink.setOnClickListener(onClickListener);
		
		front=new TextView(this);
		front.setBackgroundResource(R.drawable.slidebar);
		front.setTextColor(Color.WHITE);
		front.setText("凉菜");
		front.setTextSize(20);
		front.setGravity(Gravity.CENTER);
		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		layout.addView(front, param);
		MoveBg.moveFrontBg(front, 35, 35, 0, 0);
		
		//设置现在界面
		changefrom=0;
	}
	
	//菜单选择变化后界面的动画与变化
	private Handler m = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
			case MSG_FAILURE:break;
			case MSG_SUCCESS:
				switch(changefrom)
				{
				case 0:grid0.setVisibility(View.INVISIBLE);break;
				case 1:grid1.setVisibility(View.INVISIBLE);break;
				case 2:grid2.setVisibility(View.INVISIBLE);break;
				case 3:grid3.setVisibility(View.INVISIBLE);break;
				case 4:grid4.setVisibility(View.INVISIBLE);break;
				case 5:grid5.setVisibility(View.INVISIBLE);break;
				}
				switch(changeto)
				{
				case 0:grid0.setVisibility(View.VISIBLE);
						MoveBg.moveFrontBg(front, startX, 35, 0, 0);
						startX=35;
						front.setText("凉菜");break;
				case 1:grid1.setVisibility(View.VISIBLE);
						MoveBg.moveFrontBg(front, startX, 35+avg_width, 0, 0);
						startX=35+avg_width;
						front.setText("家常菜");break;
				case 2:grid2.setVisibility(View.VISIBLE);
						MoveBg.moveFrontBg(front, startX, 35+avg_width*2, 0, 0);
						startX=35+avg_width*2;
						front.setText("特色菜");break;
				case 3:grid3.setVisibility(View.VISIBLE);
						MoveBg.moveFrontBg(front, startX,35+ avg_width*3, 0, 0);
						startX=35+avg_width*3;
						front.setText("汤、粥");break;
				case 4:grid4.setVisibility(View.VISIBLE);
						MoveBg.moveFrontBg(front, startX, 35+avg_width*4, 0, 0);
						startX=35+avg_width*4;
						front.setText("面、米");break;
				case 5:grid5.setVisibility(View.VISIBLE);
						MoveBg.moveFrontBg(front, startX,35+ avg_width*5, 0, 0);
						startX=35+avg_width*5;
						front.setText("饮品");break;
				}
				changefrom=changeto;
				}
		}
	};
	
	//菜单选择的run函数
	Runnable r = new Runnable()
	{
		@Override
		public void run()
		{
			m.obtainMessage(MSG_SUCCESS).sendToTarget();
		}

	};
	
	//菜单选择的单击函数
	private OnClickListener onClickListener = new OnClickListener() {
		
		
		public void onClick(View v)
		{
			
			
			avg_width = findViewById(R.id.layout).getWidth();
			switch(v.getId())
			{
			case R.id.menu_cold:
				/*MoveBg.moveFrontBg(front, startX, 35, 0, 0);
				startX=35;
				front.setText("凉菜");*/
				changeto=0;
				break;
			case R.id.menu_home:
				/*MoveBg.moveFrontBg(front, startX, 35+avg_width, 0, 0);
				startX=35+avg_width;
				front.setText("家常菜");*/
				changeto=1;
				break;
			case R.id.menu_special:
				/*MoveBg.moveFrontBg(front, startX, 35+avg_width*2, 0, 0);
				startX=35+avg_width*2;
				front.setText("特色菜");*/
				changeto=2;
				break;
			case R.id.menu_soup:
				/*MoveBg.moveFrontBg(front, startX,35+ avg_width*3, 0, 0);
				startX=35+avg_width*3;
				front.setText("汤、粥");*/
				changeto=3;
				break;
			case R.id.menu_noodle:
				/*MoveBg.moveFrontBg(front, startX, 35+avg_width*4, 0, 0);
				startX=35+avg_width*4;
				front.setText("面、米");*/
				changeto=4;
				break;
			case R.id.menu_drink:
				/*MoveBg.moveFrontBg(front, startX,35+ avg_width*5, 0, 0);
				startX=35+avg_width*5;
				front.setText("饮品");*/
				changeto=5;
				break;
			default:break;
			}
			Thread t = new Thread(r);
			
			t.start();
			}
		};


		
	
}
