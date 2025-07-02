package com.sakila.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.CityEntity;
import com.sakila.api.entity.CityMapping;
import com.sakila.api.entity.CountryEntity;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
   Long countByCountryEntity(CountryEntity countryEntity);
   Page<CityMapping> findAllBy(Pageable pageable);
   // select count(*) from city where country_id = ?
}