/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.DemoCRUD;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.DemoCRUD.mhs.Mahasiswa;

/**
 *
 * @author ASUS
 */
public class MainController implements Initializable {
    

    @FXML
    private TextField tfNim;
    @FXML
    private TextField tfNama;
    @FXML
    private TextField tfProdi;
    @FXML
    private TextField tfFak;
    @FXML
    private TableView<Mahasiswa> tableView;
    @FXML
    private TableColumn<Mahasiswa, Integer> viewNim;
    @FXML
    private TableColumn<Mahasiswa, String> viewNama;
    @FXML
    private TableColumn<Mahasiswa, String> viewProdi;
    @FXML
    private TableColumn<Mahasiswa, String> viewFak;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        if(event.getSource() == btnAdd){
            InsertRecord();
        }else if (event.getSource() == btnUpdate){
            UpdateRecord();
        }else if(event.getSource() == btnDelete){
            DeleteButton();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showMhs();
    }

    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mhs?useSSL=false&serverTimezone=UTC&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false", "root","admin");
            System.out.print("connect database");
            return conn;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.print("error : "+ e.getMessage());
            return null;
        }
    }  
    
    public ObservableList<Mahasiswa> getMhsList(){
        ObservableList<Mahasiswa> mhsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM mahasiswa";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Mahasiswa mahasiswa;
            while(rs.next()){
                mahasiswa = new Mahasiswa(rs.getInt("nim"), rs.getString("nama"), rs.getString("programStudi"), rs.getString("fakultas"));
                mhsList.add(mahasiswa);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return mhsList;
    }
    
    public void showMhs(){
        ObservableList<Mahasiswa> list = getMhsList();
        viewNim.setCellValueFactory(new PropertyValueFactory<Mahasiswa, Integer>("nim"));
        viewNama.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("nama"));
        viewProdi.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("programStudi"));
        viewFak.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("fakultas"));
        
        tableView.setItems(list);
    }
    
    private void InsertRecord(){
        String query = "INSERT INTO mahasiswa VALUES ("+ tfNim.getText() + ",'" + tfNama.getText() + "','" + tfProdi.getText() + "','" + tfFak.getText() + "')";
        executeQuery(query);
        showMhs();
    }
    
    private void UpdateRecord(){
        String query = "UPDATE mahasiswa SET Nim = '" + tfNim.getText() + "', Nama = '" + tfNama.getText() + "', ProgramStudi = '" + tfProdi.getText() + "', Fakultas = '" + tfFak.getText() + "' WHERE Nim = " + tfNim.getText()+"";
        executeQuery(query);
        showMhs();
    }
    
    private void DeleteButton(){
        String query = "DELETE FROM mahasiswa WHERE nim=" + tfNim.getText()+"";
        executeQuery(query);
        showMhs();
    }
    
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Mahasiswa mhs = tableView.getSelectionModel().getSelectedItem();
        tfNim.setText(""+mhs.getNim());
        tfNama.setText(mhs.getNama());
        tfProdi.setText(mhs.getProgramStudi());
        tfFak.setText(mhs.getFakultas());
    }


}
