package com.example.bookshelf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BooksAdapter (private val books: List<BookItem>) :
    RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookImageView: ImageView = itemView.findViewById(R.id.bookImageView)
        val bookTitleTextView: TextView = itemView.findViewById(R.id.bookTitleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bookTitleTextView.text = book.volumeInfo.title
        val imageUrl = book.volumeInfo.imageLinks?.thumbnail?.replace("http", "https")
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.bookImageView)
    }

    override fun getItemCount() = books.size
}
