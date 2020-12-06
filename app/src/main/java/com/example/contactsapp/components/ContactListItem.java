package com.example.contactsapp.components;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.contactsapp.BaseActivity;
import com.example.contactsapp.ContactActivity;
import com.example.contactsapp.R;
import com.example.contactsapp.models.Contact;
import com.example.contactsapp.presenters.BaseMVPView;
import com.example.contactsapp.presenters.contactsPresenter;
import com.google.android.material.button.MaterialButton;

public class ContactListItem extends LinearLayout {

    public ContactListItem(Context context, Contact contact){
        super(context);
        MaterialButton button = new MaterialButton(context,null, R.attr.borderlessButtonStyle);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        button.setText(contact.Name);
        button.setGravity(Gravity.LEFT);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(context, ContactActivity.class);
            intent.putExtra("contact",contact);
//            intent.putExtra("phoneNumber",contact.PhoneNumber);
//            intent.putExtra("email",contact.emailAddress);
            context.startActivity(intent);
        });
        button.setTextSize(12);
        addView(button);

    }


}
