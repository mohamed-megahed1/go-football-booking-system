package com.gofootballbookingsystem.service;

import com.gofootballbookingsystem.base.UserServiceBase;
import com.gofootballbookingsystem.dto.OwnerDTO;



public interface OwnerService extends UserServiceBase {


   // public OwnerDTO createNewOwner(OwnerDTO ownerDTO);

    public OwnerDTO updatePersonalData(OwnerDTO ownerDTO,Long owner_id);

    public void deleteOwnerByUserName(String userName);

    public OwnerDTO getAllDetailsByUserName(String userName);



}
