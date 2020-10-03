package com.food.client;

import com.food.client.api.RestauranteClient;
import com.food.client.api.exception.ClientApiException;
import org.springframework.web.client.RestTemplate;

public class ListagemRestauranteMain {

    public static void main(String[] args) {
        try {
            RestauranteClient restauranteClient = new RestauranteClient("http://localhost:8080", new RestTemplate());
            restauranteClient.listar().forEach(System.out::println);
        } catch (ClientApiException e) {
            if (e.getProblem() != null) {
                System.out.println(e.getProblem().userMessage());
            } else {
                System.out.println("Erro desconhecido");
                e.printStackTrace();
            }
        }
    }
}
