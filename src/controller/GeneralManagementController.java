package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Cette classe est la Classe generale pour les fenetres de management
 * Il s'agit d'une classe abstraite, non instanciable et devant etre obligatoirement heritee 
 * 
 * @author 	Alain CASARA
 * @version	1.0
 */
public abstract class GeneralManagementController {
	// ***************************************************
	// Declaration des controles de la fenetre
	// ***************************************************
	@FXML 	protected Stage 				dialogStage;
	@FXML	protected Label					lblTitre;
	@FXML	protected Button				btnAnnuler;
	@FXML	protected Button				btnAjouter;
	@FXML	protected Button				btnModifier;
	@FXML	protected Button				btnSupprimer;
	@FXML   protected TextField				txfSelection;
	@FXML	protected ImageView				imvSelection;

	/**
	 * Methode 	: initialize
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Methode lancee implicitement a l'affichage de la fenetre fxml.
	 */
	@FXML
	public abstract void initialize();	
	/**
	 * Methode 	: evtOnMouseClickedImvSelection
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur l'image [Selection]
	 */
	public abstract void  evtOnMouseClickedImvSelection();
	/**
	 * Methode 	: evtOnMouseClickedBtnModifier
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur le bouton [Modifier]
	 */
	public abstract void  evtOnMouseClickedBtnModifier();	
	/**
	 * Methode 	: evtOnMouseClickedBtnAjouter
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur le bouton [Ajouter]
	 */
	public abstract void  evtOnMouseClickedBtnAjouter();	
	/**
	 * Methode 	: evtOnMouseClickedBtnSupprimer
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur le bouton [Supprimer]
	 */
	public abstract void  evtOnMouseClickedBtnSupprimer();	
	/**
	 * Methode 	: evtOnMousePressedTbvDonnees
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement double-clic sur la tableView
	 */
	public abstract void  evtOnMousePressedTbvDonnees(MouseEvent event);
	/**
	 * Methode 	: setDialogStage
	 * Description 	: Cette methode permet de definir le stage de la fenetre courante a partir de la fenetre
	 * 				  appelante. Elle est definie dans la classe mere et donc appliquee par heritage dans
	 * 				  les fenetres filles.
	 * @param	dialogStage	[Stage]
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	/**
	 * Methode 	: evtOnMouseClickedBtnAnnuler
	 * Description 	: Cette methode, rattachee au bouton [Annuler] permet de fermer la fenetre en cours.
	 * 				  Elle est definie dans la classe mere et donc appliquee par heritage dans
	 * 				  les fenetres filles.
	 */
	public void evtOnMouseClickedBtnAnnuler() {
		this.dialogStage.close();
	}	
}
