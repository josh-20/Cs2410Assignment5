package com.example.contactsapp.presenters;

import com.example.contactsapp.database.AppDataBase;
import com.example.contactsapp.models.Contact;

public class contactPresenter {

    private MVPView view;
    private AppDataBase dataBase;
    private Contact contact;

    public interface MVPView extends BaseMVPView{
        void goBackToContactsPage(Contact contact,boolean didDelete);
        void goToEditPage(Contact contact);
        void makePhoneCall(String number);
        void sendText(String number);
        void sendEmail(String emailAdress);

    }

    public contactPresenter(MVPView view){
        this.view = view;
        dataBase = view.getContextDataBase();
    }

    public void deleteContact(Contact contact){
        new Thread(() ->{
            dataBase.getTodoDao().delete(contact);
            view.goBackToContactsPage(contact,true);
        }).start();
    }
    public void handleEditClick(){
        view.goToEditPage(contact);
    }
    public void handleCallPress(String phoneNumber){
        view.makePhoneCall(phoneNumber);

    }
    public void handleTextPress(String phoneNumber){
        view.sendText(phoneNumber);

    }
    public void handleEmailPress(String email){
        view.sendEmail(email);
    }
}
