package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.dto.CountryDto;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.service.CountryService;

@RestController
public class CountryController {
   private CountryService countryService;
   
   // 필드주입 대신 생성자 주입을 사용
   public CountryController(CountryService countryService) {
      this.countryService = countryService;
   }
   
   // 삭제
   @DeleteMapping("/country/{countryId}")
   public ResponseEntity<String> deleteCountry(@PathVariable int countryId) {
	   
	   boolean result = countryService.delete(countryId); // 이슈 : 자식테이블의
	   if(result) {
	   return new ResponseEntity<String>("삭제성공", HttpStatus.OK);
   }
	   return new ResponseEntity<String>("삭제실패", HttpStatus.OK);
   } 
   
   // 수정
   @PatchMapping("/country")
   public ResponseEntity<String> updateCountry(@RequestBody CountryDto countryDto) {
	   countryService.update(countryDto);
	   return new ResponseEntity<String>("수정성공", HttpStatus.OK);
   }
   
   
   @PostMapping("/country")
   public ResponseEntity<String> country(@RequestBody CountryDto countryDto) {
	   // json -> CountryDto로 자동 매핑됨
	   
	   System.out.println(countryDto.toString());
	   countryService.save(countryDto);
	   
	   return new ResponseEntity<String>("입력성공", HttpStatus.OK);
   }
   
   // @RequestBody: 클라이언트에서 보낸 JSON 바디를 Java 객체로 변환
   // @ResponseBody: Java 객체를 JSON으로 응답할 때 사용 (지금처럼 @RestController면 자동 포함됨이라 필요 없음)
   
   
   @GetMapping("/country")
   public ResponseEntity<List<CountryEntity>> country() {
      return new ResponseEntity<List<CountryEntity>>(countryService.findAll(), HttpStatus.OK);
   }
}
