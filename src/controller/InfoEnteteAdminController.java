package controller;

import static bdd.FenetresBdd.selectOneFenetre;
import static bdd.InfoEnteteBdd.deleteInfoEntete;
import static bdd.InfoEnteteBdd.insertInfoEntete;
import static bdd.InfoEnteteBdd.selectAllInfoEntete;
import static bdd.InfoEnteteBdd.selectNbreInfoDetail;
import static bdd.InfoEnteteBdd.selectOneInfoEntete;
import static bdd.InfoEnteteBdd.updateInfoEntete;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Fenetres;
import model.InfoEntete;
import model.LoaderFXML;
import model.TypeAgent;
import resources.Cstes;
import utilities.DialogBox;

public class InfoEnteteAdminController extends AdministrationManagementController {
	/** Declaration des variables **/
	ObservableList<InfoEntete> listeDonnees					= FXCollections.observableArrayList();
	InfoEntete infoEnteteSelected							= null;
	/** Listes contenant les informations des Spinners **/
	private ObservableList<Integer> 		listeDetailMax	= FXCollections.observableArrayList();	
	/** ValueFactory des différents Spinners **/
	private SpinnerValueFactory<Integer> 	valueFactoryDetailMax 	= null;
	/** Déclaration des contrôles de la fenêtre **/
	@FXML private TextField							txfInfoEnteteKey;
	@FXML private TextField							txfInfoEnteteDescription;
	@FXML private TextField							txfInfoEnteteCbx;
	@FXML private Label								lblInfoEnteteIdt;
	@FXML private Label								lblMessage;
	@FXML private Label								lblTotalLignesDetail;
	@FXML private Spinner<Integer> 					spiInfoEnteteNbreLigneMax;
	@FXML private TableView<InfoEntete>				tbvDonnees;
	@FXML private TableColumn<InfoEntete, String>	tbcInfoEnteteKey;
	@FXML private TableColumn<InfoEntete, String>	tbcInfoEnteteDescription;
	@FXML private ImageView							imvInfoEnteteDetail;

	@Override
	public void initialize() {
		lblTitre.setText("Gestion des entêtes d'info");
		lblMessage.setText("");
		trtAffichageDonnees();
		gestionBtn(true, false, false);
		imvInfoEnteteDetail.setDisable(true);
		
		for(int i=1; i<=100; i++)listeDetailMax.add(i);
		valueFactoryDetailMax = new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(listeDetailMax); 
		spiInfoEnteteNbreLigneMax.setValueFactory(valueFactoryDetailMax); 
	}
	/**
	 * Methode 	: trtAffichageDonnees
	 * Description 	: Methode gerant l'affichage et le reaffichage de la TableView
	 */
	private void  trtAffichageDonnees() {
		tbvDonnees.getItems().clear();
		listeDonnees.clear();
		
		listeDonnees = selectAllInfoEntete();
		tbcInfoEnteteKey.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getInfoEnteteKeyProperty());
		tbcInfoEnteteDescription.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getInfoEnteteDescriptionProperty());
		
		tbvDonnees.setItems(listeDonnees);
	}
	/**
	 * Méthode permettant de gérer les zones de saisie 
	 * @param infoEntete [InfoEntete]	: InfoEntete a afficher, si null les zones sont Raz
	 */
	private void trtAffichageZones(InfoEntete infoEntete) {
		if(infoEntete != null) {
			lblInfoEnteteIdt.setText(String.valueOf(infoEntete.getInfoEnteteIdt()));
			txfInfoEnteteKey.setText(infoEntete.getInfoEnteteKey());
			txfInfoEnteteDescription.setText(infoEntete.getInfoEnteteDescription());
			txfInfoEnteteCbx.setText(infoEntete.getInfoEnteteCbx());
			spiInfoEnteteNbreLigneMax.getValueFactory().setValue(infoEntete.getInfoEnteteNbreDetailMax()); 
			lblTotalLignesDetail.setText("Nombre de ligne(s) de détail " + selectNbreInfoDetail(infoEntete.getInfoEnteteIdt()));
		} else {
			lblInfoEnteteIdt.setText("");
			txfInfoEnteteKey.clear();
			txfInfoEnteteDescription.clear();
			txfInfoEnteteCbx.clear();
			lblTotalLignesDetail.setText("");
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
	private boolean trtControlesZones(/*String traitement*/) {
		if (isTextFieldEmpty(txfInfoEnteteKey) || isTextFieldEmpty(txfInfoEnteteDescription)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void evtOnMouseClickedBtnModifier() {
		retraitErreurs(txfInfoEnteteKey);
		retraitErreurs(txfInfoEnteteDescription);
		if (trtControlesZones() == true) {
			infoEnteteSelected.setInfoEnteteKey(txfInfoEnteteKey.getText());
			infoEnteteSelected.setInfoEnteteDescription(txfInfoEnteteDescription.getText());
			infoEnteteSelected.setInfoEnteteCbx(txfInfoEnteteCbx.getText());
			infoEnteteSelected.setInfoEnteteNbreDetailMax(spiInfoEnteteNbreLigneMax.getValue());
			updateInfoEntete(infoEnteteSelected);
			trtAffichageDonnees();
			txfInfoEnteteKey.clear();
			txfInfoEnteteDescription.clear();
			txfInfoEnteteCbx.clear();
			gestionBtn(true, false, false);
			imvInfoEnteteDetail.setDisable(true);
		} else {
			if (isTextFieldEmpty(txfInfoEnteteKey)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir la clé", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			} else if (isTextFieldEmpty(txfInfoEnteteDescription)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir la description", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			}
		}
	}

	@Override
	public void evtOnMouseClickedBtnAjouter() {
		retraitErreurs(txfInfoEnteteKey);
		retraitErreurs(txfInfoEnteteDescription);
		if (trtControlesZones() == true) {
			InfoEntete newInfoEntete = new InfoEntete(0, txfInfoEnteteKey.getText(), txfInfoEnteteDescription.getText(), txfInfoEnteteCbx.getText(), spiInfoEnteteNbreLigneMax.getValue());
			insertInfoEntete(newInfoEntete);
			trtAffichageDonnees();
			txfInfoEnteteKey.clear();
			txfInfoEnteteDescription.clear();
			txfInfoEnteteCbx.clear();
			gestionBtn(true, false, false);
			imvInfoEnteteDetail.setDisable(true);
		} else {
			if (isTextFieldEmpty(txfInfoEnteteKey)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir la clé", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			} else if (isTextFieldEmpty(txfInfoEnteteDescription)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir la description", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			}
		}
	}

	@Override
	public void evtOnMouseClickedBtnSupprimer() {
		retraitErreurs(txfInfoEnteteKey);
		retraitErreurs(txfInfoEnteteDescription);
		DialogBox dialogBox = new DialogBox("Supression de l'entête", "", "Voulez-vous vraiment supprimer l'entête " + infoEnteteSelected.getInfoEnteteKey(), AlertType.CONFIRMATION, ButtonType.CANCEL);
		ButtonType reponse = dialogBox.showDialogConfirmation();
		if(reponse == ButtonType.OK) {
			deleteInfoEntete(infoEnteteSelected);
			trtAffichageDonnees();
			lblInfoEnteteIdt.setText("Label");
			txfInfoEnteteKey.clear();
			txfInfoEnteteDescription.clear();
			txfInfoEnteteCbx.clear();
			gestionBtn(true, false, false);
			imvInfoEnteteDetail.setDisable(true);
		}	
	}

	@Override
	public void evtOnMousePressedTbvDonnees(MouseEvent event) {
		lblMessage.setText("");
		retraitErreurs(txfInfoEnteteKey);
		retraitErreurs(txfInfoEnteteDescription);
		if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
			infoEnteteSelected = tbvDonnees.getSelectionModel().getSelectedItem();

			if(infoEnteteSelected != null) {
				trtAffichageZones(infoEnteteSelected);
				
				if (selectNbreInfoDetail(infoEnteteSelected.getInfoEnteteIdt()) != 0) {
					gestionBtn(false, true, false);
					imvInfoEnteteDetail.setDisable(false);
					lblMessage.setText("L'entête ne peut pas être supprimé, il a des lignes détail !!");
				} else {
					gestionBtn(false, true, true);
					imvInfoEnteteDetail.setDisable(false);
				}
			}
		}
	}
	@FXML private void evtOnMouseClickedImvInfoEnteteDetail() {
		Fenetres fenetre	  = selectOneFenetre(Cstes.DETAILINFO);
		if(fenetre!=null) {
			LoaderFXML loaderFxml 					= new LoaderFXML(fenetre);
			Stage primaryStage 	  					= loaderFxml.createLoaderBorderPane();
			InfoDetailAdminController controler 	= loaderFxml.getLoader().getController();
			controler.setDialogStage(primaryStage);
			controler.setInfoEntete(infoEnteteSelected);
			primaryStage.showAndWait();
			int nbreDeLignesDetail = selectNbreInfoDetail(infoEnteteSelected.getInfoEnteteIdt());
			boolean affichage	= (nbreDeLignesDetail>0);
			lblTotalLignesDetail.setText("Nombre de ligne(s) de détail : " + nbreDeLignesDetail);
			lblMessage.setVisible(affichage);
			gestionBtn(true, false, affichage);
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
