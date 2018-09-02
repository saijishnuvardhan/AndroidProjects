package com.example.praneeth.booklist;

public class Book {

    private String title;
    private String author;
    private String url;

    public Book(String t,String a,                                                                                                                                                                                                                                                                                                                                          String u){
        title=t;
        author=a;

        url=u;
    }

    public String gettitle(){
        return title;
    }

    public String getAuthor() {
        return author;
    }


    public String getUrl() {
        return url;
    }
}
