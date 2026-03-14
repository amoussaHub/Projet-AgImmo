package bdd;

import static utilities.GestionExceptions.gestionDesExceptionsMap;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import model.Connexions;

/** ***********************************************************************************************
 * CLASSE : ConnexionsBdd
 * ************************************************************************************************
 * Cette classe contient les méthodes CRUD d'accès à la table [Connexions] de la base de données.
 * Elle hérite de la Classe ConnexionBdd contenant les données de connexion à la base de données.  
 * @author 		CASARA alain
 * @since		2.0
 * @version		1.0
 * @see			model.Connexions
 */
public class ConnexionsBdd extends ConnexionBdd {
	/** Attributs de la classe **/
	private static String classeName	= ConnexionsBdd.class.getSimpleName();
	/** *********************************************************************************
	 * Méthode permettant de sélectionner la dernière connexion d'un UUID
	 * **********************************************************************************
	 * @param uuid		[String]		: UUID de connexion
	 * @return			[Connexions]	: instance créée 
	 */
	public static Connexions selectLastConnexion(String uuid) {
		/** Déclaration des variables **/
		Connexions connexions	= null;
		/** Initialisation de la requête */
		String SQL		   = "Select TOP 1 connexionsIdt, connexionsUuid, connexionsDate, connexionsTime FROM Connexions "
						   + "WHERE connexionsUuid = ? "
						   + "ORDER BY connexionsIdt DESC";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, uuid);
			ResultSet resultSet   				= preparedStatement.executeQuery();
			while (resultSet.next()) {
				connexions = map(resultSet);
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
		return connexions;
	}
	/** *********************************************************************************
	 * Méthode permettant de récupérer le nombre de connexions pour un Uuid 
	 * **********************************************************************************
	 * @param	uuid	[String]	: UUID de connexion
	 * @return			[int]		: nombre de connexions
	 */
	public static int selectNbreConnexions(String uuid) {
		/** Déclaration des variables **/
		int nbreConnexions	= 0;
		/** Initialisation de la requête */
		String SQL		  	= "Select count(*) FROM Connexions "
							+ "WHERE connexionsUuid = ? ";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, uuid);
			ResultSet resultSet   				= preparedStatement.executeQuery();
			while (resultSet.next()) {
				nbreConnexions = resultSet.getInt(1);
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
		return nbreConnexions;
	}
	/** *********************************************************************************
	 * Méthode permettant d'insérer une connexions dans la base de données
	 * **********************************************************************************
	 * @param uuid		[String]	: UUID de connexion
	 * @return			[int]		: nombre d'enregistrements insérés
	 */
	public static int insertConnexions(String uuid) {
		/** Déclaration des variables **/
		int nbreEnreg	= 0;
		/** Initialisation de la requête */
		String SQL		   = "INSERT INTO Connexions (connexionsUuid, connexionsDate, connexionsTime) "
						   + "VALUES(?, ?, ?)";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false
												,uuid
												,LocalDate.now()
												,LocalTime.now()
												);
			nbreEnreg							= preparedStatement.executeUpdate();
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsStates(e, SQL, classeName, methodeName);
		}
		return nbreEnreg;
	}
	/** *********************************************************************************
	 * Méthode permettant de supprimer les connexions d'un UUID
	 * **********************************************************************************
	 * @param uuid		[String]	: UUID de connexion
	 * @return			[int]		: nombre d'enregistrements supprimés
	 */
	public static int deleteConnexions(String uuid) {
		/** Déclaration des variables **/
		int nbreEnreg	= 0;
		/** Initialisation de la requête */
		String SQL		   = "DELETE FROM Connexions WHERE connexionsUuid = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false,uuid);
			nbreEnreg							= preparedStatement.executeUpdate();
		} catch (SQLException e) {
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsStates(e, SQL, classeName, methodeName);
		}
		return nbreEnreg;
	}
	/** *********************************************************************************
	 * Méthode permettant de créer un objet de type [Connexions] à partir 
	 * d'un enregistrement de la base de données.
	 * **********************************************************************************
	 * @param 	resultset	[Resultset]		: resultat de la requête
	 * @return				[Connexions]	: instance créée
	 */
	private static Connexions map(ResultSet resultset) {
		/** Initialisation des variables **/
		Connexions connexion				= null;
		
		int connexionsIdt = 0;
		String connexionsUuid = null;
		LocalDate connexionsDate = null;
		LocalTime connexionsTime = null;
		
		try {
			connexionsIdt = resultset.getInt("connexionsIdt");
			connexionsUuid = resultset.getString("connexionsUuid");
			connexionsDate	= Instant.ofEpochMilli(resultset.getDate("connexionsDate").getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			connexionsTime	= resultset.getTime("connexionsTime").toLocalTime();
			
			
			connexion = new Connexions(connexionsIdt, connexionsUuid, connexionsDate, connexionsTime);
			
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsMap(e, classeName, methodeName);
		}		
		return connexion;
	}
}
