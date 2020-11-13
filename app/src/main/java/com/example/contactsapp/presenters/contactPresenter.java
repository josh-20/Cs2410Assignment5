package com.example.contactsapp.presenters;

import com.example.contactsapp.database.AppDataBase;
import com.example.contactsapp.models.Contact;

public class contactPresenter {

    private MVPView view;
    private AppDataBase dataBase;
    private Contact contact;

    public interface MVPView extends BaseMVPView{
        public void displayContact(Contact contact);
    }

    public contactPresenter(MVPView view){
        this.view = view;
        view.displayContact(contact);
    }
}
