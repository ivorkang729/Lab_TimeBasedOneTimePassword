package com.example.totp.receiver;

//import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.jboss.aerogear.security.otp.api.Clock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/message/")
public class TotpController {
	private static final Logger logger = LoggerFactory.getLogger(TotpController.class);
	
	private final String SHARED_SECRET = "My name is Oliver Kang. I'm King of myself.";
	private final FixedTotp totp;
	
    public TotpController() {
    	Clock customClock = new Clock(10);
    	this.totp = new FixedTotp(Base32.encode(SHARED_SECRET.getBytes()), customClock);
	}

	@GetMapping("/{clientTime}/{ourTotp}")
    public ResponseEntity<Object> receive(@PathVariable("clientTime") String clientTime, @PathVariable("ourTotp") String ourTotp) {
		boolean verifyResult = totp.verify(ourTotp);
		String msg = String.format("驗證: clientTime=%s, token=%s, verifyResult=%s", clientTime, ourTotp, verifyResult).toString();
		logger.info(msg);
		return new ResponseEntity<>(msg, HttpStatus.OK);
    }
	
}
