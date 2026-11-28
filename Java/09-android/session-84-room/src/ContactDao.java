// ContactDao.java — Room DAO
// نیازمند Android SDK
package com.example.myapp;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAllContacts();

    @Query("SELECT * FROM contacts WHERE category = :category")
    LiveData<List<Contact>> getContactsByCategory(String category);

    @Query("SELECT * FROM contacts WHERE id = :id")
    Contact getContactById(int id);

    @Insert
    void insert(Contact contact);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Contact> contacts);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM contacts WHERE id = :id")
    void deleteById(int id);

    @Query("DELETE FROM contacts")
    void deleteAll();
}
