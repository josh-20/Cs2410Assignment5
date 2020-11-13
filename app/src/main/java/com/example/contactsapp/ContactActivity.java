package com.example.contactsapp;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.contactsapp.database.AppDataBase;
import com.example.contactsapp.models.Contact;
import com.example.contactsapp.presenters.NewContactPresenter;
import com.example.contactsapp.presenters.contactPresenter;
import com.example.contactsapp.presenters.contactsPresenter;

import java.util.ArrayList;

public class ContactActivity extends BaseActivity implements contactPresenter.MVPView{
    contactPresenter presenter;
    LinearLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLayout = new LinearLayout(this);
        presenter = new contactPresenter(this);

    }

    @Override
    public void displayContact(Contact contact) {
        AppCompatTextView Name = new AppCompatTextView(this);
        AppCompatTextView PhoneNumber = new AppCompatTextView(this);
        AppCompatTextView Email = new AppCompatTextView(this);
        Name.setText(contact.Name);
        PhoneNumber.setText(contact.PhoneNumber);
        Email.setText(contact.emailAddress);
        mainLayout.addView(Name);
        mainLayout.addView(PhoneNumber);
        mainLayout.addView(Email);
        setContentView(mainLayout);
    }
}
