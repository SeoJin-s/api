package com.sakila.api.entity;

public interface CustomerMapping {
	int getCustomerId();
	String getFirstName();
	String getLastName();
}


// 4개의 엔티티 전체 리스트를 - > Mapping 타입의 전체 페이지 
// 리스트로 변경