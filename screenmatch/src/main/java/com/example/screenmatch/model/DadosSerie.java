package com.example.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//ignorar as informações que nao foram passadas por alias
@JsonIgnoreProperties(ignoreUnknown = true)
//alias, associa o nome entre parenteses que aparecer no json
public record DadosSerie(@JsonAlias("Title") String titulo,
                        @JsonAlias("totalSeasons") Integer totaltemporadas,
                        @JsonAlias("imdbRating") String avaliacao ){
    
}
