package com.shweta.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface UserDao {
    @Insert
    void Insert(PojoModel pojo);

    @Update
    void Update(PojoModel pojo);

    @Query("delete from PojoModel where id=:id")
    void delete (double id);

    @Query("Select * from PojoModel")
    List<PojoModel> getAllUsers();
}
