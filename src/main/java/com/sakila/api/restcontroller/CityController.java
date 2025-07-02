package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sakila.api.dto.CityDto;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.entity.CityMapping;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.service.CityService;

@RestController
@CrossOrigin
public class CityController {
   private final CityService cityService;

   public CityController(CityService cityService) {
      this.cityService = cityService;
   }
   
   // 단일조회
   @GetMapping("/cityOne/{cityId}")
   public ResponseEntity<CityEntity> cityOne(@PathVariable int cityId) {
       return new ResponseEntity<>(cityService.findById(cityId), HttpStatus.OK);
   }

   //  전체 조회
   @GetMapping("/cityList/{currentPage}")
   public ResponseEntity<Page<CityMapping>> city(@PathVariable int currentPage) {
	   return new ResponseEntity<>(cityService.findAll(currentPage), HttpStatus.OK);
   }

   // 입력
   @PostMapping("/city")
   public ResponseEntity<String> insertCity(@RequestBody CityDto cityDto) {
      System.out.println(cityDto.toString());
      cityService.save(cityDto);
      return new ResponseEntity<>("입력성공", HttpStatus.OK);
   }

   // 수정
   @PatchMapping("/city")
   public ResponseEntity<String> updateCity(@RequestBody CityDto cityDto) {
      cityService.update(cityDto);
      return new ResponseEntity<>("수정성공", HttpStatus.OK);
   }

   // ▶ 삭제
   @DeleteMapping("/city/{cityId}")
   public ResponseEntity<String> deleteCity(@PathVariable int cityId) {
      boolean result = cityService.delete(cityId); // true or false 리턴
      if (result) {
         return new ResponseEntity<>("삭제성공", HttpStatus.OK);
      }
      return new ResponseEntity<>("삭제실패", HttpStatus.BAD_REQUEST);
   }
}
