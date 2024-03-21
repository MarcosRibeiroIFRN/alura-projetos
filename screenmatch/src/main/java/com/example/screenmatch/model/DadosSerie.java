package com.example.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//alias, associa o nome entre parenteses que aparecer no json
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo,
                        @JsonAlias("totalSeasons") Integer totaltemporadas,
                        @JsonAlias("imdbRating") String avaliacao ){
    
}
