package kr.co.tripct.tripctserver.config;

import kr.co.tripct.tripctserver.config.jwt.JwtAuthenticationFilter;
import kr.co.tripct.tripctserver.config.jwt.JwtAuthorizationFilter;
import kr.co.tripct.tripctserver.config.oauth.OAuth2DetailsService;
import kr.co.tripct.tripctserver.config.oauth.handler.OAuth2LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final OAuth2DetailsService oAuth2DetailsService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                // .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or
                // hasRole('ROLE_USER')")
                // .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and
                // hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .successHandler(oAuth2LoginSuccessHandler)
                .userInfoEndpoint().userService(oAuth2DetailsService);

        return http.build();
    }
}
