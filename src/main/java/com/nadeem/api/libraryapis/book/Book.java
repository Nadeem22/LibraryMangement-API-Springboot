package com.nadeem.api.libraryapis.book;

import com.nadeem.api.libraryapis.author.Author;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class Book {
    private Integer BookId;
    @NotNull
    @Size(min=1,max = 50, message = "ISBN must be 1 to 50 character")
    private String isbn;
    @Size(min = 1, max = 50, message = "Title must be 1 to 50 character")
    private String title;
    private Integer publisherId;
    private Integer yearPublished;
    @Size(min = 1, max = 50, message = "Edition must be 1 to 50 character")
    private  String edition;
    private BookStatus bookStatus;
    private Set<Author> author=new HashSet<>();

    public Book(Integer bookId,  String isbn,  String title, Integer publisherId, Integer yearPublished, String edition, BookStatus bookStatus, Set<Author> author) {
        BookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.publisherId = publisherId;
        this.yearPublished = yearPublished;
        this.edition = edition;
        this.bookStatus = bookStatus;
        this.author = author;
    }

    public Integer getBookId() {
        return BookId;
    }

    public void setBookId(Integer bookId) {
        BookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Set<Author> getAuthor() {
        return author;
    }

    public void setAuthor(Set<Author> author) {
        this.author = author;
    }
}
