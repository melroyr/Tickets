package com.dasanjos.tickets.app;

import com.dasanjos.tickets.service.TicketingService;
import com.dasanjos.tickets.service.TicketingServiceImpl;

/**
 * This is the driver class that starting the ticketing application and assist ticket issuers to issue tickets to ticket holders
 * at high demand venues
 * 
 * @author melro
 *
 */
public class Tickets {
	
	public static void main(String[] args) {
		
		TicketingService ticketingService = new TicketingServiceImpl();
		String action="start";
		String customerEmail=null, seats=null;
		while (!action.equals("exit")) {
			ticketingService.displaySeatsAvailable();
			System.out.print("Enter Customer Email: ");
			customerEmail = System.console().readLine();
			System.out.println("customerEmail: " + customerEmail);
			System.out.print("Enter seats required in the following format 01,02,03,04: ");
			seats = System.console().readLine();
			long time = ticketingService.findAndHoldSeats(seats, customerEmail);
			ticketingService.displaySeatsAvailable();
			System.out.print("Your seats are held for 30 seconds. Please enter any key to confirm:");
			seats = System.console().readLine();
			System.out.println("confirm: " + seats);
			int resp = ticketingService.reserveSeats(time);
			if(resp == 1) {
				System.out.println("Ypur tickets have been reserved"); 
			} else if (resp == 0) {
				System.out.println("Sorry, the hold timed out, please try again");
			}
			ticketingService.displaySeatsAvailable();
			System.out.print("Enter exit to exit, any other key to continue:");
			action = System.console().readLine();
			System.out.println("action: " + action);
			
		}
	}

}
