package com.hexa.accountsassistant.activity.dashboard;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hexa.accountsassistant.R;
import com.hexa.accountsassistant.activity.dashboard.adapter.ColorsAdapter;
import com.hexa.accountsassistant.activity.entry.EntryActivity;
import com.hexa.accountsassistant.constant.ConstantVars;
import com.hexa.accountsassistant.db.model.AccountHeads;
import com.hexa.accountsassistant.db.model.History;
import com.hexa.accountsassistant.model.ExpenseVM;
import com.hexa.accountsassistant.model.VM;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    DashboardViewModel model;

    PieChart mPieChart;
    RecyclerView colorRecyclerList;
    TextView totalIncome, totalExpense, remainingBalance, percentage;
    List<AccountHeads> headsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        model = ViewModelProviders.of(this).get(DashboardViewModel.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, EntryActivity.class);
                startActivity(intent);
            }
        });

        mPieChart = findViewById(R.id.piechart);
        colorRecyclerList = findViewById(R.id.colorRecyclerList);
        totalExpense = findViewById(R.id.total_expense);
        totalIncome = findViewById(R.id.total_income);
        remainingBalance = findViewById(R.id.total_remaining_balance);
        percentage = findViewById(R.id.total_percentage);

        setupData();
    }

    private void setupData(){
        headsList = new ArrayList<>();
        LiveData<List<AccountHeads>> getHeads = model.getHeads();
        getHeads.observe(this, new Observer<List<AccountHeads>>() {
            @Override
            public void onChanged(@Nullable List<AccountHeads> accountHeads) {
                headsList = accountHeads;
                ColorsAdapter adapter = new ColorsAdapter(accountHeads);
                colorRecyclerList.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));
                colorRecyclerList.setAdapter(adapter);
            }
        });
        LiveData<List<VM>> historyLiveData = model.getAllData();
        historyLiveData.observe(this, new Observer<List<VM>>() {
            @Override
            public void onChanged(@Nullable List<VM> vms) {
                List<PieModel> pieModels = new ArrayList<>();
                ExpenseVM expenseVM = new ExpenseVM();
                expenseVM.setTotalExpense(0);
                expenseVM.setTotalIncome(0);
                expenseVM.setPercentage(0);
                expenseVM.setRemainingBalance(0);
                for (VM vm:vms) {
                    pieModels.add(new PieModel(vm.getHeadName(), Float.valueOf(String.valueOf(vm.getAmount())), vm.getColorCode()));
                    if(vm.isReceivable())
                        expenseVM.totalIncome += vm.getAmount();
                    else
                        expenseVM.totalExpense += vm.getAmount();
                }
                expenseVM.remainingBalance = expenseVM.totalIncome - expenseVM.totalExpense;
                expenseVM.percentage = (expenseVM.totalExpense/expenseVM.totalIncome)*100;
                setUpData(pieModels , expenseVM);
            }
        });
    }

    private void setUpData(List<PieModel> models, ExpenseVM vm){
        for (PieModel model : models) {
            mPieChart.addPieSlice(model);
        }

        mPieChart.setUseInnerValue(true);
        mPieChart.setInnerValueSize(Float.valueOf(String.valueOf(vm.totalIncome)));
        mPieChart.setInnerValueString("Monthly Review");
        mPieChart.setInnerValueColor(R.color.primaryColorDarkTheme);
        mPieChart.startAnimation();

        totalExpense.setText(vm.getTotalExpense()+"");
        totalIncome.setText(vm.getTotalIncome()+"");
        remainingBalance.setText(vm.getRemainingBalance()+"");
        if(vm.totalExpense != 0){
            percentage.setText(vm.getPercentage()+"%");
        }else {
            percentage.setText("0%");
        }

    }
}
