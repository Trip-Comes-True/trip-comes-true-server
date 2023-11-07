package kr.co.tripct.tripctserver.client.festival.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FestivalList {

    private FestivalData item;
    private int totalCnt;
    private String resultCode;
    private String errorMsg;
    private String resultMsg;
}
