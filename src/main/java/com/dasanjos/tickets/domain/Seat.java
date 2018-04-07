package com.dasanjos.tickets.domain;

public class Seat {
	
	public static char AVALIABLE = 'A';
	
	public static char HOLD = 'H';
	
	public static char RESERVED = 'R';
			
	private int seatNumber;
	
	private char status;
	
	private long transId;
	
	private String custId;
	
	private int version;


	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public long getTransId() {
		return transId;
	}

	public void setTransId(long transId) {
		this.transId = transId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	

}
