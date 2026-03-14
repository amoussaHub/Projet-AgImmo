package model;
/**
 * Classe	: Address
 * Cette classe contient les attributs, constructeur, Accesseurs et methodes permettant
 * d'instancier des objets de Type [Address].
  * 
 * @author 	Alain CASARA
 * @version	1.0
 */

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Address {
	/**
	 * Attributs de la classe [Address]
	 * addressIdt				: Identifiant de l'adresse. AutoIncremente dans la base de donnees.
	 * addressDeliveryPoint		: Point de livraison ( etage, en appartement, boite aux lettres, couloir)
	 * addressNumber			: Numero de l'adresse + complement (bis, ter, etc.)
	 * addressPortLabel			: libelle de la voie
	 * addressNext				: Adresse suite  (Poste restante, boite postale, etc.)
	 * addressTownIdt			: Identifiant de la ville ( lien avec la table Town )
	 * town						: objet ville
	 * addressGenerationKey		: Champ temporaire utilise lors de la creation de l'adresse afin de retrouver
	 * 							  l'enregistrement
	 */
	private int 	addressIdt;
	private String	addressDeliveryPoint;
	private String	addressNumber;
	private String	addressPortLabel;
	private String	addressNext;
	private int		addressTownIdt;
	private Town	town;
	private String	addressGenerationKey;
	/**
	 * Constructeur de la classe [Address]
	 * @param newAddressIdt
	 * @param newAddressDeliveryPoint
	 * @param newAddressNumber
	 * @param newAddressPortLabel
	 * @param newAddressNext
	 * @param newAddressTownIdt
	 * @param newTown
	 * @param newAddressGenerationKey
	 */
	public Address(int newAddressIdt, String newAddressDeliveryPoint, String newAddressNumber, String newAddressPortLabel,
			String newAddressNext, int newAddressTownIdt, Town newTown,String newAddressGenerationKey) {
		super();
		this.addressIdt 			= newAddressIdt;
		this.addressDeliveryPoint 	= newAddressDeliveryPoint;
		this.addressNumber 			= newAddressNumber;
		this.addressPortLabel 		= newAddressPortLabel;
		this.addressNext 			= newAddressNext;
		this.addressTownIdt 		= newAddressTownIdt;
		this.town					= newTown;
		this.addressGenerationKey 	= newAddressGenerationKey;
	}
	
	/**
	 * 
	 */
	public Address() {
		super();
	}
	/**
	 * @return the addressIdt
	 */
	public int getAddressIdt() {
		return addressIdt;
	}
	/**
	 * @return the addressDeliveryPoint
	 */
	public String getAddressDeliveryPoint() {
		return addressDeliveryPoint;
	}
	/**
	 * @return the addressNumber
	 */
	public String getAddressNumber() {
		return addressNumber;
	}
	/**
	 * @return the addressPortLabel
	 */
	public String getAddressPortLabel() {
		return addressPortLabel;
	}
	/**
	 * @return the addressNext
	 */
	public String getAddressNext() {
		return addressNext;
	}
	/**
	 * @return the addressTownIdt
	 */
	public int getAddressTownIdt() {
		return addressTownIdt;
	}
	/**
	 * @return the town
	 */
	public Town getTown() {
		return town;
	}
	
	/**
	 * @return the adressComplete
	 */
	public StringProperty getAdressCompleteProperty() {
		StringProperty adressNumber 	= new SimpleStringProperty(this.addressNumber);
		StringProperty addressPortLabel = new SimpleStringProperty(this.addressPortLabel);
		StringProperty addressNext 		= new SimpleStringProperty(this.addressNext);
		StringProperty addressComplete	= new SimpleStringProperty("");
		addressComplete.bind(Bindings.concat(adressNumber," ",addressPortLabel," ",addressNext));
		return addressComplete;
	}

	/**
	 * @return the addressGenerationKey
	 */
	public String getAddressGenerationKey() {
		return addressGenerationKey;
	}
	
	/**
	 * @param addressIdt the addressIdt to set
	 */
	public void setAddressIdt(int addressIdt) {
		this.addressIdt = addressIdt;
	}
	/**
	 * @param addressDeliveryPoint the addressDeliveryPoint to set
	 */
	public void setAddressDeliveryPoint(String addressDeliveryPoint) {
		this.addressDeliveryPoint = addressDeliveryPoint;
	}
	/**
	 * @param addressNumber the addressNumber to set
	 */
	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}
	/**
	 * @param addressPortLabel the addressPortLabel to set
	 */
	public void setAddressPortLabel(String addressPortLabel) {
		this.addressPortLabel = addressPortLabel;
	}
	/**
	 * @param addressNext the addressNext to set
	 */
	public void setAddressNext(String addressNext) {
		this.addressNext = addressNext;
	}
	/**
	 * @param addressTownIdt the addressTownIdt to set
	 */
	public void setAddressTownIdt(int addressTownIdt) {
		this.addressTownIdt = addressTownIdt;
	}
	/**
	 * @param town the town to set
	 */
	public void setTown(Town town) {
		this.town = town;
	}
	/**
	 * @param addressGenerationKey the addressGenerationKey to set
	 */
	public void setAddressGenerationKey(String addressGenerationKey) {
		this.addressGenerationKey = addressGenerationKey;
	}
	/**
	 * Methode	: toString
	 * Description 	: Cette methode permet renvoie l'integralite des informations d'une adresse.
	 * @return  String   
	 */
	@Override
	public String toString() {
		return "Address [addressIdt=" + addressIdt + ", addressDeliveryPoint=" + addressDeliveryPoint
				+ ", addressNumber=" + addressNumber + ", addressPortLabel=" + addressPortLabel + ", addressNext="
				+ addressNext + ", addressTownIdt=" + addressTownIdt + ", addressGenerationKey=" + addressGenerationKey
				+ "]";
	}
}
