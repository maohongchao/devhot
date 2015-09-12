package com.bss.proj.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.bss.proj.dao.OrderDao;
import com.bss.proj.entity.Order;

public class MyBatisTest
{
	private static String mybatisPath = "config/mybatis/configuration.xml";
	
	public static void main(String[] args) throws IOException, SQLException
	{
		//修改log4j日志配置文件加载路径
		System.setProperty("log4j.configuration", "config/log4j/log4j.properties");
		
		InputStream is = Resources.getResourceAsStream(mybatisPath);
		
		SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
		
		SqlSessionFactory sessionFactory = (factoryBuilder.build(is));
		
		SqlSession session = sessionFactory.openSession();
		
		OrderDao orderDao = session.getMapper(OrderDao.class);
		
		Order order = orderDao.findOrderById("4a51cbdaee4a449b896c8a3673d47494");
		
		Logger logger = Logger.getLogger(MyBatisTest.class);
		logger.debug("==>中文是否乱码");
		
		System.out.println(order);
		
	}
	
}
