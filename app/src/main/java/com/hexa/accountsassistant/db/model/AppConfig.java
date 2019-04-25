package com.hexa.accountsassistant.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "app_config")
public class AppConfig {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String configName;
}
