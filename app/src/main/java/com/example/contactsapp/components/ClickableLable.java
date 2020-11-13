package com.example.contactsapp.components;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class ClickableLable extends LinearLayout {
    private AppCompatButton input;
    public ClickableLable(Context context, String labelText) {
        super(context);

        this.input = new AppCompatButton(context);
        input.setText(labelText);
        input.setTextSize(18);
        input.setBackgroundColor(Color.TRANSPARENT);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        input.setLayoutParams(params);
        addView(input);

    }
}
