package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CountryDto;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.repository.CityRepository;
import com.sakila.api.repository.CountryRepository;

@Service
@Transactional
public class CountryService {
   private CountryRepository countryRepository;
   private CityRepository cityRepository;
   
   // 필드주입 대신 생성자 주입을 사용
   public CountryService(CountryRepository countryRepository) {
      this.countryRepository = countryRepository;
   }
   
   // Country 삭제
   public void delete(int countryId) {
	   // 자식테이블에 참조하는 행이 없다면...? (select count(*) form city where country_id = ? )
	   
	   if(0 == cityRepository.countByCountryEntity(countryRepository.findById(countryId).orElse(null))) {
	    countryRepository.deleteById(countryId);
	    
	   } else {
	   System.out.println("자식테이블");
   }
   
   // Country 수정
   public void update(CountryDto countryDto) {
	   CountryEntity updateCountryEntity = countryRepository.findById(countryDto.getCountryId()).orElse(null);
	   updateCountryEntity.setCountry(countryDto.getCountry());
   }
   
   // CountryEntity 입력
   public void save(CountryDto countryDto) {
	   CountryEntity saveCountryEntity = new CountryEntity();
	   saveCountryEntity.setCountry(countryDto.getCountry());
	   countryRepository.save(saveCountryEntity);
   }
   
   
   // 전체조회
   public List<CountryEntity> findAll() {
      return countryRepository.findAll();
   }
   
}
