package model;

/**
 * Classe	: Fenetres
 * Cette classe contient les attributs, constructeur, Accesseurs et méthodes permettant
 * d'instancier des objets de Type [Fenetres].
 * Cette classe est en lecture seule (uniquement les Getters)
 * 
 * @author 	Alain CASARA
 * @version	1.0
 */
public class Fenetres {
	/**
	 * fenetreIdt				: Identifiant de la fenêtre
	 * fenetreKey				: Clé de recherche pour la fenêtre
	 * fenetrelocation			: Chemin d'accès dans le projet pour le fichier FXML
	 * fenetreStyleSheet		: Chemin d'accès dans le projet pour la feuille de style racine
	 * fenetreStageTitle		: Titre de la fenêtre (information située sur le bandeau )
	 * fenetreStageIcone		: Chemin d'accès dans le projet pour l'icône de la fenêtre
	 * fenetreLoaderMsgErreur	: Message d'erreur en cas de problème sur l'ouverture de la fenêtre 
	 */
	private int 		fenetreIdt;
	private String		fenetreKey;
	private String 		fenetrelocation;
	private String 		fenetreStyleSheet;
	private String 		fenetreStageTitle;
	private String 		fenetreStageIcone;
	private String 		fenetreLoaderMsgErreur;
	/**
	 * @param newFenetreIdt
	 * @param newFenetreKey
	 * @param newFenetrelocation
	 * @param newFenetreStyleSheet
	 * @param newFenetreStageTitle
	 * @param newFenetreStageIcone
	 * @param newFenetreLoaderMsgErreur
	 */
	public Fenetres(int newFenetreIdt, String newFenetreKey, String newFenetrelocation, String newFenetreStyleSheet,
			String newFenetreStageTitle, String newFenetreStageIcone, String newFenetreLoaderMsgErreur) {
		super();
		this.fenetreIdt 			= newFenetreIdt;
		this.fenetreKey 			= newFenetreKey;
		this.fenetrelocation 		= newFenetrelocation;
		this.fenetreStyleSheet 		= newFenetreStyleSheet;
		this.fenetreStageTitle 		= newFenetreStageTitle;
		this.fenetreStageIcone 		= newFenetreStageIcone;
		this.fenetreLoaderMsgErreur = newFenetreLoaderMsgErreur;
	}
	/**
	 * @return the fenetreIdt
	 */
	public int getFenetreIdt() {
		return fenetreIdt;
	}
	/**
	 * @return the fenetreKey
	 */
	public String getFenetreKey() {
		return fenetreKey;
	}
	/**
	 * @return the fenetrelocation
	 */
	public String getFenetrelocation() {
		return fenetrelocation;
	}
	/**
	 * @return the fenetreStyleSheet
	 */
	public String getFenetreStyleSheet() {
		return fenetreStyleSheet;
	}
	/**
	 * @return the fenetreStageTitle
	 */
	public String getFenetreStageTitle() {
		return fenetreStageTitle;
	}
	/**
	 * @return the fenetreStageIcone
	 */
	public String getFenetreStageIcone() {
		return fenetreStageIcone;
	}
	/**
	 * @return the fenetreLoaderMsgErreur
	 */
	public String getFenetreLoaderMsgErreur() {
		return fenetreLoaderMsgErreur;
	}
	/**
	 * Methode	: toString
	 * Description 	: Cette methode permet d'afficher les informations d'une fenetre
	 * @return  String  
	 */
	@Override
	public String toString() {
		return "Fenetres [fenetreIdt=" + fenetreIdt + ", fenetreKey=" + fenetreKey + ", fenetrelocation="
				+ fenetrelocation + ", fenetreStyleSheet=" + fenetreStyleSheet + ", fenetreStageTitle="
				+ fenetreStageTitle + ", fenetreStageIcone=" + fenetreStageIcone + ", fenetreLoaderMsgErreur="
				+ fenetreLoaderMsgErreur + "]";
	}	
}