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
import model.Civility;
import static interfaces.GestionCbxInfos.initCbx;

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
	}
	/**
	 * Description 	: Cette methode appelee lors de la creation du FXMLLoader permet d'afficher
	 * 				  le contact.
	 * @param 	contact	[Contact]
	 */
	public void setContact(Contact contact) {
	}
	
	@Override
	@FXML public void evtOnMouseClickedBtnValider() {
		/**Non utilisé **/
	}
}
