package kr.co.tripct.tripctserver.client.festival.service;

import kr.co.tripct.tripctserver.client.festival.dto.response.FestivalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FestivalService {

    private final FestivalClient festivalClient;

    @Value("${iq.api.apikey}")
    private String apikey;


    public List<FestivalResponse> getFestival(int page, int size) {

        List<FestivalResponse> festivalDataList = festivalClient.getFestivalInfo(
                apikey,
                "culture",
                "json",
                page,
                size);

        return festivalDataList;
    }
}
