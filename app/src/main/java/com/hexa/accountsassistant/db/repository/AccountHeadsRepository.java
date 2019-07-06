package com.hexa.accountsassistant.db.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.hexa.accountsassistant.db.AssistanceDB;
import com.hexa.accountsassistant.db.dao.AccountHeadsDao;
import com.hexa.accountsassistant.db.model.AccountHeads;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AccountHeadsRepository {
    private AccountHeadsDao dao;

    public AccountHeadsRepository(Application application){
        dao = AssistanceDB.getDatabase(application.getApplicationContext()).accountHeadsDao();
    }

    public void insert(final AccountHeads accountHeads){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(accountHeads);
            }
        });
    }

    public void delete(){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteAll();
            }
        });
    }

    public LiveData<List<AccountHeads>> getAll(){
        return dao.getAll();
    }

    public void update(AccountHeads accountHeads){

    }

    public LiveData<List<AccountHeads>> getHeads(String headName){
        return dao.getById(headName);
    }

    public LiveData<List<AccountHeads>> getHeadsByReceivable(boolean headName){
        return dao.getByReceivable(headName);
    }

    public LiveData<AccountHeads> getHead(int id){
        return dao.getById(id);
    }
}
