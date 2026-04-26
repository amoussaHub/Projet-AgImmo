package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * La classe [Person] est une classe abstraite, elle ne peut être instancier.
 * @author acasara
 *
 */
public abstract class Person {
	/**
	 * personIdt		[int]		: Identifiant de la personne
	 * personName		[String]	: Nom 
	 * personFirstName	[String]	: Prénom 
	 * personMobile		[String]	: Téléphone portable
	 * personPhone		[String]	: Téléphone
	 * personEmail		[String]	: Email
	 * personCivility	[int]		: Identifiant de la civilité
	 * civility			[Civility]	: Civilité
	 */
	private int  		personIdt;
	private String 		personName;
	private String 		personFirstName;
	private String 		personMobile;
	private String 		personPhone;
	private String 		personEmail;
	private int    		personCivility;
	private Civility	civility;
	/**
	 * 
	 * @param newPersonIdt
	 * @param newPersonName
	 * @param newPersonFirstName
	 * @param newPersonMobile
	 * @param newPersonPhone
	 * @param newPersonEmail
	 * @param newPersonCivility
	 * @param newCivility
	 */
	public Person(int newPersonIdt, String newPersonName, String newPersonFirstName, 
			String newPersonMobile, String newPersonPhone,
			String newPersonEmail, int newPersonCivility, Civility newCivility) {
		super();
		this.personIdt 			= newPersonIdt;
		this.personName 		= newPersonName;
		this.personFirstName 	= newPersonFirstName;
		this.personMobile 		= newPersonMobile;
		this.personPhone 		= newPersonPhone;
		this.personEmail 		= newPersonEmail;
		this.personCivility 	= newPersonCivility;
		this.civility			= newCivility;
	}
	public Person() {
	}
	/**
	 * @return the personIdt
	 */
	public int getPersonIdt() {
		return personIdt;
	}
	/**
	 * @return the personName
	 */
	public String getPersonName() {
		return personName;
	}
	public StringProperty getPersonNameProperty() {
		return new SimpleStringProperty(personName);
	}
	/**
	 * @return the personFirstName
	 */
	public String getPersonFirstName() {
		return personFirstName;
	}
	public StringProperty getPersonFirstNameProperty() {
		return new SimpleStringProperty(personFirstName);
	}
	/**
	 * @return the personMobile
	 */
	public String getPersonMobile() {
		return personMobile;
	}
	/**
	 * @return the personPhone
	 */
	public String getPersonPhone() {
		return personPhone;
	}
	/**
	 * @return the personEmail
	 */
	public String getPersonEmail() {
		return personEmail;
	}
	/**
	 * @return the personCivility
	 */
	public int getPersonCivility() {
		return personCivility;
	}
	/**
	 * @return the civility
	 */
	public Civility getCivility() {
		return civility;
	}
	/**
	 * @param personName the personName to set
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	/**
	 * @param personFirstName the personFirstName to set
	 */
	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}
	/**
	 * @param personMobile the personMobile to set
	 */
	public void setPersonMobile(String personMobile) {
		this.personMobile = personMobile;
	}
	/**
	 * @param personPhone the personPhone to set
	 */
	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}
	/**
	 * @param personEmail the personEmail to set
	 */
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}
	/**
	 * @param personCivility the personCivility to set
	 */
	public void setPersonCivility(int personCivility) {
		this.personCivility = personCivility;
	}
	/**
	 * @param civility the civility to set
	 */
	public void setCivility(Civility civility) {
		this.civility = civility;
	}
	/**
	 * @return	[String] 
	 */
	@Override
	public String toString() {
		return personName + personFirstName;
	}
	/**
	 * Description				: Méthode permettant d'afficher l'état de l'objet courant
	 * @return	[String]
	 */
	public String affichagePerson() {
		return "Person [personIdt=" + personIdt + ", personName=" + personName + ", personFirstName=" + personFirstName
				+ ", personMobile=" + personMobile + ", personPhone=" + personPhone + ", personEmail=" + personEmail
				+ ", personCivility=" + personCivility + "]";
	}
}