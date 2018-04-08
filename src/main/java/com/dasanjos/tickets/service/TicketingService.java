package com.dasanjos.tickets.service;

/**
 * Interface for the TicketingService implementation
 * 
 * @author melro
 *
 */
public interface TicketingService {
	/**
	* The number of seats in the venue that are neither held nor reserved
	*
	*/
	void displaySeatsAvailable();
	
	/**
	* Find and hold the best available seats for a customer as asked by the customer
	*
	* @param seats the seats the customer ask to hold
	* @param customerEmail unique identifier for the customer
	* @return transaction id identifying the specific seats held information
	*/
	long findAndHoldSeats(String seats, String customerEmail);
	
	/**
	* Commit seats held for a specific customer
	*
	* @param time the seat hold identifier
	* @return success or failure to reserve
	*/
	int reserveSeats(long time);
	}