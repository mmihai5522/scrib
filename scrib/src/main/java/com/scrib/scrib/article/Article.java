package com.scrib.scrib.article;

import com.scrib.scrib.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @SequenceGenerator(
            name = "articles_sequence",
            sequenceName = "articles_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "articles_sequence"
    )
    private Long id;
    private String title;
    private String body;
    private LocalDate publishingDate;
    @ElementCollection(targetClass=String.class)
    private List<String> bibliography;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public User getUser() {
        return user;
    }

    public Article (){}

    public Article(Long id, String title, String body, LocalDate publishingDate, List<String> bibliography) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.publishingDate = publishingDate;
        this.bibliography = bibliography;
    }

    public Article(String title, String body, LocalDate publishingDate, List<String> bibliography) {
        this.title = title;
        this.body = body;
        this.publishingDate = publishingDate;
        this.bibliography = bibliography;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getPublishingDate() {
        return Period.between(this.publishingDate,LocalDate.now()).getMonths();
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }

    public List<String> getBibliography() {
        return bibliography;
    }

    public void setBibliography(List<String> bibliography) {
        this.bibliography = bibliography;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", publishingDate=" + publishingDate +
                ", bibliography=" + bibliography +
                '}';
    }
}
