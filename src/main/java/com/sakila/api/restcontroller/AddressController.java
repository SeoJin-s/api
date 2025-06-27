package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sakila.api.dto.AddressDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.service.AddressService;

@RestController
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // 전체 조회
    @GetMapping("/address")
    public ResponseEntity<List<AddressEntity>> getAllAddresses() {
        return new ResponseEntity<>(addressService.findAll(), HttpStatus.OK);
    }

    // 등록
    @PostMapping("/address")
    public ResponseEntity<String> insertAddress(@RequestBody AddressDto addressDto) {
        addressService.save(addressDto);
        return new ResponseEntity<>("입력성공", HttpStatus.OK);
    }

    // 수정
    @PatchMapping("/address")
    public ResponseEntity<String> updateAddress(@RequestBody AddressDto addressDto) {
        addressService.update(addressDto);
        return new ResponseEntity<>("수정성공", HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/address/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable int addressId) {
        boolean result = addressService.delete(addressId);
        if (result) {
            return new ResponseEntity<>("삭제성공", HttpStatus.OK);
        }
        return new ResponseEntity<>("삭제실패", HttpStatus.BAD_REQUEST);
    }
}
