/**
 * Classe	: Cstes
 * Cette classe contient les constantes de l'application
 * 
 * @author 	Alain CASARA
 * @version	1.0
 */
package resources;

public class Cstes {
	/** Retour chariot pour les messages d'erreur **/
	public final static String 	CR 						= "\n";
	
	/** Constantes étiquettes du fichier conf.properties **/
	public final static String	JDBCDRIVER				= "jdbc.driver.class";
	public final static String	JDBCURL					= "jdbc.url";
	public final static String	JDBCLOGIN				= "jdbc.login";
	public final static String	JDBCPWD					= "jdbc.pwd";
	
	/** Entetes informations **/
	public final static String 	DEFAULT					= "ValeursDefaut";
	public final static String 	DOSSIERS				= "Dossiers";
	public final static String 	CHIFFREMENT 			= "Chiffrement";

	/** Valeurs par défaut **/
	public final static int 	DEFAULTCIVILITE			= 1;
	public final static String 	NBRERREURSLOGIN 		= "NbreErreursConnexions";
	public final static String 	DUREEBLQLOGIN 			= "DureeBlqLogin";
	public final static String 	DEFAULTLEGALREGIME 		="legalRegime";
	
	/** Dossiers **/
	public final static String  DOSSIERPORTRAITS		= "Portraits";
	public final static String  DOSSIERFONTS			= "Fonts";
	public final static String  DOSSIERMODELEWORD		= "ModelesWord";
	public final static String  DOSSIERDOCUMENTS		= "Documents";
	public static final String  CARTESVISITES			= "CartesDeVisite";

	/** Fenêtres **/
	public final static String  MODIFICATIONAGENT		= "AgentDefinition";
	public final static String  TABLEAUDEBORD			= "Dashboard";
	public final static String	LOGIN					= "Login";
	public final static String  ADMINISTRATION			= "Administration";
	public final static String  CIVILITY				= "CivilityAdmin";
	public final static String  DETAILINFO				= "InfoDetailAdmin";
	public final static String  ENTETEINFO				= "InfoEnteteAdmin";
	public final static String  TYPEAGENT				= "TypeAgentAdmin";
	public final static String 	AGENCESMANAGEMENT 		= "CompanyManagement";
	public final static String 	AGENCEDEFINITION 		= "CompanyDefinition";

	/** Version de l'application **/
	public final static String  VERSIONAPPLICATION		= "V.02";
	
	/** Valeurs chiffrement **/
	public final static String KEYBF 					= "BlowFish";
	public final static String TRTCHIFFREMENTAGENT 		= "chiffrementAgent";
	
	/** Templates **/
	public final static String WEBMAPS 					= "<!DOCTYPE html><html lang=\"fr\"><body ><iframe src=\"carte\" width=\"495\" height=\"500\"style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\"referrerpolicy=\"no-referrer-when-downgrade\"></iframe></body></html>";
	
}
