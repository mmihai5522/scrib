package com.scrib.scrib.role;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter

@AllArgsConstructor
@Entity
@ToString
@Table(name = "applicationUsersRole")
public class ApplicationUserRole {

    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    private Long id;
    private String name;

    public ApplicationUserRole(){}

}
