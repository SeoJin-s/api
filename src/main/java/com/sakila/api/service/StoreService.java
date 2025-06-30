package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.StoreDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.StoreRepository;

@Service
@Transactional
public class StoreService {
	private final StoreRepository storeRepository;
	private final AddressRepository addressRepository;

	public StoreService(StoreRepository storeRepository, AddressRepository addressRepository) {
		this.storeRepository = storeRepository;
		this.addressRepository = addressRepository;
	}

	// 단일 조회
	public StoreEntity findById(int storeId) {
		return storeRepository.findById(storeId).orElse(null);
	}

	// 전체 조회
	public List<StoreEntity> findAll() {
		return storeRepository.findAll();
	}

	// 등록
	public void save(StoreDto storeDto) {
		StoreEntity store = new StoreEntity();
		store.setManagerStaffId(storeDto.getManagerStaffId());

		AddressEntity address = addressRepository.findById(storeDto.getAddressId()).orElse(null);
		store.setAddressEntity(address);

		storeRepository.save(store);
	}

	// 수정
	public void update(StoreDto storeDto) {
		StoreEntity store = storeRepository.findById(storeDto.getStoreId())
			.orElseThrow(() -> new IllegalArgumentException("해당 매장 없음"));

		store.setManagerStaffId(storeDto.getManagerStaffId());

		AddressEntity address = addressRepository.findById(storeDto.getAddressId()).orElse(null);
		store.setAddressEntity(address);
	}

	// 삭제
	public boolean delete(int storeId) {
		if (storeRepository.existsById(storeId)) {
			storeRepository.deleteById(storeId);
			return true;
		}
		return false;
	}
}
