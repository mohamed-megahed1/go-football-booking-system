package com.gofootballbookingsystem.controller;

import com.gofootballbookingsystem.dto.PlaygroundDtoViewToCustomer;
import com.gofootballbookingsystem.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private  SearchService searchService;

    @GetMapping("/getAllPlayGrounds")
    public ResponseEntity<List<PlaygroundDtoViewToCustomer>> getAllPlayGrounds() {

        List<PlaygroundDtoViewToCustomer> playGroundDTOList= searchService.getAllPlayground();
        return new ResponseEntity<>(playGroundDTOList, HttpStatus.OK);

    }
    @PostMapping("/spec")
    public ResponseEntity<List<PlaygroundDtoViewToCustomer>> searchPlayGrounds(@RequestBody PlaygroundDtoViewToCustomer search) {

        List<PlaygroundDtoViewToCustomer> playGroundDTOList= searchService.searchPlayground(search);
        return new ResponseEntity<>(playGroundDTOList, HttpStatus.OK);

    }
}
