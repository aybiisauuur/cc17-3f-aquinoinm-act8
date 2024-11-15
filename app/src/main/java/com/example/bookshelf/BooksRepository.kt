package com.example.bookshelf

class BooksRepository {

    private val apiService = BooksApiService.instance

    suspend fun searchBooks(query: String): List<BookItem>? {
        val response = apiService.searchBooks(query)
        return response.items
    }
}