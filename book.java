package com.example.studyproject;

public class book {

    int bookID; //책 고유번호
    String bookTitle; //책 이름
    String bookWriter; //책 저자
    String bookPublisher; //출판사

    public book(int bookID, String bookTitle, String bookWriter, String bookPublisher) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookWriter = bookWriter;
        this.bookPublisher = bookPublisher;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookWriter() {
        return bookWriter;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }
}
