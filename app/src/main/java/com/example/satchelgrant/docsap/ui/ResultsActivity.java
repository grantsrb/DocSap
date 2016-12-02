package com.example.satchelgrant.docsap.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.satchelgrant.docsap.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultsActivity extends AppCompatActivity {
    private final String[] doctors = {"Dr. Osborn", "Dr. Dre", "Dr. Love", "Dr. DooLittle", "Dr. Octopus", "Dr. Metropolis"};
    @Bind(R.id.doctorList) ListView mDoctorList;
    @Bind(R.id.submittedSearch) TextView mSubmittedSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Typeface droidSans = Typeface.createFromAsset(getAssets(), "fonts/DroidSans.ttf");
        mSubmittedSearch.setTypeface(droidSans);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");

        mSubmittedSearch.setText("Results for " + search);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, doctors);
        mDoctorList.setAdapter(adapter);

        mDoctorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = ((TextView) view).getText().toString();
                text = text + " does not actually exist!";
                Toast.makeText(ResultsActivity.this, text, Toast.LENGTH_LONG).show();
            }
        });

    }
}