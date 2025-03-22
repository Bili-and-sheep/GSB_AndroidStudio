package com.example.gsb_mars2025;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEmail = findViewById(R.id.input1); // Assurez-vous que input1 est bien l'email
        inputPassword = findViewById(R.id.input2); // Assurez-vous que input2 est bien le mot de passe
        continueButton = findViewById(R.id.continueButton);

        continueButton.setOnClickListener(v -> attemptLogin());
    }

    private void attemptLogin() {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Création de l'instance Retrofit avec getVisiteursInterface
        getVisiteursInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(getVisiteursInterface.class);
        LoginRequest loginRequest = new LoginRequest(email, password);

        Call<LoginResponse> call = apiService.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    Log.d("LOGIN_SUCCESS", "Token: " + loginResponse.getToken());

                    // Redirection vers l'écran suivant après connexion réussie
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("TOKEN", loginResponse.getToken()); // On passe le token à la prochaine activité
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("LOGIN_ERROR", "Réponse non réussie: " + response.errorBody());
                    Toast.makeText(MainActivity.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("LOGIN_ERROR", "Erreur: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Erreur de connexion au serveur", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
