package com.example.contactsapp.components;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.contactsapp.BaseActivity;
import com.example.contactsapp.models.Contact;
import com.example.contactsapp.presenters.BaseMVPView;
import com.example.contactsapp.presenters.contactsPresenter;

public class ContactListItem extends LinearLayout {

    public ContactListItem(Context context, Contact contact){
        super(context);
        AppCompatTextView button = new AppCompatTextView(context);
        button.setText(contact.Name);
        button.setTextSize(20);
        button.setBackgroundColor(Color.TRANSPARENT);
        addView(button);

    }


}
