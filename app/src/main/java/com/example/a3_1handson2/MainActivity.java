package com.example.a3_1handson2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private TextView tvPerception, tvReasoning, tvAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etInput = findViewById(R.id.etInput);
        tvPerception = findViewById(R.id.tvPerception);
        tvReasoning = findViewById(R.id.tvReasoning);
        tvAction = findViewById(R.id.tvAction);
        Button btnAnalyze = findViewById(R.id.btnAnalyze);

        btnAnalyze.setOnClickListener(v -> {
            String input = etInput.getText().toString().trim().toLowerCase();
            processWorkflow(input);
        });
    }

    private void processWorkflow(String input) {
        if (input.isEmpty()) {
            tvPerception.setText("Perception: Empty input detected.");
            tvReasoning.setText("Reasoning: Cannot process null or empty data.");
            tvAction.setText("Action: Asking user for valid input.");
            return;
        }

        tvPerception.setText("Perception: Input received - \"" + input + "\"");

        if (input.contains("hello") || input.contains("hi")) {
            tvReasoning.setText("Reasoning: User is initiating a greeting.");
            tvAction.setText("Action: Respond with 'Hello! How can I help you today?'");
        } else if (input.contains("time")) {
            tvReasoning.setText("Reasoning: User wants temporal information.");
            tvAction.setText("Action: Retrieving and displaying current system time.");
        } else if (input.contains("weather")) {
            tvReasoning.setText("Reasoning: User is interested in environmental conditions.");
            tvAction.setText("Action: Fetching latest weather report from API.");
        } else if (input.contains("light") && (input.contains("on") || input.contains("off"))) {
            tvReasoning.setText("Reasoning: Control command for smart home device detected.");
            tvAction.setText("Action: Sending signal to toggle smart light.");
        } else {
            tvReasoning.setText("Reasoning: Input does not match predefined rules.");
            tvAction.setText("Action: Search online or ask for clarification.");
        }
    }
}
