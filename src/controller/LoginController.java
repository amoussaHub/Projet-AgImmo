package controller;

import static bdd.AgentBdd.selectAgentByLogin;
import static bdd.FenetresBdd.selectOneFenetre;
import static utilities.UtilitiesFermeture.fenetreFermeture;
import static bdd.InfoDetailBdd.selectOneInfoDetailDescription;
import static bdd.ConnexionsBdd.selectNbreConnexions;
import static bdd.ConnexionsBdd.selectLastConnexion;
import static bdd.ConnexionsBdd.selectLastConnexion;
import static bdd.ConnexionsBdd.insertConnexions;
import static bdd.ConnexionsBdd.deleteConnexions;
import static bdd.SessionsBdd.insertSessions;
import static batch.TraitementsBatch.traitementChiffrementDonneesPersonnelles;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;


import bdd.AgentBdd;
import bdd.ConnexionsBdd;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Agent;
import model.Connexions;
import model.Fenetres;
import model.InfoDetail;
import model.LoaderFXML;
import model.Sessions;
import resources.Cstes;
import utilities.DialogBox;
import at.favre.lib.crypto.bcrypt.BCrypt;

/** ***********************************************************************************************
 * CLASSE : LoginController
 * ************************************************************************************************
 * Cette classe est le controleur de la fenêtre de Login
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 */
public class LoginController {
	/** *************************************************************
	 * Déclaration des contrôles du fichier fxml
	 * **************************************************************/
	private Stage 				dialogStage;
	private Agent 				agent;

	@FXML private Label 		lblErreur;
	@FXML private Label 		lblDepassement;
	@FXML private TextField 	txfLogin;
	@FXML private PasswordField pwfPwd;
	@FXML private Button 		btnLogin;
	@FXML private Button 		btnQuitter;

	InfoDetail nbreErreursConnexions = selectOneInfoDetailDescription(Cstes.DEFAULT, Cstes.NBRERREURSLOGIN);
	InfoDetail dureeBlqLogin = selectOneInfoDetailDescription(Cstes.DEFAULT, Cstes.DUREEBLQLOGIN);

	String uuid = null;
	int nbrConnexions = 0;
	Connexions lastConnexion = null;
	int countdownStarter = 60 * dureeBlqLogin.getInfoDetailValueInt();

	/**
	 * Méthode lancée implicitement à la génération de la fenêtre fxml.
	 * Elle permet d'initialiser les valeurs statiques de la fenêtre
	 */
	@FXML public void initialize() {	
		lblErreur.setVisible(false);
		//lblDepassement.setVisible(false);

		txfLogin.textProperty().addListener((obs, oldValue, newValue) -> lblErreur.setVisible(false));
		pwfPwd.textProperty().addListener((obs, oldValue, newValue) -> lblErreur.setVisible(false));

		traitementChiffrementDonneesPersonnelles();
	}
	/**
	 * Méthode permettant de récupérer le stage initialisé par la fenêtre appelante 
	 * Permet de fermer la fenêtre en cours
	 * @param dialogStage	[Stage]	: Stage   
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * Cette méthode permet de gérer la touche [Entrée] sur le fenêtre.
	 * @param 	keyEvent	[KeyEvent] : Evènement clique sur une touche du clavier
	 */
	@FXML public void evtOnKeyPressedBtnLogin(KeyEvent keyEvent){
		if(keyEvent.getCode().equals(KeyCode.ENTER) && !btnLogin.isDisabled()) {
			traitementConnexion();
		}
	}
	/**
	 * Cette méthode permet de gérer le clic sur le bouton [Valider]
	 */
	@FXML public void evtOnMouseClickedBtnLogin() {
		traitementConnexion();
	}
	/**
	 * Cette méthode permet de gérer le clic sur le bouton [Quitter]
	 */
	@FXML public void evtOnMouseClickedBtnQuitter() {
		DialogBox dialogBox = new DialogBox("Fermeture de l'application", "", "Voulez-vous quitter l'application", AlertType.CONFIRMATION, ButtonType.CANCEL);
		ButtonType reponse = dialogBox.showDialogConfirmation();
		if(reponse == ButtonType.OK) {
			this.dialogStage.close();
		}	
	}
	/**
	 * Méthode permettant contrôler les données saisies ( login & pwd ) et d'afficher un message d'erreur si besoin 
	 * ou d'appeler le tableau de bord
	 */
	public void traitementConnexion(){
		/** Contrôle de l'existence de l'agent dans la base de données
		 *  à partir de son login et de son mot de passe 
		 */
		agent = selectAgentByLogin(txfLogin.getText());
		nbrConnexions = selectNbreConnexions(uuid);
		String motDePasse = pwfPwd.getText();


		/** Si l'agent existe **/
		if(agent != null) {
			BCrypt.Result resultat = BCrypt.verifyer().verify(motDePasse.toCharArray(), agent.getAgentPwd());

			if(resultat.verified) {
				/** Fermeture de la fenêtre de login **/
				dialogStage.close();
				/** Appel de la fenêtre tableau de bord **/
				try {
					Stage primaryStage 					= new Stage();
					Fenetres fenetre	 				= selectOneFenetre(Cstes.TABLEAUDEBORD);
					if(fenetre!=null) {
						LoaderFXML loaderFxml 			= new LoaderFXML(fenetre);
						primaryStage 	  				= loaderFxml.createLoaderBorderPane();
						DashboardController controler 	= loaderFxml.getLoader().getController();
						controler.setDialogStage(primaryStage);
						controler.setAgent(agent);

						/** Rajout d'un évènement sur la fenêtre du tableau de bord interdisant de quitter l'appliaction 
						 *  en cliquant sur la croix de la fenêtre Windows.
						 */
						fenetreFermeture(primaryStage);
						deleteConnexions(uuid);

						Sessions session = new Sessions(0, agent.getPersonIdt(), uuid, LocalDate.now(), LocalTime.now(), LocalDate.now(), LocalTime.now(), agent);
						insertSessions(session);

						primaryStage.show();
					}		
				} catch(Exception e) {
					e.printStackTrace();
				}
				return;
			}
		}
		/** Affichage du message d'erreur **/
		txfLogin.clear();      
		pwfPwd.clear();         
		lblErreur.setVisible(true);
		txfLogin.requestFocus();
		insertConnexions(uuid);

		/** Gestion du dépassement **/
		if (nbrConnexions >= nbreErreursConnexions.getInfoDetailValueInt()) {
			lastConnexion = selectLastConnexion(uuid);
			//lblDepassement.setVisible(true);
			if (lastConnexion != null) {
				LocalDateTime dateTimeLastConnexion = LocalDateTime.of(lastConnexion.getConnexionsDate(), lastConnexion.getConnexionsTime());
				LocalDateTime dateTimeActuel = LocalDateTime.now();
				long duree = ChronoUnit.SECONDS.between(dateTimeLastConnexion, dateTimeActuel); 
				if (duree < dureeBlqLogin.getInfoDetailValueInt()) {
					btnLogin.setDisable(true);
					btnQuitter.setDisable(true);
					/** Initialisation d'un timer avec un pas de 1 seconde **/
					Timeline timeline = new Timeline();
					timeline.getKeyFrames().add(

							new KeyFrame(Duration.seconds(1),

									new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									countdownStarter--;
									lblDepassement.setText("Le compte est bloqué pendant encore " +

		        		countdownStarter + " seconde(s)");

									if(countdownStarter <= 0) {
										btnLogin.setDisable(false);
										btnQuitter.setDisable(false);
										//lblDepassement.setVisible(true);
										lblDepassement.setText("Dépassement de tentatives");
										lblDepassement.setVisible(false);
										lblErreur.setVisible(false);
										timeline.stop();
									}
								}
							}));

					timeline.setCycleCount(Animation.INDEFINITE);
					timeline.play();
				} else {
					insertConnexions(uuid);
				}
			}	
		}		
	}
}
