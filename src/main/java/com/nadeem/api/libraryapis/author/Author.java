package com.nadeem.api.libraryapis.author;

import com.nadeem.api.libraryapis.model.common.Gender;


import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class Author {

    private Integer authorId;
    @NotNull
    @Size(min = 1,max = 50,message = "First Name must be between 1 and 50 character")
    private String firstName;
    @NotNull
    @Size(min = 1,max = 50,message = "Last Name must be between 1 and 50 character")
    private String lastName;
    @Past(message = "Date of birth must be past date")

    private LocalDate  dateOfBirth;
    private Gender gender;

    public Author(){

    }

    public Author(Integer authorId, String firstName, String lastName, LocalDate  dateOfBirth, Gender gender) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate  getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate  dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                '}';
    }
}
