package com.example.gsb.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.gsb.Model.Praticien;
import com.example.gsb.Model.Visiteur;
import com.example.gsb.Repository.VisiteurRepository;

import java.util.List;

public class VisiteurViewModel extends ViewModel {

    private final VisiteurRepository repository;

    public VisiteurViewModel() {
        repository = new VisiteurRepository();
    }

    public LiveData<List<Praticien>> getPraticiens(String visiteurId) {
        return repository.getPraticiens(visiteurId);
    }
    public LiveData<Visiteur> getVisiteurById(String token, String id) {
        return repository.getVisiteurById(token, id);
    }
}