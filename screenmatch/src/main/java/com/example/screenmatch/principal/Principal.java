package com.example.screenmatch.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.example.screenmatch.model.DadosEpisodio;
import com.example.screenmatch.model.DadosSerie;
import com.example.screenmatch.model.DadosTemporada;
import com.example.screenmatch.model.Episodio;
import com.example.screenmatch.service.ConsumoApi;
import com.example.screenmatch.service.ConverteDados;

public class Principal {

        private Scanner leitura =new Scanner(System.in);
        private final String  endereco="https://omdbapi.com/?t=";
        private final String  apiKey="&apikey=16dd5b65";
        private ConsumoApi consumo =new ConsumoApi();
        private ConverteDados conversor=new ConverteDados();


        public void exibeMenu(){

            System.out.println("Digite o nome da série");
            var nomeSerie=leitura.nextLine();

            var json = consumo.obterDados(endereco + nomeSerie.replace(" ","+") + apiKey);
            DadosSerie dados=conversor.obterDados(json,DadosSerie.class);
            
            System.out.println(dados);
            List<DadosTemporada> temporadas=new ArrayList<>();

		    for(int i=1;i<=dados.totaltemporadas();i++){
			json=consumo.obterDados(endereco + nomeSerie.replace(" ","+") +"&season="+i+ apiKey);
			DadosTemporada dadosTemporada= conversor.obterDados(json,DadosTemporada.class);
			temporadas.add(dadosTemporada);
		    };
		    // temporadas.forEach(System.out::println);
            // for (int i = 0;i<dados.totaltemporadas();i++){
            //     List<DadosEpisodio> episodiosTemporada=temporadas.get(i).episodios();
            //     for (int j=0;j<episodiosTemporada.size();j++){
            //         System.out.println(episodiosTemporada.get(j).titulo());
            //     }
            // }
            //funções lambidas cada vez que for interar em uma coleção
            temporadas.forEach(t->t.episodios().forEach(e-> System.out.println(e.titulo())));
            List<DadosEpisodio> dadosEpisodios=temporadas.stream()
            .flatMap(t->t.episodios().stream())
            .collect(Collectors.toList());

            System.out.println("\n Top 5 episódios");
            dadosEpisodios.stream()
            .filter(e->!e.avaliacao().equalsIgnoreCase("N/A"))
            .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
            .limit(5)
            .forEach(System.out::println);

            
            // List<String> nomes = Arrays.asList("Jacque","Iasmin","Paulo","Rodrigo");
            // nomes.stream().sorted().filter(n->n.startsWith("P")).map(n->n.toUpperCase()).limit(3).forEach(System.out::println);
            
            List<Episodio> episodios=temporadas.stream()
            .flatMap(t->t.episodios().stream()
            .map(d-> new Episodio(t.numero(),d))
            ).collect(Collectors.toList());

            episodios.forEach(System.out::println);
        }
}
