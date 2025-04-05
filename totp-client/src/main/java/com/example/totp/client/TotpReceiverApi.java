package com.example.totp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "totpReceiverApi", url = "${api.totpReceiver.url}")
public interface TotpReceiverApi {
	
	@GetMapping("/api/message/{clientTime}/{ourTotp}")
    String callApi(@PathVariable("clientTime") String clientTime, @PathVariable("ourTotp") String ourTotp);
	
}
