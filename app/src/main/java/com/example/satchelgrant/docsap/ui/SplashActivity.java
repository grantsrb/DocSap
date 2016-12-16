package com.example.satchelgrant.docsap.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.satchelgrant.docsap.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.usernameDisplay) TextView mUserDisplay;
    @Bind(R.id.submitSearch) Button mSubmitSearch;
    @Bind(R.id.nameQuery) EditText mNameQuery;
    @Bind(R.id.specialtyQuery) EditText mSpecialtyQuery;
    @Bind(R.id.welcome) TextView mWelcome;
    @Bind(R.id.queryInput) EditText mQuery;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Typeface droidSans = Typeface.createFromAsset(getAssets(), "fonts/DroidSans.ttf");
        mWelcome.setTypeface(droidSans);
        mUserDisplay.setTypeface(droidSans);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPrefs.edit();
        String recentAilment = mPrefs.getString("ailment", "Reason for needing doctor");
        String recentName = mPrefs.getString("name", "Doctor name");
        String recentSpecialty = mPrefs.getString("specialty", "Doctor specialty");
        if(!recentAilment.isEmpty())
            mQuery.setHint(recentAilment);
        if(!recentName.isEmpty())
            mNameQuery.setHint(recentName);
        if(!recentSpecialty.isEmpty())
            mSpecialtyQuery.setHint(recentSpecialty);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if(user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName());
                }
            }
        };

        Intent intent = getIntent();
        mUserDisplay.setText(intent.getStringExtra("username"));
        mSubmitSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == mSubmitSearch) {
            String nameQuery = mNameQuery.getText().toString().trim();
            String specialtyQuery = mSpecialtyQuery.getText().toString().trim();
            String query = mQuery.getText().toString().trim();

            // Form validation (uses regex to prevent non-alphanumeric characters"
            if(Pattern.matches(".*\\W.*|[a-zA-Z]{0}", nameQuery) && Pattern.matches(".*\\W.*|[a-zA-Z]{0}", specialtyQuery) && Pattern.matches(".*\\W.*|[a-zA-Z]{0}", query)) {
                Toast.makeText(SplashActivity.this, "At least one field must be filled, no spaces, and no punctuation!", Toast.LENGTH_LONG).show();
            } else {
                mEditor.putString("ailment", query).apply();
                mEditor.putString("name", nameQuery).apply();
                mEditor.putString("specialty", specialtyQuery).apply();
                Intent newIntent = new Intent(SplashActivity.this, ResultsActivity.class);
                newIntent.putExtra("name", nameQuery);
                newIntent.putExtra("specialty", specialtyQuery);
                newIntent.putExtra("query", query);
                startActivity(newIntent);
            }

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener != null)
            mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_logout) {
            logout();
            return true;
        } else if(id == R.id.action_about) {
            Intent intent = new Intent(SplashActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        } else if(id == R.id.action_contact) {
            Intent intent = new Intent(SplashActivity.this, ContactActivity.class);
            startActivity(intent);
            return true;
        } else if(id == R.id.action_reviews) {
            Intent intent = new Intent(SplashActivity.this, ReviewedDoctorsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}
