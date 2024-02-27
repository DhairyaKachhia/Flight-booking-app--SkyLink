package com.example.skylink.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skylink.R;
import com.example.skylink.business.UserHandler;
import com.example.skylink.business.validations.IValidateUserAuth;
import com.example.skylink.business.validations.ValidateUserAuth;
import com.example.skylink.objects.UserProperties;

public class SignUpActivity extends AppCompatActivity {

    private EditText fullname, email, password, retypePassword;
    private TextView signIn;
    private Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullname = findViewById(R.id.etFullname);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        retypePassword = findViewById(R.id.etRePassword);



        signIn = findViewById(R.id.tvSignInClick);
        signIn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(intent);
        });



        signUp = findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(v -> {

            if (validInputs()) {

                String userFullname = fullname.getText().toString();
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                String userRePassword = retypePassword.getText().toString();


                UserProperties user = new UserProperties(userFullname, userEmail, userPassword);
                UserHandler handler = new UserHandler();

                if (handler.createUser(user, userRePassword)) {
                    Intent intent = new Intent(SignUpActivity.this, UpdateUserProfileActivity.class);
                    startActivity(intent);
                } else {
                    // Handle user creation failure, show an error message, etc.
                    Toast.makeText(SignUpActivity.this, "Unable to create new user", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }

    private boolean validInputs() {
        boolean isValid = true;

        IValidateUserAuth validateUserAuth = new ValidateUserAuth();
        String error = "";

        error = validateUserAuth.validFullname(fullname.getText().toString());
        if (!error.isEmpty()) {
            fullname.setError(error);
            isValid = false;
        }

        error = validateUserAuth.validEmail(email.getText().toString());
        if (!error.isEmpty()) {
            email.setError(error);
            isValid = false;
        }

        error = validateUserAuth.validPassword(password.getText().toString());
        if (!error.isEmpty()) {
            password.setError(error);
            isValid = false;
        }

        error = validateUserAuth.validRePassword(password.getText().toString(), retypePassword.getText().toString());
        if (!error.isEmpty()) {
            retypePassword.setError(error);
            isValid = false;
        }


        return isValid;
    }
}