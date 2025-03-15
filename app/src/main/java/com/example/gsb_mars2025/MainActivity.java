package com.example.gsb_mars2025;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération des éléments UI
        ImageView logo = findViewById(R.id.logo);
        EditText input1 = findViewById(R.id.input1);
        EditText input2 = findViewById(R.id.input2);
        Button continueButton = findViewById(R.id.continueButton);

        // Action au clic du bouton
        continueButton.setOnClickListener(v -> {
            // TODO: Ajouter une action ici (par exemple passer à une autre activité)
        });
    }
}
