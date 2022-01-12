package com.coindesk.test.coindesktool.vo;

import java.util.Map;

public class CurrentpriceVO {

    private Map<String,String> time;
    
    private String disclaimer;
    
    private String chartName;
    
    Map<String,CurrencyVO> bpi;

    public Map<String, String> getTime() {
        return time;
    }

    public void setTime(Map<String, String> time) {
        this.time = time;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public Map<String, CurrencyVO> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, CurrencyVO> bpi) {
        this.bpi = bpi;
    }

    
    
}
