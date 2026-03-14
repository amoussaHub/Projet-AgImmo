package model;
/**
 * Classe	: LegalRegime (Type de régime juridique)
 * Cette classe contient les attributs, constructeur, Accesseurs et méthodes permettant
 * d'instancier des objets de Type [LegalRegime].
 * Cette classe est en lecture seule (uniquement les Getters)
 * Les données seront uniquement insérées par requête SQL. Il n'est pas prévu de programme
 * de gestion de ces informations
 * 
 * @author 	Alain CASARA
 * @version	1.0
 */
public class LegalRegime {
	/**
	 * Attributs de la classe [LegalRegime]
	 * legalRegimeIdt	: Identifiant du régime
	 * legalRegimeLbl	: Libellé long
	 * legalRegimeLbc	: Libellé court
	 */
	private int 	legalRegimeIdt;
	private String	legalRegimeLbl;
	private String	legalRegimeLbc;
	/**
	 * Constructeur de la classe [LegalRegime]
	 * @param newLegalRegimeIdt
	 * @param newLegalRegimeLbl
	 * @param newLegalRegimeLbc
	 */
	public LegalRegime(int newLegalRegimeIdt, String newLegalRegimeLbl, String newLegalRegimeLbc) {
		super();
		this.legalRegimeIdt = newLegalRegimeIdt;
		this.legalRegimeLbl = newLegalRegimeLbl;
		this.legalRegimeLbc = newLegalRegimeLbc;
	}
	/**
	 * @return the legalRegimeIdt
	 */
	public int getLegalRegimeIdt() {
		return legalRegimeIdt;
	}
	/**
	 * @return the legalRegimeLbl
	 */
	public String getLegalRegimeLbl() {
		return legalRegimeLbl;
	}
	/**
	 * @return the legalRegimeLbc
	 */
	public String getLegalRegimeLbc() {
		return legalRegimeLbc;
	}
	/**
	 * Méthode	: toString
	 * Description 	: Cette méthode permet, dans le cadre de l'application, d'afficher les informations 
	 * utiles d'un régime dans une combobox.
	 * @return  String (chaine de caractères affichant le libellé long du régime)  
	 */
	@Override
	public String toString() {
		return legalRegimeLbl;
	}
	/**
	 * Méthode	: affichage
	 * Description 	: Cette méthode permet renvoie l'intégralité des informations d'un régime
	 * @return  String
	 */
	public String affichage() {
		return "LegalRegime [legalRegimeIdt=" + legalRegimeIdt + ", legalRegimeLbl=" + legalRegimeLbl
				+ ", legalRegimeLbc=" + legalRegimeLbc + "]";
	}

}
