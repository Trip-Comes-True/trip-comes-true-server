package kr.co.tripct.tripctserver.client.festival.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.tripct.tripctserver.client.festival.dto.data.FestivalData;
import kr.co.tripct.tripctserver.client.festival.dto.response.FestivalResponse;
import kr.co.tripct.tripctserver.client.festival.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/festival")
@Tag(name = "festival api", description = "인천 행사")
public class FestivalController {

    private final FestivalService festivalService;

    @Operation(summary = "인천 지역 문화 예술 행사")
    @GetMapping("/")
    public ResponseEntity<List<FestivalResponse>> getFestival(@RequestParam int page, @RequestParam int size) {
        List<FestivalResponse> data = festivalService.getFestival(page, size);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
