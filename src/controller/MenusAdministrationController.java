package controller;

import static bdd.FenetresBdd.selectOneFenetre;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Fenetres;
import model.LoaderFXML;
import resources.Cstes;

public class MenusAdministrationController {

	/** Declaration des controles de la fenetre **/
	@FXML protected Stage 		dialogStage;
	@FXML private 	Label 		lblTitre;	 
	@FXML private 	BorderPane 	racineAdministration;
	
	@FXML private void initialize() {
		lblTitre.setText("Administration des tables de la base de données");
	}
	@FXML private void evtOnActionMnuClose() {
		dialogStage.close();
	}
	@FXML private void evtOnActionMnuCivility() {
		/*Fenetres fenetre	  			= selectOneFenetre(Cstes.CIVILITY);
		if(fenetre!=null) {
			LoaderFXML loaderFxml 		= new LoaderFXML(fenetre);	
			BorderPane rootCivility   	= loaderFxml.createLoaderBorderPaneAdmin();
			racineAdministration.setCenter(rootCivility);
		}*/
		Fenetres fenetre = selectOneFenetre(Cstes.CIVILITY);
		if(fenetre!=null) {
		LoaderFXML loaderFxml = new LoaderFXML(fenetre.getFenetrelocation(),
		fenetre.getFenetreStyleSheet(),
		fenetre.getFenetreStageTitle(), fenetre.getFenetreStageIcone(),

		fenetre.getFenetreLoaderMsgErreur());

		BorderPane rootCivility = loaderFxml.createLoaderBorderPaneAdmin();
		racineAdministration.setCenter(rootCivility);
		}
	}
	@FXML private void evtOnActionMnuTypeAgent() {
		Fenetres fenetre	  			= selectOneFenetre(Cstes.TYPEAGENT);
		if(fenetre!=null) {
			LoaderFXML loaderFxml 		= new LoaderFXML(fenetre);	
			BorderPane rootTypeAgent   	= loaderFxml.createLoaderBorderPaneAdmin();
			racineAdministration.setCenter(rootTypeAgent);
		}
	}
	@FXML private void evtOnActionMnuInfoEntete() {
		Fenetres fenetre	  				= selectOneFenetre(Cstes.ENTETEINFO);
		if(fenetre!=null) {
			LoaderFXML loaderFxml 			= new LoaderFXML(fenetre);	
			BorderPane rootInfoEntete   	= loaderFxml.createLoaderBorderPaneAdmin();
			racineAdministration.setCenter(rootInfoEntete);
		}
	}
	/**
	 * Methode 	: setDialogStage
	 * Description 	: Cette methode permet de definir le stage de la fenetre courante a partir de la fenetre
	 * 				  appelante. 
	 * @param	dialogStage	[Stage]
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage 		= dialogStage;
		racineAdministration 	= (BorderPane) dialogStage.getScene().getRoot();
	}
}
