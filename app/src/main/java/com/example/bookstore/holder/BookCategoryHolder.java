package com.example.bookstore.holder;


import androidx.recyclerview.widget.RecyclerView;
import com.example.bookstore.databinding.ItemBookCategoryBinding;
import com.example.bookstore.model.BookCategory;


public class BookCategoryHolder extends RecyclerView.ViewHolder {
    public ItemBookCategoryBinding binding;

    public BookCategoryHolder(ItemBookCategoryBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(BookCategory bookCategoryVM) {
        this.binding.setViewmodel(bookCategoryVM);
        binding.executePendingBindings();
    }

    public ItemBookCategoryBinding getBinding() {
        return binding;
    }
}
