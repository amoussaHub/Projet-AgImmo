package controller;

import static bdd.AddressBdd.insertAddress;
import static bdd.AddressBdd.selectOneAdresseByKey;
import static bdd.AddressBdd.updateAddress;
import static bdd.BankDetailBdd.insertBankDetail;
import static bdd.BankDetailBdd.selectOneBankDetailByKey;
import static bdd.BankDetailBdd.updateBankDetail;
import static bdd.ContactBdd.deleteContact;
import static bdd.InfoDetailBdd.selectOneInfoDetailDescription;
import static bdd.LandlordBdd.insertLandlord;
import static bdd.LandlordBdd.updateLandlord;
import static bdd.TownBdd.selectAllTown;
import static utilities.UtilitiesControls.isEmailAdress;
import static utilities.UtilitiesControls.isItemNotSelected;
import static utilities.UtilitiesControls.isTextFieldEmpty;
import static utilities.UtilitiesControls.isTextFieldLongueur;
import static utilities.UtilitiesControls.isTextFieldNumeric;
import static utilities.UtilitiesControls.validatePhoneNumber;
import static utilities.UtilitiesPassay.generateKey;

import interfaces.GestionCbxDefault;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import model.Address;
import model.BankDetail;
import model.Civility;
import model.Contact;
import model.Landlord;
import model.LegalRegime;
import model.Town;
import resources.Cstes;
import utilities.DialogBox;

/**
 * Cette classe est la Classe de definition d'un proprietaire. Elle sert pour les operations de 
 * creation et de modification d'un proprietaire.
 * Cette classe herite de la classe GeneralDefinitionController
 * 
 * @author 	Alain CASARA
 * @version	1.0
 */
public class LandlordDefinitionController extends GeneralDefinitionController implements GestionCbxDefault{
	/** Declaration des variables **/
	ObservableList<Civility>	listeCivilite 		= FXCollections.observableArrayList();	
	ObservableList<LegalRegime> listeLegalRegimes 	= FXCollections.observableArrayList();	
	ObservableList<Town> 		listeTown			= FXCollections.observableArrayList();	
	private Landlord landlord						= null;
	private Contact contact							= null;
	private BankDetail bankDetail					= null;
	private Address address							= null;
	/** controles de la fenetre **/
	@FXML TextField				txfPersonIdt;
	@FXML TextField				txfPersonName;
	@FXML TextField				txfPersonFirstName;
	@FXML TextField				txfPersonMobile;
	@FXML TextField				txfPersonPhone;
	@FXML TextField				txfPersonEmail;
	@FXML ComboBox<Civility>	cbxCivility;
	@FXML ComboBox<LegalRegime>	cbxLandlordLegalRegimeIdt;
	@FXML CheckBox				chkLandlordPrivateData;
	@FXML TextField				txfAddressDeliveryPoint;
	@FXML TextField				txfAddressNumber;
	@FXML TextField				txfAddressPortLabel;
	@FXML TextField				txfAddressNext;
	@FXML TextField				txfSelectTown;
	@FXML ComboBox<Town>		cbxAddressTown;
	@FXML TextField				txfTownPostCode;
	@FXML TextField				txfBankDetailCode;
	@FXML TextField				txfBankDetailWicketCode;
	@FXML TextField				txfBankDetailAccountNumber;
	@FXML TextField				txfBankDetailRibKey;
	@FXML TextField				txfBankDetailHolder;
	@FXML TextField				txfBankDetailDomiciliation;
	@FXML TextField				txfBankDetailBic;
	@FXML ImageView				imvImpression;
	@FXML ImageView				imvAide;
	@FXML TextField				txfIbanCodePays;
	@FXML TextField				txfIbanCleIban;
	@FXML TextField				txfIbanCodeBq;
	@FXML TextField				txfIbanWicketCode;
	@FXML TextField				txfIbanAccountNumber;
	@FXML TextField				txfIbanRibKey;

	/**
	 * Description 	: Methode lancee implicitement a l'affichage de la fenetre fxml.
	 * 				  Elle est automatiquement generee par heritage 
	 * 				  Elle va permettre d'initialiser les differentes combobox presentes
	 * 				  sur la fenetre.
	 * 				  Les combobox peuvent etre initialisees avec les valeurs par defaut
	 * 				  definies dans la table [InfoDetail]
	 */
	@Override
	public void initialize() {
		/** Remplissage de la combobox Civilite et initialisation avec la valeur par defaut
		 * definie dans la table informations
		 **/
		/** Remplissage de la combobox sur les Regimes juridiques et initialisation avec la valeur par defaut
		 * definie dans la table informations
		 **/ 
		/** Remplissage de la combobox sur les villes **/
		
		txfSelectTown.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Town> filteredTowns = listeTown.stream()
                    .filter(town -> town.getTownName().toLowerCase().contains(newValue.toLowerCase())
                            || town.getTownPostCode().contains(newValue))
                    .collect(Collectors.toList());
            cbxAddressTown.setItems(FXCollections.observableArrayList(filteredTowns));
            if (!cbxAddressTown.isShowing()) {
            	cbxAddressTown.show();
            }
        });

		/** Rajout de listener pour gérer les changements sur les données bancaires **/
		txfBankDetailCode.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				txfIbanCodeBq.setText(newValue);
			}
		});
		txfBankDetailWicketCode.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				txfIbanWicketCode.setText(newValue);
			}
		});
		txfBankDetailAccountNumber.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				txfIbanAccountNumber.setText(newValue);
			}
		});
		txfBankDetailRibKey.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				txfIbanRibKey.setText(newValue);
			}
		});
		/** Infos-bulles sur les zones **/
	}
	/**
	 * Description 	: Cette methode appelee lors de la creation du FXMLLoader permet de recuperer le candidat a convertir
	 * 				  en propriétaire.
	 * @param 	contact	[Contact]
	 */
	public void setContact(Contact contact) {
	}
	/**
	 * Description 	: Cette methode appelee lors de la creation du FXMLLoader permet de recuperer le proprietaire a modifier
	 *                en affichant ses informations dans la fenetre. 
	 * @param	landlord	[Landlord]
	 */
	public void setLandlord(Landlord landlord) {
	}
	/**
	 * Description 	: Cette methode appelee lors de la creation du FXMLLoader permet de definir l'action CRU en cours
	 * @param	codeAction	[String]
	 */
	public void setAction(String codeAction) {
		switch (codeAction) {
		case "create": 
			lblTitre.setText("Creation d'un nouveau proprietaire");
			break;
		case "update": 
			lblTitre.setText("Modification d'un proprietaire");
			break;
		case "Contact": 
			lblTitre.setText("Conversion d'un contact en proprietaire");
		}
		this.codeAction = codeAction;
	}
	/**
	 * Description 	: Cette Methode correspond aux traitements suite a un clic sur le bouton [Valider]
	 * 				  1. controles des differentes zones obligatoires
	 * 				  2. Affichage d'une dialogueBox si besoin pour afficher les zones de saisie
	 * 				  3. Traitements sur la base de donnees.
	 */
	@Override
	public void evtOnMouseClickedBtnValider() {
		/** Initialisation des variables **/
		String messageErreur = "";
	}    
	/**
	 * Methode 	: evtOnActionCbxAddressTownIdt
	 * Description 	: Cette Methode permet d'afficher dans la zone texte le code postal de la ville
	 */
	public void evtOnActionCbxAddressTown() {
		txfTownPostCode.setText(cbxAddressTownIdt.getSelectionModel().getSelectedItem().getTownPostCode());
	}
}
