package com.example.myapp.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapp.Common.LoginSignup.RetailerStartupScreen;
import com.example.myapp.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.myapp.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.myapp.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.myapp.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.myapp.HelperClasses.HomeAdapter.MostViewedAdapter;
import com.example.myapp.HelperClasses.HomeAdapter.MostViewedHelperClass;
import com.example.myapp.R;
import com.example.myapp.Settings;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    static final float END_SCALE = 0.7f;

    RecyclerView featuredRecycler,mostViewedRecycler,categoriesRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    private Button btnToggleDark;





    ImageView menuIcon;
    LinearLayout contentView;
    RelativeLayout c1;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14;


    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        featuredRecycler = findViewById(R.id.featured_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        t1  = findViewById(R.id.app_name);
        t2  = findViewById(R.id.text1);
        t3  = findViewById(R.id.text2);
        t4  = findViewById(R.id.text3);
        t5  = findViewById(R.id.text4);
        t6  = findViewById(R.id.text5);
        t7  = findViewById(R.id.text6);
        t8  = findViewById(R.id.text7);
        t9  = findViewById(R.id.text8);
        t10 = findViewById(R.id.text9);
        t11 = findViewById(R.id.text10);
        t12 = findViewById(R.id.text11);
        t13 = findViewById(R.id.text12);



        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);






        navigationDrawer();

        
        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();
        Load_settings();






    }
    private void Load_settings(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean chk_night = sp.getBoolean("NIGHT",false);
        if(chk_night){
            contentView.setBackgroundColor(Color.parseColor("#222222"));

            navigationView.setBackgroundColor(Color.parseColor("#222222"));
            t1.setTextColor(Color.parseColor("#ffffff"));
            t2.setTextColor(Color.parseColor("#ffffff"));
            t3.setTextColor(Color.parseColor("#ffffff"));
            t4.setTextColor(Color.parseColor("#ffffff"));
            t5.setTextColor(Color.parseColor("#ffffff"));
            t6.setTextColor(Color.parseColor("#ffffff"));
            t7.setTextColor(Color.parseColor("#ffffff"));
            t8.setTextColor(Color.parseColor("#ffffff"));
            t9.setTextColor(Color.parseColor("#ffffff"));
            t10.setTextColor(Color.parseColor("#ffffff"));
            t11.setTextColor(Color.parseColor("#ffffff"));
            t12.setTextColor(Color.parseColor("#ffffff"));
            t13.setTextColor(Color.parseColor("#ffffff"));



        }else{
            contentView.setBackgroundColor(Color.parseColor("#ffffff"));

            navigationView.setBackgroundColor(Color.parseColor("#ffffff"));
            t1.setTextColor(Color.parseColor("#222222"));
            t2.setTextColor(Color.parseColor("#222222"));
            t3.setTextColor(Color.parseColor("#222222"));
            t4.setTextColor(Color.parseColor("#222222"));
            t5.setTextColor(Color.parseColor("#222222"));
            t6.setTextColor(Color.parseColor("#222222"));
            t7.setTextColor(Color.parseColor("#222222"));
            t8.setTextColor(Color.parseColor("#222222"));
            t9.setTextColor(Color.parseColor("#222222"));
            t10.setTextColor(Color.parseColor("#222222"));
            t11.setTextColor(Color.parseColor("#222222"));
            t12.setTextColor(Color.parseColor("#222222"));
            t13.setTextColor(Color.parseColor("#222222"));

        }
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorSplash));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
        super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.settings:
                Intent i = new Intent(this, Settings.class);
                startActivity(i);
        }
        return true;
    }

    @Override
    protected void onResume() {
        Load_settings();
        super.onResume();
    }

    private void featuredRecycler(){
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.chanel,"Chanel","fghjkkjhgfddertghjuytrfg"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.starbucks,"Star Buck's","fghjkkjhgfddertghjuytrfg"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.prada,"Prada","fghjkkjhgfddertghjuytrfg"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffeff400,0xffaff600});

    }
    public void callRetailerScreen(View view){
        startActivity(new Intent(getApplicationContext(), RetailerStartupScreen.class));
    }

    private void mostViewedRecycler() {

        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.chanel, "Chanel"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.starbucks, "Edenrobe"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.prada, "J."));


        adapter = new MostViewedAdapter(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);

    }
    private void categoriesRecycler() {

        //All Gradients
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});


        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient1, R.drawable.education, "Rtjhg"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient3, R.drawable.restaurant, "KJHgf"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient4, R.drawable.shop, "Shopping"));



        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);

    }



}