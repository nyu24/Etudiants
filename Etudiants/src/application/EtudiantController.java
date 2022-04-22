package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class EtudiantController implements Initializable{

    @FXML
    private TableColumn<Etudiant, Double> ageCol;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TableView<Etudiant> etudiantTable;

    @FXML
    private TextField txtAge;

    @FXML
    private Button btnEffacer;

    @FXML
    private ComboBox<String> cboDepartement;

    @FXML
    private Button btnRecommencer;

    @FXML
    private Button btnModifier;

    @FXML
    private TableColumn<Etudiant, String> nomCol;

    @FXML
    private TableColumn<Etudiant, String> departementCol;

    @FXML
    private TableColumn<Etudiant, String> prenomCol;

    @FXML
    private Button btnAjouter;

    @FXML
    private TextField txtNom;
    
    //liste pour les departements - cette liste permettre de donner les valeurs du comboBox
    private ObservableList<String> list = (ObservableList<String>) FXCollections.observableArrayList("Sciences","Droit","Medecine");
    
    //placer les etudiants dans une observable list
    public ObservableList<Etudiant> etudiantData = FXCollections.observableArrayList();
    
    //creer une methode pour acceder a la liste des etudiants
    public ObservableList<Etudiant> getetudiantData(){
    	return etudiantData;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
				cboDepartement.setItems(list);
				//attribuer les valeurs aux colonnes du tableView
				prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
				nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
				departementCol.setCellValueFactory(new PropertyValueFactory<>("departement"));
				ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
				etudiantTable.setItems(etudiantData);
				
				btnModifier.setDisable(true);
				btnEffacer.setDisable(true);
				btnRecommencer.setDisable(true);
				
				//initialise par null
				showEtudiants(null);
				//Mettre a jour l'affichage d'un etudiant selectionne
				etudiantTable.getSelectionModel().selectedItemProperty().addListener((
						observable, oldValue, newValue)-> showEtudiants(newValue));
	}
	
	//ajouter un etudiant
	@FXML
	void ajouter() {
		Etudiant tmp = new Etudiant();
		tmp = new Etudiant();
		tmp.setNom(txtNom.getText());
		tmp.setPrenom(txtPrenom.getText());
		tmp.setAge(Double.parseDouble(txtAge.getText()));
		tmp.setDepartement(cboDepartement.getValue());
		etudiantData.add(tmp);
		clearFields();
	}
	
	//effacer les donnees
	@FXML
	void clearFields() {
		cboDepartement.setValue(null);
		txtNom.setText("");
		txtPrenom.setText("");
		txtAge.setText("");
	}
	
	//affichier les donnees
	@FXML
	void showEtudiants(Etudiant etudiant) {
		if(etudiant != null) {
			cboDepartement.setValue(etudiant.getDepartement());
			txtNom.setText(etudiant.getNom());
			txtPrenom.setText(etudiant.getPrenom());
			txtAge.setText(Double.toString(etudiant.getAge()));
			btnModifier.setDisable(false);
			btnEffacer.setDisable(false);
			btnRecommencer.setDisable(false);
		} else {
			clearFields();
		}
	}
	
	//Mise a jour d'un etudiant
	@FXML
    public void updateEtudiant() {
		Etudiant etudiant = etudiantTable.getSelectionModel().getSelectedItem();
		
		etudiant.setNom(txtNom.getText());
		etudiant.setPrenom(txtPrenom.getText());
		etudiant.setAge(Double.parseDouble(txtAge.getText()));
		etudiant.setDepartement(cboDepartement.getValue());
		etudiantTable.refresh();
	}
	
	//effacer un etudiant
	@FXML
	public void deleteEtudiant() {
		int selectedIndex = etudiantTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0) {
			etudiantTable.getItems().remove(selectedIndex);
		}
	}
	
	//Affichier les statistiques
	@FXML
	void handleStats() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("AgeStat.FXML"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			AgeStat agestat = loader.getController();
			agestat.SetStats(etudiantData);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Statistiques");
			stage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//affichier les statistiques diagcircularie
	@FXML
	void handleStat2() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("AgeStat.fxml"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			DiagCirculaire diag = loader.getController();
			diag.SetStat2(etudiantData);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Diagramme Circulaire");
			stage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	} //12:14 unfinished and theres an error
	//sauvegarde de donnees
		//recuperer le chemin (path) des fichiers si ca existe
	public File getEtudiantFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);
		if(filePath != null) {
			return new File(filePath);
		}else {
			return null;
		}
	}
		//attribuer un chemin de fichiers
	public void setEtudiantFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if(file != null) {
			prefs.put("filePath", file.getPath());
		}else {
			prefs.remove("filePath");
		}
	}
	//Prendre les donnees de type XML et les convertr en donnees de type javaFX
	public void loadEtudiantDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(EtudiantListWrapper.class);
			Unmarshaller un = context.createUnmarshaller();
			
			EtudiantListWrapper wrapper = (EtudiantListWrapper) un.unmarshal(file);
			etudiantData.clear();
			etudiantData.addAll(wrapper.getEtudiants());
			setEtudiantFilePath(file);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("les données n'ont pas été trouvées");
			alert.setContentText("Les données ne pouvaient pas être trouvées dans le fichier : /n" + file.getPath());
			alert.showAndWait();
			
		}
	}
	//Prendre les donnees de type JavaFx et les convertir en type XML
	public void saveEtudiantDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(EtudiantListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			EtudiantListWrapper wrapper = new EtudiantListWrapper();
			wrapper.setEtudiants(etudiantData);
			
			m.marshal(wrapper, file);
			
			//Sauvegarder dans le registre
			setEtudiantFilePath(file);
		} catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Données non sauvegardées");
			alert.setContentText("Les données ne pouvaient pas être sauvegarder dans le fichier:/n" + file.getPath());
			alert.showAndWait();
		}
	}
	//commencer un nouveau
	@FXML
	private void handleNew() {
		getetudiantData().clear();
		setEtudiantFilePath(null);
	}
	//Le filechoose permet a l'usager de choisisr le fichier a ouvrir
	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();
		//permettre un filtre sur l'extension du fichier a chercher
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)","*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		//montrer le dialogue
		File file = fileChooser.showOpenDialog(null);
		if(file != null) {
			loadEtudiantDataFromFile(file);
		}
	}
	/*
	 * sauvegarde le fichier correspondant a l'etudiant actif
	 * s'il n y a pas de fichier, le menu sauvegarder sous va s'affichier
	 */
	@FXML
	private void handleSave() {
		File etudiantFile = getEtudiantFilePath();
		if(etudiantFile != null) {
			saveEtudiantDataToFile(etudiantFile);
		} else {
			handleSaveAs();
		}
	}
	//Ouvrir le FileChooser pour trouver le chemin
	@FXML
	private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)","*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		//Sauvegarde
		File file = fileChooser.showSaveDialog(null);
		if(file != null) {
			//verification de l'extension
			if(!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			saveEtudiantDataToFile(file);
		}
	}
	
	
	

}
