package com.example.praneeth.booklist;

import java.util.List;

public class volumeInfo {

    private String title;
    private List<String> authors;
    private String infoLink;
    private imageLinks imageLinks;

    public volumeInfo(String title, List<String> authors, String infoLink, imageLinks imageLinks) {
        this.title = title;
        this.authors = authors;
        this.infoLink = infoLink;
        this.imageLinks = imageLinks;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public imageLinks getImageLinks() {
        return imageLinks;
    }


}
