package com.example.android.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {


    private Button LoginButton,PhoneLoginButton;
    private EditText UserEmail,UserPassword;
    private TextView NeedNewAccountLink,ForgetPasswordLink;

    private ProgressDialog loadingbar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        InitializeFields();

        NeedNewAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                sendUserToRegisterActivity();

            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AllowUserToLogin();

            }
        });
    }

    public void AllowUserToLogin()
    {

        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please Enter email.....",Toast.LENGTH_SHORT);
        }


        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please Enter password.....",Toast.LENGTH_SHORT);
        }
        else
        {
            loadingbar.setTitle("Sign in");
            loadingbar.setMessage("Please Wait .......");
            loadingbar.setCanceledOnTouchOutside(true);
            loadingbar.show();

            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                sendUserToMainActivity();

                                Toast.makeText(LoginActivity.this, "Logged in Successful"  , Toast.LENGTH_SHORT).show();
                                loadingbar.dismiss();
                            }
                            else
                            {
                                String message = task.getException().toString();
                                Toast.makeText(LoginActivity.this,"Error : "+ message,Toast.LENGTH_SHORT).show();
                                loadingbar.dismiss();
                            }

                        }
                    });
        }


    }


    public void InitializeFields()
    {
            LoginButton=(Button) findViewById(R.id.login_button);
            PhoneLoginButton=(Button) findViewById(R.id.phone_login_button);
            UserEmail=(EditText)findViewById(R.id.login_email);
            UserPassword=(EditText)findViewById(R.id.login_password);
            NeedNewAccountLink = (TextView) findViewById(R.id.need_new_account_link);
            ForgetPasswordLink = (TextView) findViewById(R.id.forget_password_link);
            loadingbar=new ProgressDialog(this);
    }



    private void sendUserToMainActivity()
    {
        Intent mainintent = new Intent(LoginActivity.this,MainActivity.class);
        mainintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainintent);
        finish();

    }
    private void sendUserToRegisterActivity()
    {
        Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(registerIntent);
    }
}
