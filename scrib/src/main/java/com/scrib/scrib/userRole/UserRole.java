package com.scrib.scrib.userRole;

import javax.persistence.*;


@Entity
@Table(name="userRoleTable")
public class UserRole {

    @Id
    @SequenceGenerator(
            name = "userRole_sequence",
            sequenceName = "userRole_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userRole_sequence"
    )
    private Long id;

    public UserRole(){}

    public UserRole(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
