package model;

/**
 * Classe	: Landlord
 * Cette classe contient les attributs, constructeur, Accesseurs et methodes permettant
 * d'instancier des objets de Type [Landlord].
 * Cette classe herite des caracteristiques de la Classe [Person]
 * Cette classe est en lecture seule (uniquement les Getters)
 * 
 * @author 	Alain CASARA
 * @version	1.0
 */
public class Landlord extends Person {
	/**
	 * Attributs de la classe [Landlord]
	 * Les attributs herites de la classe [Person] seront decris dans cette derniere.
	 * landlordLegalRegimeIdt		: Identifiant du regime juridique. Lien avec la table [LegalRegime]
	 * legalRegime					: LegalRegime
	 * landlordAddressIdt			: Identifiant adresse. Lien avec la table [Address]
	 * address						: Address
	 * landlordBankDetailIdt		: Identifiant coordonnees bancaires. Lien avec la table [BankDetail]
	 * bankDetail					: BankDetail
	 * landlordPrivateData			: Booleen pour l'autorisation de stockage des donnees personnelles
	 */
	private int 			landlordLegalRegimeIdt;
	private LegalRegime		legalRegime;
	private int 			landlordAddressIdt;
	private Address			address;
	private int 			landlordBankDetailIdt;
	private BankDetail		bankDetail;
	private Boolean			landlordPrivateData;
	/**
	 * 
	 * @param newPersonIdt
	 * @param newPersonName
	 * @param newPersonFirstName
	 * @param newPersonMobile
	 * @param newPersonPhone
	 * @param newPersonEmail
	 * @param newPersonCivility
	 * @param newLandlordLegalRegimeIdt
	 * @param newLandlordAddressIdt
	 * @param newLandlordBankDetailIdt
	 * @param newLegalRegime
	 * @param newAddress
	 * @param newBankDetail
	 * @param newLandlordPrivateData
	 */
	public Landlord(int newPersonIdt, String newPersonName, String newPersonFirstName, String newPersonMobile,
			String newPersonPhone, String newPersonEmail, int newPersonCivility, Civility civility, int newLandlordLegalRegimeIdt,
			int newLandlordAddressIdt, int newLandlordBankDetailIdt, LegalRegime newLegalRegime, Address newAddress, 
			BankDetail newBankDetail, boolean newLandlordPrivateData) {
		super(newPersonIdt, newPersonName, newPersonFirstName, newPersonMobile, newPersonPhone, newPersonEmail,
				newPersonCivility, civility);
		this.landlordLegalRegimeIdt 	= newLandlordLegalRegimeIdt;
		this.landlordAddressIdt 		= newLandlordAddressIdt;
		this.landlordBankDetailIdt 		= newLandlordBankDetailIdt;
		this.legalRegime				= newLegalRegime;
		this.address					= newAddress;
		this.bankDetail					= newBankDetail;
		this.landlordPrivateData		= newLandlordPrivateData;
	}
	
	/**
	 * Constructeur à vide 
	 */
	public Landlord() {
		super();
	}

	/**
	 * @return the landlordLegalRegimeIdt
	 */
	public int getLandlordLegalRegimeIdt() {
		return landlordLegalRegimeIdt;
	}
	/**
	 * @return the landlordAddressIdt
	 */
	public int getLandlordAddressIdt() {
		return landlordAddressIdt;
	}
	/**
	 * @return the landlordBankDetailIdt
	 */
	public int getLandlordBankDetailIdt() {
		return landlordBankDetailIdt;
	}
	/**
	 * @return the legalRegime
	 */
	public LegalRegime getLegalRegime() {
		return legalRegime;
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @return the bankDetail
	 */
	public BankDetail getBankDetail() {
		return bankDetail;
	}

	/**
	 * @return the landlordPrivateData
	 */
	public Boolean getLandlordPrivateData() {
		return landlordPrivateData;
	}
	/**
	 * @param landlordLegalRegimeIdt the landlordLegalRegimeIdt to set
	 */
	public void setLandlordLegalRegimeIdt(int landlordLegalRegimeIdt) {
		this.landlordLegalRegimeIdt = landlordLegalRegimeIdt;
	}
	/**
	 * @param landlordAddressIdt the landlordAddressIdt to set
	 */
	public void setLandlordAddressIdt(int landlordAddressIdt) {
		this.landlordAddressIdt = landlordAddressIdt;
	}
	/**
	 * @param landlordBankDetailIdt the landlordBankDetailIdt to set
	 */
	public void setLandlordBankDetailIdt(int landlordBankDetailIdt) {
		this.landlordBankDetailIdt = landlordBankDetailIdt;
	}
	/**
	 * @param landlordPrivateData the landlordPrivateData to set
	 */
	public void setLandlordPrivateData(Boolean landlordPrivateData) {
		this.landlordPrivateData = landlordPrivateData;
	}
	// *********************************************************************************************
	// Methodes
	// *********************************************************************************************
	/**
	 * Methode	: toString
	 * Description 	: Cette methode permet, dans le cadre de l'application, d'afficher les informations 
	 * utiles d'un proprietaire dans une combobox.
	 * @return  String (chaine de caracteres affichant le nom et le prenom d'un proprietaire)  
	 */
	@Override
	public String toString() {
		return getPersonName() + " " + getPersonFirstName();
	}
	/**
	 * Methode	: affichage
	 * Description 	: Cette methode permet renvoie l'integralite des informations d'un proprietaire
	 * @return  String
	 */
	public String affichageLandlord() {
		return "Landlord [landlordLegalRegimeIdt=" + landlordLegalRegimeIdt + ", landlordAddressIdt="
				+ landlordAddressIdt + ", landlordnewBankDetailIdt=" + landlordBankDetailIdt + landlordPrivateData  
				+ ", donnees Person=" + affichagePerson() + "]";
	}
}
