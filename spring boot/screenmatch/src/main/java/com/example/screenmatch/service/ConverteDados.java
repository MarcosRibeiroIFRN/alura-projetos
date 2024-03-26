package com.example.screenmatch.service;

// import com.example.screenmatch.model.DadosSerie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper =new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        // TODO Auto-generated method stub

        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
