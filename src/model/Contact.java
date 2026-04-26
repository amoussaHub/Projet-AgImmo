package model;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {

	/**
	 * contactIdt			: Identifiant du contact
	 * contactName			: Nom 
	 * contactFirstName		: Prénom
	 * contactMobile		: Mobile
	 * contactEmail			: Email
	 * contactCivilite		: Civilite ( lien avec la table [Civility])
	 * civility				: Civilite
	 * contactType			: Type du contact
	 * contactTypeLbl		: Type ( Landlord / Tenant ) pour affichage dans la tableview
	 * contactMessage		: Message
	 * contactDateDemande	: Date de la demande
	 */
	private int 		contactIdt;
	private String 		contactName;
	private String 		contactFirstName;
	private String		contactMobile;
	private String		contactEmail;
	private int 		contactCivilite;
	private Civility	civility;
	private int			contactType;
	private String 		contactTypeLbl;
	private String 		contactMessage;
	private LocalDate	contactDateDemande;
	/**
	 * @param newContactIdt
	 * @param newContactName
	 * @param newContactFirstName
	 * @param newContactMobile
	 * @param newContactEmail
	 * @param newContactCivilite
	 * @param newCivilite
	 * @param newContactType
	 * @param newContactTypeLbl
	 * @param newContactMessage
	 * @param newContactDateDemande
	 */
	public Contact(int newContactIdt, String newContactName, String newContactFirstName,
			String newContactMobile, String newContactEmail, int newContactCivilite, Civility newCivilite,
			int newContactType, String newContactTypeLbl,
			String newContactMessage, LocalDate newContactDateDemande) {
		super();
		this.contactIdt 			= newContactIdt;
		this.contactName 			= newContactName;
		this.contactFirstName 		= newContactFirstName;
		this.contactMobile 			= newContactMobile;
		this.contactEmail 			= newContactEmail;
		this.contactCivilite 		= newContactCivilite;
		this.civility				= newCivilite;
		this.contactType			= newContactType;
		this.contactTypeLbl			= newContactTypeLbl;
		this.contactMessage 		= newContactMessage;
		this.contactDateDemande		= newContactDateDemande;
	}
	/**
	 * @return the contactIdt
	 */
	public int getContactIdt() {
		return contactIdt;
	}
	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}
	public StringProperty getContactNameProperty() { 
		   return new SimpleStringProperty(contactName);
		}
	/**
	 * @return the contactFirstName
	 */
	public String getContactFirstName() {
		return contactFirstName;
	}
	public StringProperty getContactFirstNameProperty() { 
		   return new SimpleStringProperty(contactFirstName);
		}
	/**
	 * @return the contactMobile
	 */
	public String getContactMobile() {
		return contactMobile;
	}
	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}
	/**
	 * @return the contactCivilite
	 */
	public int getContactCivilite() {
		return contactCivilite;
	}	
	/**
	 * @return the civility
	 */
	public Civility getCivility() {
		return civility;
	}
	/**
	 * @return the contactType
	 */
	public int getContactType() {
		return contactType;
	}
	/**
	 * @return the contactTypeLbl
	 */
	public String getContactTypeLbl() {
		return contactTypeLbl;
	}
	public StringProperty getContactTypeLblProperty() { 
		   return new SimpleStringProperty(contactTypeLbl);
		}
	/**
	 * @return the contactMessage
	 */
	public String getContactMessage() {
		return contactMessage;
	}
	public StringProperty getContactMessageProperty() { 
		   return new SimpleStringProperty(contactMessage);
		}
	/**
	 * @return the contactDateDemande
	 */
	public LocalDate getContactDateDemande() {
		return contactDateDemande;
	}
	@Override
	public String toString() {
		return "Contact [contactIdt=" + contactIdt + ", contactName=" + contactName + ", contactFirstName="
				+ contactFirstName + ", contactMobile=" + contactMobile + ", contactEmail=" + contactEmail
				+ ", contactCivilite=" + contactCivilite + ", contactType=" + contactType + ", contactMessage="
				+ contactMessage + ", contactDateDemande=" + contactDateDemande + "]";
	}
}
