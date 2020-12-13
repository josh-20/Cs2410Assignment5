package com.example.contactsapp.presenters;

import com.example.contactsapp.database.AppDataBase;
import com.example.contactsapp.models.Contact;

public class CreateOrUpdateContactPresenter {
    private MVPView view;
    private AppDataBase dataBase;
    public static final int DEFAULT_ID = -1;
    Contact contact;
    public interface MVPView extends BaseMVPView{
         void goBackToContactsPage(Contact contact);
         void goToPhotos();
         void takePhoto();
         void displayImage(String uri);
         void renderContactForm(Contact contact);
    }
    public CreateOrUpdateContactPresenter(MVPView view){
        this.view = view;
        dataBase = view.getContextDataBase();
    }
    public void saveContact(String name, String email, String phoneNumber,String imageUri){

        // check if valid contact.
        //TODO: check for valid email, phone number and name.
        new Thread(() ->{
            Contact contactToSave;
            if(contact != null){
                contactToSave = contact;
            }else{
                contactToSave = new Contact();

            }
            contactToSave.Name = name;
            contactToSave.PhoneNumber = phoneNumber;
            contactToSave.emailAddress = email;
            contactToSave.imagePath = imageUri;

            if (contact != null){
                dataBase.getTodoDao().update(contactToSave);

            }else{
                dataBase.getTodoDao().insert(contactToSave);
            }
            view.goBackToContactsPage(contactToSave);
        }).start();
    }
    public void handleCancelPress(){view.goBackToContactsPage(null);}

    public void handleSelectPicturePress(){
        view.goToPhotos();
    }
    public void handleTakePicturePress(){
        view.takePhoto();
    }
    public void handlePictureSelected(String uri){
        view.displayImage(uri);
    }

    public void loadContact(Contact contact1) {
        if (contact1.id != DEFAULT_ID) {
            new Thread(() ->{
                contact = contact1;
                view.renderContactForm(contact);
            }).start();
        }
    }
}
