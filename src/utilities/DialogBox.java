package utilities;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

/** ***********************************************************************************************
 * CLASSE : DialogBox
 * ************************************************************************************************
 * Cette classe permet de generer des boites de dialogue generiques en instanciant un objet DialogBox.
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 */
public class DialogBox {
	/**
	 *  dialogTitle			: Titre de la DialogBox
	 *  dialogHeaderText	: Texte entête
	 *  dialogMessage		: Message affiché
	 *  dialog				: Type de DialogBox
	 *  dialogAlertType		: Type d'alerte
	 *  dialogDefaultButton	: Bouton par défaut
	 */
	private String 		dialogTitle;
	private String 		dialogHeaderText;
	private String 		dialogMessage;
	private Alert 		dialog;
	private AlertType 	dialogAlertType;
	private ButtonType  dialogDefaultButton;
	/**
	 * 
	 * @param newDialogTitle
	 * @param newDialogHeaderText
	 * @param newDialogMessage
	 * @param newDialogAlertType
	 * @param newDialogDefaultButton
	 */
	public DialogBox(String newDialogTitle, String newDialogHeaderText, String newDialogMessage, 
			AlertType newDialogAlertType, ButtonType  newDialogDefaultButton) {
		super();
		this.dialogTitle 		 = newDialogTitle;
		this.dialogHeaderText 	 = newDialogHeaderText;
		this.dialogMessage 		 = newDialogMessage;
		this.dialogAlertType     = newDialogAlertType;
		this.dialog 			 = new Alert(dialogAlertType);
		this.dialogDefaultButton = newDialogDefaultButton;
	}	
	/**
	 * @return the dialogTitle
	 */
	public String getDialogTitle() {
		return dialogTitle;
	}
	/**
	 * @param dialogTitle the dialogTitle to set
	 */
	public void setDialogTitle(String dialogTitle) {
		this.dialogTitle = dialogTitle;
	}
	/**
	 * @return the dialogHeaderText
	 */
	public String getDialogHeaderText() {
		return dialogHeaderText;
	}
	/**
	 * @param dialogHeaderText the dialogHeaderText to set
	 */
	public void setDialogHeaderText(String dialogHeaderText) {
		this.dialogHeaderText = dialogHeaderText;
	}
	/**
	 * @return the dialogMessage
	 */
	public String getDialogMessage() {
		return dialogMessage;
	}
	/**
	 * @param dialogMessage the dialogMessage to set
	 */
	public void setDialogMessage(String dialogMessage) {
		this.dialogMessage = dialogMessage;
	}
	/**
	 * @return the dialog
	 */
	public Alert getDialog() {
		return dialog;
	}
	/**
	 * @param dialog the dialog to set
	 */
	public void setDialog(Alert dialog) {
		this.dialog = dialog;
	}
	/**
	 * @return the dialogAlertType
	 */
	public AlertType getDialogAlertType() {
		return dialogAlertType;
	}
	/**
	 * @param dialogAlertType the dialogAlertType to set
	 */
	public void setDialogAlertType(AlertType dialogAlertType) {
		this.dialogAlertType = dialogAlertType;
	}
	/**
	 * @return the dialogDefaultButton
	 */
	public ButtonType getDialogDefaultButton() {
		return dialogDefaultButton;
	}
	/**
	 * @param dialogDefaultButton the dialogDefaultButton to set
	 */
	public void setDialogDefaultButton(ButtonType dialogDefaultButton) {
		this.dialogDefaultButton = dialogDefaultButton;
	}
	/**
	 * Méthode permettant d'afficher une boîte de dialogue de type Erreur.
	 */
	public void showDialogError() {
		dialog.setTitle(dialogTitle);
		dialog.setHeaderText(dialogHeaderText);
		String message  = dialogMessage;
		dialog.setContentText(message);
		dialog.showAndWait();	
	}
	/**
	 * Méthode permettant d'afficher une boîte de dialogue de type confirmation.
	 *                Elle renvoie un objet ButtonType qui correspond au bouton sélectionné par
	 *                l'utilisateur.
	 * @return 		[ButtonType]
	 */
	public ButtonType showDialogConfirmation() {
    	dialog = setDefaultButton(dialog, dialogDefaultButton);
		dialog.setTitle(dialogTitle);
		dialog.setHeaderText(dialogHeaderText);
		String message  = dialogMessage;
		dialog.setContentText(message);
		Optional<ButtonType> reponse = dialog.showAndWait();		
		return reponse.get();
	}
	/**
	 * Méthode permettant de modifier la bouton par défaut d'une boite de dialogue
	 * de type confirmation.
	 * @param  alert	[Alert]      
	 * @param  defBtn	[ButtonType]         
	 * @return Alert	
	 */
	private static Alert setDefaultButton ( Alert alert, ButtonType defBtn ) { 
		/** Initialisation du panel de la boite de dialogue **/
		DialogPane pane = alert.getDialogPane(); 
		/** Balayage des différents boutons de la boite de dialogue afin de modifier celui passé en paramêtre **/
		for ( ButtonType t : alert.getButtonTypes() ) ( (Button) pane.lookupButton(t) ).setDefaultButton( t == defBtn ); 
		/** return de la boite de dialogue modifiée **/
		return alert; 
	} 
}
