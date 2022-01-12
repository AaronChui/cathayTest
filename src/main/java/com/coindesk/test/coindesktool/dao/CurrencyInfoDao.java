package com.coindesk.test.coindesktool.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coindesk.test.coindesktool.entity.CurrencyInfo;

public interface CurrencyInfoDao extends JpaRepository<CurrencyInfo, String> {

    
    @Query( value = " select "
            + " code, "
            + " ch_name, "
            + " eng_name, "
            + " symbol, "
            + " FORMATDATETIME( update_time , 'yyyy/MM/dd HH:mm:ss'  ) as update_time "
            + " from currency_info "
            + " where ( code = :code ) "
            + " or ( eng_name like  %:engName%  ) "
            + " or ( ch_name like %:chName% )",nativeQuery = true )
    public List<Map<String,Object>> findCurrencyInfoByCondition(
            @Param("code") String code,
            @Param("engName") String engName,
            @Param("chName") String chName
            );
    
    @Query( value = " select "
            + " code, "
            + " ch_name, "
            + " eng_name, "
            + " symbol, "
            + " FORMATDATETIME( update_time , 'yyyy/MM/dd HH:mm:ss'  ) as update_time "
            + " from currency_info ",nativeQuery = true )
    public List<Map<String,Object>> findCurrencyInfo();
    
    @Query(" select info from CurrencyInfo info where code = :code ")
    public CurrencyInfo findByCode(@Param("code") String code);
    
}
