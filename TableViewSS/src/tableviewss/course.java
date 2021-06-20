package tableviewss;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author taj
 */
public class course {
    private   int courseId;
    private String courseName;
    private  String courseRoom;

    public course(int courseId, String courseName, String courseRoom) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseRoom = courseRoom;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseRoom() {
        return courseRoom;
    }

    public void setCourseRoom(String courseRoom) {
        this.courseRoom = courseRoom;
    }
    
    
    
}
