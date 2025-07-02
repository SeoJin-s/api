package com.sakila.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CustomerDto;
import com.sakila.api.entity.AddressEntity;

import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.entity.CustomerMapping;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CustomerRepository;
import com.sakila.api.repository.StoreRepository;

@Service
@Transactional
public class CustomerService {
	private final CustomerRepository customerRepository;
	private final StoreRepository storeRepository;
	private final AddressRepository addressRepository;

	public CustomerService(CustomerRepository customerRepository, StoreRepository storeRepository, AddressRepository addressRepository) {
		this.customerRepository = customerRepository;
		this.storeRepository = storeRepository;
		this.addressRepository = addressRepository;
	}

	// 단일 조회
	public CustomerEntity findById(int customerId) {
		return customerRepository.findById(customerId).orElse(null);
	}

	// 전체 조회
	public Page<CustomerMapping> findAll(int currentPage) {
		  int pageSize = 10;
		  int pageNumber = currentPage - 1;
		  
		  Sort sort = Sort.by("customerId").ascending();
		  PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);
	      return customerRepository.findAllBy(pageable);
	   }

	// 등록
	public void save(CustomerDto customerDto) {
		CustomerEntity customer = new CustomerEntity();
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setActive(customerDto.getActive());

		StoreEntity store = storeRepository.findById(customerDto.getStoreId()).orElse(null);
		customer.setStore(store);

		AddressEntity address = addressRepository.findById(customerDto.getAddressId()).orElse(null);
		customer.setAddress(address);

		customerRepository.save(customer);
	}

	// 수정
	public void update(CustomerDto customerDto) {
		CustomerEntity customer = customerRepository.findById(customerDto.getCustomerId())
			.orElseThrow(() -> new IllegalArgumentException("해당 고객 없음"));

		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setActive(customerDto.getActive());

		StoreEntity store = storeRepository.findById(customerDto.getStoreId()).orElse(null);
		customer.setStore(store);

		AddressEntity address = addressRepository.findById(customerDto.getAddressId()).orElse(null);
		customer.setAddress(address);
	}

	// 삭제
	public boolean delete(int customerId) {
		if (customerRepository.existsById(customerId)) {
			customerRepository.deleteById(customerId);
			return true;
		}
		return false;
	}
}
