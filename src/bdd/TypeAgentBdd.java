package bdd;

import static utilities.GestionExceptions.gestionDesExceptionsMap;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Civility;
import model.TypeAgent;

/** ***********************************************************************************************
 * CLASSE : TypeAgentBdd
 * ************************************************************************************************
 * Cette classe contient les méthodes CRUD d'accès à la table [TypeAgent] de la base de données.
 * Elle hérite de la Classe ConnexionBdd contenant les données de connexion à la base de données.  
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 * @see			model.TypeAgent
 */
public class TypeAgentBdd extends ConnexionBdd {
	/** 
	 * Attributs de la classe 
	 * l'attribut classeName sert à fournir le nom de la classe dès qu'une Exception se produit
	 */
	private static String classeName	= TypeAgentBdd.class.getSimpleName();
	/** *********************************************************************************
	 * Méthode permettant de récupérer une liste des types d'agent
	 * **********************************************************************************
	 * @return		[ObservableList<TypeAgent>]	: Collection d'instances créée
	 */
	public static ObservableList<TypeAgent> selectAllTypeAgent(){
		/** Déclaration des variables **/
		ObservableList<TypeAgent> listeDonnees 	= FXCollections.observableArrayList();
		TypeAgent typeAgent						= null;	
		/** Initialisation de la requête **/
		String SQL		 = "SELECT * FROM TypeAgent";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			Statement statement  = connexion.createStatement();
			ResultSet resultSet  = statement.executeQuery(SQL);
			while (resultSet.next()) {
				typeAgent = map(resultSet);
				listeDonnees.add(typeAgent);
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
	/** *********************************************************************************
	 * Méthode permettant de récupérer un type d'agent à partir de son identifiant
	 * **********************************************************************************
	 * @param		typeAgentIdt	[int]		: identifiant du type d'agent
	 * @return						[TypeAgent]	: instance TypeAgent Créée
	 */
	public static TypeAgent selectOneTypeAgent(int typeAgentIdt){
		/** Déclaration des variables **/
		TypeAgent typeAgent						= null;	
		/** Initialisation de la requête **/
		String SQL		= "SELECT * FROM TypeAgent WHERE typeAgentIdt = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, typeAgentIdt);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					typeAgent = map(resultSet);
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
		return typeAgent;			
	}
	/**
	 * Méthode permettant d'insérer un type d'agent
	 * @param typeAgent	[TypeAgent]	: TypeAgent à insérer
	 * @return			[int]		: nombre d'enregistrements supprimés  
	 */
	public static int insertTypeAgent(TypeAgent typeAgent) {
		/** Déclaration des variables **/
		int nbrEnregistrement = 0;
		/** Initialisation de la requête **/
		String SQL		 = 	"INSERT INTO TypeAgent(typeAgentLbl, typeAgentLbc)"
							+ "VALUES(?, ?)";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, typeAgent.getTypeAgentLbl(), typeAgent.getTypeAgentLbc());
			nbrEnregistrement					= preparedStatement.executeUpdate();
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsStates(e, SQL, classeName, methodeName);
		}		
		return nbrEnregistrement;
	}
	
	/**
	 * Méthode permettant de modifier un type d'agent
	 * @param typeAgent	[TypeAgent]	: TypeAgent à modifier
	 * @return			[int]		: nombre d'enregistrements supprimés  
	 */
	public static int updateTypeAgent(TypeAgent typeAgent) {
		/** Déclaration des variables **/
		int nbrEnregistrement = 0;
		/** Initialisation de la requête **/
		String SQL		 = 	"UPDATE TypeAgent SET typeAgentLbl = ?, typeAgentLbc = ? WHERE typeAgentIdt = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, typeAgent.getTypeAgentLbl(), typeAgent.getTypeAgentLbc(), typeAgent.getTypeAgentIdt());
			nbrEnregistrement					= preparedStatement.executeUpdate();
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsStates(e, SQL, classeName, methodeName);
		}		
		return nbrEnregistrement;
	}
	
	/**
	 * Méthode permettant de supprimer un type d'agent
	 * @param typeAgent	[TypeAgent]	: TypeAgent à supprimer
	 * @return			[int]		: nombre d'enregistrements supprimés  
	 */
	public static int deleteTypeAgent(TypeAgent typeAgent) {
		/** Déclaration des variables **/
		int nbrEnregistrement = 0;
		/** Initialisation de la requête **/
		String SQL		 = 	"DELETE FROM TypeAgent WHERE typeAgentIdt = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, typeAgent.getTypeAgentIdt());
			nbrEnregistrement					= preparedStatement.executeUpdate();
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsStates(e, SQL, classeName, methodeName);
		}		
		return nbrEnregistrement;
	}
	
	/**
	 * Méthode permettant de contrôler si le type d'agent est utilisée
	 * @return			[int]		: nombre d'enregistrements trouvés  
	 */
	public static int selectNbreTypeAgent(int typeAgentIdt) {
		/** Déclaration des variables **/
		int nbrEnregistrement = 0;
		/** Initialisation de la requête **/
		String SQL		 = 	"SELECT count(*) FROM Agent WHERE agentType = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, typeAgentIdt);
			ResultSet resultSet   				= preparedStatement.executeQuery();
			while (resultSet.next()) {
				nbrEnregistrement = resultSet.getInt(1);
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
		return nbrEnregistrement;
	}
	
	/** *********************************************************************************
	 * Méthode permettant de créer un objet de type [TypeAgent] à partir 
	 * d'un enregistrement de la base de données
	 * **********************************************************************************
	 * @param 	resultset	[Resultset]		: resultat de la requête
	 * @return				[TypeAgent]		: instance TypeAgent créée
	 */
	private static TypeAgent map(ResultSet resultset) {
		/** Déclaration de l'information **/
		TypeAgent typeAgent 		= null;
		try {
			
			int typeAgentIdt      = resultset.getInt("typeAgentIdt");
	        String typeAgentLbl   = resultset.getString("typeAgentLbl");
	        String typeAgentLbc   = resultset.getString("typeAgentLbc");
	        
	        typeAgent = new TypeAgent(typeAgentIdt, typeAgentLbl, typeAgentLbc);
			
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsMap(e, classeName, methodeName);
		}		
		return typeAgent;
	}
}
