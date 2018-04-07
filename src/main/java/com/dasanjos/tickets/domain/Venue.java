package com.dasanjos.tickets.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class Venue {
	
	public static int WIDTH = 10;
	
	public static int LENGTH = 10;
	
	public static String INDENT = "  ";
	
	private Map<Integer, Seat> seats = new HashMap<>();
	
	private Map<Long, List<Integer>> heldSeats = new HashMap<>();
	
	private String venueName;
	
	public Venue(String venueName) {
		this.venueName = venueName;
	}
	
	public void generateSeats() {
		int noOfSeats = WIDTH * LENGTH;
		Seat seat = null;
		for (int i=1;i<=noOfSeats;i++) {
			seat = new Seat();
			seat.setSeatNumber(i);
			seat.setStatus(Seat.AVALIABLE);
			seats.put(i, seat);
		}
//		seats.get(1).setStatus(Seat.HOLD);
//		seats.get(12).setStatus(Seat.HOLD);
//		seats.get(22).setStatus(Seat.HOLD);
//		seats.get(33).setStatus(Seat.HOLD);
//		seats.get(44).setStatus(Seat.RESERVED);
//		seats.get(55).setStatus(Seat.RESERVED);
//		seats.get(66).setStatus(Seat.RESERVED);
//		seats.get(77).setStatus(Seat.RESERVED);
//		seats.get(88).setStatus(Seat.RESERVED);
//		seats.get(99).setStatus(Seat.RESERVED);
		
	}
	
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
				} else {
					System.out.print("  ");
				}
				seat++;
			}
			System.out.println("\n");
		}
	}
	
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
	
	public static void main(String[] args) {
		Venue venue = new Venue("HIPERF");
		venue.generateSeats();
		venue.displaySeats();
	}

}
