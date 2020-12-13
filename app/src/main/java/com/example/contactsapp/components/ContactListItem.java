package com.example.contactsapp.components;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.contactsapp.BaseActivity;
import com.example.contactsapp.ContactActivity;
import com.example.contactsapp.R;
import com.example.contactsapp.models.Contact;
import com.example.contactsapp.presenters.BaseMVPView;
import com.example.contactsapp.presenters.contactsPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.circularreveal.CircularRevealLinearLayout;
import com.google.android.material.imageview.ShapeableImageView;

public class ContactListItem extends LinearLayout {

    public ContactListItem(Context context, Contact contact){
        super(context);
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(HORIZONTAL);
        setTag(contact.id);

        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(125,125 );
        if(!contact.imagePath.equals("")){
            AppCompatImageView imageView= new AppCompatImageView(context);
            imageView.setImageURI(Uri.parse(contact.imagePath));
            imageView.setLayoutParams(imageParams);
            layout.addView(imageView);
        }else{
            ShapeableImageView imageView = new ShapeableImageView(context);
            imageView.setLayoutParams(imageParams);
            imageView.setImageResource(R.drawable.ic_baseline_person_24);
            layout.addView(imageView);
        }
        MaterialButton button = new MaterialButton(context,null, R.attr.borderlessButtonStyle);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        button.setText(contact.Name);
        button.setGravity(Gravity.LEFT);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(context, ContactActivity.class);
            intent.putExtra("contact",contact);
            context.startActivity(intent);
        });
        button.setTextSize(12);
        layout.addView(button);
        addView(layout);

    }
}
