package com.gofootballbookingsystem.service.Imp;

import com.gofootballbookingsystem.base.UserBaseRepository;
import com.gofootballbookingsystem.dto.OwnerDTO;
import com.gofootballbookingsystem.entity.Customer;
import com.gofootballbookingsystem.entity.Owner;
import com.gofootballbookingsystem.exception.ResourceNotFoundException;
import com.gofootballbookingsystem.mapper.OwnerMapper;
import com.gofootballbookingsystem.repository.CustomerRepository;
import com.gofootballbookingsystem.repository.OwnerRepository;
import com.gofootballbookingsystem.service.OwnerService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class OwnerServiceImp implements OwnerService {


    private final OwnerRepository ownerRepository;

    private final OwnerMapper ownerMapper;

    private final CustomerRepository customerRepository;


    private final PasswordEncoder  passwordEncoder;
    @Override
    public boolean isUsernameExists(String username) {
        return ownerRepository.existsByUserName(username);
    }
    @Override
    public boolean isEmailExists(String email) {
        return ownerRepository.existsByEmail(email);
    }

    @Override
    public boolean isIdExists(Long id) {
        return ownerRepository.existsById(id);
    }
 /*   @Override
    public OwnerDTO createNewOwner(OwnerDTO ownerDTO) {


        if (customerRepository.existsByEmail(ownerDTO.getEmail()) ||
                customerRepository.existsByUserName(ownerDTO.getUserName())){

            throw new RuntimeException("the email or user_name is already exists in the system");

        }else {
            Owner owner=ownerMapper.fromOwnerDtoToOwner(ownerDTO);

            Owner savedOwner= ownerRepository.save(owner);

            return ownerMapper.fromOwnerToOwnerDto(savedOwner);
        }

    }*/

    @Override
    public OwnerDTO updatePersonalData(OwnerDTO ownerDTO, Long owner_id) {

        if (!ownerDTO.getPassword().isEmpty() && ownerDTO.getPassword()!=null){
            ownerDTO.setPassword(passwordEncoder.encode(ownerDTO.getPassword()));
        }
        Owner owner1= ownerRepository.findById(owner_id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id : "+owner_id));

        if (customerRepository.existsByEmail(ownerDTO.getEmail()) ||
                customerRepository.existsByUserName(ownerDTO.getUserName())){
            throw new RuntimeException("the email or user_name is already exists in the system");

        }else {
            Owner owner = ownerMapper.updateOwnerFromDTO(ownerDTO, owner1);

            Owner updatedOwner = ownerRepository.save(owner1);

            return ownerMapper.fromOwnerToOwnerDto(updatedOwner);
        }
    }


    @Override
    public void deleteOwnerByUserName(String userName) {

        Owner owner=ownerRepository.findByUserName(userName);

        if (owner==null){
            throw new ResourceNotFoundException("the owner with user name : "+userName+" not found.");
        }else {
            ownerRepository.deleteOwnerByUserName(userName);
        }

    }

    @Override
    public OwnerDTO getAllDetailsByUserName(String userName) {

        Owner owner=ownerRepository.findByUserName(userName);



        if(owner==null){
            throw new ResourceNotFoundException("Owner with username  :"+userName+" not found");
        }else{
            OwnerDTO ownerDTO=ownerMapper.fromOwnerToOwnerDto(owner);
            return ownerDTO;
        }


    }


}
