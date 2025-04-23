package com.example.gsb_mars2025.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gsb_mars2025.databinding.ActivityPraticienBinding;

public class PraticienActivity extends AppCompatActivity {

    private ActivityPraticienBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPraticienBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}