package com.gofootballbookingsystem.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;



@NoRepositoryBean
public interface UserBaseRepository<T extends BaseUser> extends JpaRepository<T,Long> {

    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);

   // BaseUser findByUserNameOrEmail(String userName, String email);

}
