package com.example.screenmatch.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.screenmatch.model.DadosSerie;
import com.example.screenmatch.model.DadosTemporada;
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
		    temporadas.forEach(System.out::println);
        }
}
