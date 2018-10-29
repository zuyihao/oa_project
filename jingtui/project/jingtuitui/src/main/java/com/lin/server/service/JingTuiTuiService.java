package com.lin.server.service;

import java.util.Map;

public interface JingTuiTuiService {
	/***
	 * ��½��������cookies
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> loginJingTuiTui() throws Exception;
	/****
	 * ��ȡȫ����Ʒ��Ϣ
	 * @param cookies
	 * @return
	 * @throws Exception
	 */
	public String getGoods(Map<String, String> cookies) throws Exception;
	/****
	 * ��ѯ
	 * @param cookies
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String searchGoods(Map<String, String> cookies,String key) throws Exception;
	
}
