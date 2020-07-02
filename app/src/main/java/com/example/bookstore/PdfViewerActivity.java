package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bookstore.model.Book;


public class PdfViewerActivity extends AppCompatActivity {
//    PDFView pdfView;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
       // pdfView=findViewById(R.id.pdfv);
        name = getIntent().getStringExtra("BookName");
       // pdfView.fromAsset(name).load();
    }
}
