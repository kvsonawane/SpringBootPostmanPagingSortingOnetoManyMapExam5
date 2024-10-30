package com.example.demo.controller;


import com.example.demo.entity.CheckIn;

import com.example.demo.repository.CheckInRepository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkIns")
public class CheckInController {
	
	@Autowired
    private CheckInRepository checkInRepository;

	
  
    @PostMapping
    public CheckIn createCheckIn(@RequestBody CheckIn checkIn) {
        return checkInRepository.save(checkIn);
    }
    
    @GetMapping
    public List<CheckIn> getAllCheckIns() {
        return checkInRepository.findAll();
    }


    
    @GetMapping("/{id}")
    public ResponseEntity<CheckIn> getCheckInById(@PathVariable Long id) {
        return checkInRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<CheckIn> updateCheckIn(@PathVariable Long id, @RequestBody CheckIn checkInDetails) {
        return checkInRepository.findById(id)
                .map(cart -> {
                   cart.setCheckInDate(checkInDetails.getCheckInDate());
                   cart.setCheckOutDate(checkInDetails.getCheckOutDate());
             
                    return ResponseEntity.ok(checkInRepository.save(cart));
                })
                .orElse(ResponseEntity.notFound().build());
    }

 
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCheckIn(@PathVariable Long id) {
        return checkInRepository.findById(id)
                .map(checkIn -> {
                    checkInRepository.delete(checkIn);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    
    @GetMapping("/paginated")
    public Page<CheckIn> getCheckInsPaginated(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return checkInRepository.findAll(pageable);
    }

   
    @GetMapping("/sorted")
    public Iterable<CheckIn> getCheckInsSorted(@RequestParam String sortBy, @RequestParam String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        return checkInRepository.findAll(sort);
    }

    
    @GetMapping("/paginated-sorted")
    public Page<CheckIn> getCheckInsPaginatedAndSorted(@RequestParam int page, @RequestParam int size,
                                                         @RequestParam String sortBy, @RequestParam String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return checkInRepository.findAll(pageable);
    }

}
