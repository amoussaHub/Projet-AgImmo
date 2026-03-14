package utilities;

import java.sql.SQLException;

/** ***********************************************************************************************
 * CLASSE : GestionExceptions
 * ************************************************************************************************
 * Cette classe contient les méthodes de gestion des Exceptions dans les différentes classes.
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 */
public class GestionExceptions {
	 /** ********************************************************************************
	  * Méthode permettant de gérer les Exceptions lors du traitement des requêtes SQL
	  * dans les classes bdd
	  * *********************************************************************************
	  * @param e			[SQLException]	: Exception levée
	  * @param SQL			[String]		: requête SQL en cause
	  * @param classeName	[String]		: Nom de la classe ayant levée l'exception
	  * @param methodeName	[String]		: Nom de la méthode ayant levée l'exception
	  */
	 public static void gestionDesExceptionsStates(SQLException e, String SQL, String classeName, String methodeName) {
		 System.out.println("Classe appelante 	: " + classeName);
		 System.out.println("Méthode 			: " + methodeName);
		 switch (e.getSQLState()) {
		 case "08S01": 
			 System.out.println("Impossible de se connecter à la base de données");
			 break;
		 case "28000":
			 System.out.println("Erreur sur le login ou le mot de passe de connexion ");
			 break;
		 case "42000":
			 System.out.println("La base de donnees n'existe pas ");
			 System.out.println(SQL);
			 break;
		 case "42S02":
			 System.out.println("Erreur dans la requete de selection ");
			 System.out.println(SQL);
			 break;
		 default:
			 System.out.println("Erreur dans le traitement SQL");
			 System.out.println(e.getSQLState());
			 System.out.println(SQL);
		 }
	 }
	 /** ********************************************************************************
	  * Méthode permettant de gérer les Exceptions lors du traitement de la méthode
	  * MAP (génération d'un objet à partir d'une enregistrement Bdd)
	  * *********************************************************************************
	  * @param e			[SQLException]	: Exception levée
	  * @param classeName	[String]		: Nom de la classe ayant levée l'exception
	  * @param methodeName	[String]		: Nom de la méthode ayant levée l'exception
	  */
	 public static void gestionDesExceptionsMap(SQLException e, String classeName, String methodeName) {
		 System.out.println("Classe appelante 	: " + classeName);
		 System.out.println("Méthode 			: " + methodeName);
		 System.out.println("Erreur dans le traitement de lecture d'un enregistrement de la base de données");
		 System.out.println(e.getSQLState());
	 }
	 /** ********************************************************************************
	  * Méthode permettant de gérer les Exceptions lors du traitement de connexion
	  * à la base de données
	  * *********************************************************************************
	  * @param exception	[String]		: libellé de l'exception levée
	  * @param e			[String]		: libellé de la trace e.getStackTrace()
	  * @param classeName	[String]		: Nom de la classe ayant levée l'exception
	  * @param methodeName	[String]		: Nom de la méthode ayant levée l'exception
	  */
	 public static void gestionDesExceptionsConnexion(String exception, String e, String classeName, String methodeName) {
		 System.out.println("Classe appelante 	: " + classeName);
		 System.out.println("Méthode 			: " + methodeName);
		 switch (exception) {
		 case "ClassNotFoundException": 
				System.out.println("Impossible de trouver le driver JDBC");
				System.out.println(e);
				break;
		 case "SQLException":		
				System.out.println("Impossible de se connecter a la base de données");
				System.out.println(e);
		 }
	 }
 }
