package com.example.satchelgrant.docsap.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.satchelgrant.docsap.R;
import com.example.satchelgrant.docsap.services.DoctorService;

import java.io.IOException;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String Tag = ResultsActivity.class.getSimpleName();
    private String mName;
    private String mQuery;
    private String mSpecialty;


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
                Log.d(Tag, response.body().string());
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
