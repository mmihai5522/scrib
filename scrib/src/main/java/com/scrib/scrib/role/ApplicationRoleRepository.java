package com.scrib.scrib.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRoleRepository extends JpaRepository<ApplicationUserRole,Long> {

    @Query("SELECT r FROM ApplicationUserRole r WHERE r.name=?1")
    ApplicationUserRole findBy(String name);

}
