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



    private Long role_id;

    public UserRole(){}

    public UserRole(Long id) {
        this.id = id;
    }

    public UserRole(Long id, Long role_id) {
        this.id = id;
        this.role_id = role_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
}
