package com.example.totp.client;

import java.time.LocalDateTime;

import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.jboss.aerogear.security.otp.api.Clock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotpClientService {
private static final Logger logger = LoggerFactory.getLogger(TotpClientService.class);

	private final String SHARED_SECRET = "My name is Oliver Kang. I'm King of myself.";
	private final Totp totp;
    
    private final TotpReceiverApi api;
    
    @Autowired
    public TotpClientService(TotpReceiverApi api) {
        this.api = api;
        
        // 初始化 Totp Object
        Clock customClock = new Clock(10);
        this.totp = new Totp(Base32.encode(SHARED_SECRET.getBytes()), customClock);
    }
    
    public void callExternalApi() {
        try {
            String currentTime = LocalDateTime.now().toString();
            String currentTotp = generateTotp();
            logger.info("發送請求至外部API，clientTime: {}, ourTotp: {}", currentTime, currentTotp);
            String response = api.callApi(currentTime, currentTotp);
            logger.info("收到回應: {}", response);
        } catch (Exception e) {
            logger.error("呼叫API時發生錯誤", e);
        }
    }
    
    private String generateTotp() {
    	return totp.now();
    }
    
}
