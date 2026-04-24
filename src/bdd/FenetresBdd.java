package bdd;

import static utilities.GestionExceptions.gestionDesExceptionsMap;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Fenetres;

/** ***********************************************************************************************
 * CLASSE : FenetresBdd
 * ************************************************************************************************
 * Cette classe contient les méthodes CRUD d'accès à la table [Fenetres] de la base de données.
 * Elle hérite de la Classe ConnexionBdd contenant les données de connexion à la base de données.  
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 * @see			model.Fenetres
 */
public class FenetresBdd extends ConnexionBdd {
	/** 
	 * Attributs de la classe 
	 * l'attribut classeName sert à fournir le nom de la classe dès qu'une Exception se produit
	 */
	private static String classeName	= FenetresBdd.class.getSimpleName();
	/** *********************************************************************************
	 * Méthode permettant de récupérer les informations liées à une fenétre à partir
	 * du nom de la fenêtre
	 * **********************************************************************************
	 * @param	key			[String]	: clé de recherche   	
	 * @return  Fenetres	[Fenetres]	: instance créée	            
	 */
	public static Fenetres selectOneFenetre(String key){
		/** Déclaration des variables **/
		Fenetres fenetre 	= null;
		/** Initialisation de la requête **/
		String SQL			= "";
		SQL			       += "SELECT fenetreIdt, fenetreKey, fenetreLocation, fenetreStyleSheet, fenetreStageTitle, fenetreStageIcone, fenetreLoaderMsgErreur";
		SQL			       += " FROM dbo.Fenetres";
		SQL			       += " WHERE fenetreKey like ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, key);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					fenetre = map(resultSet);
				}	
			} catch (SQLException e) {
				/**
				 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
				 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
				 */
				class Dummy {};
				String methodeName 	= Dummy.class.getEnclosingMethod().getName();
				gestionDesExceptionsStates(e, SQL, classeName, methodeName);
			}		
		}
		return fenetre;		
	}	
	/** *********************************************************************************
	 * Méthode permettant d'instancier un objet [Fenêtre] à partir 
	 * d'un enregistrement de la base de données.
	 * **********************************************************************************
	 * @param 	resultset	[Resultset]		: resultat de la requête
	 * @return  			[Fenetres]		: instance Fenetres créée	            
	 */
	private static Fenetres map(ResultSet resultset) throws SQLException {
		/** Déclaration du nouvel objet **/
		Fenetres fenetre						= null;			 
		try {
			int 		fenetreIdt				= resultset.getInt("fenetreIdt");
			String		fenetreKey				= resultset.getString("fenetreKey");
			String 		fenetrelocation			= resultset.getString("fenetreLocation");
			String 		fenetreStyleSheet		= resultset.getString("fenetreStyleSheet");
			String 		fenetreStageTitle		= resultset.getString("fenetreStageTitle");
			String 		fenetreStageIcone		= resultset.getString("fenetreStageIcone");
			String 		fenetreLoaderMsgErreur	= resultset.getString("fenetreLoaderMsgErreur");
			fenetre								= new Fenetres(fenetreIdt, fenetreKey, fenetrelocation, fenetreStyleSheet, fenetreStageTitle, fenetreStageIcone, fenetreLoaderMsgErreur);
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsMap(e, classeName, methodeName);
		}		
		return fenetre;
	}
}
