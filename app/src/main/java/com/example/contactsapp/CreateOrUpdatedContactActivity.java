package com.example.contactsapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.example.contactsapp.components.ImageSelector;
import com.example.contactsapp.components.MaterialInput;
import com.example.contactsapp.models.Contact;
import com.example.contactsapp.presenters.CreateOrUpdateContactPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateOrUpdatedContactActivity extends BaseActivity implements CreateOrUpdateContactPresenter.MVPView {
    CreateOrUpdateContactPresenter presenter;
    ImageSelector imageSelector;
    MaterialInput nameInput;
    MaterialInput phoneNumberInput;
    MaterialInput emailInput;
    String curretPhotopath = "";
    private static int PICK_IMAGE = 1;
    private static int TAKE_PICTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CreateOrUpdateContactPresenter(this);
        Intent intent = getIntent();

        // problems with null pointer
//        Contact updateContact = (Contact)intent.getSerializableExtra("contact");
//        System.out.println(updateContact);
//        presenter.loadContact(updateContact);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        // TODO: Change to use MaterialButton labels other then normal AppCompatTextView
        nameInput = new MaterialInput(this,"Name");

        phoneNumberInput = new MaterialInput(this,"Phone Number");
        emailInput = new MaterialInput(this,"Email");


        MaterialButton save = new MaterialButton(this);
        MaterialButton cancel = new MaterialButton(this,null,R.attr.borderlessButtonStyle);
        imageSelector = new ImageSelector(
                this,
                ()->{
                    new MaterialAlertDialogBuilder(this)
                        .setTitle("Choose Image")
                        .setItems(new CharSequence[]{"From Camera","From Photos"},(view, i) ->{
                            if(i == 0){
                                presenter.handleTakePicturePress();
                            }else{
                            presenter.handleSelectPicturePress();
                            }
                        })
                        .show();

                });



        //buttons Layout
        LinearLayout buttons = new LinearLayout(this);
        buttons.setPadding(48,0,48,0);
        buttons.setGravity(Gravity.RIGHT);

        save.setText("Save");
        cancel.setText("Cancel");

        save.setOnClickListener(view ->{
            presenter.saveContact(nameInput.getText().toString(),
                    emailInput.getText().toString(),
                    phoneNumberInput.getText().toString(),
                    imageSelector.getImageUri());

        });
        cancel.setOnClickListener(view -> {
            presenter.handleCancelPress();
        });
        mainLayout.addView(imageSelector);
        buttons.addView(cancel);
        buttons.addView(save);


        mainLayout.addView(nameInput);
        mainLayout.addView(phoneNumberInput);
        mainLayout.addView(emailInput);
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
    public void takePhoto() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String  imageFileName = "JPEG_" + timeStamp + ".jpg";
        File imageFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), imageFileName);
        curretPhotopath = imageFile.getAbsolutePath();
        Uri photoUri = FileProvider.getUriForFile(this,"com.example.contactsapp.provider", imageFile);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
        startActivityForResult(intent, TAKE_PICTURE);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK){
            String uri = data.getData().toString();
            presenter.handlePictureSelected(uri);
        }
         if(requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK){
             presenter.handlePictureSelected(curretPhotopath);
         }
    }

    @Override
    public void displayImage(String uri) {
        imageSelector.setImageUri(uri);
    }

    @Override
    public void renderContactForm(Contact contact) {
        runOnUiThread(() ->{
            imageSelector.setImageUri(contact.imagePath);
            nameInput.setText(contact.Name);
            phoneNumberInput.setText(contact.PhoneNumber);
            emailInput.setText(contact.emailAddress);
        });

    }
}
