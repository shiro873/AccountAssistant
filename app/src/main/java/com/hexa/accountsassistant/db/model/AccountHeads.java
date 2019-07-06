package com.hexa.accountsassistant.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "account_head", indices = @Index(value = {"headName"}, unique = true))
public class AccountHeads {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "headName")
    public String headName;
    public boolean receivable;
    public int colorCode;

    public AccountHeads(){}

    public AccountHeads(String headName, int colorCode, boolean receivable){
        this.headName = headName;
        this.receivable = receivable;
        this.colorCode = colorCode;
    }

    public int getColorCode() {
        return colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public boolean isReceivable() {
        return receivable;
    }

    public void setReceivable(boolean receivable) {
        this.receivable = receivable;
    }
}
