package com.scrib.scrib.role;



import com.scrib.scrib.userRole.UserRole;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roleTable")
public class Role {

    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    private Long id;
    private RoleType roleType;

    @OneToMany(targetEntity = UserRole.class
            ,cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id"
            ,referencedColumnName = "id")
    private List<UserRole> UserRole;

    public Role() {}

    public Role(RoleType roleType, List<UserRole> userRole) {
        this.roleType = roleType;
        UserRole = userRole;
    }

    public Role(Long id, RoleType roleType, List<UserRole> userRole) {
        this.id = id;
        this.roleType = roleType;
        UserRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public List<com.scrib.scrib.userRole.UserRole> getUserRole() {
        return UserRole;
    }

    public void setUserRole(List<com.scrib.scrib.userRole.UserRole> userRole) {
        UserRole = userRole;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleType=" + roleType +
                ", UserRole=" + UserRole +
                '}';
    }
}
