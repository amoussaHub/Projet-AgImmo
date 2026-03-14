package model;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Cette classe contient les attributs, constructeur, Accesseurs et méthodes permettant
 * d'instancier des objets de Type [LoaderFXML].
 * 
 * @author 	Alain CASARA
 * @version	1.0
 */
public class LoaderFXML {
	/**
	 * loader			: FXMLLoader pour la création de la fenêtre
	 * loaderLocation	: Chemin d'accès dans le projet pour le fichier FXML
	 * sceneStyleSheet	: Chemin d'accès dans le projet pour la feuille de style racine
	 * stageTitle		: Titre de la fenêtre (information située sur le bandeau )
	 * stageIcone		: Chemin d'accès dans le projet pour l'icône de la fenêtre
	 * loaderMsgErreur	: Message d'erreur en cas de problème sur l'ouverture de la fenêtre 
	 */
	private FXMLLoader 	loader;
	private String     	loaderLocation;
	private String     	sceneStyleSheet;
	private String		stageTitle;
	private String		stageIcone;
	private String 		loaderMsgErreur;
	/**
	 * @param newLoaderLocation
	 * @param newSceneStyleSheet
	 * @param newStageTitle
	 * @param newStageIcone
	 * @param newLoaderMsgErreur
	 */
	public LoaderFXML(String newLoaderLocation, String newSceneStyleSheet, String newStageTitle,
			String newStageIcone, String newLoaderMsgErreur) {
		super();
		this.loader				= new FXMLLoader();
		this.loaderLocation 	= newLoaderLocation;
		this.sceneStyleSheet 	= newSceneStyleSheet;
		this.stageTitle 		= newStageTitle;
		this.stageIcone 		= newStageIcone;
		this.loaderMsgErreur	= newLoaderMsgErreur;
	}
	/**
	 * 
	 * @param newFenetre
	 */
	public LoaderFXML(Fenetres newFenetre) {
		super();
		this.loader				= new FXMLLoader();
		this.loaderLocation 	= newFenetre.getFenetrelocation();
		this.sceneStyleSheet 	= newFenetre.getFenetreStyleSheet();
		this.stageTitle 		= newFenetre.getFenetreStageTitle();
		this.stageIcone 		= newFenetre.getFenetreStageIcone();
		this.loaderMsgErreur	= newFenetre.getFenetreLoaderMsgErreur();
	}
	/**
	 * @return the loader
	 */
	public FXMLLoader getLoader() {
		return loader;
	}
	/**
	 * @return the loaderLocation
	 */
	public String getLoaderLocation() {
		return loaderLocation;
	}
	/**
	 * @return the sceneStyleSheet
	 */
	public String getSceneStyleSheet() {
		return sceneStyleSheet;
	}
	/**
	 * @return the stageTitle
	 */
	public String getStageTitle() {
		return stageTitle;
	}
	/**
	 * @return the stageIcone
	 */
	public String getStageIcone() {
		return stageIcone;
	}
	/**
	 * @return the loaderMsgErreur
	 */
	public String getLoaderMsgErreur() {
		return loaderMsgErreur;
	}
	/**
	 * Méthode permettant de créer un stage avec un BorderPane
	 * @return		[Stage]
	 */
	public Stage createLoaderBorderPane() {
		loader.setLocation(getClass().getResource(loaderLocation));
		BorderPane root;
		try {
			root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(sceneStyleSheet).toExternalForm());
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle(stageTitle);
			primaryStage.getIcons().add(new Image(stageIcone));
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			return primaryStage;
		} catch (IOException e) {
			System.out.println(loaderMsgErreur + " : " + e);
		}
		return null;
	}
	/**
	 * Méthode permettant de créer un stage avec un BorderPane
	 * @return		[Stage]
	 */
	public BorderPane createLoaderBorderPaneAdmin() {
		loader.setLocation(getClass().getResource(loaderLocation));
		BorderPane root;
		try {
			root = (BorderPane) loader.load();
			return root;
		} catch (IOException e) {
			System.out.println(loaderMsgErreur + " : " + e);
		}
		return null;
	}
	/**
	 * Méthode permettant de créer un stage avec un AnchorPane
	 * @return		[Stage]
	 */
	public Stage createLoaderAnchorPane() {
		loader.setLocation(getClass().getResource(loaderLocation));
		AnchorPane root;
		try {
			root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(sceneStyleSheet).toExternalForm());
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle(stageTitle);
			primaryStage.getIcons().add(new Image(stageIcone));
			primaryStage.setResizable(false);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			return primaryStage;
		} catch (IOException e) {
			System.out.println(loaderMsgErreur + " : " + e);
		}
		return null;
	}	
}