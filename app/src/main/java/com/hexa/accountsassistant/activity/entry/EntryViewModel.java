package com.hexa.accountsassistant.activity.entry;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hexa.accountsassistant.db.model.AccountHeads;
import com.hexa.accountsassistant.db.model.History;
import com.hexa.accountsassistant.db.repository.AccountHeadsRepository;
import com.hexa.accountsassistant.db.repository.HistoryRepository;

import java.util.List;

public class EntryViewModel extends AndroidViewModel {
    AccountHeadsRepository repository;
    HistoryRepository historyRepository;

    public EntryViewModel(@NonNull Application application) {
        super(application);
        repository = new AccountHeadsRepository(application);
        historyRepository = new HistoryRepository(application);
    }

    public LiveData<List<AccountHeads>> getHeads(){
        return repository.getAll();
    }

    public void insertHistory(History history){
        historyRepository.insert(history);
    }

    public LiveData<List<AccountHeads>> getHeadDetails(String headName){
        return repository.getHeads(headName);
    }
}
