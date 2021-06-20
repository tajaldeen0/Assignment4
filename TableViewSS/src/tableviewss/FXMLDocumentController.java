/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableviewss;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class FXMLDocumentController implements Initializable {

    //Connection
    //PreparedStatement
    //ResultSet
    ObservableList<Student> studentList = FXCollections.observableArrayList();
    ObservableList<String> CollegesList = FXCollections.observableArrayList();
    ObservableList<Student> coursList = FXCollections.observableArrayList();

    private int index = -1;
    String cours = "";

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
    private TextField IDTextField;

    @FXML
    private TextField NameTextField;

    @FXML
    private ChoiceBox<String> CollegeChoiceBox;

    @FXML
    private Slider GpaSlider;

    @FXML
    private Spinner<Integer> AgeSpinner;

    @FXML
    private DatePicker graduationDatePicker;

    @FXML
    private Button addButton;

    @FXML
    private Label gpaLabel;
    @FXML
    private Button updateButton;

    @FXML
    private TextField searchTextField;
    @FXML
    private Button course;

    @FXML
    public ChoiceBox<String> coursBox;

    @FXML
    private TableColumn<Student, String> stdCours;

    @FXML
    void AddCourseTostudent(ActionEvent event) {
       // System.out.println(studentList.get(index).getCouresName());
        if (VerifyCourseData()) {
            int idCoures = myDatabase.showCouresID(coursBox.getValue());
            try {
                boolean isDone
                        = myDatabase.addStudentcours(Integer.parseInt(IDTextField.getText()), idCoures);
               /* if (studentList.get(index).getCouresName()==null) {
            cours=null;
        }*/
                if (index != -1&&isDone) {
                    cours+= coursBox.getValue() + ","+studentList.get(index).getCouresName();

                    studentList.get(index).setCouresName(cours);
                    StudentsTable.refresh();
                    if (isDone) {
                        JOptionPane.showMessageDialog(null, "Done");
                    }

                }

                //System.out.println(cours);

            } catch (Exception e) {
                System.out.println(e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "All Fields Are Required", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        cours="";
    }

    @FXML
    void Query(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("FXMLToQuery.fxml"));

        Scene scene2 = new Scene(root1);
        Stage stage = new Stage();
        stage.setScene(scene2);
        stage.show();
    }
    @FXML
    private Button Query;
    @FXML
    private Button courseMangemet;

    @FXML
    void courseMangemet(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("course.fxml"));

        Scene scene = new Scene(root1);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        if (stage.isShowing()) {
            StudentsTable.refresh();
        }
        if (!stage.isShowing()) {
            StudentsTable.refresh();
        }
            
        
    }

    @FXML
    void AddStudent(ActionEvent event) {
        if (VerifyStudentData()) {
            String name = NameTextField.getText();
            int id = Integer.parseInt(IDTextField.getText());
            String college = CollegeChoiceBox.getValue();
            int age = AgeSpinner.getValue();
            double gpa = GpaSlider.getValue();
            LocalDate date = graduationDatePicker.getValue();

            Student s = new Student(id, name, age, college, gpa, date);
            myDatabase.addStudentRow(s);
            studentList.add(s);
        } else {
            JOptionPane.showMessageDialog(null, "All Fields Are Required", "Error Message", JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    void UpdateStudent(ActionEvent event) {
        if (index != -1) {
            int requiredId = studentList.get(index).getId();

            int id = Integer.parseInt(IDTextField.getText());
            String name = NameTextField.getText();
            String college = CollegeChoiceBox.getSelectionModel().getSelectedItem();
            double gpa = GpaSlider.getValue();
            int age = AgeSpinner.getValue();

            Student s = new Student(id, name, age, college, gpa);
            if (graduationDatePicker.getValue() != null) {
                LocalDate date = graduationDatePicker.getValue();
                studentList.get(index).setDate(date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear());
                s.setDate(date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear());
            }

            myDatabase.updateStudentRow(requiredId, s);
            studentList.get(index).setId(id);
            studentList.get(index).setAge(age);
            studentList.get(index).setName(name);
            studentList.get(index).setCollege(college);
            studentList.get(index).setGpa(gpa);

            StudentsTable.refresh();

        }
    }

    @FXML
    void deleteRow(ActionEvent event) {
        if (index != -1) {
            Student s = studentList.get(index);
            myDatabase.deletAllcoursOnSTD(s.getId());
            myDatabase.deleteStudentRow(s.getId());
            studentList.remove(index);
            StudentsTable.refresh();
        }
    }

    @FXML
    void SearchRows() {

        ObservableList<Student> resultList = FXCollections.observableArrayList();

        String text = searchTextField.getText();
        for (Student student : studentList) {
            String id = student.getId() + "";
            if (student.getName().contains(text) || student.getCollege().contains(text) || id.contains(text)) {
                resultList.add(student);
            }
        }
        StudentsTable.setItems(resultList);
        StudentsTable.refresh();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Query.setVisible(false);

        // System.out.println();
        // myDatabase.getAllStudent();
        LoadData2();
        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 100, 18, 1);
        AgeSpinner.setValueFactory(svf);

        GpaSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                gpaLabel.setText(GpaSlider.getValue() + "");
            }
        });
        CollegesList.add("IT");
        CollegesList.add("Medical");
        CollegesList.add("Engineering");
        CollegesList.add("Nursing");
        CollegesList.add("Sceince");
        CollegesList.add("Software Engineering");

        CollegeChoiceBox.setItems(CollegesList);
        coursBox.setItems(myDatabase.showAllcourss());

        /*
        coursChoiceBox.add("java");
        coursChoiceBox.add("sql");
        coursBox.setItems(coursChoiceBox);*/
    }

    public void LoadData2() {
        // adding the List to the Table
        id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<Student, Integer>("age"));
        college.setCellValueFactory(new PropertyValueFactory<Student, String>("college"));
        gpa.setCellValueFactory(new PropertyValueFactory<Student, Double>("gpa"));
        date.setCellValueFactory(new PropertyValueFactory<Student, String>("date"));
        stdCours.setCellValueFactory(new PropertyValueFactory<Student, String>("CouresName"));

        // coursList=myDatabase.getcoursSTD();
        studentList = myDatabase.getAllStudentCours();
        StudentsTable.setItems(studentList);
    }

    public void LoadData() {

        index = StudentsTable.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Student s = studentList.get(index);
            IDTextField.setText("" + s.getId());
            NameTextField.setText(s.getName());
            AgeSpinner.getValueFactory().setValue(s.getAge());
            GpaSlider.setValue(s.getGpa());
            CollegeChoiceBox.getSelectionModel().select(s.getCollege());
        } else {
            JOptionPane.showMessageDialog(null, "You Should Select A ROW");
        }
    }

    public boolean VerifyStudentData() {
        if ((!IDTextField.getText().isEmpty()) && (!NameTextField.getText().isEmpty())
                && (CollegeChoiceBox.getValue() != null) && (graduationDatePicker.getValue() != null)) {
            return true;
        }
        return false;
    }

    public boolean VerifyCourseData() {
        if ((!IDTextField.getText().isEmpty()) && (!NameTextField.getText().isEmpty())
                && (CollegeChoiceBox.getValue() != null)) {
            return true;
        }
        return false;
    }
   

}

//
//
