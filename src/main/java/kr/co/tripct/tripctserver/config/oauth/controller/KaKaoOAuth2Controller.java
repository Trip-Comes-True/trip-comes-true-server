package kr.co.tripct.tripctserver.config.oauth.controller;

import kr.co.tripct.tripctserver.config.oauth.service.KaKaoOAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/kakao/login")
public class KaKaoOAuth2Controller {

    private final KaKaoOAuthService authService;

    @GetMapping("")
    public ResponseEntity<?> getKaKaoAuthUrl() {
        String reqUrl = authService.getLoginUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(reqUrl));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/callback")
    public ResponseEntity<?> KaKaoOAuthProcess(@RequestParam(value = "code") String authCode) {
        return authService.process(authCode);
    }

}
