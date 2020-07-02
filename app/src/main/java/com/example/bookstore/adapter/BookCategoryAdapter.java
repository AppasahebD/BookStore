package com.example.bookstore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookstore.BookListActivity;
import com.example.bookstore.R;
import com.example.bookstore.databinding.ItemBookCategoryBinding;
import com.example.bookstore.holder.BookCategoryHolder;
import com.example.bookstore.interfaces.AdapterClick;
import com.example.bookstore.model.BookCategory;
import java.util.List;

public class BookCategoryAdapter extends RecyclerView.Adapter<BookCategoryHolder>  implements  AdapterClick{
    private Context context;
    List<BookCategory> bookCategoryArrayList;

    public BookCategoryAdapter(Context context , List<BookCategory> bookCategoryArrayList){
        this.context = context;
        this.bookCategoryArrayList = bookCategoryArrayList;
    }

    @NonNull
    @Override
    public BookCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookCategoryBinding binding= DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_book_category,parent,false);
        return new BookCategoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookCategoryHolder holder, int position) {
        BookCategory bookCategoryVM = bookCategoryArrayList.get(position);
        holder.bind(bookCategoryVM);
        holder.binding.setItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return bookCategoryArrayList.size();
    }

    @Override
    public void onClick(Object item) {
        BookCategory bookCategory = (BookCategory) item;
        Toast.makeText(context , "Clicked"+bookCategory.toString() , Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, BookListActivity.class);
        intent.putExtra("BookCategory",bookCategory.getId());
        context.startActivity(intent);

    }
}
