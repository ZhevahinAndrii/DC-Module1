package task1java;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
public class Reader implements Runnable{
    public static SecureRandom random = new SecureRandom();
    private final Library library;
    public Reader(Library lib){
        this.library = lib;
    }
    @Override
    public void run(){
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis()<start+50000){
            if(random.nextBoolean()){
                try{
                    library.semaphore.acquire();
                    List<String> taken_books = new ArrayList<String>();
                    int taken_books_num = random.nextInt(library.booksInLibrary);
                    String reader_str = Thread.currentThread().getName()+" is reading books at home:";
                    for(int i=0;i<taken_books_num&&!library.books.isEmpty();i++){
                        String taken_book = library.books.remove(random.nextInt(library.books.size()));
                        taken_books.add(taken_book);
                        reader_str += taken_book+",";
                    }

                    if(reader_str==Thread.currentThread().getName()+" is reading books at home:"){
                        reader_str=Thread.currentThread().getName()+" is waiting for some books to be available";
                    }
                    System.out.println(reader_str);
                    library.semaphore.release();
                    Thread.sleep(taken_books_num*1000);
                    library.semaphore.acquire();
                    for(String taken_book:taken_books){
                        library.books.add(taken_book);
                    }
                    library.semaphore.release();
                    System.out.println(Thread.currentThread().getName()+" returned all the books he taken");
                    Thread.sleep(random.nextInt(1700));
                }
                catch(InterruptedException exception) {
                    exception.printStackTrace();
                }
                }
            else{
                try{
                    library.semaphore.acquire();
                    List<String> taken_books = new ArrayList<String>();
                    int taken_books_num = random.nextInt(library.booksInLibrary/2);
                    String reader_str = Thread.currentThread().getName()+" is reading books at library:";
                    for(int i =0;i<taken_books_num&&!library.books.isEmpty();i++){
                        String taken_book=library.books.remove(random.nextInt(library.books.size()));
                        taken_books.add(taken_book);
                        reader_str+=taken_book+",";
                    }
                    library.semaphore.release();
                    if(reader_str==Thread.currentThread().getName()+" is reading books at library:"){
                        reader_str=Thread.currentThread().getName()+" is waiting for some books to be available";
                    }
                    System.out.println(reader_str);
                    Thread.sleep(taken_books_num*450);
                    library.semaphore.acquire();
                    for(String taken_book:taken_books){
                        library.books.add(taken_book);
                    }
                    library.semaphore.release();
                    System.out.println(Thread.currentThread().getName()+" returned all the books he taken");
                    Thread.sleep(random.nextInt(1700));
                }
                catch(InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            }
        }
    }

