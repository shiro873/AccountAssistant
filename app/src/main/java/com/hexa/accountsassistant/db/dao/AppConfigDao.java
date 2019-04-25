package com.hexa.accountsassistant.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.hexa.accountsassistant.db.model.AppConfig;

import java.util.List;

@Dao
public interface AppConfigDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AppConfig accountHeads);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(AppConfig... accountHeads);

    @Query("DELETE FROM app_config")
    void deleteAll();

    @Query("SELECT * from app_config")
    LiveData<List<AppConfig>> getAll();
}
