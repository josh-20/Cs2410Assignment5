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

public class ContactActivity extends BaseActivity implements contactPresenter.MVPView{
    contactPresenter presenter;
    LinearLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
//        presenter = new contactPresenter(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phoneNumber = intent.getStringExtra("phoneNumber");
        String email = intent.getStringExtra("email");

        displayLabel contactName = new displayLabel(this,"name: ",name);
        displayLabel contactNumber = new displayLabel(this,"Phone Number: ",phoneNumber);
        displayLabel contactEmail = new displayLabel(this,"Email Address: ",email);

        mainLayout.addView(contactName);
        mainLayout.addView(contactNumber);
        mainLayout.addView(contactEmail);
        setContentView(mainLayout);

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
