package com.lin.server.service;

import java.net.URLDecoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lin.model.base.hyey.HyeyData;
import com.lin.model.base.hyey.HyeyResponseEntity;

public class HyeyShopServiceImpl {

	private static RestTemplate restTemplate = new RestTemplate();
	public static void main(String[] args) {
		List<String> urls = getMenuUrl();
//		getGoods(urls.get(0));
		System.out.println(urls.size());
		List<Integer> count = Lists.newArrayList();
		for(String url:urls) {
			getGoods(url,count);
		}
		int sum = 0;
		for(int i:count) {
			sum+=i;
		}
		System.out.println(sum);
	
	}
	public static  List<String> getMenuUrl(){
		List<String> urls = Lists.newArrayList();
		try {
			Document doc = Jsoup.connect("http://www.hyey.cn/index.aspx").get();
			Elements ems = doc.getElementsByTag("em");
			Iterator<Element> eList = ems.iterator();
			while (eList.hasNext()) {
				eList.forEachRemaining(e->{
					urls.add(e.child(0).attr("href"));
				});
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urls;
	}
	
	//"{"pageIndex":"1","keys":"去乙酰毛花苷注射液","FGSID":"","Lx":"3","jg":"","isck":"","xqid":"0"}"
	//pageIndex=1&keys=%E5%8E%BB%E4%B9%99%E9%85%B0%E6%AF%9B%E8%8A%B1%E8%8B%B7%E6%B3%A8%E5%B0%84%E6%B6%B2&FGSID=&Lx=3&jg=&isck=&xqid=0&_=1541695772347
	
	private static String goodsUrl = "http://www.hyey.cn/ashx/GetList2.ashx?pageIndex=1&FGSID=&jg=&isck=&xqid=0&Lx=3&";
	public static void getGoods(String url,List<Integer> count) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Lists.newArrayList(MediaType.TEXT_HTML,MediaType.APPLICATION_XHTML_XML,MediaType.APPLICATION_XML));
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
			postParameters.add("COOKIE_PWD", "1111111111");
			postParameters.add("COOKIE_NAME", "111111");
			postParameters.add("COOKIE_mode", "0");
			postParameters.add("COOKIE_Jg", "");
			postParameters.add("COOKIE_Ck", "");
			postParameters.add("COOKIE_ssMode", "0");
			postParameters.add("ASP.NET_SessionId", "xuhyilwrrfmnmdluwz3vde2d");
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
					postParameters, headers);

			String t = url.substring(url.indexOf("?")+1,url.length())+"&_="+new Date().getTime();
			String u = URLDecoder.decode(goodsUrl+t, "utf-8");
			System.out.println(u);
			ResponseEntity<String> response = restTemplate.postForEntity(u,requestEntity, String.class);
			String res = response.getBody();
			HyeyResponseEntity entity = JSON.parseObject(res,HyeyResponseEntity.class);
			System.out.println(entity.getTotal().getRecord());
			count.add(entity.getTotal().getRecord());
//			HyeyData data = JSON.parseObject(entity.getData(),HyeyData.class);
//			System.out.println(data.getTables());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
