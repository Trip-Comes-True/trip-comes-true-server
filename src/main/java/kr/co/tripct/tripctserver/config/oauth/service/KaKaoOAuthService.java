package kr.co.tripct.tripctserver.config.oauth.service;

import kr.co.tripct.tripctserver.config.jwt.JwtProvider;
import kr.co.tripct.tripctserver.config.oauth.dto.KaKaoLoginDto;
import kr.co.tripct.tripctserver.config.oauth.dto.request.KaKaoLoginRequest;
import kr.co.tripct.tripctserver.config.oauth.dto.response.KaKaoTokenResponse;
import kr.co.tripct.tripctserver.config.oauth.dto.response.TokenResponse;
import kr.co.tripct.tripctserver.user.repository.UserRepository;
import kr.co.tripct.tripctserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
@RequiredArgsConstructor
public class KaKaoOAuthService implements OAuthService {

    @Value("${spring.kakao.login.url}")
    private String loginUrl;

    @Value("${spring.kakao.client.id}")
    private String clientId;

    @Value("${spring.kakao.redirect.url}")
    private String redirectUrl;

    @Value("${spring.kakao.auth.url}")
    private String authUrl;

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Override
    public ResponseEntity<?> process(String authCode) {
        KaKaoLoginRequest kaKaoLoginRequest = KaKaoLoginRequest.builder()
                .client_id(clientId)
                .code(authCode)
                .redirect_uri(redirectUrl)
                .grant_type("authorization_code")
                .build();
        KaKaoTokenResponse kaKaoTokenResponse = getToken(kaKaoLoginRequest);
        log.info("카카오 토큰 Id 값 : " + kaKaoTokenResponse.getAccess_token());

        return createToken(kaKaoTokenResponse);
    }

    @Override
    public String getLoginUrl() {
        return loginUrl + "/oauth/authorize?client_id=" + clientId +
                "&redirect_uri=" + redirectUrl + "&response_type=code";
    }

    public ResponseEntity<?> createToken(KaKaoTokenResponse kaKaoTokenResponse) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + kaKaoTokenResponse.getAccess_token());

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"kakao_account.email\"]");

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
        KaKaoLoginDto kaKaoLoginDto = restTemplate.postForObject(authUrl, request, KaKaoLoginDto.class);

        if(kaKaoLoginDto != null) {
            return ResponseEntity.ok(OAuthService.super.getTokenResponse(kaKaoLoginDto.getEmail(), userRepository, jwtProvider, userService));
        }

        return ResponseEntity.ok(null);
    }

    public KaKaoTokenResponse getToken(KaKaoLoginRequest kaKaoLoginRequest) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        String[] fieldNames = {"client_id", "grant_type", "redirect_uri", "code"};
        for (String fieldName : fieldNames) {
            String fieldValue = getFieldByName(kaKaoLoginRequest, fieldName);
            body.add(fieldName, fieldValue);
        }

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<KaKaoTokenResponse> responseEntity = restTemplate.exchange(loginUrl + "/oauth/token", HttpMethod.POST, request, KaKaoTokenResponse.class);

        return responseEntity.getBody();
    }

    private String getFieldByName(KaKaoLoginRequest kaKaoLoginRequest, String fieldName) {
        switch (fieldName) {
            case "client_id":
                return kaKaoLoginRequest.getClient_id();
            case "grant_type":
                return "authorization_code";
            case "redirect_uri":
                return kaKaoLoginRequest.getRedirect_uri();
            case "code":
                log.info("인가코드임 : " + kaKaoLoginRequest.getCode());
                return kaKaoLoginRequest.getCode();
            default:
                return "";
        }
    }

    public ResponseEntity<TokenResponse> login(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"kakao_account.email\"]");

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
        KaKaoLoginDto kaKaoLoginDto = restTemplate.postForObject(authUrl, request, KaKaoLoginDto.class);

        if(kaKaoLoginDto != null) {
            return ResponseEntity.ok(OAuthService.super.getTokenResponse(kaKaoLoginDto.getEmail(), userRepository, jwtProvider, userService));
        }

        return ResponseEntity.ok(null);
    }
}
