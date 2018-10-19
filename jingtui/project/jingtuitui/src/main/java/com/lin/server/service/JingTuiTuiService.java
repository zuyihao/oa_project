package com.lin.server.service;

import java.util.Map;

public interface JingTuiTuiService {
	
	public Map<String,String> loginJingTuiTui() throws Exception;
	
	public void getGoods() throws Exception;
	
	public void searchGoods(String kw) throws Exception;
	
	public void test()  throws Exception ;
}
