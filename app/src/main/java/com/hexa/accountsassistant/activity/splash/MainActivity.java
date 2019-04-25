package com.hexa.accountsassistant.activity.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hexa.accountsassistant.R;
import com.hexa.accountsassistant.activity.dashboard.DashboardActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
        startActivity(intent);
    }
}
