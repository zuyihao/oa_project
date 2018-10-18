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
		// 连接地址（通过阅读html源代码获得，即为登陆表单提交的URL）
		String surl = "http://www.jingdongdaili.com/relogin/";

		/**
		 * 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL得到。比如： // Using java.net.URL
		 * and //java.net.URLConnection
		 */
		URL url = new URL(surl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		/**
		 * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。
		 * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做：
		 */
		connection.setDoOutput(true);
		/**
		 * 最后，为了得到OutputStream，简单起见，把它约束在Writer并且放入POST信息中，例如： ...
		 */
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "GBK");
		// 其中的memberName和password也是阅读html代码得知的，即为表单中对应的参数名称
		out.write("id=468&username=1111&password=myPassword"); // post的关键所在！
		// remember to clean up
		out.flush();
		out.close();
		// 取得cookie，相当于记录了身份，供下次访问时使用
		String cookieVal = connection.getHeaderField("Set-Cookie");
		System.out.println(cookieVal);
	}

	public void loginJingTuiTui() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		//headers.add("X-Auth-Token", UUID.randomUUID().toString());
		//headers.setAccept(MediaType.);
		MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
		postParameters.add("id", "468");
		postParameters.add("username", "11111");
		postParameters.add("password", "111111");
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
				postParameters, headers);
		//String response = template.postForObject("http://www.jingdongdaili.com/relogin/",JSON.toJSONString(requestEntity), String.class);
		//System.out.println(response);
		//JingTuiTuiResponseEntity result = JSON.parseObject(response, JingTuiTuiResponseEntity.class);
		//System.out.println(result.getResult());
		//System.out.println(result.getReturn());
		String requesetString = "id=468&username=1111&password=111";
		ResponseEntity<String> response = template.postForEntity("http://www.jingdongdaili.com/relogin/", requesetString, String.class);
		System.out.println("result body" + response.getHeaders());
		System.out.println("result body" + response.getBody());
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
