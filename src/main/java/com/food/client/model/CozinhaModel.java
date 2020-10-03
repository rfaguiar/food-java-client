package com.food.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CozinhaModel(@JsonProperty("id") Long id,
                           @JsonProperty("nome") String nome) {
}
