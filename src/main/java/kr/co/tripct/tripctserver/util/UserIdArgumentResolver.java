package kr.co.tripct.tripctserver.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import kr.co.tripct.tripctserver.config.jwt.JwtProperties;
import kr.co.tripct.tripctserver.user.dto.request.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Value("${jwt.secret}")
    public String SECRET;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == UserId.class;
    }

    @Override
    public UserId resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        try {
            String jwtToken = Objects.requireNonNull(webRequest.getHeader(JwtProperties.ACCESS_HEADER_STRING)).replace(JwtProperties.TOKEN_PREFIX, "");

            Long id = (JWT.require(Algorithm.HMAC512(SECRET)).build().verify(jwtToken).getClaim("id")).asLong();

            return new UserId(id);
        } catch (Exception e) {
            return null;
        }
    }
}
