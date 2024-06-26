package com.gofootballbookingsystem.service;

import com.gofootballbookingsystem.dto.PlaygroundDtoViewToCustomer;

import java.util.List;

public interface SearchService {

    List<PlaygroundDtoViewToCustomer> getAllPlayground();

    List<PlaygroundDtoViewToCustomer> searchPlayground(PlaygroundDtoViewToCustomer searchPlayground);

}
