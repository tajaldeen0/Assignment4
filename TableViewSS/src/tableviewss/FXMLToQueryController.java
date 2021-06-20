/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableviewss;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author taj
 */
public class FXMLToQueryController implements Initializable {
      ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    private TextArea Textquery;

    @FXML
    private Button supmit;
    
    @FXML
    void btnSupmit(ActionEvent event) {
        String SE="SELECT * FROM std WHERE college=\"Software Engineering\"";
        String ExcellentGrades="SELECT * FROM std WHERE gpa >90";
        String Science="UPDATE std SET gpa=gpa+3  WHERE gpa<70  AND college=\"Science\"";
        String ASC="SELECT * FROM std WHERE gpa >60 order by name ASC";
        if (Textquery.getText().equalsIgnoreCase(ASC)) {
            
            ASC();
            StudentsTable.setVisible(true);
        Textquery.setVisible(false);
        }
        if (Textquery.getText().equalsIgnoreCase(SE)) {
            LoadDataSE();
            StudentsTable.setVisible(true);
        Textquery.setVisible(false);
        }
        if (Textquery.getText().equalsIgnoreCase(ExcellentGrades)) {
            LoadDataExcellentGrades();
            StudentsTable.setVisible(true);
        Textquery.setVisible(false);
        }
        if (Textquery.getText().equalsIgnoreCase(Science)) {
            LoadDataScience();
            StudentsTable.setVisible(true);
        Textquery.setVisible(false);
        }
        
        
        

    }
     @FXML
    private TableView<Student> StudentsTable;

    @FXML
    private TableColumn<Student, Integer> id;

    @FXML
    private TableColumn<Student, String> name;

    @FXML
    private TableColumn<Student, Integer> age;

    @FXML
    private TableColumn<Student, String> college;

    @FXML
    private TableColumn<Student, Double> gpa;

    @FXML
    private TableColumn<Student, String> date;
    @FXML
    private Button Exit;

    @FXML
    void btnExit(ActionEvent event) {
 StudentsTable.setVisible(false);
 Textquery.setVisible(true);
// studentList.remove(0, studentList.size());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  myDatabase.getAllStudentSE();
        
        StudentsTable.setVisible(false);
    }    
    
    public void LoadDataSE() {
        // adding the List to the Table
        id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<Student, Integer>("age"));
        college.setCellValueFactory(new PropertyValueFactory<Student, String>("college"));
        gpa.setCellValueFactory(new PropertyValueFactory<Student, Double>("gpa"));
        date.setCellValueFactory(new PropertyValueFactory<Student, String>("date"));

        studentList = myDatabase.getAllStudentSE();
        StudentsTable.setItems(studentList);
    }
     public void LoadDataExcellentGrades() {
        // adding the List to the Table
        id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<Student, Integer>("age"));
        college.setCellValueFactory(new PropertyValueFactory<Student, String>("college"));
        gpa.setCellValueFactory(new PropertyValueFactory<Student, Double>("gpa"));
        date.setCellValueFactory(new PropertyValueFactory<Student, String>("date"));

        studentList = myDatabase.getAllStudentExcellentGrades();
        StudentsTable.setItems(studentList);
    }
     public void LoadDataScience() {
        // adding the List to the Table
        id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<Student, Integer>("age"));
        college.setCellValueFactory(new PropertyValueFactory<Student, String>("college"));
        gpa.setCellValueFactory(new PropertyValueFactory<Student, Double>("gpa"));
        date.setCellValueFactory(new PropertyValueFactory<Student, String>("date"));

        studentList = myDatabase.LoadDataScience();
        StudentsTable.setItems(studentList);
    }
     public void ASC() {
        // adding the List to the Table
        id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<Student, Integer>("age"));
        college.setCellValueFactory(new PropertyValueFactory<Student, String>("college"));
        gpa.setCellValueFactory(new PropertyValueFactory<Student, Double>("gpa"));
        date.setCellValueFactory(new PropertyValueFactory<Student, String>("date"));

        studentList = myDatabase.getAllStudentPassStudentASC();
        StudentsTable.setItems(studentList);
    }
    
}
