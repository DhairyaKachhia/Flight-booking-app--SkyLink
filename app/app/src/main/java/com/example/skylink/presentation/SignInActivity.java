package com.example.skylink.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.skylink.R;
import com.example.skylink.business.UserHandler;
import com.example.skylink.business.validations.IValidateUserAuth;
import com.example.skylink.business.validations.ValidateUserAuth;
import com.example.skylink.objects.UserProperties;
import com.example.skylink.business.IUsesHandler;
public class SignInActivity extends AppCompatActivity {

    private EditText email, password;
    private TextView singUp;
    private Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);


        singUp = findViewById(R.id.tvSignInClick);

        singUp.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);

        });


        signIn = findViewById(R.id.btnSignIn);

        signIn.setOnClickListener(v -> {

            if (isValid()) {
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                UserProperties user = new UserProperties(userEmail,userPassword);
                UserHandler checkUser = new UserHandler();
                if(checkUser.signinUser(user)){
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean isValid(){
        boolean isValid = true;

        IValidateUserAuth validateUserAuth = new ValidateUserAuth();
        String error = "";

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


        return isValid;
    }
}