package controller;

import static bdd.AddressBdd.deleteAddress;
import static bdd.BankDetailBdd.deleteBankDetail;
import static bdd.FenetresBdd.selectOneFenetre;
import static bdd.LandlordBdd.deleteLandlord;
import static bdd.LandlordBdd.selectAllLandlord;
import static bdd.LandlordBdd.selectAllLandlordWithSelection;

import bdd.LandlordBdd;
import bdd.TownBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Fenetres;
import model.Landlord;
import model.LoaderFXML;
import resources.Cstes;
import utilities.DialogBox;

/**
 * Cette classe est la Classe controleur de la fenetre de management des proprietaires
 * Elle herite de la classe GeneralManagementController
 * 
 * @author 	Alain CASARA
 * @version	1.0
 */
public class LandlordManagementController extends GeneralManagementController {
	/** Declaration des variables **/
	ObservableList<Landlord> listeDonnees	= FXCollections.observableArrayList();
	/** Declaration des controles de la fenetre **/
	@FXML private TableView<Landlord>			tbvDonnees;
	@FXML private TableColumn<Landlord, String>	tbcLandlordCivility;
	@FXML private TableColumn<Landlord, String> tbcLandlordNom;
	@FXML private TableColumn<Landlord, String> tbcLandlordPrenom;
	@FXML private TableColumn<Landlord, String> tbcLandlordAdresse;
	@FXML private TableColumn<Landlord, String> tbcLandlordVille;	

	/**
	 * Methode 	: initialize
	 * Description 	: Methode lancee implicitement a l'affichage de la fenetre fxml.
	 * 				  Elle est automatiquement generee par heritage 
	 */
	@FXML
	public void initialize() {	
		/** Initialisation du titre **/
		lblTitre.setText("Gestion des proprietaires");
		/** Declaration des colonnes de la TableView avec les attributs de la classe Landlord **/
		/** Remplissage de la tableView **/
		trtAffichageDonnees();
		
		if (listeDonnees.isEmpty()) {
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
		
		if (listeDonnees.isEmpty()) listeDonnees = selectAllLandlord();
		System.out.println(listeDonnees);
		tbcLandlordCivility.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getCivility().getCivilityLblProperty());
		tbcLandlordNom.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getPersonNameProperty());
		tbcLandlordPrenom.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getPersonFirstNameProperty());
		tbcLandlordAdresse.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getAddress().getAdressCompleteProperty());
		tbcLandlordVille.setCellValueFactory(CellDataFeatures -> TownBdd.selectOneTown(CellDataFeatures.getValue().getAddress().getAddressTownIdt()).getTownCompleteProperty());
		
		tbvDonnees.setItems(listeDonnees);
	}
	/**
	 * Methode 	: evtOnMouseClickedImvSelection
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur l'image [Selection]
	 */
	@Override
	public void evtOnMouseClickedImvSelection() {
		// TODO Auto-generated method stub
		listeDonnees = LandlordBdd.selectAllLandlordWithSelection(txfSelection.getText());
		trtAffichageDonnees();
	}
	/**
	 * Methode 	: evtOnMouseClickedBtnModifier
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur le bouton [Modifier]
	 */
	@Override
	public void evtOnMouseClickedBtnModifier() {
		// TODO Auto-generated method stub
	}
	/**
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur le bouton [Ajouter]
	 */
	@Override
	public void evtOnMouseClickedBtnAjouter() {
		// TODO Auto-generated method stub
	}
	/**
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur le bouton [Supprimer]
	 */
	@Override
	@FXML public void evtOnMouseClickedBtnSupprimer() {
		// TODO Auto-generated method stub
	}
	/**
	 * Description	: Methode gérant le double clic sur la liste, elle correspond à l'action Modifier.
	 * @param  event	[MouseEvent]	: action sur la souris par l'utilisateur
	 */
	@Override
	@FXML public void evtOnMousePressedTbvDonnees(MouseEvent event) {
		// TODO Auto-generated method stub
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			evtOnMouseClickedBtnModifier();
		}
	}
}
