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

	@Override
	public void initialize() {
	}
	/**
	 * Methode 	: trtAffichageDonnees
	 * Description 	: Methode gerant l'affichage et le reaffichage de la TableView
	 */
	private void  trtAffichageDonnees() {
	}
	@Override
	public void evtOnMouseClickedImvSelection() {
	}

	@Override
	public void evtOnMouseClickedBtnModifier() {
	}

	@Override
	public void evtOnMouseClickedBtnAjouter() {
	}

	/**
	 * Methode 	: evtOnMouseClickedBtnSupprimer
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur le bouton [Supprimer]
	 */
	@Override
	public void evtOnMouseClickedBtnSupprimer() {
	}

	@Override
	public void evtOnMousePressedTbvDonnees(MouseEvent event) {
		// TODO Auto-generated method stub
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			evtOnMouseClickedBtnAjouter();
		}
	}
}
