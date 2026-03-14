package controller;


import static bdd.TypeAgentBdd.deleteTypeAgent;
import static bdd.TypeAgentBdd.insertTypeAgent;
import static bdd.TypeAgentBdd.selectAllTypeAgent;
import static bdd.TypeAgentBdd.selectNbreTypeAgent;
import static bdd.TypeAgentBdd.updateTypeAgent;
import static utilities.UtilitiesControls.isTextFieldEmpty;

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
import model.TypeAgent;
import resources.Cstes;
import utilities.DialogBox;

public class TypeAgentAdminController extends AdministrationManagementController {
	/** Declaration des variables **/
	ObservableList<TypeAgent> listeDonnees	= FXCollections.observableArrayList();
	TypeAgent typeAgentSelected				= null;
	/** Déclaration des contrôles de la fenêtre **/
	@FXML private TextField							txfTypeAgentLbl;
	@FXML private TextField							txfTypeAgentLbc;
	@FXML private Label								lblTypeAgentIdt;
	@FXML private Label								lblMessage;
	@FXML private TableView<TypeAgent>				tbvDonnees;
	@FXML private TableColumn<TypeAgent, String>	tbcTypeAgentLbl;
	@FXML private TableColumn<TypeAgent, String>	tbcTypeAgentLbc;
	@Override
	public void initialize() {
		lblTitre.setText("Gestion des types d'agents");
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
		
		listeDonnees = selectAllTypeAgent();
		tbcTypeAgentLbl.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getTypeAgentLblProperty());
		tbcTypeAgentLbc.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getTypeAgentLbcProperty());
		
		tbvDonnees.setItems(listeDonnees);
	}
	/**
	 * Méthode permettant de gérer les zones de saisie 
	 * @param typeAgent [TypeAgent]	: TypeAgent a afficher, si null les zones sont Raz
	 */
	private void trtAffichageZones(TypeAgent typeAgent) {
		if(typeAgent != null) {
			lblTypeAgentIdt.setText(String.valueOf(typeAgent.getTypeAgentIdt()));
			txfTypeAgentLbl.setText(typeAgent.getTypeAgentLbl());
			txfTypeAgentLbc.setText(typeAgent.getTypeAgentLbc());
		} else {
			lblTypeAgentIdt.setText("");
			txfTypeAgentLbl.clear();
			txfTypeAgentLbc.clear();
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
		if (isTextFieldEmpty(txfTypeAgentLbl) || isTextFieldEmpty(txfTypeAgentLbc)) {
			return false;
		} else {
			return true;
		}
	}
	@Override
	public void evtOnMouseClickedBtnModifier() {
		retraitErreurs(txfTypeAgentLbl);
		retraitErreurs(txfTypeAgentLbc);
		if (trtControlesZones() == true) {
			typeAgentSelected.setTypeAgentLbl(txfTypeAgentLbl.getText());
			typeAgentSelected.setTypeAgentLbc(txfTypeAgentLbc.getText());
			updateTypeAgent(typeAgentSelected);
			trtAffichageDonnees();
			txfTypeAgentLbl.clear();
			txfTypeAgentLbc.clear();
			gestionBtn(true, false, false);
		} else {
			if (isTextFieldEmpty(txfTypeAgentLbl)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir le libellé long", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			} else if (isTextFieldEmpty(txfTypeAgentLbc)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir le libellé court", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			}
		}
	}

	@Override
	public void evtOnMouseClickedBtnAjouter() {
		retraitErreurs(txfTypeAgentLbl);
		retraitErreurs(txfTypeAgentLbc);
		if (trtControlesZones() == true) {
			TypeAgent newTypeAgent = new TypeAgent(0, txfTypeAgentLbl.getText(), txfTypeAgentLbc.getText());
			insertTypeAgent(newTypeAgent);
			trtAffichageDonnees();
			txfTypeAgentLbl.clear();
			txfTypeAgentLbc.clear();
			gestionBtn(true, false, false);
		} else {
			if (isTextFieldEmpty(txfTypeAgentLbl)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir le libellé long", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			} else if (isTextFieldEmpty(txfTypeAgentLbc)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir le libellé court", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			}
		}
	}

	@Override
	public void evtOnMouseClickedBtnSupprimer() {
		retraitErreurs(txfTypeAgentLbl);
		retraitErreurs(txfTypeAgentLbc);
		DialogBox dialogBox = new DialogBox("Supression du type d'agent", "", "Voulez-vous vraiment supprimer le type d'agent " + typeAgentSelected.getTypeAgentLbl(), AlertType.CONFIRMATION, ButtonType.CANCEL);
		ButtonType reponse = dialogBox.showDialogConfirmation();
		if(reponse == ButtonType.OK) {
			deleteTypeAgent(typeAgentSelected);
			trtAffichageDonnees();
			lblTypeAgentIdt.setText("Label");
			txfTypeAgentLbl.clear();
			txfTypeAgentLbc.clear();
			gestionBtn(true, false, false);
		}	
	}

	@Override
	public void evtOnMousePressedTbvDonnees(MouseEvent event) {
		retraitErreurs(txfTypeAgentLbl);
		retraitErreurs(txfTypeAgentLbc);
		if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
			typeAgentSelected = tbvDonnees.getSelectionModel().getSelectedItem();

			if(typeAgentSelected != null) {
				trtAffichageZones(typeAgentSelected);
				
				if (selectNbreTypeAgent(typeAgentSelected.getTypeAgentIdt()) != 0) {
					gestionBtn(false, true, false);
					lblMessage.setText("Le type d'agent ne peut pas être supprimé, il est utilisé !!");
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
