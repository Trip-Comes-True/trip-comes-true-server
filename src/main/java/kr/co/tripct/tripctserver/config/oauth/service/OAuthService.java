package kr.co.tripct.tripctserver.config.oauth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.tripct.tripctserver.config.jwt.JwtProvider;
import kr.co.tripct.tripctserver.config.oauth.dto.response.TokenResponse;
import kr.co.tripct.tripctserver.user.domain.UserEntity;
import kr.co.tripct.tripctserver.user.repository.UserRepository;
import kr.co.tripct.tripctserver.user.service.UserService;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface OAuthService {

    ResponseEntity<?> process(String authCode) throws JsonProcessingException;
    default TokenResponse getTokenResponse(String email, UserRepository userRepository, JwtProvider jwtProvider, UserService userService) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            UserEntity userEntity = user.get();
            String accessToken = jwtProvider.accessTokenCreate(userEntity.getId());
            String refreshToken = jwtProvider.refreshTokenCreate(userEntity.getId());
            return new TokenResponse(accessToken, refreshToken);
        } else {
            UserEntity userEntity = new UserEntity(email);
            userService.registerOAuthUser(userEntity);
            String accessToken = jwtProvider.accessTokenCreate(userEntity.getId());
            String refreshToken = jwtProvider.refreshTokenCreate(userEntity.getId());
            return new TokenResponse(accessToken, refreshToken);
        }
    }
    String getLoginUrl();
}
