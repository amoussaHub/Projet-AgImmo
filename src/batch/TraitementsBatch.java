package batch;

import static bdd.AgentBdd.updateAgent;
import static bdd.CivilityBdd.selectOneCivility;
import static bdd.InfoEnteteBdd.deleteInfoEntete;
import static bdd.InfoEnteteBdd.selectOneInfoEnteteByKey;
import static bdd.TypeAgentBdd.selectOneTypeAgent;
import static utilities.GestionExceptions.gestionDesExceptionsMap;
import static utilities.GestionExceptions.gestionDesExceptionsStates;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import at.favre.lib.crypto.bcrypt.BCrypt;
import bdd.ConnexionBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Agent;
import model.Civility;
import model.InfoEntete;
import model.TypeAgent;
import resources.Cstes;

/** ***********************************************************************************************
 * CLASSE : TraitementsBatch
 * ************************************************************************************************
 * Cette classe contient des traitements batch qui peuvent être utiles à l'application
 * @author 		CASARA alain
 * @since		2.0
 * @version		1.0
 */
public class TraitementsBatch extends ConnexionBdd {
	/** 
	 * Attributs de la classe 
	 * l'attribut classeName sert à fournir le nom de la classe dès qu'une Exception se produit
	 */
	private static String classeName	= TraitementsBatch.class.getSimpleName();

	public static void traitementChiffrementDonneesPersonnelles() {
		/** Déclaration des variables **/
		ObservableList<Agent> listeAgents	= FXCollections.observableArrayList();
		/** Contrôle s'il faut faire le traitement. 
		 * Le traitement se fera s'il y a un enregistrement "chiffrementAgent" dans la table InfoEntete 
		 * si l'enregistrement n'existe pas, exécutez la requête suivante sur le serveur :
		 * 
		 * INSERT INTO AgImmo2.dbo.InfoEntete
		 * (infoEnteteKey, infoEnteteDescription, infoEnteteCbx, infoEnteteNbreDetailMax)
		 * VALUES('chiffrementAgent', 'Enregistrement permettant de lancer le batch de chiffrement', '', 0);
		 * 
		 * L'enregistrement est supprimé en fin de traitement 
		 * **/
		InfoEntete traitement	= selectOneInfoEnteteByKey(Cstes.TRTCHIFFREMENTAGENT);
		if(traitement!=null) {
			/** Récupération de la liste des agents **/
			listeAgents	= selectAllAgents();
			/** Traitements : il suffit de modifier l'agent**/
			for(Agent agent :listeAgents) {
				String motDePasse 		= agent.getAgentPwd();
				String motDePasseHache 	= BCrypt.withDefaults().hashToString(12, motDePasse.toCharArray());
				agent.setAgentPwd(motDePasseHache);
				updateAgent(agent);
			}
			/** Suppression de l'enregistrement infoEntete dans la Bdd **/
			deleteInfoEntete(traitement);
		} else {
			System.out.println("L'enregistrement n'existe pas dans la base de données");
		}
	}
	/** ***************************************************************************************************************
	 *  Méthodes SQL spécifiques au traitement Batch
	 *  ************************************************************************************************************ */
	/** *********************************************************************************
	 * Méthode permettant de récupérer une liste d'agents triée par nom
	 * **********************************************************************************
	 * @return	[ObservableList<Agent>]	: Collection d'instances créée
	 */
	public static ObservableList<Agent> selectAllAgents(){
		/** Déclaration des variables **/
		ObservableList<Agent> listeDonnees	= FXCollections.observableArrayList();
		Agent agent 						= null;
		/** Initialisation de la requête **/
		String SQL		 = "";
		SQL			    += "SELECT agentIdt, agentName, agentFirstName, agentMobile, agentPhone, agentEmail, agentCivility, agentType, agentLogin, agentPwd, agentImage";
		SQL			    += " FROM dbo.Agent";
		SQL			    += " ORDER BY agentName";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL avec gestion des Exceptions */
		try {
			Statement statement  = connexion.createStatement();
			ResultSet resultSet  = statement.executeQuery(SQL);
			while (resultSet.next()) {
				agent = mapAgent(resultSet);
				listeDonnees.add(agent);
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
		return listeDonnees;
	}
	
	/** ***************************************************************************************************************
	 *  Méthodes MAP spécifiques au traitement Batch
	 *  ************************************************************************************************************ */
	/** *********************************************************************************
	 * Méthode permettant d'instancier un objet de type [Agent] à partir 
	 * d'un enregistrement de la base de données.
	 * Il faut passer par une méthode spécifique car dans AgentBdd les données sont 
	 * déchiffrées au moment de la lecture. Ce qui n'est pas le cas ici
	 * **********************************************************************************
	 * @param 	resultset	[Resultset]	: resultat de la requête
	 * @return				[Agent]		: instance Agent créée
	 */
	private static Agent mapAgent(ResultSet resultset){
		/** Déclaration du nouvel objet **/
		Agent agent						= null;
		try {
			/** Initialisation des variables **/
			int  		personIdt		= resultset.getInt("agentIdt");
			String 		personName		= resultset.getString("agentName");
			String 		personFirstName = resultset.getString("agentFirstName");
			String 		personMobile	= resultset.getString("agentMobile");
			String 		personPhone		= resultset.getString("agentPhone");
			String 		personEmail		= resultset.getString("agentEmail");
			int    		personCivility	= resultset.getInt("agentCivility");
			Civility	civility		= selectOneCivility(personCivility);
			int    		agentType		= resultset.getInt("agentType");
			TypeAgent	typeAgent		= selectOneTypeAgent(agentType);
			String 		agentLogin		= resultset.getString("agentLogin");
			String 		agentPwd		= resultset.getString("agentPwd");
			String 		agentImage		= resultset.getString("agentImage");
			/** Instanciation d'un nouvel objet */
			agent						= new Agent(personIdt, personName, personFirstName, personMobile, personPhone, personEmail, personCivility, civility, agentType, typeAgent, agentLogin, agentPwd, agentImage);
			/** Création de l'agent **/
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
