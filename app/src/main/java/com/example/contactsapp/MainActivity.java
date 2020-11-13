package com.example.contactsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.contactsapp.components.ContactListItem;
import com.example.contactsapp.database.AppDataBase;
import com.example.contactsapp.models.Contact;
import com.example.contactsapp.presenters.contactsPresenter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements contactsPresenter.MVPView {
    private final int CreateNewContact = 1;
    contactsPresenter presenter;
    LinearLayout mainLayout;
    LinearLayout contactsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new contactsPresenter(this);
        mainLayout = new LinearLayout(this);
        contactsLayout = new LinearLayout(this);
        ScrollView scrollView = new ScrollView(this);

        mainLayout.setOrientation(LinearLayout.VERTICAL);
        contactsLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(contactsLayout);
        scrollView.addView(mainLayout);

        AppCompatButton newContact = new AppCompatButton(this);
        newContact.setText("New Contact");
        newContact.setOnClickListener(view -> {
            presenter.goToNewContactsPage();
        });
        mainLayout.addView(newContact);
        setContentView(scrollView);
    }

    @Override
    public void renderContacts(ArrayList<Contact> contacts) {
        runOnUiThread(() -> {
            contactsLayout.removeAllViews();
            contacts.forEach(contact -> {
                ContactListItem listItem = new ContactListItem(this, contact);
                listItem.setOnClickListener(view -> {
                    Intent intent = new Intent(this,ContactActivity.class);
                    startActivity(intent,contact);
                });
                contactsLayout.addView(listItem);
                });
            });
    }

    @Override
    public void goToNewContactsPage() {
        // with new intents always make a new activity inside of the manifests
        Intent intent = new Intent(this, newContactActivity.class);
        startActivityForResult(intent, CreateNewContact);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CreateNewContact && resultCode == Activity.RESULT_OK) {
            Contact newContact = (Contact)data.getSerializableExtra("result");
            presenter.onNewContactCreate(newContact);
        }
    }
}