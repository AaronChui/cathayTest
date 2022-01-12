package com.coindesk.test.coindesktool;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest( classes = CoindeskToolApplication.class )
@WebAppConfiguration
@FixMethodOrder(MethodSorters.JVM)
class CoindeskToolApplicationTests {

	@Test
	void contextLoads() {
	    System.out.println("單元測試");
	}
	
	private static final String URI_API = "/coindeskAPI";
	private static final String URI_API_REBUILD = "/info";
	
	private static final String URI_INSERT = "/newCurrency";
	private static final String URI_UPDATE = "/updateCurrency";
	private static final String URI_DELETE = "/deleteCurrency";
	private static final String URI_QUERY = "/searchCurrency";
	
	private MockMvc mvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Before
    public void initTestData() throws Exception {
        JSONObject usData = new JSONObject();
        usData.put("code", "USD");
        usData.put("chName", "美元");
        usData.put("engName", "United States Dollar");
        
        JSONObject euData = new JSONObject();
        euData.put("code", "EUR");
        euData.put("chName", "歐元");
        euData.put("engName", "Euro");
        getTestData(URI_INSERT, usData);
        getTestData(URI_INSERT, euData);
        
    }
    
    
    /**
     * 測試 Coindesk API 本體
     * @throws Exception
     */
    @Test
    public void testCoinddeskAPI() throws Exception {
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI_API).headers(httpHeaders);
        
        MvcResult mvcRs = mvc.perform(requestBuilder).andReturn();
        
        String json = mvcRs.getResponse().getContentAsString();
        int code = mvcRs.getResponse().getStatus();
        
        
        System.out.println("###testCoinddeskAPI - " + json);
        Assert.assertEquals("http response error !", 200, code);
        
        
        
    }
    /**
     * 測試重新整理後的 coindeskAPI
     * @throws Exception
     */
    @Test
    public void testCoinddeskRebuildAPI() throws Exception {
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI_API_REBUILD).headers(httpHeaders);
        
        MvcResult mvcRs = mvc.perform(requestBuilder).andReturn();
        
        String json = mvcRs.getResponse().getContentAsString();
        int code = mvcRs.getResponse().getStatus();
        
        
        System.out.println("###testCoinddeskRebuildAPI:" + json);
        Assert.assertEquals("http response error !", 200, code);
        
    }
    
    @Test
    public void testQuery()  throws Exception {
        
        JSONObject inputJSON = new JSONObject();
        
        //查詢美元
        inputJSON.put("code", "USD");
        
        Map<String,Object> testData = getTestData(URI_QUERY,inputJSON);
        
        System.out.println("###testQuery:" + testData.get("json"));
        int code = (Integer) testData.get("code");
        Assert.assertEquals("http response error !", 200, code);
        
    }
    
    
    @Test
    public void testInsert() throws Exception {
        
        JSONObject inputJSON = new JSONObject();
        
        //新增印尼盾
        inputJSON.put("code", "IDR");
        inputJSON.put("chName", "印尼盾");
        inputJSON.put("engName", "Indonesian rupiah");
        
        Map<String,Object> testData = getTestData(URI_INSERT,inputJSON);
        
        System.out.println("###testInsert:" + testData.get("json"));
        int code = (Integer) testData.get("code");
        Assert.assertEquals("http response error !", 200, code);
    }
    
    @Test
    public void testUpdate() throws Exception {

        JSONObject inputJSON = new JSONObject();
        
        //修改美元中文名稱
        inputJSON.put("code", "USD");
        inputJSON.put("chName", "USSSSSS");
        
        Map<String,Object> testData = getTestData(URI_UPDATE,inputJSON);
        
        int code = (Integer) testData.get("code");
        Assert.assertEquals("http response error !", 200, code);
        
        
        //查詢美元檢視結果
        testData = getTestData(URI_QUERY,inputJSON);
        System.out.println("###testUpdate - updateResult: " + testData.get("json"));
    }
    
    @Test
    public void testDelete()  throws Exception {
        
        JSONObject inputJSON = new JSONObject();
        
        //刪除歐元
        inputJSON.put("code", "EUR");
        
        Map<String,Object> testData = getTestData(URI_DELETE,inputJSON);
        
        System.out.println("###testDelete:" + testData.get("json"));
        int code = (Integer) testData.get("code");
        Assert.assertEquals("http response error !", 200, code);
        
    }
    
    private Map<String,Object> getTestData(String uri , JSONObject inputJSON) throws Exception {
        

        Map<String,Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("json", "");
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri).headers(httpHeaders)
                .content(inputJSON.toString());
        
        MvcResult mvcRs = mvc.perform(requestBuilder).andReturn();
        
        String json = mvcRs.getResponse().getContentAsString();
        int code = mvcRs.getResponse().getStatus();
        
        result.put("code", code);
        result.put("json", json);
        
        return result;
    }

}
