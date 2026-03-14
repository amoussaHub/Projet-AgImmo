package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe	: Town
 * Cette classe contient les attributs, constructeur, Accesseurs et methodes permettant
 * d'instancier des objets de Type [Town].
 * -------- A retirer
 * Cette classe est en lecture seule (uniquement les Getters)
 * Les donnees seront uniquement inserees par requete SQL. Il n'est pas prevu de programme
 * de gestion de ces informations
 * 
 * @author 	Alain CASARA
 * @version	1.0
 */
public class Town {
	/**
	 * townIdt 			: Identifiant de la ville
	 * townName			: Nom de la ville
	 * townPostCode		: Code postal. Le type est String car il faut garder le zero significatif 
	 * 					  en premiere position et le code postal pourrait contenir une chaine de 
	 * 					  caracteres ( departement Corses )
	 */
	private int		townIdt;
	private String	townName;
	private String	townPostCode;
	/**
	 * 
	 * @param newTownIdt
	 * @param newTownName
	 * @param newTownPostCode
	 */
	public Town(int newTownIdt, String newTownName, String newTownPostCode) {
		super();
		this.townIdt 		= newTownIdt;
		this.townName 		= newTownName;
		this.townPostCode 	= newTownPostCode;
	}
	/**
	 * @return the townIdt
	 */
	public int getTownIdt() {
		return townIdt;
	}
	/**
	 * @return the townName
	 */
	public String getTownName() {
		return townName;
	}
	/**
	 * @return the townPostCode
	 */
	public String getTownPostCode() {
		return townPostCode;
	}
	/**
	 * @return the town complete
	 */
	public StringProperty getTownCompleteProperty() {
		String townNameCp			= this.townPostCode + " " + this.townName; 
		StringProperty townComplete	= new SimpleStringProperty(townNameCp);
		return townComplete;
	}
	/**
	 * Methode	: toString
	 * Description 	: Cette methode permet, dans le cadre de l'application, d'afficher les informations 
	 * utiles d'une ville dans une combobox.
	 * @return  String (chaine de caracteres affichant le nom de la ville)  
	 */
	@Override
	public String toString() {
		return townName;
	}
	/**
	 * Methode	: affichage
	 * Description 	: Cette methode permet renvoie l'integralite des informations d'une ville
	 * @return  String
	 */
	public String affichage() {
		return "Town [townIdt=" + townIdt + ", townName=" + townName + ", townPostCode=" + townPostCode + "]";
	}
}