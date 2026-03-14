package controller;

import static bdd.InfoDetailBdd.deleteInfoDetail;
import static bdd.InfoDetailBdd.insertInfoDetail;
import static bdd.InfoDetailBdd.selectAllInfoDetail;
import static bdd.InfoDetailBdd.updateInfoDetail;
import static bdd.InfoEnteteBdd.deleteInfoEntete;
import static bdd.InfoEnteteBdd.insertInfoEntete;
import static bdd.InfoEnteteBdd.selectAllInfoEntete;
import static bdd.InfoEnteteBdd.selectNbreInfoDetail;
import static bdd.InfoEnteteBdd.selectOneInfoEntete;
import static bdd.InfoEnteteBdd.updateInfoEntete;
import static utilities.UtilitiesControls.isTextFieldEmpty;
import static utilities.UtilitiesControls.isTextFieldNumeric;
import static utilities.UtilitiesControls.isTextFieldNumericDouble;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.InfoDetail;
import model.InfoEntete;
import resources.Cstes;
import utilities.DialogBox;

public class InfoDetailAdminController extends AdministrationManagementController {
	/** Declaration des variables **/
	ObservableList<InfoDetail> listeDonnees						= FXCollections.observableArrayList();
	InfoDetail infoDetailSelected								= null;
	InfoEntete infoEntete										= null;
	/** Listes contenant les informations des Spinners **/
	private ObservableList<Integer> 		listeOrdreTri		= FXCollections.observableArrayList();	
	/** ValueFactory des différents Spinners **/
	private SpinnerValueFactory<Integer> valueFactoryOrdreTri 	= null;
	/** Déclaration des contrôles de la fenêtre **/
	@FXML private TextField							txfInfoDetailDescription;
	@FXML private TextField							txfInfoDetailLbl;
	@FXML private TextField							txfInfoDetailLbc;
	@FXML private TextField							txfInfoDetailValueInt;
	@FXML private TextField							txfInfoDetailValueDouble;
	@FXML private Spinner<Integer> 					spiInfoDetailOrdreTri;	
	@FXML private Label								lblInfoDetailIdentifiant;
	@FXML private Label								lblInfoDetailEnteteIdt;
	@FXML private Label								lblInfoDetailKey;
	@FXML private CheckBox							chkInfoDetailDefaultValue;
	@FXML private TableView<InfoDetail>				tbvDonnees;
	@FXML private TableColumn<InfoDetail, String>	tbcInfoDetailDescription;
	@FXML private Button							btnAnnuler;


	@Override
	public void initialize() {
		lblTitre.setText(infoEntete.getInfoEnteteKey());
		trtAffichageDonnees();
	}
	/**
	 * Methode 	: trtAffichageDonnees
	 * Description 	: Methode gerant l'affichage et le reaffichage de la TableView
	 */
	private void  trtAffichageDonnees() {
		tbvDonnees.getItems().clear();
		listeDonnees.clear();
		
		listeDonnees = selectAllInfoDetail(infoEntete.getInfoEnteteKey());
		tbcInfoDetailDescription.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getInfoDetailDescriptionProperty());
		
		tbvDonnees.setItems(listeDonnees);
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
	 * Méthode permettant de gérer les zones de saisie 
	 * @param infoEntete [InfoEntete]	: InfoEntete a afficher, si null les zones sont Raz
	 */
	private void trtAffichageZones(InfoDetail infoDetail) {
		if(infoEntete != null) {
			lblInfoDetailIdentifiant.setText(String.valueOf(infoDetail.getInfoDetailIdt()));
			lblInfoDetailEnteteIdt.setText(String.valueOf(infoEntete.getInfoEnteteIdt()));
			lblInfoDetailKey.setText(String.valueOf(infoDetail.getInfoDetailKeyIdt()));
			txfInfoDetailDescription.setText(infoDetail.getInfoDetailDescription());
			txfInfoDetailLbl.setText(infoDetail.getInfoDetailLbl());
			txfInfoDetailLbc.setText(infoDetail.getInfoDetailLbc());
			txfInfoDetailValueInt.setText(String.valueOf(infoDetail.getInfoDetailValueInt()));
			txfInfoDetailValueDouble.setText(String.valueOf(infoDetail.getInfoDetailValueDouble()));
			spiInfoDetailOrdreTri.getValueFactory().setValue(infoDetail.getInfoDetailTri());
			chkInfoDetailDefaultValue.setSelected(infoDetail.isInfoDetailDefault());
		} else {
			lblInfoDetailIdentifiant.setText("");
			lblInfoDetailEnteteIdt.setText("");
			lblInfoDetailKey.setText("");
			txfInfoDetailDescription.clear();
			txfInfoDetailLbl.clear();
			txfInfoDetailLbc.clear();
			txfInfoDetailValueInt.clear();
			txfInfoDetailValueDouble.clear();
			chkInfoDetailDefaultValue.setSelected(false);
		}
	}	
	/**
	 * Méthodes permettant de contrôler les différentes zones de saisie
	 * @return	[boolean]	: indicateur si présence d'erreurs dans les zones
	 */
	private boolean trtControlesZones() {
		if (isTextFieldEmpty(txfInfoDetailDescription) || isTextFieldEmpty(txfInfoDetailLbl) || !isTextFieldNumeric(txfInfoDetailValueInt) || !isTextFieldNumericDouble(txfInfoDetailValueDouble))  {
			return false;
		} else {
			return true;
		}
	}
	@Override
	public void evtOnMouseClickedBtnModifier() {
		retraitErreurs(txfInfoDetailDescription);
		retraitErreurs(txfInfoDetailLbl);
		retraitErreurs(txfInfoDetailValueInt);
		retraitErreurs(txfInfoDetailValueDouble);
		if (trtControlesZones() == true) {
			infoDetailSelected.setInfoDetailDescription(txfInfoDetailDescription.getText());
			infoDetailSelected.setInfoDetailLbl(txfInfoDetailLbl.getText());
			infoDetailSelected.setInfoDetailLbc(txfInfoDetailLbc.getText());
			infoDetailSelected.setInfoDetailValueInt(Integer.valueOf(txfInfoDetailValueInt.getText()));
			infoDetailSelected.setInfoDetailValueDouble(Double.valueOf(txfInfoDetailValueDouble.getText()));
			infoDetailSelected.setInfoDetailTri(spiInfoDetailOrdreTri.getValue());
			updateInfoDetail(infoDetailSelected);
			trtAffichageDonnees();
			txfInfoDetailDescription.clear();
			txfInfoDetailLbl.clear();
			txfInfoDetailValueInt.clear();
			txfInfoDetailValueDouble.clear();
			spiInfoDetailOrdreTri.getValueFactory().setValue(0);
			chkInfoDetailDefaultValue.setSelected(false);
			gestionBtn(true, false, false);
		} else {
			if (isTextFieldEmpty(txfInfoDetailDescription)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir la description", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			} else if (isTextFieldEmpty(txfInfoDetailLbl)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir le libellé long", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			} else if (!isTextFieldNumeric(txfInfoDetailValueInt)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir une valeur de type numérique dans le champs valeur (int)", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			} else if (!isTextFieldNumericDouble(txfInfoDetailValueDouble)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir une valeur de type double dans le champs valeur (double)", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			}
		}
	}
	@Override
	public void evtOnMouseClickedBtnAjouter() {
		retraitErreurs(txfInfoDetailDescription);
		retraitErreurs(txfInfoDetailLbl);
		retraitErreurs(txfInfoDetailValueInt);
		retraitErreurs(txfInfoDetailValueDouble);
		if (trtControlesZones() == true) {
			InfoDetail newInfoDetail = new InfoDetail(0, infoEntete.getInfoEnteteIdt(), 0, txfInfoDetailDescription.getText(), txfInfoDetailLbl.getText(), txfInfoDetailLbc.getText(), Integer.valueOf(txfInfoDetailValueInt.getText()),
									   Double.valueOf(txfInfoDetailValueDouble.getText()), spiInfoDetailOrdreTri.getValue(), chkInfoDetailDefaultValue.isSelected(), infoEntete);
			insertInfoDetail(newInfoDetail);
			trtAffichageDonnees();
			txfInfoDetailDescription.clear();
			txfInfoDetailLbl.clear();
			txfInfoDetailValueInt.clear();
			txfInfoDetailValueDouble.clear();
			spiInfoDetailOrdreTri.getValueFactory().setValue(0);
			chkInfoDetailDefaultValue.setSelected(false);
			gestionBtn(true, false, false);
		} else {
			if (isTextFieldEmpty(txfInfoDetailDescription)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir la description", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			} else if (isTextFieldEmpty(txfInfoDetailLbl)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir le libellé long", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			} else if (!isTextFieldNumeric(txfInfoDetailValueInt)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir une valeur de type numérique dans le champs valeur (int)", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			} else if (!isTextFieldNumericDouble(txfInfoDetailValueDouble)) {
				DialogBox dialogBox = new DialogBox("Erreur de saisie", "", "Veuillez saisir une valeur de type double dans le champs valeur (double)", AlertType.CONFIRMATION, null);
				ButtonType reponse = dialogBox.showDialogConfirmation();
			}
		}
	}
	@Override
	public void evtOnMouseClickedBtnSupprimer() {
		retraitErreurs(txfInfoDetailDescription);
		retraitErreurs(txfInfoDetailLbl);
		retraitErreurs(txfInfoDetailValueInt);
		retraitErreurs(txfInfoDetailValueDouble);
		DialogBox dialogBox = new DialogBox("Supression du détail", "", "Voulez-vous vraiment supprimer le détail " + infoDetailSelected.getInfoDetailLbl(), AlertType.CONFIRMATION, ButtonType.CANCEL);
		ButtonType reponse = dialogBox.showDialogConfirmation();
		if(reponse == ButtonType.OK) {
			deleteInfoDetail(infoDetailSelected);
			trtAffichageDonnees();
			lblInfoDetailIdentifiant.setText("Label");
			lblInfoDetailEnteteIdt.setText("Label");
			lblInfoDetailKey.setText("Label");
			txfInfoDetailDescription.clear();
			txfInfoDetailLbl.clear();
			txfInfoDetailValueInt.clear();
			txfInfoDetailValueDouble.clear();
			spiInfoDetailOrdreTri.getValueFactory().setValue(0);
			chkInfoDetailDefaultValue.setSelected(false);
			gestionBtn(true, false, false);		}
	}
	@Override
	public void evtOnMousePressedTbvDonnees(MouseEvent event) {
		retraitErreurs(txfInfoDetailDescription);
		retraitErreurs(txfInfoDetailLbl);
		retraitErreurs(txfInfoDetailValueInt);
		retraitErreurs(txfInfoDetailValueDouble);
		if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
			infoDetailSelected = tbvDonnees.getSelectionModel().getSelectedItem();

			if(infoDetailSelected != null) {
				gestionBtn(false, true, true);
				trtAffichageZones(infoDetailSelected);
			}
		}
	}
	@FXML private void evtOnMouseClickedBtnAnnuler() {
		dialogStage.close();
	}
	/**
	 * Méthode recevant l'entete à modifier
	 * @param infoEntete	[InfoEntete]
	 */
	public void setInfoEntete(InfoEntete infoEntete) {
		this.infoEntete = infoEntete;
		
	}
	/**
	 * Méthode permettant de mettre à jour le contrôle Spinner selon l'action ( insérer / supprimer )
	 */
	private void trtSpinner() {
		
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
