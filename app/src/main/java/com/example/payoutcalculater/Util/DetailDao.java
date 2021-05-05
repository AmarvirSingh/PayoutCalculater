package com.example.payoutcalculater.Util;

import android.widget.LinearLayout;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DetailDao {

    @Insert
    void insert(DetailEntity entity);

    @Update
    void update(DetailEntity entity);

    @Delete
    void delete(DetailEntity entity);

    @Query("SELECT * FROM DetailEntity")
    LiveData<List<DetailEntity>> getAllData();

    @Query("SELECT * FROM detailentity WHERE Employer = :Employer")
    LiveData<List<DetailEntity>> getData(String Employer);


}
