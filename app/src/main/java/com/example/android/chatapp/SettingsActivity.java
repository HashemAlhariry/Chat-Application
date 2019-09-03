package com.example.android.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    private Button UpdateaAccountSettings;
    private EditText userName,userStatus;
    private CircleImageView userProfileImage;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        InitializeFields();
    }

    private void InitializeFields() {
        UpdateaAccountSettings= (Button) findViewById(R.id.update_settings_buttons);
        userName=(EditText) findViewById(R.id.set_user_name);
        userStatus=(EditText) findViewById(R.id.set_profile_statue);
        userProfileImage=(CircleImageView) findViewById(R.id.set_profile_image);
    }
}
