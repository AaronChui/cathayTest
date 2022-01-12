package com.coindesk.test.coindesktool;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoindeskToolApplication {

    
    @Autowired
    DataSource dataSource;
    
    
	public static void main(String[] args) {
		SpringApplication.run(CoindeskToolApplication.class, args);
	}

}
