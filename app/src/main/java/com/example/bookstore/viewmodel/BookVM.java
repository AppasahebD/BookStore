package com.example.bookstore.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.EBookRepository;

import java.util.List;

public class BookVM extends CommonViewModel {

    public EBookRepository eBookRepository;

    public BookVM(@NonNull Application application) {
        super(application);
        eBookRepository = new EBookRepository(application);
    }

    public LiveData<List<Book>> getAllBooks() {
        return eBookRepository.getAllBooks();
    }

    public LiveData<List<Book>> getCategoryByBooks(int categoryID){
        return eBookRepository.getBookByCategory(categoryID);
    }
}
