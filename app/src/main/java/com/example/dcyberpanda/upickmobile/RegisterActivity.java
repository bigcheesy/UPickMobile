package com.example.dcyberpanda.upickmobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private TextView nameView;
    private TextView surnameView;
    private TextView phonenrView;
    private TextView passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameView = (TextView) findViewById(R.id.register_name);
        surnameView = (TextView) findViewById(R.id.register_surname);
        phonenrView = (TextView) findViewById(R.id.register_phonenr);
        passwordView = (TextView) findViewById(R.id.register_password);
    }

    //Checks if any of the textviews are empty, returns true if empty, false if not.
    private boolean checkEmptiness(){
        if (isEmpty(nameView) || isEmpty(surnameView) || isEmpty(phonenrView) || isEmpty(passwordView)){
            Toast.makeText(getApplicationContext(), "Ju lutem plotesoni te gjitha fushat.", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    //Checks if textview is empty or null
    private boolean isEmpty(TextView textView){
        if (textView.getText() != null) {
            if (textView.getText().toString().isEmpty()){
                return true;
            }
        }else{
            return true;
        }

        return false;
    }

    public void register(View v){
        String name = nameView.getText().toString();
        String surname = surnameView.getText().toString();
        String phonenr = phonenrView.getText().toString();
        String password = passwordView.getText().toString();

        if (!checkEmptiness()) {
            DatabaseConnection.createUser(getApplicationContext(), name, surname, phonenr, password, new DatabaseConnection.VolleyCallback() {
                @Override
                public void onSuccess(Object result) {
                    Toast.makeText(getApplicationContext(), "Regjistrimi u krye me sukses!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, BarpickActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}


