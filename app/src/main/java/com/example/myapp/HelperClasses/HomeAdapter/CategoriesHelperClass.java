package com.example.myapp.HelperClasses.HomeAdapter;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class CategoriesHelperClass {
    int image;
    String title;
    GradientDrawable gradient1;

    public CategoriesHelperClass(GradientDrawable gradient1, int image, String title) {
       this.gradient1 = gradient1;
        this.image = image;
        this.title = title;

    }

    public int getImageView() {
        return image;
    }

    public String getTextView() {
        return title;
    }


    public Drawable getGradient() {return gradient1;
    }
}

