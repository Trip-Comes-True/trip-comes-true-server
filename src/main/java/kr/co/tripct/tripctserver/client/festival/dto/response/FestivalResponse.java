package kr.co.tripct.tripctserver.client.festival.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.co.tripct.tripctserver.client.festival.dto.data.FestivalData;
import lombok.Data;

import java.util.List;

@Data
public class FestivalResponse {
    @JsonProperty("item")
    private List<FestivalData> item;
    @JsonProperty("totalCnt")
    private int totalCnt;
    @JsonProperty("resultCode")
    private String resultCode;
    @JsonProperty("errorMsg")
    private String errorMsg;
    @JsonProperty("resultMsg")
    private String resultMsg;

}