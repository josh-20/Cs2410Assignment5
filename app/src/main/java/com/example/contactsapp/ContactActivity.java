package com.example.contactsapp;

import android.app.Activity;
import android.content.Intent;
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

import androidx.appcompat.widget.AppCompatImageView;

import com.example.contactsapp.models.Contact;
import com.example.contactsapp.presenters.contactPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

public class ContactActivity extends BaseActivity implements contactPresenter.MVPView {
    contactPresenter presenter;
    private final int UPDATE_POST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new contactPresenter(this);
        Intent intent = getIntent();
        Contact contact = (Contact)intent.getSerializableExtra("contact");
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
        LinearLayout bodyLayout = new LinearLayout(this);
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bodyLayout.setOrientation(LinearLayout.HORIZONTAL);
        bodyLayout.setLayoutParams(bp);

        MaterialButton contactNumber = new MaterialButton(this,null,R.attr.materialButtonOutlinedStyle);
        contactNumber.setText(contact.PhoneNumber);
        MaterialButton contactEmail = new MaterialButton(this,null,R.attr.materialButtonOutlinedStyle);
        contactEmail.setPadding(0,0,0,0);
        contactEmail.setText(contact.emailAddress);
        bodyLayout.addView(contactNumber);
        bodyLayout.addView(contactEmail);

        bodyView.addView(bodyLayout);


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
}