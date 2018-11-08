package com.lin.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lin.model.base.ResponseEntity;
import com.lin.server.service.JingTuiTuiService;

@Controller
public class JingTuiTuiController {
	
	private final Logger logger = LoggerFactory.getLogger(JingTuiTuiController.class);
	
	@Autowired
	private JingTuiTuiService jingTuiTuiService;
	
	private Map<String,String> jingTuiTuiCookies = null;
	
	@RequestMapping("searchView")
	public String searchView(Model model) {
		return "jingtuitui";
	}
	@RequestMapping("search")
    @ResponseBody
	public ResponseEntity<String> greeting(@RequestParam(name = "searchKey", required = false, defaultValue = "") String searchKey) {
		logger.info("search goods");
		ResponseEntity<String> result = new ResponseEntity<String>();
		try {
			if(CollectionUtils.isEmpty(jingTuiTuiCookies)) {
				refreshJingTuiTuiCookies();
			}
			String data = jingTuiTuiService.searchGoods(jingTuiTuiCookies,searchKey);
			result.setData(data);
		} catch (Exception e) {
			logger.error("get jingtuitui error",e);
			result.setCode(-1);
		}
		return result;
	}
	
	private void refreshJingTuiTuiCookies() throws Exception {
		jingTuiTuiCookies = jingTuiTuiService.loginJingTuiTui();
	}
}
