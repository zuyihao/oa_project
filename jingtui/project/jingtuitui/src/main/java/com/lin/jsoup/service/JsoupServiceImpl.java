package com.lin.jsoup.service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupServiceImpl {
	// ����һ����ҳ�����д���
	public static String getjsoup() throws Exception {
		// ����һ����ҳ�����д��룬get��ȡ���ݵķ�ʽ��
		// Document doc =
		// Jsoup.connect("http://www.jingdongdaili.com/login/468").timeout(1000).get();
		Connection.Response res = Jsoup.connect("http://www.jingdongdaili.com/login/468").timeout(1000).execute();
		String _cfduid = res.cookie("__cfduid");// ��ͬ��վ��ַ��cookie��һ��������ÿ�η��ʶ���һ�������Բ�Ҫ���Ű�session����������
		String agentSiteId = res.cookie("agent_site_id");// cook��ô�鿴�أ�������
		String token = res.cookie("token");
		String yjsId = res.cookie("yjs_id");
		System.out.println(_cfduid);
		System.out.println(agentSiteId);
		System.out.println(token);
		System.out.println(yjsId);
		return res.parse().outerHtml();
	}

	// ����һ����ҳ�����д���
	public static String getjsoup2() throws Exception {
		// ����һ����ҳ�����д��룬get��ȡ���ݵķ�ʽ��
		// �õ�session ������ģ���½�����������֤�룬�ҾͲ�֪���ˣ���--����԰��ţ����
		Connection.Response res = Jsoup.connect("http://www.jingdongdaili.com/relogin/")
				.data("id", "468", "username", "11111", "password", "11111")// ����ģ���½
				.method(Connection.Method.POST).timeout(10000).execute();// ��������ʱ��͵�½�õ��û��������롣
		Document doc = res.parse();
		// ����session��������--����԰��ţ����
		// ע�ͣ�����������վ���Ƕ���Ҫcook��Ҳ�������е���վcook����iPlanetDirectoryPro��ÿ����վcook����һ����
		// ��Ҫ֪����վ��cook���Լ��ٶȰɣ�--̫��������������
		String sessionId = res.cookie("__cfduid");// ��ͬ��վ��ַ��cookie��һ��������ÿ�η��ʶ���һ�������Բ�Ҫ���Ű�session����������
		String se = res.cookie("token");// cook��ô�鿴�أ�������
		System.out.println(sessionId);
		System.out.println(se);
		Document objectDoc = Jsoup.connect("http://www.****.com").cookie(// �������ַ(��������Ҫ��ȡ����ҳ)
				"AAA", sessionId).cookie("BBB", se).timeout(10000).post();// ���������ʱ��(�������õ�����ʱ����10��)
		return objectDoc.text();
	}

	public static String getAll(Map<String, String> cookies) throws Exception {
	
		Connection.Response res = Jsoup.connect("http://www.jingdongdaili.com/create?cateid=sift").cookies(cookies)
				.method(Connection.Method.POST).timeout(10000).execute();// ���������ʱ��(�������õ�����ʱ����10��)
		Document doc = res.parse();
		return doc.html();

	}
	public static String search(Map<String, String> cookies,String key) throws Exception {
		Connection.Response res = Jsoup.connect("http://www.jingdongdaili.com/create?kw=" + key).cookies(cookies)
				.method(Connection.Method.POST).timeout(10000).execute();// ���������ʱ��(�������õ�����ʱ����10��)
		Document doc = res.parse();
		return doc.html();
	}

	public static void main(String[] args) {
		try {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("token", "c4b7b8129045ceaba29f42104125964f");
//			map.put("agent_site_id", "468");
//			System.out.println(search(map,""));
			
			
			
			String s = "result body{Date=[Fri, 19 Oct 2018 14:34:40 GMT], Content-Type=[application/json; charset=utf-8], Content-Length=[55], Connection=[keep-alive], Set-Cookie=[__cfduid=d83745ad0b68b021794b9067ced6faf111539959679; expires=Sat, 19-Oct-19 14:34:39 GMT; path=/; domain=.jingdongdaili.com; HttpOnly, token=56168e9883c117523c7eb5e6b3308c18; expires=Fri, 26-Oct-2018 14:34:40 GMT; Max-Age=604800; path=/, agent_site_id=468; expires=Fri, 26-Oct-2018 14:34:40 GMT; Max-Age=604800; path=/], X-Powered-By=[PHP/5.5.25, ASP.NET], Server=[yunjiasu-nginx], CF-RAY=[46c3f77f319e3f13-CGO]}";
			//System.out.println(s.substring(s.indexOf("token=")).split(";")[0]);
			String pattern = "(token*=[a-z])";
			 
		      // ���� Pattern ����
		      Pattern r = Pattern.compile(pattern);
		 
		      // ���ڴ��� matcher ����
		      Matcher m = r.matcher(s);
		      if (m.find( )) {
		         System.out.println(m.group(0) );
		      } else {
		         System.out.println("NO MATCH");
		      }
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
