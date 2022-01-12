package com.coindesk.test.coindesktool.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coindesk.test.coindesktool.service.CoindeskAPIService;
import com.coindesk.test.coindesktool.vo.CurrencyVO;
import com.coindesk.test.coindesktool.vo.CurrentpriceVO;
import com.coindesk.test.coindesktool.vo.NormalResponseVO;
import com.google.gson.Gson;

@RestController
public class ApiController {

    @Autowired
    CoindeskAPIService coindeskAPIService;
    
	@PostMapping(value = "/info")
    public Map<String,Object> info() {
		
	    Map<String,Object> result = new HashMap<>();
        try {
            
            result = coindeskAPIService.rebuildCoindeskAPIInfo();
            
        }catch (Exception e) {
            e.printStackTrace();
            result.put("message","例外錯誤:" + e.getMessage());
        }
	    
	    return result;
	    
    }
	
	@PostMapping(
	        value = "/coindeskAPI", produces = "application/json")
    public CurrentpriceVO callCoindeskAPI() {
	    CurrentpriceVO vo = new CurrentpriceVO();
	    try {
	        
	        vo = coindeskAPIService.getCurrentPrice();
	        
        } catch (Exception e) {
            vo.setChartName("error");
            e.printStackTrace();
        }
		
        return vo;
        
    }
	
	
	
}
