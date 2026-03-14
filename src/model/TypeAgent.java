package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TypeAgent {

	/**
	 * typeAgentIdt	[int]		: Identifiant du type d'agent
	 * typeAgentLbl	[String]	: Libellé long
	 * typeAgentLbc	[String]	: Libellé court
	 */
	private int		typeAgentIdt;
	private StringProperty	typeAgentLbl;
	private StringProperty	typeAgentLbc;
	/**
	 * @param newTypeAgentIdt
	 * @param newTypeAgentLbl
	 * @param newTypeAgentLbc
	 */
	public TypeAgent(int newTypeAgentIdt, String newTypeAgentLbl, String newTypeAgentLbc) {
		super();
		this.typeAgentIdt = newTypeAgentIdt;
		this.typeAgentLbl = new SimpleStringProperty(newTypeAgentLbl);
		this.typeAgentLbc = new SimpleStringProperty(newTypeAgentLbc);
	}
	/**
	 * @return the typeAgentIdt
	 */
	public int getTypeAgentIdt() {
		return typeAgentIdt;
	}
	/**
	 * @param typeAgentIdt the typeAgentIdt to set
	 */
	public void setTypeAgentIdt(int typeAgentIdt) {
		this.typeAgentIdt = typeAgentIdt;
	}
	/**
	 * @return the typeAgentLbl
	 */
	public String getTypeAgentLbl() {
		return typeAgentLbl.get();
	}
	public StringProperty getTypeAgentLblProperty() {
		return typeAgentLbl;
	}
	/**
	 * @param typeAgentLbl the typeAgentLbl to set
	 */
	public void setTypeAgentLbl(String typeAgentLbl) {
		this.typeAgentLbl = new SimpleStringProperty(typeAgentLbl);;
	}
	public void setTypeAgentLblProperty(StringProperty typeAgentLbl) {
		this.typeAgentLbl = typeAgentLbl;
	}
	/**
	 * @return the typeAgentLbc
	 */
	public String getTypeAgentLbc() {
		return typeAgentLbc.get();
	}
	public StringProperty getTypeAgentLbcProperty() {
		return typeAgentLbc;
	}
	/**
	 * @param typeAgentLbc the typeAgentLbc to set
	 */
	public void setTypeAgentLbc(String typeAgentLbc) {
		this.typeAgentLbc = new SimpleStringProperty(typeAgentLbc);;
	}
	public void setTypeAgentLbcProperty(StringProperty typeAgentLbc) {
		this.typeAgentLbc = typeAgentLbc;
	}
	@Override
	public String toString() {
	    return typeAgentLbl.get();  
	}

	
	
	
}
