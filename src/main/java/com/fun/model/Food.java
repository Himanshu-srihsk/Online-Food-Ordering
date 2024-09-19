package com.fun.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long price;
    @ToString.Exclude
    @ManyToOne
    private Category foodCategory;
    @Column(length = 1000)
    @ElementCollection
    private List<String> images;
    private boolean available;
    @ToString.Exclude
    @ManyToOne
//    @JsonIgnore
    private Restaurant restaurant;
    private boolean  isVegeterian;
    private boolean  isSeasonal;
    @ManyToMany
    private List<IngredientsItem> ingredientsItems = new ArrayList<>();
    private Date creationDate;

}
