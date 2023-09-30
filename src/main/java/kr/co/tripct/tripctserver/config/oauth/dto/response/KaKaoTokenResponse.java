package kr.co.tripct.tripctserver.config.oauth.dto.response;

import lombok.Data;

@Data
public class KaKaoTokenResponse {
    private String token_type;
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private Integer refresh_token_expires_in;
}
