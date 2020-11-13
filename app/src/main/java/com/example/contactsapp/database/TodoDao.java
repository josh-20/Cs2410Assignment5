package com.example.contactsapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.contactsapp.models.Contact;

import java.util.List;

// insert and Retrieve things from the dataBase
@Dao
public interface TodoDao {
    @Query("SELECT * FROM contact")
    List<Contact> getAllContacts();

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);
}
