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
import com.example.skylink.business.Implementations.UserHandler;
import com.example.skylink.business.validations.IValidateUserAuth;
import com.example.skylink.business.validations.ValidateUserAuth;
import com.example.skylink.objects.Implementations.UserProperties;
import com.example.skylink.presentation.FlightSearching.MainActivity;

public class SignInActivity extends AppCompatActivity {

    private EditText email, password;
    private TextView singUp;
    private Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Services.setup(this);

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
//                if(checkUser.signinUser(user)){
                if(true){
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignInActivity.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
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