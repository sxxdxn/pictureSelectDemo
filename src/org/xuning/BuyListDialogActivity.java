package org.xuning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class BuyListDialogActivity extends Activity{
	
	//int buy_id=1001;
	float whole_price;
	BuylistAdapter buylistadapter;
	ListView buylistlist;
	List<Map<String,Object>> listItemsBuy;
	
	TextView buylistsum;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buylistlayout);
		
		String sql = "select * from buy where buy_id = "+MenuActivity.buy_id;
		listItemsBuy = new ArrayList<Map<String,Object>>();
		listItemsBuy = LoadUtil.getBuyList(sql);
		
		buylistlist = (ListView)findViewById(R.id.buylistlist);
		buylistadapter = new BuylistAdapter(BuyListDialogActivity.this,listItemsBuy);
		buylistlist.setAdapter(buylistadapter);
		
		final Builder builder = new AlertDialog.Builder(this);
		
		whole_price=0;
		for(int i=0;i<listItemsBuy.size();i++)
		{
			whole_price = whole_price + (Float)listItemsBuy.get(i).get("price")*(Integer)listItemsBuy.get(i).get("num");
		}
		
		buylistsum = (TextView)findViewById(R.id.buylistsum);
		buylistsum.setText("合计共 "+whole_price+" 元");
		
		buylistlist.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				final Map<String,Object> map=( Map<String,Object>)BuyListDialogActivity.this.buylistadapter.getItem(position);
				final int cai_id = (Integer)map.get("id");
				Map<String,Object> map2 = LoadUtil.getCaiById(cai_id);
				Bundle data = new Bundle();
				data.putInt("id", (Integer)map.get("id"));
				data.putString("name", (String)map2.get("name"));
				data.putFloat("price", (Float)map2.get("price"));
				data.putFloat("star", (Float)map2.get("star"));
				data.putString("info", (String)map2.get("info"));
				data.putString("pic", (String)map2.get("pic"));
				data.putString("yingyang1",(String)map2.get("yingyang1"));
				data.putString("yingyang2",(String)map2.get("yingyang2"));
				data.putString("yingyang3",(String)map2.get("yingyang3"));
				data.putString("yingyang4",(String)map2.get("yingyang4"));
				
				Intent intent = new Intent(BuyListDialogActivity.this,InfoDialogActivity.class);
				intent.putExtras(data);
				startActivity(intent);
				
				
			}
			
		});
		buylistlist.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				final Map<String,Object> map=( Map<String,Object>)BuyListDialogActivity.this.buylistadapter.getItem(position);
				final int cai_id = (Integer)map.get("id");
				final int num = (Integer)map.get("num");
				
				builder.setTitle("选择");
				builder.setItems(new String[]{"加一份","减一份","修改数量","删除"},new OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch(which)
						{
						case 0:
							String sql0="delete from buy where buy_id = "+MenuActivity.buy_id+" and cai_id = "+cai_id+"";
							LoadUtil.delete(sql0);
							int temp=num+1;
							sql0="insert into buy values("+MenuActivity.buy_id+","+cai_id+","+ temp +",'null')";
							LoadUtil.insert(sql0);
							map.put("num", num+1);
							listItemsBuy.set(position, map);
							buylistadapter = new BuylistAdapter(BuyListDialogActivity.this,listItemsBuy);
							buylistlist.setAdapter(buylistadapter);
							
							whole_price=0;
							for(int i=0;i<listItemsBuy.size();i++)
							{
								whole_price = whole_price + (Float)listItemsBuy.get(i).get("price")*(Integer)listItemsBuy.get(i).get("num");
							}
							buylistsum.setText("合计共 "+whole_price+" 元");
							
							break;
						case 1:
							if(num==1)
							{
								String sql1="delete from buy where buy_id = "+MenuActivity.buy_id+" and cai_id = "+cai_id+"";
								LoadUtil.delete(sql1);
								listItemsBuy.remove(position);
								buylistadapter = new BuylistAdapter(BuyListDialogActivity.this,listItemsBuy);
								buylistlist.setAdapter(buylistadapter);
							}else if(num>1){
								String sql1="delete from buy where buy_id = "+MenuActivity.buy_id+" and cai_id = "+cai_id+"";
								LoadUtil.delete(sql1);
								int temp2=num-1;
								sql1="insert into buy values("+MenuActivity.buy_id+","+cai_id+","+ temp2 +",'null')";
								LoadUtil.insert(sql1);
								map.put("num", num-1);
								listItemsBuy.set(position, map);
								buylistadapter = new BuylistAdapter(BuyListDialogActivity.this,listItemsBuy);
								buylistlist.setAdapter(buylistadapter);
							}	
							whole_price=0;
							for(int i=0;i<listItemsBuy.size();i++)
							{
								whole_price = whole_price + (Float)listItemsBuy.get(i).get("price")*(Integer)listItemsBuy.get(i).get("num");
							}
							buylistsum.setText("合计共 "+whole_price+" 元");
							break;
						case 2:
							Bundle data = new Bundle();
							data.putInt("id", cai_id);
							Intent intent = new Intent(BuyListDialogActivity.this,ChangeNumActivity.class);
							intent.putExtras(data);
							startActivity(intent);
							break;
						case 3:
							String sql3="delete from buy where buy_id = "+MenuActivity.buy_id+" and cai_id = "+cai_id+"";
							LoadUtil.delete(sql3);
							listItemsBuy.remove(position);
							buylistadapter = new BuylistAdapter(BuyListDialogActivity.this,listItemsBuy);
							buylistlist.setAdapter(buylistadapter);
							whole_price=0;
							for(int i=0;i<listItemsBuy.size();i++)
							{
								whole_price = whole_price + (Float)listItemsBuy.get(i).get("price")*(Integer)listItemsBuy.get(i).get("num");
							}
							buylistsum.setText("合计共 "+whole_price+" 元");
							break;
							
						}
						
					}
					
				});
				builder.create().show();
				
				
				return true;
			}});
		
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		String sql = "select * from buy where buy_id = "+MenuActivity.buy_id;
		listItemsBuy = new ArrayList<Map<String,Object>>();
		listItemsBuy = LoadUtil.getBuyList(sql);
		
		buylistlist = (ListView)findViewById(R.id.buylistlist);
		buylistadapter = new BuylistAdapter(BuyListDialogActivity.this,listItemsBuy);
		buylistlist.setAdapter(buylistadapter);
		
		whole_price=0;
		for(int i=0;i<listItemsBuy.size();i++)
		{
			whole_price = whole_price + (Float)listItemsBuy.get(i).get("price")*(Integer)listItemsBuy.get(i).get("num");
		}
		
		buylistsum = (TextView)findViewById(R.id.buylistsum);
		buylistsum.setText("合计共 "+whole_price+" 元");
	}

}
