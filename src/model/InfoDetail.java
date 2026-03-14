package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InfoDetail {
	/**
	 * infoDetailIdt			[int]		: Identifiant du détail de l'informatio
	 * infoEnteteIdt			[int]		: Identifiant de l'entête 
	 * infoDetailKeyIdt			[int]		: Clé de recherche du détail
	 * infoDetailDescription	[String]	: Description du détail
	 * infoDetailLbl			[String]	: Libellé long
	 * infoDetailLbc			[String]	: Libellé court
	 * infoDetailValueInt		[int]		: Valeur numérique entier
	 * infoDetailValueDouble	[double]	: Valeur numérique décimale
	 * infoDetailTri			[int]		: Ordre de tri des lignes de détail
	 * infoDetailDefault		[boolean]	: Encréation cette ligne est la ligne par défaut
	 * infoEntete				[InfoEntete]: Entête
	 */
	private int 			infoDetailIdt;
	private int 			infoEnteteIdt;		
	private int 			infoDetailKeyIdt;
	private StringProperty	infoDetailDescription;
	private String 			infoDetailLbl;
	private String 			infoDetailLbc;
	private int				infoDetailValueInt;
	private double			infoDetailValueDouble;
	private int 			infoDetailTri;
	private boolean 		infoDetailDefault;
	private InfoEntete		infoEntete;
	/**
	 * @param newInfoDetailIdt
	 * @param newInfoEnteteIdt
	 * @param newInfoDetailKeyIdt
	 * @param newInfoDetailLbl
	 * @param newInfoDetailLbc
	 * @param newInfoDetailValueInt
	 * @param newInfoDetailValueDouble
	 * @param newInfoDetailTri
	 * @param newInfoDetailDefault
	 * @param newInfoEntete
	 */
	public InfoDetail(int newInfoDetailIdt, int newInfoEnteteIdt, int newInfoDetailKeyIdt, String newInfoDetailDescription, String newInfoDetailLbl,
			String newInfoDetailLbc, int newInfoDetailValueInt, double newInfoDetailValueDouble, int newInfoDetailTri,
			boolean newInfoDetailDefault, InfoEntete newInfoEntete) {
		super();
		this.infoDetailIdt 			= newInfoDetailIdt;
		this.infoEnteteIdt 			= newInfoEnteteIdt;
		this.infoDetailKeyIdt 		= newInfoDetailKeyIdt;
		this.infoDetailDescription	= new SimpleStringProperty(newInfoDetailDescription);
		this.infoDetailLbl 			= newInfoDetailLbl;
		this.infoDetailLbc 			= newInfoDetailLbc;
		this.infoDetailValueInt 	= newInfoDetailValueInt;
		this.infoDetailValueDouble 	= newInfoDetailValueDouble;
		this.infoDetailTri 			= newInfoDetailTri;
		this.infoDetailDefault 		= newInfoDetailDefault;
		this.infoEntete				= newInfoEntete;
	}
	/**
	 * @return the infoDetailIdt
	 */
	public int getInfoDetailIdt() {
		return infoDetailIdt;
	}
	/**
	 * @param infoDetailIdt the infoDetailIdt to set
	 */
	public void setInfoDetailIdt(int infoDetailIdt) {
		this.infoDetailIdt = infoDetailIdt;
	}
	/**
	 * @return the infoEnteteIdt
	 */
	public int getInfoEnteteIdt() {
		return infoEnteteIdt;
	}
	/**
	 * @param infoEnteteIdt the infoEnteteIdt to set
	 */
	public void setInfoEnteteIdt(int infoEnteteIdt) {
		this.infoEnteteIdt = infoEnteteIdt;
	}
	/**
	 * @return the infoDetailKeyIdt
	 */
	public int getInfoDetailKeyIdt() {
		return infoDetailKeyIdt;
	}
	/**
	 * @param infoDetailKeyIdt the infoDetailKeyIdt to set
	 */
	public void setInfoDetailKeyIdt(int infoDetailKeyIdt) {
		this.infoDetailKeyIdt = infoDetailKeyIdt;
	}
	/**
	 * @return the infoDetailDescription
	 */
	public String getInfoDetailDescription() {
		return infoDetailDescription.get();
	}
	public StringProperty getInfoDetailDescriptionProperty() {
		return infoDetailDescription;
	}
	/**
	 * @param infoDetailDescription the infoDetailDescription to set
	 */
	public void setInfoDetailDescription(String infoDetailDescription) {
		this.infoDetailDescription = new SimpleStringProperty(infoDetailDescription);;
	}
	public void setInfoDetailDescriptionProperty(StringProperty infoDetailDescription) {
		this.infoDetailDescription = infoDetailDescription;
	}
	/**
	 * @return the infoDetailLbl
	 */
	public String getInfoDetailLbl() {
		return infoDetailLbl;
	}
	/**
	 * @param infoDetailLbl the infoDetailLbl to set
	 */
	public void setInfoDetailLbl(String infoDetailLbl) {
		this.infoDetailLbl = infoDetailLbl;
	}
	/**
	 * @return the infoDetailLbc
	 */
	public String getInfoDetailLbc() {
		return infoDetailLbc;
	}
	/**
	 * @param infoDetailLbc the infoDetailLbc to set
	 */
	public void setInfoDetailLbc(String infoDetailLbc) {
		this.infoDetailLbc = infoDetailLbc;
	}
	/**
	 * @return the infoDetailValueInt
	 */
	public int getInfoDetailValueInt() {
		return infoDetailValueInt;
	}
	/**
	 * @param infoDetailValueInt the infoDetailValueInt to set
	 */
	public void setInfoDetailValueInt(int infoDetailValueInt) {
		this.infoDetailValueInt = infoDetailValueInt;
	}
	/**
	 * @return the infoDetailValueDouble
	 */
	public double getInfoDetailValueDouble() {
		return infoDetailValueDouble;
	}
	/**
	 * @param infoDetailValueDouble the infoDetailValueDouble to set
	 */
	public void setInfoDetailValueDouble(double infoDetailValueDouble) {
		this.infoDetailValueDouble = infoDetailValueDouble;
	}
	/**
	 * @return the infoDetailTri
	 */
	public int getInfoDetailTri() {
		return infoDetailTri;
	}
	/**
	 * @param infoDetailTri the infoDetailTri to set
	 */
	public void setInfoDetailTri(int infoDetailTri) {
		this.infoDetailTri = infoDetailTri;
	}
	/**
	 * @return the infoDetailDefault
	 */
	public boolean isInfoDetailDefault() {
		return infoDetailDefault;
	}
	/**
	 * @param infoDetailDefault the infoDetailDefault to set
	 */
	public void setInfoDetailDefault(boolean infoDetailDefault) {
		this.infoDetailDefault = infoDetailDefault;
	}
	/**
	 * @return the infoEntete
	 */
	public InfoEntete getInfoEntete() {
		return infoEntete;
	}
	/**
	 * @param infoEntete the infoEntete to set
	 */
	public void setInfoEntete(InfoEntete infoEntete) {
		this.infoEntete = infoEntete;
	}
	@Override
	public String toString() {
		return "InfoDetail [infoDetailIdt=" + infoDetailIdt + ", infoEnteteIdt=" + infoEnteteIdt + ", infoDetailKeyIdt="
				+ infoDetailKeyIdt + ", infoDetailDescription=" + infoDetailDescription + ", infoDetailLbl="
				+ infoDetailLbl + ", infoDetailLbc=" + infoDetailLbc + ", infoDetailValueInt=" + infoDetailValueInt
				+ ", infoDetailValueDouble=" + infoDetailValueDouble + ", infoDetailTri=" + infoDetailTri
				+ ", infoDetailDefault=" + infoDetailDefault + ", infoEntete=" + infoEntete + "]";
	}
	
	

}