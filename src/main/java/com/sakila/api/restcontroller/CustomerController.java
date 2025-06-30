package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sakila.api.dto.CustomerDto;
import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.service.CustomerService;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    // 생성자 주입
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 전체 조회
    @GetMapping("/customer")
    public ResponseEntity<List<CustomerEntity>> findAll() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    // 단일 조회
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerEntity> findOne(@PathVariable int customerId) {
        return new ResponseEntity<>(customerService.findById(customerId), HttpStatus.OK);
    }

    // 등록
    @PostMapping("/customer")
    public ResponseEntity<String> save(@RequestBody CustomerDto dto) {
        customerService.save(dto);
        return new ResponseEntity<>("입력성공", HttpStatus.OK);
    }

    // 수정
    @PatchMapping("/customer")
    public ResponseEntity<String> update(@RequestBody CustomerDto dto) {
        customerService.update(dto);
        return new ResponseEntity<>("수정성공", HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<String> delete(@PathVariable int customerId) {
        boolean result = customerService.delete(customerId);
        return new ResponseEntity<>(result ? "삭제성공" : "삭제실패", HttpStatus.OK);
    }
}
