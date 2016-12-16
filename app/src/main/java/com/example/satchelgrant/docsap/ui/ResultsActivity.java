package com.example.satchelgrant.docsap.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.satchelgrant.docsap.DoctorRecListAdapter;
import com.example.satchelgrant.docsap.R;
import com.example.satchelgrant.docsap.models.Doctor;
import com.example.satchelgrant.docsap.services.DoctorService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String Tag = ResultsActivity.class.getSimpleName();
    private String mName;
    private String mQuery;
    private String mSpecialty;
    private ArrayList<Doctor> mDoctors;
    private DoctorRecListAdapter mAdapter;

    @Bind(R.id.doctorRecycler) RecyclerView mRecyclerView;
    @Bind(R.id.submittedSearch) TextView mBanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Typeface droidSans = Typeface.createFromAsset(getAssets(), "fonts/DroidSans.ttf");

        Intent intent = getIntent();
        mQuery = intent.getStringExtra("query");
        mName = intent.getStringExtra("name");
        mSpecialty = intent.getStringExtra("specialty");

        findDoctors(mQuery, mName, mSpecialty);

    }

    public void findDoctors(String query, String name, String specialty) {
        final DoctorService service = new DoctorService();
        service.getDoctors(query, name, specialty, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mDoctors = service.processResponse(response);

                ResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(mDoctors.size() > 0){
                            mAdapter = new DoctorRecListAdapter(getApplicationContext(), mDoctors);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager=
                                    new LinearLayoutManager(ResultsActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
                        } else {
                            mRecyclerView.setVisibility(View.GONE);
                            mBanner.setText("No doctors exist with that search :(");
                        }

                    }
                });
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_home) {
            Intent intent = new Intent(ResultsActivity.this, SplashActivity.class);
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
