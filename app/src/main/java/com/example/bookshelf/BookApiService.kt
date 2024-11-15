package com.example.bookshelf

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 1. Define the data classes for your JSON response (adjust these as needed).
data class BooksResponse(
    val items: List<BookItem>?
)

data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val imageLinks: ImageLinks?
)

data class ImageLinks(
    val smallThumbnail: String?,
    val thumbnail: String?
)

// 2. Define Retrofit interface for API calls.
interface BooksApiService {

    // Endpoint to search books by query
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 10
    ): BooksResponse

    companion object {
        private const val BASE_URL = "https://www.googleapis.com/books/v1/"

        // Singleton Retrofit instance
        val instance: BooksApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BooksApiService::class.java)
        }
    }
}
