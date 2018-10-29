package com.lin.server.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.lin.model.JingTuiTuiResponseEntity;

@Service("jingTuiTuiService")
public class JingTuiTuiServiceImpl implements JingTuiTuiService {

	private final Logger logger = LoggerFactory.getLogger(JingTuiTuiServiceImpl.class);

	@Autowired
	private RestTemplate template;

	public Map<String,String> loginJingTuiTui() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
		postParameters.add("id", "468");
		postParameters.add("username", "111111");
		postParameters.add("password", "111111");
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
				postParameters, headers);
		ResponseEntity<String> response = template.postForEntity("http://www.jingdongdaili.com/relogin/", requestEntity, String.class);
		//logger.info("result headers:"+ response.getHeaders().getFirst("Set-Cookie"));
		//logger.info("result body:"+ response.getBody());
		JingTuiTuiResponseEntity res = JSON.parseObject(response.getBody(),JingTuiTuiResponseEntity.class);
		if("0".equals(res.getResult())) {
			Map<String,String> cookies = new HashMap<String,String>();
			try {
				cookies=getCookies(response.getHeaders().toString());
			} catch (Exception e) {
				logger.error("format cookies failed",e);
			}
			return cookies;
		}else {
			throw new Exception("login jingtuitui failed:"+response.getBody());
		}
		
	}

	public String getGoods(Map<String, String> cookies) throws Exception {
		Connection.Response res = Jsoup.connect("http://www.jingdongdaili.com/create?cateid=sift").cookies(cookies)
				.method(Connection.Method.POST).timeout(10000).execute();// 设置请求的时间(这里设置的请求时间是10秒)
		Document doc = res.parse();
		return doc.html();
	}

	public String searchGoods(Map<String, String> cookies,String key) throws Exception {
		Connection.Response res = Jsoup.connect("http://www.jingdongdaili.com/create?kw=" + key).cookies(cookies)
				.method(Connection.Method.POST).timeout(10000).execute();// 设置请求的时间(这里设置的请求时间是10秒)
		Document doc = res.parse();
		return doc.html();
	}
	public static void main(String[] args) {
		String headers = "{Date=[Sun, 28 Oct 2018 16:32:29 GMT], Content-Type=[application/json; charset=utf-8], Content-Length=[55], Connection=[keep-alive], Set-Cookie=[__cfduid=dda528c7ef52e2d91238af8f66df911f81540744349; expires=Mon, 28-Oct-19 16:32:29 GMT; path=/; domain=.jingdongdaili.com; HttpOnly, token=e6de9a8a3eac578bcd280aedc09a39c9; expires=Sun, 04-Nov-2018 16:32:29 GMT; Max-Age=604800; path=/, agent_site_id=468; expires=Sun, 04-Nov-2018 16:32:29 GMT; Max-Age=604800; path=/], X-Powered-By=[PHP/5.5.25, ASP.NET], Server=[yunjiasu-nginx], CF-RAY=[470ecc78409a3f59-CGO]}";
		System.out.println(getCookies(headers));
	}
	private static Map<String,String> getCookies(String headers){
		Map<String,String> cookies = Maps.newHashMap();
		Pattern p=Pattern.compile("Set-Cookie(.*?)]");  
		Matcher m=p.matcher(headers);  
		while(m.find())  
		{                 
		    String keyAndValue = m.group();
		    String __cfduid=getCookieByKey(keyAndValue, "__cfduid");
		    cookies.put("__cfduid", __cfduid);
		    
		    String token=getCookieByKey(keyAndValue, "token");
		    cookies.put("token", token);
		    
		    String agent_site_id=getCookieByKey(keyAndValue, "agent_site_id");
		    cookies.put("agent_site_id", agent_site_id);
		    
		}  
		return cookies;
	}
	private static String getCookieByKey(String cookies,String key) {
		String value = "";
		Pattern p=Pattern.compile(key+"=(.*?);");  
		Matcher m=p.matcher(cookies);  
		while(m.find())  
		{                 
			String keyAndValue = m.group();
			int start = keyAndValue.indexOf(key+"=");
			int end = keyAndValue.indexOf(";");
			value = keyAndValue.substring(start+key.length()+1,end);
		}  
		return value;
	}
}
