package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InfoEntete {

	/**
	 * infoEnteteIdt			[int]		: Identifiant de l'entête
	 * infoEnteteKey			[String]	: Clé de recherche de l'information
	 * infoEnteteDescription	[String]	: Description de l'information
	 * infoEnteteCbx			[String]	: Combobox rattachée à l'information
	 * infoEnteteNbreDetailMax	[int]		: Nombre de lignes de détail de l'information (facultatif)
	 */
	private int 	infoEnteteIdt;
	private StringProperty	infoEnteteKey;
	private StringProperty  infoEnteteDescription;
	private String  infoEnteteCbx;
	private int 	infoEnteteNbreDetailMax;
	/**
	 * @param newInfoEnteteIdt
	 * @param newInfoEnteteKey
	 * @param newInfoEnteteDescription
	 * @param newInfoEnteteCbx
	 * @param newInfoEnteteNbreDetailMax
	 */
	public InfoEntete(int newInfoEnteteIdt, String newInfoEnteteKey, String newInfoEnteteDescription, String newInfoEnteteCbx,
			int newInfoEnteteNbreDetailMax) {
		super();
		this.infoEnteteIdt 				= newInfoEnteteIdt;
		this.infoEnteteKey 				= new SimpleStringProperty(newInfoEnteteKey);
		this.infoEnteteDescription 		= new SimpleStringProperty(newInfoEnteteDescription);
		this.infoEnteteCbx 				= newInfoEnteteCbx;
		this.infoEnteteNbreDetailMax 	= newInfoEnteteNbreDetailMax;
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
	 * @return the infoEnteteKey
	 */
	public String getInfoEnteteKey() {
		return infoEnteteKey.get();
	}
	public StringProperty getInfoEnteteKeyProperty() {
		return infoEnteteKey;
	}
	/**
	 * @param infoEnteteKey the infoEnteteKey to set
	 */
	public void setInfoEnteteKey(String infoEnteteKey) {
		this.infoEnteteKey = new SimpleStringProperty(infoEnteteKey);;
	}
	public void setInfoEnteteKeyProperty(StringProperty infoEnteteKey) {
		this.infoEnteteKey = infoEnteteKey;
	}
	/**
	 * @return the infoEnteteDescription
	 */
	public String getInfoEnteteDescription() {
		return infoEnteteDescription.get();
	}
	public StringProperty getInfoEnteteDescriptionProperty() {
		return infoEnteteDescription;
	}
	/**
	 * @param infoEnteteDescription the infoEnteteDescription to set
	 */
	public void setInfoEnteteDescription(String infoEnteteDescription) {
		this.infoEnteteDescription = new SimpleStringProperty(infoEnteteDescription);;
	}
	public void setInfoEnteteDescriptionProperty(StringProperty infoEnteteDescription) {
		this.infoEnteteDescription = infoEnteteDescription;
	}
	/**
	 * @return the infoEnteteCbx
	 */
	public String getInfoEnteteCbx() {
		return infoEnteteCbx;
	}
	/**
	 * @param infoEnteteCbx the infoEnteteCbx to set
	 */
	public void setInfoEnteteCbx(String infoEnteteCbx) {
		this.infoEnteteCbx = infoEnteteCbx;
	}
	/**
	 * @return the infoEnteteNbreDetailMax
	 */
	public int getInfoEnteteNbreDetailMax() {
		return infoEnteteNbreDetailMax;
	}
	/**
	 * @param infoEnteteNbreDetailMax the infoEnteteNbreDetailMax to set
	 */
	public void setInfoEnteteNbreDetailMax(int infoEnteteNbreDetailMax) {
		this.infoEnteteNbreDetailMax = infoEnteteNbreDetailMax;
	}
	@Override
	public String toString() {
		return "InfoEntete [infoEnteteIdt=" + infoEnteteIdt + ", infoEnteteKey=" + infoEnteteKey
				+ ", infoEnteteDescription=" + infoEnteteDescription + ", infoEnteteCbx=" + infoEnteteCbx
				+ ", infoEnteteNbreDetailMax=" + infoEnteteNbreDetailMax + "]";
	}
	
	

}