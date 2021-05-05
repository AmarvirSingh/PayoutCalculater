package com.example.payoutcalculater.Models;

import android.app.Application;
import android.app.Presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.payoutcalculater.Util.DetailDao;
import com.example.payoutcalculater.Util.DetailEntity;
import com.example.payoutcalculater.Util.DetailRepository;

import java.util.List;

public class DeepViewModel extends AndroidViewModel {
    private DetailRepository repository;
    private LiveData<List<DetailEntity>> detailData;


    public DeepViewModel(@NonNull Application application) {
        super(application);
        repository = new DetailRepository(application);

    }

    public LiveData<List<DetailEntity>> specificInModelView(String Name){
        return repository.getSpecificData(Name);
    }
}
