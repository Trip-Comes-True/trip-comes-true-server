package kr.co.tripct.tripctserver.client.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiClientConfig {

    private ObjectMapper customObjectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

    @Bean
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(new JacksonDecoder(customObjectMapper)); // 디코딩 과정에서 사용할 수 있는 Decoder Bean을 생성
    }

    @Bean
    public Encoder feignEncoder() {
        return new JacksonEncoder(customObjectMapper); //인코딩 과정에서 사용할 수 있는 Encoder Bean을 생성
    }
}
