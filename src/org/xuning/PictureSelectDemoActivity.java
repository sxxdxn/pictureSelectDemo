package org.xuning;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.and.netease.utils.MoveBg;


public class PictureSelectDemoActivity extends TabActivity{
	TabHost tabHost;
	TabHost.TabSpec tabSpec;
	RadioGroup radioGroup;
	RelativeLayout bottom_layout;
	ImageView img;
	int startLeft;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		//隐去标题栏（应用程序的名字）  
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐去状态栏部分(电池等图标和一切修饰部分)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
		setContentView(R.layout.main);
		
		CreateDatabase.createdatabase();
		
		bottom_layout = (RelativeLayout)findViewById(R.id.layout_bottom);
		
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("welcome").setIndicator("Welcome").setContent(new Intent(this,WelcomeActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("special").setIndicator("Special").setContent(new Intent(this,SpecialActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("menu").setIndicator("Menu").setContent(new Intent(this,MenuActivity.class)));
		//tabHost.addTab(tabHost.newTabSpec("buy").setIndicator("Buy").setContent(new Intent(this,SelectActivity.class)));
		
		tabHost.addTab(tabHost.newTabSpec("buy").setIndicator("Buy").setContent(new Intent(this,BuyListDialogActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("user").setIndicator("User").setContent(new Intent(this,SelectActivity.class)));
		
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(checkedChangeListener);
        
        img = new ImageView(this);
        img.setImageResource(R.drawable.tab_front_bg);
        bottom_layout.addView(img);
	}
	
	private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group,int checkedId)
		{
			RadioButton radiobtn = (RadioButton)findViewById(R.id.radio_welcome);
			switch(checkedId){
			case R.id.radio_welcome:
				tabHost.setCurrentTabByTag("welcome");
				MoveBg.moveFrontBg(img, startLeft, 0, 0, 0);
				startLeft = 0;
				break;
			case R.id.radio_special:
				tabHost.setCurrentTabByTag("special");
				MoveBg.moveFrontBg(img, startLeft, radiobtn.getWidth(), 0, 0);
				startLeft = radiobtn.getWidth();
				break;
			case R.id.radio_menu:
				tabHost.setCurrentTabByTag("menu");
				MoveBg.moveFrontBg(img, startLeft, radiobtn.getWidth()*2, 0, 0);
				startLeft = radiobtn.getWidth() * 2;
				break;
			case R.id.radio_buy:
				tabHost.setCurrentTabByTag("buy");
				MoveBg.moveFrontBg(img, startLeft, radiobtn.getWidth()*3, 0, 0);
				startLeft = radiobtn.getWidth() * 3;
				break;
			case R.id.radio_user:
				tabHost.setCurrentTabByTag("user");
				MoveBg.moveFrontBg(img, startLeft, radiobtn.getWidth()*4, 0, 0);
				startLeft = radiobtn.getWidth() * 4;
				break;
			default:break;
			}
		}
	};
}