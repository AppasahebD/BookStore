package com.example.bookstore.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bookstore.model.BookCategory;

import java.util.List;

@Dao
public interface BookCategoryDao {

    @Insert
    public void insertBookCategory(BookCategory bookCategory);

    @Update
    public void updateBookCategory(BookCategory bookCategory);

    @Delete
    public  void  deleteCategory(BookCategory bookCategory);

    @Query("select * from bookcategory")
    public LiveData<List<BookCategory>> getAllCategoryBooks();
}
