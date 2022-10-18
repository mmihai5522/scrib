package com.scrib.scrib.registration.token;

import com.scrib.scrib.appuser.ApplicationUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "usersConfirmationTable")
public class ConfirmationToken implements Serializable {

    @SequenceGenerator(
            name = "confirmationToken_sequence",
            sequenceName = "confirmationToken_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmationToken_sequence"
    )
    private Long id;
    @Column(nullable=false)
    private String token;
    @Column(nullable=false)
    private LocalDateTime createdAt;
    @Column(nullable=false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(
            nullable = false
            ,name = "app_sId"
            ,foreignKey = @ForeignKey(name = "token_users_fk")
    )
    private ApplicationUser applicationUser;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, ApplicationUser applicationUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.applicationUser = applicationUser;
    }




}
