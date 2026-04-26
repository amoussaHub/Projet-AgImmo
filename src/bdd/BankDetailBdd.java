package bdd;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;
import static utilities.UtilitiesBlowFish.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.BankDetail;

/**
 * Cette classe contient les methodes CRUD de l'entite [BankDetail]. 
 * Elle herite de la Classe ConnexionBdd qui gere le driver et la connexion a la base de donnees
 * 
 * @author 		Alain CASARA
 * @version		1.0
 * Date			Juillet 2023
 */
public class BankDetailBdd extends ConnexionBdd {
	/** Attributs de la classe **/
	private static String classeName	= BankDetailBdd.class.getSimpleName();
	/**
	 * Description 	: Methode permettant de recuperer une coordonnee bancaire.
	 * @param	bankDetailIdt	[int]
	 * @return  				[BankDetail]	: Instance de BankDetail creee 			            
	 */
	public static BankDetail selectOneBankDetail(int bankDetailIdt) {
		/** Initialisation des variables **/
		BankDetail bankDetail = null;
		/** Initialisation de la requete **/
		String SQL		= "SELECT * FROM BankDetail WHERE bankDetailIdt = ?";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, bankDetailIdt);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					bankDetail = map(resultSet);
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
		return bankDetail;		
	}
	/**
	 * Description 	: Methode permettant de recuperer l'identifiant d'une coordonnee bancaire a partir de sa cle de generation
	 * @param	bankDetailGenerateKey	[String]
	 * @return  						[int]	: identifiant du detail bancaire		            
	 */
	public static int selectOneBankDetailByKey(String bankDetailGenerateKey) {
		/** Initialisation des variables **/
		int bankDetailIdt		= 0;
		/** Initialisation de la requete **/
		String SQL		= "SELECT * FROM BankDetail WHERE bankDetailGenerationKey = ?";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, bankDetailGenerateKey);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					bankDetailIdt = map(resultSet).getBankDetailIdt();
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
		return bankDetailIdt;		
	}	
	/**
	 * Description 	: Methode permettant d'inserer une coordonnee bancaire dans la base de donnees avec une requete INSERT.
	 * @param	bankDetail	[BankDetail]   	
	 * @return  			[int]			: nombre d'enregistrements inseres, permet de controler que l'insertion s'est bien effectuee	            
	 */
	public static int insertBankDetail(BankDetail bankDetail) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requete **/
		String SQL		= "INSERT INTO BankDetail(bankDetailCode, bankDetailWicketCode, bankDetailAccountNumber, bankDetailRibKey, bankDetailHolder, bankDetailIban, bankDetailDomiciliation, bankDetailBic, bankDetailGenerationKey)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, bankDetail.getBankDetailCode(), bankDetail.getBankDetailWicketCode(), bankDetail.getBankDetailAccountNumber(), bankDetail.getBankDetailRibKey(),
																			bankDetail.getBankDetailHolder(), bankDetail.getBankDetailIban(), bankDetail.getBankDetailDomiciliation(), bankDetail.getBankDetailBic(), bankDetail.getBankDetailGenerationKey());
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
	 * Description 	: Methode permettant de modifier une coordonnee bancaire dans la base de donnees avec une requete UPDATE.
	 * @param	bankDetail	[BankDetail]   	
	 * @return  			[int]			: nombre d'enregistrements modifies, permet de controler que la modification s'est bien effectuee	            
	 */
	public static int updateBankDetail(BankDetail bankDetail) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requete **/
		String SQL		= "UPDATE BankDetail SET bankDetailCode = ?, bankDetailWicketCode = ?, bankDetailAccountNumber = ?, bankDetailRibKey = ?, bankDetailHolder = ?, bankDetailIban = ?, "
						+ "bankDetailDomiciliation = ?, bankDetailBic = ?, bankDetailGenerationKey = ? WHERE bankDetailIdt = ?";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, bankDetail.getBankDetailCode(), bankDetail.getBankDetailWicketCode(), bankDetail.getBankDetailAccountNumber(), bankDetail.getBankDetailRibKey(),
																			bankDetail.getBankDetailHolder(), bankDetail.getBankDetailIban(), bankDetail.getBankDetailDomiciliation(), bankDetail.getBankDetailBic(), bankDetail.getBankDetailGenerationKey(), 
																			bankDetail.getBankDetailIdt());
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
	 * Description 	: Methode permettant de supprimer une coordonnee bancaire dans la base de donnees avec une requete DELETE.
	 * @param	bankDetailIdt	[int]   	
	 * @return  				[int]	: nombre d'enregistrements supprimes, permet de controler que la suppression s'est bien effectuee	            
	 */
	public static int deleteBankDetail(int bankDetailIdt) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requete **/
		String SQL		= "DELETE FROM BankDetail WHERE bankDetailIdt = ?";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, bankDetailIdt);
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
	 * Description 	: Methode permettant de d'instancier un objet [BankDetail] a partir d'un enregistrement
	 *                de la base de donnees.
	 * @param 	resultset	[Resultset]		: resultat de la requete
	 * @return				[BankDetail]	: instance de BankDetail creee
	 */
	private static BankDetail map(ResultSet resultset){
		/** Initialisation des variables **/
		BankDetail bankDetail			= null;
		try {
			/** Initialisation des variables **/
			int		bankDetailIdt 			= resultset.getInt("bankDetailIdt");
			String	bankDetailCode 			= resultset.getString("bankDetailCode");
			String	bankDetailWicketCode 	= resultset.getString("bankDetailWicketCode");
			String	bankDetailAccountNumber = resultset.getString("bankDetailAccountNumber");
			String	bankDetailRibKey 		= resultset.getString("bankDetailRibKey");
			String	bankDetailHolder 		= resultset.getString("bankDetailHolder");
			String	bankDetailIban 			= resultset.getString("bankDetailIban");
			String	bankDetailDomiciliation = resultset.getString("bankDetailDomiciliation");
			String	bankDetailBic 			= resultset.getString("bankDetailBic");
			String	bankDetailGenerationKey = resultset.getString("bankDetailGenerationKey");
			
			/** Création de l'agent **/
			bankDetail = new BankDetail(bankDetailIdt, bankDetailCode, bankDetailWicketCode, bankDetailAccountNumber, bankDetailRibKey, bankDetailHolder, 
						 bankDetailIban, bankDetailDomiciliation, bankDetailBic, bankDetailGenerationKey);
		} catch (SQLException e) {
			System.out.println("Erreur lors de la lecture des donnees des donnees bancaire : " + e);
			e.printStackTrace();
		}
		return bankDetail;
	}
}
