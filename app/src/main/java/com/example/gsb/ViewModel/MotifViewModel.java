package com.example.gsb.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.gsb.Model.Motif;
import com.example.gsb.Repository.MotifRepository;

import java.util.List;

public class MotifViewModel extends ViewModel {

    private final MotifRepository repository;

    public MotifViewModel() {
        repository = new MotifRepository();
    }

    public LiveData<List<Motif>> getMotifs() {
        return repository.getMotifs();
    }
}