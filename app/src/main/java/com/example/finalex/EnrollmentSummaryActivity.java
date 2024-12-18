package com.example.finalex;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EnrollmentSummaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_summary);

        TextView summaryText = findViewById(R.id.text_enrollment_summary);

        // Retrieve data from Intent
        String selectedSubjects = getIntent().getStringExtra("selectedSubjects");

        int totalCredits = getIntent().getIntExtra("totalCredits", 0);
        String formattedSubjects = "None";
        if (selectedSubjects != null && !selectedSubjects.isEmpty()) {
            // Remove trailing comma, if present
            selectedSubjects = selectedSubjects.endsWith(",")
                    ? selectedSubjects.substring(0, selectedSubjects.length() - 1)
                    : selectedSubjects;

            // Split subjects by comma and join them with a new line
            String[] subjectsArray = selectedSubjects.split(",");
            StringBuilder formattedBuilder = new StringBuilder();
            for (String subject : subjectsArray) {
                formattedBuilder.append(subject).append("\n");
            }
            formattedSubjects = formattedBuilder.toString();
        }


        // Display summary details
        String summary = "Subjects Enrolled: \n" + formattedSubjects + "Total Credits: " + totalCredits;
        summaryText.setText(summary);
    }
}
