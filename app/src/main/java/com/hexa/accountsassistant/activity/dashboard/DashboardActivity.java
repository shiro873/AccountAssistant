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
import android.widget.ProgressBar;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        model = ViewModelProviders.of(this).get(DashboardViewModel.class);

        try{
            Thread.sleep(500);
            startActivity(new Intent(DashboardActivity.this, EntryActivity.class));
        }catch (Exception e){

        }
    }


    public void updateChart(){
        // Update the text in a center of the chart:
        //TextView numberOfCals = findViewById(R.id.number_of_calories);
        //numberOfCals.setText(String.valueOf(calsBurned) + " / " + calsConsumed);

        // Calculate the slice size and update the pie chart:
        /*ProgressBar pieChart = findViewById(R.id.stats_progressbar);
        double d = (double) calsBurned / (double) calsConsumed;
        int progress = (int) (d * 100);
        pieChart.setProgress(progress);*/
    }

}
