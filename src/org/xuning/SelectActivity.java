package org.xuning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SelectActivity extends Activity{
	int[] imageIds = new int[]{
			R.drawable.ajax,R.drawable.css,R.drawable.css3,R.drawable.dreamweaver,
			R.drawable.drupal,R.drawable.fireworks,R.drawable.flash,
			R.drawable.html,R.drawable.html5,R.drawable.javascript
	};
	GridView grid0;
	List<Map<String,Object>> listItems0;
	private MenuAdapter gridAdapter0;
	
	private static final int MSG_SUCCESS = 0;//获取成功的标识  
	private static final int MSG_FAILURE = 1;//获取失败的标识 
	private ProgressDialog progressDialog = null;
	private Thread mThread;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags
        (
        		WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );      
        //强制为竖屏
        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        //selectView myview = new selectView(this);
        //setContentView(myview);
        
        setContentView(R.layout.buylistlayout);
        /*List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i=0;i<imageIds.length;i++)
        {
        	Map<String,Object> listItem = new HashMap<String,Object>();
        	listItem.put("image", imageIds[i]);
        	listItems.add(listItem);
        }
        GridView grid = (GridView)findViewById(R.id.grid);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.piclayout,
        		new String[]{"image"},new int[]{R.id.image1});
        grid.setAdapter(simpleAdapter);*/
        
        mThread = new Thread(runnable);
		progressDialog = ProgressDialog.show(SelectActivity.this, "请稍等...", "读取数据中...", true);
		mThread.start();
	}

	private Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
			case MSG_FAILURE:
				Toast.makeText(getApplication(), "读取信息失败，请检查数据完整性", Toast.LENGTH_SHORT).show();
				progressDialog.dismiss();
				break;
			case MSG_SUCCESS:
				ListView buylistlist = (ListView)findViewById(R.id.buylistlist);
				BuylistAdapter buylistadapter = new BuylistAdapter(SelectActivity.this,listItems0);
				buylistlist.setAdapter(buylistadapter);

				progressDialog.dismiss();
				break;
			}
		}
	};
	
	Runnable runnable = new Runnable()
	{
		@Override
		public void run()
		{
			listItems0=new ArrayList<Map<String,Object>>();

			String sql = "select * from buy where buy_id = 1001";
			
			listItems0=LoadUtil.getBuyList(sql);

			//System.out.println("1菜名"+(String)listItems0.get(0).get("name"));
			//System.out.println("2菜名"+(String)listItems0.get(1).get("name"));
			//System.out.println("3菜名"+(String)listItems0.get(2).get("name"));
			//System.out.println("4菜名"+(String)listItems0.get(3).get("name"));
			if(listItems0.isEmpty()==true)
				System.out.println("listItems0空");

			
			
			mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
		}
	};
}
