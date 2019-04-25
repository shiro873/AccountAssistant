package com.hexa.accountsassistant.activity.entry;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hexa.accountsassistant.R;
import com.hexa.accountsassistant.activity.dashboard.DashboardActivity;
import com.hexa.accountsassistant.db.model.AccountHeads;
import com.hexa.accountsassistant.db.model.History;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import solid.collections.SolidList;

import static solid.collectors.ToSolidList.toSolidList;
import static solid.stream.Stream.stream;

public class EntryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText amountInput;
    Spinner spinner;
    Button button;

    EntryViewModel viewModel;

    List<AccountHeads> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        viewModel = ViewModelProviders.of(this).get(EntryViewModel.class);

        amountInput = findViewById(R.id.amount);
        spinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertHistory();
            }
        });

        spinner.setOnItemSelectedListener(this);
        list = new ArrayList<>();

        initSpinner();
    }

    private void initSpinner(){
        LiveData<List<AccountHeads>> getHeads = viewModel.getHeads();
        getHeads.observe(this, new Observer<List<AccountHeads>>() {
            @Override
            public void onChanged(@Nullable List<AccountHeads> accountHeads) {
                list = accountHeads;
                SolidList<String> headList = stream(accountHeads).map(AccountHeads::getHeadName).collect(toSolidList());
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(EntryActivity.this, android.R.layout.simple_spinner_item, headList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);
                spinner.setSelection(0);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void insertHistory(){
        if(TextUtils.isDigitsOnly(amountInput.getText().toString())){
            LiveData<List<AccountHeads>> getHeadsDetails = viewModel.getHeadDetails(spinner.getSelectedItem().toString());
            getHeadsDetails.observe(this, new Observer<List<AccountHeads>>() {
                @Override
                public void onChanged(@Nullable List<AccountHeads> accountHeads) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
                    String currentDate = sdf.format(new Date());
                    History history = new History();
                    history.setDateTime(currentDate);
                    history.setAmount(Double.parseDouble(amountInput.getText().toString()));
                    boolean isReceived = false;
                    for (AccountHeads head: list) {
                        if(head.getHeadName().equals(history.getHead()))
                            isReceived = head.isReceivable();
                    }
                    history.setHead(accountHeads.get(0).getId());
                    //isReceived = stream(list).filter(headName -> headName.equals(history.getHead())).collect(toSolidList()).get(0).isReceivable();
                    history.setReceived(isReceived);
                    viewModel.insertHistory(history);
                    Toast.makeText(EntryActivity.this, "Saved Successfully", Toast.LENGTH_LONG).show();
                    updateUI();
                }
            });
        }else {
            Toast.makeText(this, "Please select type", Toast.LENGTH_LONG).show();
        }
    }

    public void updateUI(){
        Intent intent = new Intent(EntryActivity.this, DashboardActivity.class);
        startActivity(intent);
    }
}
