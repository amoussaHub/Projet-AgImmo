package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Civility {

	/**
	 * civilityIdt	[int]		: Identifiant de la civilité
	 * civilityLbl	[String]	: Libellé long
	 * civilityLbc	[String]	: Libellé court
	 */
	private int		civilityIdt;
	private StringProperty	civilityLbl;
	private StringProperty	civilityLbc;
	/**
	 * @param newCivilityIdt
	 * @param newCivilityLbl
	 * @param newCivilityLbc
	 */
	public Civility(int newCivilityIdt, String newCivilityLbl, String newCivilityLbc) {
		super();
		this.civilityIdt = newCivilityIdt;
		this.civilityLbl = new SimpleStringProperty(newCivilityLbl);
		this.civilityLbc = new SimpleStringProperty(newCivilityLbc);
	}
	/**
	 * @return the civilityIdt
	 */
	public int getCivilityIdt() {
		return civilityIdt;
	}
	/**
	 * @param civilityIdt the civilityIdt to set
	 */
	public void setCivilityIdt(int civilityIdt) {
		this.civilityIdt = civilityIdt;
	}
	/**
	 * @return the civilityLbl
	 */
	public String getCivilityLbl() {
		return civilityLbl.get();
	}
	public StringProperty getCivilityLblProperty() {
		return civilityLbl;
	}
	/**
	 * @param civilityLbl the civilityLbl to set
	 */
	public void setCivilityLbl(String civilityLbl) {
		this.civilityLbl = new SimpleStringProperty(civilityLbl);;
	}
	public void setCivilityLblProperty(StringProperty civilityLbl) {
		this.civilityLbl = civilityLbl;
	}
	/**
	 * @return the civilityLbc
	 */
	public String getCivilityLbc() {
		return civilityLbc.get();
	}
	public StringProperty getCivilityLbcProperty() {
		return civilityLbc;
	}
	/**
	 * @param civilityLbc the civilityLbc to set
	 */
	public void setCivilityLbc(String civilityLbc) {
		this.civilityLbc = new SimpleStringProperty(civilityLbc);
	}
	public void setCivilityLbcProperty(StringProperty civilityLbc) {
		this.civilityLbc = civilityLbc;
	}
	
	@Override
	public String toString() {
		return civilityLbl.get();
	}
	
}