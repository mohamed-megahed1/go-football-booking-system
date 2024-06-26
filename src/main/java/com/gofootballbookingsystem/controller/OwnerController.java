package com.gofootballbookingsystem.controller;

import com.gofootballbookingsystem.dto.OwnerDTO;
import com.gofootballbookingsystem.exception.ResourceNotFoundException;
import com.gofootballbookingsystem.exception.UsernameAlreadyExistsException;
import com.gofootballbookingsystem.mapper.OwnerMapper;
import com.gofootballbookingsystem.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {


    @Autowired
    private  OwnerService ownerService;

    @Autowired
    private  OwnerMapper ownerMapper;


  /*  @PostMapping("/createOwner")
    public ResponseEntity<?>createNewOwner(@RequestBody OwnerDTO ownerDTO){

        try {
            if (ownerService.isUsernameExists(ownerDTO.getUserName()) || ownerService.isEmailExists(ownerDTO.getEmail())) {
                throw new UsernameAlreadyExistsException("Username or email already exists");
            }
            OwnerDTO ownerDTO1=ownerService.createNewOwner(ownerDTO);
             return ResponseEntity.ok(ownerDTO1);
        }catch (UsernameAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or email already exists");
        }

    }*/
  @PreAuthorize("hasRole('OWNER')")
    @PutMapping("/updatePersonal/{ownerId}")
    public ResponseEntity<?>updataPersonal(@RequestBody OwnerDTO ownerDTO,@PathVariable("ownerId") Long ownerId){

        try {
            if (!ownerService.isIdExists(ownerId)) {
                throw new ResourceNotFoundException("this user is not exists");
            }

            OwnerDTO ownerDTO1=ownerService.updatePersonalData(ownerDTO,ownerId);
            return ResponseEntity.ok(ownerDTO1);
        }catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("this user is not exists");
        }

    }
    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/deleteOwner/{userName}")
    public ResponseEntity<String>deleteOwnerByUserName(@PathVariable String userName){

        ownerService.deleteOwnerByUserName(userName);
        return new ResponseEntity<>("owner deleted succesfully.",HttpStatus.OK);
    }
    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/getownerdetails/{userName}")
    public ResponseEntity<OwnerDTO>getOwnerByUserName(@PathVariable String userName){

       OwnerDTO ownerDTO=ownerService.getAllDetailsByUserName(userName);
        return new ResponseEntity<>(ownerDTO,HttpStatus.OK);
    }

}
