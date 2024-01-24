package com.shweta.roomdatabase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PojoModel.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao getDao();
    public static UserDatabase INSTANCE;
    public static UserDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,UserDatabase.class,"userDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
