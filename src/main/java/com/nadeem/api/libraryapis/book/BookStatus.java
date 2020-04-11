package com.nadeem.api.libraryapis.book;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

public class BookStatus {

    private int bookid;

    private BookStatusState state;
    @Pattern(regexp = "[1-9][0-9]")
    private int totlaNumberOfCopies;
    @Pattern(regexp = "[1-9][0-9]")
    private int numberOfCopiesIssued;

    public BookStatus(BookStatusState state,  int totlaNumberOfCopies,  int numberOfCopiesIssued) {
        this.state = state;
        this.totlaNumberOfCopies = totlaNumberOfCopies;
        this.numberOfCopiesIssued = numberOfCopiesIssued;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public BookStatusState getState() {
        return state;
    }

    public void setState(BookStatusState state) {
        this.state = state;
    }

    public int getTotlaNumberOfCopies() {
        return totlaNumberOfCopies;
    }

    public void setTotlaNumberOfCopies(int totlaNumberOfCopies) {
        this.totlaNumberOfCopies = totlaNumberOfCopies;
    }

    public int getNumberOfCopiesIssued() {
        return numberOfCopiesIssued;
    }

    public void setNumberOfCopiesIssued(int numberOfCopiesIssued) {
        this.numberOfCopiesIssued = numberOfCopiesIssued;
    }
}
