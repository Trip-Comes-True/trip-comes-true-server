package kr.co.tripct.tripctserver.client.festival.dto.data;

import lombok.Data;

@Data
public class FestivalData {

    private Long idx;
    private String title;
    private String link;
    private String category;
    private String sdate;
    private String edate;
    private String place;
    private String placeSido;
    private String placeGugun;
    private String management;
    private String feeCase;
    private String fee;
    private String tel;
    private String homepage;
    private String poster;
    private String posterThumb;
    private String description;
    private String reserveInfo;
    private String reserveUrl;
    private String pubDate;

    public FestivalData() {
        this.idx = idx;
        this.title = title;
        this.link = link;
        this.category = category;
        this.sdate = sdate;
        this.edate = edate;
        this.place = place;
        this.placeSido = placeSido;
        this.placeGugun = placeGugun;
        this.management = management;
        this.feeCase = feeCase;
        this.fee = fee;
        this.tel = tel;
        this.homepage = homepage;
        this.poster = poster;
        this.posterThumb = posterThumb;
        this.description = description;
        this.reserveInfo = reserveInfo;
        this.reserveUrl = reserveUrl;
        this.pubDate = pubDate;
    }
}
