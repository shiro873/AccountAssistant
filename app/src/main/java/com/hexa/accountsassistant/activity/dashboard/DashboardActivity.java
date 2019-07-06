package com.hexa.accountsassistant.activity.dashboard;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {

    DashboardViewModel model;
    FloatingActionButton fabAdd;
    ProgressBar pieChart;
    TextView incomeText, expenseText, percentage, monthYear;
    double income = 0, expense = 0;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        model = ViewModelProviders.of(this).get(DashboardViewModel.class);

        fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, EntryActivity.class));
            }
        });
        pieChart = findViewById(R.id.stats_progressbar);
        incomeText = findViewById(R.id.income_amount);
        expenseText = findViewById(R.id.expense_amount);
        percentage = findViewById(R.id.percentage_text);
        monthYear = findViewById(R.id.month_year);

        setData();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            System.exit(0);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void setData(){
        LiveData<Double> getIncome = model.getByReceived(true);
        getIncome.observe(this, new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double aDouble) {
                if(aDouble != null){
                    income = aDouble;
                    updateChart();
                }

            }
        });
        LiveData<Double> getExpense = model.getByReceived(false);
        getExpense.observe(this, new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double aDouble) {
                if(aDouble != null){
                    expense = aDouble;
                    updateChart();
                }
            }
        });
    }


    public void updateChart(){
        if(income != 0){
            double d = expense / income;
            int progress = (int) (d * 100);
            pieChart.setProgress(progress);
            incomeText.setText(income+"");
            expenseText.setText(expense+"");
            percentage.setText(progress+"%");
            monthYear.setText(getMonthForInt());
        }else {
            pieChart.setProgress(0);
            incomeText.setText(0+"");
            expenseText.setText(0+"");
            monthYear.setText(getMonthForInt());
        }
    }

    String getMonthForInt() {
        Calendar mCalendar;
        mCalendar = Calendar.getInstance();
        String month = mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        int year = mCalendar.get(Calendar.YEAR);
        return month + "-"+ year;
    }

}
