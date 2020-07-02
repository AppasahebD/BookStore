package com.example.bookstore.database;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.bookstore.dao.BookCategoryDao;
import com.example.bookstore.dao.BookDao;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookCategory;


@Database(entities = {BookCategory.class, Book.class}, version = 1 , exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {

    public abstract BookCategoryDao bookCategoryDao();
    public abstract BookDao bookDao();

    private static BookDatabase instance;

    public static BookDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BookDatabase.class, "books_database")
                    .build();
        }
        return instance;
    }




}
