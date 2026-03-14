package interfaces;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import model.InfoDetail;
import static bdd.InfoDetailBdd.selectAllInfoDetailForCbx;

public interface GestionCbxInfos {

	
	/**
	 * Méthode permettant d'initialiser une combobox avec des enregistrements de la table [InfoDetail]
	 * @param 	cbxCivilite		ComboBox<InfoDetail> 	: Combobox devant être initialisée
	 * @param 	cbxName			[String]				: nom de la combobox
	 * @return
	 */
	public static ObservableList<InfoDetail> initCbx(ComboBox<InfoDetail> comboBox, String cbxName){
		/** Initialisation des variables **/
		ObservableList<InfoDetail> listeDonnees = FXCollections.observableArrayList();
		/** Raz de la combobox **/
		comboBox.getItems().clear();
		/** Récupération des données Civilite dans la table [InfoDetail]**/
		listeDonnees							= selectAllInfoDetailForCbx(cbxName);
		comboBox.getItems().addAll(listeDonnees);
		/** Contrôle que l'une des valeurs est celle par défaut **/
		int index 								= 0;
		boolean indexFind						= false;
		for(InfoDetail infoDetail : listeDonnees) {
			if(infoDetail.isInfoDetailDefault()) {
				indexFind = true;
				break;
			}
			index++;
		}
		if(indexFind) {
			comboBox.getSelectionModel().select(index);
		}
		/** **/
		return listeDonnees;
	}
	/**
	 * Méthode permettant d'initialiser une combobox avec des enregistrements de la table [InfoDetail]
	 * puis de la positionner avec une enregistrement
	 * @param 	cbxCivilite		ComboBox<InfoDetail> 	: Combobox devant être initialisée
	 * @param 	cbxName			[String]				: nom de la combobox
	 * @param 	detailKeyIdt	[int]					: identifiant permettant de se positionner dans la combobox
	 * @return
	 */
	public static ObservableList<InfoDetail> initModifCbx(ComboBox<InfoDetail> comboBox, String cbxName, int detailKeyIdt){
		/** Initialisation des variables **/
		ObservableList<InfoDetail> listeDonnees = FXCollections.observableArrayList();
		/** Raz de la combobox **/
		comboBox.getItems().clear();
		/** Récupération des données Civilite dans la table [InfoDetail]**/
		listeDonnees							= selectAllInfoDetailForCbx(cbxName);
		comboBox.getItems().addAll(listeDonnees);
		/** Contrôle que l'une des valeurs est celle par défaut **/
		int index 								= 0;
		boolean indexFind						= false;
		for(InfoDetail infoDetail : listeDonnees) {
			if(infoDetail.getInfoDetailKeyIdt()==detailKeyIdt) {
				indexFind = true;
				break;
			}
			index++;
		}
		if(indexFind) {
			comboBox.getSelectionModel().select(index);
		}
		/** **/
		return listeDonnees;
	}
}
