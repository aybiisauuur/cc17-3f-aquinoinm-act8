package com.example.bookshelf

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {

    private val repository = BooksRepository()

    // LiveData to hold the list of books
    private val _books = MutableLiveData<List<BookItem>>()
    val books: LiveData<List<BookItem>> get() = _books

    // LiveData to handle loading state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    // LiveData to handle error messages
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    // Function to fetch books based on a search query
    fun fetchBooks(query: String) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                // Fetch books from the repository
                val bookList = repository.searchBooks(query)
                _books.value = bookList ?: emptyList()
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Function to clear error messages
    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}