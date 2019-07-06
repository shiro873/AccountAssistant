package com.hexa.accountsassistant.db.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.hexa.accountsassistant.activity.App;
import com.hexa.accountsassistant.db.AssistanceDB;
import com.hexa.accountsassistant.db.dao.HistoryDao;
import com.hexa.accountsassistant.db.model.History;
import com.hexa.accountsassistant.model.VM;

import java.util.List;

public class HistoryRepository {
    private HistoryDao dao;

    public HistoryRepository(Application application){
        dao = AssistanceDB.getDatabase(application.getApplicationContext()).historyDao();
    }

    public void insert(final History history){
       new Thread(new Runnable() {
           @Override
           public void run() {
                dao.insert(history);
           }
       }).start();
    }

    public void delete(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.deleteAll();
            }
        }).start();
    }

    public LiveData<List<History>> getAll(){
        return dao.getAll();
    }

    public void update(History history){

    }

    public LiveData<List<VM>> getAllData(){
        return dao.getAllData();
    }

    public LiveData<Double> getByReceived(boolean isReceived){
        return dao.getByReceived(isReceived);
    }
}
