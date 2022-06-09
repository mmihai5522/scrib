package com.scrib.scrib.permission;


import com.scrib.scrib.role.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="permission")
public class Permission {

    @Id
    @SequenceGenerator(
            name = "permission_sequence",
            sequenceName = "permission_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "permission_sequence"
    )
    private Long id;
    private PermissionType permissionType;
    @OneToMany(targetEntity = Role.class
            ,cascade = CascadeType.ALL)
    @JoinColumn(name = "perm_id"
            ,referencedColumnName = "id")
    private List<Role> role;


    public Permission(){}

    public Permission(Long id, PermissionType permissionType,List<Role> role) {
        this.id = id;
        this.permissionType = permissionType;
        this.role=role;
    }

    public Permission(PermissionType permissionType, List<Role> role) {

        this.permissionType = permissionType;
        this.role=role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionType=" + permissionType +
                ", role=" + role +
                '}';
    }
}
