package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import com.example.bookstore.adapter.BookListAdapter;
import com.example.bookstore.databinding.ActivityBookListBinding;
import com.example.bookstore.model.Book;
import com.example.bookstore.viewmodel.BookVM;

import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private ActivityBookListBinding binding;
    private BookVM bookVM;
    private BookListAdapter adapter;
    private int categoryID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_list);
        bookVM = new ViewModelProvider(this).get(BookVM.class);
        categoryID = getIntent().getIntExtra("BookCategory",0);

        bookVM.getCategoryByBooks(categoryID).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> bookList) {
                adapter=new BookListAdapter(BookListActivity.this,bookList);
                binding.recyclerviewBookList.setLayoutManager(new LinearLayoutManager(BookListActivity.this));
                binding.recyclerviewBookList.setAdapter(adapter);

            }
        });
    }
}
