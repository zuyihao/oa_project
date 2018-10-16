package com.lin.server.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.lin.model.JingTuiTuiResponseEntity;

public class JingTuiTuiServiceImpl {

	@Autowired
	private RestTemplate template;

	public void loginJingTuiTui() {
		HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", UUID.randomUUID().toString());
        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
        postParameters.add("id", "1111");
        postParameters.add("username", "11111");
        postParameters.add("password", "1111111");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
                postParameters, headers);
        JingTuiTuiResponseEntity user = template.postForObject("http://www.jingdongdaili.com/relogin/", requestEntity,JingTuiTuiResponseEntity.class);
	}
	public void getGoods() {
		ResponseEntity<String> re = template.getForEntity("http://www.jingdongdaili.com/create?cateid=sift",
				String.class, "");
		System.out.println(re.getStatusCode());
		System.out.println(re.getBody());
	}
	public void searchGoods(String kw) {
		ResponseEntity<String> re = template.getForEntity("http://www.jingdongdaili.com/create?kw="+kw,
				String.class, "");
		System.out.println(re.getStatusCode());
		System.out.println(re.getBody());
	}
}
