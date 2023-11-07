package kr.co.tripct.tripctserver.client.festival.service;

import kr.co.tripct.tripctserver.client.festival.dto.response.FestivalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FestivalService {

    private final FestivalClient festivalClient;

    @Value("${iq.api.apikey}")
    private String apikey;


    public FestivalResponse getFestival(int page, int size) {

        FestivalResponse festivalDataList = festivalClient.getFestivalInfo(
                apikey,
                "culture",
                "json",
                page,
                size);

        return festivalDataList;
    }
}
