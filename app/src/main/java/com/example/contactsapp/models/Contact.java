package com.example.contactsapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Name")
    public String Name;

    @ColumnInfo(name = "Phone_Number")
    public String PhoneNumber;

    @ColumnInfo(name = "email_Address")
    public String emailAddress;

    @ColumnInfo(name ="image_path")
    public String imagePath;
}
