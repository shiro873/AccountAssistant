package com.hexa.accountsassistant.activity;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.hexa.accountsassistant.db.AssistanceDB;
import com.hexa.accountsassistant.db.model.AccountHeads;
import com.hexa.accountsassistant.db.repository.AccountHeadsRepository;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AssistanceDB.getDatabase(this);
    }
}
