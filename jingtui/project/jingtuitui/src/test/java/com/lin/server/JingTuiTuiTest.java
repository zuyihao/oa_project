package com.lin.server;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;

import com.lin.server.service.JingTuiTuiService;

public class JingTuiTuiTest{
//	@Autowired //自动注入
//	private JingTuiTuiService jingTuiTuiService;
	
	@Test
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚 
	public void test(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("\\applicationContext.xml");
		JingTuiTuiService jingTuiTuiService = (JingTuiTuiService) ac.getBean("jingTuiTuiService");
		try {
			System.out.println(jingTuiTuiService.loginJingTuiTui());
			//jingTuiTuiService.test();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

