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

/** ***********************************************************************************************
 * CLASSE : CivilityBdd
 * ************************************************************************************************
 * Cette classe contient les méthodes CRUD d'accès à la table [Civility] de la base de données.
 * Elle hérite de la Classe ConnexionBdd contenant les données de connexion à la base de données.  
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 * @see			model.Civility
 */
public class CivilityBdd extends ConnexionBdd {
	/** 
	 * Attributs de la classe 
	 * l'attribut classeName sert à fournir le nom de la classe dès qu'une Exception se produit
	 */
	private static String classeName	= CivilityBdd.class.getSimpleName();
	/** *********************************************************************************
	 * Méthode permettant de récupérer la liste des civilités triée par libellé long
	 * **********************************************************************************
	 * @return		[ObservableList<Civility>]	: Collection d'instances créée
	 */
	public static ObservableList<Civility> selectAllCivility(){
		/** Déclaration des variables **/
		ObservableList<Civility> listeDonnees 	= FXCollections.observableArrayList();
		Civility civility						= null;	
		/** Initialisation de la requête **/
		String SQL		 = "SELECT * FROM Civility ORDER BY civilityLbl";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			Statement statement  = connexion.createStatement();
			ResultSet resultSet  = statement.executeQuery(SQL);
			while (resultSet.next()) {
				civility = map(resultSet);
				listeDonnees.add(civility);
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
	 * Méthode permettant de récupérer une civilité à partir de son identifiant
	 * **********************************************************************************
	 * @param civilityIdt	[int]		: identifiant de la civilité
	 * @return				[Civility]	: instance Civility Créée
	 */
	public static Civility selectOneCivility(int civilityIdt) {
		/** Déclaration des variables **/
		Civility civility						= null;	
		/** Initialisation de la requête **/
		String SQL		 = "SELECT * FROM Civility WHERE civilityIdt = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, civilityIdt);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					civility = map(resultSet);
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
		return civility;	
	}
	/**
	 * Méthode permettant d'insérer une civility
	 * @param civility	[civility]	: Civility à insérer
	 * @return			[int]		: nombre d'enregistrements supprimés  
	 */
	public static int insertCivility(Civility civility) {
		/** Déclaration des variables **/
		int nbrEnregistrement = 0;
		/** Initialisation de la requête **/
		String SQL		 = 	"INSERT INTO Civility(civilityLbl, civilityLbc)"
							+ "VALUES(?, ?)";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, civility.getCivilityLbl(), civility.getCivilityLbc());
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
	 * Méthode permettant de modifier une civility
	 * @param civility	[civility]	: Civility à modifier
	 * @return			[int]		: nombre d'enregistrements supprimés  
	 */
	public static int updateCivility(Civility civility) {
		/** Déclaration des variables **/
		int nbrEnregistrement = 0;
		/** Initialisation de la requête **/
		String SQL		 = 	"UPDATE Civility SET civilityLbl = ?, civilityLbc = ? WHERE civilityIdt = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, civility.getCivilityLbl(), civility.getCivilityLbc(), civility.getCivilityIdt());
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
	 * Méthode permettant de supprimer une civility
	 * @param civility	[civility]	: Civility à supprimer
	 * @return			[int]		: nombre d'enregistrements supprimés  
	 */
	public static int deleteCivility(Civility civility) {
		/** Déclaration des variables **/
		int nbrEnregistrement = 0;
		/** Initialisation de la requête **/
		String SQL		 = 	"DELETE FROM Civility WHERE civilityIdt = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, civility.getCivilityIdt());
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
	 * Méthode permettant de contrôler si la civilité est utilisée
	 * @return			[int]		: nombre d'enregistrements trouvés  
	 */
	public static int selectNbreCivility(int civilityIdt) {
		/** Déclaration des variables **/
		int nbrEnregistrement = 0;
		/** Initialisation de la requête **/
		String SQL		 = 	"SELECT count(*) FROM Agent WHERE agentCivility = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, civilityIdt);
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
	 * Méthode permettant de créer un objet de type [Civility] à partir 
	 * d'un enregistrement de la base de données
	 * **********************************************************************************
	 * @param 	resultset	[Resultset]		: resultat de la requête
	 * @return				[Civility]		: instance Civility créée
	 */
	private static Civility map(ResultSet resultset) {
		/** Déclaration du nouvel objet **/
		Civility civility 			= null;
		try {
			
			 // si ta classe Civility a un constructeur vide

			// Récupération des données depuis le ResultSet
			int civilityIdt = resultset.getInt("civilityIdt");
			String civilityLbl = resultset.getString("civilityLbl");
			String civilityLbc = resultset.getString("civilityLbc");

			
			civility = new Civility(civilityIdt, civilityLbl, civilityLbc); 
			
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsMap(e, classeName, methodeName);
		}		
		return civility;
	}
}
