/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableviewss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class myDatabase {

    static Connection conn = null;

    public static void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/iug", "root", "");
            System.out.println("Log : Connection to Database IUG has been Established");
        } catch (ClassNotFoundException ex) {
            System.err.println("Class Not Found");
        } catch (SQLException ex) {
            System.err.println("Sql Exception : cannot Connect to Database");
        }
    }

    public static ObservableList<Student> getAllStudent() {

        int id = 0;
        ObservableList<Student> resultList = FXCollections.observableArrayList();
        if (conn == null) {
            getConnection();
        }
        try {
            PreparedStatement stat = conn.prepareStatement("Select * From std");

            ResultSet rs = stat.executeQuery();
            while (rs.next()) {

                id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String college = rs.getString("college");
                double gpa = rs.getDouble("gpa");
                String date = rs.getString("graduationDate");

                Student s = new Student(id, name, age, college, gpa);
                s.setDate(date);

                resultList.add(s);

            }

            //System.out.println(name1+"\n"+name2);
        } catch (SQLException ex) {

            System.err.println("Select Statement Error");
        }
        return resultList;
    }
public static ObservableList<Student> getAllStudentCours() {

        int id = 0;
        ObservableList<Student> resultList = FXCollections.observableArrayList();
        if (conn == null) {
            getConnection();
        }
        try {
            PreparedStatement stat = conn.prepareStatement("Select * From std");

            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
String nameCours="";
                id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String college = rs.getString("college");
                double gpa = rs.getDouble("gpa");
                String date = rs.getString("graduationDate");
             
                PreparedStatement statStd2 = conn.prepareStatement("Select courseid From registration WHERE studentid=?");
                statStd2.setInt(1, id);
                ResultSet rs2 = statStd2.executeQuery();
                while (rs2.next()) {
                    PreparedStatement statStd1 = conn.prepareStatement("Select name From course WHERE id=?");
                    int courseid = rs2.getInt("courseid");
                    statStd1.setInt(1, courseid);
                    ResultSet rs1 = statStd1.executeQuery();
                    while (rs1.next()) {
                         nameCours += rs1.getString("name")+",";  
                    }
                }
            
                Student s = new Student(id, name, age, college, gpa,nameCours);
                s.setDate(date);

                resultList.add(s);

            }

            //System.out.println(name1+"\n"+name2);
        } catch (SQLException ex) {

            System.err.println("Select Statement Error");
        }
        return resultList;
    }
    public static ObservableList<Student> getAllStudentSE() {
        ObservableList<Student> resultList = FXCollections.observableArrayList();
        if (conn == null) {
            getConnection();
        }
        try {
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM std WHERE college=?");
            stat.setString(1, "Software Engineering");
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String college = rs.getString("college");
                double gpa = rs.getDouble("gpa");
                String date = rs.getString("graduationDate");
                //System.out.println(id);
                Student s = new Student(id, name, age, college, gpa);
                s.setDate(date);
                resultList.add(s);
                //System.out.println(s.getId());
            }
        } catch (SQLException ex) {

            System.err.println("Select Statement Error");
        }
        return resultList;
    }

    public static ObservableList<Student> getAllStudentExcellentGrades() {
        ObservableList<Student> resultList = FXCollections.observableArrayList();
        
        if (conn == null) {
            getConnection();
        }
        try {
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM std WHERE gpa >90");
            ResultSet rs = stat.executeQuery();
            String nameCours = "";
            while (rs.next()) {
               int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String college = rs.getString("college");
                double gpa = rs.getDouble("gpa");
                String date = rs.getString("graduationDate");
                //System.out.println(id);
               
                
                Student s = new Student(id, name, age, college, gpa);
                s.setDate(date);
                resultList.add(s);
                //System.out.println(s.getId());
            }
        } catch (SQLException ex) {

            System.err.println("Select Statement Error");
        }
        return resultList;
    }
    public static ObservableList<Student> getAllStudentPassStudentASC() {
        ObservableList<Student> resultList = FXCollections.observableArrayList();
        if (conn == null) {
            getConnection();
        }
        try {
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM std WHERE gpa >60 order by name ASC");
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String college = rs.getString("college");
                double gpa = rs.getDouble("gpa");
                String date = rs.getString("graduationDate");
                //System.out.println(id);
                Student s = new Student(id, name, age, college, gpa);
                s.setDate(date);
                resultList.add(s);
                //System.out.println(s.getId());
            }
        } catch (SQLException ex) {

            System.err.println("Select Statement Error");
        }
        return resultList;
    }

    public static ObservableList<Student> LoadDataScience() {
        ObservableList<Student> resultList = FXCollections.observableArrayList();

        try {
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM std WHERE college='Sceince'");

            PreparedStatement stat2 = conn.prepareStatement("UPDATE std SET gpa=gpa+3  WHERE gpa<70  AND college='Sceince'");
            //stat2.setString(1,"Science");
            int update = stat2.executeUpdate();

            //stat.setString(1, "Science");
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String college = rs.getString("college");
                double gpa = rs.getDouble("gpa");
                String date = rs.getString("graduationDate");
                //System.out.println(id);
                Student s = new Student(id, name, age, college, gpa);
                s.setDate(date);
                resultList.add(s);
                //System.out.println(s.getId());
            }
        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
        }
        return resultList;
    }

    public static ObservableList<String> showAllcourss() {
        ObservableList<String> resultList = FXCollections.observableArrayList();
        if (conn == null) {
            getConnection();
        }
        try {
            PreparedStatement stat = conn.prepareStatement("SELECT name FROM course");
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                // System.out.println(name);
                resultList.add(name);

                // stat.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("Select Statement Error show cours");
        }
        return resultList;
    }

    public static ObservableList<course> showAllcourss2() {
        ObservableList<course> resultList = FXCollections.observableArrayList();
        if (conn == null) {
            getConnection();
        }
        try {
            PreparedStatement stat = conn.prepareStatement("SELECT id,name,room FROM course");
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String room = rs.getString("room");
                course c = new course(id, name, room);

                resultList.add(c);

            }

        } catch (Exception e) {
            System.out.println("Select Statement Error show cours");
        }
        return resultList;
    }

    public static int showCouresID(String couresName) {
        if (conn == null) {
            getConnection();
        }
        try {
            PreparedStatement stat = conn.prepareStatement("SELECT id FROM course WHERE name=?");
            stat.setString(1, couresName);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                return id;

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static boolean addStudentcours(int idStd, int idCours) {

        try {
            PreparedStatement stat = conn.prepareStatement("INSERT INTO registration VALUES(?,?,?)");
            stat.setInt(1, idStd);
            stat.setInt(2, idCours);
            stat.setString(3, "semester 1");
            int rowCount = stat.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "It is not possible to add the same course to the same person");
        }
        return false;
    }

    public static void addStudentRow(Student std) {
        if (conn == null) {
            getConnection();
        }

        try {
            PreparedStatement stat = conn.prepareStatement("INSERT INTO std VALUES(?,?,?,?,?,?)");
            stat.setInt(1, std.getId());
            stat.setString(2, std.getName());
            stat.setInt(3, std.getAge());
            stat.setString(4, std.getCollege());
            stat.setDouble(5, std.getGpa());
            stat.setString(6, std.getDate());
            int rowCount = stat.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.err.println("Error in Inserting row");
        }

    }

    public static void addCourse(course c) {
        if (conn == null) {
            getConnection();

        }
        try {
            PreparedStatement stat = conn.prepareStatement("INSERT INTO course VALUES(?,?,?)");
            stat.setInt(1, c.getCourseId());
            stat.setString(2, c.getCourseName());
            stat.setString(3, c.getCourseRoom());
            int rowCount = stat.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error in Inserting Course ");
        }
    }

    public static void DeleteCourseStudentRow(int id) {
        if (conn == null) {
            getConnection();
        }
        try {
            PreparedStatement stat = conn.prepareStatement("DELETE FROM course WHERE id = ?");
            stat.setInt(1, id);
            int rowCount = stat.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void DeleteCourse(int idCours) {
        if (conn == null) {
            getConnection();
        }
        try {
            PreparedStatement stat = conn.prepareStatement("DELETE FROM registration WHERE courseid=?");
            stat.setInt(1, idCours);
            int rowCount = stat.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteStudentRow(int id) {
        if (conn == null) {
            getConnection();
        }

        try {
            PreparedStatement stat = conn.prepareStatement("DELETE FROM std WHERE id = ?");
            stat.setInt(1, id);
            int rowCount = stat.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error in DELETEING row");
        }

    }
    public  static void deletAllcoursOnSTD(int id){
    if (conn == null) {
            getConnection();
        }
        try {
            PreparedStatement stat=conn.prepareStatement("DELETE FROM registration WHERE studentid=?");
            stat.setInt(1, id);
            int rowcount=stat.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateStudentRow(int id, Student std) {
        if (conn == null) {
            getConnection();
        }

        try {
            PreparedStatement stat = conn.prepareStatement("UPDATE std SET id = ? , name = ? , age = ? , college = ? , gpa = ? , graduationDate = ? Where id = ?");
            stat.setInt(1, std.getId());
            stat.setString(2, std.getName());
            stat.setInt(3, std.getAge());
            stat.setString(4, std.getCollege());
            stat.setDouble(5, std.getGpa());
            stat.setString(6, std.getDate());
            stat.setInt(7, id);
            int rowCount = stat.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println("Error in Inserting row");
        }

    }

}
