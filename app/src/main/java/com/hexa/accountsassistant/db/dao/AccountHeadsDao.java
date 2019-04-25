package com.hexa.accountsassistant.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.hexa.accountsassistant.db.model.AccountHeads;

import java.util.List;

@Dao
public interface AccountHeadsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AccountHeads accountHeads);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(AccountHeads... accountHeads);

    @Query("DELETE FROM account_head")
    void deleteAll();

    @Query("SELECT * from account_head")
    LiveData<List<AccountHeads>> getAll();

    @Query("select * from account_head where headName = :headName")
    LiveData<List<AccountHeads>> getById(String headName);

    @Query("select * from account_head where id = :id limit 1")
    LiveData<AccountHeads> getById(int id);
}
