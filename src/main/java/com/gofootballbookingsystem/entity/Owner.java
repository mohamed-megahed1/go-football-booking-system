package com.gofootballbookingsystem.entity;

import com.gofootballbookingsystem.base.BaseUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@DiscriminatorValue("owner")
public class Owner extends BaseUser {


    @OneToMany
    @JoinColumn(name = "owner_id")
    private Set<PlayGround> playGround;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "owner_roles")
    private Set<Role> roles=new HashSet<>();



    private Double bending=0.0;

    public void addPlayGround(PlayGround playGround){
        this.playGround.add(playGround);
    }

}
