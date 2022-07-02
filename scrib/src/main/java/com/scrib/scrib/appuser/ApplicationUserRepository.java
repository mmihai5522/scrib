package com.scrib.scrib.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {

    ApplicationUser findApplicationUserByuserName(String name);

    ApplicationUser findApplicationUserByEmail(String email);

//    ApplicationUser register(String firstName
//    , String lastName, String userName, String email);

    @Override
    List<ApplicationUser> findAll();


}