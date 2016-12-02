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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.login) Button mLogin;
    @Bind(R.id.username) EditText mUsername;
    @Bind(R.id.password) EditText mPassword;
    @Bind(R.id.title) TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface droidSans = Typeface.createFromAsset(getAssets(), "fonts/DroidSans.ttf");
        mTitle.setTypeface(droidSans);

        mLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == mLogin) {
            String userName = mUsername.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
            if(Pattern.matches(".*[^a-z&&[^A-Z&&[^\\s]]].*|[a-zA-Z]{0}", userName)) { // Uses regex to prevent non alphanumeric characters
                Toast.makeText(this, "Enter something in the username field! I don't care about the password yet!", Toast.LENGTH_LONG).show();
            } else {
                intent.putExtra("username", userName);
                startActivity(intent);
            }
        }
    }
}
