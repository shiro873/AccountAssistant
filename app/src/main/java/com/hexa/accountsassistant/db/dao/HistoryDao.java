package com.hexa.accountsassistant.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.hexa.accountsassistant.db.model.AccountHeads;
import com.hexa.accountsassistant.db.model.History;
import com.hexa.accountsassistant.model.VM;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(History history);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(History... historys);

    @Query("DELETE FROM history")
    void deleteAll();

    @Query("SELECT * from history")
    LiveData<List<History>> getAll();

    @Query("select ac.headName, ac.colorCode, ac.receivable," +
            "h.amount, h.dateTime, h.received from history h inner join account_head ac on ac.id = h.headId")
    LiveData<List<VM>> getAllData();

    @Query("select sum(amount) from history where received= :is_received")
    LiveData<Double> getByReceived(boolean is_received);
}
