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
import bdd.AgentBdd;
import bdd.AgentEnAgenceBdd;
import bdd.CivilityBdd;
import bdd.TypeAgentBdd;
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
import javafx.stage.Stage;
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
	
	private Tooltip tooltipTelephone = new Tooltip("Le n° doit avoir le format suivant : XX-XX-XX-XX-XX");
	private Tooltip tooltipEmail = new Tooltip("Le mail doit avoir le format suivant : XXXXXXX@YYYY.ZZZ");
	private Tooltip tooltipPwd = new Tooltip("Le mot de passe doit contenir au moins : \n - un chiffre entre 0 et 9 \n - une lettre en minuscule \n - une lettre en majuscule \n - un caractère spécial \n - une longueur entre 8 et 20");
	
	@FXML private ImageView imvCartesVisite;
	@FXML private Label lblTitre;

	@FXML private Stage dialogStage; 
	@FXML private String messageErreur;


	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	/**
	 * Méthode lancée implicitement à la génération de la fenêtre fxml.
	 * Elle permet d'initialiser les valeurs statiques de la fenêtre
	 */
	@Override
	@FXML public void initialize() {
		/** Le code est n'est pas changé **/
		lblTitre.setText("Paramètres de modifications");


		listeCivility = selectAllCivility();
		cbxCivilite.setItems(listeCivility);

		listeTypeAgent = selectAllTypeAgent();
		cbxTypeAgent.setItems(listeTypeAgent);

		Image image 				= new Image(getClass().getResourceAsStream("/images/icones/information.jpg"));
		ImageView imageView 		= new ImageView(image);
		imageView.setFitWidth(20); 
		imageView.setFitHeight(20); 

		tooltipTelephone.setGraphic(imageView);
		tooltipEmail.setGraphic(imageView);
		tooltipPwd.setGraphic(imageView);

		txfAgentTelephone.setTooltip(tooltipTelephone);
		txfAgentMobile.setTooltip(tooltipTelephone);
		txfAgentEmail.setTooltip(tooltipEmail);
		pwfAgentPwd.setTooltip(tooltipPwd);


		/** Rajout d'un évènement sur le bouton btnAgentImage pour afficher l'explorateur Windows **/
		btnAgentImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				fileChooser.setTitle("Sélectionner une image de portrait");
				fileChooser.setInitialDirectory(new File("C:/Users/amoussady/eclipse-workspace/Projet-AgImmo/src/images"));

				File file = fileChooser.showOpenDialog(dialogStage);

				if (file != null) {
					LblAgentImage.setText(file.getName());
					lblAgentDossier.setText("Dans le dossier : " + file.getParent() + "\\");
				}
			}
		});

		/** Le login est généré automatiquement avec le format suivant :
		 *    première lettre du prénom en minuscule
		 *  + nom en minuscule. s'il y a des espaces dans le nom, ils sont supprimés.
		 * **/
		/** Rajout de listener pour gérer les changements sur le nom **/
		txfAgentNom.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
			}
		});
		/** Rajout de listener pour gérer les changements sur le prénom **/
		txfAgentPrenom.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
			}
		});

		txfAgentPrenom.textProperty().addListener((obs, oldText, newText) -> {
			updateLogin();
		});
		txfAgentNom.textProperty().addListener((obs, oldText, newText) -> {
			updateLogin();
		});
		/** Initialisation des combobox **/
		pwfAgentPwd.setDisable(true);
		pwfAgentPwdConfirme.setDisable(true);
		
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
	public void setAgent(Agent agent) {
		this.agent = agent;


		txfAgentNom.setText(agent.getPersonName());
		txfAgentPrenom.setText(agent.getPersonFirstName());
		txfAgentMobile.setText(agent.getPersonMobile());
		txfAgentTelephone.setText(agent.getPersonPhone());
		txfAgentEmail.setText(agent.getPersonEmail());
		cbxCivilite.getSelectionModel().select(agent.getCivility());

		for (TypeAgent t : listeTypeAgent) {
			if (t.getTypeAgentIdt() == agent.getAgentType()) {
				cbxTypeAgent.getSelectionModel().select(t);
				break;
			}
		}

		txfAgentLogin.setText(agent.getAgentLogin());
		//pwfAgentPwd.setText(agent.getAgentPwd());
		//pwfAgentPwdConfirme.setText("");
		LblAgentImage.setText(agent.getAgentImage());
		
		listeAgences = selectAllAgencesAgent(agent.getPersonIdt());
		
		tbcCompanyName.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getCompany().getCompanyNameProperty());
		tbcIsAttached.setCellValueFactory(CellDataFeatures -> CellDataFeatures.getValue().getAgentIsAttachedProperty());
		tbvDonnees.setItems(listeAgences);
		
		tbvDonnees.setEditable(true);
	}
	/**
	 * Méthode contrôlant si au moins une information de l'agent a été modifié
	 * @return	[boolean]	: valeur de retour
	 */
	private boolean traitementControleModification() {
		/** Initialisation des variables **/
		boolean	objetModifier	= false;
		/** Traitements de contrôles **/
		int     modification	= 0;
		modification += (cbxCivilite.getSelectionModel().getSelectedItem().getCivilityIdt()!=agent.getPersonCivility())? 1 : 0;
		modification += (!txfAgentNom.getText().equals(agent.getPersonName()))? 1:0;
		modification += (!txfAgentPrenom.getText().equals(agent.getPersonFirstName()))? 1:0;
		modification += (!txfAgentMobile.getText().equals(agent.getPersonMobile()))? 1:0;
		modification += (!txfAgentTelephone.getText().equals(agent.getPersonPhone()))? 1:0;
		modification += (!txfAgentEmail.getText().equals(agent.getPersonEmail()))? 1:0;
		modification += (cbxTypeAgent.getSelectionModel().getSelectedItem().getTypeAgentIdt()!=agent.getAgentType())? 1:0;
		modification += (!txfAgentLogin.getText().equals(agent.getAgentLogin()))? 1:0;
		modification += (!pwfAgentPwd.getText().equals(agent.getAgentPwd()))? 1:0;
		modification += (!LblAgentImage.getText().equals(agent.getAgentImage()))? 1:0;
		objetModifier = (modification>0);
		return objetModifier;
	}
	/**
	 * Méthode permettant à la fenêtre appelante de savoir si le bouton Valider à été sélectionné.
	 * @return	[boolean]
	 */
	public boolean isValiderClicked() {
		return validerClicked;
	}    
	/**
	 * Cette méthode permet de générer les cartes de visite d'un agent
	 */
	@FXML private void evtOnMouseClickedImvCartesVisite() {
		/** Initialisation des variables **/
		boolean insertionNom 		= false;
		boolean insertionPoste 		= false;
		boolean insertionPortable 	= false;
		boolean insertionEmail 		= false;
		String  path				= "";
		/** Recherche du dossier contenant les modèles word et du dossier des documents **/
		InfoDetail dossierModeleWord 	= selectOneInfoDetailDescription(Cstes.DOSSIERS,Cstes.DOSSIERMODELEWORD);
		InfoDetail dossierDocuments 	= selectOneInfoDetailDescription(Cstes.DOSSIERS,Cstes.DOSSIERDOCUMENTS);	
		if(dossierModeleWord!=null && dossierDocuments!=null) {
			XWPFDocument doc = null;
			try {
				path								= dossierModeleWord.getInfoDetailLbl();
				if(!path.endsWith("/")) path	  	= path + "/";
				doc 								= new XWPFDocument(OPCPackage.open(path + "CarteDeVisite.docx"));
				XWPFWordExtractor we 				= new XWPFWordExtractor(doc);
				List<XWPFTable> tables 				= doc.getTables();
				for (XWPFTable table: tables){
					for (int rowIndex = 0; rowIndex < table.getNumberOfRows(); rowIndex++)
					{
						insertionNom		= (rowIndex==2 || ((rowIndex-2)%7==0));
						insertionPoste		= (rowIndex==3 || ((rowIndex-3)%7==0));
						insertionPortable	= (rowIndex==4 || ((rowIndex-4)%7==0));
						insertionEmail		= (rowIndex==5 || ((rowIndex-5)%7==0));
						XWPFTableRow row 	= table.getRow(rowIndex);
						int numberOfCell 	= row.getTableCells().size();
						for (int colIndex = 0; colIndex < numberOfCell; colIndex++){
							XWPFTableCell cell 		= row.getCell(colIndex);
							if(insertionNom) {
								cell.setText(agent.getPersonName() + " " + agent.getPersonFirstName());
							} else if(insertionPoste) {
								cell.setText(cbxTypeAgent.getSelectionModel().getSelectedItem().getTypeAgentLbl());
							} else if(insertionPortable) {
								cell.setText(agent.getPersonMobile());
							} else if(insertionEmail){
								cell.setText(agent.getPersonEmail());
							}
						}
					}
				}
				try {
					path 					  		  = dossierDocuments.getInfoDetailLbl() + Cstes.CARTESVISITES;
					if(!path.endsWith("/")) path	  = path + "/";
					FileOutputStream fileOutputStream = new FileOutputStream(path+ "CarteDeVisite_" + agent.getPersonName() + "_" + agent.getPersonFirstName() + ".docx"); 
					doc.write(fileOutputStream);
					fileOutputStream.close();
					we.close();
					doc.close();
					/** Dialog Box informant de la fin de l'impression **/
					DialogBox dialogBox = new DialogBox("Impression des cartes de visites", "Impression terminée", "", AlertType.INFORMATION, null);
					dialogBox.showDialogError();
				} catch (IOException e) {
					System.out.println("Erreur lors de la sauvegarde du fichier Word - génénration des cartes de visite : " + e);
				}
			} catch (InvalidFormatException | IOException e) {
				System.out.println("Erreur dans la création du fichier Word - génénration des cartes de visite : " + e);
			}
		}
	}
	
	private void updateLogin() {
		String prenom = txfAgentPrenom.getText();
		String nom = txfAgentNom.getText();

		if (!prenom.isEmpty() && !nom.isEmpty()) {
			String login = prenom.substring(0, 1).toLowerCase() + nom.toLowerCase();
			txfAgentLogin.setText(login);
		} else {
			txfAgentLogin.setText(""); 
		}
	}

	/**
	 * Méthode effectuant les différents contrôles de saisie sur les zones obligatoires et sur les formats de saisie
	 * S'il n'y a pas d'erreur, la méthode met à jour l'agent
	 * Cette méthode est créée par héritage
	 */
	@Override
	@FXML public void evtOnMouseClickedBtnValider() {
		/** Initialisation de la variable qui contiendra les messgaes d'erreurs **/
		System.out.println(listeAgences);

		messageErreur = "";
		/** Contrôle des zones obligatoires et des formats **/

		if(txfAgentNom.getText().isEmpty()) messageErreur += "Le nom est obligatoire.\n";
		if(txfAgentPrenom.getText().isEmpty()) messageErreur += "Le prénom est obligatoire.\n";
		String nom = txfAgentNom.getText();




		if(txfAgentMobile.getText().isEmpty()) messageErreur += "Le mobile est obligatoire.\n";
		if(txfAgentTelephone.getText().isEmpty()) messageErreur += "Le téléphone est obligatoire.\n";
		if(txfAgentEmail.getText().isEmpty()) messageErreur += "L'email est obligatoire.\n";
		if(pwfAgentPwd.getText().isEmpty()) messageErreur += "Le mot de passe est obligatoire.\n";
		if(LblAgentImage.getText().isEmpty()) messageErreur += "L'image est obligatoire.\n";

		String regexTel = "\\d{2}-\\d{2}-\\d{2}-\\d{2}-\\d{2}";
		if(!txfAgentMobile.getText().isEmpty() && !txfAgentMobile.getText().matches(regexTel))
			messageErreur += "Le mobile doit être au format XX-XX-XX-XX-XX.\n";

		if(!txfAgentTelephone.getText().isEmpty() && !txfAgentTelephone.getText().matches(regexTel))
			messageErreur += "Le téléphone doit être au format XX-XX-XX-XX-XX.\n";

		String regexEmail = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$";
		if(!txfAgentEmail.getText().isEmpty() && !txfAgentEmail.getText().matches(regexEmail))
			messageErreur += "Email invalide.\n";

		String pwd = pwfAgentPwd.getText();
		String pwdConf = pwfAgentPwdConfirme.getText();
		String ancienPwd = agent.getAgentPwd();
		String regexPwd = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,20}$";
		String pwdHache = null;

		// Si le mot de passe a été modifié
		if (chkUpdatePwd.isSelected()) {
			if (!pwd.equals(ancienPwd)) {
				if (!pwd.matches(regexPwd)) {
					messageErreur += "Mot de passe invalide (1 maj, 1 min, 1 chiffre, 1 spécial, 8-20 caractères).\n";
				} else if (!pwd.equals(pwdConf)) { 
					messageErreur += "Les mots de passe ne correspondent pas.\n";
				} else {
					pwdHache = BCrypt.withDefaults().hashToString(12,pwd.toCharArray());
				}
			}
		}
		
		Boolean verifAttached = false;
		for (AgentEnAgence agentEnAgence : listeAgences) {
			if (agentEnAgence.getAgentIsAttached()) {
				verifAttached = true;
				break;
			}
		}
		if (!verifAttached) messageErreur += "L'agent doit être attaché à une agence";
		


		/** S'il y a une erreur, on l'affiche dans la textArea **/
		if(!messageErreur.isEmpty()) {
			DialogBox dialog = new DialogBox("Erreur de saisie", messageErreur, "", AlertType.ERROR, null);
			dialog.showDialogError();
		} else {
			/** Contrôle s'il y a eu une modification pour mémorisation , dans le cas contraire on ne fait rien **/
			if(traitementControleModification()) {
				/** Modification de l'agent **/
				// Formatage avant stockage
				String nomStockage = txfAgentNom.getText().toUpperCase();

				String prenomTemp = txfAgentPrenom.getText();
				String prenomStockage = "";
				if (!prenomTemp.isEmpty()) {
					prenomStockage = prenomTemp.substring(0,1).toUpperCase() + prenomTemp.substring(1).toLowerCase();
				}

				// Mise à jour de l'objet Agent avec les valeurs formatées
				agent.setPersonName(nomStockage);
				agent.setPersonFirstName(prenomStockage);
				agent.setPersonMobile(txfAgentMobile.getText());
				agent.setPersonPhone(txfAgentTelephone.getText());
				agent.setPersonEmail(txfAgentEmail.getText());
				agent.setCivility(cbxCivilite.getSelectionModel().getSelectedItem());
				agent.setTypeAgent(cbxTypeAgent.getSelectionModel().getSelectedItem());
				agent.setAgentPwd(pwdHache);
				agent.setAgentImage(LblAgentImage.getText());
				
				AgentBdd.updateAgent(agent);
				AgentEnAgenceBdd.deleteAgentEnAgence(agent.getPersonIdt());
				for (AgentEnAgence agentEnAgence : listeAgences) {
					if (agentEnAgence.getAgentIsAttached()) {
						AgentEnAgenceBdd.insertAgentEnAgence(agentEnAgence);
					}
				}
				

				validerClicked = true;
				dialogStage.close();
			}
			/** Sortie de la fenêtre **/
			dialogStage.close();
		}
	}
	/**
	 * Méthode permettant de gérer la modification d'un mot de passe
	 */
	@FXML private void evtOnActionChkUpdatePwd() {
		if(chkUpdatePwd.isSelected()){
			pwfAgentPwd.setDisable(false);
			pwfAgentPwdConfirme.setDisable(false);
		} else {
			pwfAgentPwd.setDisable(true);
			pwfAgentPwdConfirme.setDisable(true);
		}
	}
	
	@FXML public void evtOnMouseClickedBtnAnnuler() {
		dialogStage.close();
	}
}
