package com.example.gsb.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.gsb.Model.Visite;
import com.example.gsb.Repository.VisiteRepository;

import java.util.List;

public class VisiteViewModel extends ViewModel {

    private final VisiteRepository repository;

    public VisiteViewModel() {
        repository = new VisiteRepository();
    }

    public LiveData<List<Visite>> getVisitesByPraticien(String praticienId) {
        return repository.getVisitesByPraticien(praticienId);
    }
}