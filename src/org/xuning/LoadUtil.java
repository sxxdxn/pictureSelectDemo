package org.xuning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//"/data/data/org.xuning/mydb",

public class LoadUtil {
	public static SQLiteDatabase createOrOpenDatabase()
	{
		SQLiteDatabase db=null;
		try{
			db=SQLiteDatabase.openDatabase(
					"/data/data/org.xuning/mydb",
					null,
					SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return db;
	}
	
	public static void createTable(String sql)
	{
		SQLiteDatabase db=createOrOpenDatabase();
		try{
			db.execSQL(sql);
			db.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean insert(String sql)
	{
		SQLiteDatabase db=createOrOpenDatabase();
		try{
			db.execSQL(sql);
			db.close();
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	
	public static void delete(String sql)
	{
		SQLiteDatabase db=createOrOpenDatabase();
		try{
			db.execSQL(sql);
			db.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static List<Map<String,Object>> query(String sql)
	{
		List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
		
		SQLiteDatabase db=createOrOpenDatabase();
		int codeColumn,id;
		String name,pic;
		int yingyang1,yingyang2,yingyang3,yingyang4;
		float price,star;
		String info;
		try{
			Cursor cursor = db.rawQuery(sql, new String[]{});
			while(cursor.moveToNext())
			{
				Map<String,Object>listItem=new HashMap<String,Object>();
				codeColumn = cursor.getColumnIndex("cai_id");
				id = cursor.getInt(codeColumn);
				codeColumn = cursor.getColumnIndex("cai_name");
				name=cursor.getString(codeColumn);
				codeColumn = cursor.getColumnIndex("cai_pic");
				pic=cursor.getString(codeColumn);
				codeColumn = cursor.getColumnIndex("cai_price");
				price = cursor.getFloat(codeColumn);
				codeColumn = cursor.getColumnIndex("cai_star");
				star = cursor.getFloat(codeColumn);
				codeColumn = cursor.getColumnIndex("cai_info");
				info = cursor.getString(codeColumn);
				codeColumn = cursor.getColumnIndex("cai_yingyang1");
				yingyang1 = cursor.getInt(codeColumn);
				codeColumn = cursor.getColumnIndex("cai_yingyang2");
				yingyang2 = cursor.getInt(codeColumn);
				codeColumn = cursor.getColumnIndex("cai_yingyang3");
				yingyang3 = cursor.getInt(codeColumn);
				codeColumn = cursor.getColumnIndex("cai_yingyang4");
				yingyang4 = cursor.getInt(codeColumn);
				
				listItem.put("name", name);
				listItem.put("pic", pic);
				listItem.put("price", price);
				listItem.put("star", star);
				listItem.put("id", id);
				listItem.put("check", 0);
				listItem.put("info", info);
				
				switch(yingyang1){
				case 0:listItem.put("yingyang1", "无");break;
				case 1:listItem.put("yingyang1", "少量");break;
				case 2:listItem.put("yingyang1", "中量");break;
				case 3:listItem.put("yingyang1", "大量");break;
				default:listItem.put("yingyang1", "读取失败");break;
				}
				
				switch(yingyang2){
				case 0:listItem.put("yingyang2", "无");break;
				case 1:listItem.put("yingyang2", "少量");break;
				case 2:listItem.put("yingyang2", "中量");break;
				case 3:listItem.put("yingyang2", "大量");break;
				default:listItem.put("yingyang2", "读取失败");break;
				}
				
				switch(yingyang3){
				case 0:listItem.put("yingyang3", "无");break;
				case 1:listItem.put("yingyang3", "少量");break;
				case 2:listItem.put("yingyang3", "中量");break;
				case 3:listItem.put("yingyang3", "大量");break;
				default:listItem.put("yingyang3", "读取失败");break;
				}
				
				switch(yingyang4){
				case 0:listItem.put("yingyang4", "无");break;
				case 1:listItem.put("yingyang4", "少量");break;
				case 2:listItem.put("yingyang4", "中量");break;
				case 3:listItem.put("yingyang4", "大量");break;
				default:listItem.put("yingyang4", "读取失败");break;
				}
				
				listItems.add(listItem);
				//listItem.clear();
			}
			cursor.close();
			db.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return listItems;
	}
	
	public static List<Map<String,Object>> getkind0()
	{
		String sql = "select * from menu where cai_kind = 0";
		
		List<Map<String,Object>> listItems = query(sql);
		return listItems;
	}
	
	public static List<Map<String,Object>> getkind1()
	{
		String sql = "select * from menu where cai_kind = 1";
		
		List<Map<String,Object>> listItems = query(sql);
		return listItems;
	}
	public static List<Map<String,Object>> getkind2()
	{
		String sql = "select * from menu where cai_kind = 2";
		
		List<Map<String,Object>> listItems = query(sql);
		return listItems;
	}
	public static List<Map<String,Object>> getkind3()
	{
		String sql = "select * from menu where cai_kind = 3";
		
		List<Map<String,Object>> listItems = query(sql);
		return listItems;
	}
	public static List<Map<String,Object>> getkind4()
	{
		String sql = "select * from menu where cai_kind = 4";
		
		List<Map<String,Object>> listItems = query(sql);
		return listItems;
	}
	public static List<Map<String,Object>> getkind5()
	{
		String sql = "select * from menu where cai_kind = 5";
		
		List<Map<String,Object>> listItems = query(sql);
		return listItems;
	}
	
	public static List<Map<String,Object>> getyy(int yy1,int yy2,int yy3,int yy4)
	{
		String sql = "select * from menu where ";
		if(yy1!=-1)
			sql+="cai_yingyang1 = "+yy1;
		if(yy2!=-1)
		{
			if(yy1==-1)
				sql+="cai_yingyang2 = "+yy2;
			else
				sql+=" and cai_yingyang2 = "+yy2;
		}
		if(yy3!=-1)
		{
			if(yy2==-1&&yy1==-1)
				sql+="cai_yingyang3 = "+yy3;
			else
				sql+=" and cai_yingyang3 = "+yy3;
		}
			
		if(yy4!=-1)
		{
			if(yy1==-1&&yy2==-1&&yy3==-1)
				sql+="cai_yingyang4 = "+yy4;
			else
				sql+=" and cai_yingyang4 = " +yy4;
		}
			

		
		System.out.println(sql);
		
		List<Map<String,Object>> listItems = query(sql);
		return listItems;
	}
	
	public static List<Map<String,Object>> getad()
	{
		String sql = "select * from ad";
		
		List<Map<String,Object>> listItems = query(sql);
		return listItems;
	}
	
	public static Map<String,Object> getCaiById(int cai_id)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		SQLiteDatabase db=createOrOpenDatabase();
		String sql = "select * from menu where cai_id = "+cai_id;
		int codeColumn,id;
		String name,pic;
		int yingyang1,yingyang2,yingyang3,yingyang4;
		float price,star;
		String info;
		
		try{
			Cursor cursor = db.rawQuery(sql, new String[]{});
			cursor.moveToNext();
			//Map<String,Object>listItem=new HashMap<String,Object>();
			codeColumn = cursor.getColumnIndex("cai_id");
			id = cursor.getInt(codeColumn);
			codeColumn = cursor.getColumnIndex("cai_name");
			name=cursor.getString(codeColumn);
			codeColumn = cursor.getColumnIndex("cai_pic");
			pic=cursor.getString(codeColumn);
			codeColumn = cursor.getColumnIndex("cai_price");
			price = cursor.getFloat(codeColumn);
			codeColumn = cursor.getColumnIndex("cai_star");
			star = cursor.getFloat(codeColumn);
			codeColumn = cursor.getColumnIndex("cai_info");
			info = cursor.getString(codeColumn);
			codeColumn = cursor.getColumnIndex("cai_yingyang1");
			yingyang1 = cursor.getInt(codeColumn);
			codeColumn = cursor.getColumnIndex("cai_yingyang2");
			yingyang2 = cursor.getInt(codeColumn);
			codeColumn = cursor.getColumnIndex("cai_yingyang3");
			yingyang3 = cursor.getInt(codeColumn);
			codeColumn = cursor.getColumnIndex("cai_yingyang4");
			yingyang4 = cursor.getInt(codeColumn);
			
			map.put("name", name);
			map.put("pic", pic);
			map.put("price", price);
			map.put("star", star);
			map.put("id", id);
			map.put("check", 0);
			map.put("info", info);
			
			switch(yingyang1){
			case 0:map.put("yingyang1", "无");break;
			case 1:map.put("yingyang1", "少量");break;
			case 2:map.put("yingyang1", "中量");break;
			case 3:map.put("yingyang1", "大量");break;
			default:map.put("yingyang1", "读取失败");break;
			}
			
			switch(yingyang2){
			case 0:map.put("yingyang2", "无");break;
			case 1:map.put("yingyang2", "少量");break;
			case 2:map.put("yingyang2", "中量");break;
			case 3:map.put("yingyang2", "大量");break;
			default:map.put("yingyang2", "读取失败");break;
			}
			
			switch(yingyang3){
			case 0:map.put("yingyang3", "无");break;
			case 1:map.put("yingyang3", "少量");break;
			case 2:map.put("yingyang3", "中量");break;
			case 3:map.put("yingyang3", "大量");break;
			default:map.put("yingyang3", "读取失败");break;
			}
			
			switch(yingyang4){
			case 0:map.put("yingyang4", "无");break;
			case 1:map.put("yingyang4", "少量");break;
			case 2:map.put("yingyang4", "中量");break;
			case 3:map.put("yingyang4", "大量");break;
			default:map.put("yingyang4", "读取失败");break;
			}
			
			db.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}
	public static List<Map<String,Object>> getBuyList(String sql)
	{
		//sql = select * from buy where buy_id = 1001
		
		List<Map<String,Object>> buyListItems = new ArrayList<Map<String,Object>>();
		SQLiteDatabase db=createOrOpenDatabase();
		
		int codeColumn,id,num;
		float price;
		String name;
		
		try{
			Cursor cursor = db.rawQuery(sql, new String[]{});
			while(cursor.moveToNext())
			{
				List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
				Map<String,Object>listItem=new HashMap<String,Object>();
				codeColumn = cursor.getColumnIndex("cai_id");
				id = cursor.getInt(codeColumn);
				codeColumn = cursor.getColumnIndex("cai_num");
				num = cursor.getInt(codeColumn);
				
				String sql2="select * from menu where cai_id = "+id;
				System.out.println("getBuyList:"+sql2);
				listItems = query(sql2);
				price = (Float)listItems.get(0).get("price");
				name = (String)listItems.get(0).get("name");
				
				listItem.put("id", id);
				listItem.put("name", name);
				listItem.put("num", num);
				listItem.put("price", price);
				
				buyListItems.add(listItem);
				db.close();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return buyListItems;
	}
}
	

