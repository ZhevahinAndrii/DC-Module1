package task1java;

import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args){
        List<String> books=new ArrayList<String>();
        int N_books=20;
        int N_readers=5;
        for(int i=0;i<N_books;i++){
            books.add("Book "+i);
        }
        Library library = new Library(books);
        for(int i =0;i<N_readers;i++){
            Thread thread = new Thread(new Reader(library),"Thread "+i);
            thread.start();
        }
    }
}
