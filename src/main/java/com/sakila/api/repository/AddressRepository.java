package com.sakila.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.AddressMapping;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
	Page<AddressMapping> findAllBy(Pageable pageable);
}
