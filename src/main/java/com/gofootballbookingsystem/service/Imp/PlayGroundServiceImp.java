package com.gofootballbookingsystem.service.Imp;

import com.gofootballbookingsystem.dto.PlayGroundDTO;
import com.gofootballbookingsystem.entity.Owner;
import com.gofootballbookingsystem.entity.PlayGround;
import com.gofootballbookingsystem.exception.ResourceNotFoundException;
import com.gofootballbookingsystem.mapper.OwnerMapper;
import com.gofootballbookingsystem.mapper.PlayGroundMapper;
import com.gofootballbookingsystem.repository.OwnerRepository;
import com.gofootballbookingsystem.repository.PlayGroundRepository;
import com.gofootballbookingsystem.service.PlayGroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayGroundServiceImp implements PlayGroundService {



    private final PlayGroundRepository playGroundRepository;


    private final OwnerRepository ownerRepository ;


    private final PlayGroundMapper playGroundMapper;


    private final OwnerMapper ownerMapper;


    @Override
    public PlayGroundDTO addPlayGroundByOwnerUserName(PlayGroundDTO playGroundDTO, String user_name) {

        Owner owner=ownerRepository.findByUserName(user_name);

        if(owner==null){
            throw new ResourceNotFoundException("Owner with username  :"+user_name+" not found");
        }else{

            PlayGround playGround=playGroundMapper.fromPlayGroundDtoToPlayGround(playGroundDTO);

         //   playGround.setOwner(owner);

            playGroundRepository.save(playGround);
            owner.addPlayGround(playGround);
            ownerRepository.save(owner);


            return playGroundMapper.fromPlayGroundToPlayGroundDto(playGround);
        }


    }

    @Override
    public List<PlayGroundDTO> getAllPlayGroundByOwnerId(Long owner_id) {


        if (ownerRepository.existsById(owner_id)) {
            List<PlayGround> playGroundList= playGroundRepository.findByOwnerId(owner_id);
            List<PlayGroundDTO>playGroundDTOList=playGroundMapper.fromPlayGroundToPlayGroundDtoList(playGroundList);
            return playGroundDTOList;
        }else {
            throw new ResourceNotFoundException("Owner with id  :"+owner_id+" not found");
        }

    }

    @Override
    public PlayGroundDTO getSpecifficPlaygroundByIdAndOwnerId(Long playground_id) {


        PlayGround playGround=playGroundRepository.findById(playground_id).
                orElseThrow(() -> new ResourceNotFoundException("PlayGround not found"));

            return playGroundMapper.fromPlayGroundToPlayGroundDto(playGround);



    }

    @Override
    public void deletePlaygroundById(Long playgroundId) {
        PlayGround playGround=playGroundRepository.findById(playgroundId).orElseThrow(
                () -> new ResourceNotFoundException("playground not found"));
        playGroundRepository.deleteById(playgroundId);

    }

    @Override
    public PlayGroundDTO updatePlayGroundByPlaygroundId(Long playground_id,Long owner_id ,PlayGroundDTO playGroundDTO) {

        PlayGround playGround=playGroundRepository.findById(playground_id).
                orElseThrow(() -> new ResourceNotFoundException("PlayGround not found"));

        if (playGround.getOwnerId()!=owner_id){
            throw new ResourceNotFoundException("PlayGround not found");
        }
        PlayGround playGround1 = playGroundMapper.updatePlayGroundFromDTO(playGroundDTO, playGround);

        PlayGround playGround2 = playGroundRepository.save(playGround1);

        return playGroundMapper.fromPlayGroundToPlayGroundDto(playGround2);

        }


    }



