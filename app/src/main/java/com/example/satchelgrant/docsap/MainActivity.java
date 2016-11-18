package com.example.satchelgrant.docsap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.login) Button mLogin;
    @Bind(R.id.username) EditText mUsername;
    @Bind(R.id.password) EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mLogin) {
            String userName = mUsername.getText().toString();
            String password = mPassword.getText().toString();
            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
            if(userName.equals("")) {
                intent.putExtra("username", "No Name");
            } else {
                intent.putExtra("username", userName);
            }
            startActivity(intent);
        }
    }
}
