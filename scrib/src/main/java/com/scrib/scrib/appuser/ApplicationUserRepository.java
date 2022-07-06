package com.scrib.scrib.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
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


}