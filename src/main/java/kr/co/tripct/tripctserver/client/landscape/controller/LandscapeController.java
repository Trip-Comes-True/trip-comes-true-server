package kr.co.tripct.tripctserver.client.landscape.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/landscape")
//@Tag(name = "landscape api", description = "자연경관")
//public class LandscapeController {
//
//    @Operation(summary = "자연경관")
//    @GetMapping("")
//    public ResponseEntity<> getLandscape(@RequestParam int page, @RequestParam int size) {
//
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//    }
//
//}
