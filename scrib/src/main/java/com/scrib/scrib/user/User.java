package com.scrib.scrib.user;

import com.scrib.scrib.userRole.UserRole;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Entity
@Table(name="users")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String firstName;
    private String secondName;
    private LocalDate dateOfBirth;
    private String email;
    private String description;
    @Transient
    private Integer age;
    @OneToMany( targetEntity = UserRole.class
            ,cascade = CascadeType.ALL,
            fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id"
            ,referencedColumnName = "id")
    private List<UserRole> userRole;


    public User(){}

    public User(Long id, String firstName
            , String secondName, LocalDate dateOfBirth
            , String email, String description
            ,List<UserRole> userRole) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.description = description;
        this.userRole=userRole;
    }

    public User(String firstName, String secondName
            , LocalDate dateOfBirth, String email
            , String description,List<UserRole> userRole) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.description = description;
        this.userRole=userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth,LocalDate.now()).getYears();
    }

    public List<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(List<UserRole> userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", age=" + age +
                ", userRole=" + userRole +
                '}';
    }
}
