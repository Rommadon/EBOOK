package com.example.gdrom.ebook;

/**
 * Created by gdrom on 6/5/2017 AD.
 */

import com.example.gdrom.ebook.data.Book;
import com.example.gdrom.ebook.data.Repository;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;




    public class BookPresenter implements Observer {
        private BookView view;
        private Repository repository;

        ArrayList<Book> books,searchList;

        public BookPresenter(Repository repository, BookView view) {
            this.repository = repository;
            this.view = view;
        }

        public void initialize() {
            repository.addObserver(this);
            repository.fetchAllBooks();
        }

        @Override
        public void update(Observable obj, Object arg) {
            if(obj == repository) {
                books = new ArrayList<Book>(repository.getAllBooks());
                view.setBookList(books);
            }
        }

        public void searchWishList(String s) {

            searchList.clear();



            for(int i=0 ;i<books.size();i++){
                if(books.get(i).getTitle().contains(s)){
                    searchList.add(books.get(i));

                }else if(books.get(i).getPublicationYear()==Integer.parseInt(s)){
                    searchList.add(books.get(i));

                }
            }
        }


        public ArrayList<Book> getSearch() {
            return searchList;
        }
    }





