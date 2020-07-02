package com.example.bookstore;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.bookstore.adapter.BookCategoryAdapter;
import com.example.bookstore.databinding.ActivityBookListCategoryBinding;
import com.example.bookstore.model.BookCategory;
import com.example.bookstore.utils.GridSpacingItemDecoration;
import com.example.bookstore.viewmodel.BookCategoryVM;

import java.util.List;

public class BookListCategoryActivity extends CommonActivity {
    private ActivityBookListCategoryBinding binding;
    private BookCategoryVM bookCategoryVM;
    private BookCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_category);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_book_list_category);
        bookCategoryVM = new ViewModelProvider(this).get(BookCategoryVM.class);
        bookCategoryVM.getBookCategory().observe(this, new Observer<List<BookCategory>>() {
            @Override
            public void onChanged(List<BookCategory> bookCategoryList) {
                adapter=new BookCategoryAdapter(BookListCategoryActivity.this,bookCategoryList);
                binding.recyclerviewBookList.setLayoutManager(new GridLayoutManager(getApplicationContext(),2, RecyclerView.VERTICAL,false));
                binding.recyclerviewBookList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                binding.recyclerviewBookList.setItemAnimator(new DefaultItemAnimator());
                binding.recyclerviewBookList.setAdapter(adapter);

            }
        });

    }

}
