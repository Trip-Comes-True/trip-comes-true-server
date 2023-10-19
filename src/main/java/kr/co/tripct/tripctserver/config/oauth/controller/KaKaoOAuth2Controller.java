package kr.co.tripct.tripctserver.config.oauth.controller;

import kr.co.tripct.tripctserver.config.oauth.dto.request.TokenRequest;
import kr.co.tripct.tripctserver.config.oauth.service.KaKaoOAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        log.info("자동 호출 되었습니다!");
        return authService.process(authCode);
    }

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody TokenRequest tokenRequest) {
        log.info(tokenRequest.getAccess_token());
        return authService.process(tokenRequest.getAccess_token());
    }

}
