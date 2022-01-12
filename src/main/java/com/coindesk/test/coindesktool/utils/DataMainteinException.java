package com.coindesk.test.coindesktool.utils;

import java.util.HashMap;
import java.util.Map;

public class DataMainteinException extends Exception {

    private static final long serialVersionUID = 7431745984262749571L;
    
    private static final Map<Integer,String> alertMap;
    static {
        alertMap = new HashMap<>();
        alertMap.put(3000, "資料驗證錯誤");
        alertMap.put(3001, "新增資料錯誤");
        alertMap.put(3002, "更新資料錯誤");
        alertMap.put(9999, "例外錯誤");
    }

    public DataMainteinException(String message) {
        super(message);
    }
    
    
    
}
