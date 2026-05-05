package controller;

import static bdd.TownBdd.insertTown;
import static bdd.TownBdd.updateTown;
import static utilities.UtilitiesControls.isTextFieldEmpty;
import static utilities.UtilitiesControls.isTextFieldLongueur;
import static utilities.UtilitiesControls.isTextFieldNumeric;
import static utilities.UtilitiesControls.validatePhoneNumber;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.Town;
import resources.Cstes;
import utilities.DialogBox;

public class TownDefinitionController extends GeneralDefinitionController {
	/** Declaration des variables **/
	Town town									= null;
	int index									= 0;
	private boolean validerClicked 				= false;
	/** controles de la fenetre **/
	@FXML	private TextField					txfIdt;
	@FXML	private TextField					txfName;
	@FXML	private TextField					txfPostCode;
	/**
	 * Description 	: Methode lancee implicitement a l'affichage de la fenetre fxml.
	 * 				  Elle est automatiquement generee par heritage 
	 * 				  Elle va permettre d'initialiser les differentes combobox presentes
	 * 				  sur la fenetre.
	 */
	@Override
	@FXML public void initialize() {
		
	}
	
	/**
	 * Description 	: Cette methode appelee lors de la creation du FXMLLoader permet de recuperer la ville a modifier
	 *                en affichant ses informations dans la fenetre. 
	 * @param	town	[Town]
	 */
	public void setTown(Town town) {
		/** Initialisation des donnees de la  ville **/
		this.town = town;
	}
	/**
	 * Description 	: Cette methode appelee lors de la creation du FXMLLoader permet de definir l'action CRU en cours
	 * @param	codeAction	[String]
	 */
	public void setAction(String codeAction) {
		switch (codeAction) {
		case "create": 
			lblTitre.setText("Creation d'une nouvelle ville");
			break;
		case "update": 
			lblTitre.setText("Modification d'une ville");
			txfIdt.setText(String.valueOf(town.getTownIdt()));
			txfName.setText(town.getTownName());
			txfPostCode.setText(town.getTownPostCode());
			break;
		}
		this.codeAction = codeAction;
	}
	
	/**
	 * Description 	: Cette Methode correspond aux traitements suite a un clic sur le bouton [Valider]
	 * 				  1. controles des differentes zones obligatoires
	 * 				  2. Affichage d'une dialogueBox si besoin pour afficher les zones de saisie
	 * 				  3. Traitements sur la base de donnees.
	 */
	@Override
	@FXML public void evtOnMouseClickedBtnValider() {
		/** Initialisation des variables **/
		String messageErreur = "";
		
		/** controle des zones obligatoires **/
		if (isTextFieldEmpty(txfName)) {
			messageErreur += "- Veuillez entrer le nom de la ville" + Cstes.CR;
		} 
		if (isTextFieldEmpty(txfPostCode) || !isTextFieldLongueur(txfPostCode, 5) || !isTextFieldNumeric(txfPostCode)) {
			messageErreur += "- Veuillez entrer le code postal de la ville" + Cstes.CR + "Celui doit être composé de 5 caractères numériques";
		}
		
		if (messageErreur.isEmpty()) {
			if (codeAction.equals("create")) {
				town = new Town();
				
				town.setTownName(txfName.getText());
				town.setTownPostCode(txfPostCode.getText());
				
				insertTown(town);
			} else {
				town.setTownName(txfName.getText());
				town.setTownPostCode(txfPostCode.getText());
				
				updateTown(town);
			}
			validerClicked = true;
			this.dialogStage.close();
		} else {
			DialogBox dialogBox = new DialogBox("Gestion de la ville", "", messageErreur, AlertType.CONFIRMATION, ButtonType.CANCEL);
			ButtonType reponse = dialogBox.showDialogConfirmation();
		}
	}
	
	/**
	 * Description 	: Cette Methode renvoie la valeur de l'attribut [validerClicked]
	 *							  permettant a l'ecran appelant de savoir si le bouton [Valider]
	 *							  a ete clique
	 * @return	boolean
	 */
	public boolean isValiderClicked() {
		return validerClicked;
	}  
}
