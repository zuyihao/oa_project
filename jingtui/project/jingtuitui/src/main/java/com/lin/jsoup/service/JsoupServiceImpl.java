package com.lin.jsoup.service;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupServiceImpl{
	// 返会一个网页的所有代码
	public static String getjsoup() throws Exception {
		// 返回一个网页的所有代码，get获取内容的方式。
		//Document doc = Jsoup.connect("http://www.jingdongdaili.com/login/468").timeout(1000).get();
		 Connection.Response res = Jsoup.connect("http://www.jingdongdaili.com/login/468").timeout(1000).execute();
		 String _cfduid = res.cookie("__cfduid");//不同网站网址的cookie不一样。而且每次访问都不一样，所以不要想着把session保存起来。
         String agentSiteId = res.cookie("agent_site_id");//cook怎么查看呢？看下面
         String token = res.cookie("token");
         String yjsId = res.cookie("yjs_id");
         System.out.println(_cfduid);
         System.out.println(agentSiteId);
         System.out.println(token);
         System.out.println(yjsId);
		return res.parse().outerHtml();
	}
	// 返会一个网页的所有代码
	public static String getjsoup2() throws Exception{
		//返回一个网页的所有代码，get获取内容的方式。
		//得到session ，进行模拟登陆，（如果有验证码，我就不知道了）。--博客园老牛大讲堂
        Connection.Response res= Jsoup.connect("http://www.jingdongdaili.com/relogin/").data(
                 "id","468","username", "11111", "password", "11111")//进行模拟登陆
                 .method(Connection.Method.POST).timeout(10000).execute();//设置请求时间和登陆用的用户名，密码。
         Document doc = res.parse();
         //根据session进行爬虫--博客园老牛大讲堂
         //注释：不是所有网站他们都需要cook，也不是所有的网站cook都是iPlanetDirectoryPro。每个网站cook都不一样。
         //想要知道网站的cook，自己百度吧！--太基础，不介绍了
         String sessionId = res.cookie("AAA");//不同网站网址的cookie不一样。而且每次访问都不一样，所以不要想着把session保存起来。
         String se = res.cookie("BBB");//cook怎么查看呢？看下面
         System.out.println(sessionId);
         System.out.println(se);
         Document objectDoc = Jsoup.connect(
                 "http://www.****.com").cookie(//里面的网址(就是你想要爬取的网页)
                 "AAA", sessionId).cookie("BBB", se)
                 .timeout(10000).post();//设置请求的时间(这里设置的请求时间是10秒)
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
