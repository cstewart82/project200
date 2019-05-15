package com.example.a40126571.project200;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    // Creating variables to hold the text entered by the user in the activity_log_in.xml page.
    EditText UsernameEt, PasswordEt;

    // This method begins when a user starts up the app. It tells the variables "UsernameET" and "PasswordET" to find the appropriate fields by searching for the corresponding ID.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        UsernameEt = (EditText)findViewById(R.id.etUsername);
        PasswordEt = (EditText)findViewById(R.id.etPassword);
    }

    // This method happens upon the user pressing the "login" button on the activity_log_in.xml page.
    public void OnLogin(View view){
        // Whichever text was entered into the appropriate fields "UsernameET" and "PasswordET" when the user pressed the login button is now saved and set to the String variables.
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        // A "type" variable is set; this is for the BackgroundWorker class to know which section of the "if..." statement to carry out.
        String type = "login";

        // Calls the BackgroundWorker class to execute its function and passes it the necessary parameters to complete its function.
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }

    // This method happens upon the user pressing the "Register" button on the activity_log_in.xml page.
    public void OpenReg(View view) {
        // This triggers the activity_register.xml page.
        startActivity(new Intent(this, Register.class));
    }

    // This method happens upon the user pressing the "Go To Record!" button on the activity_log_in.xml page.
    public void OpenRec(View view) {
        // This triggers the activity_record.xml page.
        startActivity(new Intent(this, Record.class));
    }

    // This method validates the information entered by the user, and if the login credentials match those on a database, it directs the user to the "user landing" page.
    // WORK IN PROGRESS. MIGHT BE BETTER PLACES IN BACKGROUND WORKER CLASS.
    private void validate(String userName, String passWord){

            if ((userName == "xxx") && (passWord == "xxx")) {
                Intent intent = new Intent(LogInActivity.this, Record.class);

            }


    }
}
