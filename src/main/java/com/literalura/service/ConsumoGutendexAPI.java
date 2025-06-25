package com.literalura.service;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Service
public class ConsumoGutendexAPI {

    private static final String BASE_URL = "https://gutendex.com/books/?";
    private final HttpClient client = HttpClient.newHttpClient();

    public String buscarLivros(String nomeLivro) {
        try {
            String url = BASE_URL + "search=" + nomeLivro.replace(" ", "+");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String buscarLivrosComParametros(String parametros) {
        try {
            String url = BASE_URL + parametros;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
