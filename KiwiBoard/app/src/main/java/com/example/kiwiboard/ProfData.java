package com.example.kiwiboard;

import java.util.ArrayList;

public class ProfData {

    private static String name = "";                 // Professor's name
    private static String ID;                 // Professor's ID
    private static String email;                // Professor's email
    private static String password;             // Professor's password
    private static int currentcourse = -1;           // Professor's current course
    private static int lastclickedquestion = -1;     // The most recently selected question index
    private static int lastclickedstudent = -1;     // The most recently selected student index
    private static boolean professormode = false;      // Flag to track when professor mode is entered
    private static ArrayList<Course> courses;   // Professor's currently selected course
    private static String university;

    // Default constructor with parameters
    public ProfData(String name, String ID, String email, String password, int currentcourse, ArrayList<Course> courses) {
        ProfData.name = name;
        ProfData.name = ID;
        ProfData.email = email;
        ProfData.password = password;
        ProfData.currentcourse = currentcourse;
        ProfData.courses = courses;
    }

    public ProfData(String name, String email,String password,String university){
        ProfData.name = name;
        ProfData.email = email;
        ProfData.password = password;
        ProfData.university = university;
        courses = new ArrayList<>();
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        ProfData.name = name;
    }

    public static String getID() {
        return ID;
    }

    public static void setID(String ID) {
        ProfData.ID = ID;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        ProfData.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ProfData.password = password;
    }

    public static String getUniversity() {
        return university;
    }

    public static void setUniversity(String university) {
        ProfData.university = university;
    }

    public static boolean isProfessormode() {
        return professormode;
    }

    public static void setProfessormode(boolean mode) {
        if (mode)
            StudentData.setStudentmode(false);

        ProfData.professormode = mode;
    }

    public static int getLastclickedquestion() {
        return lastclickedquestion;
    }

    public static void setLastclickedquestion(int lastclickedquestion) {
        ProfData.lastclickedquestion = lastclickedquestion;
    }

    public static int getLastclickedstudent() {
        return lastclickedstudent;
    }

    public static void setLastclickedstudent(int lastclickedstudent) {
        ProfData.lastclickedstudent = lastclickedstudent;
    }

    public static int getCurrentcourse() {
        return currentcourse;
    }

    public static void setCurrentcourse(int currentcourse) {
        ProfData.currentcourse = currentcourse;
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static void setCourses(ArrayList<Course> courses) {
        ProfData.courses = courses;
    }

    // Adds a new course from an existing Course object
    public static void addCourse(Course course){
        courses.add(course);
    }

    // Adds a new course using Course's default constructor with parameters
    public static void addNewCourse(String courseName, String instructorname, int classKey, ArrayList<Question> questions, ArrayList<Student> students){
        courses.add(new Course(courseName, instructorname, classKey, questions, students));
    }

    // Set the course at the index given
    public static void setCourse(int index, Course course){
        courses.set(index, course);
    }

    // Set the course at the index given
    public static Course getCourse(int index){
        return courses.get(index);
    }

    // Removes the course at the index. Indices start at 0.
    public static void removeCourse(int index){
        courses.remove(index);
        if(index == currentcourse){
            currentcourse = -1;
        }
    }

    // Clears all professor data
    public static void clearAllData(){
        name = "";
        email = "";
        password = "";
        university = "";
        currentcourse = -1;
        lastclickedquestion = -1;
        courses = new ArrayList<Course>();
    }

    public static void loadSampleData(){

    }

}
