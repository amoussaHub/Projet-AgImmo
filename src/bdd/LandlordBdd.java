package bdd;
import static bdd.AddressBdd.selectOneAdresse;
import static bdd.BankDetailBdd.selectOneBankDetail;
import static bdd.LegalRegimeBdd.selectOneLegalRegime;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesBlowFish.decrypt;
import static utilities.UtilitiesBlowFish.encrypt;
import static utilities.UtilitiesJdbc.initialisationRequete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Address;
import model.BankDetail;
import model.Civility;
import model.Landlord;
import model.LegalRegime;

/**
 * Cette classe contient les methodes CRUD de l'entite [Landlord]. 
 * Elle herite de la Classe ConnexionBdd qui gere le driver et la connexion a la base de donnees
 * 
 * @author 		Alain CASARA
 * @version		1.0
 * Date			Juillet 2023
 */
public class LandlordBdd extends ConnexionBdd {
	/** Attributs de la classe **/
	private static String classeName	= LandlordBdd.class.getSimpleName();
	/**
	 * Description 	: Methode permettant de recuperer la liste des proprietaires triee par nom et prenom.
	 * @return  	[ObservableList<Landlord>]		            
	 */
	public static ObservableList<Landlord> selectAllLandlord(){
		/** Initialisation des variables **/
		ObservableList<Landlord> listeDonnees 	= FXCollections.observableArrayList();
		Landlord landlord						= null;
		/** Initialisation de la requete **/
		String SQL		= "SELECT * FROM Landlord, Address, Town WHERE landlordAddressIdt = addressIdt AND addressTownIdt = townIdt ORDER BY landlordName, landlordFirstName";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					landlord = map(resultSet);
					if(landlord!=null) listeDonnees.add(landlord);
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
	 * Description 	: Methode permettant de recuperer la liste des proprietaires triee par nom et prenom avec 
	 * 				  une selection sur le nom ou l'adresse ou la ville
	 * @param   selection 	[String]
	 * @return  			[ObservableList<Landlord>]		            
	 */
	public static ObservableList<Landlord> selectAllLandlordWithSelection(String selection){
		/** Initialisation des variables **/
		ObservableList<Landlord> listeDonnees 	= FXCollections.observableArrayList();
		Landlord landlord						= null;
		selection								= "%" + selection + "%";
		/** Initialisation de la requete **/
		String SQL		= "";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
		}
		return listeDonnees;			
	}	
	/**
	 * Description 	: Methode permettant d'inserer un proprietaire dans la base de donnees avec une requete INSERT.
	 * @param	landlord	[Landlord]   	
	 * @return  			[int]		: nombre d'enregistrements inseres, permet de controler que l'insertion s'est bien effectuee	            
	 */
	public static int insertLandlord(Landlord landlord) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requete **/
		String SQL		= "INSERT INTO Landlord(landlordName, landlordFirstName, landlordMobile, landlordPhone, landlordEmail, landlordCivility, landlordLegalRegimeIdt, landlordAddressIdt, landlordBankDetailIdt, landlordPrivateData)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			if(connexion!=null) {
				/** Traitements SQL */
				try {
					PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, landlord.getPersonName(), landlord.getPersonFirstName(), landlord.getPersonMobile(), landlord.getPersonPhone(), landlord.getPersonEmail(),
														  landlord.getPersonCivility(), landlord.getLandlordLegalRegimeIdt(), landlord.getLandlordAddressIdt(), landlord.getLandlordBankDetailIdt(), landlord.getLandlordPrivateData());
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
		}		
		return nbreEnreg;		
	}
	/**
	 * Description 	: Methode permettant de modifier un proprietaire dans la base de donnees avec une requete UPDATE.
	 * @param	landlord	[Landlord]   	
	 * @return  			[int]		: nombre d'enregistrements modifies, permet de controler que la modification s'est bien effectuee	            
	 */
	public static int updateLandlord(Landlord landlord) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requete **/
		String SQL		= "UPDATE Landlord SET landlordName = ?, landlordFirstName = ?, landlordMobile = ?, landlordPhone = ?, landlordEmail = ?, landlordCivility = ?, landlordLegalRegimeIdt = ?, landlordAddressIdt = ?, landlordBankDetailIdt = ?, landlordPrivateData = ?"
						+ "WHERE landlordIdt = ?";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, landlord.getPersonName(), landlord.getPersonFirstName(), landlord.getPersonMobile(), landlord.getPersonPhone(), landlord.getPersonEmail(),
													  landlord.getPersonCivility(), landlord.getLandlordLegalRegimeIdt(), landlord.getLandlordAddressIdt(), landlord.getLandlordBankDetailIdt(), landlord.getLandlordPrivateData(), landlord.getPersonIdt());
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
	 * Description 	: Methode permettant de supprimer un proprietaire dans la base de donnees avec une requete DELETE.
	 * @param	landlord	[Landlord]   	
	 * @return  			[int]		: nombre d'enregistrements supprimes, permet de controler que la suppression s'est bien effectuee	            
	 */
	public static int deleteLandlord(Landlord landlord) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requete **/
		String SQL		= "DELETE FROM Landlord WHERE landlordIdt = ?";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, landlord.getPersonIdt());
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
	 * Description 	: Methode permettant de d'instancier un objet [Landlord] a partir d'un enregistrement
	 *                de la base de donnees.
	 * @param 	resultset	[Resultset]	: resultat de la requete
	 * @return				[Landlord]	: instance de Landlord creee
	 */
	private static Landlord map(ResultSet resultset) throws SQLException {
		/** Initialisation des variables **/
		Landlord landlord							= null;
		try {
/** Initialisation des variables **/
			
			int 		personIdt        		= resultset.getInt("landlordIdt");
	        String 		personName       		= resultset.getString("landlordName");
	        String 		personFirstName  		= resultset.getString("landlordFirstName");
	        String 		personMobile     		= resultset.getString("landlordMobile");
	        String 		personPhone      		= resultset.getString("landlordPhone");
	        String 		personEmail      		= resultset.getString("landlordEmail");
	        int 		personCivility   		= resultset.getInt("landlordCivility");
	        
	        int 		landlordLegalRegimeIdt	= resultset.getInt("landlordLegalRegimeIdt");
	    	int 		landlordAddressIdt		= resultset.getInt("landlordAddressIdt");
	    	int 		landlordBankDetailIdt	= resultset.getInt("landlordBankDetailIdt");
	    	Boolean		landlordPrivateData		= resultset.getBoolean("landlordPrivateData");
	    	
	    	Civility	civility				= CivilityBdd.selectOneCivility(personCivility);
	    	LegalRegime	legalRegime				= LegalRegimeBdd.selectOneLegalRegime(landlordLegalRegimeIdt);
	    	Address		address					= AddressBdd.selectOneAdresse(landlordAddressIdt);
	    	BankDetail	bankDetail				= BankDetailBdd.selectOneBankDetail(landlordBankDetailIdt);
	    	
	    	/** Création du Landlord **/
	    	landlord = 	new Landlord(personIdt, personName, personFirstName, personMobile, personPhone, personEmail, personCivility, civility, landlordLegalRegimeIdt, landlordAddressIdt, 
	    				landlordBankDetailIdt, legalRegime, address, bankDetail, landlordPrivateData);
	    	
		} catch (SQLException e) {
			System.out.println("Erreur lors de la lecture des donnees du proprietaire : " + e);
			e.printStackTrace();
		}
		return landlord;
	}
}
