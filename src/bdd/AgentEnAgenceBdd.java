package bdd;

import static bdd.AgentBdd.selectAgentByIdt;
import static bdd.CompanyBdd.selectOneCompany;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Agent;
import model.AgentEnAgence;
import model.Company;

public class AgentEnAgenceBdd extends ConnexionBdd {
	/** Attributs de la classe **/
	private static String classeName	= AgentEnAgenceBdd.class.getSimpleName();
	/**
	 * Méthode permettant de récupérer la liste de toutes les agences et si l'agence est rattaché 
	 * @param 	agentIdt	[int]								: identifiant de l'agent
	 * @return				[ObservableList<AgentEnAgence>]		: Liste des agences et rattachement
	 */
	public static ObservableList<AgentEnAgence> selectAllAgencesAgent(int agentIdt){
		/** Initialisation des variables **/
		ObservableList<AgentEnAgence> listeDonnees 	= FXCollections.observableArrayList();
		AgentEnAgence agentEnAgence					= null;	
		/** Initialisation de la requête **/
		String SQL		= "SELECT agentIdt, Company.companyIdt, companyName FROM AgentEnAgence FULL OUTER JOIN Company ON Company.companyIdt = AgentEnAgence.companyIdt";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					agentEnAgence = map(resultSet);
					if(agentEnAgence!=null) listeDonnees.add(agentEnAgence);
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
		return listeDonnees;
	}
	/**
	 * Méthode permettant de récupérer la liste des agents d'une agence 
	 * @param 	companyIdt	[int]								: identifiant de l'agence
	 * @return				[ObservableList<AgentEnAgence>]		: Liste des agences et rattachement
	 */
	public static ObservableList<AgentEnAgence> selectAllAgentsForAgence(int companyIdt){
		/** Initialisation des variables **/
		ObservableList<AgentEnAgence> listeDonnees 	= FXCollections.observableArrayList();
		AgentEnAgence agentEnAgence					= null;	
		/** Initialisation de la requête **/
		String SQL		= "SELECT * FROM AgentEnAgence WHERE companyIdt = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, companyIdt);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					agentEnAgence = map(resultSet);
					if(agentEnAgence!=null) listeDonnees.add(agentEnAgence);
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
		return listeDonnees;
	}
	/**
	 * Description 	: Methode permettant de rattacher une agence à un agent
	 * @param	agentEnAgence	[AgentEnAgence]   	
	 * @return  				[int]			: nombre d'enregistrements inseres, permet de controler que l'insertion s'est bien effectuee	            
	 */
	public static int insertAgentEnAgence(AgentEnAgence agentEnAgence) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requete **/
		String SQL		= "INSERT INTO AgentEnAgence(agentIdt, CompanyIdt)"
						+ "VALUES(?, ?)";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, agentEnAgence.getAgentIdt(), agentEnAgence.getCompanyIdt());
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
		}
		return nbreEnreg;
	}
	/**
	 * Description 	: Methode permettant de supprimer les agences rattachées à un agent.
	 * @param	agentIdt	[int]   	
	 * @return  			[int]	: nombre d'enregistrements supprimes, permet de controler que la suppression s'est bien effectuee	            
	 */
	public static int deleteAgentEnAgence(int agentIdt) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requete **/
		String SQL		= "DELETE FROM AgentEnAgence WHERE agentIdt = ?";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, agentIdt);
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
		}
		return nbreEnreg;
	}
	/**
	 * Méthode permettant de créer un objet de type [Civility] à partir d'un enregistrement de la base de données
	 * @param 	resultset	[Resultset]		: resultat de la requête
	 * @return				[AgentEnAgence]	: instance AgentEnAgence créée
	 */
	private static AgentEnAgence map(ResultSet resultset) {
		/** Initialisation des variables **/
		AgentEnAgence agentEnAgence			= null;
		try {
			/** Initialisation des variables **/
			int 	agentIdt	= resultset.getInt("agentIdt");
			int 	companyIdt	= resultset.getInt("agentIdt");;
			boolean agentIsAttached;
			
			Agent	agent 		= AgentBdd.selectAgentByIdt(agentIdt);
			//Company	company		= CompanyBdd.;
			
			
		} catch (SQLException e) {
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			System.out.println("Classe  : " + classeName);
			System.out.println("Méthode : " + methodeName);
			System.out.println("Erreur lors de la lecture de la l'agence de l'agent : " + e);
		}		
		return agentEnAgence;
	}
}
