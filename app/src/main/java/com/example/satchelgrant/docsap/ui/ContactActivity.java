package com.example.satchelgrant.docsap.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.satchelgrant.docsap.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.gitHub) TextView mGitHub;
    @Bind(R.id.linkedIn) TextView mLinkedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

        mGitHub.setOnClickListener(this);
        mLinkedIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mGitHub) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/grantsrb"));
            startActivity(intent);
        } else if (v == mLinkedIn) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/grantsrb"));
            startActivity(intent);
        }
    }
}
