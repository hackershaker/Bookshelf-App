package com.example.bookshelf.ui

import android.util.Log
import com.example.bookshelf.api.RequestApi
import com.example.bookshelf.data.bookinfo.BookDetail
import com.example.bookshelf.data.booklist.SearchResult

private val TAG = "bookshelf.BookRepository"

class BookRepository(val requestApi: RequestApi) : Repository {

    /**
     * 검색어 입력 시 그에 맞는 책 리스트를 가져온다. 띄어쓰기 전처리 필요.
     */
    suspend fun search(searchWord: String): SearchResult {
        Log.d(TAG, "requestApi search method 시작.")
        val result = requestApi.search(searchWord)
        Log.d(TAG, "requestApi search method 응답: $result")
        return result
    }

    suspend fun search(searchWord: String, startIndex: Int, maxResult: Int): SearchResult =
        requestApi.search(searchWord, maxResult, startIndex)


    suspend fun getBookInfo(bookId: String): BookDetail {
        return requestApi.bookRequest(bookId)
    }


}