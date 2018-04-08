package com.dasanjos.tickets.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 * Tis class represents a venue
 * 
 * @author melro
 *
 */
public class Venue {
	
	/**
	 * Venue number of seats widthwise
	 */
	public static int WIDTH = 10;
	
	/**
	 * Venue number of rows
	 */
	public static int LENGTH = 10;
	
	/** 
	 * Indent for display purpose
	 */
	public static String INDENT = "  ";
	
	/**
	 * Venue seats
	 */
	private Map<Integer, Seat> seats = new HashMap<>();
	
	/**
	 * Temporarily held seats
	 */
	private Map<Long, List<Integer>> heldSeats = new HashMap<>();
	
	/**
	 * Venue name
	 */
	private String venueName;
	
	/**
	 * Constructor
	 * 
	 * @param venueName
	 */
	public Venue(String venueName) {
		this.venueName = venueName;
	}
	
	/**
	 * Generate Seats for a venue
	 */
	public void generateSeats() {
		int noOfSeats = WIDTH * LENGTH;
		Seat seat = null;
		for (int i=1;i<=noOfSeats;i++) {
			seat = new Seat();
			seat.setSeatNumber(i);
			seat.setStatus(Seat.AVALIABLE);
			seats.put(i, seat);
		}
	}
	
	/**
	 * Display seating arrangement for a venue
	 */
	public void displaySeats() {
		System.out.println("=======================================");
		int seat = 1;
		for(int l=1;l<=LENGTH;l++) {
			System.out.print(INDENT);
			Seat lseat = null;
			for (int w=1; w<=WIDTH; w++) {
				lseat = seats.get(seat);
				if (lseat.getStatus() == Seat.AVALIABLE) {
					System.out.print(String.format("%02d", lseat.getSeatNumber()));
				} else if (lseat.getStatus() == Seat.HOLD) {
					System.out.print("HH");
				} else if (lseat.getStatus() == Seat.RESERVED) {
					System.out.print("RR");
				}
				seat++;
			}
			System.out.println("\n");
		}
	}
	
	/**
	 * Find and temporarily hold seats. CurrentTimeMills is used as it is unique if this application becomes
	 * multithreaded.
	 * 
	 * @param seats
	 * @param customerEmail
	 * @return transaction id
	 */
	public long findAndHoldSeats(String seats, String customerEmail) {
		
		StringTokenizer tokenizer = new StringTokenizer(seats, ",");
		long time = System.currentTimeMillis();
		String seatStr = null;
		Seat seat = null;
		List<Integer> lheldSeats = new ArrayList<>();
		boolean hold = true;
		
		synchronized(seats) {
			while(tokenizer.hasMoreTokens()) {
				seatStr = tokenizer.nextToken();
				seat = this.seats.get(Integer.parseInt(seatStr));
				seat.setCustId(customerEmail);
				seat.setTransId(time);
				seat.setStatus(Seat.HOLD);
				if (seat.getVersion() == 0) {
					seat.setVersion(1);
				} else {
					hold = false;
					break;
				}
				lheldSeats.add(seat.getSeatNumber());
			}
		}
		
		if(hold) {
			heldSeats.put(time, lheldSeats);
			return time;
		} else {
			return 0;
		}
	}
	
	/**
	 * Reserve temporarily held seats
	 * 
	 * @param time
	 * @return 1 for reserved 0 if hold time elapsed
	 */
	public int reserveSeats(Long time) {
		long currTime = System.currentTimeMillis();
		long elapsedTime = currTime - time;
		long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
		if (seconds > 30) {
			List<Integer> seatNumbers = heldSeats.get(time);
			int length = seatNumbers.size();
			Seat seat = null;
			for (int i = 0; i < length; i++) {
				seat = seats.get(seatNumbers.get(i));
				seat.setCustId("");
				seat.setTransId(0L);
				seat.setStatus(Seat.AVALIABLE);
				seat.setVersion(0);
			}
			heldSeats.remove(time);
			return 0;
		} else {
			List<Integer> seatNumbers = heldSeats.get(time);
			int length = seatNumbers.size();
			Seat seat = null;
			for (int i = 0; i < length; i++) {
				seat = seats.get(seatNumbers.get(i));
				seat.setStatus(Seat.RESERVED);
			}
			heldSeats.remove(time);
			return 1;
		}
	}
}
