package com.gofootballbookingsystem.repository;


import com.gofootballbookingsystem.base.UserBaseRepository;
import com.gofootballbookingsystem.entity.Owner;
import com.gofootballbookingsystem.entity.PlayGround;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OwnerRepository extends UserBaseRepository<Owner> {


    @EntityGraph(attributePaths = "playGround")
    Owner findByUserName(String userName);

    @Transactional
    void deleteOwnerByUserName(String userName);

   Owner findByUserNameOrEmail(String userName, String email);
  //  public List<PlayGround> findAllPlayGroundByOwnerId(Long owner_id);

  //  public PlayGround findPlayGroundById(Long playground_id);
}
