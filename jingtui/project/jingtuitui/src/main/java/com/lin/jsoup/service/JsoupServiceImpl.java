package com.lin.jsoup.service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupServiceImpl {
	// 返会一个网页的所有代码
	public static String getjsoup() throws Exception {
		// 返回一个网页的所有代码，get获取内容的方式。
		// Document doc =
		// Jsoup.connect("http://www.jingdongdaili.com/login/468").timeout(1000).get();
		Connection.Response res = Jsoup.connect("http://www.jingdongdaili.com/login/468").timeout(1000).execute();
		String _cfduid = res.cookie("__cfduid");// 不同网站网址的cookie不一样。而且每次访问都不一样，所以不要想着把session保存起来。
		String agentSiteId = res.cookie("agent_site_id");// cook怎么查看呢？看下面
		String token = res.cookie("token");
		String yjsId = res.cookie("yjs_id");
		System.out.println(_cfduid);
		System.out.println(agentSiteId);
		System.out.println(token);
		System.out.println(yjsId);
		return res.parse().outerHtml();
	}

	// 返会一个网页的所有代码
	public static String getjsoup2() throws Exception {
		// 返回一个网页的所有代码，get获取内容的方式。
		// 得到session ，进行模拟登陆，（如果有验证码，我就不知道了）。--博客园老牛大讲堂
		Connection.Response res = Jsoup.connect("http://www.jingdongdaili.com/relogin/")
				.data("id", "468", "username", "11111", "password", "11111")// 进行模拟登陆
				.method(Connection.Method.POST).timeout(10000).execute();// 设置请求时间和登陆用的用户名，密码。
		Document doc = res.parse();
		// 根据session进行爬虫--博客园老牛大讲堂
		// 注释：不是所有网站他们都需要cook，也不是所有的网站cook都是iPlanetDirectoryPro。每个网站cook都不一样。
		// 想要知道网站的cook，自己百度吧！--太基础，不介绍了
		String sessionId = res.cookie("__cfduid");// 不同网站网址的cookie不一样。而且每次访问都不一样，所以不要想着把session保存起来。
		String se = res.cookie("token");// cook怎么查看呢？看下面
		System.out.println(sessionId);
		System.out.println(se);
		Document objectDoc = Jsoup.connect("http://www.****.com").cookie(// 里面的网址(就是你想要爬取的网页)
				"AAA", sessionId).cookie("BBB", se).timeout(10000).post();// 设置请求的时间(这里设置的请求时间是10秒)
		return objectDoc.text();
	}

	public static String getAll(Map<String, String> cookies) throws Exception {
	
		Connection.Response res = Jsoup.connect("http://www.jingdongdaili.com/create?cateid=sift").cookies(cookies)
				.method(Connection.Method.POST).timeout(10000).execute();// 设置请求的时间(这里设置的请求时间是10秒)
		Document doc = res.parse();
		return doc.html();

	}
	public static String search(Map<String, String> cookies,String key) throws Exception {
		Connection.Response res = Jsoup.connect("http://www.jingdongdaili.com/create?kw=" + key).cookies(cookies)
				.method(Connection.Method.POST).timeout(10000).execute();// 设置请求的时间(这里设置的请求时间是10秒)
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
			 
		      // 创建 Pattern 对象
		      Pattern r = Pattern.compile(pattern);
		 
		      // 现在创建 matcher 对象
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
