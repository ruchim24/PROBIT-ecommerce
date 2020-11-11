package com.example.myapp.User.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapp.R;

public class shops extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        TextView tvShop=findViewById(R.id.tvShop);
        String description=getIntent().getStringExtra("shop");
        tvShop.setText(description+" description will show here");

    }

}