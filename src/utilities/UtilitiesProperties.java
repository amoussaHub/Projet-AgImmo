package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/** ***********************************************************************************************
 * CLASSE : UtilitiesProperties
 * ************************************************************************************************
 * Cette classe contient les méthodes d'accès aux fichiers properties
 * @author 		CASARA alain
 * @since		1.0
 * @version		1.0
 */
public class UtilitiesProperties {

    public static FileInputStream fileInputStream;
    public static String property = "";

    /**
     * Méthode permettant de récupérer la valeur d'une propriété dans le fichier conf.properties
     * @param 	strProperty	[String] 	: propriété à rechercher dans le fichier 
     * @return				[String]	
     */
     public static String getConfigProperties(String strProperty) {

        Properties configProperties = new Properties();
        
        try {
            fileInputStream = new FileInputStream("src/resources/conf.properties");
            configProperties.load(fileInputStream);
            property = configProperties.getProperty(strProperty);
        } catch (FileNotFoundException ex) {
            System.out.println("Le fichier conf.properties est introuvable : " + ex);
        } catch (Exception e) {
        	System.out.println("Erreur sur la propriété " + strProperty + " : "+ e);
        }
        return property;
    }	
}