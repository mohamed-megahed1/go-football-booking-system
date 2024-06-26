package com.gofootballbookingsystem.service.Imp;

import com.gofootballbookingsystem.dto.PlaygroundDtoViewToCustomer;
import com.gofootballbookingsystem.entity.PlayGround;
import com.gofootballbookingsystem.mapper.PlayGroundDtoToViewCustomerMapper;
import com.gofootballbookingsystem.repository.PlayGroundRepository;
import com.gofootballbookingsystem.repository.spec.PlaygroundSpec;
import com.gofootballbookingsystem.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImp implements SearchService {

    @Autowired
    private  PlayGroundRepository playGroundRepository;
    @Autowired
    private  PlayGroundDtoToViewCustomerMapper playGroundMapper;



    @Override
    public List<PlaygroundDtoViewToCustomer> getAllPlayground() {

        List<PlayGround>playGrounds=playGroundRepository.findAll();
        List<PlaygroundDtoViewToCustomer>viewToCustomers=playGroundMapper.fromPlayGroundToPlayGroundDtoList(playGrounds);

        return viewToCustomers;
    }

    @Override
    public List<PlaygroundDtoViewToCustomer> searchPlayground(PlaygroundDtoViewToCustomer searchPlayground) {
        Specification spec=new PlaygroundSpec(searchPlayground);
        List<PlayGround>playGrounds=playGroundRepository.findAll(spec);

        List<PlaygroundDtoViewToCustomer>viewToCustomers=playGroundMapper.fromPlayGroundToPlayGroundDtoList(playGrounds);

        return viewToCustomers;
    }

}
