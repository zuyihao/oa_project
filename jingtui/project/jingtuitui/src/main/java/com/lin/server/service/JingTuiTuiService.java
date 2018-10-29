package com.lin.server.service;

import java.util.Map;

public interface JingTuiTuiService {
	/***
	 * 登陆，并返回cookies
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> loginJingTuiTui() throws Exception;
	/****
	 * 获取全部商品信息
	 * @param cookies
	 * @return
	 * @throws Exception
	 */
	public String getGoods(Map<String, String> cookies) throws Exception;
	/****
	 * 查询
	 * @param cookies
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String searchGoods(Map<String, String> cookies,String key) throws Exception;
	
}
