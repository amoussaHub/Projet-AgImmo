package interfaces;

import static bdd.CivilityBdd.selectAllCivility;
import static bdd.InfoDetailBdd.selectOneInfoDetailDescription;
import static bdd.LegalRegimeBdd.selectAllLegalRegime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import model.Civility;
import model.InfoDetail;
import model.LegalRegime;
import resources.Cstes;

public interface GestionCbxDefault {





	/**
	 * Méthode permettant d'initialiser une combobox des civilités
	 * @param cbxCivility	[ComboBox<Civility>] 		: Combobox à initialiser
	 * @return				[ObservableList<Civility>] 	: Liste des civilités
	 */
	public static ObservableList<Civility> gestionCbxCivility(ComboBox<Civility> cbxCivility) {
		/** Declaration des variables **/
		ObservableList<Civility>	listeCivilite 		= FXCollections.observableArrayList();	
		int index 										= 0;
		/** Récupération de la liste des civilités dans la Bdd et remplissage de la combobox **/
		listeCivilite 									= selectAllCivility();
		cbxCivility.getItems().addAll(listeCivilite);
		/** Récupération de la valeur par défaut **/
		InfoDetail civilityDefault 						= selectOneInfoDetailDescription(Cstes.DEFAULT,Cstes.DEFAULTCIVILITE);
		if(civilityDefault!=null) {
			index = 0;
			for(Civility civilite : listeCivilite) {
				if(civilite.getCivilityIdt()==civilityDefault.getInfoDetailValueInt())break;
				index++;
			}
		}
		cbxCivility.getSelectionModel().select(index);
		return listeCivilite;
	}
	/**
	 * Méthode permettant d'initialiser une combobox des régimes juridiques
	 * @param cbxLandlordLegalRegime	[ComboBox<LegalRegime>] 		: Combobox à initialiser
	 * @return							[ObservableList<LegalRegime>] 	: Liste des régimes
	 */
	public static ObservableList<LegalRegime> gestionCbxLegalRegime(ComboBox<LegalRegime> cbxLandlordLegalRegime){
		/** Declaration des variables **/
		ObservableList<LegalRegime> listeLegalRegimes 	= FXCollections.observableArrayList();	
		int index 										= 0;
		/** Récupération de la liste des régimes dans la Bdd et remplissage de la combobox **/
		listeLegalRegimes 								= selectAllLegalRegime();
		cbxLandlordLegalRegime.getItems().addAll(listeLegalRegimes);
		/** Récupération de la valeur par défaut **/
		InfoDetail legalRegimeDefault 	= selectOneInfoDetailDescription(Cstes.DEFAULT,Cstes.DEFAULTLEGALREGIME);
		if(legalRegimeDefault!=null) {
			index = 0;
			for(LegalRegime legalRegime : listeLegalRegimes) {
				if(legalRegime.getLegalRegimeIdt()==legalRegimeDefault.getInfoDetailValueInt()) break;
				index++;
			}
		}		
		cbxLandlordLegalRegime.getSelectionModel().select(index);
		return listeLegalRegimes;
	}
}
