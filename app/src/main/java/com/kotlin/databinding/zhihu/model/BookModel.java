package com.kotlin.databinding.zhihu.model;

import java.util.ArrayList;

/**
 * Created by chawei on 2017/6/28.
 */

public class BookModel{
    private int count;
    private int start;
    private int total;
    private ArrayList<BookInfoResponse>books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<BookInfoResponse> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<BookInfoResponse> books) {
        this.books = books;
    }
}
