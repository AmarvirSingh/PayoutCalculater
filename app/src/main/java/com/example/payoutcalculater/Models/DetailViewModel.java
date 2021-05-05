package com.example.payoutcalculater.Models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.payoutcalculater.Util.DetailEntity;
import com.example.payoutcalculater.Util.DetailRepository;

import java.util.List;

public class DetailViewModel extends AndroidViewModel {

    private final DetailRepository repository;
    private final LiveData<List<DetailEntity>> allData;
    private LiveData<List<DetailEntity>> specificData;
    private String name;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        repository = new DetailRepository(application);
        allData = repository.getAllData();

    }

    // ,methods to be called to perform database operations
    public void insert(DetailEntity entity){
        repository.insert(entity);
    }

    public LiveData<List<DetailEntity>> getAllData(){
        return repository.getAllData();
    }


}
