package bdd;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;
import static bdd.TownBdd.selectOneTown;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Address;
import model.Town;

/**
 * Cette classe contient les methodes CRUD de l'entite [Address]. 
 * Elle herite de la Classe ConnexionBdd qui gere le driver et la connexion a la base de donnees
 * 
 * @author 		Alain CASARA
 * @version		1.0
 * Date			Juillet 2023
 */

public class AddressBdd extends ConnexionBdd {
	/** Attributs de la classe **/
	private static String classeName	= AddressBdd.class.getSimpleName();
	/**
	 * Description 			: Methode permettant de recuperer une adresse à partir de son identifiant
	 * @param addressIdt	[int]		: Identifiant de l'adresse
	 * @return				[Address]	: Instance de l'adresse
	 */
	public static Address selectOneAdresse(int addressIdt) {
		/** Initialisation des variables **/
		Address address	= null;
		/** Initialisation de la requete **/
		String SQL		= "SELECT * FROM Address WHERE addressIdt = ?";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, addressIdt);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					address = map(resultSet);
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
		return address;
	}
	/**
	 * Description 					: Methode permettant de recuperer l'identifiant d'une adresse a partir de sa cle de generation
	 * @param	addressGenerateKey	[String] 	: Cle de recherche
	 * @return  					[int]		: Identifiant de l'adresse	            
	 */
	public static int selectOneAdresseByKey(String addressGenerateKey) {
		/** Initialisation des variables **/
		int	addressIdt	= 0;
		/** Initialisation de la requete **/
		String SQL		= "SELECT addressIdt FROM Address WHERE addressGenerationKey = ?";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, addressGenerateKey);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					addressIdt = resultSet.getInt("addressIdt");
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
		return addressIdt;
	}	
	/**
	 * Description 			: Methode permettant d'inserer une adresse dans la base de donnees avec une requete INSERT.
	 * @param	address		[Address]   	
	 * @return  			[int]		: nombre d'enregistrements inseres, permet de controler que l'insertion s'est bien effectuee	            
	 */
	public static int insertAddress(Address address) {
		/** Initialisation des variables **/
		int nbreEnreg 	= 0;
		/** Initialisation de la requete **/
		String SQL		= "INSERT INTO Address(addressDeliveryPoint, addressNumber, addressPortLabel, addressNext, addressTownIdt, addressGenerationKey)"
						+ "VALUES(?, ?, ?, ?, ?, ?)";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, address.getAddressDeliveryPoint(), address.getAddressNumber(), address.getAddressPortLabel(), address.getAddressNext(), address.getAddressTownIdt(), address.getAddressGenerationKey());
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
	 * Description 			: Methode permettant de modifier une adresse dans la base de donnees avec une requete UPDATE.
	 * @param	address		[Address]   	
	 * @return  			[int]		: nombre d'enregistrements modifies, permet de controler que la modification s'est bien effectuee	            
	 */
	public static int updateAddress(Address address) {
		/** Initialisation des variables **/
		int nbreEnreg 	= 0;
		/** Initialisation de la requete **/
		String SQL		= "UPDATE Address SET addressDeliveryPoint = ?, addressNumber = ?, addressPortLabel = ?, addressNext = ?, addressTownIdt = ?, addressGenerationKey = ? WHERE addressIdt = ?";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, address.getAddressDeliveryPoint(), address.getAddressNumber(), address.getAddressPortLabel(), address.getAddressNext(), address.getAddressTownIdt(), address.getAddressGenerationKey(), address.getAddressIdt());
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
	 * Description 			: Methode permettant de supprimer une adresse dans la base de donnees avec une requete DELETE.
	 * @param	addressIdt	[int]   	
	 * @return  			[int]	: nombre d'enregistrements supprimes, permet de controler que la suppression s'est bien effectuee	            
	 */
	public static int deleteAddress(int addressIdt) {
		/** Initialisation des variables **/
		int nbreEnreg 	= 0;
		/** Initialisation de la requete **/
		String SQL		= "DELETE FROM Address WHERE addressIdt = ?";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, addressIdt);
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
	 * Description 			: Methode permettant de d'instancier un objet [Address] a partir d'un enregistrement
	 *                		  de la base de donnees.
	 * @param 	resultset	[Resultset]	: resultat de la requete
	 * @return				[Address]	: instance d'address creee
	 */
	private static Address map(ResultSet resultset) throws SQLException {
		/** Initialisation des variables **/
		Address address					= null;
		
		int addressIdt = 0;
		String addressDeliveryPoint = null;
		String addressNumber = null;
		String addressPortLabel = null;
		String addressNext = null;
		int addressTownIdt = 0;
		String addressGenerationKey = null;
		Town town = null;
		
		
		try {
			addressIdt = resultset.getInt("addressIdt");
			addressDeliveryPoint = resultset.getString("addressDeliveryPoint");
			addressNumber = resultset.getString("addressNumber");
			addressPortLabel = resultset.getString("addressPortLabel");
			addressNext = resultset.getString("addressNext");
			addressTownIdt = resultset.getInt("addressTownIdt");
			addressGenerationKey = resultset.getString("addressGenerationKey");
			
			town = selectOneTown(addressTownIdt);
			
			address = new Address(addressIdt, addressDeliveryPoint, addressNumber, addressPortLabel, addressNext, addressTownIdt, town, addressGenerationKey);
		} catch (SQLException e) {
			System.out.println("Erreur lors de la lecture des donnees de l'address : " + e);
			e.printStackTrace();
		}
		return address;
	}
}