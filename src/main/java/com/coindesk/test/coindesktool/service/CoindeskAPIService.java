package com.coindesk.test.coindesktool.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coindesk.test.coindesktool.dao.CurrencyInfoDao;
import com.coindesk.test.coindesktool.entity.CurrencyInfo;
import com.coindesk.test.coindesktool.utils.AppUtils;
import com.coindesk.test.coindesktool.utils.DataMainteinException;
import com.coindesk.test.coindesktool.vo.CurrencyVO;
import com.coindesk.test.coindesktool.vo.CurrentpriceVO;
import com.google.gson.Gson;

@Service
public class CoindeskAPIService {

	@Autowired
    private HttpRequestService httpRequestService;
	
	@Autowired
	private CurrencyInfoDao currencyInfoDao;
	
	@Value("${coindesk.api.url}")
    private String url;
	
	
    public CurrentpriceVO getCurrentPrice() throws Exception {
        
        String apiResult = "";
        
        apiResult = httpRequestService.get(url);
        
        CurrentpriceVO vo = new CurrentpriceVO();
        
        Gson g = new Gson();
        
        vo = g.fromJson(apiResult, CurrentpriceVO.class);
        
        return vo;
    }
    
    public Map<String,Object> rebuildCoindeskAPIInfo() throws Exception {
        
        Map<String,Object> out = new HashMap<>();
        
        CurrentpriceVO coindeskVO = getCurrentPrice();
        
        if(coindeskVO == null) {
            throw new DataMainteinException("呼叫 coindesk API 發生異常");
        }
        
        Map<String,String> timeMap = coindeskVO.getTime();
        
        //轉換 UTC 時間
        String utcTimeStr = timeMap.get("updatedISO");
        //LocalDateTime utcTime = LocalDateTime.parse(utcTimeStr);
        LocalDateTime lt = AppUtils.parseISODateStr(utcTimeStr);
        
        out.put("updateTime", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(lt));
        
        Map<String, CurrencyVO> currencyVOMap = coindeskVO.getBpi();
        
        List<Map<String,Object>> listData = new ArrayList<>();
        for(String code : currencyVOMap.keySet()) {
            Map<String,Object> m = new HashMap<>();
            CurrencyVO vo = currencyVOMap.get(code);
            CurrencyInfo info = currencyInfoDao.findByCode(code);
            String chName = "";
            if(info == null || StringUtils.isBlank(info.getChName())) {
                chName ="N/A";
            }else {
                chName = info.getChName();
            }
            m.put("code", vo.getCode());
            m.put("name", chName);
            m.put("rate", vo.getRate_float());
            
            listData.add(m);
        }
        
        out.put("currencyList", listData);
        
        return out;
    }

}
