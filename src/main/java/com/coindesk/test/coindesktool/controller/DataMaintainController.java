package com.coindesk.test.coindesktool.controller;

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

import com.coindesk.test.coindesktool.dao.CurrencyInfoDao;
import com.coindesk.test.coindesktool.entity.CurrencyInfo;
import com.coindesk.test.coindesktool.service.CoindeskAPIService;
import com.coindesk.test.coindesktool.service.DataMaintainService;
import com.coindesk.test.coindesktool.utils.DataMainteinException;
import com.coindesk.test.coindesktool.vo.CurrencyVO;
import com.coindesk.test.coindesktool.vo.CurrentpriceVO;
import com.coindesk.test.coindesktool.vo.NormalResponseVO;
import com.google.gson.Gson;

@RestController
public class DataMaintainController {

    @Autowired
    DataMaintainService dataMaintainService;
	
    
	@PostMapping(
            value = "/searchCurrency", consumes = "application/json", produces = "application/json")
    public NormalResponseVO searchCurrency(@RequestBody CurrencyInfo condition ,HttpServletResponse response) {
        
        NormalResponseVO respVO = new NormalResponseVO();
        
        try {
            
            //欄位/資料檢核
            List<Map<String,Object>> list = dataMaintainService.findCurrencyInfoByCondition(condition);
            respVO.setMessage("執行完成");
            respVO.setSuccess(true);
            respVO.setData(list);
            
        }catch (Exception e) {
            e.printStackTrace();
            respVO.setMessage("例外錯誤:" + e.getMessage());
            respVO.setSuccess(false);
        }
        
        return respVO;
        
    }
	
	/**
	 * 新增幣別
	 * @param newData
	 * @param response
	 * @return
	 */
	@PostMapping(
	        value = "/newCurrency", consumes = "application/json", produces = "application/json")
    public NormalResponseVO newCurrency(@RequestBody CurrencyInfo newData ,HttpServletResponse response) {
	    
	    NormalResponseVO respVO = new NormalResponseVO();
	    
	    try {
	        
	        dataMaintainService.insertNewCurrencyInfo(newData);
	        respVO.setMessage("執行完成");
            respVO.setSuccess(true);
            respVO.setData("");
	        
        }catch(DataMainteinException dataE) {
            respVO.setMessage(dataE.getMessage());
            respVO.setSuccess(false);
        }catch (Exception e) {
            e.printStackTrace();
            respVO.setMessage("例外錯誤:" + e.getMessage());
            respVO.setSuccess(false);
        }
		
        return respVO;
        
    }
	
	/**
     * 更新幣別
     * @param updateData
     * @param response
     * @return
     */
    @PostMapping(
            value = "/updateCurrency", consumes = "application/json", produces = "application/json")
    public NormalResponseVO updateCurrency(@RequestBody CurrencyInfo updateData ,HttpServletResponse response) {
        
        NormalResponseVO respVO = new NormalResponseVO();
        
        try {
            
            dataMaintainService.updateCurrencyInfo(updateData);
            respVO.setMessage("執行完成");
            respVO.setSuccess(true);
            respVO.setData("");
            
        }catch(DataMainteinException dataE) {
            respVO.setMessage(dataE.getMessage());
            respVO.setSuccess(false);
        }catch (Exception e) {
            e.printStackTrace();
            respVO.setMessage("例外錯誤:" + e.getMessage());
            respVO.setSuccess(false);
        }
        
        return respVO;
        
    }
    
    /**
     * 刪除幣別
     * @param deleteData
     * @param response
     * @return
     */
    @PostMapping(
            value = "/deleteCurrency", consumes = "application/json", produces = "application/json")
    public NormalResponseVO deleteCurrency(@RequestBody CurrencyInfo deleteData ,HttpServletResponse response) {
        
        NormalResponseVO respVO = new NormalResponseVO();
        
        try {
            
            dataMaintainService.deleteCurrencyInfo(deleteData);
            respVO.setMessage("執行完成");
            respVO.setSuccess(true);
            respVO.setData("");
            
        }catch(DataMainteinException dataE) {
            respVO.setMessage(dataE.getMessage());
            respVO.setSuccess(false);
        }catch (Exception e) {
            e.printStackTrace();
            respVO.setMessage("例外錯誤:" + e.getMessage());
            respVO.setSuccess(false);
        }
        
        return respVO;
        
    }
	
	
	
}
