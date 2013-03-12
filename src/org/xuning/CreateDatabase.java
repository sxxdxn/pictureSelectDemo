package org.xuning;

public class CreateDatabase {
	public static void createdatabase(){
		try{
			String sqll[]=new String[]{
					//建表_菜单
				"create table if not exists menu "+
				"(cai_id integer primary key,"+
				"cai_name char(20),cai_kind integer,"+
				"cai_pic char(50),"+
				"cai_price float(2),cai_star float(1),"+
				"cai_discount float(2),cai_info char(50),"+
				"cai_yingyang1 integer,cai_yingyang2 integer,cai_yingyang3 integer,cai_yingyang4 integer,"+
				"cai_other char(50))",
				//建表_订购
				"create table if not exists buy "+
				"(buy_id integer,"+
				"cai_id integer,cai_num integer,"+
				"buy_other char(50) ,primary key(buy_id,cai_id))",
				//建表_广告
				"create table if not exists ad "+
				"(cai_id integer primary key,"+
				"cai_name char(20),cai_kind integer,"+
				"cai_pic char(50),"+
				"cai_price float(2),cai_star float(1),"+
				"cai_discount float(2),cai_info char(50),"+
				"cai_yingyang1 integer,cai_yingyang2 integer,cai_yingyang3 integer,cai_yingyang4 integer,"+
				"cai_other char(50))",
				//插入数据
				"insert into menu values(1001,'香辣里脊',2,'/sdcard/data/easyMenu/cai_1.png',18.00,5,10,'香辣里脊很好吃',1,2,3,0,'null')",
				"insert into menu values(1002,'香菇油菜',1,'/sdcard/data/easyMenu/cai_2.png',16.00,4.5,10,'香菇油菜很好吃',0,0,1,2,'null')",
				"insert into menu values(1003,'水煮肉',2,'/sdcard/data/easyMenu/cai_3.png',20.00,4.5,10,'水煮肉很好吃',3,3,1,2,'null')",
				"insert into menu values(1004,'过油肉',2,'/sdcard/data/easyMenu/cai_4.png',20.00,4.5,10,'过油肉很好吃',1,2,3,0,'null')",
				"insert into menu values(1005,'椒盐里脊',2,'/sdcard/data/easyMenu/cai_5.jpg',21.50,4.5,10,'椒盐里脊很好吃',0,0,2,2,'null')",
				"insert into menu values(1006,'椒盐里脊',0,'/sdcard/data/easyMenu/cai_5.jpg',21.50,4.5,10,'椒盐里脊很好吃',1,2,3,0,'null')",
				"insert into menu values(1007,'香辣意粉',0,'/sdcard/data/easyMenu/cai_7.png',17.00,3.5,10,'香辣意粉别有风味',0,1,3,2,'null')",
				"insert into menu values(1008,'银丝抄虾',1,'/sdcard/data/easyMenu/cai_8.png',21.00,4.0,10,'银丝抄虾很实惠',1,2,2,1,'null')",
				"insert into menu values(1009,'鱼片花粥',2,'/sdcard/data/easyMenu/cai_9.png',15.00,4.5,10,'粥清香鱼美味',3,2,2,3,'null')",
				"insert into menu values(10010,'黄金菜卷',1,'/sdcard/data/easyMenu/cai_10.png',18.00,3.0,10,'小巧美味色泽光亮',1,1,2,3,'null')",
				"insert into menu values(10011,'农家肉夹馍',1,'/sdcard/data/easyMenu/cai_11.png',19.00,4.5,10,'陕西人的最爱',1,3,2,1,'null')",
				"insert into menu values(10012,'草莓甜点',0,'/sdcard/data/easyMenu/cai_12.png',22.00,5.0,10,'口味清爽香气迷人',2,2,2,1,'null')",
				"insert into menu values(10013,'香草冰激凌',0,'/sdcard/data/easyMenu/cai_13.png',13.00,4.5,10,'厚味无穷',1,2,1,1,'null')",
				"insert into menu values(10014,'爆炒龙虾',2,'/sdcard/data/easyMenu/cai_14.png',30.00,5.0,10,'香辣可口虾肉香嫩',2,2,2,2,'null')",
				"insert into menu values(10015,'肉松蛋卷',1,'/sdcard/data/easyMenu/cai_15.png',16.00,4.0,10,'蛋卷松软可口',1,2,2,3,'null')",
				"insert into menu values(10016,'牛肉拌细面',1,'/sdcard/data/easyMenu/cai_16.png',15.00,3.5,10,'口味清淡',2,1,2,1,'null')",
				"insert into menu values(10017,'酱牛肉',1,'/sdcard/data/easyMenu/cai_17.png',30.00,4.5,10,'入口香嫩',2,3,2,3,'null')",
				"insert into menu values(10018,'川味椒麻鸡',1,'/sdcard/data/easyMenu/cai_18.png',35.00,4.5,10,'香辣可口肌肉鲜嫩',2,3,2,1,'null')",
				"insert into menu values(10019,'鲜味蛤蜊',1,'/sdcard/data/easyMenu/cai_19.png',35.00,5.0,10,'肉鲜嫩汤美味',2,3,2,3,'null')",
				"insert into menu values(10020,'水果的诱惑',1,'/sdcard/data/easyMenu/cai_20.png',25.00,5.0,10,'水果丰富',3,3,2,1,'null')",
				"insert into menu values(10021,'蒜苔炒肉',3,'/sdcard/data/easyMenu/cai_21.jpg',15.00,4.5,10,'蒜苔炒肉很好吃的',0,0,2,2,'null')",
				"insert into menu values(10022,'蔬菜水果',1,'/sdcard/data/easyMenu/cai_22.jpg',18.00,3.5,10,'清脆可口',3,2,2,2,'null')",
				"insert into menu values(10023,'奶油甜点',1,'/sdcard/data/easyMenu/cai_23.jpg',15.00,4.5,10,'香甜可口',2,1,3,0,'null')",
				"insert into menu values(10024,'胡椒鸡块',3,'/sdcard/data/easyMenu/cai_24.jpg',25.00,4.5,10,'满口生香',1,1,2,2,'null')",
				"insert into menu values(10025,'烤羊肉条',3,'/sdcard/data/easyMenu/cai_25.jpg',30.00,4.5,10,'很好吃的',3,0,2,2,'null')",
				
				"insert into ad values(10021,'蒜苔炒肉',3,'/sdcard/data/easyMenu/cai_21.jpg',15.00,4.5,10,'蒜苔炒肉很好吃的',0,0,2,2,'null')",
				"insert into ad values(10022,'蔬菜水果',1,'/sdcard/data/easyMenu/cai_22.jpg',18.00,3.5,10,'清脆可口',3,2,2,2,'null')",
				"insert into ad values(10023,'奶油甜点',1,'/sdcard/data/easyMenu/cai_23.jpg',15.00,4.5,10,'香甜可口',2,1,3,0,'null')",
				"insert into ad values(10024,'胡椒鸡块',3,'/sdcard/data/easyMenu/cai_24.jpg',25.00,4.5,10,'满口生香',1,1,2,2,'null')",
				"insert into ad values(10025,'烤羊肉条',3,'/sdcard/data/easyMenu/cai_25.jpg',30.00,4.5,10,'很好吃的',3,0,2,2,'null')",
			};
			
			for(String o:sqll){//循环所有SQL语句，进行建表和初始化一些数据操作
				LoadUtil.createTable(o);
			}	
			System.out.println("数据库创建成功");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
