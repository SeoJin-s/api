package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sakila.api.dto.StoreDto;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.service.StoreService;

@RestController
public class StoreController {
	private final StoreService storeService;

	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}

	// 전체 조회
	@GetMapping("/store")
	public ResponseEntity<List<StoreEntity>> findAll() {
		return new ResponseEntity<>(storeService.findAll(), HttpStatus.OK);
	}

	// 단일 조회
	@GetMapping("/store/{storeId}")
	public ResponseEntity<StoreEntity> findById(@PathVariable int storeId) {
		return new ResponseEntity<>(storeService.findById(storeId), HttpStatus.OK);
	}

	// 등록
	@PostMapping("/store")
	public ResponseEntity<String> save(@RequestBody StoreDto storeDto) {
		storeService.save(storeDto);
		return new ResponseEntity<>("입력성공", HttpStatus.OK);
	}

	// 수정
	@PatchMapping("/store")
	public ResponseEntity<String> update(@RequestBody StoreDto storeDto) {
		storeService.update(storeDto);
		return new ResponseEntity<>("수정성공", HttpStatus.OK);
	}

	// 삭제
	@DeleteMapping("/store/{storeId}")
	public ResponseEntity<String> delete(@PathVariable int storeId) {
		boolean result = storeService.delete(storeId);
		if (result) {
			return new ResponseEntity<>("삭제성공", HttpStatus.OK);
		}
		return new ResponseEntity<>("삭제실패", HttpStatus.OK);
	}
}
