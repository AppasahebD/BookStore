package com.example.bookstore.services;

import android.content.Context;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.example.bookstore.R;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookCategory;
import com.example.bookstore.repository.EBookRepository;
import com.example.bookstore.utils.DataConversion;


public class MasterSyncWorkerManager extends Worker{
    private EBookRepository eBookRepository;
    public static final String PROGRESS = "PROGRESS";
    private Context context;
    public MasterSyncWorkerManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
        eBookRepository = new EBookRepository(context);
        setProgressAsync(new Data.Builder().putInt(PROGRESS,0).build());
    }

    @NonNull
    @Override
    public Result doWork() {

        BookCategory bookCategoryNovel = new BookCategory();
        bookCategoryNovel.setBookImg(DataConversion.convertImage2ByteArray(BitmapFactory.decodeResource(context.getResources(),R.drawable.maharaj)));
        bookCategoryNovel.setName("Novel");
        eBookRepository.insertBookCategory(bookCategoryNovel);

        BookCategory bookCategoryStory = new BookCategory();
        bookCategoryStory.setBookImg(DataConversion.convertImage2ByteArray(BitmapFactory.decodeResource(context.getResources(),R.drawable.maharaj)));
        bookCategoryStory.setName("Story");
        eBookRepository.insertBookCategory(bookCategoryStory);

        setProgressAsync(new Data.Builder().putInt(PROGRESS,25).build());

        BookCategory bookCategoryHistorical = new BookCategory();
        bookCategoryHistorical.setName("History");
        bookCategoryHistorical.setBookImg(DataConversion.convertImage2ByteArray(BitmapFactory.decodeResource(context.getResources(),R.drawable.csm)));
        eBookRepository.insertBookCategory(bookCategoryHistorical);

        BookCategory bookCategoryFort = new BookCategory();
        bookCategoryFort.setName("Fort");
        bookCategoryFort.setBookImg(DataConversion.convertImage2ByteArray(BitmapFactory.decodeResource(context.getResources(),R.drawable.fort)));
        eBookRepository.insertBookCategory(bookCategoryFort);

        setProgressAsync(new Data.Builder().putInt(PROGRESS,50).build());

        Book bookPratapgad = new Book();
        bookPratapgad.setName("Pratapgad.pdf");
        bookPratapgad.setDescription("c Shivaji Maharaj Fort ");
        bookPratapgad.setCategoryId(4);
        bookPratapgad.setRating("5");
        bookPratapgad.setBookImg(DataConversion.convertImage2ByteArray(BitmapFactory.decodeResource(context.getResources(),R.drawable.lohagad)));
        eBookRepository.insertBook(bookPratapgad);

        Book bookLohagad = new Book();
        bookLohagad.setName("Lohagad.pdf");
        bookLohagad.setDescription("c Shivaji Maharaj Fort ");
        bookLohagad.setCategoryId(4);
        bookLohagad.setRating("5");
        bookLohagad.setBookImg(DataConversion.convertImage2ByteArray(BitmapFactory.decodeResource(context.getResources(),R.drawable.lohagad)));
        eBookRepository.insertBook(bookLohagad);

        setProgressAsync(new Data.Builder().putInt(PROGRESS,60).build());


        Book bookPurandar = new Book();
        bookPurandar.setName("Purandar.pdf");
        bookPurandar.setDescription("c Shivaji Maharaj Fort ");
        bookPurandar.setCategoryId(4);
        bookPurandar.setRating("5");
        bookPurandar.setBookImg(DataConversion.convertImage2ByteArray(BitmapFactory.decodeResource(context.getResources(),R.drawable.lohagad)));
        eBookRepository.insertBook(bookPurandar);

        Book bookRaigad = new Book();
        bookRaigad.setName("Raigad.pdf");
        bookRaigad.setDescription("c Shivaji Maharaj Fort ");
        bookRaigad.setCategoryId(4);
        bookRaigad.setRating("5");
        bookRaigad.setBookImg(DataConversion.convertImage2ByteArray(BitmapFactory.decodeResource(context.getResources(),R.drawable.raigad)));
        eBookRepository.insertBook(bookRaigad);

        setProgressAsync(new Data.Builder().putInt(PROGRESS,70).build());

        Book bookShivneri = new Book();
        bookShivneri.setName("Shivneri.pdf");
        bookShivneri.setDescription("c Shivaji Maharaj Fort ");
        bookShivneri.setCategoryId(4);
        bookShivneri.setRating("5");
        bookShivneri.setBookImg(DataConversion.convertImage2ByteArray(BitmapFactory.decodeResource(context.getResources(),R.drawable.shivneri)));
        eBookRepository.insertBook(bookShivneri);

        Book bookSindhudurg = new Book();
        bookSindhudurg.setName("Sindhudurg.pdf");
        bookSindhudurg.setDescription("c Shivaji Maharaj Fort ");
        bookSindhudurg.setCategoryId(4);
        bookSindhudurg.setRating("5");
        bookSindhudurg.setBookImg(DataConversion.convertImage2ByteArray(BitmapFactory.decodeResource(context.getResources(),R.drawable.lohagad)));
        eBookRepository.insertBook(bookSindhudurg);

        setProgressAsync(new Data.Builder().putInt(PROGRESS,80).build());

        Book bookTorna = new Book();
        bookTorna.setName("Torna.pdf");
        bookTorna.setDescription("c Shivaji Maharaj Fort ");
        bookTorna.setCategoryId(4);
        bookTorna.setRating("5");
        bookTorna.setBookImg(DataConversion.convertImage2ByteArray(BitmapFactory.decodeResource(context.getResources(),R.drawable.lohagad)));
        eBookRepository.insertBook(bookTorna);

        Book bookVijaydurg = new Book();
        bookVijaydurg.setName("Vijaydurg.pdf");
        bookVijaydurg.setDescription("c Shivaji Maharaj Fort ");
        bookVijaydurg.setCategoryId(4);
        bookVijaydurg.setRating("5");
        bookVijaydurg.setBookImg(DataConversion.convertImage2ByteArray(BitmapFactory.decodeResource(context.getResources(),R.drawable.lohagad)));
        eBookRepository.insertBook(bookVijaydurg);

        setProgressAsync(new Data.Builder().putInt(PROGRESS,90).build());

        Book bookVishalgad = new Book();
        bookVishalgad.setName("Vishalgad.pdf");
        bookVishalgad.setDescription("c Shivaji Maharaj Fort ");
        bookVishalgad.setCategoryId(4);
        bookVishalgad.setRating("5");
        bookVishalgad.setBookImg(DataConversion.convertImage2ByteArray(BitmapFactory.decodeResource(context.getResources(),R.drawable.lohagad)));
        eBookRepository.insertBook(bookVishalgad);

        setProgressAsync(new Data.Builder().putInt(PROGRESS,100).build());

        return Result.success();
    }


}
