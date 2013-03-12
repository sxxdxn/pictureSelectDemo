package org.xuning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeNumActivity extends Activity{

	Button plus;
	Button minus;
	Button submit;
	EditText edit;
	
	int num,numTemp;
	int id;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changenum);    
        
        init();
        
        plus.setOnClickListener(onClickListener);
        minus.setOnClickListener(onClickListener);
        submit.setOnClickListener(onClickListener);
	
	}
	
	private OnClickListener onClickListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			switch(v.getId())
			{
			case R.id.changenum_minus:
				if(num>0)
				{
					numTemp = num-1;
					edit.setText(numTemp+"");
					num = numTemp;
				}
				else
				{
					edit.setText(0);
					num=0;
				}
				break;
			case R.id.changenum_plus:
				if(num<0)
				{
					num=0;
					edit.setText(0);
				}
				else
				{
					numTemp = num+1;
					edit.setText(numTemp+"");
					num = numTemp;
				}
				break;
			case R.id.changenum_submit:
				String sql1="delete from buy where buy_id = "+MenuActivity.buy_id+" and cai_id = "+id+"";
				LoadUtil.delete(sql1);
				num = Integer.parseInt(edit.getText().toString());
				if(num>0)
				{
					sql1="insert into buy values("+MenuActivity.buy_id+","+id+","+ num +",'null')";
					LoadUtil.insert(sql1);
				}else if(num<0){
					Toast.makeText(getApplication(),"数量不能小于0，请重新输入！", Toast.LENGTH_LONG).show();
				}
				ChangeNumActivity.this.finish();
				break;
			}
		}
	};
	
	private void init() {
		plus = (Button)findViewById(R.id.changenum_plus);
		minus = (Button)findViewById(R.id.changenum_minus);
		submit = (Button)findViewById(R.id.changenum_submit);
		edit = (EditText)findViewById(R.id.changenum_edit);
		
		num = Integer.parseInt(edit.getText().toString());
		
		Intent intent = getIntent();
		Bundle data = intent.getExtras();
		
		id = (Integer)data.getInt("id");
		
		
	}
}
