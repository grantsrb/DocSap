package com.example.satchelgrant.docsap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.usernameDisplay) TextView mUserDisplay;
    @Bind(R.id.submitSearch) Button mSubmitSearch;
    @Bind(R.id.search) EditText mSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mUserDisplay.setText(intent.getStringExtra("username"));
        mSubmitSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mSubmitSearch) {
            String searchText = mSearch.getText().toString();
            searchText = searchText.equals("") ? searchText : "No Search Terms";
            Intent newIntent = new Intent(SplashActivity.this, ResultsActivity.class);
            newIntent.putExtra("search", searchText);
            startActivity(newIntent);
        }
    }
}
