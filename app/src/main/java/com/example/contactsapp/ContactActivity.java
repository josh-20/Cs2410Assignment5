package com.example.contactsapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.example.contactsapp.models.Contact;
import com.example.contactsapp.presenters.contactPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

public class ContactActivity extends BaseActivity implements contactPresenter.MVPView {
    contactPresenter presenter;
    private final int UPDATE_POST = 1;
    private final int REQUEST_PHONE_PERMISSIONS = 0;
    private final int REQUEST_SMS_PERMISSIONS = 0;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new contactPresenter(this);
        Intent intent = getIntent();
        contact = (Contact)intent.getSerializableExtra("contact");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        // wrapper
        LinearLayout wrapper = new LinearLayout(this);
        wrapper.setLayoutParams(params);
        wrapper.setOrientation(LinearLayout.VERTICAL);
        FrameLayout frame = new FrameLayout(this);


        //Image/top
        LinearLayout top = new LinearLayout(this);
        top.setOrientation(LinearLayout.VERTICAL);
        if(!contact.imagePath.equals("")) {
            AppCompatImageView imageView = new AppCompatImageView(this);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1000);
            imageView.setLayoutParams(imageParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageURI(Uri.parse(contact.imagePath));
            top.addView(imageView);
        }else{
            AppCompatImageView imageView = new AppCompatImageView(this);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,1000);
            imageView.setLayoutParams(imageParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(R.drawable.ic_baseline_person_24);
            top.addView(imageView);}

        // Title Layout
        LinearLayout titleView = new LinearLayout(this);

        titleView.setPadding(60,800,60,0);
        MaterialTextView title = new MaterialTextView(this,null,R.attr.textAppearanceHeadline6);
        title.setText(contact.Name);
        title.setTextSize(45);
        title.setTextColor(Color.WHITE);
        titleView.addView(title);


        //body Layout
        MaterialCardView bodyView = new MaterialCardView(this);
        LinearLayout callTextLayout = new LinearLayout(this);
        LinearLayout emailLayout = new LinearLayout(this);
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        emailLayout.setOrientation(LinearLayout.HORIZONTAL);
        emailLayout.setLayoutParams(bp);
        emailLayout.setPadding(0,200,0,0);
        callTextLayout.setPadding(0,100,0,0);
        callTextLayout.setOrientation(LinearLayout.HORIZONTAL);
        callTextLayout.setLayoutParams(bp);

        MaterialTextView contactNumber = new MaterialTextView(this,null,R.attr.materialButtonOutlinedStyle);
        contactNumber.setTextSize(20);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(150,150);
        MaterialButton call = new MaterialButton(this,null,R.attr.borderlessButtonStyle);
        MaterialButton text = new MaterialButton(this,null,R.attr.borderlessButtonStyle);
        text.setLayoutParams(buttonParams);
        call.setLayoutParams(buttonParams);
        text.setIconResource(R.drawable.ic_baseline_message_24);
        call.setIconResource(R.drawable.ic_baseline_phone_24);
        contactNumber.setText(contact.PhoneNumber);
        text.setOnClickListener(view ->{
            presenter.handleTextPress(contact.PhoneNumber);

        });
        call.setOnClickListener(view -> {
            presenter.handleCallPress(contact.PhoneNumber);

        });


        MaterialTextView contactEmail = new MaterialTextView(this,null,R.attr.materialButtonOutlinedStyle);
        MaterialButton email = new MaterialButton(this,null,R.attr.borderlessButtonStyle);
        email.setIconResource(R.drawable.ic_baseline_email_24);
        email.setLayoutParams(buttonParams);
        contactEmail.setText(contact.emailAddress);
        email.setOnClickListener(view -> {
            presenter.handleEmailPress(contact.emailAddress);

        });




        callTextLayout.addView(contactNumber);
        callTextLayout.addView(call);
        callTextLayout.addView(text);

        emailLayout.addView(contactEmail);
        emailLayout.addView(email);


        bodyView.addView(callTextLayout);
        bodyView.addView(emailLayout);


        // button Layout
        FloatingActionButton fab = new FloatingActionButton(this);
        FrameLayout.LayoutParams fabParams = new FrameLayout.LayoutParams(150, ViewGroup.LayoutParams.WRAP_CONTENT);
        fab.setImageResource(R.drawable.ic_baseline_edit_24);
        fabParams.gravity = (Gravity.TOP | Gravity.RIGHT);
        fab.setLayoutParams(fabParams);
        PopupMenu popupMenu = new PopupMenu(this,fab);
        popupMenu.getMenu().add("Edit");
        popupMenu.getMenu().add("Delete");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getTitle().toString().equals("Edit")){
                    presenter.handleEditClick();
                }else{
                    presenter.deleteContact(contact);
                    //delete
                }
                return false;
            }
        });
        fab.setOnClickListener(view ->{
            popupMenu.show();
        });

        frame.addView(top);
        frame.addView(titleView);
        wrapper.addView(frame);
        wrapper.addView(bodyView);
        frame.addView(fab);
        setContentView(wrapper);


    }

    @Override
    public void goBackToContactsPage(Contact contact, boolean didDelete) {
        Intent intent = new Intent();
        if (didDelete) {
            intent.putExtra("id", contact.id);
            setResult(ContactsActivity.DELETED_RESULT, intent);
        } else {
            setResult(Activity.RESULT_CANCELED, null);
        }
        finish();

    }

    @Override
    public void goToEditPage(Contact contact) {
        Intent intent = new Intent(this, CreateOrUpdatedContactActivity.class);
        intent.putExtra("contact",contact);
        startActivityForResult(intent, UPDATE_POST);
    }

    @Override
    public void makePhoneCall(String number) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + number));
            startActivity(callIntent);
        }else{
            requestPermissions(new String []{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_PERMISSIONS);
        }
    }

    @Override
    public void sendText(String number) {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("sms:"+ number));
            startActivity(smsIntent);
        }else{
            requestPermissions(new String []{Manifest.permission.SEND_SMS}, REQUEST_SMS_PERMISSIONS);
        }

    }

    @Override
    public void sendEmail(String emailAddress) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"+emailAddress));
        startActivity(emailIntent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PHONE_PERMISSIONS){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                presenter.handleCallPress(contact.PhoneNumber);
            }
        }
    }
}