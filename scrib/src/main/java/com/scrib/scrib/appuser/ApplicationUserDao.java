package com.scrib.scrib.appuser;

import java.util.Optional;


public interface ApplicationUserDao {

    public Optional<ApplicationUser> selectApplicationuserByUser(String userName);
}
