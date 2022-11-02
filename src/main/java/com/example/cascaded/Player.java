package com.example.cascaded;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Set is apparently better practice :shrug:
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private Set<Item> items;

    public Player() {
        this.items = new HashSet<>() ;
    }

    public void addItem(Item item){
        // update both sides of the relationship
        item.setPlayer(this);
        this.items.add(item);
    }

    public void removeItem(Item item){
        // update both sides of the relationship
        item.setPlayer(null);
        this.items.remove(item);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
