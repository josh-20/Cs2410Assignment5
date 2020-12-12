package com.example.contactsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;

import com.example.contactsapp.components.ImageSelector;
import com.example.contactsapp.components.MaterialInput;
import com.example.contactsapp.database.AppDataBase;
import com.example.contactsapp.models.Contact;
import com.example.contactsapp.presenters.NewContactPresenter;
import com.google.android.material.button.MaterialButton;

import static com.example.contactsapp.components.ImageSelector.*;

public class newContactActivity extends BaseActivity implements NewContactPresenter.MVPView {
    NewContactPresenter presenter;
    ImageSelector imageSelector;
    public static int PICK_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewContactPresenter(this);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        // TODO: Change to use MaterialButton labels other then normal AppCompatTextView
        MaterialInput name = new MaterialInput(this,"Name");
        MaterialInput phoneNumber = new MaterialInput(this,"Phone Number");
        MaterialInput email = new MaterialInput(this,"Email");


        MaterialButton save = new MaterialButton(this);
        MaterialButton cancel = new MaterialButton(this,null,R.attr.borderlessButtonStyle);
        imageSelector = new ImageSelector(this,()->{
            presenter.handleSelectPicturePress();
        });



        //buttons Layout
        LinearLayout buttons = new LinearLayout(this);
        buttons.setPadding(48,0,48,0);
        buttons.setGravity(Gravity.RIGHT);

        save.setText("Save");
        cancel.setText("Cancel");

        save.setOnClickListener(view ->{
            presenter.CreateContact(name.getText().toString(),
                    email.getText().toString(),
                    phoneNumber.getText().toString(),
                    imageSelector.getImageUri());

        });
        cancel.setOnClickListener(view -> {
            presenter.handleCancelPress();
        });
        mainLayout.addView(imageSelector);
        buttons.addView(cancel);
        buttons.addView(save);


        mainLayout.addView(name);
        mainLayout.addView(phoneNumber);
        mainLayout.addView(email);
        mainLayout.addView(buttons);
        setContentView(mainLayout);
    }
    @Override
    public void goBackToContactsPage(Contact newContact) {
        if(newContact == null){
            setResult(Activity.RESULT_CANCELED,null);
        }else {
            Intent intent = new Intent();
            intent.putExtra("result", newContact);
            setResult(Activity.RESULT_OK, intent);
        }
        finish();
    }

    @Override
    public void goToPhotos(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK){
            String uri = data.getData().toString();
            presenter.handlePictureSelected(uri);
        }
    }

    @Override
    public void displayImage(String uri) {
        imageSelector.setImageUri(uri);
    }
}
