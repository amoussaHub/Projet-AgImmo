package controller;

import static bdd.CivilityBdd.deleteCivility;
import static bdd.CivilityBdd.selectAllCivility;
import static bdd.CivilityBdd.updateCivility;
import static bdd.CivilityBdd.selectNbreCivility;
import static bdd.CivilityBdd.insertCivility;
import static utilities.UtilitiesControls.isTextFieldEmpty;
import static bdd.CivilityBdd.selectAllCivility;

import java.io.Console;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Civility;
import resources.Cstes;
import utilities.DialogBox;

public class CivilityAdminController extends AdministrationManagementController {
	/** Declaration des variables **/
	ObservableList<Civility> listeDonnees	= FXCollections.observableArrayList();
	Civility civilitySelected				= null;
	/** Déclaration des contrôles de la fenêtre **/
	@FXML private TextField						txfCivilityLbl;
	@FXML private TextField						txfCivilityLbc;
	@FXML private Label							lblCivilityIdt;
	@FXML private Label							lblMessage;
	@FXML private TableView<Civility>			tbvDonnees;
	@FXML private TableColumn<Civility, String>	tbcCivilityLbl;
	@FXML private TableColumn<Civility, String>	tbcCivilityLbc;
	
	
	@Override
	public void initialize() {
		lblTitre.setText("Gestion des Civilités");
		lblMessage.setText("");
		trtAffichageDonnees();
		gestionBtn(true, false, false);
		
	}
	/**
	 * Methode 	: trtAffichageDonnees
	 * Description 	: Methode gerant l'affichage et le reaffichage de la TableView
	 */
	private void  trtAffichageDonnees() {
		tbvDonnees.getItems().clear();
		listeDonnees.clear();
		
		listeDonnees = selectAllCivility();
		tbcCivilityLbl.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getCivilityLblProperty());
		tbcCivilityLbc.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getCivilityLbcProperty());
		
		tbvDonnees.setItems(listeDonnees);
	}
	/**
	 * Méthode permettant de gérer les zones de saisie 
	 * @param civility [Civility]	: Civiliy a afficher, si null les zones sont Raz
	 */
	private void trtAffichageZones(Civility civility) {
		//civilitySelected = tbvDonnees.getSelectionModel().getSelectedItem();
		if(civility != null) {
			lblCivilityIdt.setText(String.valueOf(civility.getCivilityIdt()));
			txfCivilityLbl.setText(civility.getCivilityLbl());
			txfCivilityLbc.setText(civility.getCivilityLbc());
		} else {
			lblCivilityIdt.setText("");
			txfCivilityLbl.clear();
			txfCivilityLbc.clear();
		}
	}
	/**
	 * Méthode permettant de supprimer le contour en erreur
	 * @param textField	[TextField]
	 */
	private void retraitErreurs(TextField textField) {
		/** Initialisation des variables **/
		String classeErreur = "zoneEnErreur";
		/** Retrait de la classe erreur **/
		textField.getStyleClass().remove(classeErreur);
	}
	/**
	 * Méthodes permettant de contrôler les différentes zones de saisie
	 * @return	[boolean]	: indicateur si présence d'erreurs dans les zones
	 */
	private boolean trtControlesZones() {
		if (isTextFieldEmpty(txfCivilityLbl) || isTextFieldEmpty(txfCivilityLbc)) {
			return false;
		} else {
			return true;
		}
		
	}
	
	@Override
	public void evtOnMouseClickedBtnModifier() {
		retraitErreurs(txfCivilityLbl);
		retraitErreurs(txfCivilityLbc);
		if (trtControlesZones() == true) {
			civilitySelected.setCivilityLbl(txfCivilityLbl.getText());
			civilitySelected.setCivilityLbc(txfCivilityLbc.getText());
			updateCivility(civilitySelected);
			trtAffichageDonnees();
			txfCivilityLbl.clear();
			txfCivilityLbc.clear();
			gestionBtn(true, false, false);
		} else {
			if (isTextFieldEmpty(txfCivilityLbl)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir le libellé long", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			} else if (isTextFieldEmpty(txfCivilityLbc)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir le libellé court", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			}
		}
	}
	@Override
	public void evtOnMouseClickedBtnAjouter() {
		retraitErreurs(txfCivilityLbl);
		retraitErreurs(txfCivilityLbc);
		if (trtControlesZones() == true) {
			Civility newCivility = new Civility(0, txfCivilityLbl.getText(), txfCivilityLbc.getText());
			insertCivility(newCivility);
			trtAffichageDonnees();
			txfCivilityLbl.clear();
			txfCivilityLbc.clear();
			gestionBtn(true, false, false);
		} else {
			if (isTextFieldEmpty(txfCivilityLbl)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir le libellé long", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			} else if (isTextFieldEmpty(txfCivilityLbc)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir le libellé court", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			}
		}
	}
	@Override
	public void evtOnMouseClickedBtnSupprimer() {
		retraitErreurs(txfCivilityLbl);
		retraitErreurs(txfCivilityLbc);
		DialogBox dialogBox = new DialogBox("Supression de la civilité", "", "Voulez-vous vraiment supprimer la civilité " + civilitySelected.getCivilityLbl(), AlertType.CONFIRMATION, ButtonType.CANCEL);
		ButtonType reponse = dialogBox.showDialogConfirmation();
		if(reponse == ButtonType.OK) {
			deleteCivility(civilitySelected);
			trtAffichageDonnees();
			lblCivilityIdt.setText("Label");
			txfCivilityLbl.clear();
			txfCivilityLbc.clear();
			gestionBtn(true, false, false);
		}	
	}
	@Override
	public void evtOnMousePressedTbvDonnees(MouseEvent event) {
		retraitErreurs(txfCivilityLbl);
		retraitErreurs(txfCivilityLbc);
		if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
			civilitySelected = tbvDonnees.getSelectionModel().getSelectedItem();

			if(civilitySelected != null) {
				trtAffichageZones(civilitySelected);
				
				if (selectNbreCivility(civilitySelected.getCivilityIdt()) != 0) {
					gestionBtn(false, true, false);
					lblMessage.setText("La civilité ne peut pas être supprimé, elle est utilisée !!");
				} else {
					gestionBtn(false, true, true);
				}
			}
		} 
		
	}
	/**
	 * Méthode permettant de gérer l'activité des boutons
	 * @param ajouter	[boolean]
	 * @param modifier	[boolean]
	 * @param supprimer	[boolean]
	 */
	private void gestionBtn(boolean ajouter, boolean modifier, boolean supprimer) {
		btnAjouter.disableProperty().set(!ajouter);
		btnModifier.disableProperty().set(!modifier);
		btnSupprimer.disableProperty().set(!supprimer);
	}
}