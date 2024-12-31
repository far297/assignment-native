package com.example.schoolapp;

public class StudentResult {
    private String studentName;
    private String subject;
    private int marks;
    private String grade;

    // Constructor
    public StudentResult(String studentName, String subject, int marks, String grade) {
        this.studentName = studentName;
        this.subject = subject;
        this.marks = marks;
        this.grade = grade;
    }

    // Getters and Setters
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
