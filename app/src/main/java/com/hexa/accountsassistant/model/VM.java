package com.hexa.accountsassistant.model;

public class VM {
    public String headName;
    public boolean receivable;
    public int colorCode;
    public String dateTime;
    public double amount;
    public boolean received;

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

    public int getColorCode() {
        return colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }
}
