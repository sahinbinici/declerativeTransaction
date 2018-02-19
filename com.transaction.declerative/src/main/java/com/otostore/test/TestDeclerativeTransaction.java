package com.otostore.test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.otostore.dao.CarDetailDeclerative;

public class TestDeclerativeTransaction {

	public static void main(String[] args){
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		CarDetailDeclerative carDetailDec=context.getBean("CarDetailDeclerative",CarDetailDeclerative.class);
		
		carDetailDec.deleteCarDetail(1);
		carDetailDec.deleteCarDetail(2);
	}
}
