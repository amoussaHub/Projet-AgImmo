package bdd;
import static bdd.InfoEnteteBdd.selectOneInfoEntete;
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
import model.InfoDetail;
import model.InfoEntete;

/** ***********************************************************************************************
 * CLASSE : InfoDetailBdd
 * ************************************************************************************************
 * Cette classe contient les méthodes CRUD d'accès à la table [InfoDetail] de la base de données.
 * Elle hérite de la Classe ConnexionBdd contenant les données de connexion à la base de données.  
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 * @see			model.InfoDetail
 */
public class InfoDetailBdd extends ConnexionBdd {
	/** 
	 * Attributs de la classe 
	 * l'attribut classeName sert à fournir le nom de la classe dès qu'une Exception se produit
	 */
	private static String classeName	= InfoDetailBdd.class.getSimpleName();
	/** *********************************************************************************
	 * Méthode permettant de récupérer une liste du détail d'une information 
	 * à partir de la clé de l'entête
	 * **********************************************************************************
	 * @param 	key	[String]						: clé de recherche
	 * @return		[ObservableList<InfoDetail>]	: Collection d'instances créée
	 */
	public static ObservableList<InfoDetail> selectAllInfoDetail(String key){
		/** Déclaration des variables **/
		ObservableList<InfoDetail> listeDonnees = FXCollections.observableArrayList();
		InfoDetail infoDetail 				  	= null;
		/** Initialisation de la requête **/
		String SQL		= "SELECT * from InfoDetail WHERE infoEnteteIdt =" + key;
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			Statement statement  = connexion.createStatement();
			ResultSet resultSet  = statement.executeQuery(SQL);
			while (resultSet.next()) {
				infoDetail = map(resultSet);
				if(infoDetail!=null) listeDonnees.add(infoDetail);
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
	 * Méthode permettant de récupérer une liste du détail d'une information 
	 * à partir du nom de la combobox qui va contenir les données
	 * **********************************************************************************
	 * @param 	cbxName	[String]						: nom de la combobox
	 * @return			[ObservableList<InfoDetail>]	: Collection d'instances créée
	 */
	public static ObservableList<InfoDetail> selectAllInfoDetailForCbx(String cbxName){
		/** Déclaration des variables **/
		ObservableList<InfoDetail> listeDonnees = FXCollections.observableArrayList();
		InfoDetail infoDetail 				  	= null;
		/** Initialisation de la requête **/
		String SQL		= "";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		/** Traitements SQL */
		try {
			Statement statement  = connexion.createStatement();
			ResultSet resultSet  = statement.executeQuery(SQL);
			while (resultSet.next()) {
				infoDetail = map(resultSet);
				if(infoDetail!=null) listeDonnees.add(infoDetail);
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
	 * Methode permettant de recuperer une liste du detail d'une information 
	 * à partir de la cle de l'entête et de la description
	 * **********************************************************************************
	 * @param key			[String]	: Cle de recherche
	 * @param description	[String]	: Description de recherche
	 * @return				[InfoDetail]: Instance créée
	 */
	public static InfoDetail selectOneInfoDetailDescription(String key, String description){
		/** Déclaration des variables **/
		InfoDetail infoDetail 				  	= null;
		/** Initialisation de la requete **/
		String SQL		= "SELECT InfoDetail.* FROM InfoDetail,InfoEntete WHERE InfoDetail.InfoEnteteIdt = InfoEntete.InfoEnteteIdt AND InfoEnteteKey = '" + key + "' AND infoDetailDescription = '" + description + "'";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				Statement statement  = connexion.createStatement();
				ResultSet resultSet  = statement.executeQuery(SQL);
				while (resultSet.next()) {
					infoDetail = map(resultSet);
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
		return infoDetail;			
	}
	/**
	 * Méthode permettant d'insérer un détail
	 * @param infoDetail	[InfoDetail]	: InfoDetail à insérer
	 * @return			[int]		: nombre d'enregistrements supprimés  
	 */
	public static int insertInfoDetail(InfoDetail infoDetail) {
		/** Déclaration des variables **/
		int nbrEnregistrement = 0;
		/** Initialisation de la requête **/
		String SQL		 = 	"INSERT INTO InfoDetail(infoEnteteIdt, infoDetailKeyIdt, infoDetailDescription, infoDetailLbl, infoDetailLbc, infoDetailValueInt, infoDetailValueDouble, infoDetailTri, infoDetailDefault)"
							+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		try {
			PreparedStatement preparedStatement = 	initialisationRequete(connexion, SQL, false, infoDetail.getInfoEnteteIdt(), infoDetail.getInfoDetailKeyIdt(), infoDetail.getInfoDetailDescription(), infoDetail.getInfoDetailLbl(), infoDetail.getInfoDetailLbc(), 
													infoDetail.getInfoDetailValueInt(), infoDetail.getInfoDetailValueDouble(), infoDetail.getInfoDetailTri(), infoDetail.isInfoDetailDefault());
			nbrEnregistrement					= 	preparedStatement.executeUpdate();
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
	 * Méthode permettant de modifier un détail
	 * @param infoDetail	[InfoDetail]	: InfoDetail à modifier
	 * @return			[int]		: nombre d'enregistrements supprimés  
	 */
	public static int updateInfoDetail(InfoDetail infoDetail) {
		/** Déclaration des variables **/
		int nbrEnregistrement = 0;
		/** Initialisation de la requête **/
		String SQL		 = 	"UPDATE InfoDetail SET infoEnteteIdt = ?, infoDetailKeyIdt = ?, infoDetailDescription = ?, infoDetailLbl = ?, infoDetailLbc = ?, infoDetailValueInt = ?, infoDetailValueDouble = ?, infoDetailTri = ?, infoDetailDefault = ? WHERE infoDetailIdt = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		try {
			PreparedStatement preparedStatement = 	initialisationRequete(connexion, SQL, false, infoDetail.getInfoEnteteIdt(), infoDetail.getInfoDetailKeyIdt(), infoDetail.getInfoDetailDescription(), infoDetail.getInfoDetailLbl(), infoDetail.getInfoDetailLbc(), 
													infoDetail.getInfoDetailValueInt(), infoDetail.getInfoDetailValueDouble(), infoDetail.getInfoDetailTri(), infoDetail.isInfoDetailDefault(), infoDetail.getInfoDetailIdt());
			nbrEnregistrement					= 	preparedStatement.executeUpdate();
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
	 * Méthode permettant de supprimer un détail
	 * @param infoDetail	[InfoDetail]	: InfoDetail à supprimer
	 * @return			[int]		: nombre d'enregistrements supprimés  
	 */
	public static int deleteInfoDetail(InfoDetail infoDetail) {
		/** Déclaration des variables **/
		int nbrEnregistrement = 0;
		/** Initialisation de la requête **/
		String SQL		 = 	"DELETE FROM InfoDetail WHERE infoDetailIdt = ?";
		/** Connexion à la base de données **/
		Connection connexion = trtConnexionBdd();
		try {
			PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, infoDetail.getInfoDetailIdt());
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
	
	/** *********************************************************************************
	 * Méthode permettant de créer un objet de type [InfoDetail] à partir 
	 * d'un enregistrement de la base de données
	 * **********************************************************************************
	 * @param 	resultset	[Resultset]		: resultat de la requête
	 * @return				[InfoDetail]	: instance InfoDetail créée
	 */
	private static InfoDetail map(ResultSet resultset) {
		/** Déclaration du détail **/
		InfoDetail infoDetail 					= null;
		try {
			
			int infoDetailIdt       = resultset.getInt("infoDetailIdt");
	        int infoEnteteIdt       = resultset.getInt("infoEnteteIdt");
	        int infoDetailKeyIdt    = resultset.getInt("infoDetailKeyIdt");
	        String infoDetailDescription = resultset.getString("infoDetailDescription");
	        String infoDetailLbl    = resultset.getString("infoDetailLbl");
	        String infoDetailLbc    = resultset.getString("infoDetailLbc");
	        int infoDetailValueInt  = resultset.getInt("infoDetailValueInt");
	        double infoDetailValueDouble = resultset.getDouble("infoDetailValueDouble");
	        int infoDetailTri       = resultset.getInt("infoDetailTri");
	        boolean infoDetailDefault = resultset.getBoolean("infoDetailDefault");


	        InfoEntete infoEntete = InfoEnteteBdd.selectOneInfoEntete(infoEnteteIdt);
	        
	        infoDetail = new InfoDetail(
	                infoDetailIdt, infoEnteteIdt, infoDetailKeyIdt, infoDetailDescription,
	                infoDetailLbl, infoDetailLbc, infoDetailValueInt, infoDetailValueDouble,
	                infoDetailTri, infoDetailDefault, infoEntete);
			
		} catch (SQLException e) {
			/**
			 * L'utilisation de Class.getEnclosingMethod() de la classe Dummy (classe interne anonyme) renvoie un objet 
			 * java.lang.reflect.Method qui contient des informations sur la méthode immédiatement englobante.
			 */
			class Dummy {};
			String methodeName 	= Dummy.class.getEnclosingMethod().getName();
			gestionDesExceptionsMap(e, classeName, methodeName);
		}		
		return infoDetail;
	}
}
