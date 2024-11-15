package com.example.bookshelf.model.BooksResponse

import com.google.gson.annotations.SerializedName

data class BooksResponse (
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("items") val items: List<BookItem>?
)

// Represents each book in the list
data class BookItem(
    @SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo
)

// Contains details about each book
data class VolumeInfo(
    @SerializedName("title") val title: String,
    @SerializedName("authors") val authors: List<String>?,
    @SerializedName("publishedDate") val publishedDate: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("imageLinks") val imageLinks: ImageLinks?
)

// Contains URLs for book images
data class ImageLinks(
    @SerializedName("thumbnail") val thumbnail: String?
)