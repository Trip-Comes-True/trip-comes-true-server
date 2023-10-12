package kr.co.tripct.tripctserver.config.jwt;

import lombok.Getter;

@Getter
public class JwtResponseDto {

    private String accessToken;
    private String refreshToken;

    public JwtResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
