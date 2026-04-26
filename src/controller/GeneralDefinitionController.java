package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Cette classe est la Classe generale pour les fenetres de definition 
 * 
 * @author 	Alain CASARA
 * @version	1.0
 */
public abstract class GeneralDefinitionController {
	/** Déclaration des variables de la fenêtre **/
	protected boolean validerClicked 	= false;								
	protected String  codeAction		= "";
	protected int 	  index				= 0;
	protected String  messageErreur 	= "";
	/** Declaration des controles de la fenetre **/
	@FXML 	protected Stage 				dialogStage;
	@FXML	protected Label					lblTitre;
	@FXML	protected Button				btnAnnuler;
	@FXML	protected Button				btnValider;
	/**
	 * Methode 	: initialize
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. 
	 */
	@FXML
	public abstract void initialize(); 
	/**
	 * Methode 	: evtOnMouseClickedBtnValider
	 * Description 	: Methode abstraite devant etre obligatoirement initialisee dans la classe
	 * 				  heritante. Elle doit correspondre a l'evenement clic sur le bouton [Valider]
	 */
	public abstract void evtOnMouseClickedBtnValider();
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
	/**
	 * Description 	: Cette Methode renvoie la valeur de l'attribut [validerClicked]
	 *							  permettant a l'ecran appelant de savoir si le bouton [Valider]
	 *							  a ete clique
	 * @return	boolean
	 */
	@FXML public boolean isValiderClicked() {
		return validerClicked;
	}
}
