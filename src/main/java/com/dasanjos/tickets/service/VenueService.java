package com.dasanjos.tickets.service;

/**
 * VenueService Interface 
 * 
 * @author melro
 *
 */

public interface VenueService {
	
	/**
	* The number of seats in the venue that are neither held nor reserved
	*
	*/
	void displaySeats();
	
	/**
	* Find and hold the best available seats for a customer as asked by the customer
	*
	* @param seats the seats the customer ask to hold
	* @param customerEmail unique identifier for the customer
	* @return transaction id identifying the specific seats held information
	*/
	long findAndHoldSeats(String numSeats, String customerEmail);
	
	/**
	* Commit seats held for a specific customer
	*
	* @param time the seat hold identifier
	* @return success or failure to reserve
	*/
	int reserveSeats(long time);
	
}
