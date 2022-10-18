package com.scrib.scrib.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {

    ApplicationUser findApplicationUserByUserName(String name);

    ApplicationUser findApplicationUserByEmail(String email);

    @Override
    List<ApplicationUser> findAll();

    @Transactional
    @Modifying
    @Query("UPDATE ApplicationUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(final String email);


}