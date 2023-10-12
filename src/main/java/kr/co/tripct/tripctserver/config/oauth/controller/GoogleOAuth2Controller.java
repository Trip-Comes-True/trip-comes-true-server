package kr.co.tripct.tripctserver.config.oauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.tripct.tripctserver.config.oauth.service.GoogleOAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController()
@RequestMapping("/google/login")
@RequiredArgsConstructor
@Slf4j
public class GoogleOAuth2Controller {

    private final GoogleOAuthService authService;

    @GetMapping("")
    public ResponseEntity<?> getGoogleAuthUrl() {
        String reqUrl = authService.getLoginUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(reqUrl));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/redirect")
    public ResponseEntity<?> googleOauthCheck(@RequestParam(value = "code") String authCode) throws JsonProcessingException {
        return authService.process(authCode);
    }
}
