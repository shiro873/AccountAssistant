package com.hexa.accountsassistant.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.hexa.accountsassistant.constant.ConstantVars;
import com.hexa.accountsassistant.db.dao.AccountHeadsDao;
import com.hexa.accountsassistant.db.dao.AppConfigDao;
import com.hexa.accountsassistant.db.dao.HistoryDao;
import com.hexa.accountsassistant.db.model.AccountHeads;
import com.hexa.accountsassistant.db.model.AppConfig;
import com.hexa.accountsassistant.db.model.History;

import java.util.concurrent.Executors;

@Database(entities = {AccountHeads.class, History.class, AppConfig.class},
        version = 1,
        exportSchema = false)
public abstract class AssistanceDB extends RoomDatabase{
    public abstract AccountHeadsDao accountHeadsDao();
    public abstract HistoryDao historyDao();
    public abstract AppConfigDao configDao();

    private static AssistanceDB INSTANCE;

    public static AssistanceDB getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AssistanceDB.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AssistanceDB.class,
                        "SrLocation")
                        .addCallback(new RoomDatabase.Callback() {
                            @Override
                            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                super.onOpen(db);
                                Executors.newSingleThreadExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        INSTANCE.accountHeadsDao().insert( new AccountHeads("House Rent", new ConstantVars(context).getRentColor(), false));
                                        INSTANCE.accountHeadsDao().insert( new AccountHeads("Food", new ConstantVars(context).getFoodColor(), false));
                                        INSTANCE.accountHeadsDao().insert( new AccountHeads("Vehicle Fair", new ConstantVars(context).getVehicleColor(), false));
                                        INSTANCE.accountHeadsDao().insert( new AccountHeads("Loan", new ConstantVars(context).getLoanColor(), false));
                                        INSTANCE.accountHeadsDao().insert( new AccountHeads("Others Cost", new ConstantVars(context).getOthersCostColor(), false));
                                        INSTANCE.accountHeadsDao().insert( new AccountHeads("Utility Cost", new ConstantVars(context).getUtilityColor(), false));
                                        INSTANCE.accountHeadsDao().insert( new AccountHeads("Salary", new ConstantVars(context).getSalaryColor(), true));
                                        INSTANCE.accountHeadsDao().insert( new AccountHeads("Payment", new ConstantVars(context).getPaymentColor(), true));
                                        INSTANCE.accountHeadsDao().insert( new AccountHeads("Other Income", new ConstantVars(context).getAllowanceColor(), true));
                                        INSTANCE.accountHeadsDao().insert( new AccountHeads("Other Income", new ConstantVars(context).getOthersIncomeColor(), true));
                                        INSTANCE.accountHeadsDao().insert( new AccountHeads("Borrowed Money", new ConstantVars(context).getBorrowedMoneyColor(), true));
                                    }
                                });
                            }
                        }).build();
            }
        }
        return INSTANCE;
    }
}
