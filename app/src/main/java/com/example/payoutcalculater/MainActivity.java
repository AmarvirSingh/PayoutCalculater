package com.example.payoutcalculater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.payoutcalculater.Models.DetailViewModel;
import com.example.payoutcalculater.Util.DetailEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> months =  new ArrayList<>();
    ArrayList<String> employer = new ArrayList<>();
    List<DetailEntity> dataList;
    String selectedMonth, selectedEmployer;

    // creating instace to our ViewModel
    private DetailViewModel viewModel;

    DetailAdapter RvAdapter;
    // views
    Spinner monthSpinner, employerSpinner;
    RecyclerView recyclerView;
    EditText etHours, etDay;
    Button btnAdd, btnDeepDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(DetailViewModel.class);

        monthSpinner = findViewById(R.id.monthSpinner);
        employerSpinner = findViewById(R.id.spinnerEmployer);
        recyclerView = findViewById(R.id.rv);
        etDay = findViewById(R.id.etDay);
        etHours = findViewById(R.id.etHours);
        btnAdd = findViewById(R.id.btnAddData);
        btnDeepDetail = findViewById(R.id.btnDeepDetail);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etDay.getText().toString().isEmpty()){
                    etDay.setError("Enter Day Of The Month");
                    etDay.requestFocus();
                    return;
                }
                if (etHours.getText().toString().isEmpty()){
                    etHours.setError("Enter Number Of Hours");
                    etHours.requestFocus();
                    return;
                }

                String day = String.valueOf(etDay.getText().toString()) + " "+ selectedMonth;
                int hour = Integer.parseInt(etHours.getText().toString());

                DetailEntity entity = new DetailEntity(selectedEmployer,day,hour);
                viewModel.insert(entity);

            }
        });


        btnDeepDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DeepDetailActivity.class));
            }
        });




        // adding data to spinners 
        addMonthsAndEmployer();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,months);
        monthSpinner.setAdapter(adapter);
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedMonth = months.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter employerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,employer);
        employerSpinner.setAdapter(employerAdapter);
        employerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedEmployer = employer.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // recycler view data
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        viewModel.getAllData().observe(this, new Observer<List<DetailEntity>>() {
            @Override
            public void onChanged(List<DetailEntity> detailEntities) {

                RvAdapter = new DetailAdapter(getApplicationContext(),detailEntities);
                recyclerView.setAdapter(RvAdapter);

              }
        });

      


    }

    private void addMonthsAndEmployer() {

        months.add("January");
        months.add("February");
        months.add("March");
        months.add("april");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

        employer.add("Beckers");
        employer.add("Tim Hortons");


    }
}