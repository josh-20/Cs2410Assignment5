package com.example.contactsapp.components;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.contactsapp.R;
import com.example.contactsapp.models.Contact;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

public class displayLabel extends LinearLayout {

    public displayLabel(Context context, Contact contact){
        super(context);
        // TODO: Make Card View for update, call, text / fix margins for each input
        //CardView

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(48,24,48,24);
        setLayoutParams(params);



        // wrapper
        LinearLayout wrapper = new LinearLayout(context);
        wrapper.setOrientation(VERTICAL);
        FrameLayout frame = new FrameLayout(context);


        //Image/top
        LinearLayout top = new LinearLayout(context);
        top.setOrientation(LinearLayout.VERTICAL);
        if(!contact.imagePath.equals("")) {
            AppCompatImageView imageView = new AppCompatImageView(context);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,500);
            imageView.setLayoutParams(imageParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageURI(Uri.parse(contact.imagePath));
            top.addView(imageView);
        }




        // Title Layout
        LinearLayout titleView = new LinearLayout(context);
        titleView.setPadding(60,400,60,0);
        MaterialTextView title = new MaterialTextView(context,null,R.attr.textAppearanceHeadline6);
        title.setText(contact.Name);
        titleView.addView(title);


        //body Layout
        LinearLayout bodyView = new LinearLayout(context);
        bodyView.setOrientation(LinearLayout.HORIZONTAL);
        bodyView.setPadding(60,100,60,0);
        MaterialButton contactNumber = new MaterialButton(context,null,R.attr.materialButtonOutlinedStyle);
        contactNumber.setText(contact.PhoneNumber);
        MaterialTextView contactEmail = new MaterialTextView(context,null,R.attr.materialButtonOutlinedStyle);
        contactEmail.setText(contact.emailAddress);
        bodyView.addView(contactNumber);
        bodyView.addView(contactEmail);


        // button Layout
        FloatingActionButton fab = new FloatingActionButton(context);
        FrameLayout.LayoutParams fabParams = new FrameLayout.LayoutParams(150, ViewGroup.LayoutParams.WRAP_CONTENT);
        fab.setImageResource(R.drawable.ic_baseline_edit_24);
        fabParams.gravity = (Gravity.TOP | Gravity.RIGHT);
        fab.setLayoutParams(fabParams);




        //



        frame.addView(top);
        frame.addView(titleView);
        wrapper.addView(frame);
        wrapper.addView(bodyView);
        frame.addView(fab);
        addView(wrapper);



    }
}
