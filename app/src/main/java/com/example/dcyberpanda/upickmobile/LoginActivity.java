package com.example.dcyberpanda.upickmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextView phonenrView;
    private TextView passwordView;
    public static User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phonenrView = (EditText) findViewById(R.id.login_phonenr);
        passwordView = (EditText) findViewById(R.id.login_password);
    }

    public void login(View v){
        String phonenr = phonenrView.getText().toString();
        String password = passwordView.getText().toString();

        DatabaseConnection.login(getApplicationContext(), phonenr, password, new DatabaseConnection.VolleyCallback() {
            @Override
            public void onSuccess(Object result) {
                if (result instanceof User){
                    currentUser = (User) result;
                    Intent intent = new Intent(LoginActivity.this, BarpickActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    String strResult = (String) result;
                    if (strResult.equals("wrong_phonenr")){
                        Toast.makeText(getApplicationContext(), "Ky numer telefoni nuk eshte i regjistruar me asnje llogari uPick!", Toast.LENGTH_SHORT).show();
                    }else if (strResult.equals("wrong_password")){
                        Toast.makeText(getApplicationContext(), "Password i gabuar!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void toRegister(View v){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
