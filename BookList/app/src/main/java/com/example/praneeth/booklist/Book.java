package com.example.praneeth.booklist;

public class Book {

    private String title;
    private String author;
    private String url;
    private String thumbnail;

    public Book(String t,String a, String u,String thumbnail){
        title=t;
        author=a;
       this.thumbnail=thumbnail;
        url=u;
    }

    public String gettitle(){
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getUrl() {
        return url;
    }
}
