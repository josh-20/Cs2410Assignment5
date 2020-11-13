package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.contactsapp.database.AppDataBase;
import com.example.contactsapp.presenters.BaseMVPView;

public class BaseActivity extends AppCompatActivity implements BaseMVPView {

    public AppDataBase getContextDataBase(){
        return Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "contacts-database").build();
    }
}
