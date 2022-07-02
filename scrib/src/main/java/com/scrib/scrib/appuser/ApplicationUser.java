package com.scrib.scrib.appuser;

import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@ToString
@Table(name = "applicationUsers")
@NoArgsConstructor
public class ApplicationUser implements Serializable {

    @SequenceGenerator(
            name = "applicationUser_sequence",
            sequenceName = "applicationUser_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "applicationUser_sequence"
    )
    @Column(nullable = false, updatable = false)
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String password;
    private String userName;
    private String imageUrl;
    @Column
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")

    private Date joinDate;
    @Column
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")

    private Date lastLoginDate;
    @Column
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date lastLoginDateDisplay;
    private String email;
    @Enumerated(EnumType.STRING)
    private ApplicationUserRole applicationUserRole;
    @ElementCollection(targetClass=String.class)
    private Set<ApplicationUserPermission> authorithies;// Admin- can delete,create,update-- user: read,edit
//    @ElementCollection(targetClass=String.class)
   // private String[] authorities;
    private Boolean locked;
    private Boolean enabled;

    public ApplicationUser(String firstName, String lastName, String password, String userId
            , String userName, String imageUrl, @Nullable Date joinDate
            , @Nullable Date lastLoginDate, Date lastLoginDateDisplay, String email
            , ApplicationUserRole applicationUserRole, Set<ApplicationUserPermission> authorithies
            , Boolean locked, Boolean enabled) {
        this.userId=userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userName = userName;
        this.imageUrl = imageUrl;
        this.joinDate = joinDate;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginDateDisplay = lastLoginDateDisplay;
        this.email = email;
        this.applicationUserRole = applicationUserRole;
        this.authorithies = authorithies;
        this.locked = locked;
        this.enabled = enabled;

    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastLoginDateDisplay() {
        return lastLoginDateDisplay;
    }

    public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
        this.lastLoginDateDisplay = lastLoginDateDisplay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ApplicationUserRole getApplicationUserRole() {
        return applicationUserRole;
    }

    public void setApplicationUserRole(ApplicationUserRole applicationUserRole) {
        this.applicationUserRole = applicationUserRole;
    }

    public Set<ApplicationUserPermission> getAuthorithies() {
        return authorithies;
    }

    public void setAuthorithies(Set<ApplicationUserPermission> authorithies) {
        this.authorithies = authorithies;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
