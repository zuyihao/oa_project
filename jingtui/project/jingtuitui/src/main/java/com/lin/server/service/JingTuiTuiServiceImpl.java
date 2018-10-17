package com.lin.server.service;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

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
import com.lin.model.JingTuiTuiResponseEntity;

@Service("jingTuiTuiService")
public class JingTuiTuiServiceImpl implements JingTuiTuiService {

	private final Logger logger = LoggerFactory.getLogger(JingTuiTuiServiceImpl.class);

	@Autowired
	private RestTemplate template;

	public void test()  throws Exception {
		// ���ӵ�ַ��ͨ���Ķ�htmlԴ�����ã���Ϊ��½���ύ��URL��
		String surl = "http://www.jingdongdaili.com/relogin/";

		/**
		 * ����Ҫ��URL�µ�URLConnection�Ի��� URLConnection���Ժ����׵Ĵ�URL�õ������磺 // Using java.net.URL
		 * and //java.net.URLConnection
		 */
		URL url = new URL(surl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		/**
		 * Ȼ���������Ϊ���ģʽ��URLConnectionͨ����Ϊ������ʹ�ã���������һ��Webҳ��
		 * ͨ����URLConnection��Ϊ���������԰����������Webҳ���͡��������������
		 */
		connection.setDoOutput(true);
		/**
		 * ���Ϊ�˵õ�OutputStream�������������Լ����Writer���ҷ���POST��Ϣ�У����磺 ...
		 */
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "GBK");
		// ���е�memberName��passwordҲ���Ķ�html�����֪�ģ���Ϊ���ж�Ӧ�Ĳ�������
		out.write("id=468&username=1111&password=myPassword"); // post�Ĺؼ����ڣ�
		// remember to clean up
		out.flush();
		out.close();
		// ȡ��cookie���൱�ڼ�¼����ݣ����´η���ʱʹ��
		String cookieVal = connection.getHeaderField("Set-Cookie");
		System.out.println(cookieVal);
	}

	public void loginJingTuiTui() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Auth-Token", UUID.randomUUID().toString());
		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
		postParameters.add("id", "1111");
		postParameters.add("username", "11111");
		postParameters.add("password", "1111111");
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
				postParameters, headers);
		String response = template.postForObject("http://www.jingdongdaili.com/relogin/",
				JSON.toJSONString(requestEntity), String.class);
		System.out.println(response);
		JingTuiTuiResponseEntity result = JSON.parseObject(response, JingTuiTuiResponseEntity.class);
		System.out.println(result.getResult());
		System.out.println(result.getReturn());
	}

	public void getGoods() throws Exception {
		ResponseEntity<String> re = template.getForEntity("http://www.jingdongdaili.com/create?cateid=sift",
				String.class, "");
		System.out.println(re.getStatusCode());
		System.out.println(re.getBody());
	}

	public void searchGoods(String kw) throws Exception {
		ResponseEntity<String> re = template.getForEntity("http://www.jingdongdaili.com/create?kw=" + kw, String.class,
				"");
		System.out.println(re.getStatusCode());
		System.out.println(re.getBody());
	}
}
