package com.scrib.scrib.appuser;

import com.scrib.scrib.role.ApplicationUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {


    ApplicationUser findApplicationUserByEmail(String email);


}