package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CheckIn;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, Long>{

}
