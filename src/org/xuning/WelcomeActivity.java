package org.xuning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.and.netease.utils.MoveBg;
import com.genius.scroll.MyScrollLayout;
import com.genius.scroll.OnViewChangeListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class WelcomeActivity extends Activity implements OnViewChangeListener,OnClickListener{

	private MyScrollLayout mScrollLayout;	
	private ImageView[] mImageViews;	
	private LinearLayout welbtnlayout;
	private int mViewCount;	
	private int mCurSel;
	Button wel_id_buy;
	Button wel_id_info;
	Button wel_btn_buy;
	Button wel_btn_like;
	Button wel_btn_rand;
	
	List<Map<String,Object>> listItems;
	
	int idnum;
	//侧边栏的显示状态
	boolean inout;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomelayout);    
        initbtn();
        init();
        
    }
	
	private void initbtn()
	{
		welbtnlayout = (LinearLayout)findViewById(R.id.wel_btn_layout);
		wel_btn_buy = (Button)findViewById(R.id.wel_btn_buy);
		wel_btn_like = (Button)findViewById(R.id.wel_btn_like);
		wel_btn_rand = (Button)findViewById(R.id.wel_btn_rand);
		wel_id_info = (Button)findViewById(R.id.wel_id_info);
		
		//MoveBg.moveFrontBg(welbtnlayout, 0, -20, 0, 0);
		inout = true;
		
		/*welbtnlayout.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View source)
			{
				if(inout==true)
				{
					wel_btn_buy.setVisibility(View.VISIBLE);
					wel_btn_like.setVisibility(View.VISIBLE);
					wel_btn_rand.setVisibility(View.VISIBLE);
					
					MoveBg.moveFrontBg(welbtnlayout, -20, 0, 0, 0);
					inout=false;
					return;
				}
				else
				{
					wel_btn_buy.setVisibility(View.INVISIBLE);
					wel_btn_like.setVisibility(View.INVISIBLE);
					wel_btn_rand.setVisibility(View.INVISIBLE);
					
					MoveBg.moveFrontBg(welbtnlayout, 0, -20, 0, 0);
					inout=true;
					return;
				}
				
			}
		});*/
	}
	private void init()
    {
    	mScrollLayout = (MyScrollLayout) findViewById(R.id.ScrollLayout); 	
    	LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llayout);   	
    	mViewCount = mScrollLayout.getChildCount();
    	mImageViews = new ImageView[mViewCount];   	
    	for(int i = 0; i < mViewCount; i++)    	{
    		mImageViews[i] = (ImageView) linearLayout.getChildAt(i);
    		mImageViews[i].setEnabled(true);
    		mImageViews[i].setOnClickListener(this);
    		mImageViews[i].setTag(i);
    	}    	
    	mCurSel = 0;
    	mImageViews[mCurSel].setEnabled(false);    	
    	mScrollLayout.SetOnViewChangeListener(this);
    	idnum=mCurSel;
    	
    	wel_id_buy = (Button)findViewById(R.id.wel_id_buy);
    	wel_id_buy.setOnClickListener(new btn_welidbuyOnClickListener());
    	wel_id_info.setOnClickListener(new btn_welidinfoOnClickListener());
    	wel_btn_buy.setOnClickListener(new btn1clicklistener());
    	
    	listItems = new ArrayList<Map<String,Object>>();
    	listItems = LoadUtil.getad();
    	
    }
	
	//查看购买列表的btn1的单击事件
	class btn1clicklistener implements View.OnClickListener
	{

		@Override
		public void onClick(View arg0) {
			
			Intent intent = new Intent(WelcomeActivity.this,BuyListDialogActivity.class);
			startActivity(intent);
			
			
		}

		
		
	}
	
	class btn_welidbuyOnClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View arg0)
		{
			Toast.makeText(getApplication(),"购买的是第"+idnum+"幅广告", Toast.LENGTH_LONG).show();
			//mScrollLayout.snapToScreen(idnum+1);
			Map<String,Object> map = null;
			map=( Map<String,Object>)listItems.get(idnum);
			int cai_id = (Integer)map.get("id");
			String sql="insert into buy values("+MenuActivity.buy_id+","+cai_id+",1,'null')";
			System.out.println(sql);
			LoadUtil.insert(sql);
		}
	}
	
	class btn_welidinfoOnClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View arg0)
		{
			Toast.makeText(getApplication(),"查询的是第"+idnum+"幅广告", Toast.LENGTH_LONG).show();
			Map<String,Object> map = null;
			map=( Map<String,Object>)listItems.get(idnum);
			
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
			
			Intent intent = new Intent(WelcomeActivity.this,InfoDialogActivity.class);
			intent.putExtras(data);
			startActivity(intent);
			//mScrollLayout.snapToScreen(idnum+1);
		}
	}
	
	private void setCurPoint(int index)
    {
    	if (index < 0 || index > mViewCount - 1 || mCurSel == index)    	{
    		return ;
    	}    	
    	mImageViews[mCurSel].setEnabled(true);
    	mImageViews[index].setEnabled(false);    	
    	mCurSel = index;
    	idnum=index;
    }

    @Override
	public void OnViewChange(int view) {
		// TODO Auto-generated method stub
		setCurPoint(view);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int pos = (Integer)(v.getTag());
		setCurPoint(pos);
		mScrollLayout.snapToScreen(pos);
	}
	
}
