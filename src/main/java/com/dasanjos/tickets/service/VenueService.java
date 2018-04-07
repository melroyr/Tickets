package com.dasanjos.tickets.service;

public interface VenueService {
	
	void displaySeats();
	
	/**
	* Find and hold the best available seats for a customer
	*
	* @param numSeats the number of seats to find and hold
	* @param customerEmail unique identifier for the customer
	* @return a List of seats object identifying the specific seats and related
	information
	*/
	long findAndHoldSeats(String numSeats, String customerEmail);
	
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
