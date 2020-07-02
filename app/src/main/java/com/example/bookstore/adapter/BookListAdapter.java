package com.example.bookstore.adapter;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.PdfViewerActivity;
import com.example.bookstore.R;
import com.example.bookstore.databinding.ItemBookBinding;
import com.example.bookstore.holder.BookListHolder;
import com.example.bookstore.interfaces.AdapterClick;
import com.example.bookstore.model.Book;


import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListHolder> implements AdapterClick {
    private Context context;
    private List<Book> bookList;

    public BookListAdapter(Context context,List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_book,parent,false);
        return new BookListHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull BookListHolder holder, int position) {
        Book book = bookList.get(position);
        holder.bind(book);
        holder.binding.setItemClickListener(this);


    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    @Override
    public void onClick(Object item) {
        Book book = (Book) item;
        Intent intent = new Intent(context, PdfViewerActivity.class);
        intent.putExtra("BookName",book.getName());
        context.startActivity(intent);

    }
}
