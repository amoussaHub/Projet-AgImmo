package model;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Company {

	/**
	 * Attributs de la classe
	 * companyIdt			: identifiant
	 * companyName			: nom
	 * companyAddressIdt	: identifiant de l'adresse
	 * companyTelephone		: n° de téléphone
	 * companyEmail			: Email
	 * companyWebSite		: site web
	 * companyLegalRegime	: identifiant régime légal
	 * companyCreationDate	: date de création
	 * companySiren			: Siren
	 * companySiret			: Siret
	 * companyAdminSeat		: top siège
	 * address				: Adresse de l'agence
	 * legalRegime			: Régime légal de l'agence
	 * companyMaps			: Coordonnées Maps pour carte
	 */
	private int 			companyIdt;
	private String 			companyName;
	private int 			companyAddressIdt;
	private String			companyTelephone;
	private String			companyEmail;
	private String 			companyWebSite;
	private int 			companyLegalRegime;
	private LocalDate 		companyCreationDate;
	private String 			companySiren;
	private String 			companySiret;
	private boolean 		companyAdminSeat;
	private Address			adress;
	private LegalRegime		legalRegime;
	private String			companyMaps;
	/**
	 * @param newCompanyIdt
	 * @param newCompanyName
	 * @param newCompanyAddressIdt
	 * @param newCompanyTelephone
	 * @param newCompanyEmail
	 * @param newCompanyWebSite
	 * @param newCompanyLegalRegime
	 * @param newCompanyCreationDate
	 * @param newCompanySiren
	 * @param newCompanySiret
	 * @param newCompanyAdminSeat
	 * @param newAddress
	 * @param newLegalRegime
	 * @Param newCompanyMaps
	 */
	public Company(int newCompanyIdt, String newCompanyName, int newCompanyAddressIdt, String newCompanyTelephone,
			String newCompanyEmail, String newCompanyWebSite, int newCompanyLegalRegime, LocalDate newCompanyCreationDate,
			String newCompanySiren, String newCompanySiret, boolean newCompanyAdminSeat, Address newAddress, LegalRegime newLegalRegime,
			String newCompanyMaps) {
		super();
		this.companyIdt 		 = newCompanyIdt;
		this.companyName 		 = newCompanyName;
		this.companyAddressIdt 	 = newCompanyAddressIdt;
		this.companyTelephone 	 = newCompanyTelephone;
		this.companyEmail 		 = newCompanyEmail;
		this.companyWebSite 	 = newCompanyWebSite;
		this.companyLegalRegime  = newCompanyLegalRegime;
		this.companyCreationDate = newCompanyCreationDate;
		this.companySiren 		 = newCompanySiren;
		this.companySiret 		 = newCompanySiret;
		this.companyAdminSeat 	 = newCompanyAdminSeat;
		this.adress				 = newAddress;
		this.legalRegime		 = newLegalRegime;
		this.companyMaps		 = newCompanyMaps;
	}
	/**
	 * 
	 */
	public Company() {
		super();
	}
	/**
	 * @return the companyIdt
	 */
	public int getCompanyIdt() {
		return companyIdt;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	public StringProperty getCompanyNameProperty() {
		return new SimpleStringProperty(companyName);
	}
	/**
	 * @return the companyAddressIdt
	 */
	public int getCompanyAddressIdt() {
		return companyAddressIdt;
	}
	/**
	 * @return the companyTelephone
	 */
	public String getCompanyTelephone() {
		return companyTelephone;
	}
	public StringProperty getCompanyTelephoneProperty() {
		return new SimpleStringProperty(companyTelephone);
	}
	/**
	 * @return the companyEmail
	 */
	public String getCompanyEmail() {
		return companyEmail;
	}
	public StringProperty getCompanyEmailProperty() {
		return new SimpleStringProperty(companyEmail);
	}
	/**
	 * @return the companyWebSite
	 */
	public String getCompanyWebSite() {
		return companyWebSite;
	}
	/**
	 * @return the companyLegalRegime
	 */
	public int getCompanyLegalRegime() {
		return companyLegalRegime;
	}
	/**
	 * @return the companyCreationDate
	 */
	public LocalDate getCompanyCreationDate() {
		return companyCreationDate;
	}
	/**
	 * @return the companySiren
	 */
	public String getCompanySiren() {
		return companySiren;
	}
	/**
	 * @return the companySiret
	 */
	public String getCompanySiret() {
		return companySiret;
	}
	/**
	 * @return the companyAdminSeat
	 */
	public boolean isCompanyAdminSeat() {
		return companyAdminSeat;
	}
	/**
	 * @return the adress
	 */
	public Address getAdress() {
		return adress;
	}
	/**
	 * @return the legalRegime
	 */
	public LegalRegime getLegalRegime() {
		return legalRegime;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @param companyAddressIdt the companyAddressIdt to set
	 */
	public void setCompanyAddressIdt(int companyAddressIdt) {
		this.companyAddressIdt = companyAddressIdt;
	}
	/**
	 * @param companyTelephone the companyTelephone to set
	 */
	public void setCompanyTelephone(String companyTelephone) {
		this.companyTelephone = companyTelephone;
	}
	/**
	 * @param companyEmail the companyEmail to set
	 */
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	/**
	 * @param companyWebSite the companyWebSite to set
	 */
	public void setCompanyWebSite(String companyWebSite) {
		this.companyWebSite = companyWebSite;
	}
	/**
	 * @param companyLegalRegime the companyLegalRegime to set
	 */
	public void setCompanyLegalRegime(int companyLegalRegime) {
		this.companyLegalRegime = companyLegalRegime;
	}
	/**
	 * @param companyCreationDate the companyCreationDate to set
	 */
	public void setCompanyCreationDate(LocalDate companyCreationDate) {
		this.companyCreationDate = companyCreationDate;
	}
	/**
	 * @param companySiren the companySiren to set
	 */
	public void setCompanySiren(String companySiren) {
		this.companySiren = companySiren;
	}
	/**
	 * @param companySiret the companySiret to set
	 */
	public void setCompanySiret(String companySiret) {
		this.companySiret = companySiret;
	}
	/**
	 * @param companyAdminSeat the companyAdminSeat to set
	 */
	public void setCompanyAdminSeat(boolean companyAdminSeat) {
		this.companyAdminSeat = companyAdminSeat;
	}
	/**
	 * @return the companyMaps
	 */
	public String getCompanyMaps() {
		return companyMaps;
	}
	/**
	 * @param companyMaps the companyMaps to set
	 */
	public void setCompanyMaps(String companyMaps) {
		this.companyMaps = companyMaps;
	}
	@Override
	public String toString() {
		return "Company [companyIdt=" + companyIdt + ", companyName=" + companyName + ", companyAddressIdt="
				+ companyAddressIdt + ", companyTelephone=" + companyTelephone + ", companyEmail=" + companyEmail
				+ ", companyWebSite=" + companyWebSite + ", companyLegalRegime=" + companyLegalRegime
				+ ", companyCreationDate=" + companyCreationDate + ", companySiren=" + companySiren + ", companySiret="
				+ companySiret + ", companyAdminSeat=" + companyAdminSeat + ", companyMaps=" + companyMaps + "]";
	}
}
