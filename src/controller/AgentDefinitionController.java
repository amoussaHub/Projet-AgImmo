package controller;

import static bdd.AgentEnAgenceBdd.deleteAgentEnAgence;
import static bdd.AgentEnAgenceBdd.insertAgentEnAgence;
import static bdd.AgentEnAgenceBdd.selectAllAgencesAgent;
import static bdd.AgentBdd.updateAgent;
import static bdd.CivilityBdd.selectAllCivility;
import static bdd.InfoDetailBdd.selectOneInfoDetailDescription;
import static bdd.TypeAgentBdd.selectAllTypeAgent;
import static utilities.UtilitiesControls.isEmailAdress;
import static utilities.UtilitiesControls.isTextFieldEmpty;
import static utilities.UtilitiesControls.validatePhoneNumber;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import at.favre.lib.crypto.bcrypt.BCrypt;
import interfaces.GestionCbxInfos;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import model.Agent;
import model.AgentEnAgence;
import model.Civility;
import model.InfoDetail;
import model.TypeAgent;
import resources.Cstes;
import utilities.DialogBox;

/** ***********************************************************************************************
 * CLASSE : AgentDefinitionController
 * ************************************************************************************************
 * Cette classe est la classe de lancement de l'application
 * Elle hérite de la Classe Application qui est la classe Abstraite à implémenter pour une application 
 * JavaFX..  
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 * @see			model.Agent
 */
public class AgentDefinitionController extends GeneralDefinitionController implements GestionCbxInfos{
	/** *************************************************************
	 * Déclaration des variables
	 * **************************************************************/
	final FileChooser fileChooser 						= new FileChooser();	/** Objet de sélection d'un dossier **/
	Agent agent 			  	  						= null;
	private ObservableList<AgentEnAgence> listeAgences	= FXCollections.observableArrayList(); 
	private ObservableList<TypeAgent> listeTypeAgent	= FXCollections.observableArrayList(); 
	private ObservableList<Civility>  listeCivility		= FXCollections.observableArrayList(); 
	private boolean validerClicked 						= false;
	InfoDetail dossierPortraits							= null;
	/** *************************************************************
	 * Déclaration des contrôles du fichier fxml
	 * **************************************************************/
	@FXML private ComboBox<Civility> 		cbxCivilite;
	@FXML private ComboBox<TypeAgent>		cbxTypeAgent;
	@FXML private TextField					txfAgentNom;
	@FXML private TextField					txfAgentPrenom;
	@FXML private TextField					txfAgentMobile;
	@FXML private TextField					txfAgentTelephone;
	@FXML private TextField					txfAgentEmail;
	@FXML private TextField					txfAgentLogin;
	@FXML private CheckBox					chkUpdatePwd;
	@FXML private PasswordField				pwfAgentPwd;
	@FXML private PasswordField				pwfAgentPwdConfirme;
	@FXML private Label						LblAgentImage;
	@FXML private Label						lblAgentDossier;
	@FXML private Button					btnAgentImage;
	@FXML private TableView<AgentEnAgence>  			tbvDonnees;
	@FXML private TableColumn<AgentEnAgence, String> 	tbcCompanyName;
	@FXML private TableColumn<AgentEnAgence, Boolean> 	tbcIsAttached;
	/**
	 * Méthode lancée implicitement à la génération de la fenêtre fxml.
	 * Elle permet d'initialiser les valeurs statiques de la fenêtre
	 */
	@Override
	@FXML public void initialize() {
		/** Le code est n'est pas changé **/

		/** Initialisation de la tableView **/
		tbcCompanyName.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getCompany().getCompanyNameProperty());
		tbcIsAttached.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getAgentIsAttachedProperty());
		tbcIsAttached.setCellValueFactory(new Callback<CellDataFeatures<AgentEnAgence, Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<AgentEnAgence, Boolean> param) {
				AgentEnAgence agentEnAgence 		= param.getValue();
				SimpleBooleanProperty isAttached 	= new SimpleBooleanProperty(agentEnAgence.getAgentIsAttached());

				isAttached.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,Boolean newValue) {
						agentEnAgence.setAgentIsAttached(newValue);
					}
				});
				return isAttached;
			}
		});
		tbcIsAttached.setCellFactory(new Callback<TableColumn<AgentEnAgence, Boolean>, //
				TableCell<AgentEnAgence, Boolean>>() {
			@Override
			public TableCell<AgentEnAgence, Boolean> call(TableColumn<AgentEnAgence, Boolean> p) {
				CheckBoxTableCell<AgentEnAgence, Boolean> cell = new CheckBoxTableCell<AgentEnAgence, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
	}
	/**
	 * Méthode permettant de recevoir un agent en paramètre de la fenêtre appelante
	 * @param agent	[Agent] : objet Agent correspondant à l'agent connecté.
	 */
	public void setAgent(Agent agent) {}
	/**
	 * Méthode contrôlant si au moins une information de l'agent a été modifié
	 * @return	[boolean]	: valeur de retour
	 */
	private boolean traitementControleModification() {}
	/**
	 * Méthode permettant à la fenêtre appelante de savoir si le bouton Valider à été sélectionné.
	 * @return	[boolean]
	 */
	public boolean isValiderClicked() {}    
	/**
	 * Cette méthode permet de générer les cartes de visite d'un agent
	 */
	@FXML private void evtOnMouseClickedImvCartesVisite() {}

	/**
	 * Méthode effectuant les différents contrôles de saisie sur les zones obligatoires et sur les formats de saisie
	 * S'il n'y a pas d'erreur, la méthode met à jour l'agent
	 * Cette méthode est créée par héritage
	 */
	@Override
	@FXML public void evtOnMouseClickedBtnValider() {}
	/**
	 * Méthode permettant de gérer la modification d'un mot de passe
	 */
	@FXML private void evtOnActionChkUpdatePwd() {}
}
