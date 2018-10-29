package com.lin.server;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;

import com.lin.server.service.JingTuiTuiService;

public class JingTuiTuiTest{
//	@Autowired //�Զ�ע��
//	private JingTuiTuiService jingTuiTuiService;
	
	@Test
    @Rollback(false)  //����ʹ����˷��������񲻻ع�,trueʱΪ�ع� 
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

