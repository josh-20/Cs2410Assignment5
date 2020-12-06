package com.example.contactsapp.components;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.contactsapp.R;
import com.example.contactsapp.models.Contact;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

public class displayLabel extends LinearLayout {

    public displayLabel(Context context, Contact contact){
        super(context);
        // TODO: Make Card View for update, call, text / fix margins for each input
        //CardView

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);



        // wrapper
        MaterialCardView wrapper = new MaterialCardView(context);



        // Title Layout
        LinearLayout titleView = new LinearLayout(context);
        titleView.setPadding(60,48,60,0);
        MaterialTextView title = new MaterialTextView(context,null,R.attr.textAppearanceHeadline6);
        title.setText(contact.Name);
        titleView.addView(title);


        //body Layout
        LinearLayout bodyView = new LinearLayout(context);
        bodyView.setOrientation(LinearLayout.VERTICAL);
        bodyView.setPadding(60,200,60,0);
        MaterialTextView contactNumber = new MaterialTextView(context,null,R.attr.textAppearanceListItem);
        contactNumber.setText(contact.PhoneNumber);
        MaterialTextView contactEmail = new MaterialTextView(context,null,R.attr.textAppearanceListItem);
        contactEmail.setText(contact.emailAddress);
        bodyView.addView(contactNumber);
        bodyView.addView(contactEmail);


        // button Layout
        LinearLayout buttonView = new LinearLayout(context);


        //




        wrapper.addView(titleView);
        wrapper.addView(bodyView);
        wrapper.addView(buttonView);
        addView(wrapper);



    }
}
