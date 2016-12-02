package com.example.satchelgrant.docsap;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.satchelgrant.docsap.ui.AboutActivity;
import com.example.satchelgrant.docsap.ui.ContactActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.usernameDisplay) TextView mUserDisplay;
    @Bind(R.id.submitSearch) Button mSubmitSearch;
    @Bind(R.id.about) Button mAbout;
    @Bind(R.id.contact) Button mContact;
    @Bind(R.id.nameQuery) EditText mNameQuery;
    @Bind(R.id.specialtyQuery) EditText mSpecialtyQuery;
    @Bind(R.id.welcome) TextView mWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Typeface droidSans = Typeface.createFromAsset(getAssets(), "fonts/DroidSans.ttf");
        mWelcome.setTypeface(droidSans);
        mUserDisplay.setTypeface(droidSans);

        Intent intent = getIntent();
        mUserDisplay.setText(intent.getStringExtra("username"));
        mSubmitSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == mSubmitSearch) {
            String nameQuery = mNameQuery.getText().toString();
            String specialtyQuery = mSpecialtyQuery.getText().toString();
            Intent newIntent = new Intent(SplashActivity.this, ResultsActivity.class);
            newIntent.putExtra("nameQuery", nameQuery);
            newIntent.putExtra("specialtyQuery", specialtyQuery);
            startActivity(newIntent);
        } else if (v == mAbout) {
            Intent newIntent = new Intent(SplashActivity.this, AboutActivity.class);
            startActivity(newIntent);
        } else if(v == mContact) {
            Intent newIntent = new Intent(SplashActivity.this, ContactActivity.class);
            startActivity(newIntent);
        }
    }
}
