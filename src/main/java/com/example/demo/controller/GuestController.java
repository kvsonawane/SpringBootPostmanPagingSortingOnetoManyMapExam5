package com.example.demo.controller;


import com.example.demo.entity.Guest;

import com.example.demo.repository.GuestRepository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
	
	@Autowired
    private GuestRepository guestRepository;

	
  
    @PostMapping
    public Guest createGuest(@RequestBody Guest guest) {
        return guestRepository.save(guest);
    }
    
    @GetMapping
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }


    
    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable Long id) {
        return guestRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable Long id, @RequestBody Guest guestDetails) {
        return guestRepository.findById(id)
                .map(guest -> {
                   guest.setName(guestDetails.getName());
                   guest.setEmail(guestDetails.getEmail());
             
                    return ResponseEntity.ok(guestRepository.save(guest));
                })
                .orElse(ResponseEntity.notFound().build());
    }

 
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGuest(@PathVariable Long id) {
        return guestRepository.findById(id)
                .map(guest -> {
                    guestRepository.delete(guest);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    
    @GetMapping("/paginated")
    public Page<Guest> getGuestsPaginated(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return guestRepository.findAll(pageable);
    }

   
    @GetMapping("/sorted")
    public Iterable<Guest> getGuestsSorted(@RequestParam String sortBy, @RequestParam String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        return guestRepository.findAll(sort);
    }

    
    @GetMapping("/paginated-sorted")
    public Page<Guest> getGuestsPaginatedAndSorted(@RequestParam int page, @RequestParam int size,
                                                         @RequestParam String sortBy, @RequestParam String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return guestRepository.findAll(pageable);
    }


}
