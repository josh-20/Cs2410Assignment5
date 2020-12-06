package com.example.contactsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.contactsapp.components.ContactListItem;
import com.example.contactsapp.database.AppDataBase;
import com.example.contactsapp.models.Contact;
import com.example.contactsapp.presenters.contactsPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements contactsPresenter.MVPView {
    private final int CreateNewContact = 1;
    contactsPresenter presenter;
    FrameLayout mainLayout;
    LinearLayout contactsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new contactsPresenter(this);
        mainLayout = new FrameLayout(this);
        ScrollView contactsScroll = new ScrollView(this);
        FrameLayout.LayoutParams materialButtonParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        AppCompatImageView imageView = new AppCompatImageView(this);
        imageView.setImageResource(R.drawable.ic_launcher_foreground);
        contactsLayout = new LinearLayout(this);

        materialButtonParams.gravity = (Gravity.BOTTOM | Gravity.RIGHT);


        contactsLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(imageView);
        contactsScroll.addView(contactsLayout);

        FloatingActionButton newContact = new FloatingActionButton(this);
        newContact.setLayoutParams(materialButtonParams);
        newContact.setImageResource(R.drawable.ic_baseline_add_24);
        newContact.setOnClickListener(view -> {
            presenter.goToNewContactsPage();
        });
        mainLayout.addView(newContact);
        mainLayout.addView(contactsScroll);
        setContentView(mainLayout);
    }

    @Override
    public void renderContacts(ArrayList<Contact> contacts) {
        runOnUiThread(() -> {
            contactsLayout.removeAllViews();
            contacts.forEach(contact -> {
                ContactListItem listItem = new ContactListItem(this, contact);
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