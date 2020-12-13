package com.example.myapp.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.User.UserDashboard;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class Login extends AppCompatActivity {
    CountryCodePicker countryCodePicker;
    TextInputLayout phoneNumber,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_login);

        countryCodePicker = findViewById(R.id.login_country_code_picker);
        phoneNumber       = findViewById(R.id.login_phone_number);
        password          = findViewById(R.id.login_password);

    }

    public void letTheUserLogin(View view) {
        if(!validateFields()){
            return;
        }

        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString().trim();

        if(_phoneNumber.charAt(0)=='0'){
            _phoneNumber = _phoneNumber.substring(1);
        }

        String _completePhoneNumber = "+"+countryCodePicker.getFullNumber()+_phoneNumber;

        Query chechUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNo").equalTo(_completePhoneNumber);
        chechUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);

                    String systemPassword = snapshot.child(_completePhoneNumber).child("password").getValue(String.class);
                    if(systemPassword.equals(_password)){
                        password.setError(null);
                        password.setErrorEnabled(false);

                        String _fullname = snapshot.child(_completePhoneNumber).child("fullName").getValue(String.class);
                        String _email = snapshot.child(_completePhoneNumber).child("email").getValue(String.class);
                        String _phoneNo = snapshot.child(_completePhoneNumber).child("phoneNo").getValue(String.class);
                        String _dateOfBirth = snapshot.child(_completePhoneNumber).child("date").getValue(String.class);

                        Toast.makeText(Login.this,_fullname+"\n"+_email+"\n"+_phoneNo+"\n"+_dateOfBirth,Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Login.this,"data doesnot exist!",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Login.this,"data doesnot exist!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
        finish();
    }

    private boolean validateFields(){
        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString().trim();

        if(_phoneNumber.isEmpty()){
            phoneNumber.setError("Phone Number can not be empty!");
            phoneNumber.requestFocus();
            return false;
        }else if(_password.isEmpty()){
            password.setError("Password can not be empty!");
            password.requestFocus();
            return false;
        }else {
            return true;
        }
    }
}