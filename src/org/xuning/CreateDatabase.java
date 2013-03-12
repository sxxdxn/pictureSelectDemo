package org.xuning;

public class CreateDatabase {
	public static void createdatabase(){
		try{
			String sqll[]=new String[]{
					//����_�˵�
				"create table if not exists menu "+
				"(cai_id integer primary key,"+
				"cai_name char(20),cai_kind integer,"+
				"cai_pic char(50),"+
				"cai_price float(2),cai_star float(1),"+
				"cai_discount float(2),cai_info char(50),"+
				"cai_yingyang1 integer,cai_yingyang2 integer,cai_yingyang3 integer,cai_yingyang4 integer,"+
				"cai_other char(50))",
				//����_����
				"create table if not exists buy "+
				"(buy_id integer,"+
				"cai_id integer,cai_num integer,"+
				"buy_other char(50) ,primary key(buy_id,cai_id))",
				//����_���
				"create table if not exists ad "+
				"(cai_id integer primary key,"+
				"cai_name char(20),cai_kind integer,"+
				"cai_pic char(50),"+
				"cai_price float(2),cai_star float(1),"+
				"cai_discount float(2),cai_info char(50),"+
				"cai_yingyang1 integer,cai_yingyang2 integer,cai_yingyang3 integer,cai_yingyang4 integer,"+
				"cai_other char(50))",
				//��������
				"insert into menu values(1001,'�����Ｙ',2,'/sdcard/data/easyMenu/cai_1.png',18.00,5,10,'�����Ｙ�ܺó�',1,2,3,0,'null')",
				"insert into menu values(1002,'�㹽�Ͳ�',1,'/sdcard/data/easyMenu/cai_2.png',16.00,4.5,10,'�㹽�Ͳ˺ܺó�',0,0,1,2,'null')",
				"insert into menu values(1003,'ˮ����',2,'/sdcard/data/easyMenu/cai_3.png',20.00,4.5,10,'ˮ����ܺó�',3,3,1,2,'null')",
				"insert into menu values(1004,'������',2,'/sdcard/data/easyMenu/cai_4.png',20.00,4.5,10,'������ܺó�',1,2,3,0,'null')",
				"insert into menu values(1005,'�����Ｙ',2,'/sdcard/data/easyMenu/cai_5.jpg',21.50,4.5,10,'�����Ｙ�ܺó�',0,0,2,2,'null')",
				"insert into menu values(1006,'�����Ｙ',0,'/sdcard/data/easyMenu/cai_5.jpg',21.50,4.5,10,'�����Ｙ�ܺó�',1,2,3,0,'null')",
				"insert into menu values(1007,'�������',0,'/sdcard/data/easyMenu/cai_7.png',17.00,3.5,10,'������۱��з�ζ',0,1,3,2,'null')",
				"insert into menu values(1008,'��˿��Ϻ',1,'/sdcard/data/easyMenu/cai_8.png',21.00,4.0,10,'��˿��Ϻ��ʵ��',1,2,2,1,'null')",
				"insert into menu values(1009,'��Ƭ����',2,'/sdcard/data/easyMenu/cai_9.png',15.00,4.5,10,'����������ζ',3,2,2,3,'null')",
				"insert into menu values(10010,'�ƽ�˾�',1,'/sdcard/data/easyMenu/cai_10.png',18.00,3.0,10,'С����ζɫ�����',1,1,2,3,'null')",
				"insert into menu values(10011,'ũ�������',1,'/sdcard/data/easyMenu/cai_11.png',19.00,4.5,10,'�����˵��',1,3,2,1,'null')",
				"insert into menu values(10012,'��ݮ���',0,'/sdcard/data/easyMenu/cai_12.png',22.00,5.0,10,'��ζ��ˬ��������',2,2,2,1,'null')",
				"insert into menu values(10013,'��ݱ�����',0,'/sdcard/data/easyMenu/cai_13.png',13.00,4.5,10,'��ζ����',1,2,1,1,'null')",
				"insert into menu values(10014,'������Ϻ',2,'/sdcard/data/easyMenu/cai_14.png',30.00,5.0,10,'�����ɿ�Ϻ������',2,2,2,2,'null')",
				"insert into menu values(10015,'���ɵ���',1,'/sdcard/data/easyMenu/cai_15.png',16.00,4.0,10,'��������ɿ�',1,2,2,3,'null')",
				"insert into menu values(10016,'ţ���ϸ��',1,'/sdcard/data/easyMenu/cai_16.png',15.00,3.5,10,'��ζ�嵭',2,1,2,1,'null')",
				"insert into menu values(10017,'��ţ��',1,'/sdcard/data/easyMenu/cai_17.png',30.00,4.5,10,'�������',2,3,2,3,'null')",
				"insert into menu values(10018,'��ζ���鼦',1,'/sdcard/data/easyMenu/cai_18.png',35.00,4.5,10,'�����ɿڼ�������',2,3,2,1,'null')",
				"insert into menu values(10019,'��ζ����',1,'/sdcard/data/easyMenu/cai_19.png',35.00,5.0,10,'����������ζ',2,3,2,3,'null')",
				"insert into menu values(10020,'ˮ�����ջ�',1,'/sdcard/data/easyMenu/cai_20.png',25.00,5.0,10,'ˮ���ḻ',3,3,2,1,'null')",
				"insert into menu values(10021,'��̦����',3,'/sdcard/data/easyMenu/cai_21.jpg',15.00,4.5,10,'��̦����ܺóԵ�',0,0,2,2,'null')",
				"insert into menu values(10022,'�߲�ˮ��',1,'/sdcard/data/easyMenu/cai_22.jpg',18.00,3.5,10,'���ɿ�',3,2,2,2,'null')",
				"insert into menu values(10023,'�������',1,'/sdcard/data/easyMenu/cai_23.jpg',15.00,4.5,10,'����ɿ�',2,1,3,0,'null')",
				"insert into menu values(10024,'��������',3,'/sdcard/data/easyMenu/cai_24.jpg',25.00,4.5,10,'��������',1,1,2,2,'null')",
				"insert into menu values(10025,'��������',3,'/sdcard/data/easyMenu/cai_25.jpg',30.00,4.5,10,'�ܺóԵ�',3,0,2,2,'null')",
				
				"insert into ad values(10021,'��̦����',3,'/sdcard/data/easyMenu/cai_21.jpg',15.00,4.5,10,'��̦����ܺóԵ�',0,0,2,2,'null')",
				"insert into ad values(10022,'�߲�ˮ��',1,'/sdcard/data/easyMenu/cai_22.jpg',18.00,3.5,10,'���ɿ�',3,2,2,2,'null')",
				"insert into ad values(10023,'�������',1,'/sdcard/data/easyMenu/cai_23.jpg',15.00,4.5,10,'����ɿ�',2,1,3,0,'null')",
				"insert into ad values(10024,'��������',3,'/sdcard/data/easyMenu/cai_24.jpg',25.00,4.5,10,'��������',1,1,2,2,'null')",
				"insert into ad values(10025,'��������',3,'/sdcard/data/easyMenu/cai_25.jpg',30.00,4.5,10,'�ܺóԵ�',3,0,2,2,'null')",
			};
			
			for(String o:sqll){//ѭ������SQL��䣬���н���ͳ�ʼ��һЩ���ݲ���
				LoadUtil.createTable(o);
			}	
			System.out.println("���ݿⴴ���ɹ�");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
