package com.example.payoutcalculater.Util;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = DetailEntity.class,version = 1)
public abstract class DetailDatabase extends RoomDatabase {

    // creating singleton instance
    public static DetailDatabase instance;

    // creating abstract method which we will use to call dao functions from DetailDao
    public abstract DetailDao detailDao();

    //needed function
    public static synchronized DetailDatabase getInstance(Context context){
    if (instance == null){
        instance = Room.databaseBuilder(context.getApplicationContext(),
                DetailDatabase.class,
                "Detail_database")
                .fallbackToDestructiveMigration()
                .addCallback(roomCallback) // this will populate our room database when application first creatred
                .build();
    }
    return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new FirstDataAsyncTask(instance).execute();
        }
    };


    private static class FirstDataAsyncTask extends AsyncTask<Void, Void, Void>{

        private DetailDao detailDao;

        public FirstDataAsyncTask(DetailDatabase database){
            detailDao = database.detailDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {

            detailDao.insert(new DetailEntity("Becker", "24 April",7));
            detailDao.insert(new DetailEntity("Tim Horton", "25 April",8));

            return null;
        }
    }


}
