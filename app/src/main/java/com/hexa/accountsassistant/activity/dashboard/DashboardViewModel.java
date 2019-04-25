package com.hexa.accountsassistant.activity.dashboard;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hexa.accountsassistant.db.model.AccountHeads;
import com.hexa.accountsassistant.db.model.History;
import com.hexa.accountsassistant.db.repository.AccountHeadsRepository;
import com.hexa.accountsassistant.db.repository.HistoryRepository;
import com.hexa.accountsassistant.model.ExpenseVM;
import com.hexa.accountsassistant.model.VM;

import java.util.List;

public class DashboardViewModel extends AndroidViewModel {
    private HistoryRepository repository;
    private AccountHeadsRepository headsRepository;

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        repository = new HistoryRepository(application);
        headsRepository = new AccountHeadsRepository(application);
    }

    public LiveData<List<History>> getHistory(){
        return repository.getAll();
    }

    public LiveData<List<AccountHeads>> getHeads(){
        return headsRepository.getAll();
    }

    public LiveData<List<VM>> getAllData(){
        return repository.getAllData();
    }
}
