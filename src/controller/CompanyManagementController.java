package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Agent;
import model.Company;
import model.Fenetres;
import model.LoaderFXML;
import model.TypeAgent;
import resources.Cstes;

import static bdd.AddressBdd.deleteAddress;
import static bdd.CompanyBdd.*;
import static bdd.FenetresBdd.selectOneFenetre;
import static bdd.TypeAgentBdd.deleteTypeAgent;
import static bdd.TypeAgentBdd.selectOneTypeAgent;

//import java.awt.Button;

//import java.awt.TextField;

import utilities.DialogBox;

public class CompanyManagementController extends GeneralManagementController {
	/** *************************************************************
	 * Déclaration des variables
	 * **************************************************************/
	ObservableList<Company> listeDonnees 	= FXCollections.observableArrayList();
	Agent agent								= null;
	/** *************************************************************
	 * Déclaration des contrôles du fichier fxml
	 * **************************************************************/
	@FXML private TableView<Company> tbvDonnees;
	@FXML private TableColumn<Company, String> 	tbcCompanyName;
	@FXML private TableColumn<Company, String> 	tbcCompanyTelephone;
	@FXML private TableColumn<Company, String> 	tbcCompanyEmail;
	@FXML private TableColumn<Company, String> 	tbcCompanyAddress;
	@FXML private TableColumn<Company, String> 	tbcCompanyTown;
	
	@FXML private ImageView imvSelection;
	@FXML private TextField txfSelection;
	@FXML private Button btnSupprimer;
	@FXML private Button btnModifier;
	@FXML private Button btnAjouter;
	@FXML private Label lblTitle;
	/**
	 * Méthode lancée implicitement à la génération de la fenêtre fxml.
	 * Elle permet d'initialiser les valeurs statiques de la fenêtre
	 */
	@Override
	public void initialize() {
		imvSelection.setVisible(false);
		txfSelection.setVisible(false);
		
		tbcCompanyName.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getCompanyNameProperty());
		tbcCompanyTelephone.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getCompanyTelephoneProperty());
		tbcCompanyEmail.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getCompanyEmailProperty());
		tbcCompanyAddress.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getAdress().getAdressCompleteProperty());
		tbcCompanyTown.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getAdress().getTown().getTownCompleteProperty());	
		
		tbvDonnees.setItems(listeDonnees);
		trtAffichage();
	}
	/**
	 * Méthode permettant :
	 * 1. Raz ObservableList
	 * 2. Raz TableView
	 * 3. Remplissage de l'ObservableList
	 * 4. Mise à jour de la TableView
	 */
	private void trtAffichage() {
		listeDonnees.clear();
		tbvDonnees.getItems().clear();
		listeDonnees	= selectAllCompany();
		tbvDonnees.getItems().addAll(listeDonnees);
	}

	@Override
	public void evtOnMouseClickedImvSelection() {
		// TODO Auto-generated method stub
		// Méthode non utilisée pour l'instant mais obligatoire.
	}

	/**
	 * Méthode permettant d'appeler la fenêtre de définition d'une agence en modification
	 */
	@Override
	public void evtOnMouseClickedBtnModifier() {
		try {
			Stage primaryStage = new Stage();
			Fenetres fenetre = selectOneFenetre(Cstes.AGENCEDEFINITION);
			
			if(fenetre!=null) {
				LoaderFXML loaderFxml = new LoaderFXML(fenetre);
				primaryStage = loaderFxml.createLoaderBorderPane();
				CompanyDefinitionController controller = loaderFxml.getLoader().getController();
				controller.setCompany(tbvDonnees.getSelectionModel().getSelectedItem());
				controller.setAction("update");
				controller.setDialogStage(primaryStage);
				primaryStage.showAndWait();
				if (controller.isValiderClicked()) {
					trtAffichage();
				}
			}   
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Méthode permettant d'appeler la fenêtre de définition d'une agence en ajout
	 */
	@Override
	public void evtOnMouseClickedBtnAjouter() {
		try {
			Stage primaryStage = new Stage();
			Fenetres fenetre = selectOneFenetre(Cstes.AGENCEDEFINITION);
			
			if(fenetre!=null) {
				LoaderFXML loaderFxml = new LoaderFXML(fenetre);
				primaryStage = loaderFxml.createLoaderBorderPane();
				CompanyDefinitionController controller = loaderFxml.getLoader().getController();
				controller.setDialogStage(primaryStage);
				controller.setAction("create");
				primaryStage.showAndWait();
				if (controller.isValiderClicked()) {
					trtAffichage();
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur le bouton [Supprimer]
	 * La suppression doit être validée par un choix utilisateur au travers d'une dialogBox
	 */
	@Override
	@FXML public void evtOnMouseClickedBtnSupprimer() {
		Company companySelected = tbvDonnees.getSelectionModel().getSelectedItem();
		DialogBox dialogBox = new DialogBox("Supression d'une agence", "", "Voulez-vous vraiment supprimer l'agence " + companySelected.getCompanyName(), AlertType.CONFIRMATION, ButtonType.CANCEL);
		ButtonType reponse = dialogBox.showDialogConfirmation();
		if(reponse == ButtonType.OK) {
			deleteCompany(companySelected);
			deleteAddress(companySelected.getCompanyAddressIdt());
			trtAffichage();
		}	
	}
	/**
	 * Description	: Methode gérant le double clic sur la liste, elle correspond à  l'action Modifier.
	 * @param  event	[MouseEvent]	: action sur la souris par l'utilisateur
	 */
	@Override
	@FXML public void evtOnMousePressedTbvDonnees(MouseEvent event) {
		// TODO Auto-generated method stub
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2 && btnModifier.isDisable() == false) {
			evtOnMouseClickedBtnModifier();
		}
	}
	/**
	 * Méthode permettant de recevoir un agent en paramètre de la fenêtre appelante
	 * @param agent	[Agent] : objet Agent correspondant à l'agent connecté.
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
		
		if (agent.getAgentType() != 1 && agent.getAgentType() != 3 && agent.getAgentType() != 4) {
			btnAjouter.setDisable(true);
			btnSupprimer.setDisable(true);
			btnModifier.setDisable(true);
		} else if (agent.getAgentType() == 3) {
			btnSupprimer.setDisable(true);
			//btnModifier.setDisable(false);
		} else if (agent.getAgentType() == 1 ){
			btnAjouter.setDisable(true);
			btnSupprimer.setDisable(true);
		} 
	}
	
	public void controlValiderClicked(boolean clicked) {
		if (clicked == true) trtAffichage();
	}
	
}
