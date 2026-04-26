package bdd;

import static bdd.CivilityBdd.selectOneCivility;
import static utilities.GestionExceptions.gestionDesExceptionsStates;
import static utilities.UtilitiesJdbc.initialisationRequete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Civility;
import model.Contact;

/**
 * Cette classe contient les methodes CRUD de l'entite [Contact]. 
 * Elle herite de la Classe ConnexionBdd qui gere le driver et la connexion a la base de donnees
 * 
 * @author 		Alain CASARA
 * @version		1.0
 * Date			Juillet 2023
 */
public class ContactBdd extends ConnexionBdd {
	/** Attributs de la classe **/
	private static String classeName	= ContactBdd.class.getSimpleName();
	/**
	 * Methode permettant de recuperer une liste des contacts avec un filtre sur les deux types
	 * @param 	topLandlord	[int]	: top affichage des propriétaires
	 * @param 	topTenant	[int]   : top affichage des locataires
	 * @return				[ObservableList<Contact>]
	 */
	public static ObservableList<Contact> selectAllContactWithSelection(int topLandlord, int topTenant, String key){
		/** Initialisation des variables **/
		ObservableList<Contact> listeDonnees 	= FXCollections.observableArrayList();
		Contact contact							= null;	
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
	 * Description 	: Methode permettant de supprimer un contact dans la base de donnees avec une requete DELETE.
	 * @param	contact		[Contact]   	
	 * @return  			[int]		: nombre d'enregistrements supprimes, permet de controler que la suppression s'est bien effectuee	            
	 */
	public static int deleteContact(Contact contact) {
		/** Initialisation des variables **/
		int nbreEnreg = 0;
		/** Initialisation de la requete **/
		String SQL		= "DELETE FROM Contact WHERE ContactIdt = ?";
		/** Connexion a la base de donnees **/
		Connection connexion = trtConnexionBdd();
		if(connexion!=null) {
			/** Traitements SQL */
			try {
				PreparedStatement preparedStatement = initialisationRequete(connexion, SQL, false, contact.getContactIdt());
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
	 * Methode permettant de creer un objet de type [Contact] a partir d'un enregistrement de la base de donnees
	 * @param 	resultset	[Resultset]		: resultat de la requete
	 * @return				[Contact]		: instance Civility creee
	 */
	private static Contact map(ResultSet resultset) {
		/** Initialisation des variables **/
		Contact contact 						= null;
		try {
			int 		contactIdt			= resultset.getInt("contactIdt");
			String 		contactName			= resultset.getString("contactName");
			String 		contactFirstName	= resultset.getString("contactFirstName");
			String		contactMobile		= resultset.getString("contactMobile");
			String		contactEmail		= resultset.getString("");
			int 		contactCivilite		= resultset.getInt("contactCivilite");
			int			contactType			= resultset.getInt("contactType");
			String 		contactTypeLbl		= resultset.getString("contactType");
			String 		contactMessage		= resultset.getString("contactMessage");
			LocalDate	contactDateDemande	= resultset.getObject("contactDateDemande", LocalDate.class);
			
			Civility	civility			= CivilityBdd.selectOneCivility(contactCivilite);
			
			contact								= new Contact(contactIdt, contactName, contactFirstName, contactMobile, contactEmail, contactCivilite, civility, contactType, contactTypeLbl, contactMessage, contactDateDemande);
		} catch (SQLException e) {
			System.out.println("Erreur lors de la lecture du contact : " + e);
			e.printStackTrace();
		}		
		return contact;
	}
}
