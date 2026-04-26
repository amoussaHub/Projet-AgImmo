package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/** ***********************************************************************************************
 * CLASSE : UtilitiesControls
 * ************************************************************************************************
 * Cette classe contient les méthodes de contrôles des différentes zones de saisie
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 */
public class UtilitiesControls {
	/**
	 * Méthode contrôlant le format d'un téléphone ( ou mobile )
	 * Format de saisie : XX-XX-XX-XX-XX
	 * @param   textField	[TextField]
	 * @param 	phone		[String]
	 * @return				[boolean]	
	 */
	public static boolean validatePhoneNumber(TextField textField) {
		/** Initialisation des variables **/
		boolean phoneFormat	= true;
		String classeErreur = "zoneEnErreur";
		String phone		= textField.getText();
		/** Contrôle **/
		Pattern pPhone		= Pattern.compile("\\d{2}-\\d{2}-\\d{2}-\\d{2}-\\d{2}");
		Matcher mPhone		= pPhone.matcher(phone);
		phoneFormat			= mPhone.matches();
		/** Retrait de la classe erreur **/
		textField.getStyleClass().remove(classeErreur);
		/** Contrôle du format **/
		if(!phoneFormat) {
			phoneFormat	= false;
			textField.getStyleClass().add(classeErreur);
		}    	
		return phoneFormat;
	}
	/**
	 * Méthode contrôlant d'une adresse Email
	 * Format de saisie : XXXXXXX@YYYY.ZZZ
	 * @param   textField	[TextField]
	 * @param 	email		[String]
	 * @return				[boolean]	
	 */
	public static boolean isEmailAdress(TextField textField){
		/** Initialisation des variables **/
		boolean emailFormat	= true;
		String classeErreur = "zoneEnErreur";
		String email		= textField.getText();
		/** Contrôle **/
		Pattern pEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Matcher mEmail = pEmail.matcher(email.toUpperCase());
		emailFormat			= mEmail.matches();
		/** Retrait de la classe erreur **/
		textField.getStyleClass().remove(classeErreur);
		/** Contrôle du format **/
		if(!emailFormat) {
			emailFormat	= false;
			textField.getStyleClass().add(classeErreur);
		}    	
		return emailFormat;
	}
	/**
	 * Méthode contrôlant un mot de passe
	 *  (?=.*\\d)    		:   doit contenir un chiffre entre 0 et 9
	 *  (?=.*[a-z]) 		:   doit contenir une lettre en minuscule
	 *  (?=.*[A-Z]) 		:   doit contenir une lettre en majuscule
	 *  (?=.*[-+!*$@%_])	:   doit contenir une des caracteres speciaux
	 *  {8,20}				:   La longueur doit etre de 8 au minimum 
	 *							et de 20 au maximum
	 * @param   passwordField	[PasswordField]
	 * @param 	pwd				[String]
	 * @return					[boolean]
	 */
	public static boolean validatePwd(PasswordField passwordField){
		/** Initialisation des variables **/
		boolean pwdFormat	= true;
		String classeErreur = "zoneEnErreur";
		String pwd			= passwordField.getText();	
		/** Contrôle **/
		Pattern pPwd = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,20})$");
		Matcher mPwd = pPwd.matcher(pwd);
		pwdFormat			= mPwd.matches();
		/** Retrait de la classe erreur **/
		passwordField.getStyleClass().remove(classeErreur);
		/** Contrôle du format **/
		if(!pwdFormat) {
			pwdFormat	= false;
			passwordField.getStyleClass().add(classeErreur);
		}    	
		return pwdFormat;
	}
	/**
	 * Méthode permettant Contrôler si aucune ligne n'est sélectionnée dans une combobox
	 * @param 	comboBox	[ComboBox]
	 * @return				[boolean]
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isItemNotSelected(ComboBox comboBox){
		/** Initialisation des variables **/
		boolean itemNotSelected 	= false;
		String classeErreur  		= "zoneEnErreur";
		/** Contrôle **/
		comboBox.getStyleClass().remove(classeErreur);
		if(comboBox.getSelectionModel().getSelectedItem() == null) {
			itemNotSelected = true;
			comboBox.getStyleClass().add(classeErreur);
		}
		return itemNotSelected;
	}	
	/**
	 * Méthode permettant Contrôler si une textField contient une valeur positive (>0)
	 * @param 	textField	[TextField]
	 * @return				[boolean]
	 */
	public static boolean isValeurNotPositive(TextField textField) {
		/** Initialisation des variables **/
		boolean textFieldNotPositive 	= false;
		String classeErreur  			= "zoneEnErreur";
		textField.getStyleClass().remove(classeErreur);
		/** Contrôle **/
		int valeurNumerique = Integer.parseInt(textField.getText());
		if(valeurNumerique<=0) {
			textFieldNotPositive = true;
			textField.getStyleClass().add(classeErreur);
		}
		return textFieldNotPositive;
	}
	/**
	 * Méthode permettant Contrôler si une textField est non vide
	 * @param 	textField	[TextField]
	 * @return				[boolean]
	 */
	public static boolean isTextFieldEmpty(TextField textField) {
		/** Initialisation des variables **/
		boolean textFieldEmpty 	= false;
		String classeErreur  	= "zoneEnErreur";
		textField.getStyleClass().remove(classeErreur);
		/** Contrôle **/
		if(textField.getText().isEmpty() || textField.getText().isBlank()) {
			textFieldEmpty = true;
			textField.getStyleClass().add(classeErreur);
		}
		return textFieldEmpty;
	}
	/**
	 * Méthode permettant de contrôler si un textField contient une valeur numérique
	 * @param 	textField	[TextField]
	 * @return				[boolean]
	 */
	public static boolean isTextFieldNumeric(TextField textField) {
		/** Initialisation des variables **/
		boolean textFieldNumeric 	= true;
		String classeErreur  		= "zoneEnErreur";
		textField.getStyleClass().remove(classeErreur);
		/** Contrôle **/
		if(!textField.getText().matches("\\d+")) {
			textFieldNumeric = false;
			textField.getStyleClass().add(classeErreur);
		}
		return textFieldNumeric;
	}
	/**
	 * Méthode permettant de contrôler si un textField contient une valeur numérique double
	 * @param 	textField	[TextField]
	 * @return				[boolean]
	 */
	public static boolean isTextFieldNumericDouble(TextField textField) {
		/** Initialisation des variables **/
		boolean textFieldNumeric 	= true;
		String classeErreur  		= "zoneEnErreur";
		textField.getStyleClass().remove(classeErreur);
		/** Contrôle **/
		if(!textField.getText().matches("^[0-9]+([,.][0-9]?)?$")) {
			textFieldNumeric = false;
			textField.getStyleClass().add(classeErreur);
		}
		return textFieldNumeric;
	}
	/**
	 * Méthode contrôlant une clé de chiffrement
	 *  (?=.*\\d)    		:   doit contenir un chiffre entre 0 et 9
	 *  (?=.*[a-z]) 		:   doit contenir une lettre en minuscule
	 *  (?=.*[A-Z]) 		:   doit contenir une lettre en majuscule
	 *  (?=.*[-+!*$@%_])	:   doit contenir un  des caracteres speciaux
	 *  {8,20}				:   La longueur doit etre de 12
	 * @param   textField		[TextField]
	 * @param 	pwd				[String]
	 * @return					[boolean]
	 */
	public static boolean validateKey(TextField textField){
		/** Initialisation des variables **/
		boolean keyFormat	= true;
		String classeErreur = "zoneEnErreur";
		String pwd			= textField.getText();	
		/** Contrôle **/
		Pattern pPwd 		= Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{12})$");
		Matcher mPwd 		= pPwd.matcher(pwd);
		keyFormat			= mPwd.matches();
		/** Retrait de la classe erreur **/
		textField.getStyleClass().remove(classeErreur);
		/** Contrôle du format **/
		if(!keyFormat) {
			keyFormat	= false;
			textField.getStyleClass().add(classeErreur);
		}    	
		return keyFormat;
	}
	/**
	 * Description 	: Méthode permettant de contrôler si le texte saisi dans un contrôle TextField 
	 *				  à la longueur souhaitée	 
	 * @param	textField   [TextField]	: Correspond au contrôle TextField à contrôler.
	 * @param   longueur	[int] 		: longueur à tester
	 * @return  			[boolean]            
	 */
 	public static boolean isTextFieldLongueur(TextField textField, int longueur) {
		/** Initialisation des variables **/
		boolean textFieldLenght 		= true;
		String classeErreur  			= "zoneEnErreur";
		textField.getStyleClass().remove(classeErreur);
		/** Contrôle **/
 		if(textField.getLength()!=longueur) {
 			textFieldLenght = false;
			textField.getStyleClass().add(classeErreur);
 		}
 		return textFieldLenght;
 	}
}
