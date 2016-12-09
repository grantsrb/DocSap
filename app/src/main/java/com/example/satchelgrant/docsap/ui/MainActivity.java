package com.example.satchelgrant.docsap.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.satchelgrant.docsap.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.registerTextView) TextView mRegisterTextView;
    @Bind(R.id.emailEditText) EditText mEmail;
    @Bind(R.id.passwordEditText) EditText mPassword;
    @Bind(R.id.passwordLoginButton) Button mLogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRegisterTextView.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        mLogin.setOnClickListener(this);
        setUpProgressDialog();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if(user != null) {
                    Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    public void loginWithCredentials() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        if (email.equals("")) {
            mEmail.setError("Please enter your email");
            return;
        }
        if (password.equals("")) {
            mPassword.setError("Password cannot be blank");
            return;
        }
        mProgressDialog.show();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mProgressDialog.dismiss();
                if(task.isSuccessful()) {
                    Log.d("Log In", "Success!");
                } else {
                    Log.w("Log In", "signInWithEmail", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setUpProgressDialog() {
        mProgressDialog =  new ProgressDialog(this);
        mProgressDialog.setTitle("Loading...");
        mProgressDialog.setMessage("Validating credentials...");
        mProgressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View view) {
        if (view == mRegisterTextView) {
            Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        } else if(view == mLogin) {
            loginWithCredentials();
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
        if(mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
