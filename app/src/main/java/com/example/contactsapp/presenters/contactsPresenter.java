package com.example.contactsapp.presenters;

import com.example.contactsapp.database.AppDataBase;
import com.example.contactsapp.models.Contact;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class contactsPresenter{
    private MVPView view;
    private ArrayList<Contact> contacts = new ArrayList<>();
    private AppDataBase dataBase;

    public interface MVPView extends BaseMVPView{
        public void renderContacts(ArrayList<Contact> contacts);
        public void goToNewContactsPage();
    }

    public contactsPresenter(MVPView view){
        this.view = view;
        dataBase = view.getContextDataBase();
        new Thread(() ->{
            contacts = (ArrayList<Contact>)dataBase.getTodoDao().getAllContacts();
            view.renderContacts(contacts);
        }).start();

    }
    public void goToNewContactsPage(){
        view.goToNewContactsPage();
    }
    public void onNewContactCreate(Contact contact){
        contacts.add(contact);
        view.renderContacts(contacts);
    }
}
