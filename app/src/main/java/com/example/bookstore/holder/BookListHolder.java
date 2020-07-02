package com.example.bookstore.holder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.databinding.ItemBookBinding;
import com.example.bookstore.model.Book;

public class BookListHolder extends RecyclerView.ViewHolder {
    public ItemBookBinding binding;
    public BookListHolder(ItemBookBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Book book) {
        this.binding.setViewmodel(book);
        binding.executePendingBindings();
    }

    public ItemBookBinding getBinding() {
        return binding;
    }
}
