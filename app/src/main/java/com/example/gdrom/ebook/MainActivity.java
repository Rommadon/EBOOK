package com.example.gdrom.ebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.gdrom.ebook.data.Book;
import com.example.gdrom.ebook.data.RemoteBookRepository;
import com.example.gdrom.ebook.data.Repository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  BookView{

    BookPresenter presenter;
    ArrayAdapter<Book> bookArrayAdapter, searchArrayAdapter;
    private ListView bookListView;
    private EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = RemoteBookRepository.getInstance();

        bookListView = (ListView) findViewById(R.id.list);
        searchText = (EditText) findViewById(R.id.text);

        bookArrayAdapter = createAdapter(new ArrayList<Book>());
        bookListView.setAdapter(bookArrayAdapter);

        presenter = new BookPresenter(repository, this);
        presenter.initialize();
    }

    @Override
    public void setBookList(ArrayList<Book> books) {
        bookArrayAdapter = createAdapter(books);
        bookListView.setAdapter(bookArrayAdapter);
    }

    private ArrayAdapter<Book> createAdapter(ArrayList<Book> books) {
        return new ArrayAdapter<Book>(this,
                android.R.layout.simple_list_item_1,
                books);
    }

    public void onSearch(View view){
        String s = searchText.getText().toString();
        bookListView.setAdapter(null);

        presenter.searchWishList(s);
        searchArrayAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1,presenter.getSearch());
        bookListView.setAdapter(searchArrayAdapter );


    }


}
