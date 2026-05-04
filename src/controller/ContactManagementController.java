package controller;

import static bdd.ContactBdd.deleteContact;
import static bdd.ContactBdd.selectAllContactWithSelection;
import static bdd.FenetresBdd.selectOneFenetre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Contact;
import model.Fenetres;
import model.LoaderFXML;
import resources.Cstes;
import utilities.DialogBox;

public class ContactManagementController extends GeneralManagementController {
	/** Declaration des variables **/
	ObservableList<Contact> listeDonnees	= FXCollections.observableArrayList();
	/** Declaration des controles de la fenetre **/
	@FXML private TableView<Contact>			tbvDonnees;
	@FXML private TableColumn<Contact, String>	tbcContactName;
	@FXML private TableColumn<Contact, String>  tbcContactFirstName;
	@FXML private TableColumn<Contact, String>  tbcContactType;
	@FXML private TableColumn<Contact, String>  tbcContactMessage;
	@FXML private CheckBox						chkSelectionProprietaire;
	@FXML private CheckBox						chkSelectionLocataires;
	
	int topLandlord = 0;
	int topTenant	= 0;
	Contact contactSelected = null;

	@Override
	public void initialize() {
		lblTitre.setText("Gestion des contacts");
		btnModifier.setText("Visualiser");
		
		topLandlord = isChkSelectedInt(chkSelectionProprietaire, 1);
		topTenant 	= isChkSelectedInt(chkSelectionLocataires, 2);
		
		trtAffichageDonnees();
		
		if (listeDonnees.isEmpty()) {
			btnAjouter.setDisable(true);
			btnModifier.setDisable(true);
			btnSupprimer.setDisable(true);
		}
	}
	/**
	 * Methode 	: trtAffichageDonnees
	 * Description 	: Methode gerant l'affichage et le reaffichage de la TableView
	 */
	private void  trtAffichageDonnees() {
		tbvDonnees.getItems().clear();
		listeDonnees.clear();
		
		listeDonnees = selectAllContactWithSelection(topLandlord, topTenant, Cstes.TYPECONTACT);
		
		tbcContactName.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getContactNameProperty());
		tbcContactFirstName.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getContactFirstNameProperty());
		tbcContactType.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getContactTypeLblProperty());
		tbcContactMessage.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getContactMessageProperty());
		
		tbvDonnees.setItems(listeDonnees);
	}
	@Override
	public void evtOnMouseClickedImvSelection() {
		topLandlord = isChkSelectedInt(chkSelectionProprietaire, 1);
		topTenant 	= isChkSelectedInt(chkSelectionLocataires, 2);
		
		trtAffichageDonnees();
	}

	@Override
	public void evtOnMouseClickedBtnModifier() {
			try {
				Stage primaryStage = new Stage();
				Fenetres fenetre = selectOneFenetre(Cstes.CONTACTDEFINITION);
				
				if(fenetre!=null) {
					LoaderFXML loaderFxml = new LoaderFXML(fenetre);
					primaryStage = loaderFxml.createLoaderBorderPane();
					ContactDefinitionController controller = loaderFxml.getLoader().getController();
					controller.setDialogStage(primaryStage);
					controller.setContact(contactSelected);
					primaryStage.show();
				}   
			} catch (Exception e){
				e.printStackTrace();
			}
	}

	@Override
	public void evtOnMouseClickedBtnAjouter() {
		if (contactSelected != null) {
			try {
				Stage primaryStage = new Stage();
				Fenetres fenetre = selectOneFenetre(Cstes.LANDLORDDEFINITION);
				
				if(fenetre!=null) {
					LoaderFXML loaderFxml = new LoaderFXML(fenetre);
					primaryStage = loaderFxml.createLoaderBorderPane();
					LandlordDefinitionController controller = loaderFxml.getLoader().getController();
					controller.setDialogStage(primaryStage);
					controller.setContact(contactSelected);
					controller.setAction("Contact");
					primaryStage.show();
				}   
			} catch (Exception e){
				e.printStackTrace();
			}
		} else {
			DialogBox dialogBox = new DialogBox("Supression du Contact", "", "Veuillez séléctionner un contact", AlertType.CONFIRMATION, ButtonType.CANCEL);
			ButtonType reponse = dialogBox.showDialogConfirmation();
		}
	}

	/**
	 * Methode 	: evtOnMouseClickedBtnSupprimer
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur le bouton [Supprimer]
	 */
	@Override
	public void evtOnMouseClickedBtnSupprimer() {
		DialogBox dialogBox = new DialogBox("Supression du Contact", "", "Voulez-vous vraiment supprimer le contact " + contactSelected.getContactName() + " " + contactSelected.getContactFirstName()
		, AlertType.CONFIRMATION, ButtonType.CANCEL);
		ButtonType reponse = dialogBox.showDialogConfirmation();
		if(reponse == ButtonType.OK) {
			deleteContact(contactSelected);
			trtAffichageDonnees();
		}	
	}

	@Override
	public void evtOnMousePressedTbvDonnees(MouseEvent event) {
		// TODO Auto-generated method stub
		contactSelected = tbvDonnees.getSelectionModel().getSelectedItem();
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			evtOnMouseClickedBtnAjouter();
		}
	}
	
	/** Méthode permettant de vérifier si une checkBox est coché : return value si coché, 0 sinon () **/
	private int isChkSelectedInt(CheckBox checkBox, int value) {
		int checkBoxInt = 0;
		if (checkBox.isSelected()) {
			checkBoxInt = value;
		}
		return checkBoxInt;
	}
}
