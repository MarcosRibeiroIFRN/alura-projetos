package com.example.screenmatch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.screenmatch.model.DadosEpisodio;
import com.example.screenmatch.model.DadosSerie;
import com.example.screenmatch.model.DadosTemporada;
import com.example.screenmatch.service.ConsumoApi;
import com.example.screenmatch.service.ConverteDados;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args)throws Exception{
		var consumoApi=new ConsumoApi();
		//obtem os dados da série na variável json
		var json=consumoApi.obterDados("https://omdbapi.com/?t=the+boys&apikey=16dd5b65");
		// imprime os dados na variável json
		System.out.println(json);
		// instancia a um objeto conversor
		ConverteDados conversor=new ConverteDados();
		//usa o conversor para converter os dados da série e alocar em um objeto DadosSerie
		DadosSerie dados=conversor.obterDados(json,DadosSerie.class);
		System.out.println(dados + "\n");
		//usa o conversor para converter os dados de um episódio e alocar em um objeto DadosSerie
		json=consumoApi.obterDados("https://omdbapi.com/?t=the+boys&season=1&episode=2&apikey=16dd5b65");
		DadosEpisodio dadosEpisodio=conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio+"\n");

		List<DadosTemporada> temporadas=new ArrayList<>();

		for(int i=1;i<=dados.totaltemporadas();i++){
			json=consumoApi.obterDados("https://omdbapi.com/?t=the+boys&season="+i+"&apikey=16dd5b65");
			DadosTemporada dadosTemporada= conversor.obterDados(json,DadosTemporada.class);
			temporadas.add(dadosTemporada);
		};
		temporadas.forEach(System.out::println);
	}
}
