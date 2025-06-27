package com.sakila.api.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.AddressDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CityRepository;

@Service
@Transactional
public class AddressService {
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;

    public AddressService(AddressRepository addressRepository, CityRepository cityRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
    }

    public List<AddressEntity> findAll() {
        return addressRepository.findAll();
    }

    // 등록
    public void save(AddressDto addressDto) {
        AddressEntity entity = new AddressEntity();
        entity.setAddress(addressDto.getAddress());
        entity.setAddress2(addressDto.getAddress2());
        entity.setDistrict(addressDto.getDistrict());
        entity.setPostalCode(addressDto.getPostalCode());
        entity.setPhone(addressDto.getPhone());
        entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));

        // cityId → cityEntity로 변환
        CityEntity city = cityRepository.findById(addressDto.getCityId())
            .orElseThrow(() -> new IllegalArgumentException("해당 cityId 없음"));

        entity.setCityEntity(city);

        addressRepository.save(entity);
    }


    //  수정
    public void update(AddressDto addressDto) {
        AddressEntity entity = addressRepository.findById(addressDto.getAddressId())
            .orElseThrow(() -> new IllegalArgumentException("해당 주소 없음"));

        entity.setAddress(addressDto.getAddress());
        entity.setAddress2(addressDto.getAddress2());
        entity.setDistrict(addressDto.getDistrict());
        entity.setPostalCode(addressDto.getPostalCode());
        entity.setPhone(addressDto.getPhone());
        entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));

        CityEntity city = cityRepository.findById(addressDto.getCityId())
            .orElseThrow(() -> new IllegalArgumentException("해당 cityId 없음"));
        entity.setCityEntity(city);

        addressRepository.save(entity);
    }


    // 삭제
    public boolean delete(int addressId) {
        if (addressRepository.existsById(addressId)) {
            addressRepository.deleteById(addressId);
            return true;
        }
        return false;
    }
}
