package org.xuning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xuning.MenuActivity.grid_item_listener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SpecialActivity extends Activity{
	
	TextView YY1Text;
	TextView YY2Text;
	TextView YY3Text;
	TextView YY4Text;
	RadioGroup YY1Group;
	RadioGroup YY2Group;
	RadioGroup YY3Group;
	RadioGroup YY4Group;
	RadioButton YY1none;
	RadioButton YY1little;
	RadioButton YY1middle;
	RadioButton YY1large;
	RadioButton YY2none;
	RadioButton YY2little;
	RadioButton YY2middle;
	RadioButton YY2large;
	RadioButton YY3none;
	RadioButton YY3little;
	RadioButton YY3middle;
	RadioButton YY3large;
	RadioButton YY4none;
	RadioButton YY4little;
	RadioButton YY4middle;
	RadioButton YY4large;
	Button btn;
	Button spe_buy;
	GridView gridview;
	boolean yy_1,yy_2,yy_3,yy_4;
	int yy1,yy2,yy3,yy4;
	List<Map<String,Object>> listItems;
	private MenuAdapter gridAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speciallayout);
		
		init();
		
		YY1Text.setOnClickListener(onClickListener);
		YY2Text.setOnClickListener(onClickListener);
		YY3Text.setOnClickListener(onClickListener);
		YY4Text.setOnClickListener(onClickListener);
		btn.setOnClickListener(onClickListener);
		spe_buy.setOnClickListener(onClickListener);
		gridview.setOnItemClickListener(new grid_item_listener());
		gridview.setOnItemLongClickListener(new grid_item_long_listener());
		
		
	}

	void init()
	{
		YY1Text = (TextView)findViewById(R.id.special_YY1Text);
		YY2Text = (TextView)findViewById(R.id.special_YY2Text);
		YY3Text = (TextView)findViewById(R.id.special_YY3Text);
		YY4Text = (TextView)findViewById(R.id.special_YY4Text);
		YY1Group = (RadioGroup)findViewById(R.id.special_YY1Group);
		YY2Group = (RadioGroup)findViewById(R.id.special_YY2Group);
		YY3Group = (RadioGroup)findViewById(R.id.special_YY3Group);
		YY4Group = (RadioGroup)findViewById(R.id.special_YY4Group);
		YY1none = (RadioButton)findViewById(R.id.yy1_none);
		YY2none = (RadioButton)findViewById(R.id.yy2_none);
		YY3none = (RadioButton)findViewById(R.id.yy3_none);
		YY4none = (RadioButton)findViewById(R.id.yy4_none);
		YY1little = (RadioButton)findViewById(R.id.yy1_little);
		YY2little = (RadioButton)findViewById(R.id.yy2_little);
		YY3little = (RadioButton)findViewById(R.id.yy3_little);
		YY4little = (RadioButton)findViewById(R.id.yy4_little);
		YY1middle = (RadioButton)findViewById(R.id.yy1_middle);
		YY2middle = (RadioButton)findViewById(R.id.yy2_middle);
		YY3middle = (RadioButton)findViewById(R.id.yy3_middle);
		YY4middle = (RadioButton)findViewById(R.id.yy4_middle);
		YY1large = (RadioButton)findViewById(R.id.yy1_large);
		YY2large = (RadioButton)findViewById(R.id.yy2_large);
		YY3large = (RadioButton)findViewById(R.id.yy3_large);
		YY4large = (RadioButton)findViewById(R.id.yy4_large);
		gridview = (GridView)findViewById(R.id.special_grid);
		
		
		btn = (Button)findViewById(R.id.special_btn);
		spe_buy = (Button)findViewById(R.id.spe_btn_buy);
		
		yy_1 = false;
		yy_3 = false;
		yy_2 = false;
		yy_4 = false;
	}
	
	class grid_item_listener implements AdapterView.OnItemClickListener
	{

		@SuppressWarnings("unchecked")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			int cai_id;
			
			String sql;
			Map<String,Object> map =( Map<String,Object>)SpecialActivity.this.gridAdapter.getItem(position);
			
			if((Integer)map.get("check")==0)
			{
				map.put("check", 1);
				
				cai_id = (Integer)map.get("id");
				sql="insert into buy values("+MenuActivity.buy_id+","+cai_id+",1,'null')";
				System.out.println(sql);
				LoadUtil.insert(sql);
			}
			else
			{
				map.put("check", 0);
				
				cai_id = (Integer)map.get("id");
				sql="delete from buy where buy_id = "+MenuActivity.buy_id+" and cai_id = "+cai_id+"";
				System.out.println(sql);
				LoadUtil.delete(sql);
				
			}
			gridview.setAdapter(gridAdapter);
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
				
				map=( Map<String,Object>)SpecialActivity.this.gridAdapter.getItem(position);
				
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
				
				Intent intent = new Intent(SpecialActivity.this,InfoDialogActivity.class);
				intent.putExtras(data);
				startActivity(intent);
				return false;
			}
			
		}
		
	
	private OnClickListener onClickListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			switch(v.getId())
			{
			case R.id.special_YY1Text:
				if(yy_1==false)
				{
					YY1Group.setVisibility(View.VISIBLE);
					yy_1=true;
				}
				else
				{
					YY1Group.setVisibility(View.GONE);
					YY1Group.clearCheck();
					yy_1=false;
				}
				break;
			case R.id.special_YY2Text:
				if(yy_2==false)
				{
					YY2Group.setVisibility(View.VISIBLE);
					yy_2=true;
				}
				else
				{
					YY2Group.setVisibility(View.GONE);
					YY2Group.clearCheck();
					yy_2=false;
				}
				break;
			case R.id.special_YY3Text:
				if(yy_3==false)
				{
					YY3Group.setVisibility(View.VISIBLE);
					yy_3=true;
				}
				else
				{
					YY3Group.setVisibility(View.GONE);
					YY3Group.clearCheck();
					yy_3=false;
				}
				break;
			case R.id.special_YY4Text:
				if(yy_4==false)
				{
					YY4Group.setVisibility(View.VISIBLE);
					yy_4=true;
				}
				else
				{
					YY4Group.setVisibility(View.GONE);
					YY4Group.clearCheck();
					yy_4=false;
				}
				break;
			case R.id.special_btn:
				if(YY1none.isChecked()==true)
					yy1 = 0;
				else if(YY1little.isChecked()==true)
					yy1 = 1;
				else if(YY1middle.isChecked()==true)
					yy1 = 2;
				else if(YY1large.isChecked()==true)
					yy1 = 3;
				else
					yy1 = -1;
				
				if(YY2none.isChecked()==true)
					yy2 = 0;
				else if(YY2little.isChecked()==true)
					yy2 = 1;
				else if(YY2middle.isChecked()==true)
					yy2 = 2;
				else if(YY2large.isChecked()==true)
					yy2 = 3;
				else
					yy2 = -1;
				if(YY3none.isChecked()==true)
					yy3 = 0;
				else if(YY3little.isChecked()==true)
					yy3 = 1;
				else if(YY3middle.isChecked()==true)
					yy3 = 2;
				else if(YY3large.isChecked()==true)
					yy3 = 3;
				else
					yy3 = -1;
				if(YY4none.isChecked()==true)
					yy4 = 0;
				else if(YY4little.isChecked()==true)
					yy4 = 1;
				else if(YY4middle.isChecked()==true)
					yy4 = 2;
				else if(YY4large.isChecked()==true)
					yy4 = 3;
				else
					yy4 = -1;
				
				listItems=new ArrayList<Map<String,Object>>();
				if(yy1==yy2&&yy2==yy3&&yy3==yy4&&yy4==-1)
				{
					Toast.makeText(getApplication(),"请选择条件", Toast.LENGTH_LONG).show();  
				}else{
					listItems=LoadUtil.getyy(yy1, yy2, yy3, yy4);
				}
					
				if(listItems.isEmpty()==true)
				{
					Toast.makeText(getApplication(),"没有查找到符合所选条件的菜，请重新选择", Toast.LENGTH_LONG).show();  
					//gridview.setVisibility(View.INVISIBLE);
				}else{
					gridAdapter = new MenuAdapter(SpecialActivity.this,listItems);
					gridview.setAdapter(gridAdapter);
					//gridview.setVisibility(View.VISIBLE);
				}
					
				break;
			case R.id.spe_btn_buy:
				Intent intent = new Intent(SpecialActivity.this,BuyListDialogActivity.class);
				startActivity(intent);
				break;
			}
		}
		
	};
}
