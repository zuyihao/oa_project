package com.lin.server.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;

public class Test {
	
	private final Logger logger = LoggerFactory.getLogger(Test.class);


	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
		postParameters.add("id", "468");
		postParameters.add("username", "haoooo");
		postParameters.add("password", "hao123lin");
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
				postParameters, headers);
		ResponseEntity<String> response = restTemplate.postForEntity("http://www.jingdongdaili.com/relogin/", requestEntity, String.class);
		System.out.println("result headers:"+ response.getHeaders().getFirst("Set-Cookie"));
		System.out.println("result body:"+ response.getBody());
//		JingTuiTuiResponseEntity res = JSON.parseObject(response.getBody(),JingTuiTuiResponseEntity.class);
//		if("0".equals(res.getResult())) {
			Map<String,String> cookies = new HashMap<String,String>();
//			try {
//				cookies=getCookies(response.getHeaders().toString());
//			} catch (Exception e) {
//				logger.error("format cookies failed",e);
//			}
			
//		}else {
//			throw new Exception("login jingtuitui failed:"+response.getBody());
//		}
		
	}
}
