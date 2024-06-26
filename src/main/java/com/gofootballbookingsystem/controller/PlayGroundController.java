package com.gofootballbookingsystem.controller;

import com.gofootballbookingsystem.dto.PlayGroundDTO;
import com.gofootballbookingsystem.mapper.PlayGroundMapper;
import com.gofootballbookingsystem.service.PlayGroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/playground")
public class PlayGroundController {

    @Autowired
    private PlayGroundService playGroundService;


    @Autowired
    private PlayGroundMapper playGroundMapper;
    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/addPlayground/{user_name}")
    public ResponseEntity<PlayGroundDTO>addPlayGroundByOwnerUserName(@RequestBody PlayGroundDTO playGroundDTO,
                                                                  @PathVariable("user_name") String username){

       PlayGroundDTO playGroundDTO1= playGroundService.addPlayGroundByOwnerUserName(playGroundDTO,username);

       return new ResponseEntity<>(playGroundDTO1, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/getAllPlayGround/{owner_id}")
    public ResponseEntity<List<PlayGroundDTO>> getAllPlayGroundByOwnerId(@PathVariable("owner_id")Long owner_id){


        List<PlayGroundDTO>playGroundDTOList=playGroundService.getAllPlayGroundByOwnerId(owner_id);


        return new ResponseEntity<>(playGroundDTOList,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/getPlayGroundIdAndOwnerId/{playground_id}")
    public ResponseEntity<PlayGroundDTO>getPlayGroundIdAndOwnerId(@PathVariable("playground_id") Long playground_id)
    {

        PlayGroundDTO playGroundDTO=playGroundService.getSpecifficPlaygroundByIdAndOwnerId(playground_id);

        return new ResponseEntity<>( playGroundDTO, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/deleteplayground/{playgroundId}")
    public ResponseEntity<String>deletePlaygroundById(@PathVariable("playgroundId") Long playgroundId){
        playGroundService.deletePlaygroundById(playgroundId);
        return new ResponseEntity<>("playground deleted",HttpStatus.OK);
    }
    @PreAuthorize("hasRole('OWNER')")
    @PutMapping("/updateplayground/{owner_id},{playground_id}")
    public ResponseEntity<?>updatePlayground(@PathVariable("playground_id") Long playground_id ,
                                             @PathVariable("owner_id") Long owner_id ,
                                             @RequestBody PlayGroundDTO playGroundDTO){

        PlayGroundDTO playGroundDTO1=playGroundService.updatePlayGroundByPlaygroundId(
                playground_id,owner_id,playGroundDTO);
        return ResponseEntity.ok(playGroundDTO1);
    }


}
