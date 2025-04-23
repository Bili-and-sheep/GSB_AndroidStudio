package com.example.gsb_mars2025.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gsb_mars2025.databinding.ActivityCreatePraticienBinding;

public class CreatePraticienActivity extends AppCompatActivity {

    private ActivityCreatePraticienBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreatePraticienBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    };
}