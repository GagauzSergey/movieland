package com.movieland.mvc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;


@Entity
@Repository
@Data
@NoArgsConstructor(force = true)
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            targetEntity = Movie.class)
    @JoinTable(name = "movie_country", joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @JsonBackReference
    List<Movie> movieCountryList;
}
