package com.example.skylink.presentation.User_Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skylink.R;
import com.example.skylink.application.Main;
import com.example.skylink.application.Services;

import com.example.skylink.business.Implementations.FlightBookingHandler;
import com.example.skylink.business.Interface.iFlightBookingHandler;
import com.example.skylink.objects.Interfaces.iFlightInfo;
import com.example.skylink.presentation.Session;
import com.example.skylink.business.Implementations.UserHandler;
import com.example.skylink.business.validations.IValidateUserAuth;
import com.example.skylink.business.validations.ValidateUserAuth;
import com.example.skylink.objects.Implementations.UserProperties;
import com.example.skylink.presentation.FlightSearching.FlightSearchP;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

public class SignInActivity extends AppCompatActivity {

    private EditText email, password;
    private TextView signUp;
    private Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        copyDatabaseToDevice();
        Session.getInstance().setContext(this);
        setupViews();
        setupListeners();
    }

    private void setupListeners() {
        signUp.setOnClickListener(v -> {
            navigateToSignUpActivity();
        });

        signIn.setOnClickListener(v -> {
            attemptSignIn();
        });
    }

    private void navigateToSignUpActivity() {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void attemptSignIn() {
        if (isValid()) {
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            UserProperties user = new UserProperties(userEmail, userPassword);
            UserHandler userHandler = new UserHandler(Services.getUserDatabase());

            if (userHandler.signinUser(user)) {
                long userId = userHandler.getUserIdByEmail(userEmail);
                Session.getInstance().setUser_id(userId);
                Intent intent = new Intent(SignInActivity.this, FlightSearchP.class);
                Session.getInstance().setEmail(userEmail);
                startActivity(intent);
            } else {
                showIncorrectCredentialsMessage();
            }
        }
    }

    private void showIncorrectCredentialsMessage() {
        Toast.makeText(SignInActivity.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
    }


    private void setupViews() {
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        signUp = findViewById(R.id.tvSignInClick);
        signIn = findViewById(R.id.btnSignIn);
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

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {

        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}