package com.example.demo.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class CheckIn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date checkInDate;
	
	private Date checkOutDate;
	
	@OneToMany(mappedBy = "checkIn", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Guest> guests;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}
	
	
	
	

}
