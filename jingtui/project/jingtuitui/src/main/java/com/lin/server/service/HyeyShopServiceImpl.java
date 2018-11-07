package com.lin.server.service;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HyeyShopServiceImpl {

	public static void main(String[] args) {
		// Connection.Response res =
		// Jsoup.connect("http://www.hyey.cn/index.aspx").get();
		try {
			Document doc = Jsoup.connect("http://www.hyey.cn/index.aspx").get();
			// System.out.println(doc.html());
			Elements e = doc.getElementsByTag("em");
			Iterator<Element> eList = e.iterator();
			while (eList.hasNext()) {
				// System.out.println(eList.next());

			}
			System.out.println(e.html());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Document doc = Jsoup.connect("http://www.hyey.cn/ashx/GetList2.ashx?pageIndex=1&keys=%E7%9B%90%E9%85%B8%E7%BE%8E%E8%A5%BF%E5%BE%8B%E7%89%87&FGSID=&Lx=3&jg=&isck=&xqid=0&_=1541600").get();
			System.out.println(doc.html());
			//Elements e = doc.getElementsByTag("td");
//			Iterator<Element> eList = e.iterator();
//			while (eList.hasNext()) {
//				// System.out.println(eList.next());
//
//			}
			//System.out.println(e.html());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
