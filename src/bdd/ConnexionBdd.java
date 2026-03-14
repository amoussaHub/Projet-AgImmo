package bdd;
import static utilities.UtilitiesProperties.getConfigProperties;
import static utilities.GestionExceptions.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import resources.Cstes;

/** ***********************************************************************************************
 * CLASSE : ConnexionBdd
 * ************************************************************************************************
 * Cette classe contient contient la méthode de connexion à la base de données.
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 */
public abstract class ConnexionBdd {
	/** 
	 * Attributs de la classe 
	 * l'attribut classeName sert à fournir le nom de la classe dès qu'une Exception se produit
	 */
	private static String classeName	= AgentBdd.class.getSimpleName();

	/** Déclaration des attributs de la classe **/
	static String driver 	= getConfigProperties(Cstes.JDBCDRIVER);
	static String path		= getConfigProperties(Cstes.JDBCURL);
	static String user 		= getConfigProperties(Cstes.JDBCLOGIN);
	static String pwd		= getConfigProperties(Cstes.JDBCPWD);
	/** *********************************************************************************
	 * Méthode permettant de se connecter à la base de données
	 * **********************************************************************************
	 * @return 	[Connection]	: renvoie un objet connexion à la base de données
	 */
	public static Connection trtConnexionBdd() {
		/**
		 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
		 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
		 */
		class Dummy {};
		String methodeName 	= Dummy.class.getEnclosingMethod().getName();
		/** Déclaration de la connexion **/
		Connection connexion = null;
		/** Chargement du driver et création de la connexion **/
		try {
			Class.forName(driver);
			connexion = DriverManager.getConnection(path, user, pwd);
		} catch (ClassNotFoundException e) {
			gestionDesExceptionsConnexion("ClassNotFoundException", e.getStackTrace().toString(), classeName, methodeName);
		}catch (SQLException e) {
			gestionDesExceptionsConnexion("SQLException", e.getStackTrace().toString(), classeName, methodeName);
		}
		return connexion;
	}
}
