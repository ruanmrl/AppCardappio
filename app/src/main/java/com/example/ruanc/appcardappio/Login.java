package com.example.ruanc.appcardappio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    final public static String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void entraConta(View v) {
        EditText Email = (EditText) findViewById(R.id.txtEmail);
        EditText Senha = (EditText) findViewById(R.id.txtPwd);

        String email = String.valueOf(Email.getText());
        String password = String.valueOf(Senha.getText());

        String user = String.valueOf(Email.getText());

        if (email.matches("") || password.matches("")) {
            Toast.makeText(this, "You did not enter a username or password", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Login.this, "Verifying...", Toast.LENGTH_SHORT).show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail", task.getException());
                                Toast.makeText(Login.this, "Autentication Problem", Toast.LENGTH_SHORT).show();

                            } else {

                                Toast.makeText(Login.this, "Logged in", Toast.LENGTH_SHORT).show();

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                Intent myIntent = new Intent(Login.this, Home.class);
                                startActivity(myIntent);
                            }
                        }
                    });
        }

    }
}

