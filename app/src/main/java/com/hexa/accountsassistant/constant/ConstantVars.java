package com.hexa.accountsassistant.constant;

import android.content.Context;
import android.graphics.Color;

import com.hexa.accountsassistant.R;

public class ConstantVars {
    private Context context;

    public ConstantVars(Context context){
        this.context = context;
    }

    public int getRentColor(){
        return context.getResources().getColor(R.color.dakrBlue);
    }

    public int getFoodColor(){
        return context.getResources().getColor(R.color.purple);
    }
    public int getVehicleColor(){
        return context.getResources().getColor(R.color.slightlyLightBlue);
    }
    public int getLoanColor(){
        return context.getResources().getColor(R.color.lightPurple);
    }
    public int getOthersCostColor(){
        return context.getResources().getColor(R.color.lightIndigo);
    }
    public int getUtilityColor(){
        return context.getResources().getColor(R.color.lightIndigo2);
    }
    public int getSalaryColor(){
        return context.getResources().getColor(R.color.lightGreen);
    }
    public int getPaymentColor(){
        return context.getResources().getColor(R.color.darkPasteGreen);
    }
    public int getOthersIncomeColor(){
        return context.getResources().getColor(R.color.lightPaste);
    }
    public int getAllowanceColor(){
        return context.getResources().getColor(R.color.bottleGreen);
    }
    public int getBorrowedMoneyColor(){
        return context.getResources().getColor(R.color.darkBottleGreen);
    }

    public static final String rentColor = "#280680";
    public static final String foodColor = "#5e35b1";
    public static final String vehicleColor = "#311b92";
    public static final String loanColor = "#9162e4";
    public static final String othersCostColor = "#3949ab";
    public static final String utilityColor = "#338a3e";
    public static final String salaryColor = "#98ee99";
    public static final String paymentColor = "#2e7d32";
    public static final String othersIncomeColor = "#005005";
    public static final String AllowanceColor = "#7d85ff";
    public static final String BorrowedMoneyColor = "#7d85ff";

}
