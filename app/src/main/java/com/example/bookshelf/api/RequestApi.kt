package com.example.bookshelf.api

import com.example.bookshelf.data.BOOK_URL
import com.example.bookshelf.data.bookinfo.BookDetail
import com.example.bookshelf.data.booklist.SearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RequestApi {
    //    BASE_URL과 GET요청을 보내는 URL이 같을 때는 .으로 retrofit에게 알린다.
    @GET(".")
    suspend fun search(@Query("q") searchWord: String): SearchResult

    @GET(".")
    suspend fun search(
        @Query("q") searchWord: String, @Query("maxResults") maxResult: Int
    ): SearchResult

    @GET(".")
    suspend fun search(
        @Query("q") searchWord: String,
        @Query("maxResults") maxResults: Int,
        @Query("startIndex") startIndex: Int
    ): SearchResult

    @GET(BOOK_URL)
    suspend fun bookRequest(@Path("book_id") id: String): BookDetail
}