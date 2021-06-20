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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author taj
 */
public class CourseController implements Initializable {
    ObservableList<course> courseList = FXCollections.observableArrayList();
    int index = -1;
        @FXML
    private TableView<course> courseTable;
 @FXML
    private TableColumn<course, Integer> courseId;

    @FXML
    private TableColumn<course, String> courseName;

    @FXML
    private TableColumn<course, String> courseRoom;
    
    @FXML
    private TextField LabelCoursName;

    @FXML
    private TextField LabelCoursID;

    @FXML
    private TextField LabelCoursRoom;
    @FXML
    void btnAddCourse(ActionEvent event) {
       
              
        if (cheacCoures()) {
            String nameCours=LabelCoursName.getText();
            int IDCours=Integer.parseInt(LabelCoursID.getText());
            String RoomCours=LabelCoursRoom.getText();
            course c=new course(IDCours, nameCours,RoomCours);
           myDatabase.addCourse(c);
           courseList.add(c);
            
        }else{
            JOptionPane.showMessageDialog(null, "All Fields Are Required");
        }

    }
    @FXML
    void btnDeleteCourse(ActionEvent event) {
        
        
        if (index != -1) {
           course c=courseList.get(index);
           myDatabase.DeleteCourse(c.getCourseId());
            myDatabase.DeleteCourseStudentRow(c.getCourseId());
            
            courseList.remove(index);
            courseTable.refresh();
        }
        else{System.out.println("ERORE in Delete");}

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       LoadDataCours();
        
    } 
    public void LoadDataCourseClicked() {

        index = courseTable.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            course c=courseList.get(index);
            LabelCoursID.setText(""+c.getCourseId());
            LabelCoursName.setText(c.getCourseName());
            LabelCoursRoom.setText(c.getCourseRoom());
        } else {
            JOptionPane.showMessageDialog(null, "You Should Select A ROW");
        }
    }
     public void LoadDataCours(){
         courseId.setCellValueFactory(new PropertyValueFactory<course, Integer>("courseId"));
         courseName.setCellValueFactory(new PropertyValueFactory<course, String>("courseName"));
          courseRoom.setCellValueFactory(new PropertyValueFactory<course, String>("courseRoom"));
          
          courseList=myDatabase.showAllcourss2();
          courseTable.setItems(courseList);
         
        }
     public  boolean cheacCoures(){
         if ((!LabelCoursName.getText().isEmpty()) && (!LabelCoursID.getText().isEmpty()) &&(!LabelCoursRoom.getText().isEmpty())) {
             return true;
         }
         return false;
     
     }
    
}
