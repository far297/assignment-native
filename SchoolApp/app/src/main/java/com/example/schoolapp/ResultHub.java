package com.example.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ResultHub extends AppCompatActivity {

    Spinner spinnerRemoveStudent, spinnerClass;
    Button btnDelete;
    TableLayout tableLayoutResults; // TableLayout to display student results

    List<StudentResult> studentResults; // List of results

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resulthub);

        // Initialize Views
        spinnerRemoveStudent = findViewById(R.id.spinnerRemoveStudent);
        spinnerClass = findViewById(R.id.spinnerClass);
        tableLayoutResults = findViewById(R.id.tableLayoutResults);
        btnDelete = findViewById(R.id.btnDelete);

        // Retrieve data from Intent
        Intent intent = getIntent();
        String student = intent.getStringExtra("student");
        String subject = intent.getStringExtra("subject");
        String marks = intent.getStringExtra("marks");
        String grade = intent.getStringExtra("grade");

        // Setup Student Results
        studentResults = new ArrayList<>();
        if (student != null && marks != null && grade != null) {
            studentResults.add(new StudentResult(student, subject, Integer.parseInt(marks), grade));
        }

        // Adding other default results
        studentResults.add(new StudentResult("NUR JAZMINA", "MATHEMATICS", 45, "B"));
        studentResults.add(new StudentResult("AIN HAFIZA", "MATHEMATICS", 60, "A"));
        studentResults.add(new StudentResult("HANNA", "MATHEMATICS", 82, "A"));
        studentResults.add(new StudentResult("NUR ATHIRAH", "MATHEMATICS", 75, "B"));

        // Display results in TableLayout
        displayResultsInTable();

        // Setup Remove Student Spinner
        List<String> studentNames = new ArrayList<>();
        for (StudentResult result : studentResults) {
            studentNames.add(result.getStudentName());
        }
        ArrayAdapter<String> adapterStudents = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, studentNames);
        spinnerRemoveStudent.setAdapter(adapterStudents);

        // Delete Button Logic
        btnDelete.setOnClickListener(v -> {
            String selectedStudent = spinnerRemoveStudent.getSelectedItem().toString();
            for (int i = 0; i < studentResults.size(); i++) {
                if (studentResults.get(i).getStudentName().equals(selectedStudent)) {
                    studentResults.remove(i);
                    displayResultsInTable(); // Update table
                    Toast.makeText(ResultHub.this, "Student Removed Successfully", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });
    }

    // Method to display student results in the TableLayout
    private void displayResultsInTable() {
        tableLayoutResults.removeAllViews(); // Clear existing rows

        // Table Header
        TableRow headerRow = new TableRow(this);
        TextView headerName = new TextView(this);
        headerName.setText("Student Name");
        headerRow.addView(headerName);

        TextView headerSubject = new TextView(this);
        headerSubject.setText("Subject");
        headerRow.addView(headerSubject);

        TextView headerGrade = new TextView(this);
        headerGrade.setText("Grade");
        headerRow.addView(headerGrade);

        tableLayoutResults.addView(headerRow); // Add header row

        // Table rows for each student result
        for (StudentResult result : studentResults) {
            TableRow row = new TableRow(this);

            TextView studentName = new TextView(this);
            studentName.setText(result.getStudentName());
            row.addView(studentName);

            TextView subject = new TextView(this);
            subject.setText(result.getSubject());
            row.addView(subject);

            TextView grade = new TextView(this);
            grade.setText(result.getGrade());
            row.addView(grade);

            tableLayoutResults.addView(row); // Add row to the table
        }
    }
}
