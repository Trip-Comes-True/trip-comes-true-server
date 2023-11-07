package kr.co.tripct.tripctserver.client.festival.dto.data;

import lombok.Data;

@Data
public class FestivalList {

    private FestivalData item;
    private int totalCnt;
    private String resultCode;
    private String errorMsg;
    private String resultMsg;
}
