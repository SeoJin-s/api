package com.sakila.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CityDto;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.repository.CityRepository;
import com.sakila.api.repository.CountryRepository;

@Service
@Transactional
public class CityService {
   private CityRepository cityRepository;
   private CountryRepository countryRepository;
   
   public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
      this.cityRepository = cityRepository;
      this.countryRepository = countryRepository;
   }
   
   // 입력
   public void save(CityDto cityDto) {
	   CityEntity saveCityEntity = new CityEntity();
	   saveCityEntity.setCity(cityDto.getCity());
	   
	   
	   // CountryEntity
	   CountryEntity countryEntity = countryRepository.findById(cityDto.getCountryId()).orElse(null);
	   saveCityEntity.setCountryEntity(countryEntity);
	   
	   
	   
	   cityRepository.save(saveCityEntity);
   }
   
   public List<CityEntity> findAll() {
      return cityRepository.findAll();
   }
}
