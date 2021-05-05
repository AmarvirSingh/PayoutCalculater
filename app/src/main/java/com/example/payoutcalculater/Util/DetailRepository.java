package com.example.payoutcalculater.Util;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/*
 we can not perform room databse task in main thread
 because that can  freeze our application and
 application will crash so we will use ASync task to run database operations
 in the background
*/



public class DetailRepository {

    private DetailDao detailDao;
    private LiveData<List<DetailEntity>> allData;
    private LiveData<List<DetailEntity>> specificData;


    public DetailRepository (Application application){
        DetailDatabase database = DetailDatabase.getInstance(application);
        detailDao = database.detailDao();
       // allData = detailDao.getAllData();
    }

    public void insert(DetailEntity entity){
        // reate instacne of async task class and pass entity on which operation has to be performed
        new insertDetailAsyncTask(detailDao).execute(entity);
    }

    public LiveData<List<DetailEntity>> getAllData() {
        return detailDao.getAllData();
    }

    public LiveData<List<DetailEntity>> getSpecificData(String EmployerName){
        return detailDao.getData(EmployerName);
    }



    // static class of Async task
    public static class insertDetailAsyncTask extends AsyncTask<DetailEntity,Void,Void>{
        // create dao instance for performing database task
        private  DetailDao detailDao;

        // use constructor because we can not access instance directly because its static
        public insertDetailAsyncTask(DetailDao detailDao) {
            this.detailDao = detailDao;
        }

        @Override
        protected Void doInBackground(DetailEntity... detailEntities) {
            detailDao.insert(detailEntities[0]);
            return null;
        }
    }


}
