package com.food.client;

import com.food.client.api.RestauranteClient;
import org.springframework.web.client.RestTemplate;

public class ListagemRestauranteMain {

    public static void main(String[] args) {
        RestauranteClient restauranteClient = new RestauranteClient("http://localhost:8080", new RestTemplate());
        restauranteClient.listar().forEach(System.out::println);
    }
}
