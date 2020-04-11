package com.nadeem.api.libraryapis.book;

import javax.persistence.*;

@Entity
@Table(name = "BOOK_STATUS")
public class BookStatusEntity {
    @Id
    @Column(name = "Book_Id")
    private int bookid;
    @Enumerated(EnumType.STRING)
    private BookStatusState state;
    @Column(name = "Total_Number_Of_Copies")
    private int totlaNumberOfCopies;
    @Column(name = "Number_Of_Copies_Issued")
    private int numberOfCopiesIssued;
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "Book_Id",nullable = false)
    private BookEntity bookEntity;

    public BookStatusEntity() {
    }
public BookStatusEntity(int bookid, BookStatusState state, int totlaNumberOfCopies, int numberOfCopiesIssued){
        this.bookid=bookid;
        this.state=state;
        this.totlaNumberOfCopies=totlaNumberOfCopies;
        this.numberOfCopiesIssued=numberOfCopiesIssued;
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
