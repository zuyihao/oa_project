package com.lin.jsoup.service;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupServiceImpl{
	// ����һ����ҳ�����д���
	public static String getjsoup() throws Exception {
		// ����һ����ҳ�����д��룬get��ȡ���ݵķ�ʽ��
		//Document doc = Jsoup.connect("http://www.jingdongdaili.com/login/468").timeout(1000).get();
		 Connection.Response res = Jsoup.connect("http://www.jingdongdaili.com/login/468").timeout(1000).execute();
		 String _cfduid = res.cookie("__cfduid");//��ͬ��վ��ַ��cookie��һ��������ÿ�η��ʶ���һ�������Բ�Ҫ���Ű�session����������
         String agentSiteId = res.cookie("agent_site_id");//cook��ô�鿴�أ�������
         String token = res.cookie("token");
         String yjsId = res.cookie("yjs_id");
         System.out.println(_cfduid);
         System.out.println(agentSiteId);
         System.out.println(token);
         System.out.println(yjsId);
		return res.parse().outerHtml();
	}
	// ����һ����ҳ�����д���
	public static String getjsoup2() throws Exception{
		//����һ����ҳ�����д��룬get��ȡ���ݵķ�ʽ��
		//�õ�session ������ģ���½�����������֤�룬�ҾͲ�֪���ˣ���--����԰��ţ����
        Connection.Response res= Jsoup.connect("http://www.jingdongdaili.com/relogin/").data(
                 "id","468","username", "11111", "password", "11111")//����ģ���½
                 .method(Connection.Method.POST).timeout(10000).execute();//��������ʱ��͵�½�õ��û��������롣
         Document doc = res.parse();
         //����session��������--����԰��ţ����
         //ע�ͣ�����������վ���Ƕ���Ҫcook��Ҳ�������е���վcook����iPlanetDirectoryPro��ÿ����վcook����һ����
         //��Ҫ֪����վ��cook���Լ��ٶȰɣ�--̫��������������
         String sessionId = res.cookie("AAA");//��ͬ��վ��ַ��cookie��һ��������ÿ�η��ʶ���һ�������Բ�Ҫ���Ű�session����������
         String se = res.cookie("BBB");//cook��ô�鿴�أ�������
         System.out.println(sessionId);
         System.out.println(se);
         Document objectDoc = Jsoup.connect(
                 "http://www.****.com").cookie(//�������ַ(��������Ҫ��ȡ����ҳ)
                 "AAA", sessionId).cookie("BBB", se)
                 .timeout(10000).post();//���������ʱ��(�������õ�����ʱ����10��)
         return  objectDoc.text();
    }
	public static void main(String[] args) {
		try {
			System.out.println(getjsoup2());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
