package com.dasanjos.tickets.service;

public class TicketingServiceImpl implements TicketingService {

	private VenueService venueService = new VenueServiceImpl();
	
	@Override
	public void displaySeatsAvailable() {
		
		venueService.displaySeats();
	}
	
	@Override
	public long findAndHoldSeats(String numSeats, String customerEmail) {
		return venueService.findAndHoldSeats(numSeats, customerEmail);
	}

	@Override
	public int reserveSeats(long time) {
		return venueService.reserveSeats(time);
	}	
}
