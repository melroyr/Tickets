package com.dasanjos.tickets.service;

import java.util.HashMap;
import java.util.Map;

import com.dasanjos.tickets.domain.Venue;

/**
 * This class implements the VenueService Interface
 * 
 * @author melro
 *
 */
public class VenueServiceImpl implements VenueService {
	
	/**
	 * Venue Name
	 */
	public static String VENUE_NAME = "HIPERF";
	
	/**
	 * List of venues managed 
	 */
	private Map<String, Venue> venues =  new HashMap<> ();
	
	/**
	 * Constructor
	 */
	public VenueServiceImpl() {
		prepVenues();
	}

	/**
	* The number of seats in the venue that are neither held nor reserved
	*
	*/
	@Override
	public void displaySeats() {
		venues.get(VENUE_NAME).displaySeats();

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
		return venues.get(VENUE_NAME).findAndHoldSeats(numSeats, customerEmail);
		
	}

	/**
	* Commit seats held for a specific customer
	*
	* @param time the seat hold identifier
	* @return success or failure to reserve
	*/
	@Override
	public int reserveSeats(long time) {
		return venues.get(VENUE_NAME).reserveSeats(time); 
		
	}
	
	/**
	 * Prepares the venue's seating arrangement
	 */
	private void prepVenues() {
		Venue venue = new Venue(VENUE_NAME);
		venue.generateSeats();
		venues.put(VENUE_NAME, venue);
		
	}

}
