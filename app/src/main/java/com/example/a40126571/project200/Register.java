package com.example.a40126571.project200;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    // Creating variables to hold the text entered by the user in the activity_log_in.xml page.
    EditText name, surname, age, username, password;
    String str_name, str_surname, str_age, str_username, str_password;

    // This method begins when a user begins the Register activity. It tells the variables "name" through to "password" to find the appropriate fields by searching for the corresponding ID.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText)findViewById(R.id.et_name);
        surname = (EditText)findViewById(R.id.et_surname);
        age = (EditText)findViewById(R.id.et_age);
        username = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);
    }

    // This method happens upon the user pressing the "register" button on the activity_register.xml page.
    public void OnReg (View view) {

        // Whichever text was entered into the appropriate fields "name" through to "password" when the user pressed the register button is now saved and set to the String variables.
        String str_name = name.getText().toString();
        String str_surname = surname.getText().toString();
        String str_age = age.getText().toString();
        String str_username = username.getText().toString();
        String str_password = password.getText().toString();
        // A "type" variable is set; this is for the BackgroundWorker class to know which section of the "if..." statement to carry out.
        String type = "register";


        // Calls the BackgroundWorker class to execute its function and passes it the necessary parameters to complete its function.
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_name, str_surname, str_age, str_username, str_password);
    }

}
