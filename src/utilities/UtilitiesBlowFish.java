package utilities;

import static bdd.InfoDetailBdd.selectOneInfoDetailDescription;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import model.InfoDetail;
import resources.Cstes;

/** ***********************************************************************************************
 * CLASSE : UtilitiesBlowFish
 * ************************************************************************************************
 * Cette classe contient les méthodes de chiffrement et de déchiffrement des données
 * @author 		CASARA alain
 * @since		2.0
 * @version		1.0
 */
public class UtilitiesBlowFish {

	/**
	 * Méthode permettant de chiffrer une chaine de caracteres passee en parametre
	 * La clé de chiffrement est récupérée dans la base de données
	 * @param chaineAChiffrer			[String]	: chaine à chiffrer
	 * @return							[String]	: chaine chiffrée
	 */
	public static String encrypt(String chaineAChiffrer) {
		String encryptedtext 	= "";
		try {
			InfoDetail keyBdd 		= selectOneInfoDetailDescription(Cstes.CHIFFREMENT, Cstes.KEYBF);
			String key 				= keyBdd.getInfoDetailLbc();
			byte[] KeyData 			= key.getBytes();
			SecretKeySpec KS 		= new SecretKeySpec(KeyData, "Blowfish");
			Cipher cipher 			= Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, KS);
			encryptedtext 			= Base64.getEncoder().encodeToString(cipher.doFinal(chaineAChiffrer.getBytes("UTF-8")));
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e) {
			System.out.println("Erreur dans le chiffrement du texte." + e);
		}
		return encryptedtext;

	}
	/**
	 * Méthode permettant de chiffrer une chaine de caracteres passee en parametre
	 * @param password					[String]	: chaine à chiffrer
	 * @param key						[String]	: Clé de chiffrement
	 * @return							[String]	: chaine chiffrée
	 */
	public static String encryptWithKey(String password, String key) {
		String encryptedtext 	= "";
		try {
			byte[] KeyData 		= key.getBytes();
			SecretKeySpec KS 	= new SecretKeySpec(KeyData, "Blowfish");
			Cipher cipher 		= Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, KS);
			encryptedtext 		= Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes("UTF-8")));
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | UnsupportedEncodingException e) {
			System.out.println("Erreur dans le chiffrement du texte." + e);
		}
		return encryptedtext;
	}	
	/**
	 * Méthode permettant de dechiffrer une chaine de caracteres passee en parametre
	 * La clé de chiffrement est récupérée dans la base de données
	 * @param encryptedtext					[String]	: chaine à dechiffrer
	 * @return								[String]	: chaine chiffree
	 */
	public static String decrypt(String encryptedtext) {    	
		String decryptedString 	= "";
		try {
			InfoDetail keyBdd 			= selectOneInfoDetailDescription(Cstes.CHIFFREMENT, Cstes.KEYBF);
			String key 					= keyBdd.getInfoDetailLbc();
			byte[] KeyData 				= key.getBytes();
			SecretKeySpec KS 			= new SecretKeySpec(KeyData, "Blowfish");
			byte[] ecryptedtexttobytes 	= Base64.getDecoder().decode(encryptedtext);
			Cipher cipher 				= Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, KS);
			byte[] decrypted 			= cipher.doFinal(ecryptedtexttobytes);
			decryptedString 			= new String(decrypted, Charset.forName("UTF-8"));
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			System.out.println("Erreur dans le déchiffrement du texte." + e);
		}
		return decryptedString;
	}
}
