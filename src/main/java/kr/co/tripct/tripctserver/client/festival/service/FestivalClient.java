package kr.co.tripct.tripctserver.client.festival.service;

import kr.co.tripct.tripctserver.client.config.FeignMapperConfig;
import kr.co.tripct.tripctserver.client.festival.dto.response.FestivalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "tour-api", url = "${iq.api.url}", configuration = FeignMapperConfig.class)
public interface FestivalClient {


    @GetMapping(value = "")
    List<FestivalResponse> getFestivalInfo(@RequestParam("apiKey") String apiKey,
                                           @RequestParam("svID") String svID,
                                           @RequestParam("resultType") String resultType,
                                           @RequestParam("cPage") int cPage,
                                           @RequestParam("pSize") int pSize);
}
