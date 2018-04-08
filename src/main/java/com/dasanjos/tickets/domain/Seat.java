package com.dasanjos.tickets.domain;

/**
 * This class represents a seat in a venue 
 * 
 * @author melro
 *
 */
public class Seat {
	
	/**
	 * Seat is available
	 */
	public static char AVALIABLE = 'A';
	
	/**
	 * Seat is on hold
	 */
	public static char HOLD = 'H';
	
	/**
	 * Seat is reserved
	 */
	public static char RESERVED = 'R';
	
	/**
	 * Seat numver
	 */
	private int seatNumber;
	
	/**
	 * Seat status: Available, Hold or Reserved
	 */
	private char status;
	
	/**
	 * Transaction id
	 */
	private long transId;
	
	/**
	 * Customer id
	 */
	private String custId;
	
	/**
	 * Seat version. If a seat is read by two issuers, one will update the status to hold and change the version
	 * The second issuer's hold request will fail.
	 */
	private int version;

	/**
	 * GetSeatcNumber
	 * @return seat number
	 */
	public int getSeatNumber() {
		return seatNumber;
	}

	/**
	 * SetSeatNumber
	 * @param seatNumber
	 */
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	/**
	 * GetStatus
	 * @return seat status
	 */
	public char getStatus() {
		return status;
	}

	/**
	 * SetStatus
	 * @param status
	 */
	public void setStatus(char status) {
		this.status = status;
	}

	/**
	 * GetTransId
	 * @return transaction id
	 */
	public long getTransId() {
		return transId;
	}

	/**
	 * SetTransId
	 * @param transId
	 */
	public void setTransId(long transId) {
		this.transId = transId;
	}

	/**
	 * GetCustId
	 * @return customer id
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * SetCustId
	 * @param custId
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	/**
	 * GetVersion
	 * @return seat version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * SetVersion
	 * @param version
	 */
	public void setVersion(int version) {
		this.version = version;
	}
}
