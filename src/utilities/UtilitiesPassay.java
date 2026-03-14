package utilities;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
/**
 * Classe	: UtilitiesPassay
 * Cette classe contient la methode permettant de gerer des cles.
 * 
 * @author 	Alain CASARA
 * @version	1.0
 */
public class UtilitiesPassay {
	
	/**
	 * Methode 	: generateKey
	 * Description 	: Methode permettant de generer une cle de chiffrement
     *				  lors de la generation d'un enregistrement afin de le retrouver
	 * @return	String		Cle generee
	 */
	public static String generateKey() {
		PasswordGenerator gen = new PasswordGenerator();
		EnglishCharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
		CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
		lowerCaseRule.setNumberOfCharacters(2);

		EnglishCharacterData upperCaseChars = EnglishCharacterData.UpperCase;
		CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
		upperCaseRule.setNumberOfCharacters(2);

		EnglishCharacterData digitChars = EnglishCharacterData.Digit;
		CharacterRule digitRule = new CharacterRule(digitChars);
		digitRule.setNumberOfCharacters(2);

		org.passay.CharacterData specialChars = new org.passay.CharacterData() {
			public String getErrorCode() {
				return getErrorCode();
			}

			public String getCharacters() {
				return "[string protected]#$%^&*()_+";
			}
		};
		CharacterRule splCharRule = new CharacterRule(specialChars);
		splCharRule.setNumberOfCharacters(2);

		String password = gen.generatePassword(10, splCharRule, lowerCaseRule,
				upperCaseRule, digitRule);
		return password;
	}
}
