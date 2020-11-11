package com.example.myapp.User.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapp.R;

public class cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        String cart_var=getIntent().getStringExtra("cart");
        TextView tvCart=findViewById(R.id.tvCart);
        tvCart.setText("your cart will show here");

    }

}