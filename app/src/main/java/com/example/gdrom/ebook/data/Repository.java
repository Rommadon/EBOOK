package com.example.gdrom.ebook.data;

import java.util.List;
import java.util.Observable;

/**
 * Created by gdrom on 6/5/2017 AD.
 */


public abstract class Repository extends Observable {
    public abstract void fetchAllBooks();
    public abstract List<Book> getAllBooks();



}

