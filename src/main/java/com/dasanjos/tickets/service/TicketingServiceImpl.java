package com.dasanjos.tickets.service;

/**
 * TicketingService implementation
 * 
 * @author melro
 *
 */
public class TicketingServiceImpl implements TicketingService {

	/**
	 * Venue Service instance
	 */
	private VenueService venueService = new VenueServiceImpl();
	
	/**
	* The number of seats in the venue that are neither held nor reserved
	*
	*/
	@Override
	public void displaySeatsAvailable() {
		
		venueService.displaySeats();
	}
	
	/**
	* Find and hold the best available seats for a customer as asked by the customer
	*
	* @param seats the seats the customer ask to hold
	* @param customerEmail unique identifier for the customer
	* @return transaction id identifying the specific seats held information
	*/
	@Override
	public long findAndHoldSeats(String numSeats, String customerEmail) {
		return venueService.findAndHoldSeats(numSeats, customerEmail);
	}

	/**
	* Commit seats held for a specific customer
	*
	* @param time the seat hold identifier
	
	* @return success or failure to reserve
	*/
	@Override
	public int reserveSeats(long time) {
		return venueService.reserveSeats(time);
	}	
}
