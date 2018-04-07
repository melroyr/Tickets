package com.dasanjos.tickets;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.dasanjos.tickets.service.TicketingService;
import com.dasanjos.tickets.service.TicketingServiceImpl;

public class TicketsTests {

	@Test
	public void testTicketsReserved() throws Exception {
		TicketingService ticketingService = new TicketingServiceImpl();
		long time = ticketingService.findAndHoldSeats("10,20,30,40", "melroyr@yahoo.com");
		int resp = ticketingService.reserveSeats(time);
		assertEquals(resp, 1);
	}

	@Test
	public void testTicketsNotReserved() throws Exception {
		TicketingService ticketingService = new TicketingServiceImpl();
		long time = ticketingService.findAndHoldSeats("10,20,30,40", "melroyr@yahoo.com");
		Thread.sleep(31000);
		int resp = ticketingService.reserveSeats(time);
		assertEquals(resp, 0);
	}

}
