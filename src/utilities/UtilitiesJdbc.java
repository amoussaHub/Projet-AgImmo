package utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/** ***********************************************************************************************
 * CLASSE : UtilitiesJdbc
 * ************************************************************************************************
 * Cette classe contient les méthodes de génération des preparedStatement
 * @author 		CASARA alain
 * @since		2.0
 * @version		1.0
 */
public class UtilitiesJdbc {
	/**
	 * Méthode permettant d'initialiser la requête préparée
	 * @param connexion				[Connection]	: Objet connexion à la base de données
	 * @param sql					[String]		: Requête SQL au format preparedStatement
	 * @param returnGeneratedKey	[boolean]		: Renvoyer l’indicateur de clés générées
	 * 												  Une façon de récupérer les clés après la génération automatique consiste à passer Statement.RETURN_GENERATED_KEYS 
	 * 												  à la méthode prepareStatement().
	 * 												  Après avoir préparé et exécuté la requête, il est possible d'appeler la méthode getGeneratedKeys() sur PreparedStatement 
	 * 												  pour obtenir l’id.
	 * 												  Exemple :
	 * 															try (ResultSet keys = statement.getGeneratedKeys()) {
     *																assertThat(keys.next()).isTrue();
     * 																assertThat(keys.getLong(1)).isGreaterThanOrEqualTo(1);
	 *															}	
	 *												  Il faut d’abord appeler la méthode next() pour déplacer le curseur de résultat. 
	 *												  Puis ensuite, la méthode getLong() pour obtenir la première colonne et la convertir en long.	
	 * @param objets				[Object...]		: les points de suspension permettent d'appeler la méthode quelque soit le nombre d'arguments
	 * @return						[PreparedStatement]
	 * @throws SQLException
	 */
	public static PreparedStatement initialisationRequete (Connection connexion, String sql, boolean returnGeneratedKey, Object... objets) throws SQLException {
		/** Création de l'instance de PreparedStatement **/
		PreparedStatement preparedStatement = connexion.prepareStatement(sql, returnGeneratedKey ?Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		/** Lecture des paramètres passés à la méthode et assignation à l'instance **/
		for(int i = 0; i < objets.length; i++) {
			preparedStatement.setObject(i+1, objets[i]);
		}
		/** Renvoie de l'instance correctement initialisée**/
		return preparedStatement;
	}

}
