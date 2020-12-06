package com.example.contactsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.contactsapp.components.displayLabel;
import com.example.contactsapp.database.AppDataBase;
import com.example.contactsapp.models.Contact;
import com.example.contactsapp.presenters.NewContactPresenter;
import com.example.contactsapp.presenters.contactPresenter;
import com.example.contactsapp.presenters.contactsPresenter;

import java.util.ArrayList;

public class ContactActivity extends BaseActivity{
    LinearLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
//        presenter = new contactPresenter(this);
        Intent intent = getIntent();
        Contact contact = (Contact)intent.getSerializableExtra("contact");

        // TODO: change to take one Contact
        displayLabel Contact = new displayLabel(this, contact);
        mainLayout.addView(Contact);
        setContentView(mainLayout);

    }

}
