package controller;

import static bdd.FenetresBdd.selectOneFenetre;
import static utilities.UtilitiesFermeture.fenetreFermetureLogin;

import java.util.UUID;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Fenetres;
import model.LoaderFXML;
import resources.Cstes;

/** ***********************************************************************************************
 * CLASSE : AgImmo1
 * ************************************************************************************************
 * Cette classe est la classe de lancement de l'application
 * Elle hérite de la Classe Application qui est la classe Abstraite à implémenter pour une application 
 * JavaFX..  
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 * @see			model.Agent
 */
public class AgImmo1 extends Application {
	@Override
	public void start(Stage primaryStage) {		
		/** Appel de la fenêtre de login **/
		Fenetres fenetre	 				= selectOneFenetre(Cstes.LOGIN);
		if(fenetre!=null) {
			LoaderFXML loaderFxml 			= new LoaderFXML(fenetre);
			primaryStage 	  				= loaderFxml.createLoaderBorderPane();
			LoginController controler 		= loaderFxml.getLoader().getController();
			/** Génération d'un UUID de session **/
			UUID uuid = UUID.randomUUID();
			String uuidAsString = uuid.toString();
			
			controler.setDialogStage(primaryStage);
			controler.setUuid(uuidAsString);
			fenetreFermetureLogin(primaryStage);
			primaryStage.show();
		}		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
