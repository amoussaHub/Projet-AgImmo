package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Connexions {
	/**
	 * connexionsIdt		: identifiant
	 * connexionsUuid		: Uuid de connexions
	 * connexionsDate		: date 
	 * connexionsTime		: heure
	 */
	private int 		connexionsIdt;
	private String 		connexionsUuid;
	private LocalDate	connexionsDate;
	private LocalTime	connexionsTime;
	/**
	 * @param newConnexionsIdt
	 * @param newConnexionsUuid
	 * @param newConnexionsDate
	 * @param newConnexionsTime
	 */
	public Connexions(int newConnexionsIdt, String newConnexionsUuid, LocalDate newConnexionsDate, LocalTime newConnexionsTime) {
		super();
		this.connexionsIdt 	= newConnexionsIdt;
		this.connexionsUuid = newConnexionsUuid;
		this.connexionsDate = newConnexionsDate;
		this.connexionsTime = newConnexionsTime;
	}
	
	/**
	 * @return the connexionsIdt
	 */
	public int getConnexionsIdt() {
		return connexionsIdt;
	}
	/**
	 * @return the connexionsUuid
	 */
	public String getConnexionsUuid() {
		return connexionsUuid;
	}
	/**
	 * @return the connexionsDate
	 */
	public LocalDate getConnexionsDate() {
		return connexionsDate;
	}
	/**
	 * @return the connexionsTime
	 */
	public LocalTime getConnexionsTime() {
		return connexionsTime;
	}

	@Override
	public String toString() {
		return "Connexions [connexionsIdt=" + connexionsIdt + ", connexionsUuid=" + connexionsUuid + ", connexionsDate="
				+ connexionsDate + ", connexionsTime=" + connexionsTime + "]";
	}
	
	

}
