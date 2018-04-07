package com.dasanjos.tickets.service;

import java.util.HashMap;
import java.util.Map;

import com.dasanjos.tickets.domain.Venue;

public class VenueServiceImpl implements VenueService {
	
	public static String VENUE_NAME = "HIPERF";
	
	private Map<String, Venue> venues =  new HashMap<> ();
	
	public VenueServiceImpl() {
		prepVenues();
	}

	@Override
	public void displaySeats() {
		venues.get(VENUE_NAME).displaySeats();

	}

	@Override
	public long findAndHoldSeats(String numSeats, String customerEmail) {
		return venues.get(VENUE_NAME).findAndHoldSeats(numSeats, customerEmail);
		
	}

	@Override
	public int reserveSeats(long time) {
		return venues.get(VENUE_NAME).reserveSeats(time); 
		
	}
	
	private void prepVenues() {
		Venue venue = new Venue(VENUE_NAME);
		venue.generateSeats();
		venues.put(VENUE_NAME, venue);
		
	}

}
