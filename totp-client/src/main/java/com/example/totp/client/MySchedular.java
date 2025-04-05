package com.example.totp.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MySchedular {
	private static final Logger logger = LoggerFactory.getLogger(MySchedular.class);
	    
    private final TotpClientService service;
    
    @Autowired
    public MySchedular(TotpClientService service) {
        this.service = service;
    }
    
    @Scheduled(fixedRate = 2000) // 每2秒執行一次
    public void scheduleApiCall() {
    	service.callExternalApi();
    }
	
}
