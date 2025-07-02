package com.sakila.api.entity;

// CountryEntity 의 맵핑 타입으로 사용
// CountryEntity Getter 생성 (필드 일부의 읽기 전용 타입)
public interface CountryMapping {
	int getCountryId(); // CountryEntity Getter 만 사용가능
	String getCountry();
}
