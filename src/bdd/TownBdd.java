package bdd;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Town;

/**
 * Cette classe contient les methodes CRUD de l'entite [Town]. 
 * Elle herite de la Classe ConnexionBdd qui gere le driver et la connexion a la base de donnees
 * 
 * @author 		Alain CASARA
 * @version		1.0
 * Date			Juillet 2023
 */
public class TownBdd extends ConnexionBdd {
	/** Attributs de la classe **/
	private static String classeName	= TownBdd.class.getSimpleName();
	/**
	 * Description 	: Methode permettant de recuperer la liste villes triee par nom.
	 * @return  	[ObservableList<Town>]		            
	 */
	public static ObservableList<Town> selectAllTown(){
		/** Initialisation des variables **/
		ObservableList<Town> listeDonnees 	= FXCollections.observableArrayList();
		Town town							= null;
		/** Initialisation de la requete **/
		String SQL		= "";
		SQL			    += "SELECT * FROM [dbo].[Town]";
		SQL			    += " ORDER BY [townName]";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					town = map(resultSet);
					if(town!=null) listeDonnees.add(town);
				}	
			} catch (SQLException e) {
				class Dummy {};
				String methodeName 	= Dummy.class.getEnclosingMethod().getName();
				gestionDesExceptionsStates(e, SQL, classeName, methodeName);
			}
		}
		return listeDonnees;			
	}
	/**
	 * Méthode permettant de contrôler si une ville est utilisée dans le fichier Address
	 * @param town	[Town]	: Ville à contrôler
	 * @return 		[int] 	: renvoie 0 si la ville n'est pas utilisée.
	 */
	public static int selectTownUsed(Town town){
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requete **/
		String SQL		= "";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return nbreEnreg;			
	}
	/**
	 * Description 	: Methode permettant de recuperer une ville à partir de son identifiant.
	 * @param townIdt	[int]	: identifiant de recherche
	 * @return  		[Town]	: instance de Town            
	 */
	public static Town selectOneTown(int townIdt){
		/** Initialisation des variables **/
		Town town							= null;
		/** Initialisation de la requete **/
		String SQL		= "SELECT * FROM Town WHERE townIdt = ?";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, townIdt);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					town = map(resultSet);
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
		return town;			
	}
	/**
	 * Méthode permettant d'insérer une ville
	 * @param town	[Town]	: Ville à insérer
	 * @return		[int]	: nombre d'enregistrements supprimés
	 */
	public static int insertTown(Town town) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requête **/
		String SQL		 = "";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		return nbreEnreg;
	}
	/**
	 * Méthode permettant de modifier une ville
	 * @param town	[Town]	: Ville à insérer
	 * @return		[int]	: nombre d'enregistrements supprimés
	 */
	public static int updateTown(Town town) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requête **/
		String SQL		 = "";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		return nbreEnreg;
	}	
	/**
	 * Méthode permettant de supprimer une ville
	 * @param town	[Town]	: Ville à supprimer
	 * @return		[int]	: nombre d'enregistrements supprimés
	 */
	public static int deleteTown(Town town) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requête **/
		String SQL		 = "";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		return nbreEnreg;
	}
	/**
	 * Methode 	: map
	 * Description 	: Methode permettant de d'instancier un objet [Town] a partir d'un enregistrement
	 *                de la base de donnees.
	 * @param 	resultset	[Resultset]		: resultat de la requete
	 * @return				[Town]			: instance Town creee
	 */
	private static Town map(ResultSet resultset) {
		/** Initialisation des variables **/
		Town town				= null;
		
		int townIdt = 0;
		String townName = null;
		String townPostCode = null;
		
		try {
			townIdt = resultset.getInt("townIdt");
			townName = resultset.getString("townName");
			townPostCode = resultset.getString("townPostCode");
			
			town = new Town(townIdt, townName, townPostCode);
			
		} catch (SQLException e) {
			System.out.println("Erreur lors de la lecture de la ville : " + e);
			e.printStackTrace();
		}		
		return town;
	}
}