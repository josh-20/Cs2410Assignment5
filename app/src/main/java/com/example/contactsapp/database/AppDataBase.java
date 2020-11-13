package com.example.contactsapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.contactsapp.models.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TodoDao getTodoDao();
}
