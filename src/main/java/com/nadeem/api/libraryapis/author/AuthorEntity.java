package com.nadeem.api.libraryapis.author;
import com.nadeem.api.libraryapis.model.common.Gender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "AUTHOR")
public class AuthorEntity {
    @Id
    @Column(name = "Author_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "authorId_generator")
    @SequenceGenerator(name = "authorId_generator",sequenceName = "author_sequence",allocationSize = 50)
    private int authorId;
    @Column(name = "First_Name")
    private String firstName;
    @Column(name = "Last_Name")
    private String lastName;
    @Column(name = "Date_Of_Birth")
    private LocalDate  dateOfBirth;
    @Column(name="Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    public AuthorEntity(){

    }

    public AuthorEntity(String firstName, String lastName, LocalDate  dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public int getAuthorId() {
        return authorId;
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

}
