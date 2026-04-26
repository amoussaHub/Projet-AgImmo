package model;

/**
 * Classe	: newBankDetail (Coordonnees bancaires)
 * Cette classe contient les attributs, constructeur, Accesseurs et methodes permettant
 * d'instancier des objets de Type [newBankDetail].
 * Cette classe est en lecture seule (uniquement les Getters)
 * 
 * @author 	Alain CASARA
 * @version	1.0
 */
public class BankDetail {
	/**
	 * Attributs de la classe [BankDetail]
	 * BankDetailIdt			: Identifiant des donnees banquaire
	 * BankDetailCode			: Code banque [Taille maxi 5 caracteres]
	 * BankDetailWicketCode		: Code guichet [Taille maxi 5 caracteres]
	 * BankDetailAccountNumber	: Numero de compte [Taille maxi 11 caracteres]
	 * BankDetailRibKey			: Cle RIB [Taille maxi 2 caracteres ]
	 * BankDetailHolder			: Titulaire du compte
	 * BankDetailIban			: Iban[Taille maxi 27 caracteres ]
	 * BankDetailDomiciliation	: l'intitule en clair de l'etablissement et du guichet tenant le compte
	 * BankDetailBic			: Bic [Taille maxi 11 caracteres
	 * BankDetailGenerationKey	: Champ temporaire utilise lors de la creation de la coordonnee afin de 
	 * 							  retrouver l'enregistrement
	 */
	private int				bankDetailIdt;
	private String			bankDetailCode;
	private String			bankDetailWicketCode;
	private String			bankDetailAccountNumber;
	private String			bankDetailRibKey;
	private String			bankDetailHolder;
	private String			bankDetailIban;
	private String			bankDetailDomiciliation;
	private String			bankDetailBic;
	private String			bankDetailGenerationKey;
	/**
	 * @param newBankDetailIdt
	 * @param newBankDetailCode
	 * @param newBankDetailWicketCode
	 * @param newBankDetailAccountNumber
	 * @param newBankDetailRibKey
	 * @param newBankDetailHolder
	 * @param newBankDetailIban
	 * @param newBankDetailDomiciliation
	 * @param newBankDetailBic
	 * @param newBankDetailGenerationKey
	 */
	public BankDetail(int newBankDetailIdt, String newBankDetailCode, String newBankDetailWicketCode,
			String newBankDetailAccountNumber, String newBankDetailRibKey, String newBankDetailHolder, String newBankDetailIban,
			String newBankDetailDomiciliation, String newBankDetailBic, String newBankDetailGenerationKey) {
		super();
		this.bankDetailIdt 				= newBankDetailIdt;
		this.bankDetailCode 			= newBankDetailCode;
		this.bankDetailWicketCode 		= newBankDetailWicketCode;
		this.bankDetailAccountNumber 	= newBankDetailAccountNumber;
		this.bankDetailRibKey 			= newBankDetailRibKey;
		this.bankDetailHolder 			= newBankDetailHolder;
		this.bankDetailIban 			= newBankDetailIban;
		this.bankDetailDomiciliation 	= newBankDetailDomiciliation;
		this.bankDetailBic 				= newBankDetailBic;
		this.bankDetailGenerationKey 	= newBankDetailGenerationKey;
	}
	/**
	 * 
	 */
	public BankDetail() {
		super();
	}

	// *********************************************************************************************
	// Getters
	// *********************************************************************************************
	/**
	 * @return the bankDetailIdt
	 */
	public int getBankDetailIdt() {
		return bankDetailIdt;
	}
	/**
	 * @return the bankDetailCode
	 */
	public String getBankDetailCode() {
		return bankDetailCode;
	}
	/**
	 * @return the bankDetailWicketCode
	 */
	public String getBankDetailWicketCode() {
		return bankDetailWicketCode;
	}
	/**
	 * @return the bankDetailAccountNumber
	 */
	public String getBankDetailAccountNumber() {
		return bankDetailAccountNumber;
	}
	/**
	 * @return the bankDetailRibKey
	 */
	public String getBankDetailRibKey() {
		return bankDetailRibKey;
	}
	/**
	 * @return the bankDetailHolder
	 */
	public String getBankDetailHolder() {
		return bankDetailHolder;
	}
	/**
	 * @return the bankDetailIban
	 */
	public String getBankDetailIban() {
		return bankDetailIban;
	}
	/**
	 * @return the bankDetailDomiciliation
	 */
	public String getBankDetailDomiciliation() {
		return bankDetailDomiciliation;
	}
	/**
	 * @return the bankDetailBic
	 */
	public String getBankDetailBic() {
		return bankDetailBic;
	}
	/**
	 * @return the bankDetailGenerationKey
	 */
	public String getBankDetailGenerationKey() {
		return bankDetailGenerationKey;
	}
	/**
	 * @param bankDetailIdt the bankDetailIdt to set
	 */
	public void setBankDetailIdt(int bankDetailIdt) {
		this.bankDetailIdt = bankDetailIdt;
	}
	/**
	 * @param bankDetailCode the bankDetailCode to set
	 */
	public void setBankDetailCode(String bankDetailCode) {
		this.bankDetailCode = bankDetailCode;
	}
	/**
	 * @param bankDetailWicketCode the bankDetailWicketCode to set
	 */
	public void setBankDetailWicketCode(String bankDetailWicketCode) {
		this.bankDetailWicketCode = bankDetailWicketCode;
	}
	/**
	 * @param bankDetailAccountNumber the bankDetailAccountNumber to set
	 */
	public void setBankDetailAccountNumber(String bankDetailAccountNumber) {
		this.bankDetailAccountNumber = bankDetailAccountNumber;
	}
	/**
	 * @param bankDetailRibKey the bankDetailRibKey to set
	 */
	public void setBankDetailRibKey(String bankDetailRibKey) {
		this.bankDetailRibKey = bankDetailRibKey;
	}
	/**
	 * @param bankDetailHolder the bankDetailHolder to set
	 */
	public void setBankDetailHolder(String bankDetailHolder) {
		this.bankDetailHolder = bankDetailHolder;
	}
	/**
	 * @param bankDetailIban the bankDetailIban to set
	 */
	public void setBankDetailIban(String bankDetailIban) {
		this.bankDetailIban = bankDetailIban;
	}
	/**
	 * @param bankDetailDomiciliation the bankDetailDomiciliation to set
	 */
	public void setBankDetailDomiciliation(String bankDetailDomiciliation) {
		this.bankDetailDomiciliation = bankDetailDomiciliation;
	}
	/**
	 * @param bankDetailBic the bankDetailBic to set
	 */
	public void setBankDetailBic(String bankDetailBic) {
		this.bankDetailBic = bankDetailBic;
	}
	/**
	 * @param bankDetailGenerationKey the bankDetailGenerationKey to set
	 */
	public void setBankDetailGenerationKey(String bankDetailGenerationKey) {
		this.bankDetailGenerationKey = bankDetailGenerationKey;
	}
	// *********************************************************************************************
	// Methodes
	// *********************************************************************************************	
	/**
	 * Methode	: toString
	 * Description 	: Cette methode permet, dans le cadre de l'application, d'afficher les informations 
	 * utiles d'une coordonnee bancaire.
	 * @return  String  
	 */
	@Override
	public String toString() {
		return "BankDetail [bankDetailIdt=" + bankDetailIdt + ", bankDetailCode=" + bankDetailCode
				+ ", bankDetailWicketCode=" + bankDetailWicketCode + ", bankDetailAccountNumber="
				+ bankDetailAccountNumber + ", bankDetailRibKey=" + bankDetailRibKey + ", bankDetailHolder="
				+ bankDetailHolder + ", bankDetailIban=" + bankDetailIban + ", bankDetailDomiciliation="
				+ bankDetailDomiciliation + ", bankDetailBic=" + bankDetailBic + ", bankDetailGenerationKey="
				+ bankDetailGenerationKey + "]";
	}
}
