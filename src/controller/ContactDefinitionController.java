package controller;

import static bdd.CivilityBdd.selectAllCivility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Contact;
import model.InfoDetail;
import resources.Cstes;
import model.Civility;
import static interfaces.GestionCbxInfos.initCbx;

import bdd.CivilityBdd;
import bdd.InfoDetailBdd;
import interfaces.GestionCbxDefault;

public class ContactDefinitionController extends GeneralDefinitionController {
	/** Declaration des variables **/
	ObservableList<Civility>	listeCivilite 		= FXCollections.observableArrayList();	
	ObservableList<InfoDetail>	listeTypeContact	= FXCollections.observableArrayList();	
	Contact contact									= null;
	int index										= 0;
	/** controles de la fenetre **/
	@FXML private TextField				txfContactIdt;
	@FXML private TextField				txfContactName;
	@FXML private TextField				txfContactFirstName;
	@FXML private TextField				txfContactMobile;
	@FXML private TextField				txfContactEmail;
	@FXML private TextArea				txaContactMessage;
	@FXML private ComboBox<Civility> 	cbxCivility;
	@FXML private ComboBox<InfoDetail>	cbxContactType;
	@FXML private DatePicker			dpiDateDemande;
	/**
	 * Description 	: Methode lancee implicitement a l'affichage de la fenetre fxml.
	 * 				  Elle est automatiquement generee par heritage 
	 * 				  Elle va permettre d'initialiser les differentes combobox presentes
	 * 				  sur la fenetre.
	 */
	@Override
	@FXML public void initialize() {
		lblTitre.setText("Visualisation d'un contact");
		btnValider.setVisible(false);
		
		GestionCbxDefault.gestionCbxCivility(cbxCivility);
		
		listeTypeContact = InfoDetailBdd.selectAllInfoDetailForCbx(Cstes.TYPECONTACT);
		cbxContactType.getItems().addAll(listeTypeContact);
	}
	/**
	 * Description 	: Cette methode appelee lors de la creation du FXMLLoader permet d'afficher
	 * 				  le contact.
	 * @param 	contact	[Contact]
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
		
		cbxCivility.getSelectionModel().select(contact.getContactCivilite());
		cbxContactType.getSelectionModel().select(contact.getContactType());
		
		txfContactIdt.setText(String.valueOf(contact.getContactIdt()));
		txfContactName.setText(contact.getContactName());
		txfContactFirstName.setText(contact.getContactFirstName());
		txfContactMobile.setText(contact.getContactMobile());
		txfContactEmail.setText(contact.getContactEmail());
		txaContactMessage.setText(contact.getContactMessage());
		dpiDateDemande.setValue(contact.getContactDateDemande());
	}
	
	@Override
	@FXML public void evtOnMouseClickedBtnValider() {
		/**Non utilisé **/
	}
}
