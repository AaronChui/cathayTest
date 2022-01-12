package com.coindesk.test.coindesktool.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpRequestService {

    @Autowired
    private RestTemplate restTemplate;
    
    public String get(String url) throws Exception {
        
        String resp = "";
        resp = restTemplate.getForObject(url, String.class);
        return resp;
        
    }
    
    
    public JSONObject postJSON(  String url, JSONObject params ){
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(url, params ,JSONObject.class);
        
        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
        
        
    }
    
    
}
