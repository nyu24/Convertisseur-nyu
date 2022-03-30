/*
* Author : Nicole Yu
* Date : Mar. 2, 2022
* Description : Le code pour : Un convertisseur qui peut convertir les differentes longueurs, volumes et masses. 
*/
package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;

public class UnitController implements Initializable{


    @FXML
    private Button btnAc1;

    @FXML
    private Button btnAc;

    @FXML
    private Button btnAc3;

    @FXML
    private ComboBox<String> cbov1;

    @FXML
    private ComboBox<String> cbov2;

    @FXML
    private TextField txtm1;

    @FXML
    private TextField txtm2;

    @FXML
    private Button btnV;

    @FXML
    private ComboBox<String> cbol1;

    @FXML
    private Button btnT;

    @FXML
    private ComboBox<String> cbol2;

    @FXML
    private Button btnL;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnM;

    @FXML
    private TabPane monTab;

    @FXML
    private Button btnExit3;

    @FXML
    private Button btnExit2;

    @FXML
    private Button btnExit1;

    @FXML
    private TextField txtl2;

    @FXML
    private TextField txtl1;

    @FXML
    private Tab tbLong;

    @FXML
    private TextField txtv1;

    @FXML
    private ComboBox<String> cbom2;

    @FXML
    private ComboBox<String> cbom1;

    @FXML
    private TextField txtv2;

    //initialise les valeurs
    private ObservableList<String> Long = FXCollections.observableArrayList("Mètre","Centimère","Kilomètre", "Pouce", "Pied");
    private double [] longueur = {1.0,100.0,0.001,39.37,3.281};
    
    private ObservableList<String> Vol = FXCollections.observableArrayList("Litre","Millilitre","US Gallon", "US Quart", "US Tasse");
    private double [] volume = {1.0,1000.0,0.264172,1.05669,4.16667};
    
    private ObservableList<String> M = FXCollections.observableArrayList("Kilogramme", "Gramme","Milligramme","Livre","Once");
    private double [] masse = {1.0,1000.0,1000000.0,2.20462,35.274};
    
    HashMap<String, Integer> mapB = new HashMap<>();

    //initialise tout les combo box avec les differentes tabs
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		mapB.put("Masse",2); mapB.put("Longueur",1); mapB.put("Volume",3); mapB.put("Accueil",0);
		cbol1.setItems(Long); cbol2.setItems(Long); cbol1.getSelectionModel().selectFirst(); cbol2.getSelectionModel().selectFirst();
		cbov1.setItems(Vol); cbov2.setItems(Vol); cbov1.getSelectionModel().selectFirst(); cbov2.getSelectionModel().selectFirst();
		cbom1.setItems(M); cbom2.setItems(M); cbom1.getSelectionModel().selectFirst(); cbom2.getSelectionModel().selectFirst();
		
	}
	
	//pour le methode convertir : Qui set taux pour input et output
	private double setTaux(ComboBox a, double tbl []) {
		return tbl[a.getSelectionModel().getSelectedIndex()];
	}
	
	//convertir avec longueur
	@FXML
	private void convertL1() {
		convertir(cbol1,cbol2,txtl1,txtl2,longueur);
	}
	@FXML
	private void convertL2() {
		convertir(cbol2,cbol1,txtl2,txtl1,longueur);
	}
	
	//convertir avec volume
	@FXML
	private void convertV1() {
		convertir(cbov1,cbov2,txtv1,txtv2,volume);
	}
	@FXML
	private void convertV2() {
		convertir(cbov2,cbov1,txtv2,txtv1,volume);
	}
	
	//convert avec le masse
	@FXML
	private void convertM1() {
		convertir(cbom1,cbom2,txtm1,txtm2,masse);
	}
	@FXML
	private void convertM2() {
		convertir(cbom2,cbom1,txtm2,txtm1,masse);
	}

    //convertir input
	private void convertir(ComboBox a, ComboBox b, TextField c, TextField d, double tbl []) {
		double from = setTaux(a,tbl);
		double to = setTaux(b, tbl);
		double depart = 0.0;
		depart = c.getText().equals("")?0.0:Double.parseDouble(c.getText());
		double des = (to/from)*depart;
		d.setText(String.valueOf(des));
		
	}
	//verifier si input est une nombre
	@FXML
	private void verifNum(KeyEvent e){
		TextField txt=(TextField)e.getSource();
		txt.textProperty().addListener((observable,oldValue,newValue)->
		{
			if(!newValue.matches("^-?[0-9](\\.[0-9]+)?$")){
				txt.setText(newValue.replaceAll("[^\\d*\\.\\-]",""));
				}
			});
		}
	
	//quit l'application
	@FXML
	void quitter() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Attention");
		alert.setTitle("Confirmation");
		alert.setContentText("Voulez-vous quitter l'application ?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			System.exit(0);
		}
	}
	
	//retourne a un certain tab
	@FXML
	void change(ActionEvent e) {
		Button btn = (Button)e.getSource();
		String a = btn.getText();
		monTab.getSelectionModel().select(mapB.get(a));
		
	}

}

