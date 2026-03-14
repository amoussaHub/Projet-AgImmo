package bdd;

import static bdd.CivilityBdd.selectOneCivility;
import static bdd.TypeAgentBdd.selectOneTypeAgent;
import static utilities.GestionExceptions.gestionDesExceptionsMap;
import static utilities.GestionExceptions.gestionDesExceptionsStates;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Agent;
import model.Civility;
import model.TypeAgent;

/** ***********************************************************************************************
 * CLASSE : AgentBdd
 * ************************************************************************************************
 * Cette classe contient les méthodes CRUD d'accès à la table [Agent] de la base de données.
 * Elle hérite de la Classe ConnexionBdd contenant les données de connexion à la base de données.  
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 * @see			model.Agent
 */
public class AgentBdd extends ConnexionBdd {
	/** 
	 * Attributs de la classe 
	 * l'attribut classeName sert à fournir le nom de la classe dès qu'une Exception se produit
	 */
	private static String classeName	= AgentBdd.class.getSimpleName();
	
	/** *********************************************************************************
	 * Méthode permettant de récupérer un agent à partir de son login et son mot de passe
	 * **********************************************************************************
	 * @param 	login	[String]	: login de l'agent
	 * @param 	pwd		[String]	: mot de passe
	 * @return			[Agent]		: instance Agent créée
	 */
	public static Agent selectAgentByLoginPwd(String login, String pwd) {
		/** Déclaration des variables **/
		Agent agent 	= null;
		/** Initialisation de la requête **/
		String SQL		 = "SELECT * FROM Agent WHERE agentLogin = '" + login + "' AND agentPwd = '" + pwd + "'";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL avec gestion des Exceptions */
		try {
			Statement statement  = connexion.createStatement();
			ResultSet resultSet  = statement.executeQuery(SQL);
			while (resultSet.next()) {
				agent = map(resultSet);
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
		return agent;		
	}
	/** *********************************************************************************
	 * Méthode permettant de récupérer un agent à partir de son identifiant
	 * **********************************************************************************
	 * @param  agentIdt	[int]		: Identifiant de l'agent
	 * @return			[Agent]		: instance Agent créée
	 */
	public static Agent selectAgentByIdt(int agentIdt) {
		/** Déclaration des variables **/
		Agent agent 	= null;
		/** Initialisation de la requête **/
		String SQL		= "SELECT * FROM Agent WHERE agentIdt = " + agentIdt;
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			Statement statement   = connexion.createStatement();
			ResultSet resultSet   = statement.executeQuery(SQL);
			while (resultSet.next()) {
				agent = map(resultSet);
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
		return agent;		
	}	
	/** *********************************************************************************
	 * Méthode permettant de modifier les informations d'un agent
	 * **********************************************************************************
	 * @param 	agent	[Agent]	: Agent à modifier
	 * @return			[int]	: nombre d'enregistrements modifiés
	 */
	public static int updateAgent(Agent agent){
		/** Déclaration des variables **/
		int nbreEnreg 	= 0;
		/** Initialisation de la requête **/
		String SQL		= "UPDATE Agent SET agentName = '" + agent.getPersonName() + "', "
				+ "    agentFirstName = '" + agent.getPersonFirstName() + "', "
				+ "    agentMobile = '" + agent.getPersonMobile() + "', "
				+ "    agentPhone = '" + agent.getPersonPhone() + "', "
				+ "    agentEmail = '" + agent.getPersonEmail() + "', "
				+ "    agentCivility = " + agent.getPersonCivility() + ", "
				+ "    agentType = " + agent.getAgentType() + ", "
				+ "    agentLogin = '" + agent.getAgentLogin() + "', "
				+ "    agentPwd = '" + agent.getAgentPwd() + "', "
				+ "    agentImage = '" + agent.getAgentImage() + "' "
				+ "    WHERE agentIdt = " + agent.getPersonIdt();
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			Statement statement  = connexion.createStatement();
			nbreEnreg 			 = statement.executeUpdate(SQL);
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
	 * Méthode permettant d'instancier un objet de type [Agent] à partir 
	 * d'un enregistrement de la base de données
	 * **********************************************************************************
	 * @param 	resultset	[Resultset]	: resultat de la requête
	 * @return				[Agent]		: instance Agent créée
	 */
	private static Agent map(ResultSet resultset){
		/** Déclaration du nouvel objet **/
		Agent agent						= null;
		try {
			/** Initialisation des variables **/
			
			int personIdt           = resultset.getInt("agentIdt");
	        String personName       = resultset.getString("agentName");
	        String personFirstName  = resultset.getString("agentFirstName");
	        String personMobile     = resultset.getString("agentMobile");
	        String personPhone      = resultset.getString("agentPhone");
	        String personEmail      = resultset.getString("agentEmail");
	        int personCivility      = resultset.getInt("agentCivility");  	


	        int agentType           = resultset.getInt("agentType");
	        String agentLogin       = resultset.getString("agentLogin");
	        String agentPwd         = resultset.getString("agentPwd");
	        String agentImage       = resultset.getString("agentImage");
			
			/** Instanciation d'un nouvel objet */
	        
	        Civility civility       = CivilityBdd.selectOneCivility(personCivility);
	        TypeAgent typeAgent     = TypeAgentBdd.selectOneTypeAgent(agentType);
	    
			
			/** Création de l'agent **/
	        agent = new Agent(
	                personIdt, personName, personFirstName, personMobile, personPhone, personEmail,
	                personCivility, civility, agentType, typeAgent, agentLogin, agentPwd, agentImage
	            );
	
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsMap(e, classeName, methodeName);
		}
		return agent;
	}
}
