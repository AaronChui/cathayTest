package com.coindesk.test.coindesktool.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.coindesk.test.coindesktool.dao.CurrencyInfoDao;
import com.coindesk.test.coindesktool.entity.CurrencyInfo;
import com.coindesk.test.coindesktool.utils.DataMainteinException;


@Service
public class DataMaintainService {

    @Autowired
    CurrencyInfoDao currencyInfoDao;
	
	@Value("${coindesk.api.url}")
    private String url;
	
	CoindeskAPIService coindeskAPIService;
	
	
	
	public List<Map<String,Object>> findCurrencyInfoByCondition(CurrencyInfo condition){
	    
	    List<Map<String,Object>> list = currencyInfoDao.findCurrencyInfoByCondition(
	            condition.getCode(), condition.getEngName(), condition.getChName());
	    
	    return list;
	}
	
	/**
	 * 新增幣別資料
	 * @param newData
	 * @throws Exception
	 */
	public void insertNewCurrencyInfo(CurrencyInfo newData) throws Exception {
	    
	    if(newData == null) {
	        throw new DataMainteinException("資料為空值");
	    }
	    
	    if(StringUtils.isBlank(newData.getCode())) {
	        throw new DataMainteinException("缺少欄位或資料：貨幣英文代碼:code");
        }
	    
	    CurrencyInfo curData = currencyInfoDao.getById(newData.getCode());
        
        if(curData != null) {
            throw new DataMainteinException("此貨幣資訊已存在");
        }
	    
	    
	    newData.setUpdateTime(LocalDateTime.now());
	    currencyInfoDao.save(newData);
	}
	
	/**
	 * 刪除幣別資料
	 * @param deleteData
	 * @throws Exception
	 */
	public void deleteCurrencyInfo(CurrencyInfo deleteData) throws Exception {
        
	    if(deleteData == null) {
            throw new DataMainteinException("資料為空值");
        }
        
        if(StringUtils.isBlank(deleteData.getCode())) {
            throw new DataMainteinException("缺少欄位或資料：貨幣英文代碼:code");
        }
        
        CurrencyInfo curData = currencyInfoDao.getById(deleteData.getCode());
        
        if(curData == null) {
            throw new DataMainteinException("查無此貨幣");
        }
        
        currencyInfoDao.delete(curData);
    }
	/**
	 * 更新幣別資料
	 * @param updateData
	 * @throws Exception
	 */
	public void updateCurrencyInfo(CurrencyInfo updateData) throws Exception {
        
        if(updateData == null) {
            throw new DataMainteinException("資料為空值");
        }
        
        if(StringUtils.isBlank(updateData.getCode())) {
            throw new DataMainteinException("缺少欄位或資料：貨幣英文代碼:code");
        }
        
        CurrencyInfo curData = currencyInfoDao.getById(updateData.getCode());
        
        if(curData == null) {
            throw new DataMainteinException("查無此貨幣");
        }
        
        //有不同且非空值才更新
        if(StringUtils.isNotBlank(updateData.getChName()) &&
                ! StringUtils.equals(curData.getChName(), updateData.getChName())) {
            curData.setChName(updateData.getChName());
        }
        if(StringUtils.isNotBlank(updateData.getEngName()) &&
                ! StringUtils.equals(curData.getEngName(), updateData.getEngName())) {
            curData.setEngName(updateData.getEngName());
        }
        if(StringUtils.isNotBlank(updateData.getSymbol()) &&
                ! StringUtils.equals(curData.getSymbol(), updateData.getSymbol())) {
            curData.setSymbol(updateData.getSymbol());
        }
        
        curData.setUpdateTime(LocalDateTime.now());
        
        currencyInfoDao.save(curData);
    }
	
}
