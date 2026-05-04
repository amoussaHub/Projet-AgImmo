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

import java.util.List;
import java.util.stream.Collectors;

import bdd.AddressBdd;
import bdd.BankDetailBdd;
import bdd.CivilityBdd;
import bdd.LegalRegimeBdd;
import bdd.TownBdd;
import interfaces.GestionCbxDefault;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
	private boolean validerClicked 					= false;	
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
		GestionCbxDefault.gestionCbxCivility(cbxCivility);
		/** Remplissage de la combobox sur les Regimes juridiques et initialisation avec la valeur par defaut
		 * definie dans la table informations
		 **/ 
		GestionCbxDefault.gestionCbxLegalRegime(cbxLandlordLegalRegimeIdt);
		/** Remplissage de la combobox sur les villes **/
		listeTown = selectAllTown();
		cbxAddressTown.getItems().addAll(listeTown);
		
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
		String infoPortablePhone 			= "Le format doit être le suivant : XX-XX-XX-XX-XX ";
		String infoEmail					= "l'addresse email doit avoir le format suivant : XXXXXXX@YYYY.ZZZ";
		String infoBankDetailCode			= "Le code banque doit faire 5 caractères de long et ne contenir que des chiffres";
		String infoBankDetailWicketCode		= "Le code guichet doit faire caractères de long et ne contenir que des chiffres";
		String infoBankDetailAccountNumber 	= "Le numéro de compte doit faire 11 caractères de long";
		String infoBankDetailRibKey			= "La clé rib doit faire 2 caractères de long et ne contenir que des chiffres";
		String infoBankDetailBic			= "Le code BIC doit faire entre 8 et 11 caractères de long";
		
		txfPersonMobile.setTooltip(new Tooltip(infoPortablePhone));
		txfPersonPhone.setTooltip(new Tooltip(infoPortablePhone));
		txfPersonEmail.setTooltip(new Tooltip(infoEmail));
		txfBankDetailCode.setTooltip(new Tooltip(infoBankDetailCode));
		txfBankDetailWicketCode.setTooltip(new Tooltip(infoBankDetailWicketCode));
		txfBankDetailAccountNumber.setTooltip(new Tooltip(infoBankDetailAccountNumber));
		txfBankDetailRibKey.setTooltip(new Tooltip(infoBankDetailRibKey));
		txfBankDetailBic.setTooltip(new Tooltip(infoBankDetailBic));
	}
	/**
	 * Description 	: Cette methode appelee lors de la creation du FXMLLoader permet de recuperer le candidat a convertir
	 * 				  en propriétaire.
	 * @param 	contact	[Contact]
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	/**
	 * Description 	: Cette methode appelee lors de la creation du FXMLLoader permet de recuperer le proprietaire a modifier
	 *                en affichant ses informations dans la fenetre. 
	 * @param	landlord	[Landlord]
	 */
	public void setLandlord(Landlord landlord) {
		this.landlord = landlord;
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
			cbxCivility.getSelectionModel().select(landlord.getPersonCivility());
			cbxLandlordLegalRegimeIdt.getSelectionModel().select(landlord.getLandlordLegalRegimeIdt());
			cbxAddressTown.getSelectionModel().select(TownBdd.selectOneTown(landlord.getAddress().getAddressTownIdt()));
			
			address = AddressBdd.selectOneAdresse(landlord.getLandlordAddressIdt());
			bankDetail = BankDetailBdd.selectOneBankDetail(landlord.getLandlordBankDetailIdt());
			
			txfPersonIdt.setText(String.valueOf(landlord.getPersonIdt()));
			txfPersonName.setText(landlord.getPersonName());
			txfPersonFirstName.setText(landlord.getPersonFirstName());
			txfPersonMobile.setText(landlord.getPersonMobile());
			txfPersonPhone.setText(landlord.getPersonPhone());
			txfPersonEmail.setText(landlord.getPersonEmail());
			chkLandlordPrivateData.setSelected(landlord.getLandlordPrivateData());
			
			txfAddressDeliveryPoint.setText(address.getAddressDeliveryPoint());
			txfAddressNumber.setText(address.getAddressNumber());
			txfAddressPortLabel.setText(address.getAddressPortLabel());
			txfAddressNext.setText(address.getAddressNext());
			
			txfBankDetailCode.setText(bankDetail.getBankDetailCode());
			txfBankDetailWicketCode.setText(bankDetail.getBankDetailWicketCode());
			txfBankDetailAccountNumber.setText(bankDetail.getBankDetailAccountNumber());
			txfBankDetailRibKey.setText(bankDetail.getBankDetailRibKey());
			txfBankDetailDomiciliation.setText(bankDetail.getBankDetailDomiciliation());
			txfBankDetailBic.setText(bankDetail.getBankDetailBic());
			txfBankDetailHolder.setText(bankDetail.getBankDetailHolder());
			break;
		case "Contact": 
			lblTitre.setText("Conversion d'un contact en proprietaire");
			cbxCivility.getSelectionModel().select(contact.getContactCivilite());
			
			txfPersonIdt.setText(String.valueOf(contact.getContactIdt()));
			txfPersonName.setText(contact.getContactName());
			txfPersonFirstName.setText(contact.getContactFirstName());
			txfPersonMobile.setText(contact.getContactMobile());
			txfPersonEmail.setText((contact.getContactEmail()));
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
		final String MESSAGEERREURTITLE = "Les zones marquées d’un * sont obligatoires :" + Cstes.CR;
		String messageErreur = MESSAGEERREURTITLE;
		
		/** controle des zones obligatoires **/
		if (isTextFieldEmpty(txfPersonMobile) || !validatePhoneNumber(txfPersonMobile)) {
			messageErreur += "- Portable (le format doit être : XX-XX-XX-XX-XX)" + Cstes.CR;
		} 
		if (isTextFieldEmpty(txfPersonPhone)) {
			messageErreur += "- Téléphone (le format doit être : XX-XX-XX-XX-XX)" + Cstes.CR;
		} 
		if (isTextFieldEmpty(txfPersonEmail) || !isEmailAdress(txfPersonEmail)) {
			messageErreur += "- Email (le format doit être : XXXXXXX@YYYY.ZZZ)" + Cstes.CR;
		} 
		if (isTextFieldEmpty(txfBankDetailCode) || !isTextFieldLongueur(txfBankDetailCode, 5) || !isTextFieldNumeric(txfBankDetailCode)) {
			messageErreur += "- Code banque (le code banque doit faire 5 caractères de type numérique)" + Cstes.CR;
		} 
		if (isTextFieldEmpty(txfBankDetailWicketCode) || !isTextFieldLongueur(txfBankDetailWicketCode, 5) || !isTextFieldNumeric(txfBankDetailWicketCode)) {
			messageErreur += "- Code guichet (le code guichet doit faire 5 caractères de type numérique)" + Cstes.CR;
		} 
		if (isTextFieldEmpty(txfBankDetailAccountNumber) || !isTextFieldLongueur(txfBankDetailAccountNumber, 11)) {
			messageErreur += "- Numéro de compte (le numéro de compte doit faire 11 caractères)" + Cstes.CR;
		} 
		if (isTextFieldEmpty(txfBankDetailRibKey) || !isTextFieldLongueur(txfBankDetailRibKey, 2) || !isTextFieldNumeric(txfBankDetailRibKey)) {
			messageErreur += "- Clé RIB (la clé RIB doit faire 2 caractères de type numérique)" + Cstes.CR;
		} 
		if (isTextFieldEmpty(txfBankDetailBic) || !isTextFieldLongueur(txfBankDetailBic, 11)) {
			messageErreur += "- BIC (le BIC doit faire 11 caractères)" + Cstes.CR;
		} 
		if (messageErreur.equals(MESSAGEERREURTITLE)) {
			messageErreur = "";
		}
		
		if (messageErreur.isEmpty()) {
			String key = generateKey();
			String iban = txfIbanCodePays.getText() + txfIbanCleIban.getText() + txfIbanCodeBq.getText() + txfIbanWicketCode.getText() + txfIbanAccountNumber.getText() + txfIbanRibKey.getText(); 
			
			int civilityIdt = cbxCivility.getValue().getCivilityIdt();
			int legalRegimeIdt = cbxLandlordLegalRegimeIdt.getValue().getLegalRegimeIdt(); 
			//int addressIdt = selectOneAdresseByKey(key);
			//int bankDetailIdt = selectOneBankDetailByKey(key);
			
			if(codeAction.equals("create") || codeAction.equals("Contact")) {
				address = new Address();
				bankDetail = new BankDetail();
				landlord = new Landlord();
				
				if (codeAction.equals("Contact")) deleteContact(contact);
				
				/** Création de l'address **/
				address.setAddressDeliveryPoint(txfAddressDeliveryPoint.getText());
				address.setAddressNumber(txfAddressNumber.getText());
				address.setAddressPortLabel(txfAddressPortLabel.getText());
				address.setAddressNext(txfAddressNext.getText());
				address.setAddressTownIdt(cbxAddressTown.getValue().getTownIdt());
				address.setAddressGenerationKey(key);
				
				insertAddress(address);
				
				/** création de l'identité bancaire **/
				bankDetail.setBankDetailCode(txfBankDetailCode.getText());
				bankDetail.setBankDetailWicketCode(txfBankDetailWicketCode.getText());
				bankDetail.setBankDetailAccountNumber(txfBankDetailAccountNumber.getText());
				bankDetail.setBankDetailRibKey(txfBankDetailRibKey.getText());
				bankDetail.setBankDetailHolder(txfBankDetailHolder.getText());
				bankDetail.setBankDetailIban(iban);
				bankDetail.setBankDetailDomiciliation(txfBankDetailDomiciliation.getText());
				bankDetail.setBankDetailBic(txfBankDetailBic.getText());
				bankDetail.setBankDetailGenerationKey(key);
				
				insertBankDetail(bankDetail);
				
				/** Création du Landlord **/
				int addressIdt = selectOneAdresseByKey(key);
				int bankDetailIdt = selectOneBankDetailByKey(key);
				
				landlord.setPersonName(txfPersonName.getText());
				landlord.setPersonFirstName(txfPersonFirstName.getText());
				landlord.setPersonMobile(txfPersonMobile.getText());
				landlord.setPersonPhone(txfPersonPhone.getText());
				landlord.setPersonEmail(txfPersonEmail.getText());
				landlord.setPersonCivility(civilityIdt);
				landlord.setLandlordLegalRegimeIdt(legalRegimeIdt);
				landlord.setLandlordAddressIdt(addressIdt);
				landlord.setLandlordBankDetailIdt(bankDetailIdt);
				landlord.setLandlordPrivateData(chkLandlordPrivateData.isSelected());
				
				landlord.setCivility(CivilityBdd.selectOneCivility(civilityIdt));
				landlord.setLandlordLegalRegimeIdt(LegalRegimeBdd.selectOneLegalRegime(legalRegimeIdt).getLegalRegimeIdt());
				landlord.setLandlordAddressIdt(AddressBdd.selectOneAdresse(addressIdt).getAddressIdt());
				landlord.setLandlordBankDetailIdt(BankDetailBdd.selectOneBankDetail(bankDetailIdt).getBankDetailIdt());
				
				insertLandlord(landlord);
			} else {
				/** Modification de l'address **/
				address.setAddressDeliveryPoint(txfAddressDeliveryPoint.getText());
				address.setAddressNumber(txfAddressNumber.getText());
				address.setAddressPortLabel(txfAddressPortLabel.getText());
				address.setAddressNext(txfAddressNext.getText());
				address.setAddressTownIdt(cbxAddressTown.getValue().getTownIdt());
				address.setAddressGenerationKey(key);
				
				updateAddress(address);
				
				/** Modification de l'identité bancaire **/
				bankDetail.setBankDetailCode(txfBankDetailCode.getText());
				bankDetail.setBankDetailWicketCode(txfBankDetailWicketCode.getText());
				bankDetail.setBankDetailAccountNumber(txfBankDetailAccountNumber.getText());
				bankDetail.setBankDetailRibKey(txfBankDetailRibKey.getText());
				bankDetail.setBankDetailHolder(txfBankDetailHolder.getText());
				bankDetail.setBankDetailIban(iban);
				bankDetail.setBankDetailDomiciliation(txfBankDetailDomiciliation.getText());
				bankDetail.setBankDetailBic(txfBankDetailBic.getText());
				bankDetail.setBankDetailGenerationKey(key);
				
				updateBankDetail(bankDetail);
				
				/** Modification du Landlord **/
				int addressIdt = selectOneAdresseByKey(key);
				int bankDetailIdt = selectOneBankDetailByKey(key);
				
				landlord.setPersonName(txfPersonName.getText());
				landlord.setPersonFirstName(txfPersonFirstName.getText());
				landlord.setPersonMobile(txfPersonMobile.getText());
				landlord.setPersonPhone(txfPersonPhone.getText());
				landlord.setPersonEmail(txfPersonEmail.getText());
				landlord.setPersonCivility(civilityIdt);
				landlord.setLandlordLegalRegimeIdt(legalRegimeIdt);
				landlord.setLandlordAddressIdt(addressIdt);
				landlord.setLandlordBankDetailIdt(bankDetailIdt);
				landlord.setLandlordPrivateData(chkLandlordPrivateData.isSelected());
				
				landlord.setCivility(CivilityBdd.selectOneCivility(civilityIdt));
				landlord.setLandlordLegalRegimeIdt(LegalRegimeBdd.selectOneLegalRegime(legalRegimeIdt).getLegalRegimeIdt());
				landlord.setLandlordAddressIdt(AddressBdd.selectOneAdresse(addressIdt).getAddressIdt());
				landlord.setLandlordBankDetailIdt(BankDetailBdd.selectOneBankDetail(bankDetailIdt).getBankDetailIdt());
				
				updateLandlord(landlord);
			}
			validerClicked = true;
			this.dialogStage.close();
		} else {
			DialogBox dialogBox = new DialogBox("Supression du propriétaire", "", messageErreur, AlertType.CONFIRMATION, ButtonType.CANCEL);
			ButtonType reponse = dialogBox.showDialogConfirmation();
		}
		
	}    
	/**
	 * Methode 	: evtOnActionCbxAddressTownIdt
	 * Description 	: Cette Methode permet d'afficher dans la zone texte le code postal de la ville
	 */
	public void evtOnActionCbxAddressTown() {
		txfTownPostCode.setText(cbxAddressTown.getSelectionModel().getSelectedItem().getTownPostCode());
	}
	
	/**
	 * Description 	: Cette Methode renvoie la valeur de l'attribut [validerClicked]
	 *							  permettant a l'ecran appelant de savoir si le bouton [Valider]
	 *							  a ete clique
	 * @return	boolean
	 */
	public boolean isValiderClicked() {
		return validerClicked;
	}   
}
