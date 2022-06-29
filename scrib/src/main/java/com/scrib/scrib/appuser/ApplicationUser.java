package com.scrib.scrib.appuser;

import com.scrib.scrib.role.ApplicationUserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "applicationUsers")
public class ApplicationUser implements UserDetails {

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
    private Long id;
    private String firstName;
    private String lastName;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_roles_fk"
            , referencedColumnName = "id"
            ,nullable = false)
    private List<ApplicationUserRole> applicationUserRole;
    private String email;
    private Boolean locked = false;
    private Boolean enabled = false;


    public ApplicationUser(String firstName
            , String lastName
            , String password
            , String email
            , List <ApplicationUserRole> applicationUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.applicationUserRole = applicationUserRole;

    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {

            SimpleGrantedAuthority simulate=new SimpleGrantedAuthority(applicationUserRole.toString());

            return  List.of(simulate);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }




}
