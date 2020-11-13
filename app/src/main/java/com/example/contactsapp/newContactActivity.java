package com.example.contactsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;

import com.example.contactsapp.database.AppDataBase;
import com.example.contactsapp.models.Contact;
import com.example.contactsapp.presenters.NewContactPresenter;

public class newContactActivity extends BaseActivity implements NewContactPresenter.MVPView {
    NewContactPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewContactPresenter(this);
        AppCompatTextView nameText = new AppCompatTextView(this);
        AppCompatTextView phoneNumberText = new AppCompatTextView(this);
        AppCompatTextView emailText = new AppCompatTextView(this);
        AppCompatEditText name = new AppCompatEditText(this);
        AppCompatEditText phoneNumber = new AppCompatEditText(this);
        AppCompatEditText email = new AppCompatEditText(this);
        AppCompatButton save = new AppCompatButton(this);

        nameText.setText("Name");
        phoneNumberText.setText("Phone Number");
        emailText.setText("Email");
        save.setText("Save Contact");

        save.setOnClickListener(view ->{
            presenter.CreateContact(name.getText().toString(),
                    email.getText().toString(),
                    Integer.parseInt(phoneNumber.getText().toString()));
        });

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        mainLayout.addView(nameText);
        mainLayout.addView(name);
        mainLayout.addView(phoneNumberText);
        mainLayout.addView(phoneNumber);
        mainLayout.addView(emailText);
        mainLayout.addView(email);
        mainLayout.addView(save);

        setContentView(mainLayout);
    }
    @Override
    public void goBackToContactsPage(Contact newContact) {
        Intent intent = new Intent();
        intent.putExtra("result", newContact);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
