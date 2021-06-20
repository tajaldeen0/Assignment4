/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableviewss;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author HP
 */
public class Student {
    private int id ;
    private String name ;
    private int age ;
    private String college ;
    private double gpa ;
    private String date ;
    private String CouresName;

    public String getCouresName() {
        return CouresName;
    }

    public void setCouresName(String CouresName) {
        this.CouresName = CouresName;
    }

    public Student(int id, String name, int age, String college, double gpa, LocalDate date) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.college = college;
        this.gpa = gpa;
        String d = date.getDayOfMonth() + "/"+date.getMonthValue()+"/"+date.getYear();
        this.date = d;
    }
    
    public Student(int id, String name, int age, String college, double gpa, String date, String CouresName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.college = college;
        this.gpa = gpa;
        this.date = date;
        this.CouresName = CouresName;
    }

    public Student(String CouresName) {
        this.CouresName = CouresName;
    }

   
    
     public Student(int id, String name, int age, String college, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.college = college;
        this.gpa = gpa;
      
    }
     public Student(int id, String name, int age, String college, double gpa,String CouresName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.college = college;
        this.gpa = gpa;
        this.CouresName = CouresName;
      
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}
