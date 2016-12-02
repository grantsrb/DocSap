package com.example.satchelgrant.docsap.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.satchelgrant.docsap.R;

import java.util.regex.Pattern;

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
    @Bind(R.id.queryInput) EditText mQuery;

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
        mAbout.setOnClickListener(this);
        mContact.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == mSubmitSearch) {
            String nameQuery = mNameQuery.getText().toString();
            String specialtyQuery = mSpecialtyQuery.getText().toString();
            String query = mQuery.getText().toString();

            // Form validation (uses regex to prevent non-alphanumeric characters"
            if(Pattern.matches(".*\\W.*|[a-zA-Z]{0}", nameQuery) && Pattern.matches(".*\\W.*|[a-zA-Z]{0}", specialtyQuery) && Pattern.matches(".*\\W.*|[a-zA-Z]{0}", query)) {
                Toast.makeText(SplashActivity.this, "At least one field must be filled! And only alphanumeric characters!", Toast.LENGTH_LONG).show();
            } else {
                Intent newIntent = new Intent(SplashActivity.this, ResultsActivity.class);
                newIntent.putExtra("name", nameQuery);
                newIntent.putExtra("specialty", specialtyQuery);
                newIntent.putExtra("query", query);
                startActivity(newIntent);
            }

        } else if (v == mAbout) {
            Intent newIntent = new Intent(SplashActivity.this, AboutActivity.class);
            startActivity(newIntent);
        } else if(v == mContact) {
            Intent newIntent = new Intent(SplashActivity.this, ContactActivity.class);
            startActivity(newIntent);
        }
    }
}
