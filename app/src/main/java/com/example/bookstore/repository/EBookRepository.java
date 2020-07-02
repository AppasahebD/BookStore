package com.example.bookstore.repository;

import android.content.Context;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.bookstore.dao.BookCategoryDao;
import com.example.bookstore.dao.BookDao;
import com.example.bookstore.database.BookDatabase;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookCategory;
import java.util.List;

public class EBookRepository {

    private BookDao bookDao;
    private BookCategoryDao bookCategoryDao;


    public EBookRepository(Context context) {
        BookDatabase bookDatabase = BookDatabase.getInstance(context);
        bookDao = bookDatabase.bookDao();
        bookCategoryDao = bookDatabase.bookCategoryDao();
    }

    public LiveData<List<BookCategory>> getAllCategoryBooks(){
        return bookCategoryDao.getAllCategoryBooks();
    }

    public void insertBookCategory(BookCategory bookCategory){
        new insertBookCategoryAsyncTask(bookCategoryDao).execute(bookCategory);
    }

    private class insertBookCategoryAsyncTask extends AsyncTask<BookCategory,Void,Void> {
        private BookCategoryDao bookCategoryDao;
        public insertBookCategoryAsyncTask(BookCategoryDao bookCategoryDao) {
            this.bookCategoryDao = bookCategoryDao;
        }


        @Override
        protected Void doInBackground(BookCategory... bookCategories) {
            bookCategoryDao.insertBookCategory(bookCategories[0]);
            return null;
        }
    }

    //Book insertion
    public void insertBook(Book book){
        new insertBookAsyncTask(bookDao).execute(book);
    }

    private class insertBookAsyncTask extends AsyncTask<Book,Void,Void> {
        private BookDao bookDao;
        public insertBookAsyncTask(BookDao bookDao) {
            this.bookDao = bookDao;
        }


        @Override
        protected Void doInBackground(Book... book) {
            bookDao.insertBook(book[0]);
            return null;
        }
    }

    public LiveData<List<Book>> getAllBooks(){
        return bookDao.getAllBooks();
    }

    public LiveData<List<Book>> getBookByCategory(int categoryId) {
        return bookDao.getBookByCategory(categoryId);
    }
}
