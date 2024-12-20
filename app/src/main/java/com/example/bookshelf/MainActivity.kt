package com.example.bookshelf

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var booksViewModel: BooksViewModel
    private lateinit var booksAdapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val booksRecyclerView = findViewById<RecyclerView>(R.id.booksRecyclerView)

        booksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)
        booksViewModel.books.observe(this) { books ->
            booksAdapter = BooksAdapter(books)
            booksRecyclerView.adapter = booksAdapter
        }

        booksViewModel.fetchBooks("android")
    }
}