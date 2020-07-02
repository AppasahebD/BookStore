package com.example.bookstore.viewmodel;


import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.bookstore.model.BookCategory;
import com.example.bookstore.repository.EBookRepository;
import com.example.bookstore.utils.DataConversion;
import java.util.List;

public class BookCategoryVM extends CommonViewModel{
    public String name;
    public MutableLiveData<List<BookCategoryVM>> arrayListMutableLiveData ;
    public EBookRepository eBookRepository;

    public BookCategoryVM(@NonNull Application application) {
        super(application);
        arrayListMutableLiveData = new MutableLiveData<>();
        eBookRepository = new EBookRepository(application);
    }

    @BindingAdapter(value = "imageDrawableRes")
    public static void bindErrorImageView(ImageView imageView, byte[] imageDrawableRes) {
        imageView.setImageBitmap(DataConversion.convertByteArray2Image(imageDrawableRes));

    }


    public LiveData<List<BookCategory>> getBookCategory(){
        return eBookRepository.getAllCategoryBooks();
    }

}
