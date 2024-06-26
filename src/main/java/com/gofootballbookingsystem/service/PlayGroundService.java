package com.gofootballbookingsystem.service;


import com.gofootballbookingsystem.dto.PlayGroundDTO;
import java.util.List;

public interface PlayGroundService {

    public PlayGroundDTO addPlayGroundByOwnerUserName(PlayGroundDTO playGroundDTO, String id);

    public List<PlayGroundDTO> getAllPlayGroundByOwnerId(Long owner_id);

    public PlayGroundDTO getSpecifficPlaygroundByIdAndOwnerId(Long playground_id);

    public void deletePlaygroundById(Long playgroundId);

    public PlayGroundDTO updatePlayGroundByPlaygroundId(Long playground_id,Long owner_id, PlayGroundDTO playGroundDTO);


}
