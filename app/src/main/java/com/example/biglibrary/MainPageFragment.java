package com.example.biglibrary;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.biglibrary.model.Book;
import com.example.biglibrary.model.BooksService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainPageFragment extends Fragment {
    private FloatingActionButton addBookButton, findBookButton;
    private RecyclerView recyclerView;
    private BooksService booksService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main_page, container, false);
        addBookButton = (FloatingActionButton) view.findViewById(R.id.add_book_button);
        findBookButton = (FloatingActionButton) view.findViewById(R.id.find_book_button);


        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFABs();
                Navigation.findNavController(view)
                        .navigate(R.id.action_mainPageFragment_to_addBookFragment);

            }
        });

        findBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFABs();
                Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_findBookFragment);


            }
        });

        recyclerView = view.findViewById(R.id.book_rec_view);

        BookAdapter adapter = new BookAdapter(booksService);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        return  view;
    }

    public void onResume() {
        super.onResume();
        addBookButton.show();
        findBookButton.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        booksService = new BooksService();
        ArrayList<String> tags = new ArrayList<>();
        tags.add("some tag");
        booksService.addBook(new Book("id", "Some title", "some author",
                "2002", "2020-1-1","SomeInfoAboutBook",tags));

    }


    private void hideFABs(){
        findBookButton.hide();
        addBookButton.hide();
    }



}