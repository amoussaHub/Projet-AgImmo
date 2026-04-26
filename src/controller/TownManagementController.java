package controller;

import static bdd.FenetresBdd.selectOneFenetre;
import static bdd.TownBdd.deleteTown;
import static bdd.TownBdd.selectAllTown;
import static bdd.TownBdd.selectTownUsed;

import java.util.Map;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Fenetres;
import model.LoaderFXML;
import model.Town;
import resources.Cstes;
import utilities.DialogBox;

/**
 * Cette classe est la Classe controleur de la fenetre de management des villes
 * Elle herite de la classe GeneralManagementController
 * 
 * @author 	Alain CASARA
 * @version	1.0
 */
public class TownManagementController extends GeneralManagementController {
	/** Declaration des variables **/
	private ObservableList<Town> townList = FXCollections.observableArrayList();
	/** Declaration des controles de la fenetre **/
	@FXML private TreeTableView<Town> 			ttvVilles;
	@FXML private TreeTableColumn<Town, String> ttcVille;
	@FXML private TreeTableColumn<Town, String> ttcCodePostal;



	/**
	 * Methode 	: initialize
	 * Description 	: Methode lancee implicitement a l'affichage de la fenetre fxml.
	 * 				  Elle est automatiquement generee par heritage 
	 */
	@FXML
	public void initialize() {	
		/** Initialisation du titre **/
		lblTitre.setText("Gestion des villes");
		/** Remplissage de la treeTableView **/
		trtAffichageDonnees();
	}
	/**
	 * Methode 	: trtAffichageDonnees
	 * Description 	: Methode gerant l'affichage et le reaffichage de la TableView
	 */
	@SuppressWarnings("unused")
	private void  trtAffichageDonnees() {
		townList.clear();
		townList.addAll(selectAllTown());
		// Créer la racine (non visible)
		TreeItem<Town> rootItem = new TreeItem<>(new Town(0, "Villes", ""));
		rootItem.setExpanded(true);

		// Remplir le TreeTableView en fonction de l'ObservableList
		updateTree(rootItem);

		ttvVilles.setRoot(rootItem);
		ttvVilles.setShowRoot(false); // Masquer la racine "Villes"

		// Liaison des colonnes avec les propriétés de Town
		ttcVille.setCellValueFactory(param -> param.getValue().getValue().getTownNameProperty());
		ttcCodePostal.setCellValueFactory(param -> param.getValue().getValue().getTownPostCodeProperty());

		// Observer les changements et mettre à jour l'affichage
		townList.addListener((javafx.collections.ListChangeListener<Town>) change -> {
			updateTree(rootItem);
		});
	}
	// Fonction pour organiser les villes par lettre
	private void updateTree(TreeItem<Town> rootItem) {
		rootItem.getChildren().clear(); // Nettoyer l'ancien affichage

		// Map pour trier les villes par leur première lettre
		Map<Character, TreeItem<Town>> letterGroups = new TreeMap<>();

		for (Town town : townList) {
			char firstLetter = Character.toUpperCase(town.getTownName().charAt(0));

			// Si le groupe de lettre n'existe pas, le créer
			letterGroups.putIfAbsent(firstLetter, new TreeItem<>(new Town(-1, String.valueOf(firstLetter), "")));

			// Ajouter la ville sous la bonne lettre
			letterGroups.get(firstLetter).getChildren().add(new TreeItem<>(town));
		}

		// Ajouter les groupes triés à la racine
		rootItem.getChildren().addAll(letterGroups.values());
	}

	/**
	 * Methode 	: evtOnMouseClickedImvSelection
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur l'image [Selection]
	 */
	@Override
	public void evtOnMouseClickedImvSelection() {
		// TODO Auto-generated method stub
		trtAffichageDonnees();
	}
	/**
	 * Methode 	: evtOnMouseClickedBtnModifier
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur le bouton [Modifier]
	 */
	@Override
	public void evtOnMouseClickedBtnModifier() {
	}
	/**
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur le bouton [Ajouter]
	 */
	@Override
	public void evtOnMouseClickedBtnAjouter() {
	}
	/**
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur le bouton [Supprimer]
	 */
	@Override
	@FXML public void evtOnMouseClickedBtnSupprimer() {
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
