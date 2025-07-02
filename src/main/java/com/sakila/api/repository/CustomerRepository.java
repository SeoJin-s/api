package com.sakila.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.entity.CustomerMapping;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer > {
	Page<CustomerMapping> findAllBy(Pageable pageable);
}
