package com.example.skylink.presentation.User_Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skylink.R;
import com.example.skylink.application.Services;
import com.example.skylink.objects.Session;
import com.example.skylink.business.Interface.IUserHandler;
import com.example.skylink.business.Implementations.UserHandler;
import com.example.skylink.business.validations.IValidateUserAuth;
import com.example.skylink.business.validations.ValidateUserAuth;
import com.example.skylink.objects.Interfaces.IUserProperties;
import com.example.skylink.objects.Implementations.UserProperties;

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

                IUserProperties user = new UserProperties(userFullname, userEmail, userPassword);
                IUserHandler handler = new UserHandler(Services.getUserDatabase());

                try {
                    handler.createUser(user, userRePassword);
                    Session.getInstance().setEmail(userEmail);
                    Intent intent = new Intent(SignUpActivity.this, UpdateUserProfileActivity.class);
                    startActivity(intent);
                } catch (UserHandler.UserCreationException e) {
                    Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // client side validation..
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