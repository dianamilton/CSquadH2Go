package com.example.dianamilton.h2go;

/**
 * Created by dianamilton on 2/19/17.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;
import java.util.List;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Bind;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity"; //defines activity
    private Spinner userSpinner; //defines user spinner

    //defines id as xml variable
    @Bind(R.id.input_name) EditText _nameText;
    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_signup) Button _signupButton;
    @Bind(R.id.link_login) TextView _loginLink;

    /**
     * creating spinner, buttons, links
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        userSpinner = (Spinner) findViewById(R.id.spinner_type); //assign spinner variable
        List<SpinnerEnum> list = new ArrayList<SpinnerEnum>(); //inputs enum as array list
        /**
         * fills spinner with enum by part
         */
        list.add(SpinnerEnum.User);
        list.add(SpinnerEnum.Admin);
        list.add(SpinnerEnum.Manager);
        list.add(SpinnerEnum.Worker);
        ArrayAdapter<SpinnerEnum> dataAdapter = new ArrayAdapter<SpinnerEnum>(this, android.R.layout.simple_spinner_item, list); //layout defining spinner
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //layout defining spinner
        userSpinner.setAdapter(dataAdapter); //set spinner

        /**
         * sets up Sign Up Button, calls for signup method
         */
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        /**
         * sets up Login link, once clicked, sends to login activtiy
         */
        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    /**
     * method defining signup()
     */
    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) { //if does not meet email or passcode requirements, sign up fails
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false); //instantiate button

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account..."); //popup for when registration passes
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess(); //if signup works correctly
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    /**
     * if registration is successful, log user/worker/etc into app, send to main activity page
     * by defining intent
     */
    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * if registration fails, show error
     */
    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    /**
     * checks if signup registration user, passcode is acceptable under requirements
     * @return
     */
    public boolean validate() {
        boolean valid = true;

        // allows user to input
        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}