package com.gofootballbookingsystem.repository.spec;

import com.gofootballbookingsystem.dto.PlaygroundDtoViewToCustomer;
import com.gofootballbookingsystem.entity.PlayGround;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public class PlaygroundSpec implements Specification<PlayGround> {

    private PlaygroundDtoViewToCustomer playgroundSearch;

    public PlaygroundSpec(PlaygroundDtoViewToCustomer playgroundSearch) {
        this.playgroundSearch = playgroundSearch;
    }

    @Override
    public Predicate toPredicate(Root<PlayGround> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {

        List<Predicate>predicates=new ArrayList<>();

        //playground name
        if(playgroundSearch.getName() != null || !playgroundSearch.getName().isEmpty()){
            predicates.add(cb.like(root.get("name"),"%" +playgroundSearch.getName()+ "%"));
        }
        //playground city
        if(playgroundSearch.getCity() != null || !playgroundSearch.getCity().isEmpty()){
            predicates.add(cb.like(root.get("city"),"%" +playgroundSearch.getCity()+ "%"));
        }
        //playground getNeighborhood
        if(playgroundSearch.getNeighborhood() != null || !playgroundSearch.getNeighborhood().isEmpty()){
            predicates.add(cb.like(root.get("neighborhood"),"%" +playgroundSearch.getNeighborhood()+ "%"));
        }
        //playground getTeamCapacity
        if(playgroundSearch.getTeamCapacity() != null ){
            predicates.add(cb.equal(root.get("teamCapacity"),playgroundSearch.getTeamCapacity()));
        }





        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
