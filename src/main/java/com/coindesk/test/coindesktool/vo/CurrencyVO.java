package com.coindesk.test.coindesktool.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

public class CurrencyVO {

    private String code;
    private String symbol;
    private BigDecimal rate_float;
    private String rate;
    private String description;
    
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public BigDecimal getRate_float() {
        return rate_float;
    }
    public void setRate_float(BigDecimal rate_float) {
        this.rate_float = rate_float;
    }
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
