package controller;

import static bdd.AddressBdd.insertAddress;
import static bdd.AddressBdd.selectOneAdresse;
import static bdd.AddressBdd.selectOneAdresseByKey;
import static bdd.AddressBdd.updateAddress;
import static bdd.CompanyBdd.controleSiret;
import static bdd.CompanyBdd.insertCompany;
import static bdd.CompanyBdd.selectOneCompanyAdminSeat;
import static bdd.CompanyBdd.updateCompany;
import static bdd.InfoDetailBdd.selectOneInfoDetailDescription;
import static bdd.LegalRegimeBdd.selectAllLegalRegime;
import static bdd.TownBdd.selectAllTown;
import static utilities.UtilitiesControls.isEmailAdress;
import static utilities.UtilitiesControls.isItemNotSelected;
import static utilities.UtilitiesControls.isTextFieldEmpty;
import static utilities.UtilitiesControls.isTextFieldNumeric;
import static utilities.UtilitiesControls.validatePhoneNumber;
import static utilities.UtilitiesPassay.generateKey;
import static bdd.TownBdd.selectOneTown;
import static bdd.CompanyBdd.controleSiret;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Address;
import model.Company;
import model.InfoDetail;
import model.LegalRegime;
import model.Town;
import resources.Cstes;
import utilities.DialogBox;

public class CompanyDefinitionController extends GeneralDefinitionController {
	/** *************************************************************
	 * Déclaration des variables
	 * **************************************************************/
	private boolean validerClicked 					= false;								
	private String  codeAction						= "";
	private ObservableList<LegalRegime> listeLegalRegimes 	= FXCollections.observableArrayList();	
	private ObservableList<Town> 		listeTown			= FXCollections.observableArrayList();	
	private Address address							= null;
	private Company company							= null;
	int index										= 0;
	FilteredList<Town> filteredTowns 				;
	/** *************************************************************
	 * Déclaration des contrôles du fichier fxml
	 * **************************************************************/
	@FXML private TextField				txfCompanyIdt;
	@FXML private TextField				txfCompanyName;
	@FXML private TextField				txfCompanyTelephone;
	@FXML private TextField				txfCompanyEmail;
	@FXML private TextField				txfCompanyWebSite;
	@FXML private TextField				txfCompanySiren;
	@FXML private TextField				txfCompanySiret;
	@FXML private TextField				txfAddressDeliveryPoint;
	@FXML private TextField				txfAddressNumber;
	@FXML private TextField				txfAddressPortLabel;
	@FXML private TextField				txfAddressNext;
	@FXML private TextField				txfTownPostCode;
	@FXML private TextField				txfCompanyMaps;
	@FXML private TextField				txfSelectTown;
	@FXML private ComboBox<LegalRegime> cbxCompanyLegalRegime;
	@FXML private ComboBox<Town>		cbxAddressTown;
	@FXML private DatePicker			dapCompanyCreationDate;
	@FXML private CheckBox				chkCompanyAdminSeat;
	@FXML private WebView				wbvMaps;
	
	/**
	 * Méthode lancée implicitement à la génération de la fenêtre fxml.
	 * Elle permet d'initialiser les valeurs statiques de la fenêtre
	 */
	@SuppressWarnings("unused")
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		/** Remplissage de la combobox sur les Regimes juridiques et initialisation avec la valeur par defaut
		 * definie dans la table informations
		 **/ 
		listeLegalRegimes	= selectAllLegalRegime();
		cbxCompanyLegalRegime.getItems().addAll(listeLegalRegimes);
		/** Remplissage de la combobox sur les villes **/
		listeTown			= selectAllTown();
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
		
		/** Initialisation des Tooltips **/
		String infoTelephoneString = "Le téléphone doit avoir le format suivant : XX-XX-XX-XX-XX ";
		String infoEmailString = "L'email doit avoir le format suivant : XXXXXXX@YYYY.ZZZ ";
		String infoSirenString = "Le siren doit faire 9 caractères de long et ne contenir que des chiffres";
		String infoSiretString = "Le siret doit faire 14 caractères de long et ne contenir que des chiffres " + Cstes.CR + "Le début doit être équivalent au SIREN";
		/** Affectation des Tooltips **/
		txfCompanyTelephone.setTooltip(new Tooltip(infoTelephoneString));
		txfCompanyEmail.setTooltip(new Tooltip(infoEmailString));
		txfCompanySiren.setTooltip(new Tooltip(infoSirenString));
		txfCompanySiret.setTooltip(new Tooltip(infoSiretString));
		
		/** initialisation du calendrier à la date du jour **/
		dapCompanyCreationDate.setValue(LocalDate.now());
		
	}

	@Override
	public void evtOnMouseClickedBtnValider() {
		// TODO Auto-generated method stub
		/** Initialisation des variables **/
		String messageErreur = "Les zones marquées d’un * sont obligatoires :" + Cstes.CR;
		/** *************************************************************************************************************
		 *  controle des zones de l'agence
		 ** *************************************************************************************************************/  
		/** controle de la zone obligatoire : nom **/
		if (isTextFieldEmpty(txfCompanyName)) {
			messageErreur += "- Nom" + Cstes.CR;
		}
		/** controle de la zone obligatoire : telephone  **/
		if (isTextFieldEmpty(txfCompanyTelephone) || validatePhoneNumber(txfCompanyTelephone)) {
			messageErreur += "- Telephone (le format doit être : XX-XX-XX-XX-XX)";
		}
		/** controle de la zone obligatoire : email  **/
		if (isTextFieldEmpty(txfCompanyEmail) || isEmailAdress(txfCompanyEmail)) {
			messageErreur += "- Email (le format doit être : XXXXXXX@YYYY.ZZZ)";
		}
		/** contrôle de la date de création **/
		if (codeAction.equals("create") && dapCompanyCreationDate.getValue() != LocalDate.now()) {
			dapCompanyCreationDate.setValue(LocalDate.now());
		}
		/** contrôle du SIREN **/
		if (isTextFieldEmpty(txfCompanySiren)) {
			messageErreur += "- Siren" + Cstes.CR;
		} 
		/** contrôle du SIRET **/
		if (isTextFieldEmpty(txfCompanySiret) || !txfCompanySiret.getText().startsWith(txfCompanySiren.getText())) {
			messageErreur += "- Siret (Le n° siret doit commencer par le n° siren)";
		} else {
			if (controleSiret(txfCompanySiret.getText(), company.getCompanyIdt()) < 0) {
				messageErreur += "- Siret : ce n° siret est déjà utilisé" + Cstes.CR;
			}
		}

		/** *************************************************************************************************************
		 *  controle des zones Address
		 ** *************************************************************************************************************/  
		/** controle de la zone Libelle de la voie **/
		if (isTextFieldEmpty(txfAddressNumber)) {
			messageErreur += "- Numéro de la voie" + Cstes.CR; 
		}
		if (isTextFieldEmpty(txfAddressPortLabel)) {
			messageErreur += "- Libellé de la voie" + Cstes.CR; 
		}
		/** controle de la ville **/
		
		/** S'il y a une erreur, on l'affiche dans un dialogbox **/
		if(!messageErreur.isEmpty()) {
			DialogBox dialogBox = new DialogBox("Erreur de saisie", "", messageErreur, AlertType.CONFIRMATION, null);
			dialogBox.showDialogConfirmation();
		} else {
			/**
			 * Contrôle du siège
			 * Il ne peut avoir qu'une agence de type siège dans la Bdd
			 * En création, si le contrôle CheckBox est sélectionné, il faut contrôler la présence d'un siège
			 * En modification, si le contrôle CheckBox est sélectionné et l'agence n'est pas le siège, il faut contrôler la présence d'un siège
			 * En modification, si le contrôle CheckBox est non sélectionné et l'agence est le siège, il faut le signaler
			 */
			messageErreur				= "";
			Boolean majSiege			= false;
			Company companyAdminSeat 	= selectOneCompanyAdminSeat();
			if(codeAction.equals("create")) {
				if(chkCompanyAdminSeat.isSelected()) {
					if(companyAdminSeat!=null) {
						messageErreur  += "Le siège du groupe est déjà défini sur l'agence de " + companyAdminSeat.getCompanyName() + Cstes.CR;
						majSiege		= true;
					}
				}
			} else {
				if(chkCompanyAdminSeat.isSelected() && !company.isCompanyAdminSeat()) {
					if(companyAdminSeat!=null) { 
						messageErreur  += "Le siège du groupe est déjà définition sur l'agence de " + companyAdminSeat.getCompanyName() + Cstes.CR;
						majSiege		= true;
					}
				} else if(!chkCompanyAdminSeat.isSelected() && company.isCompanyAdminSeat()) {
					messageErreur  += "Attention, le groupe n'a plus de siège " + Cstes.CR;
				}
			}
			if(!messageErreur.isEmpty()) {
				DialogBox dialogBox = new DialogBox("Information", "Modification du siège social à confirmer", messageErreur, AlertType.CONFIRMATION, ButtonType.CANCEL);
				ButtonType reponse = dialogBox.showDialogConfirmation();
				if(reponse == ButtonType.OK) {
					if(majSiege) {
						companyAdminSeat.setCompanyAdminSeat(false);
						updateCompany(companyAdminSeat);
					}
				}
			}
			if(codeAction.equals("create")) {
				company = new Company();
				address = new Address();
			}
			/** Traitements de mise à  jour des informations de l'agence **/
			/** Initialisation des variables **/
			int addressIdt 		= 0;
			String key = generateKey();
			/** *************************************************************************************************************
			 *  Mise a jour de l'objet Address avec les donnees de la fenetre
			 ** *************************************************************************************************************/ 
			/** Le traitement se fait selon le code action **/
			if(codeAction.equals("create")) {
				int townIdt = cbxAddressTown.getValue().getTownIdt();
				address = new Address(0, txfAddressDeliveryPoint.getText(), txfAddressNumber.getText(), txfAddressPortLabel.getText(), txfAddressNext.getText(), townIdt, selectOneTown(townIdt), key);
				
				insertAddress(address);
			} else {
				int townIdt = cbxAddressTown.getSelectionModel().getSelectedItem().getTownIdt();
				
				address.setAddressDeliveryPoint(txfAddressDeliveryPoint.getText());
				address.setAddressNumber(txfAddressNumber.getText());
				address.setAddressPortLabel(txfAddressPortLabel.getText());
				address.setAddressNext(txfAddressNext.getText());
				address.setAddressTownIdt(townIdt);
				address.setAddressGenerationKey(key);

				updateAddress(address);
			}
			/** *************************************************************************************************************
			 *  Mise a jour de l'objet company avec les donnees de la fenetre
			 ** *************************************************************************************************************/ 
			if(codeAction.equals("create")) {
				//company = new Company(0, txfCompanyName.getText(), , key, key, key, addressIdt, null, key, messageErreur, validerClicked, address, null, key);
			} else {
			}
		}
	}
	/**
	 * Description 	: Cette methode appelee lors de la creation du FXMLLoader permet de recuperer l'agence a modifier
	 *                en affichant ses informations dans la fenetre. 
	 * @param company	[Company]
	 */
	public void setCompany(Company company) {
		this.company = company;
		
		String urlMaps = null;
		Boolean companyAdminSeat = null;
		Address address = null;
		Town town = null;
		
		if (selectOneCompanyAdminSeat() == company) {
			companyAdminSeat = true;
		} else {
			companyAdminSeat = false;
		}
		
		address = selectOneAdresse(company.getCompanyAddressIdt());
		town = selectOneTown(address.getAddressTownIdt());
		
		txfCompanyIdt.setText(String.valueOf(company.getCompanyIdt()));
		txfCompanyName.setText(company.getCompanyName());
		txfCompanyTelephone.setText(company.getCompanyTelephone());
		txfCompanyEmail.setText(company.getCompanyEmail());
		txfCompanyWebSite.setText(company.getCompanyWebSite());
		cbxCompanyLegalRegime.setValue(company.getLegalRegime());
		dapCompanyCreationDate.setValue(company.getCompanyCreationDate());
		txfCompanySiren.setText(company.getCompanySiren());
		txfCompanySiret.setText(company.getCompanySiret());
		chkCompanyAdminSeat.setSelected(companyAdminSeat);
		txfCompanyMaps.setText(company.getCompanyMaps());
		txfAddressDeliveryPoint.setText(address.getAddressDeliveryPoint());
		txfAddressNumber.setText(address.getAddressNumber());
		txfAddressPortLabel.setText(address.getAddressPortLabel());
		txfAddressNext.setText(address.getAddressNext());
		cbxAddressTown.setValue(town);
		txfTownPostCode.setText(town.getTownPostCode());
		
		if (!isTextFieldEmpty(txfCompanyMaps)) {
			urlMaps = Cstes.WEBMAPS;
			urlMaps = urlMaps.replace("carte", txfCompanyMaps.getText());
			WebEngine webEngine = wbvMaps.getEngine();
			webEngine.loadContent(urlMaps);
		}
	}
	/**
	 * Description 	: Cette methode appelee lors de la creation du FXMLLoader permet de definir l'action CRU en cours
	 * @param	codeAction	[String]
	 */
	public void setAction(String codeAction) {
		switch (codeAction) {
		case "create": 
			lblTitre.setText("Creation d'une nouvelle agence");
			break;
		case "update": 
			lblTitre.setText("Modification d'une agence");
			break;
		}
		this.codeAction = codeAction;
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
	/**
	 * Methode 	: evtOnActionCbxAddressTownIdt
	 * Description 	: Cette Methode permet d'afficher dans la zone texte le code postal de la ville
	 */
	@FXML private void evtOnActionCbxAddressTown() {
        Town selectedTown = cbxAddressTown.getSelectionModel().getSelectedItem();
        if (selectedTown != null) {
            txfTownPostCode.setText(selectedTown.getTownPostCode());
        }
	}
}
