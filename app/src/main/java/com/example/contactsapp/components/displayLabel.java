package com.example.contactsapp.components;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

public class displayLabel extends LinearLayout {

    public displayLabel(Context context, String name, String info){
        super(context);

        AppCompatTextView label = new AppCompatTextView(context);
         label.setTextSize(18);
         label.setText(name);

         AppCompatTextView input = new AppCompatTextView(context);
         input.setTextSize(20);
         input.setText(info);

         addView(label);
         addView(input);

    }
}
