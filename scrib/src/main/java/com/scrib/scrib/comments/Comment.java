package com.scrib.scrib.comments;

import com.scrib.scrib.article.Article;
import com.scrib.scrib.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @SequenceGenerator(
            name = "comments_sequence",
            sequenceName = "comments_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comments_sequence"
    )

    private Long id;
    private String body;
    private LocalDate creationDate;

    @OneToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;

    public User getUser() {
        return user;
    }

    public Article getArticle() {
        return article;
    }

    public Comment(){}

    public Comment(Long id, String body, LocalDate creationDate, User user, Article article) {
        this.id = id;
        this.body = body;
        this.creationDate = creationDate;
        this.user = user;
        this.article = article;
    }

    public Comment(String body, LocalDate creationDate) {
        this.body = body;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
