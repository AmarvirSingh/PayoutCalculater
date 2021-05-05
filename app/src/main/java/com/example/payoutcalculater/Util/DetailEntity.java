package com.example.payoutcalculater.Util;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DetailEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "Employer")
    private String Employer;

    @ColumnInfo(name = "day")

    private String day;

    @ColumnInfo(name = "hour")

    private int hour;


    public DetailEntity(String employer,  String day, int hour) {
        Employer = employer;
        this.day = day;
        this.hour = hour;
    }

    public DetailEntity() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployer() {
        return Employer;
    }

    public void setEmployer(String employer) {
        Employer = employer;
    }


    public String getDay() {
        return day;
    }

    public void setDay( String day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
