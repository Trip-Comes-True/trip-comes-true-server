package kr.co.tripct.tripctserver.client.festival.dto.response;

import kr.co.tripct.tripctserver.client.festival.dto.data.FestivalData;
import lombok.Data;

import java.util.List;

@Data
public class FestivalResponse {
    private List<FestivalData> item;
    private int totalCnt;
    private String resultCode;
    private String errorMsg;
    private String resultMsg;

}