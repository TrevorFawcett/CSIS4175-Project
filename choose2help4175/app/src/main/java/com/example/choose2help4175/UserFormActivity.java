package com.example.choose2help4175;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserFormActivity extends AppCompatActivity {

    EditText fName;
    EditText lName;
    EditText phone;
    Button submitBtn;
    TextView userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);


        String emailAdd = "user@hotmail.com";
        //This string will be changed based on user login
        TextView email = findViewById(R.id.userEmail);
        email.setText(emailAdd);

        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fName = findViewById(R.id.firstName);
                Editable firstName = fName.getText();
                lName = findViewById(R.id.lastName);
                Editable lastName = lName.getText();
                phone = findViewById(R.id.phoneNumber);
                Editable pNumber = phone.getText();

                userData = findViewById(R.id.userData);
                String input = firstName + ", " + lastName + ", \n" + emailAdd + ", " + pNumber;
                userData.setText(input);

                Intent switchToForm = new Intent(UserFormActivity.this, UserDisplayActivity.class);
                startActivity(switchToForm);
            }
        });


    }

}