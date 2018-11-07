package com.lin.base.service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

//@Configuration
//@ConditionalOnClass(value = {RestTemplate.class, HttpClient.class})
public class RestTemplateConfig {

//  @Value("${remote.maxTotalConnect:0}")
//  private int maxTotalConnect; //���ӳص����������Ĭ��Ϊ0
//  @Value("${remote.maxConnectPerRoute:200}")
//  private int maxConnectPerRoute; //�������������������
//  @Value("${remote.connectTimeout:2000}")
//  private int connectTimeout; //���ӳ�ʱĬ��2s
//  @Value("${remote.readTimeout:30000}")
//  private int readTimeout; //��ȡ��ʱĬ��30s
//
//  //����HTTP�ͻ��˹���
//  private ClientHttpRequestFactory createFactory() {
//    if (this.maxTotalConnect <= 0) {
//      SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//      factory.setConnectTimeout(this.connectTimeout);
//      factory.setReadTimeout(this.readTimeout);
//      return factory;
//    }
//    HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(this.maxTotalConnect)
//        .setMaxConnPerRoute(this.maxConnectPerRoute).build();
//    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
//        httpClient);
//    factory.setConnectTimeout(this.connectTimeout);
//    factory.setReadTimeout(this.readTimeout);
//    return factory;
//  }
//
//  //��ʼ��RestTemplate,������spring��Bean��������springͳһ����
//  @Bean
//  @ConditionalOnMissingBean(RestTemplate.class)
//  public RestTemplate getRestTemplate() {
//    RestTemplate restTemplate = new RestTemplate(this.createFactory());
//    List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
//
//    //��������StringHttpMessageConverter�ַ���ΪUTF-8�����������������
//    HttpMessageConverter<?> converterTarget = null;
//    for (HttpMessageConverter<?> item : converterList) {
//      if (StringHttpMessageConverter.class == item.getClass()) {
//        converterTarget = item;
//        break;
//      }
//    }
//    if (null != converterTarget) {
//      converterList.remove(converterTarget);
//    }
//    converterList.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
//
//    //����FastJsonת����
//    converterList.add(new FastJsonHttpMessageConverter4());
//    return restTemplate;
//  }

}