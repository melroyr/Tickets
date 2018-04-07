package com.dasanjos.tickets.service;

import java.util.List;

import com.dasanjos.tickets.domain.Seat;

public interface TicketingService {
	/**
	* The number of seats in the venue that are neither held nor reserved
	*
	* @return the number of tickets available in the venue
	*/
	void displaySeatsAvailable();
	
	/**
	* Find and hold the best available seats for a customer
	*
	* @param numSeats the number of seats to find and hold
	* @param customerEmail unique identifier for the customer
	* @return a List of seats object identifying the specific seats and related
	information
	*/
	long findAndHoldSeats(String seats, String customerEmail);
	
	/**
	* Commit seats held for a specific customer
	*
	* @param seatHoldId the seat hold identifier
	* @param customerEmail the email address of the customer to which the
	seat hold is assigned
	* @return a reservation confirmation code
	*/
	int reserveSeats(long time);
	}