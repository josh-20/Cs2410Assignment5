package com.example.contactsapp.presenters;

import com.example.contactsapp.database.AppDataBase;
import com.example.contactsapp.models.Contact;

public class NewContactPresenter {
    private MVPView view;
    private AppDataBase dataBase;
    public interface MVPView extends BaseMVPView{
         void goBackToContactsPage(Contact contact);
         void goToPhotos();
         void displayImage(String uri);
    }
    public NewContactPresenter(MVPView view){
        this.view = view;
        dataBase = view.getContextDataBase();
    }
    public void CreateContact(String name, String email, String phoneNumber,String imageUri){

        // check if valid contact.
        //TODO: check for valid email, phone number and name.
        new Thread(() ->{
            Contact contact = new Contact();
            contact.Name = name;
            contact.PhoneNumber = phoneNumber;
            contact.emailAddress = email;
            contact.imagePath = imageUri;
            dataBase.getTodoDao().insert(contact);
            view.goBackToContactsPage(contact);
        }).start();
    }
    public void handleCancelPress(){view.goBackToContactsPage(null);}

    public void handleSelectPicturePress(){
        view.goToPhotos();
    }
    public void handlePictureSelected(String uri){
        view.displayImage(uri);
    }
}
