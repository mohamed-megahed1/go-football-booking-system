package com.gofootballbookingsystem.repository;

import com.gofootballbookingsystem.dto.PlayGroundDTO;
import com.gofootballbookingsystem.entity.PlayGround;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayGroundRepository extends JpaRepository<PlayGround,Long> , JpaSpecificationExecutor<PlayGround> {


  //  public List<PlayGround> findAllPlayGroundByOwnerId(Long owner_id);

   // public PlayGround findPlayGroundByIdAndOwnerId(Long playground_id, Long owner_id);
   List<PlayGround> findByOwnerId(Long ownerId);

}
