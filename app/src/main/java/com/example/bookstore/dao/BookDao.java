package com.example.bookstore.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bookstore.model.Book;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    public void insertBook(Book book);

    @Update
    public void updateBook(Book book);

    @Delete
    public void deleteBook(Book boook);

    @Query("select * from books")
    public LiveData<List<Book>> getAllBooks();

    @Query("select * from books where categoryId = :categoryId")
    public LiveData<List<Book>> getBookByCategory(int categoryId);

}
