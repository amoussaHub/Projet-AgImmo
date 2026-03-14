package utilities;

import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import resources.Cstes;
/** ***********************************************************************************************
 * CLASSE : UtilitiesFermeture
 * ************************************************************************************************
 * Cette classe contient les méthodes de contrôles de fermetures des fenêtres
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 */
public class UtilitiesFermeture {
	/**
	 * Méthode affichant une fenêtre d'alerte si l'application est fermée par la croix
	 * 	@param primaryStage	[Stage]
	 */
	public static void fenetreFermeture(Stage primaryStage) {
	    primaryStage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
	        @Override
	        public void handle(final WindowEvent event) {		
		    	event.consume();
		    	String titre 		= "Sortie de l'application";
		    	String messageHaut	= "Sortie impossible";
		    	String messageBas	= "Vous devez quitter l'application"+Cstes.CR+"en utilisant le menu [Deconnexion]"+Cstes.CR+"uniquement";
				DialogBox dialogBox = new DialogBox(titre, messageHaut, messageBas, AlertType.ERROR, null);
				dialogBox.showDialogError();
	        };
	    });   		
	}
	
	
	/**
	 * Méthode affichant une fenêtre d'alerte si la fenêtre de login est fermée par la croix
	 * @param primaryStage	[Stage]
	 */
	public static void fenetreFermetureLogin(Stage primaryStage) {
	    primaryStage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
	        @Override
	        public void handle(final WindowEvent event) {		
		    	event.consume();
		    	String titre 		= "Sortie de l'application";
		    	String messageHaut	= "Sortie impossible";
		    	String messageBas	= "La sortie de l'application se fait avec le bouton Quitter";
				DialogBox dialogBox = new DialogBox(titre, messageHaut, messageBas, AlertType.ERROR, null);
				dialogBox.showDialogError();
	        };
	    });   		
	}
	
}
