package kr.co.tripct.tripctserver.config.oauth.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KaKaoLoginRequest {
    private String client_id;
    private String redirect_uri;
    private String grant_type;
    private String code;
    private String client_secret;

}
