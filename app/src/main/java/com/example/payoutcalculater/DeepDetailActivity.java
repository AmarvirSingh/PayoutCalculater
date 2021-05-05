package com.example.payoutcalculater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.payoutcalculater.Models.DeepViewModel;
import com.example.payoutcalculater.Models.DetailViewModel;
import com.example.payoutcalculater.Util.DetailEntity;

import java.util.ArrayList;
import java.util.List;

public class DeepDetailActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tvTotal;
    Spinner selectionSpinner,fromSpinner, toSpinner;


    // variables
    ArrayList<String> months =  new ArrayList<>();
    ArrayList<String> employer = new ArrayList<>();
    String selectedToMonth, selectedFromMonth, selectedEmployer;
    private  DetailAdapter detailAdapter;

    private DeepViewModel deepViewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_detail);

        rv = findViewById(R.id.deepDetailRv);
        tvTotal = findViewById(R.id.totalTv);
        selectionSpinner = findViewById(R.id.selectionSpinner);
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);

        selectedEmployer = "Beckers";
        deepViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(DeepViewModel.class);

        addMonthAndEmployer();
        rv.setLayoutManager(new LinearLayoutManager(this));

        callViewModel();


       /* deepViewModel.getSpecificData(selectedEmployer).observe(this, new Observer<List<DetailEntity>>() {
            @Override
            public void onChanged(List<DetailEntity> entities) {
                detailAdapter = new DetailAdapter(getApplicationContext(),entities);
                rv.setAdapter(detailAdapter);
            }
        });
*/

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,employer);
        selectionSpinner.setAdapter(adapter);
        selectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedEmployer = employer.get(position);
                callViewModel();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,months);
        fromSpinner.setAdapter(adapter1);
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,months);
        toSpinner.setAdapter(adapter2);
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    private void callViewModel() {
        deepViewModel.specificInModelView(selectedEmployer).observe(this, new Observer<List<DetailEntity>>() {
            @Override
            public void onChanged(List<DetailEntity> entities) {
                detailAdapter = new DetailAdapter(getApplicationContext(),entities);
                rv.setAdapter(detailAdapter);
            }
        });
    }

    private void addMonthAndEmployer() {

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