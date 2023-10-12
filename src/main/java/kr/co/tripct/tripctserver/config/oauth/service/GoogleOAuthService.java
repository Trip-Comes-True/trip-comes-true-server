package kr.co.tripct.tripctserver.config.oauth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.tripct.tripctserver.config.jwt.JwtProvider;
import kr.co.tripct.tripctserver.config.oauth.dto.GoogleLoginDto;
import kr.co.tripct.tripctserver.config.oauth.dto.request.GoogleLoginRequest;
import kr.co.tripct.tripctserver.config.oauth.dto.response.GoogleLoginResponse;
import kr.co.tripct.tripctserver.user.repository.UserRepository;
import kr.co.tripct.tripctserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
@RequiredArgsConstructor
public class GoogleOAuthService implements OAuthService {

    @Value("${spring.google.auth.url}")
    private String authUrl;
    @Value("${spring.google.login.url}")
    private String loginUrl;

    @Value("${spring.google.client.id}")
    private String clientId;

    @Value("${spring.google.secret}")
    private String secret;

    @Value("${spring.google.redirect.url}")
    private String redirectUrl;

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final UserService userService;


    @Override
    public ResponseEntity<?> process(String authCode) throws JsonProcessingException {
        GoogleLoginRequest googleTokenRequest = getGoogleTokenRequest(authCode);

        GoogleLoginResponse googleLoginResponse = getGoogleToken(googleTokenRequest);

        return createToken(googleLoginResponse);
    }


    @Override
    public String getLoginUrl() {
        return loginUrl + "/o/oauth2/v2/auth?client_id=" + clientId +
                "&redirect_uri=" + redirectUrl + "&response_type=code&scope=email%20profile";
    }

    private ResponseEntity<?> createToken(GoogleLoginResponse loginResponse) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestUrl = UriComponentsBuilder.fromHttpUrl(authUrl + "/tokeninfo").queryParam("id_token", loginResponse.getId_token()).toUriString();
        String resultJson = restTemplate.getForObject(requestUrl, String.class);

        if(resultJson != null) {
            GoogleLoginDto userInfoDto = objectMapper.readValue(resultJson, GoogleLoginDto.class);
            return ResponseEntity.ok(OAuthService.super.getTokenResponse(userInfoDto.getEmail(), userRepository, jwtProvider, userService));
        }
        return ResponseEntity.notFound().build();
    }

    private GoogleLoginRequest getGoogleTokenRequest(String authCode) {
        return GoogleLoginRequest.builder()
                    .clientId(clientId)
                    .clientSecret(secret)
                    .code(authCode)
                    .redirectUri(redirectUrl)
                    .grantType("authorization_code")
                    .build();
    }

    public GoogleLoginResponse getGoogleToken(GoogleLoginRequest googleTokenRequest) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GoogleLoginResponse> googleLoginResponse = restTemplate.postForEntity(authUrl + "/token", googleTokenRequest, GoogleLoginResponse.class);
        return googleLoginResponse.getBody();
    }

}
