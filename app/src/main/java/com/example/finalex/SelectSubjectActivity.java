package com.example.finalex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SelectSubjectActivity extends AppCompatActivity {
    private int totalCredits = 0;
    private final int MAX_CREDITS = 24;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    // Track enrollment status
    private boolean isEnrolled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Checkboxes for subjects
        CheckBox subject1 = findViewById(R.id.checkbox_subject1);
        CheckBox subject2 = findViewById(R.id.checkbox_subject2);
        CheckBox subject3 = findViewById(R.id.checkbox_subject3);
        CheckBox subject4 = findViewById(R.id.checkbox_subject4);
        CheckBox subject5 = findViewById(R.id.checkbox_subject5);
        CheckBox subject6 = findViewById(R.id.checkbox_subject6);
        CheckBox subject7 = findViewById(R.id.checkbox_subject7);
        CheckBox subject8 = findViewById(R.id.checkbox_subject8);
        CheckBox subject9 = findViewById(R.id.checkbox_subject9);
        CheckBox subject10 = findViewById(R.id.checkbox_subject10);
        CheckBox subject11 = findViewById(R.id.checkbox_subject11);
        CheckBox subject12 = findViewById(R.id.checkbox_subject12);
        CheckBox subject13 = findViewById(R.id.checkbox_subject13);
        CheckBox subject14 = findViewById(R.id.checkbox_subject14);
        CheckBox subject15 = findViewById(R.id.checkbox_subject15);
        CheckBox subject16 = findViewById(R.id.checkbox_subject16);
        CheckBox subject17 = findViewById(R.id.checkbox_subject17);
        CheckBox subject18 = findViewById(R.id.checkbox_subject18);
        CheckBox subject19 = findViewById(R.id.checkbox_subject19);

        // Buttons
        Button enrollButton = findViewById(R.id.btn_enroll);
        Button enrollSummaryButton = findViewById(R.id.btn_enroll_summary);

        // Enroll button click
        enrollButton.setOnClickListener(v -> {
            totalCredits = 0;
            boolean isChecked = false;

            if (subject1.isChecked()) {
                totalCredits += 3;
                isChecked = true;
            }
            if (subject2.isChecked()) {
                totalCredits += 3;
                isChecked = true;
            }
            if (subject3.isChecked()) {
                totalCredits += 2;
                isChecked = true;
            }
            if (subject4.isChecked()) {
                totalCredits += 3;
                isChecked = true;
            }
            if (subject5.isChecked()) {
                totalCredits += 2;
                isChecked = true;
            }
            if (subject6.isChecked()) {
                totalCredits += 3;
                isChecked = true;
            }
            if (subject7.isChecked()) {
                totalCredits += 2;
                isChecked = true;
            }
            if (subject8.isChecked()) {
                totalCredits += 2;
                isChecked = true;
            }
            if (subject9.isChecked()) {
                totalCredits += 2;
                isChecked = true;
            }
            if (subject10.isChecked()) {
                totalCredits += 1;
                isChecked = true;
            }
            if (subject11.isChecked()) {
                totalCredits += 1;
                isChecked = true;
            }
            if (subject12.isChecked()) {
                totalCredits += 3;
                isChecked = true;
            }
            if (subject13.isChecked()) {
                totalCredits += 2;
                isChecked = true;
            }
            if (subject14.isChecked()) {
                totalCredits += 3;
                isChecked = true;
            }
            if (subject15.isChecked()) {
                totalCredits += 2;
                isChecked = true;
            }
            if (subject16.isChecked()) {
                totalCredits += 2;
                isChecked = true;
            }
            if (subject17.isChecked()) {
                totalCredits += 3;
                isChecked = true;
            }
            if (subject18.isChecked()) {
                totalCredits += 3;
                isChecked = true;
            }
            if (subject19.isChecked()) {
                totalCredits += 3;
                isChecked = true;
            }


            if (!isChecked) {
                Toast.makeText(this, "Please select at least one subject.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (totalCredits > MAX_CREDITS) {
                Toast.makeText(this, "Total credits exceed the maximum limit of 24!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Mark as enrolled
            isEnrolled = true;

            // Save enrollment data to Firestore
            String userId = mAuth.getCurrentUser().getUid();
            Map<String, Object> enrollmentData = new HashMap<>();
            enrollmentData.put("TotalCredits", totalCredits);
            enrollmentData.put("Calculus", subject1.isChecked());
            enrollmentData.put("Programming Concept", subject2.isChecked());
            enrollmentData.put("Web Programming", subject3.isChecked());
            enrollmentData.put("Discrete Mathematics", subject4.isChecked());
            enrollmentData.put("Computer Organization and Architecture", subject5.isChecked());
            enrollmentData.put("Operating System Design", subject6.isChecked());
            enrollmentData.put("Database System", subject7.isChecked());
            enrollmentData.put("Object Oriented and Visual Programming", subject8.isChecked());
            enrollmentData.put("Linear Algebra", subject9.isChecked());
            enrollmentData.put("Probability and Statistics", subject10.isChecked());
            enrollmentData.put("Server-side Internet Programming", subject11.isChecked());
            enrollmentData.put("Computer Network", subject12.isChecked());
            enrollmentData.put("Network Security", subject13.isChecked());
            enrollmentData.put("Data Structure Algorithm", subject14.isChecked());
            enrollmentData.put("Numerical Methods", subject15.isChecked());
            enrollmentData.put("3D Computer Graphics and Animation", subject16.isChecked());
            enrollmentData.put("Wireless Mobile Programming", subject17.isChecked());
            enrollmentData.put("Software Engineering", subject18.isChecked());
            enrollmentData.put("Artificial Intelligence", subject19.isChecked());

            db.collection("enrollments").document(userId)
                    .set(enrollmentData)
                    .addOnSuccessListener(aVoid -> Toast.makeText(this, "Enrolled successfully!", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(this, "Failed to save enrollment data!", Toast.LENGTH_SHORT).show());
        });

        // Enroll summary button click
        enrollSummaryButton.setOnClickListener(v -> {
            if (!isEnrolled) {
                Toast.makeText(this, "You must enroll first before viewing the summary!", Toast.LENGTH_SHORT).show();
                return;
            }

            totalCredits = 0;
            StringBuilder selectedSubjects = new StringBuilder();

            if (subject1.isChecked()) { totalCredits += 3; selectedSubjects.append("Calculus, "); }
            if (subject2.isChecked()) { totalCredits += 3; selectedSubjects.append("Programming Concept, "); }
            if (subject3.isChecked()) { totalCredits += 2; selectedSubjects.append("Web Programming, "); }
            if (subject4.isChecked()) { totalCredits += 3; selectedSubjects.append("Discrete Mathematics, "); }
            if (subject5.isChecked()) { totalCredits += 2; selectedSubjects.append("Computer Organization and Architecture, "); }
            if (subject6.isChecked()) { totalCredits += 3; selectedSubjects.append("Operating System Design, "); }
            if (subject7.isChecked()) { totalCredits += 2; selectedSubjects.append("Database System, "); }
            if (subject8.isChecked()) { totalCredits += 2; selectedSubjects.append("Object Oriented and Visual Programming, "); }
            if (subject9.isChecked()) { totalCredits += 2; selectedSubjects.append("Linear Algebra, "); }
            if (subject10.isChecked()) { totalCredits += 1; selectedSubjects.append("Probability and Statistics, "); }
            if (subject11.isChecked()) { totalCredits += 1; selectedSubjects.append("Server-side Internet Programming, "); }
            if (subject12.isChecked()) { totalCredits += 3; selectedSubjects.append("Computer Network, "); }
            if (subject13.isChecked()) { totalCredits += 2; selectedSubjects.append("Network Security, "); }
            if (subject14.isChecked()) { totalCredits += 3; selectedSubjects.append("Data Structure Algorithm, "); }
            if (subject15.isChecked()) { totalCredits += 2; selectedSubjects.append("Numerical Methods, "); }
            if (subject16.isChecked()) { totalCredits += 2; selectedSubjects.append("3D Computer Graphics and Animation , "); }
            if (subject17.isChecked()) { totalCredits += 3; selectedSubjects.append("Wireless Mobile Programming, "); }
            if (subject18.isChecked()) { totalCredits += 3; selectedSubjects.append("Software Engineering, "); }
            if (subject19.isChecked()) { totalCredits += 3; selectedSubjects.append("Artificial Intelligence, "); }


            if (totalCredits == 0) {
                Toast.makeText(this, "Please select at least one subject before viewing the summary!", Toast.LENGTH_SHORT).show();
            } else if (totalCredits > MAX_CREDITS) {
                Toast.makeText(this, "Total credits exceed the maximum limit!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(SelectSubjectActivity.this, EnrollmentSummaryActivity.class);
                intent.putExtra("selectedSubjects", selectedSubjects.toString());
                intent.putExtra("totalCredits", totalCredits);
                startActivity(intent);
            }
        });
    }
}
