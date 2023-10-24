package task1java;
import java.util.List;
import java.security.SecureRandom;
import java.util.concurrent.Semaphore;
public class Library {

    public final int booksInLibrary;
    public final List<String> books;
    public Semaphore semaphore = new Semaphore(1);
    public Library(List<String>books){
        this.books=books;
        this.booksInLibrary = books.size();
    }

}

