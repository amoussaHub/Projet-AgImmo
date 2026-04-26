package bdd;

import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;
import static bdd.AddressBdd.selectOneAdresse;
import static bdd.LegalRegimeBdd.selectOneLegalRegime;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Address;
import model.Company;
import model.LegalRegime;

public class CompanyBdd extends ConnexionBdd {
	/** Attributs de la classe **/
	private static String classeName	= CompanyBdd.class.getSimpleName();
	/**
	 * Méthode permettant de récupérer une liste des compagnies du groupe triée par nom
	 * @return		[ObservableList<Company>]
	 */	
	public static ObservableList<Company> selectAllCompany(){
		/** Initialisation des variables **/
		ObservableList<Company> listeDonnees 	= FXCollections.observableArrayList();
		Company company							= null;
		/** Initialisation de la requête **/
		String SQL		= "SELECT * FROM Company, Address, Town WHERE companyAddressIdt = AddressIdt AND addressTownIdt = townIdt ORDER BY companyName";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					company = map(resultSet);
					if(company!=null) listeDonnees.add(company);
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
	 * Méthode permettant de rechercher l'agence qui est le siège social
	 * @return		[Company]	: instance Company créée
	 */
	public static Company selectOneCompanyAdminSeat() {
		/** Initialisation des variables **/
		Company company							= null;
		/** Initialisation de la requête **/
		String SQL		= "SELECT * FROM Company WHERE companyAdminSeat = 1";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					company = map(resultSet);
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
		return company;
	}
	/**
	 * Méthode permettant de contrôler si un code Siret est déjàa utilisé 
	 * @param codeSiret		[String]	: Code Siret à contrôler
	 * @param companyIdt	[int]		: Identifiant de l'agence
	 * @return				[int]		
	 */
	public static int controleSiret(String codeSiret, int companyIdt) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requête **/
		String SQL		 = "";
		SQL				+= "SELECT COUNT(*) FROM Company";
		SQL				+= " WHERE companySiret LIKE ? AND companyIdt <> ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, codeSiret, companyIdt);
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
	public static Company selectOneCompany(int companyIdt) {
		/** Initialisation des variables **/
		Company company							= null;
		/** Initialisation de la requête **/
		String SQL		= "SELECT * FROM Company WHERE companyIdt = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, companyIdt);
				ResultSet resultSet   				= preparedStatement.executeQuery();
				while (resultSet.next()) {
					company = map(resultSet);
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
		return company;
	}
	/**
	 * Méthode permettant d'insérer une company
	 * @param company	[Company]	: Company à insérer
	 * @return			[int]		: nombre d'enregistrements supprimés
	 */
	public static int insertCompany(Company company) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requête **/
		String SQL		 = "INSERT INTO Company(companyName, companyAddressIdt, companyTelephone, companyEmail, companyWebSite, companyLegalRegime, companyCreationDate, companySiren, companySiret, companyAdminSeat, companyMaps)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, company.getCompanyName(), company.getCompanyAddressIdt(), company.getCompanyTelephone(), company.getCompanyEmail(), company.getCompanyWebSite(), company.getCompanyLegalRegime(),
																			company.getCompanyCreationDate(), company.getCompanySiren(), company.getCompanySiret(), selectOneCompanyAdminSeat().getCompanyIdt(), company.getCompanyMaps());
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
	 * Méthode permettant de modifier une company
	 * @param company	[Company]	: Company à modifier
	 * @return			[int]		: nombre d'enregistrements supprimés
	 */
	public static int updateCompany(Company company) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requête **/
		String SQL		 = "UPDATE Company SET companyName = ?, companyAddressIdt = ?, companyTelephone = ?, companyEmail = ?, companyWebSite = ?, companyLegalRegime = ?, companyCreationDate = ?, companySiren = ?, companySiret = ?, companyAdminSeat = ?, companyMaps = ? "
						 + "WHERE companyIdt = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, company.getCompanyName(), company.getCompanyAddressIdt(), company.getCompanyTelephone(), company.getCompanyEmail(), company.getCompanyWebSite(), company.getCompanyLegalRegime(),
																			company.getCompanyCreationDate(), company.getCompanySiren(), company.getCompanySiret(), selectOneCompanyAdminSeat().getCompanyIdt(), company.getCompanyMaps(), company.getCompanyIdt());
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
	 * Méthode permettant de supprimer une company
	 * @param company	[Company]	: Company à supprimer
	 * @return			[int]		: nombre d'enregistrements supprimés
	 */
	public static int deleteCompany(Company company) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requête **/
		String SQL		 = "DELETE FROM Company WHERE companyIdt = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, company.getCompanyIdt());
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
	 * Méthode permettant de créer un objet de type [Company] à partir d'un enregistrement de la base de données
	 * @param 	resultset	[Resultset]		: resultat de la requête
	 * @return				[Company]		: instance Company créée
	 */
	private static Company map(ResultSet resultset) {
		/** Attributs de la classe **/
		Company company 					= null;
		
		int companyIdt = 0;
		String companyName = null;
		int companyAddressIdt = 0;
		String companyTelephone = null;
		String companyEmail = null;
		String companyWebSite = null;
		int companyLegalRegime = 0;
		LocalDate companyCreationDate = null;
		String companySiren = null;
		String companySiret = null;
		Boolean companyAdminSeat = false;
		String companyMaps = null;
		Address address = null;
		LegalRegime legalRegime = null;
		
		try {
			companyIdt = resultset.getInt("companyIdt");
			companyName = resultset.getString("companyName");
			companyAddressIdt = resultset.getInt("companyAddressIdt");
			companyTelephone = resultset.getString("companyTelephone");
			companyEmail = resultset.getString("companyEmail");
			companyWebSite = resultset.getString("companyWebSite");
			companyLegalRegime = resultset.getInt("companyLegalRegime");
			companyCreationDate = resultset.getDate("companyCreationDate").toLocalDate();
			companySiren = resultset.getString("companySiren");
			companySiret = resultset.getString("companySiret");
			companyAdminSeat = resultset.getBoolean("companyAdminSeat");
			companyMaps = resultset.getString("companyMaps");
			
			address = selectOneAdresse(companyAddressIdt);
			legalRegime = selectOneLegalRegime(companyLegalRegime);
			
			company = new Company(companyIdt, companyName, companyAddressIdt, companyTelephone, companyEmail, companyWebSite, companyLegalRegime, companyCreationDate, companySiren, companySiret, companyAdminSeat, address, legalRegime, companyMaps);
			
			
		} catch (SQLException e) {
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			System.out.println("Classe  : " + classeName);
			System.out.println("Méthode : " + methodeName);
			System.out.println("Erreur lors de la lecture de la compagnie : " + e);
		}		
		return company;
	}
}