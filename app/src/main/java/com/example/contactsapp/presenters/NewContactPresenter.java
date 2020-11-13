package com.example.contactsapp.presenters;

import com.example.contactsapp.database.AppDataBase;
import com.example.contactsapp.models.Contact;

public class NewContactPresenter {
    private MVPView view;
    private AppDataBase dataBase;
    public interface MVPView extends BaseMVPView{
        public void goBackToContactsPage(Contact contact);
    }
    public NewContactPresenter(MVPView view){
        this.view = view;
        dataBase = view.getContextDataBase();
    }
    public void CreateContact(String name, String email, int phoneNumber){

        // check if valid contact.
        new Thread(() ->{
            Contact contact = new Contact();
            contact.Name = name;
            contact.PhoneNumber = phoneNumber;
            contact.emailAddress = email;
//            contact.isComplete = true;
            dataBase.getTodoDao().insert(contact);
            view.goBackToContactsPage(contact);
        }).start();
    }
}
