package com.example.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerStudents, spinnerClass, spinnerSubjects;
    EditText editTextMarks, editTextGrade;
    Button btnReset, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        spinnerStudents = findViewById(R.id.spinnerStudents);
        spinnerClass = findViewById(R.id.spinnerClass);
        spinnerSubjects = findViewById(R.id.spinnerSubjects);
        editTextMarks = findViewById(R.id.editTextMarks);
        editTextGrade = findViewById(R.id.editTextGrade);
        btnReset = findViewById(R.id.btnReset);
        btnUpdate = findViewById(R.id.btnUpdate);

        // Setup Spinners
        String[] students = {"SAIDATUL ATHIRAH", "NUR JAZMINA", "AIN HAFIZA"};
        String[] classes = {"5A4"};
        String[] subjects = {"MATHEMATICS"};

        ArrayAdapter<String> adapterStudents = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, students);
        spinnerStudents.setAdapter(adapterStudents);

        ArrayAdapter<String> adapterClasses = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, classes);
        spinnerClass.setAdapter(adapterClasses);

        ArrayAdapter<String> adapterSubjects = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, subjects);
        spinnerSubjects.setAdapter(adapterSubjects);

        // Reset Button
        btnReset.setOnClickListener(v -> {
            // Reset fields
            editTextMarks.setText("");
            editTextGrade.setText("");
            Toast.makeText(MainActivity.this, "Fields Reset", Toast.LENGTH_SHORT).show();

            // Navigate to ResultHub (without any data)
            navigateToResultHub();
        });

        // Update Button
        btnUpdate.setOnClickListener(v -> {
            String marks = editTextMarks.getText().toString();
            String grade = editTextGrade.getText().toString();

            if (marks.isEmpty() || grade.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Pass data to ResultHub
                Intent intent = new Intent(MainActivity.this, ResultHub.class);
                intent.putExtra("student", spinnerStudents.getSelectedItem().toString());
                intent.putExtra("className", spinnerClass.getSelectedItem().toString());
                intent.putExtra("subject", spinnerSubjects.getSelectedItem().toString());
                intent.putExtra("marks", marks);
                intent.putExtra("grade", grade);

                startActivity(intent);
            }
        });
    }

    // Method to navigate to ResultHub
    private void navigateToResultHub() {
        Intent intent = new Intent(MainActivity.this, ResultHub.class);
        startActivity(intent);
    }
}
